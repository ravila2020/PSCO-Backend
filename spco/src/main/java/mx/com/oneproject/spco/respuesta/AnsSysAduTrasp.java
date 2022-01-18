package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysAduTrasp;

public class AnsSysAduTrasp {

	private String cr;
	private String descripcion;
	private SysAduTrasp contenido;
	
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
	public SysAduTrasp getContenido() {
		return contenido;
	}
	public void setContenido(SysAduTrasp contenido) {
		this.contenido = contenido;
	}
	
	

}
