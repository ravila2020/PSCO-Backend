package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysRecintos;

public class AnsSysRecintos {

	private String cr;
	private String descripcion;
	private List<SysRecintos> contenido;
	
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
	public List<SysRecintos> getContenido() {
		return contenido;
	}
	public void setContenido(List<SysRecintos> contenido) {
		this.contenido = contenido;
	}
	
	
}
