package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatProducto;

public class SysCatProductoPagDesc {

	int page;
	int perPage;
	int total;
	int totalPages;
	public List<SysCatProducto> sysCatProductos;
	public List<String> DescripUMC;
	public List<String> DescripUMT;
	public List<String> Factor;
	
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
	public List<String> getDescripUMC() {
		return DescripUMC;
	}
	public void setDescripUMC(List<String> descripUMC) {
		DescripUMC = descripUMC;
	}
	public List<String> getDescripUMT() {
		return DescripUMT;
	}
	public void setDescripUMT(List<String> descripUMT) {
		DescripUMT = descripUMT;
	}
	public List<String> getFactor() {
		return Factor;
	}
	public void setFactor(List<String> factor) {
		Factor = factor;
	}

}
