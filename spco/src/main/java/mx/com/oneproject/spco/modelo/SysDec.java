package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SysDecId.class)
@Table(name = "SYS_RECINTOS")
public class SysDec {

	@Id
	@Column(name = "ID_Empresa", nullable = false, length = 4)
	private BigDecimal idEmpresa; 

	@Id
	@Column(name = "ID_Recinto", nullable = false, length = 4)
	private BigDecimal idRecinto; 

	@Column(name = "Fecha", nullable = false, length = 10)	
	private Date fecha;
	
	@Column(name = "Transaccion", nullable = false, length = 4)
	private BigDecimal transaccion; 

	@Column(name = "Desc_Larga", nullable = false, length = 100)	
	private String descLarga; 
	
	@Column(name = "HORA", nullable = false, length = 8)	
	private String hora;

	@Column(name = "USER_Mod", nullable = false, length = 8)	
	private String userMod;

	public BigDecimal getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(BigDecimal idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public BigDecimal getIdRecinto() {
		return idRecinto;
	}

	public void setIdRecinto(BigDecimal idRecinto) {
		this.idRecinto = idRecinto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(BigDecimal transaccion) {
		this.transaccion = transaccion;
	}

	public String getDescLarga() {
		return descLarga;
	}

	public void setDescLarga(String descLarga) {
		this.descLarga = descLarga;
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
