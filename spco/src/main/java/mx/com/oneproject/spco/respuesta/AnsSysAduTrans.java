package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysAduTrans;

public class AnsSysAduTrans {

	private String cr;
	private String descripcion;
	private List<SysAduTrans> contenido;
	
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
	public List<SysAduTrans> getContenido() {
		return contenido;
	}
	public void setContenido(List<SysAduTrans> contenido) {
		this.contenido = contenido;
	}
	

	
}
