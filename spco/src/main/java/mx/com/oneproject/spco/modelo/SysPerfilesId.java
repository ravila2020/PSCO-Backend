package mx.com.oneproject.spco.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysPerfilesId implements Serializable{

	private BigDecimal idEmpresa; 
	private BigDecimal idRecinto; 
	private String idPerfil;
	
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
	public String getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	} 
	
	
	
}
