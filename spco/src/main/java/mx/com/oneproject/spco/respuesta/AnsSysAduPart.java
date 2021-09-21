package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysAduPart;

public class AnsSysAduPart {

	private String cr;
	private String descripcion;
	private SysAduPart contenido;
	
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
	public SysAduPart getContenido() {
		return contenido;
	}
	public void setContenido(SysAduPart contenido) {
		this.contenido = contenido;
	}
	
	
}
