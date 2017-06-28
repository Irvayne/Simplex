package br.com.wolfes.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaInput {

	
	private JFrame frame;
	
	

	/**
	 * Create the application.
	 */
	public TelaInput() {
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnMax = new JRadioButton("MAX");
		rdbtnMax.setBounds(239, 84, 73, 23);
		frame.getContentPane().add(rdbtnMax);
		
		JRadioButton rdbtnMin = new JRadioButton("MIN");
		rdbtnMin.setBounds(239, 110, 73, 23);
		frame.getContentPane().add(rdbtnMin);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMin);
		group.add(rdbtnMax);
		
		rdbtnMin.setSelected(true);
		
		JLabel lblQuantidadeDeVariveis = new JLabel("Quantidade de Vari\u00E1veis:");
		lblQuantidadeDeVariveis.setBounds(10, 58, 180, 14);
		frame.getContentPane().add(lblQuantidadeDeVariveis);
		
		SpinnerModel spinnerModel = new SpinnerNumberModel(1, //initial value
				 1, //min
		         100, //max
		         1);//step
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(20, 85, 29, 20);
		frame.getContentPane().add(spinner);
		
		JLabel lblQuantidadeDeRestries = new JLabel("Quantidade de Restri\u00E7\u00F5es:");
		lblQuantidadeDeRestries.setBounds(10, 115, 157, 14);
		frame.getContentPane().add(lblQuantidadeDeRestries);
		
		SpinnerModel spinnerModel_1 = new SpinnerNumberModel(1, //initial value
				 1, //min
		         100, //max
		         1);//step
		JSpinner spinner_1 = new JSpinner(spinnerModel_1);
		spinner_1.setBounds(20, 140, 29, 20);
		frame.getContentPane().add(spinner_1);
		
		JButton btnNext = new JButton("Avançar");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int qntVariaveis = (int) spinner.getValue();
				int qntRestricoes = (int) spinner_1.getValue();
				int tipo = 0;
				
				if(rdbtnMax.isSelected())
					tipo = 1;
				else
					tipo = 0;
				
				TelaFuncao tela = new TelaFuncao(qntVariaveis, qntRestricoes, tipo);
				frame.setVisible(false);
				
			}
		});
		btnNext.setBounds(260, 212, 148, 23);
		frame.getContentPane().add(btnNext);
		
		JLabel lblTipoDaFuno = new JLabel("Tipo da Fun\u00E7\u00E3o Objetivo:");
		lblTipoDaFuno.setBounds(200, 58, 157, 14);
		frame.getContentPane().add(lblTipoDaFuno);
		
		JLabel lblInformaesSobreO = new JLabel("Informa\u00E7\u00F5es sobre o PPL");
		lblInformaesSobreO.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblInformaesSobreO.setBounds(64, 0, 360, 47);
		frame.getContentPane().add(lblInformaesSobreO);
		
		
	}
}
