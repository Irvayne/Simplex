package br.com.wolfes.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import br.com.wolfes.simplex.Simplex;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class TelaFuncao {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JLabel label;
	private JLabel lblZ;
	private JLabel label_1;
	private JButton btnVoltar;
	private JLabel lblSujA;


	/**
	 * Create the application.
	 * @param tipo 
	 * @param qntRestricoes 
	 * @param qntVariaveis 
	 */
	public TelaFuncao(int qntVariaveis, int qntRestricoes, int tipo) {
		initialize(qntVariaveis, qntRestricoes, tipo);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param tipo 
	 * @param qntRestricoes 
	 * @param qntVariaveis 
	 */
	private void initialize(int qntVariaveis, int qntRestricoes, int tipo) {
		frame = new JFrame();
		frame.setBounds(100, 100, 506, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);



		Object[] colunas = new Object[qntVariaveis];
		for(int i = 0; i < qntVariaveis; i++){
			colunas[i] = "Coluna"+(i+1);
		}
		//
		//		    // Cria os dados, array com 3 linha e 3 colunas
		//
		String[][] valores = new String[qntRestricoes][qntVariaveis];
		//		    for (int i = 0; i < qntRestricoes; i++) {
		//		        valores[i][0] = "linha" + i + " coluna" + 0;
		//		        valores[i][1] = "linha" + i + " coluna" + 1;
		//		        valores[i][2] = "linha" + i + " coluna" + 2;
		//		    }

		table = new JTable(valores, colunas);
		table.setBounds(65, 57, 302, 150);
		frame.getContentPane().add(table);


		Object[] colunas1 = new Object[]{"Restricoes"};

		//
		//			    // Cria os dados, array com 3 linha e 3 colunas
		//
		String[][] valores1 = new String[qntRestricoes][1];
		//			    for (int i = 0; i < 3; i++) {
		//			        valores1[i][0] = "linha" + i ;
		//			        valores1[i][0] = "linha" + i ;
		//			        valores1[i][0] = "linha" + i ;
		//			    }

		table_1 = new JTable(valores1, colunas1);
		table_1.setBounds(429, 57, 39, 150);
		frame.getContentPane().add(table_1);
		
		
		Object[] colunas2 = new Object[qntVariaveis];
	    for (int i = 0; i < qntVariaveis; i++) {
			        colunas2[i] = "linha" + i ;
			        
			    }

		//
		//			    // Cria os dados, array com 3 linha e 3 colunas
		//
		String[][] valores2 = new String[1][qntVariaveis];
		//			    for (int i = 0; i < 3; i++) {
		//			        valores1[i][0] = "linha" + i ;
		//			        valores1[i][0] = "linha" + i ;
		//			        valores1[i][0] = "linha" + i ;
		//			    }
		
		
		table_2 = new JTable(valores2, colunas2);
		table_2.setBounds(122, 11, 346, 23);
		frame.getContentPane().add(table_2);

		JButton btnNext = new JButton("Avan\u00E7ar");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					table.getCellEditor().stopCellEditing();
				}catch (Exception e) {
					// TODO: handle exception
				}
				try{
					table_1.getCellEditor().stopCellEditing();
				}catch (Exception e) {
					// TODO: handle exception
				}
				try{
					table_2.getCellEditor().stopCellEditing();
				}catch (Exception e) {
					// TODO: handle exception
				}
				double[][] restricoes = new double[qntRestricoes][qntVariaveis];

				for (int i = 0; i < qntRestricoes; i++) {
					for (int j = 0; j < qntVariaveis; j++) {
						if(valores[i][j]==null){
							JOptionPane.showMessageDialog(null, "Campos vazios");
							return;
						}else{
							try{

								double val = Double.parseDouble(valores[i][j]);
								
								restricoes[i][j] = val;
								
								System.out.println(val);
							}catch (Exception e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "Digite apenas numeros");
								return;
							}
						}

					}


				}

				double[] valorRestricao = new double[qntRestricoes];
				for (int i = 0; i < qntRestricoes; i++) {
					if(valores1[i][0]==null){
						JOptionPane.showMessageDialog(null, "Campos vazios");
						return;
					}else{
						try{

							double val = Double.parseDouble(valores1[i][0]);
							valorRestricao[i] = val;
							System.out.println(val);
						}catch (Exception e) {

							JOptionPane.showMessageDialog(null, "Digite apenas numeros");
							return;
						}
					}
				}
				
				double[] objetivo = new double[qntVariaveis];
				for (int i = 0; i < qntVariaveis; i++) {
					if(valores2[0][i]==null){
						JOptionPane.showMessageDialog(null, "Campos vazios");
						return;
					}else{
						try{

							double val = Double.parseDouble(valores2[0][i]);
							objetivo[i] = val;
							System.out.println(val);
						}catch (Exception e) {

							JOptionPane.showMessageDialog(null, "Digite apenas numeros");
							return;
						}
					}
				}

				

				frame.setVisible(false);
				TelaResultado resultado = new TelaResultado(qntRestricoes, qntVariaveis, objetivo, restricoes, valorRestricao, tipo);

			}
		});
		btnNext.setBounds(379, 226, 89, 23);
		frame.getContentPane().add(btnNext);
		
		label = new JLabel("<=");
		label.setFont(new Font("Tahoma", Font.PLAIN, 33));
		label.setBounds(377, 57, 54, 158);
		frame.getContentPane().add(label);
		
		lblZ = new JLabel("Z = ");
		lblZ.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblZ.setBounds(55, -14, 67, 72);
		frame.getContentPane().add(lblZ);
		
		label_1 = new JLabel("");
		if(tipo==1)
			label_1.setText("MAX");
		else
			label_1.setText("MIN");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(0, 11, 46, 23);
		frame.getContentPane().add(label_1);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				TelaInput in = new TelaInput();
			}
		});
		btnVoltar.setBounds(10, 226, 89, 23);
		frame.getContentPane().add(btnVoltar);
		
		lblSujA = new JLabel("Suj. A:");
		lblSujA.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSujA.setBounds(0, 120, 67, 45);
		frame.getContentPane().add(lblSujA);
		
		
	}
}
