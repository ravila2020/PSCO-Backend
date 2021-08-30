package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "SYS_CAT_PROD2")
	public class SysCatProd {

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
		
		@Column(name = "UM", nullable = false, length = 2)	
		private String uM;

		@Column(name = "Tip_Mat", nullable = false, length = 10)	
		private String Tip_Mat;
		
		@Column(name = "Empresa", nullable = false, length = 4)	
		private BigDecimal empresa;
		
		@Column(name = "Recinto", nullable = false, length = 4)	
		private BigDecimal recinto;
		
		@Column(name = "Fecha_Alta", nullable = false, length = 10)	
		private Date fechaAlta;
		
		@Column(name = "Fecha_Mod", nullable = false, length = 10)	
		private Date fechaMod;

		@Column(name = "HORA", nullable = false, length = 8)	
		private String hora;

		@Column(name = "USER_Mod", nullable = false, length = 8)	
		private String userMod;

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

		public String getuM() {
			return uM;
		}

		public void setuM(String uM) {
			this.uM = uM;
		}

		public String getTip_Mat() {
			return Tip_Mat;
		}

		public void setTip_Mat(String tip_Mat) {
			Tip_Mat = tip_Mat;
		}

		public BigDecimal getEmpresa() {
			return empresa;
		}

		public void setEmpresa(BigDecimal empresa) {
			this.empresa = empresa;
		}

		public BigDecimal getRecinto() {
			return recinto;
		}

		public void setRecinto(BigDecimal recinto) {
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

		
		
	
}
