package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysAduTrasp;

public class AnsSysAduTraspDesc {
	
	private String cr;
	private String descripcion;
	private SysAduTrasp contenido;
	private String descripcionProd;
	
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
	public String getDescripcionProd() {
		return descripcionProd;
	}
	public void setDescripcionProd(String descripcionProd) {
		this.descripcionProd = descripcionProd;
	}
	

}
