package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CodPostId.class)
@Table(name = "cod_post2")
public class CodPost {

	@Id
	@Column(name = "d_codigo", nullable = false, length = 5)
	public String dCodigo;
	@Column(name= "d_asenta" ,nullable = false ,length = 70)
	public String dAsenta;
	@Column(name= "d_tipo_asenta" ,nullable = false ,length = 35)
	public String dTipoAsenta;
	@Column(name= "D_mnpio" ,nullable = false ,length = 55)
	public String dMnpio;
	@Column(name= "d_estado" ,nullable = false ,length = 35)
	public String dEstado;
	@Column(name= "d_ciudad" ,nullable = false ,length = 55)
	public String dCiudad;
	@Column(name= "d_CP" ,nullable = false ,length = 5)
	public String dCP;
	@Column(name= "c_estado" ,nullable = false ,length = 2)
	public String cEstado;
	@Column(name= "c_oficina" ,nullable = false ,length = 5)
	public String cOficina;
	@Column(name= "c_CP" ,nullable = false ,length = 5)
	public String cCP;
	@Column(name= "c_tipo_asenta" ,nullable = false ,length = 2)
	public String cTipoAsenta;
	@Column(name= "c_mnpio" ,nullable = false ,length = 3)
	public String cMnpio;
	@Id
	@Column(name= "id_asenta_cpcons" ,nullable = false ,length = 4)
	public String idAsentaCpcons;
	@Column(name= "d_zona" ,nullable = false ,length = 10)
	public String dZona;
	@Column(name= "c_cve_ciudad" ,nullable = false ,length = 2)
	public String cCveCiudad;
	public String getdCodigo() {
		return dCodigo;
	}
	public void setdCodigo(String dCodigo) {
		this.dCodigo = dCodigo;
	}
	public String getdAsenta() {
		return dAsenta;
	}
	public void setdAsenta(String dAsenta) {
		this.dAsenta = dAsenta;
	}
	public String getdTipoAsenta() {
		return dTipoAsenta;
	}
	public void setdTipoAsenta(String dTipoAsenta) {
		this.dTipoAsenta = dTipoAsenta;
	}
	public String getdMnpio() {
		return dMnpio;
	}
	public void setdMnpio(String dMnpio) {
		this.dMnpio = dMnpio;
	}
	public String getdEstado() {
		return dEstado;
	}
	public void setdEstado(String dEstado) {
		this.dEstado = dEstado;
	}
	public String getdCiudad() {
		return dCiudad;
	}
	public void setdCiudad(String dCiudad) {
		this.dCiudad = dCiudad;
	}
	public String getdCP() {
		return dCP;
	}
	public void setdCP(String dCP) {
		this.dCP = dCP;
	}
	public String getcEstado() {
		return cEstado;
	}
	public void setcEstado(String cEstado) {
		this.cEstado = cEstado;
	}
	public String getcOficina() {
		return cOficina;
	}
	public void setcOficina(String cOficina) {
		this.cOficina = cOficina;
	}
	public String getcCP() {
		return cCP;
	}
	public void setcCP(String cCP) {
		this.cCP = cCP;
	}
	public String getcTipoAsenta() {
		return cTipoAsenta;
	}
	public void setcTipoAsenta(String cTipoAsenta) {
		this.cTipoAsenta = cTipoAsenta;
	}
	public String getcMnpio() {
		return cMnpio;
	}
	public void setcMnpio(String cMnpio) {
		this.cMnpio = cMnpio;
	}
	public String getIdAsentaCpcons() {
		return idAsentaCpcons;
	}
	public void setIdAsentaCpcons(String idAsentaCpcons) {
		this.idAsentaCpcons = idAsentaCpcons;
	}
	public String getdZona() {
		return dZona;
	}
	public void setdZona(String dZona) {
		this.dZona = dZona;
	}
	public String getcCveCiudad() {
		return cCveCiudad;
	}
	public void setcCveCiudad(String cCveCiudad) {
		this.cCveCiudad = cCveCiudad;
	}
	
	
	
	
	
}
