package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.CodPost;

public class AnsCodPostalMult {

	private String cr;
	private String descripcion;
	private List<CodPost> contenido;
	
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
	public List<CodPost> getContenido() {
		return contenido;
	}
	public void setContenido(List<CodPost> contenido) {
		this.contenido = contenido;
	}
	
	
}
