package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_CAT_AGAD")
public class SysCatAgad {

	@Id
	@Column(name = "Num_Pat", nullable = false, length = 4)
	private String numPat;
	
	@Column(name = "Nom_Ag_Adu", nullable = false, length = 7)
	private String nomAgAdu;
	@Column(name = "RFC", nullable = false, length = 13)
	private String rFC;
	@Column(name = "CURP", nullable = false, length = 18)
	private String cURP;
	@Column(name = "Pais", nullable = false, length = 3)
	private String pais;
	@Column(name = "Calle", nullable = false, length = 80)
	private String calle;
	@Column(name = "Num_Ext", nullable = false, length = 10)
	private String numExt;
	@Column(name = "Num_INT", nullable = false, length = 10)
	private String numINT;
	@Column(name = "C_P", nullable = false, length = 5)
	private String cP;
	@Column(name = "Colonia", nullable = false, length = 80)
	private String colonia;
	@Column(name = "Estado", nullable = false, length = 3)
	private String estado;
	@Column(name = "Municipio", nullable = false, length = 3)
	private String municipio;
	@Column(name = "Localidad", nullable = false, length = 4)
	private String localidad;
	@Column(name = "E_Mail", nullable = false, length = 40)
	private String eMail;
	@Column(name = "Tel", nullable = false, length = 40)
	private String tel;
	@Column(name = "Id_Act", nullable = false, length = 1)
	private String idAct;
	@Column(name = "Empresa", nullable = false, length = 4)          
	private String empresa;                                           
	@Column(name = "Recinto", nullable = false, length = 4)          
	private String recinto;                                           
	@Column(name = "Fecha_Alta", nullable = false, length = 10)      
	private String fechaAlta;                                         
	@Column(name = "Fecha_Mod", nullable = false, length = 10)       
	private String fechaMod;                                          
	@Column(name = "HORA", nullable = false, length = 8)             
	private String hora;                                              
	@Column(name = "USER_Mod", nullable = false, length = 8)     
	private String userMod;
	public String getNumPat() {
		return numPat;
	}
	public void setNumPat(String numPat) {
		this.numPat = numPat;
	}
	public String getNomAgAdu() {
		return nomAgAdu;
	}
	public void setNomAgAdu(String nomAgAdu) {
		this.nomAgAdu = nomAgAdu;
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
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
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
	public String getNumINT() {
		return numINT;
	}
	public void setNumINT(String numINT) {
		this.numINT = numINT;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdAct() {
		return idAct;
	}
	public void setIdAct(String idAct) {
		this.idAct = idAct;
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
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(String fechaMod) {
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
