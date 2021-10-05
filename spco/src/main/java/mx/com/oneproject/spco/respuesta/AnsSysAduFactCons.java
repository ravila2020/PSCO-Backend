package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysAduFact;

public class AnsSysAduFactCons {

	private String cr;
	private String descripcion;
	private SysAduFact contenido;
	
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
	public SysAduFact getContenido() {
		return contenido;
	}
	public void setContenido(SysAduFact contenido) {
		this.contenido = contenido;
	}
	
}
