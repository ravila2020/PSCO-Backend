package mx.com.oneproject.spco.respuesta;

import java.util.ArrayList;

import mx.com.oneproject.spco.result.clienteParte;

public class AnsSysAduPartListDual {

	private String cr;
	private String descripcion;
	public ArrayList<clienteParte> parteCliente;
	
	public String getCr() {
		return cr;
	}
	public void setCr(String cr) {
		this.cr = cr;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<clienteParte> getParteCliente() {
		return parteCliente;
	}
	public void setParteCliente(ArrayList<clienteParte> parteCliente) {
		this.parteCliente = parteCliente;
	}
	
	
	
	
}
