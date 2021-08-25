package mx.com.oneproject.spco.result;

import mx.com.oneproject.spco.modelo.SysUsuarios;

public class AnsSysUser {
	
	private String cr;
	private String descripcion;
	private SysUsuarios contenido;
	
	
	
	public AnsSysUser(String cr, String descripcion, SysUsuarios contenido) {
		super();
		this.cr = cr;
		this.descripcion = descripcion;
		this.contenido = contenido;
	}
	
	public AnsSysUser() {
		super();
		// TODO Auto-generated constructor stub
	}

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
	public SysUsuarios getContenido() {
		return contenido;
	}
	public void setContenido(SysUsuarios contenido) {
		this.contenido = contenido;
	}

}
