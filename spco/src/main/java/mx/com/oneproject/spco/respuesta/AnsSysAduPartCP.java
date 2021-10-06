package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysAduPart;

public class AnsSysAduPartCP {

	
	private String cr;
	private String descripcion;
	private List<SysAduPart> contenido;
	
	
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
	public List<SysAduPart> getContenido() {
		return contenido;
	}
	public void setContenido(List<SysAduPart> contenido) {
		this.contenido = contenido;
	}
	
	

}
