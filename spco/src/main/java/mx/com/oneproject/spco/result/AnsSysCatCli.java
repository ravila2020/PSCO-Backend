package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.modelo.SysCatCli;

public class AnsSysCatCli {

	private String cr;
	private String descripcion;
	private SysCatCli contenido;
	
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
	public SysCatCli getContenido() {
		return contenido;
	}
	public void setContenido(SysCatCli contenido) {
		this.contenido = contenido;
	}

	
}
