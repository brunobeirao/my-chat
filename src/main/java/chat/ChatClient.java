package chat;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class ChatClient {

	public static void main(String[] args) {
		String endereco = args[0];
		String porta = args[1];
		String nome = args[2];
		Chat chat = new Chat();

		JFrame frame = new ChatFrame(chat);
		frame.setTitle("Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		try {
			chat.IniciaSocket(endereco, Integer.parseInt(porta.toString()), nome);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,"Erro ao iniciar socket");
			System.exit(0);
		}
	}

	static class Chat extends Observable {
		private Socket socket;
		private OutputStream outputStream;

		@Override
		public void notifyObservers(Object arg) {
			super.setChanged();
			super.notifyObservers(arg);
		}

		public void IniciaSocket(String endereco, int porta, String nome) throws IOException {
			socket = new Socket(endereco, porta);
			outputStream = socket.getOutputStream();

			Thread thread = new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));
						String linha;
						outputStream.write((nome + CRLF).getBytes());
						while ((linha = bufferedReader.readLine()) != null)
							notifyObservers(linha);
					} catch (IOException ex) {
						notifyObservers(ex);
					}
				}
			};
			thread.start();
		}

		private static final String CRLF = "\r\n";

		public void envia(String texto) {
			try {
				outputStream.write((texto + CRLF).getBytes());
				outputStream.flush();
			} catch (IOException ex) {
				notifyObservers(ex);
			}
		}

		public void close() {
			try {
				socket.close();
			} catch (IOException ex) {
				notifyObservers(ex);
			}
		}
	}

	static class ChatFrame extends JFrame implements Observer {

		private static final long serialVersionUID = 1L;
		private JTextArea textoConversa;
		private JTextField textoDigitacao;
		private JButton botaoEnviar;
		private JPanel contentPane;
		private Chat chat;

		public ChatFrame(Chat chat) {
			this.chat = chat;
			chat.addObserver(this);
			chatInterface();
		}

		private void chatInterface() {
			textoConversa = new JTextArea(20, 50);
			textoConversa.setEditable(false);
			textoConversa.setLineWrap(true);
			textoConversa.setFont(new Font("Arial", Font.PLAIN, 12));
			textoConversa.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
			add(new JScrollPane(textoConversa), BorderLayout.CENTER);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);

			Box box = Box.createHorizontalBox();
			add(box, BorderLayout.SOUTH);
			textoDigitacao = new JTextField();
			textoDigitacao.setFont(new Font("Arial", Font.PLAIN, 12));
			textoDigitacao.setColumns(10);
			botaoEnviar = new JButton("Enviar");
			botaoEnviar.setFont(new Font("Arial", Font.PLAIN, 12));
			box.add(textoDigitacao);
			box.add(botaoEnviar);

			GroupLayout gl_contentPane = new GroupLayout(contentPane);

			gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
							.createParallelGroup(Alignment.LEADING)
							.addComponent(textoConversa, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textoDigitacao, GroupLayout.PREFERRED_SIZE, 303,
											GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(botaoEnviar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
							.addContainerGap()));
			gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
							.addComponent(textoConversa, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(textoDigitacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(botaoEnviar))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			contentPane.setLayout(gl_contentPane);

			ActionListener sendListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String str = textoDigitacao.getText();
					if (str != null && str.trim().length() > 0)
						chat.envia(str);
					textoDigitacao.selectAll();
					textoDigitacao.requestFocus();
					textoDigitacao.setText("");
				}
			};
			textoDigitacao.addActionListener(sendListener);
			botaoEnviar.addActionListener(sendListener);

			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					chat.close();
				}
			});
		}

		public void update(Observable o, Object arg) {
			final Object finalArg = arg;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					textoConversa.append(finalArg.toString());
					textoConversa.append("\n");
				}
			});
		}
	}
}