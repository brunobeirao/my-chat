package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import chat.ChatClient;
import chat.ChatServer;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfiguracaoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textoNome;
	private JTextField textoEndereco;
	private JTextField textoPorta;
	private String localhost = "localhost";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfiguracaoFrame frame = new ConfiguracaoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ConfiguracaoFrame() {
		setTitle("Configuração");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 263, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panelNome = new JPanel();
		panelNome.setBackground(SystemColor.controlHighlight);
		panelNome.setForeground(Color.BLACK);

		JLabel labelIdentificacao = DefaultComponentFactory.getInstance().createLabel("Identifica\u00E7\u00E3o");
		labelIdentificacao.setHorizontalAlignment(SwingConstants.LEFT);
		labelIdentificacao.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel labelTipoInstancia = DefaultComponentFactory.getInstance().createLabel("Tipo da Inst\u00E2ncia");
		labelTipoInstancia.setFont(new Font("Arial", Font.BOLD, 12));

		JPanel panelTipoInstancia = new JPanel();
		panelTipoInstancia.setBackground(SystemColor.controlHighlight);

		JLabel labelDadosConexao = DefaultComponentFactory.getInstance().createLabel("Dados da conex\u00E3o");
		labelDadosConexao.setFont(new Font("Arial", Font.BOLD, 12));

		JPanel panelDadosConexao = new JPanel();
		panelDadosConexao.setBackground(SystemColor.controlHighlight);

		JButton botaoConectar = new JButton("Conectar");

		botaoConectar.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(19)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(labelDadosConexao)
								.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(panelDadosConexao, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												201, Short.MAX_VALUE)
										.addComponent(panelTipoInstancia, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING,
												gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(labelTipoInstancia, Alignment.LEADING)
														.addComponent(labelIdentificacao, Alignment.LEADING)
														.addComponent(panelNome, Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)))
								.addGap(204))))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(81).addComponent(botaoConectar)
						.addContainerGap(266, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(
						gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
										.addComponent(labelIdentificacao).addGap(7)
										.addComponent(panelNome, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(labelTipoInstancia)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panelTipoInstancia, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(labelDadosConexao)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panelDadosConexao, GroupLayout.PREFERRED_SIZE, 73,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(botaoConectar)
										.addContainerGap(34, Short.MAX_VALUE)));

		JLabel labelEndereco = DefaultComponentFactory.getInstance().createLabel("Endere\u00E7o");
		labelEndereco.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel labelPorta = DefaultComponentFactory.getInstance().createLabel("Porta");
		labelPorta.setFont(new Font("Arial", Font.PLAIN, 12));

		textoEndereco = new JTextField();
		textoEndereco.setColumns(10);

		textoPorta = new JTextField();
		textoPorta.setColumns(10);
		GroupLayout gl_panelDadosConexao = new GroupLayout(panelDadosConexao);
		gl_panelDadosConexao.setHorizontalGroup(gl_panelDadosConexao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDadosConexao.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelDadosConexao.createParallelGroup(Alignment.LEADING)
								.addComponent(labelEndereco).addComponent(labelPorta))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelDadosConexao.createParallelGroup(Alignment.LEADING)
								.addComponent(textoPorta, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE).addComponent(
										textoEndereco, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelDadosConexao.setVerticalGroup(gl_panelDadosConexao.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDadosConexao.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelDadosConexao.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelEndereco).addComponent(textoEndereco, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelDadosConexao.createParallelGroup(Alignment.BASELINE).addComponent(labelPorta)
								.addComponent(textoPorta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE)));
		panelDadosConexao.setLayout(gl_panelDadosConexao);

		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton radioCliente = new JRadioButton("Cliente");
		radioCliente.setFont(new Font("Arial", Font.PLAIN, 12));
		radioCliente.setBackground(SystemColor.controlHighlight);
		JRadioButton radioServidor = new JRadioButton("Servidor");
		radioServidor.setBackground(SystemColor.controlHighlight);
		radioServidor.setFont(new Font("Arial", Font.PLAIN, 12));
		buttonGroup.add(radioCliente);
		buttonGroup.add(radioServidor);

		radioCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoEndereco.setEditable(true);

			}
		});

		radioServidor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoEndereco.setEditable(false);

			}
		});

		GroupLayout gl_panelTipoInstancia = new GroupLayout(panelTipoInstancia);
		gl_panelTipoInstancia.setHorizontalGroup(gl_panelTipoInstancia.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTipoInstancia.createSequentialGroup().addGap(14).addComponent(radioCliente).addGap(10)
						.addComponent(radioServidor).addContainerGap(41, Short.MAX_VALUE)));
		gl_panelTipoInstancia.setVerticalGroup(gl_panelTipoInstancia.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTipoInstancia.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelTipoInstancia.createParallelGroup(Alignment.BASELINE)
								.addComponent(radioServidor).addComponent(radioCliente, GroupLayout.DEFAULT_SIZE, 27,
										Short.MAX_VALUE))
						.addContainerGap()));
		panelTipoInstancia.setLayout(gl_panelTipoInstancia);

		JLabel labelNome = DefaultComponentFactory.getInstance().createLabel("Nome");
		labelNome.setFont(new Font("Arial", Font.PLAIN, 12));

		textoNome = new JTextField();
		textoNome.setColumns(10);
		GroupLayout gl_panelNome = new GroupLayout(panelNome);
		gl_panelNome.setHorizontalGroup(gl_panelNome.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelNome.createSequentialGroup().addContainerGap().addComponent(labelNome)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textoNome, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE).addContainerGap()));
		gl_panelNome.setVerticalGroup(gl_panelNome.createParallelGroup(Alignment.LEADING).addGroup(gl_panelNome
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panelNome.createParallelGroup(Alignment.BASELINE).addComponent(labelNome).addComponent(
						textoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(25, Short.MAX_VALUE)));
		panelNome.setLayout(gl_panelNome);
		contentPane.setLayout(gl_contentPane);

		botaoConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textoNome.getText();
				String instancia = null;
				String endereco = textoEndereco.getText();
				String porta = textoPorta.getText();

				instancia = verificaCampos(radioCliente, radioServidor, nome, instancia, porta);

				if (instancia.equals("Servidor") && !porta.equals("")) {
					endereco = localhost;
					setVisible(false);
					String[] args = new String[] { endereco, porta, nome };
					new ChatServer();
					ChatServer.main(args);
				} else if (instancia.equals("Cliente")) {
					if (endereco.equals(null)) {
						JOptionPane.showMessageDialog(null, "Preencher o endereco IP.");
					} else {
						setVisible(false);
						String[] args = new String[] { endereco, porta, nome };
						new ChatClient();
						ChatClient.main(args);
					}
				}
			}

			private String verificaCampos(JRadioButton radioCliente, JRadioButton radioServidor, String nome,
					String instancia, String porta) {
				if (nome.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencher o nome.");
				}  
				if (radioCliente.isSelected()) {
					instancia = radioCliente.getText();
				} else if (radioServidor.isSelected()) {
					instancia = radioServidor.getText();
				} else {
					JOptionPane.showMessageDialog(null, "Selecionar o tipo de inst�ncia!");
				}

				if (porta.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencher a porta.");
				}
				return instancia;
			}
		});
	}
}