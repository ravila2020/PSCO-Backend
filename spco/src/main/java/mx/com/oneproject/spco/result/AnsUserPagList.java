package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.respuesta.AppUserPag;

public class AnsUserPagList {

	private String cr;
	private String descripcion;
	private AppUserPag contenido;
	
	public AnsUserPagList() {
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

	public AppUserPag getContenido() {
		return contenido;
	}

	public void setContenido(AppUserPag contenido) {
		this.contenido = contenido;
	}
	

	
}
