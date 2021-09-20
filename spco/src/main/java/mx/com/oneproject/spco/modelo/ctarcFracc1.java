package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
// @ I d C l a s s ( c t a r c F r a c c 1 I d . c l a s s ) 
@Table(name = "CTARC_FRACC2")
public class ctarcFracc1 {

	@Id
	@Column(name = "FRACCION", nullable = false, length = 8)	
	private String fraccion; 

	@Column(name = "FEC_APLI", nullable = false, length = 10)	
	private String fecAplic; 

	@Column(name = "FEC_DOF", nullable = false, length = 10)	
	private String fecDOF; 

	@Column(name = "FEC_VIG", nullable = false, length = 10)	
	private String fecVig; 

	@Column(name = "ADV_IMP", nullable = false, length = 7)	
	private String advImp; 

	@Column(name = "ADV_EXP", nullable = false, length = 6)	
	private String advExp; 

	@Column(name = "UNIDAD", nullable = false, length = 3)	
	private String unidad; 

	@Column(name = "PORC_IVA", nullable = false, length = 6)	
	private String porcIVA;

	public String getFraccion() {
		return fraccion;
	}

	public void setFraccion(String fraccion) {
		this.fraccion = fraccion;
	}

	public String getFecAplic() {
		return fecAplic;
	}

	public void setFecAplic(String fecAplic) {
		this.fecAplic = fecAplic;
	}

	public String getFecDOF() {
		return fecDOF;
	}

	public void setFecDOF(String fecDOF) {
		this.fecDOF = fecDOF;
	}

	public String getFecVig() {
		return fecVig;
	}

	public void setFecVig(String fecVig) {
		this.fecVig = fecVig;
	}

	public String getAdvImp() {
		return advImp;
	}

	public void setAdvImp(String advImp) {
		this.advImp = advImp;
	}

	public String getAdvExp() {
		return advExp;
	}

	public void setAdvExp(String advExp) {
		this.advExp = advExp;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getPorcIVA() {
		return porcIVA;
	}

	public void setPorcIVA(String porcIVA) {
		this.porcIVA = porcIVA;
	} 

	
}
