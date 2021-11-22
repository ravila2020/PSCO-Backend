package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysAduFactGB;

public class AnsSysAduFactCuantos {

	private String cr;
	private String descripcion;
	private List<Object[]> contenido;
	
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
	public List<Object[]> getContenido() {
		return contenido;
	}
	public void setContenido(List<Object[]> contenido) {
		this.contenido = contenido;
	}

}
