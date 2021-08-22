package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.CatApendices;

public class AnsCatApendices {

	private String cr;
	private String descripcion;
	private List<CatApendices>  contenido;
	
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
	public List<CatApendices> getContenido() {
		return contenido;
	}
	public void setContenido(List<CatApendices> contenido) {
		this.contenido = contenido;
	}
	
}
