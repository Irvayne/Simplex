package br.com.wolfes.simplex;

import javax.swing.JTextArea;

public class Simplex {
	
	String saida;
	
	public void executa(int linha, int coluna,double[] objetivo, double[][] restricoes, double[] valorRestricao, int tipo, JTextArea textArea){
		System.out.println("tipo = "+tipo);
		
		double[][] tablo = geraTablo(restricoes, linha, coluna);
		double[] custosAssociado = geraCustos(objetivo, linha + coluna);//m mais n é o tamanho novo do tablo
		
		double[] custosAssociadoInicial = geraCustos(objetivo, linha + coluna);
		
		int[] indiceBase = geraBaseInicial(linha, coluna);
		

		saida = "Tablo gerado\n";
		for(int i = 0; i < linha; i++){
			for(int j = 0; j < coluna+linha; j++){
				saida += tablo[i][j]+"  ";
			}
			saida += " =  "+valorRestricao[i]+"\n";
		}
		
		saida += "Custos associados\n";
		for(int j = 0; j < coluna+linha; j++){
			saida += custosAssociado[j]+"  ";
		}
		
		saida += "\nIndice da base\n";
		for(int j = 0; j < linha; j++){
			saida += indiceBase[j]+"  ";
		}
		calculaZ(indiceBase, custosAssociadoInicial, valorRestricao);
				
		
		
		while(true){
			
			int indiceEntra;
			
			if(tipo==0){
				indiceEntra = retornaIndiceParaEntrarMIN(custosAssociado, indiceBase);
			}else{
				indiceEntra = retornaIndiceParaEntrarMAX(custosAssociado, indiceBase);
			}
			
			if(indiceEntra == Integer.MIN_VALUE){
				saida +="Infinitas solucoes\n";
				break;
			}
			
			if(indiceEntra == -1){
				saida +="Solucao Otima\n";
				break;
			}
			
			int indiceSai = testeDaRazao(tablo, indiceBase, valorRestricao, indiceEntra, linha, coluna);
			
			if(indiceSai != -1){
				pivoteia(tablo, custosAssociado, valorRestricao, indiceSai, indiceEntra, linha, coluna+linha);
				
				
				saida +="\n\nNovo Tablo Gerado\n";
				for(int i = 0; i < linha; i++){
					for(int j = 0; j < coluna+linha; j++){
						saida +=tablo[i][j]+"  ";
					}
					saida +=" =  "+valorRestricao[i]+"\n";
				}
				
				
				saida +="Custos associados \n";
				for(int j = 0; j < coluna+linha; j++){
					saida +=custosAssociado[j]+"  ";
				}
				
				saida +="\nElementos na base\n";
				for(int j = 0; j < linha; j++){
					saida +="x"+(indiceBase[j]+1)+"  ";
				}
				
			}else{
				saida +="\nSolucao ilimitada\n";
				break;
			}
			
			calculaZ(indiceBase, custosAssociadoInicial , valorRestricao);
		}
		
		textArea.setText(saida);
	}
	
	private void calculaZ(int[] indiceBase, double[] custosAssociadoInicial, double[] valorRestricao) {
		double z = 0;
		
		for(int i = 0; i < indiceBase.length; i++){
			z = z + custosAssociadoInicial[indiceBase[i]]* valorRestricao[i];
		}
		
		saida +="\nZ  =  " + z+"\n";
		
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
		saida +="Elemento que sai = x"+(indiceSai+1)+"\n";
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
			saida +="\nElemento que entra = x"+(indice+1)+"\n";
			return indice;
		}else{
			return -1;
		}
	}
	
	private  int retornaIndiceParaEntrarMAX(double custosAssociados[], int[] base){
		double valor = Integer.MIN_VALUE;
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
