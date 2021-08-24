package mx.com.oneproject.spco.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysUsuariosId implements Serializable{

	private BigDecimal idEmpresa; 
	private BigDecimal idRecinto; 
	private BigDecimal idUsuario;
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
	public BigDecimal getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}


}
