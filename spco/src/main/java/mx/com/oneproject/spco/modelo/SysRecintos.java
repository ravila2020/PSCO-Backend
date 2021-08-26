package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SysRecintosId.class)
@Table(name = "SYS_RECINTOS")
public class SysRecintos {

	@Id
	@Column(name = "ID_Empresa", nullable = false, length = 4)
	private BigDecimal idEmpresa; 

	@Id
	@Column(name = "ID_Recinto", nullable = false, length = 4)
	private BigDecimal idRecinto; 

	@Column(name = "Desc_Recinto", nullable = false, length = 40)	
	private String descRecinto; 

	@Column(name = "Estatus", nullable = false, length = 1)	
	private String estatus; 	

	@Column(name = "Term_User", nullable = false, length = 8)	
	private String termUser; 	
	
	@Column(name = "User_Mod", nullable = false, length = 8)	
	private String userMod; 	
	
	@Column(name = "Prog_User", nullable = false, length = 8)	
	private String progUser;

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

	public String getDescRecinto() {
		return descRecinto;
	}

	public void setDescRecinto(String descRecinto) {
		this.descRecinto = descRecinto;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getTermUser() {
		return termUser;
	}

	public void setTermUser(String termUser) {
		this.termUser = termUser;
	}

	public String getUserMod() {
		return userMod;
	}

	public void setUserMod(String userMod) {
		this.userMod = userMod;
	}

	public String getProgUser() {
		return progUser;
	}

	public void setProgUser(String progUser) {
		this.progUser = progUser;
	}


}
