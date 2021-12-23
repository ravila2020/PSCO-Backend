package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SysAduTransId.class)
@Table(name = "SYS_ADU_TRANS")
public class SysAduTrans {

	@Id
	@Column(name = "Empresa", nullable = false, length = 4)          
	private String empresa;                                           
	@Id
	@Column(name = "Recinto", nullable = false, length = 4)          
	private String recinto;                                           
	@Id
	@Column(name = "Id_Cli_Prov", nullable = false, length = 10)
	private String IdCliProv;
	@Column(name = "RecintoDest", nullable = false, length = 4)          
	private String recintoDest;                                           
	@Id
	@Column(name = "Num_Part", nullable = false, length = 10)
	private String numPart;
	@Id
	@Column(name = "Num_fact", nullable = false, length = 15)
	private String numFact;
	@Id
	@Column(name = "Num_Pedimento_entrada", nullable = false, length = 7)
	private String numPedimentoEntrada;	
	@Column(name = "Estatus", nullable = false, length = 2)     
	private String estatus;
	@Column(name = "Fecha_Alta", nullable = false, length = 10)       
	private String fechaAlta;                                          
	@Column(name = "Fecha_Mod", nullable = false, length = 10)       
	private String fechaMod;                                          
	@Column(name = "HORA", nullable = false, length = 8)             
	private String hora;                                              
	@Column(name = "USER_Mod", nullable = false, length = 8)     
	private String userMod;
	@Column(name = "EntSal", nullable = false, length = 1)     
	private String entSal;
	@Column(name = "Num_Fact_Ent", nullable = false, length = 15)
	private String numFactEnt;
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getRecinto() {
		return recinto;
	}
	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}
	public String getIdCliProv() {
		return IdCliProv;
	}
	public void setIdCliProv(String idCliProv) {
		IdCliProv = idCliProv;
	}
	public String getRecintoDest() {
		return recintoDest;
	}
	public void setRecintoDest(String recintoDest) {
		this.recintoDest = recintoDest;
	}
	public String getNumPart() {
		return numPart;
	}
	public void setNumPart(String numPart) {
		this.numPart = numPart;
	}
	public String getNumFact() {
		return numFact;
	}
	public void setNumFact(String numFact) {
		this.numFact = numFact;
	}
	public String getNumPedimentoEntrada() {
		return numPedimentoEntrada;
	}
	public void setNumPedimentoEntrada(String numPedimentoEntrada) {
		this.numPedimentoEntrada = numPedimentoEntrada;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(String fechaMod) {
		this.fechaMod = fechaMod;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getUserMod() {
		return userMod;
	}
	public void setUserMod(String userMod) {
		this.userMod = userMod;
	}
	public String getEntSal() {
		return entSal;
	}
	public void setEntSal(String entSal) {
		this.entSal = entSal;
	}
	public String getNumFactEnt() {
		return numFactEnt;
	}
	public void setNumFactEnt(String numFactEnt) {
		this.numFactEnt = numFactEnt;
	}

	
}
