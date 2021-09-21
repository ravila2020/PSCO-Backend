package mx.com.oneproject.spco.respuesta;

import java.util.List;

import mx.com.oneproject.spco.modelo.SysAduPart;

public class SysAduPartPag {

	int page;
	int perPage;
	int total;
	int totalPages;
	private List<SysAduPart> sysAduPartes;
	
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
	public List<SysAduPart> getSysAduPartes() {
		return sysAduPartes;
	}
	public void setSysAduPartes(List<SysAduPart> sysAduPartes) {
		this.sysAduPartes = sysAduPartes;
	}
	
	

}
