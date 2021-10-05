package mx.com.oneproject.spco.modelo;

import java.io.Serializable;

public class HistTipoCambioId implements Serializable{

	private String clave; 
	private String moneda; 
	private String fecha;
	
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	} 	
}
