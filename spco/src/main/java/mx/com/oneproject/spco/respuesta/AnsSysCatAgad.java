package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysCatAgad;

public class AnsSysCatAgad {

	private String cr;
	private String descripcion;
	private SysCatAgad contenido;
	
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
	public SysCatAgad getContenido() {
		return contenido;
	}
	public void setContenido(SysCatAgad contenido) {
		this.contenido = contenido;
	}
	
}
