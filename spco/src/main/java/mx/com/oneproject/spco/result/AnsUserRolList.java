package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.AppUserRole;

public class AnsUserRolList {

	private String cr;
	private String descripcion;
	private List<AppUserRole> contenido;
	
	public AnsUserRolList() {
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
	public List<AppUserRole> getContenido() {
		return contenido;
	}
	public void setContenido(List<AppUserRole> contenido) {
		this.contenido = contenido;
	}
	
	
	
}
