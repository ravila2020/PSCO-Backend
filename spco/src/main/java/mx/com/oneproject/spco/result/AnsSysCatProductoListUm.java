package mx.com.oneproject.spco.result;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysCatProducto;

public class AnsSysCatProductoListUm {

	private String cr;
	private String descripcion;
	int page;
	int perPage;
	int total;
	int totalPages;
	public List<SysCatProducto> objetoItem;
	public List<String> uMCDescripcion;
	public List<String> uMTDescripcion;
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
	public List<SysCatProducto> getObjetoItem() {
		return objetoItem;
	}
	public void setObjetoItem(List<SysCatProducto> objetoItem) {
		this.objetoItem = objetoItem;
	}
	public List<String> getuMCDescripcion() {
		return uMCDescripcion;
	}
	public void setuMCDescripcion(List<String> uMCDescripcion) {
		this.uMCDescripcion = uMCDescripcion;
	}
	public List<String> getuMTDescripcion() {
		return uMTDescripcion;
	}
	public void setuMTDescripcion(List<String> uMTDescripcion) {
		this.uMTDescripcion = uMTDescripcion;
	}

}
