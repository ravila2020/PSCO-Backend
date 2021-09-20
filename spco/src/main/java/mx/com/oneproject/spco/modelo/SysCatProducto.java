package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_CAT_PROD3")
public class SysCatProducto {

	@Id
	@Column(name = "Clve_Produc", nullable = false, length = 10)
	private String clveProduc; 

	@Column(name = "Tip_Prod", nullable = false, length = 1)	
	private String tipProd;
	
	@Column(name = "Ind_Vis", nullable = false, length = 1)	
	private String indVis;
	
	@Column(name = "Desc_Corta", nullable = false, length = 40)	
	private String descCorta;
	
	@Column(name = "Desc_Larga", nullable = false, length = 100)	
	private String descLarga;
	
	@Column(name = "Desc_Cor_Ing", nullable = false, length = 40)	
	private String descCorIng;
	
	@Column(name = "Desc_Lar_Ing", nullable = false, length = 100)	
	private String descLarIng;
	
	@Column(name = "UMC", nullable = false, length = 2)	
	private String uMC;

	@Column(name = "UMT", nullable = false, length = 2)	
	private String uMT;

	@Column(name = "Tip_Mat", nullable = false, length = 10)	
	private String Tip_Mat;
	
	@Column(name = "Empresa", nullable = false, length = 4)	
	private String empresa;
	
	@Column(name = "Recinto", nullable = false, length = 4)	
	private String recinto;
	
	@Column(name = "Fecha_Alta", nullable = false, length = 10)	
	private Date fechaAlta;
	
	@Column(name = "Fecha_Mod", nullable = false, length = 10)	
	private Date fechaMod;

	@Column(name = "HORA", nullable = false, length = 8)	
	private String hora;

	@Column(name = "USER_Mod", nullable = false, length = 8)	
	private String userMod;

	@Column(name = "Convers", nullable = false)	
	private float convers;

	@Column(name = "Costo_unit_DLS", nullable = false)	
	private float costoUnitDLS;

	@Column(name = "Costo_unit_MXP", nullable = false)	
	private float costoUnitMXP;

	@Column(name = "Fracc_Aranc", nullable = false, length = 8)	
	private String fraccAranc;

	@Column(name = "nico", nullable = false, length = 2)	
	private String nico;

	public String getClveProduc() {
		return clveProduc;
	}

	public void setClveProduc(String clveProduc) {
		this.clveProduc = clveProduc;
	}

	public String getTipProd() {
		return tipProd;
	}

	public void setTipProd(String tipProd) {
		this.tipProd = tipProd;
	}

	public String getIndVis() {
		return indVis;
	}

	public void setIndVis(String indVis) {
		this.indVis = indVis;
	}

	public String getDescCorta() {
		return descCorta;
	}

	public void setDescCorta(String descCorta) {
		this.descCorta = descCorta;
	}

	public String getDescLarga() {
		return descLarga;
	}

	public void setDescLarga(String descLarga) {
		this.descLarga = descLarga;
	}

	public String getDescCorIng() {
		return descCorIng;
	}

	public void setDescCorIng(String descCorIng) {
		this.descCorIng = descCorIng;
	}

	public String getDescLarIng() {
		return descLarIng;
	}

	public void setDescLarIng(String descLarIng) {
		this.descLarIng = descLarIng;
	}

	public String getuMC() {
		return uMC;
	}

	public void setuMC(String uMC) {
		this.uMC = uMC;
	}

	public String getuMT() {
		return uMT;
	}

	public void setuMT(String uMT) {
		this.uMT = uMT;
	}

	public String getTip_Mat() {
		return Tip_Mat;
	}

	public void setTip_Mat(String tip_Mat) {
		Tip_Mat = tip_Mat;
	}

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

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaMod() {
		return fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
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

	public float getConvers() {
		return convers;
	}

	public void setConvers(float convers) {
		this.convers = convers;
	}

	public float getCostoUnitDLS() {
		return costoUnitDLS;
	}

	public void setCostoUnitDLS(float costoUnitDLS) {
		this.costoUnitDLS = costoUnitDLS;
	}

	public float getCostoUnitMXP() {
		return costoUnitMXP;
	}

	public void setCostoUnitMXP(float costoUnitMXP) {
		this.costoUnitMXP = costoUnitMXP;
	}

	public String getFraccAranc() {
		return fraccAranc;
	}

	public void setFraccAranc(String fraccAranc) {
		this.fraccAranc = fraccAranc;
	}

	public String getNico() {
		return nico;
	}

	public void setNico(String nico) {
		this.nico = nico;
	}



	
	
}
