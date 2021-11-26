package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysAduFact;

public class AnsSysAduFactCuantos {

	private String cr;
	private String descripcion;
	private List<SysAduFact> contenido;
	
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
	public List<SysAduFact> getContenido() {
		return contenido;
	}
	public void setContenido(List<SysAduFact> contenido) {
		this.contenido = contenido;
	}

}
