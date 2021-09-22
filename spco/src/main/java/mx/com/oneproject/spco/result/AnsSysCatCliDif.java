package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatCli;

public class AnsSysCatCliDif {

	private String cr;
	private String descripcion;
	private List<SysCatCli> contenido;
	
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
	public List<SysCatCli> getContenido() {
		return contenido;
	}
	public void setContenido(List<SysCatCli> contenido) {
		this.contenido = contenido;
	}
	

	
}
