package mx.com.oneproject.spco.modelo;

import java.io.Serializable;

public class SysAduFactId  implements Serializable {

	private String IdCliProv;
	private String numPart;
	private String numFact;
	private String iDImpoEexpo;
	
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
	public String getiDImpoEexpo() {
		return iDImpoEexpo;
	}
	public void setiDImpoEexpo(String iDImpoEexpo) {
		this.iDImpoEexpo = iDImpoEexpo;
	}

	
}
