package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysAduFact;

public class SysAduFactPag {

	int page;
	int perPage;
	int total;
	int totalPages;
	private List<SysAduFact> sysAduFacturas;
	

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
	public List<SysAduFact> getSysAduFacturas() {
		return sysAduFacturas;
	}
	public void setSysAduFacturas(List<SysAduFact> sysAduFacturas) {
		this.sysAduFacturas = sysAduFacturas;
	}
	

}
