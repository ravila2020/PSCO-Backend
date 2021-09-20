package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.modelo.SysCatProducto;

public class AnsSysCatProducto {

	private String cr;
	private String descripcion;
	private SysCatProducto contenido;
	
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
	public SysCatProducto getContenido() {
		return contenido;
	}
	public void setContenido(SysCatProducto contenido) {
		this.contenido = contenido;
	}
	
}
