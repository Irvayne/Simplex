package br.com.wolfes.modelo;

public class Valor {

	private Coeficiente coeficiente;
	private Variavel variavel;
	
	public Valor(Coeficiente coeficiente, Variavel variavel){
		this.variavel = variavel;
		this.coeficiente = coeficiente;
	}
	
	public Coeficiente getCoeficiente() {
		return coeficiente;
	}
	public void setCoeficiente(Coeficiente coeficiente) {
		this.coeficiente = coeficiente;
	}
	public Variavel getVariavel() {
		return variavel;
	}
	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}
	
	
}
