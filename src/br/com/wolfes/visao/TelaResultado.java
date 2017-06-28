package br.com.wolfes.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.wolfes.simplex.Simplex;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaResultado {

	private JFrame frame;
	JTextArea textArea;
	private JButton btnVoltar;
	int linha;
	int coluna;
	int tipo;
	private JLabel lblResultado;

	/**
	 * Create the application.
	 */
	public TelaResultado(int linha, int coluna,double[] objetivo, double[][] restricoes, double[] valorRestricao, int tipo) {
		this.linha = linha;
		this.coluna = coluna;
		this.tipo = tipo;
		Simplex simplex = new Simplex();
		textArea = new JTextArea();
		simplex.executa(linha, coluna, objetivo, restricoes, valorRestricao, tipo, textArea);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 439, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		textArea.setBounds(32, 35, 484, 332);
		frame.getContentPane().add(textArea);
		
		JScrollPane jp= new JScrollPane(textArea);
		jp.setBounds(32, 35, 360, 248);
		frame.getContentPane().add(jp);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				TelaFuncao tela = new TelaFuncao(linha, coluna, tipo);
			}
		});
		btnVoltar.setBounds(32, 307, 89, 23);
		frame.getContentPane().add(btnVoltar);
		
		lblResultado = new JLabel("Resultado");
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblResultado.setBounds(152, -6, 261, 30);
		frame.getContentPane().add(lblResultado);
	}
}
