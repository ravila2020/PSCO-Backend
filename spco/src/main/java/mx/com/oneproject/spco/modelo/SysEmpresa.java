package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_EMP")
public class SysEmpresa {

	@Id
	@Column(name = "ID_Empresa", nullable = false, length = 4)
	private BigDecimal idEmpresa; 
	
	@Column(name = "Desc_Empre", nullable = false, length = 40)	
	private String descEmpre; 

	@Column(name = "Emp_RFC", nullable = false, length = 13)	
	private String empRFC; 

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

	public String getDescEmpre() {
		return descEmpre;
	}

	public void setDescEmpre(String descEmpre) {
		this.descEmpre = descEmpre;
	}

	public String getEmpRFC() {
		return empRFC;
	}

	public void setEmpRFC(String empRFC) {
		this.empRFC = empRFC;
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
