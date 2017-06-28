package br.com.wolfes.visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

public class TelaFuncao {

	private JFrame frame;
	private JTable table;
	private JTable table_1;


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
		frame.setBounds(100, 100, 566, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		Object[] colunas = new Object[qntVariaveis];
		for(int i = 0; i < qntVariaveis; i++){
			colunas[i] = "Coluna"+(i+1);
		}
		//
//		    // Cria os dados, array com 3 linha e 3 colunas
		//
		    Object[][] valores = new Object[qntRestricoes][qntVariaveis];
//		    for (int i = 0; i < qntRestricoes; i++) {
//		        valores[i][0] = "linha" + i + " coluna" + 0;
//		        valores[i][1] = "linha" + i + " coluna" + 1;
//		        valores[i][2] = "linha" + i + " coluna" + 2;
//		    }
		    
		    table = new JTable(valores, colunas);
			table.setBounds(60, 101, 346, 150);
			frame.getContentPane().add(table);
			
			
			Object[] colunas1 = new Object[]{"Restricoes"};
			
			//
//			    // Cria os dados, array com 3 linha e 3 colunas
			//
			    Object[][] valores1 = new Object[qntRestricoes][1];
//			    for (int i = 0; i < 3; i++) {
//			        valores1[i][0] = "linha" + i ;
//			        valores1[i][0] = "linha" + i ;
//			        valores1[i][0] = "linha" + i ;
//			    }
			
			    table_1 = new JTable(valores1, colunas1);
			    table_1.setBounds(436, 101, 71, 150);
			    frame.getContentPane().add(table_1);
	}
}
