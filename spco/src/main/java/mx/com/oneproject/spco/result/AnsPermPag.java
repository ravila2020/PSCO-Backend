package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.Permission;

public class AnsPermPag {

	private String cr;
	private String descripcion;
	private List<Permission> contenido;
	

	public AnsPermPag() {
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
	public List<Permission> getContenido() {
		return contenido;
	}
	public void setContenido(List<Permission> contenido) {
		this.contenido = contenido;
	}
	
	
}
