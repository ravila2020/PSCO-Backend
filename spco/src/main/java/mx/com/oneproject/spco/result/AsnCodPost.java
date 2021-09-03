package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.modelo.CodPost;

public class AsnCodPost {

	private String cr;
	private String descripcion;
	private CodPost contenido;
	
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
	public CodPost getContenido() {
		return contenido;
	}
	public void setContenido(CodPost contenido) {
		this.contenido = contenido;
	}
	
}
