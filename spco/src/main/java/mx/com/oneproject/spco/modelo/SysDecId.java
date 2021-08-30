package mx.com.oneproject.spco.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SysDecId  implements Serializable {

	private BigDecimal idEmpresa; 
	private BigDecimal idRecinto;
	
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

}
