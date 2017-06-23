package br.com.wolfes.modelo;

public abstract class PPL {
	
	private FuncaoObjetivo funcaoObjetivo;
	private FuncaoRestricao funcaoRestricao;
	
	public FuncaoObjetivo getFuncaoObjetivo() {
		return funcaoObjetivo;
	}
	public void setFuncaoObjetivo(FuncaoObjetivo funcaoObjetivo) {
		this.funcaoObjetivo = funcaoObjetivo;
	}
	public FuncaoRestricao getFuncaoRestricao() {
		return funcaoRestricao;
	}
	public void setFuncaoRestricao(FuncaoRestricao funcaoRestricao) {
		this.funcaoRestricao = funcaoRestricao;
	}
	
	

}
