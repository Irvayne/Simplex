package br.com.wolfes.simplex;

import java.util.Scanner;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class Simplex {
	
//	Object[] colunas = new Object[] { "coluna 1", "coluna 2", "coluna 3" };
//
//    // Cria os dados, array com 3 linha e 3 colunas
//
//    Object[][] valores = new Object[3][3];
//    for (int i = 0; i < 3; i++) {
//        valores[i][0] = "linha" + i + " coluna" + 0;
//        valores[i][1] = "linha" + i + " coluna" + 1;
//        valores[i][2] = "linha" + i + " coluna" + 2;
//    }
//	
//	
//   
//	panel.setBounds(10, 11, 446, 333);
//	frame.getContentPane().add(panel);
//	panel.setLayout(null);
//	
//	table = new JTable(valores, colunas);
//	table.setBounds(110, 5, 225, 48);
//	panel.add(table);
	
	public void executa(JTextArea textArea){
		
		System.out.println("Iniciando a aplicação");
		Scanner in = new Scanner(System.in);
		//linha - qnt de restricoes
		//coluna- qnt de variaveis
		int linha = 2;
		int coluna = 2;
		
		
		double[] objetivo = new double[coluna];
		System.out.println("Digite a funcao objetivo");
		for(int j = 0; j < coluna; j++){
			 objetivo[j] = in.nextDouble();
		}
		
		double[][] restricoes = new double[linha][coluna];
		System.out.println("Digite as restrições");
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna; j++){
				 restricoes[i][j] = in.nextDouble();
			}
		}
		
		double[] valorRestricao = new double[linha];
		System.out.println("Digite os valores das restrições");
		for (int i = 0; i < linha; i++) {
			valorRestricao[i] = in.nextDouble();
		}
		
		
		double[][] tablo = geraTablo(restricoes, linha, coluna);
		double[] custosAssociado = geraCustos(objetivo, linha + coluna);//m mais n é o tamanho novo do tablo
		
		int[] indiceBase = geraBaseInicial(linha, coluna);
		

		System.out.println("Tablo gerado");
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna+linha; j++){
				 System.out.print(tablo[i][j]+" ");
			}
			System.out.println("= "+valorRestricao[i]);
		}
		
		System.out.println("Custos associados");
		for(int j = 0; j < coluna+linha; j++){
			 System.out.print(custosAssociado[j]+" ");
		}
		
		System.out.println("\nIndice da base");
		for(int j = 0; j < linha; j++){
			 System.out.print(indiceBase[j]+" ");
		}
				
		
		
		while(true){
			
			
			int indiceEntra = retornaIndiceParaEntrarMIN(custosAssociado, indiceBase);
			
			if(indiceEntra == Integer.MIN_VALUE){
				System.out.println("Infinitas solucoes");
				break;
			}
			
			if(indiceEntra == -1){
				System.out.println("Solucao Otima");
				break;
			}
			
			int indiceSai = testeDaRazao(tablo, indiceBase, valorRestricao, indiceEntra, linha, coluna);
			
			if(indiceSai != -1){
				pivoteia(tablo, custosAssociado, valorRestricao, indiceSai, indiceEntra, linha, coluna+linha);
				
				
				System.out.println("\n\nTablo gerado pivoteado");
				for(int i = 0; i < linha; i++){
					for(int j = 0; j < coluna+linha; j++){
						 System.out.print(tablo[i][j]+" ");
					}
					System.out.println("= "+valorRestricao[i]);
				}
				
				
				System.out.println("Custos associados ");
				for(int j = 0; j < coluna+linha; j++){
					 System.out.print(custosAssociado[j]+" ");
				}
				
				System.out.println("\nElementos na base");
				for(int j = 0; j < linha; j++){
					 System.out.print("x"+(indiceBase[j]+1)+" ");
				}
				
			}else{
				System.out.println("Solucao ilimitada");
				break;
			}
		}
	}
	
	private int testeDaRazao(double tablo[][],
			int base[], double valorRestricoes[],
			int indiceEntra,
			int qntLinhasTablo, 
			int qntColunasTablo){
		
		double valor = 1000000;
		int indice = -1;
		
		for(int i = 0; i < qntLinhasTablo; i++){
			if(tablo[i][indiceEntra] > 0){
				double teste = valorRestricoes[i]/tablo[i][indiceEntra];
				
				if(teste < valor){
					valor = teste;
					indice = i;
				}
			}
		}
		if(indice != -1){
		int indiceSai = base[indice];
		System.out.println("Elemento que sai = x"+(indiceSai+1));
		base[indice] = indiceEntra;
		}
		return indice;
		
	}
	
	private int retornaIndiceParaEntrarMIN(double custosAssociados[], int base[]){
		double valor = Integer.MAX_VALUE;
		int indice = -1;
		
		for(int i = 0; i < custosAssociados.length; i++){
			boolean verifica = false;
			for(int j = 0; j < base.length; j++){
				if(base[j] == i){
					verifica = true;
				}
			}
			if(!verifica && custosAssociados[i]==0){
				return Integer.MIN_VALUE;
			}
		}
		
		
		for(int i = 0; i < custosAssociados.length; i++){
			if(valor > custosAssociados[i]){
				valor  = custosAssociados[i];
				indice = i;
			}
		}
		
		if(indice!= -1 && custosAssociados[indice] < 0){
			System.out.println("\nElemento que entra = x"+(indice+1));
			return indice;
		}else{
			return -1;
		}
	}
	
	private  int retornaIndiceParaEntrarMAX(double custosAssociados[]){
		double valor = -100000;
		int indice = -1;
		
		for(int i = 0; i < custosAssociados.length; i++){
			if(valor < custosAssociados[i]){
				valor  = custosAssociados[i];
				indice = i;
			}
		}
		
		if(indice!= -1 && custosAssociados[indice] > 0){
			return indice;
		}else{
			return -1;
		}
	}
	
	private  void pivoteia(double tablo[][],
			double custosAssociados[],
			double valorRestricao[],
			int linhaPivotear, 
			int colunaPivotear,
			int qntTotalLinhaTablo,
			int qntTotalColunasTablo){
		
		double valorPivoteado = tablo[linhaPivotear][colunaPivotear];
		
		//divide a linha do elemento para pivotear pelo valor do elemento
		//isso para tranfomalo em 1
		for(int i = 0; i < qntTotalColunasTablo; i++){
			tablo[linhaPivotear][i] = tablo[linhaPivotear][i]/valorPivoteado;
		}
		
		valorRestricao[linhaPivotear] = valorRestricao[linhaPivotear]/valorPivoteado;
		
		//zera a coluna
		//linhas para baixo
		//verifica se nao é a ultima linha
		if(linhaPivotear + 1 != qntTotalLinhaTablo){
			
				for(int j = linhaPivotear + 1; j < qntTotalLinhaTablo; j++ ){
					double valorMult = - tablo[j][colunaPivotear]/tablo[linhaPivotear][colunaPivotear];
					
					valorRestricao[j] = valorRestricao[linhaPivotear] * valorMult + valorRestricao[j]; 
					for(int i = 0; i < qntTotalColunasTablo; i++){
						double elementoLinha = tablo[linhaPivotear][i];
						double elementoAtual = tablo[j][i];
						double novoElemento = elementoLinha * valorMult + elementoAtual;
						tablo[j][i] = novoElemento;
						
				}
			}
		}
		
		
		if(linhaPivotear - 1 >= 0){
			
			for(int j = linhaPivotear - 1 ; j >= 0; j-- ){
				double valorMult = - tablo[j][colunaPivotear]/tablo[linhaPivotear][colunaPivotear];
				
				valorRestricao[j] = valorRestricao[linhaPivotear] * valorMult + valorRestricao[j]; 
				for(int i = 0; i < qntTotalColunasTablo; i++){
					double elementoLinha = tablo[linhaPivotear][i];
					double elementoAtual = tablo[j][i];
					double novoElemento = elementoLinha * valorMult + elementoAtual;
					tablo[j][i] = novoElemento;
					
				}
			}
		}
			
		//ele faz toda vez
		double valorMut = - custosAssociados[colunaPivotear]/tablo[linhaPivotear][colunaPivotear]; 
		for(int i = 0; i < qntTotalColunasTablo; i++){
			custosAssociados[i] = tablo[linhaPivotear][i] * valorMut + custosAssociados[i];
		}
		
	}

	private  int[] geraBaseInicial(int linha, int coluna) {
		int base[] = new int[linha];
		for(int i = 0; i < linha; i++){
			base[i] = coluna++;
		}
		
		return base;
	}

	private  double[] geraCustos(double[] objetivo, int i) {
		double[] novosCustos = new double[i];
		for (int j = 0; j < objetivo.length; j++) {
			novosCustos[j] = objetivo[j]; 
		}
		return novosCustos;
	}

	private  double[][] geraTablo(double[][] restricoes, int m, int n) {
		int n1 = m+n;//nova qnt de colunas com a adicao das variaveis de folga
		double[][] tablo = new double[m][n1];
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				tablo[i][j] = restricoes[i][j];
			}
		}
		
		for(int i = 0; i < m; i++){
			int k = 0;
			for(int j = n; j < n1; j++){
				if(i == k){
					tablo[i][j] = 1;
				}else{
					tablo[i][j] = 0;
				}
				k++;
			}
		}
		
		return tablo;
		
	}

}
