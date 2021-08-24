package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_USUARIOS1")
@IdClass(SysUsuariosId.class)
public class SysUsuarios {

	@Id
	@Column(name = "ID_Empresa", nullable = false, length = 4)
	private BigDecimal idEmpresa; 
	@Id	
	@Column(name = "ID_Recinto", nullable = false, length = 4)	
	private BigDecimal idRecinto; 
	@Id	
	@Column(name = "ID_usuario", nullable = false, length = 10)	
	private BigDecimal idUsuario; 
	
	@Column(name = "ID_Perfil", nullable = false, length = 4)	
	private String idPerfil; 

	@Column(name = "Password", nullable = false, length = 8)	
	private String password; 
	
	@Column(name = "Intentos", nullable = false, length = 10)	
	private BigDecimal intentos; 
	
	@Column(name = "Estatus", nullable = false, length = 1)	
	private String estatus; 
	
	@Column(name = "Fec_Est", nullable = false, length = 10)	
	private String fecEst; 

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

	public BigDecimal getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getIntentos() {
		return intentos;
	}

	public void setIntentos(BigDecimal intentos) {
		this.intentos = intentos;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFecEst() {
		return fecEst;
	}

	public void setFecEst(String fecEst) {
		this.fecEst = fecEst;
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
