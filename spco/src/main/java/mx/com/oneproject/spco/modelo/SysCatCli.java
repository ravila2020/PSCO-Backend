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
	private String idCliProv;
	
	@Column(name= "Nom_Denom"   ,nullable = false ,length = 120)	
	private String nomDenov;
	
	@Column(name= "Nom_Contacto"   ,nullable = false ,length = 120)	
	private String nomContacto;
	
	@Column(name= "Nal"   ,nullable = false ,length = 10)
	private String nal;
	
	@Column(name= "IMMEX_CVE_REC"   ,nullable = false )
	private BigDecimal immexCveRec;
	
	@Column(name= "ID_TAX"   ,nullable = false ,length = 20)
	private String idTax;

	@Column(name= "RFC"   ,nullable = false ,length = 13)
	private String rFC;
	
	@Column(name= "CURP"   ,nullable = false ,length = 18)
	private String cURP;
	
	@Column(name= "Pais"   ,nullable = false ,length = 3)
	private String país;
	
	@Column(name= "Calle"   ,nullable = false ,length = 80)
	private String calle;
	
	@Column(name= "Num_Ext"   ,nullable = false ,length = 10)
	private String numExt;
	
	@Column(name= "Num_Inte"   ,nullable = false ,length = 10)
	private String numInte;
	
	@Column(name= "C_P"   ,nullable = false ,length = 5)
	private String cP;
	
	@Column(name= "Colonia"   ,nullable = false ,length = 80)
	private String colonia;
	
	@Column(name= "Estado"   ,nullable = false ,length = 3)
	private String estado;
	
	@Column(name= "Municipio"   ,nullable = false ,length = 3)
	private String municipio;
	
	@Column(name= "Localidad"   ,nullable = false ,length = 4)
	private String localidad;
	
	@Column(name= "E_Mail"   ,nullable = false ,length = 40)
	private String eMail;
	
	@Column(name= "Tel"   ,nullable = false ,length = 20)
	private BigDecimal tel;
	
	@Column(name= "Ind_Act"   ,nullable = false ,length = 1)
	private String indAct;
	
	@Column(name= "Tipo"   ,nullable = false ,length = 1)
	private String tipo;
	
	@Column(name= "Empresa"   ,nullable = false ,length = 4)
	private String empresa;

	@Column(name= "Recinto"   ,nullable = false ,length = 4)
	private String recinto;
	
	@Column(name= "Fecha_Alta"   ,nullable = false )
	private Date fechaAlta;
	
	@Column(name= "Fecha_Mod"   ,nullable = false )
	private Date fechaMod;
	
	@Column(name= "HORA"   ,nullable = false ,length = 8)
	private String hora;
	
	@Column(name= "USER_Mod"   ,nullable = false ,length = 8)
	private String userMod;

	public String getIdCliProv() {
		return idCliProv;
	}

	public void setIdCliProv(String idCliProv) {
		this.idCliProv = idCliProv;
	}

	public String getNomDenov() {
		return nomDenov;
	}

	public void setNomDenov(String nomDenov) {
		this.nomDenov = nomDenov;
	}

	public String getNomContacto() {
		return nomContacto;
	}

	public void setNomContacto(String nomContacto) {
		this.nomContacto = nomContacto;
	}

	public String getNal() {
		return nal;
	}

	public void setNal(String nal) {
		this.nal = nal;
	}

	public BigDecimal getImmexCveRec() {
		return immexCveRec;
	}

	public void setImmexCveRec(BigDecimal immexCveRec) {
		this.immexCveRec = immexCveRec;
	}

	public String getIdTax() {
		return idTax;
	}

	public void setIdTax(String idTax) {
		this.idTax = idTax;
	}

	public String getrFC() {
		return rFC;
	}

	public void setrFC(String rFC) {
		this.rFC = rFC;
	}

	public String getcURP() {
		return cURP;
	}

	public void setcURP(String cURP) {
		this.cURP = cURP;
	}

	public String getPaís() {
		return país;
	}

	public void setPaís(String país) {
		this.país = país;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumExt() {
		return numExt;
	}

	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}

	public String getNumInte() {
		return numInte;
	}

	public void setNumInte(String numInte) {
		this.numInte = numInte;
	}

	public String getcP() {
		return cP;
	}

	public void setcP(String cP) {
		this.cP = cP;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public BigDecimal getTel() {
		return tel;
	}

	public void setTel(BigDecimal tel) {
		this.tel = tel;
	}

	public String getIndAct() {
		return indAct;
	}

	public void setIndAct(String indAct) {
		this.indAct = indAct;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getRecinto() {
		return recinto;
	}

	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaMod() {
		return fechaMod;
	}

	public void setFechaMod(Date fechaMod) {
		this.fechaMod = fechaMod;
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
