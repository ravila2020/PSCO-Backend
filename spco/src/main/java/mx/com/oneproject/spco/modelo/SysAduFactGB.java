package mx.com.oneproject.spco.modelo;

public class SysAduFactGB {

	private String IdCliProv;
	private String numPart;
	private String numPedimentoEntrada;	
	private String producto;                                          
	private String iDImpoEexpo;
 	private Double cantidad;
	
	
	public String getIdCliProv() {
		return IdCliProv;
	}
	public void setIdCliProv(String idCliProv) {
		IdCliProv = idCliProv;
	}
	public String getNumPart() {
		return numPart;
	}
	public void setNumPart(String numPart) {
		this.numPart = numPart;
	}
	public String getNumPedimentoEntrada() {
		return numPedimentoEntrada;
	}
	public void setNumPedimentoEntrada(String numPedimentoEntrada) {
		this.numPedimentoEntrada = numPedimentoEntrada;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getiDImpoEexpo() {
		return iDImpoEexpo;
	}
	public void setiDImpoEexpo(String iDImpoEexpo) {
		this.iDImpoEexpo = iDImpoEexpo;
	}
	
	  public Double getCantidad() { return cantidad; } 
	  public void   setCantidad(Double cantidad) { this.cantidad = cantidad; }
	               
}
