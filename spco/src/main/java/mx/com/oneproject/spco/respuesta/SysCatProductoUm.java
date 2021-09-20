package mx.com.oneproject.spco.respuesta;

import mx.com.oneproject.spco.modelo.SysCatProd;
import mx.com.oneproject.spco.modelo.SysCatProducto;

public class SysCatProductoUm {

	public SysCatProducto objetoItem;
	public String uMDescripcion;
	
	
	public SysCatProducto getObjetoItem() {
		return objetoItem;
	}
	public void setObjetoItem(SysCatProducto objetoItem) {
		this.objetoItem = objetoItem;
	}
	public String getuMDescripcion() {
		return uMDescripcion;
	}
	public void setuMDescripcion(String uMDescripcion) {
		this.uMDescripcion = uMDescripcion;
	}
		
}
