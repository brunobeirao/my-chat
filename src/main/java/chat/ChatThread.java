package chat;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatThread extends Thread {
	private String clientNome = null;
	private DataInputStream dataInputStream = null;
	private PrintStream printStream = null;
	private Socket clientSocket = null;
	private final ChatThread[] threads;
	private int maxClientsCount;
	private static Logger logger = Logger.getLogger("Log");

	public ChatThread(Socket clientSocket, ChatThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}

	@SuppressWarnings("deprecation")
	public void run() {
		int maxClientsCount = this.maxClientsCount;
		ChatThread[] threads = this.threads;

		try {
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			printStream = new PrintStream(clientSocket.getOutputStream());

			String nome = dataInputStream.readLine().trim();

			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] == this) {
						clientNome = "@" + nome;
						break;
					}
				}
			}

			while (true) {
				String linha = dataInputStream.readLine();
				if (linha.startsWith("/sair")) {
					break;
				}

				synchronized (this) {
					for (int i = 0; i < maxClientsCount; i++) {
						if (threads[i] != null && threads[i].clientNome != null) {
							threads[i].printStream.println(nome + " diz: " + linha);
						}
					}
				}
			}

			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null && threads[i] != this && threads[i].clientNome != null) {
						logger.log(Level.INFO, nome + " deixou a conversa"); 
					}
				}
			}

			synchronized (this) {
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] == this) {
						threads[i] = null;
					}
				}
			}

			dataInputStream.close();
			printStream.close();
			clientSocket.close();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Erro ao rodar a thread", ex); 
		}
	}
}
