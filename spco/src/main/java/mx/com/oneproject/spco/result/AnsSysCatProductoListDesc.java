package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.respuesta.SysCatProductoPagDesc;

public class AnsSysCatProductoListDesc {

	private String cr;
	private String descripcion;
	private SysCatProductoPagDesc contenido;
	
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
	public SysCatProductoPagDesc getContenido() {
		return contenido;
	}
	public void setContenido(SysCatProductoPagDesc contenido) {
		this.contenido = contenido;
	}
	
	
}
