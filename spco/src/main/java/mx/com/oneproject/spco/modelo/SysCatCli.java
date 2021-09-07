package mx.com.oneproject.spco.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_CAT_CLI")
public class SysCatCli {

	@Id
	@Column(name = "Id_Cli_Prov", nullable = false, length = 10)
	private String IdCliProv;

	@Column(name= "Nom_Denom"   ,nullable = false ,length = 120)	
	private String NomDenov;

	@Column(name= "Nom_Contacto"   ,nullable = false ,length = 120)	
	private String NomContacto;

	@Column(name= "Nal"   ,nullable = false ,length = 10)
	private String Nal;

	@Column(name= "IMMEX_CVE_REC"   ,nullable = false )
	private BigDecimal ImmexCveRec;

	@Column(name= "ID_TAX"   ,nullable = false ,length = 20)
	private String IdTax;

	@Column(name= "RFC"   ,nullable = false ,length = 13)
	private String Rfc;

	@Column(name= "CURP"   ,nullable = false ,length = 18)
	private String Curp;

	@Column(name= "Pais"   ,nullable = false ,length = 3)
	private String Pais;

	@Column(name= "Calle"   ,nullable = false ,length = 80)
	private String Calle;

	@Column(name= "Num_Ext"   ,nullable = false ,length = 10)
	private String NumExt;

	@Column(name= "Num_Inte"   ,nullable = false ,length = 10)
	private String NumInte;

	@Column(name= "C_P"   ,nullable = false ,length = 5)
	private String CP;

	@Column(name= "Colonia"   ,nullable = false ,length = 80)
	private String Colonia;

	@Column(name= "Estado"   ,nullable = false ,length = 3)
	private String Estado;

	@Column(name= "Municipio"   ,nullable = false ,length = 3)
	private String Municipio;

	@Column(name= "Localidad"   ,nullable = false ,length = 4)
	private String Localidad;

	@Column(name= "E_Mail"   ,nullable = false ,length = 40)
	private String EMail;

	@Column(name= "Tel"   ,nullable = false ,length = 20)
	private BigDecimal Tel;

	@Column(name= "Ind_Act"   ,nullable = false ,length = 1)
	private String IndAct;

	@Column(name= "Tipo"   ,nullable = false ,length = 1)
	private String Tipo;

	@Column(name= "Empresa"   ,nullable = false ,length = 4)
	private String Empresa;

	@Column(name= "Recinto"   ,nullable = false ,length = 4)
	private String Recinto;

	@Column(name= "Fecha_Alta"   ,nullable = false )
	private Date FechaAlta;

	@Column(name= "Fecha_Mod"   ,nullable = false )
	private Date FechaMod;

	@Column(name= "HORA"   ,nullable = false ,length = 8)
	private String Hora;

	@Column(name= "USER_Mod"   ,nullable = false ,length = 8)
	private String UserMod;

	public String getIdCliProv() {
		return IdCliProv;
	}

	public void setIdCliProv(String idCliProv) {
		IdCliProv = idCliProv;
	}

	public String getNomDenov() {
		return NomDenov;
	}

	public void setNomDenov(String nomDenov) {
		NomDenov = nomDenov;
	}

	public String getNomContacto() {
		return NomContacto;
	}

	public void setNomContacto(String nomContacto) {
		NomContacto = nomContacto;
	}

	public String getNal() {
		return Nal;
	}

	public void setNal(String nal) {
		Nal = nal;
	}

	public BigDecimal getImmexCveRec() {
		return ImmexCveRec;
	}

	public void setImmexCveRec(BigDecimal immexCveRec) {
		ImmexCveRec = immexCveRec;
	}

	public String getIdTax() {
		return IdTax;
	}

	public void setIdTax(String idTax) {
		IdTax = idTax;
	}

	public String getRfc() {
		return Rfc;
	}

	public void setRfc(String rfc) {
		Rfc = rfc;
	}

	public String getCurp() {
		return Curp;
	}

	public void setCurp(String curp) {
		Curp = curp;
	}

	public String getPaís() {
		return Pais;
	}

	public void setPaís(String pais) {
		Pais = pais;
	}

	public String getCalle() {
		return Calle;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}

	public String getNumExt() {
		return NumExt;
	}

	public void setNumExt(String numExt) {
		NumExt = numExt;
	}

	public String getNumInte() {
		return NumInte;
	}

	public void setNumInte(String numInte) {
		NumInte = numInte;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public String getColonia() {
		return Colonia;
	}

	public void setColonia(String colonia) {
		Colonia = colonia;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getMunicipio() {
		return Municipio;
	}

	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}

	public BigDecimal getTel() {
		return Tel;
	}

	public void setTel(BigDecimal tel) {
		Tel = tel;
	}

	public String getIndAct() {
		return IndAct;
	}

	public void setIndAct(String indAct) {
		IndAct = indAct;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(String empresa) {
		Empresa = empresa;
	}

	public String getRecinto() {
		return Recinto;
	}

	public void setRecinto(String recinto) {
		Recinto = recinto;
	}

	public Date getFechaAlta() {
		return FechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		FechaAlta = fechaAlta;
	}

	public Date getFechaMod() {
		return FechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		FechaMod = fechaMod;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getUserMod() {
		return UserMod;
	}

	public void setUserMod(String userMod) {
		UserMod = userMod;
	}
	
}
