package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.DetCatAp;

public class AnsDetCatAp {

	private String cr;
	private String descripcion;
	private List<DetCatAp> contenido;
	
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
	public List<DetCatAp> getContenido() {
		return contenido;
	}
	public void setContenido(List<DetCatAp> contenido) {
		this.contenido = contenido;
	}
	
	
}
