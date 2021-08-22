package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(DetCatApId.class)
@Table(name = "sysdtape2")
public class DetCatAp {

	@Id
	@Column(name = "CLVAP", nullable = false, length = 4)
	private String clvap;
	@Id
	@Column(name = "ID1", nullable = false, length = 5)
	private String id1;
	@Id
	@Column(name = "ID2", nullable = false, length = 8)
	private String id2;
	
	@Column(name = "IND_VIS", nullable = false, length = 1)
	private String indVis;	
	
	@Column(name = "DESC_CORTA", nullable = false, length = 50)
	private String desCorta;
	
	@Column(name = "DESC_LARGA", nullable = false, length = 130)
	private String desLarga;
	
	@Column(name = "DEL_LOGICO")
	private int delLogico;

	public String getClvap() {
		return clvap;
	}

	public void setClvap(String clvap) {
		this.clvap = clvap;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getIndVis() {
		return indVis;
	}

	public void setIndVis(String indVis) {
		this.indVis = indVis;
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

	public int getDelLogico() {
		return delLogico;
	}

	public void setDelLogico(int delLogico) {
		this.delLogico = delLogico;
	}
	

	
	
}
