package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.respuesta.RolPag;

public class AnsRolPag {

	private String cr;
	private String descripcion;
	private RolPag contenido;
	
	public AnsRolPag(String cr, String descripcion, RolPag contenido) {
		this.cr = cr;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	
	public AnsRolPag() {
		this.cr = cr;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	
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
	public RolPag getContenido() {
		return contenido;
	}
	public void setContenido(RolPag contenido) {
		this.contenido = contenido;
	}
	
	
}
