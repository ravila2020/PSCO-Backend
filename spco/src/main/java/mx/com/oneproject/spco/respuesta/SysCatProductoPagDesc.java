package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatProducto;

public class SysCatProductoPagDesc {

	int page;
	int perPage;
	int total;
	int totalPages;
	public List<SysCatProducto> sysCatProductos;
	public List<String> lDescripUMC;
	public List<String> lDescripUMT;
	public List<String> lFactor;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<SysCatProducto> getSysCatProductos() {
		return sysCatProductos;
	}
	public void setSysCatProductos(List<SysCatProducto> sysCatProductos) {
		this.sysCatProductos = sysCatProductos;
	}
	public List<String> getlDescripUMC() {
		return lDescripUMC;
	}
	public void setlDescripUMC(List<String> lDescripUMC) {
		this.lDescripUMC = lDescripUMC;
	}
	public List<String> getlDescripUMT() {
		return lDescripUMT;
	}
	public void setlDescripUMT(List<String> lDescripUMT) {
		this.lDescripUMT = lDescripUMT;
	}
	public List<String> getlFactor() {
		return lFactor;
	}
	public void setlFactor(List<String> lFactor) {
		this.lFactor = lFactor;
	}
	
	

}
