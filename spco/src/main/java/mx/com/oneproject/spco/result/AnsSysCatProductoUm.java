package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatProd;
import mx.com.oneproject.spco.modelo.SysCatProducto;

public class AnsSysCatProductoUm {

	private String cr;
	private String descripcion;
	private SysCatProducto contenido;
	private String uMDescripcion;
	
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
	public SysCatProducto getContenido() {
		return contenido;
	}
	public void setContenido(SysCatProducto contenido) {
		this.contenido = contenido;
	}
	public String getuMDescripcion() {
		return uMDescripcion;
	}
	public void setuMDescripcion(String uMDescripcion) {
		this.uMDescripcion = uMDescripcion;
	}

}
