package mx.com.oneproject.spco.result;

import java.util.Optional;

import mx.com.oneproject.spco.modelo.CodPost;

public class AnsCodPostal {

	private String cr;
	private String descripcion;
	private Optional<CodPost> contenido;
	
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
	public Optional<CodPost> getContenido() {
		return contenido;
	}
	public void setContenido(Optional<CodPost> contenido) {
		this.contenido = contenido;
	}
	
	
	

}
