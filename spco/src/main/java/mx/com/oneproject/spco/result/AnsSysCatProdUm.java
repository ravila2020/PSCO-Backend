package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.modelo.SysCatProd;

public class AnsSysCatProdUm {

	private String cr;
	private String descripcion;
	private SysCatProd contenido;
	private String uMDescripcion;
	
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
	public SysCatProd getContenido() {
		return contenido;
	}
	public void setContenido(SysCatProd contenido) {
		this.contenido = contenido;
	}
	public String getuMDescripcion() {
		return uMDescripcion;
	}
	public void setuMDescripcion(String uMDescripcion) {
		this.uMDescripcion = uMDescripcion;
	}
	
	
}
