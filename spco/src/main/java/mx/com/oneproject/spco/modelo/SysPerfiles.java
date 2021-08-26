package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SysPerfilesId.class)
@Table(name = "SYS_PERFILES")
public class SysPerfiles {

	@Id
	@Column(name = "ID_Empresa", nullable = false, length = 4)
	private BigDecimal idEmpresa; 

	@Id
	@Column(name = "ID_Recinto", nullable = false, length = 4)
	private BigDecimal idRecinto; 

	@Id
	@Column(name = "ID_Perfil", nullable = false, length = 4)
	private String idPerfil; 

	@Column(name = "Desc_Perfil", nullable = false, length = 40)	
	private String descPerfil;

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

	public String getDescPerfil() {
		return descPerfil;
	}

	public void setDescPerfil(String descPerfil) {
		this.descPerfil = descPerfil;
	} 

	
}
