package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(HistTipoCambioId.class)
@Table(name = "Hist_Tipo_Cambio")
public class HistTipoCambio {

	@Id
	@Column(name = "Clave", nullable = false, length = 5)
	private String clave; 
	@Id
	@Column(name = "Moneda", nullable = false, length = 3)
	private String moneda; 
	@Id
	@Column(name = "Fecha", nullable = false, length = 10)
	private String fecha; 
	
	@Column(name = "Factor", nullable = false)
	private float factor;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getFactor() {
		return factor;
	}

	public void setFactor(float factor) {
		this.factor = factor;
	} 


}
