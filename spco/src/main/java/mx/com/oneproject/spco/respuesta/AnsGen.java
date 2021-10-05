package mx.com.oneproject.spco.respuesta;

public abstract class AnsGen {

	public String cr;
	public String descripcion;
	public Object contenido;
	
	public abstract void reportar();
	public abstract void completar();
	
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
	public Object getContenido() {
		return contenido;
	}
	public void setContenido(Object contenido) {
		this.contenido = contenido;
	}	
}
