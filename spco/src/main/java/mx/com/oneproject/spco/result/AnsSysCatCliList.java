package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.respuesta.SysCatCliPag;

public class AnsSysCatCliList {

	private String cr;
	private String descripcion;
	private SysCatCliPag contenido;
	
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
	public SysCatCliPag getContenido() {
		return contenido;
	}
	public void setContenido(SysCatCliPag contenido) {
		this.contenido = contenido;
	}

	
}
