package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatProd;
import mx.com.oneproject.spco.modelo.SysCatProducto;

public class AnsSysCatProductoUm {

	private String cr;
	private String descripcion;
	private SysCatProducto contenido;
	private String uMCDescripcion;
	private String uMTDescripcion;
	
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
	public String getuMCDescripcion() {
		return uMCDescripcion;
	}
	public void setuMCDescripcion(String uMCDescripcion) {
		this.uMCDescripcion = uMCDescripcion;
	}
	public String getuMTDescripcion() {
		return uMTDescripcion;
	}
	public void setuMTDescripcion(String uMTDescripcion) {
		this.uMTDescripcion = uMTDescripcion;
	}
	
}
