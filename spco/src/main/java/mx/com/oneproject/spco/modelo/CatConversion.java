package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cat_conversion")
public class CatConversion {

	@Column(name = "tipo", nullable = false, length = 1)
	private String tipo; 
	
	@Id
	@Column(name = "UMO", nullable = false, length = 35)
	private String uMO; 
	
	@Column(name = "UMD", nullable = false, length = 35)
	private String uMD; 

	@Column(name = "Factor", nullable = true)
	private Float factor; 

	@Column(name = "clvap07", nullable = false, length = 2)
	private String clvap07;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getuMO() {
		return uMO;
	}

	public void setuMO(String uMO) {
		this.uMO = uMO;
	}

	public String getuMD() {
		return uMD;
	}

	public void setuMD(String uMD) {
		this.uMD = uMD;
	}

	public Float getFactor() {
		return factor;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	public String getClvap07() {
		return clvap07;
	}

	public void setClvap07(String clvap07) {
		this.clvap07 = clvap07;
	} 

}
