package chat;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.net.ServerSocket;

public class ChatServer {

	private static ServerSocket serverSocket = null;
	private static Socket clientSocket = null;
	private static final int maxClientsCount = 2;
	private static final ChatThread[] threads = new ChatThread[maxClientsCount];

	public static void main(String args[]) {
		String endereco = args[0];
		String porta = args[1];

		try {
			serverSocket = new ServerSocket(Integer.parseInt(porta.toString()));
		} catch (IOException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null, "Não foi possível conectar em " + endereco + ":"+ porta + ". Servidor já foi iniciado.");
			System.exit(0);
		} catch (NumberFormatException nfex) {
			nfex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Porta não aceita caracteres");
			System.exit(0);
		}

		new ChatClient();
		ChatClient.main(args);
		JOptionPane.showMessageDialog(null, "Aguarde o cliente conectar!");
		while (threads[1] == null) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						(threads[i] = new ChatThread(clientSocket, threads)).start();
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream printStream = new PrintStream(clientSocket.getOutputStream());
					printStream.println("Server too busy. Try later.");
					printStream.close();
					clientSocket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
