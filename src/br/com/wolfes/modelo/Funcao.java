package br.com.wolfes.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Funcao {
	
	private List<Valor> funcao;
	
	public Funcao(){
		funcao = new ArrayList<>();
	}
	
	public List<Valor> getFuncao() {
		return this.funcao;
	}

	public void setFuncao(List<Valor> funcao) {
		this.funcao = funcao;
	}

}
