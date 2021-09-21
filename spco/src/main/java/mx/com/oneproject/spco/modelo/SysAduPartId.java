package mx.com.oneproject.spco.modelo;

import java.io.Serializable;

public class SysAduPartId implements Serializable{

	private String IdCliProv;
	private String numPart;
	private String numPedimento;
	
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
	public String getNumPedimento() {
		return numPedimento;
	}
	public void setNumPedimento(String numPedimento) {
		this.numPedimento = numPedimento;
	}

	
}
