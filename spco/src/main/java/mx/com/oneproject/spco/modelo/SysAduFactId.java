package mx.com.oneproject.spco.modelo;

import java.io.Serializable;

public class SysAduFactId  implements Serializable {

	private String IdCliProv;
	private String numPart;
	private String numFact;
	private String numPedimentoEntrada;
	
	public String getIdCliProv() {
		return IdCliProv;
	}
	public void setIdCliProv(String idCliProv) {
		IdCliProv = idCliProv;
	}
	public String getNumPart() {
		return numPart;
	}
	public void setNumPart(String numPart) {
		this.numPart = numPart;
	}
	public String getNumFact() {
		return numFact;
	}
	public void setNumFact(String numFact) {
		this.numFact = numFact;
	}
	public String getNumPedimentoEntrada() {
		return numPedimentoEntrada;
	}
	public void setNumPedimentoEntrada(String numPedimentoEntrada) {
		this.numPedimentoEntrada = numPedimentoEntrada;
	}
	

	
}
