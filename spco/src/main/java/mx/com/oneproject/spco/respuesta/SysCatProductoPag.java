package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatProducto;

public class SysCatProductoPag {

	int page;
	int perPage;
	int total;
	int totalPages;
	private List<SysCatProducto> sysCatProductos;
	
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
	
	
	
}
