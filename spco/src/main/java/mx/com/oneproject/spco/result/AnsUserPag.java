package mx.com.oneproject.spco.result;


import java.util.List;

import mx.com.oneproject.spco.modelo.AppUser;

public class AnsUserPag {

	private String cr;
	private String descripcion;
	private List<AppUser> contenido;
	
	
	public AnsUserPag() {
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
	public List<AppUser> getContenido() {
		return contenido;
	}
	public void setContenido(List<AppUser> contenido) {
		this.contenido = contenido;
	}
	

	
}
