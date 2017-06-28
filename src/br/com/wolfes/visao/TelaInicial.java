package br.com.wolfes.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import br.com.wolfes.modelo.Principal;
import br.com.wolfes.simplex.Simplex;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;

public class TelaInicial{

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial window = new TelaInicial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaInicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPplresolve = new JLabel("FEIO Systems");
		lblPplresolve.setForeground(Color.BLUE);
		lblPplresolve.setBounds(146, 38, 162, 28);
		frame.getContentPane().add(lblPplresolve);
		lblPplresolve.setFont(new Font("Trebuchet MS", Font.ITALIC, 23));
				
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(146, 115, 151, 23);
		frame.getContentPane().add(btnIniciar);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaInput input = new TelaInput();
				frame.setVisible(false);
			}
		});

		
		Simplex simplex = new Simplex();
	}
}
