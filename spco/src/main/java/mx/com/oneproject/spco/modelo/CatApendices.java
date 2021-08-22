package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sysdtapl1")
public class CatApendices {

	@Id
	@Column(name = "CLVAP", nullable = false, length = 4)
	private String clvap;
	@Column(name = "DEL_LOGICO")
	private int delLogico;
	@Column(name = "DESC_CORTA", nullable = false, length = 50)
	private String desCorta;
	@Column(name = "DESC_LARGA", nullable = false, length = 130)
	private String desLarga;

	public CatApendices() {
	}

	public String getClvap() {
		return clvap;
	}

	public void setClvap(String clvap) {
		this.clvap = clvap;
	}

	public int getDelLogico() {
		return delLogico;
	}

	public void setDelLogico(int delLogico) {
		this.delLogico = delLogico;
	}

	public String getDesCorta() {
		return desCorta;
	}

	public void setDesCorta(String desCorta) {
		this.desCorta = desCorta;
	}

	public String getDesLarga() {
		return desLarga;
	}

	public void setDesLarga(String desLarga) {
		this.desLarga = desLarga;
	}


}
