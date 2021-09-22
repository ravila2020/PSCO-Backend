package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysAduPart;

public class AnsSysAduPartUm {

	private String cr;
	private String descripcion;
	private SysAduPart contenido;
	private String uMCdescripcion;
	private String uMTdescripcion;
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
	public SysAduPart getContenido() {
		return contenido;
	}
	public void setContenido(SysAduPart contenido) {
		this.contenido = contenido;
	}
	public String getuMCdescripcion() {
		return uMCdescripcion;
	}
	public void setuMCdescripcion(String uMCdescripcion) {
		this.uMCdescripcion = uMCdescripcion;
	}
	public String getuMTdescripcion() {
		return uMTdescripcion;
	}
	public void setuMTdescripcion(String uMTdescripcion) {
		this.uMTdescripcion = uMTdescripcion;
	}

}
