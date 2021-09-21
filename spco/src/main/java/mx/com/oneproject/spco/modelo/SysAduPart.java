package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(SysAduPartId.class)
@Table(name = "SYS_ADU_PART2")
public class SysAduPart {

	@Id
	@Column(name = "Id_Cli_Prov", nullable = false, length = 10)
	private String IdCliProv;
	@Id
	@Column(name = "Num_Part", nullable = false, length = 10)
	private String numPart;
	@Id
	@Column(name = "Num_pedimento", nullable = false, length = 7)
	private String numPedimento;

	@Column(name = "Fecha_Alta", nullable = false, length = 10)      
	private String fechaAlta;                                         

	@Column(name = "Fecha_Entrada", nullable = false, length = 10)   
	private String fechaEntrada;                                      

	@Column(name = "Producto", nullable = false, length = 10)      
	private String producto;                                          

	@Column(name = "Pais_Origen", nullable = false, length = 3)      
	private String paisOrigen;                                        

	@Column(name = "Cantidad", nullable = false)          
	private Float cantidad;                                          

	@Column(name = "Costo_unit_dls", nullable = false)    
	private Float costounitdls;                                      

	@Column(name = "Costo_Total_dls", nullable = false)   
	private Float costoTotaldls;                                     

	@Column(name = "Costo_unit_MXP", nullable = false)    
	private Float costounitMXP;                                      

	@Column(name = "Costo_total_MXP", nullable = false)   
	private Float costototalMXP;                                     

	@Column(name = "Tip_Cambio", nullable = false)        
	private Float tipCambio;                                         

	@Column(name = "UMC", nullable = false, length = 2)              
	private String uMC;                                               

	@Column(name = "UMT", nullable = false, length = 2)              
	private String uMT;                                               

	@Column(name = "Fracc_Aranc", nullable = false, length = 8)      
	private String fraccAranc;                                        

	@Column(name = "Nico", nullable = false, length = 2)             
	private String nico;                                              

	@Column(name = "Neto_Original", nullable = false)     
	private Float netoOriginal;                                      

	@Column(name = "Bruto_original", nullable = false)    
	private Float brutoOriginal;                                     

	@Column(name = "Neto_Conv", nullable = false)         
	private Float netoConv;                                          

	@Column(name = "Bruto_Conv", nullable = false)        
	private Float brutoConv;                                         

	@Column(name = "Fecha_Venc", nullable = false, length = 10)      
	private String fechaVenc;                                         

	@Column(name = "Empresa", nullable = false, length = 4)          
	private String empresa;                                           

	@Column(name = "Recinto", nullable = false, length = 4)          
	private String recinto;                                           

	@Column(name = "Fecha_Registro", nullable = false, length = 10)      
	private String fechaRegistro;                                         

	@Column(name = "Fecha_Mod", nullable = false, length = 10)       
	private String fechaMod;                                          
	
	@Column(name = "HORA", nullable = false, length = 8)             
	private String hora;                                              
	
	@Column(name = "USER_Mod", nullable = false, length = 8)     
	private String userMod;

	public String getIdCliProv() {
		return IdCliProv;
	}

	public void setIdCliProv(String idCliProv) {
		IdCliProv = idCliProv;
	}

	public String getNumPart() {
		return numPart;
	}

	public void setNumPart(String numPart) {
		this.numPart = numPart;
	}

	public String getNumPedimento() {
		return numPedimento;
	}

	public void setNumPedimento(String numPedimento) {
		this.numPedimento = numPedimento;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Float getCostounitdls() {
		return costounitdls;
	}

	public void setCostounitdls(Float costounitdls) {
		this.costounitdls = costounitdls;
	}

	public Float getCostoTotaldls() {
		return costoTotaldls;
	}

	public void setCostoTotaldls(Float costoTotaldls) {
		this.costoTotaldls = costoTotaldls;
	}

	public Float getCostounitMXP() {
		return costounitMXP;
	}

	public void setCostounitMXP(Float costounitMXP) {
		this.costounitMXP = costounitMXP;
	}

	public Float getCostototalMXP() {
		return costototalMXP;
	}

	public void setCostototalMXP(Float costototalMXP) {
		this.costototalMXP = costototalMXP;
	}

	public Float getTipCambio() {
		return tipCambio;
	}

	public void setTipCambio(Float tipCambio) {
		this.tipCambio = tipCambio;
	}

	public String getuMC() {
		return uMC;
	}

	public void setuMC(String uMC) {
		this.uMC = uMC;
	}

	public String getuMT() {
		return uMT;
	}

	public void setuMT(String uMT) {
		this.uMT = uMT;
	}

	public String getFraccAranc() {
		return fraccAranc;
	}

	public void setFraccAranc(String fraccAranc) {
		this.fraccAranc = fraccAranc;
	}

	public String getNico() {
		return nico;
	}

	public void setNico(String nico) {
		this.nico = nico;
	}

	public Float getNetoOriginal() {
		return netoOriginal;
	}

	public void setNetoOriginal(Float netoOriginal) {
		this.netoOriginal = netoOriginal;
	}

	public Float getBrutoOriginal() {
		return brutoOriginal;
	}

	public void setBrutoOriginal(Float brutoOriginal) {
		this.brutoOriginal = brutoOriginal;
	}

	public Float getNetoConv() {
		return netoConv;
	}

	public void setNetoConv(Float netoConv) {
		this.netoConv = netoConv;
	}

	public Float getBrutoConv() {
		return brutoConv;
	}

	public void setBrutoConv(Float brutoConv) {
		this.brutoConv = brutoConv;
	}

	public String getFechaVenc() {
		return fechaVenc;
	}

	public void setFechaVenc(String fechaVenc) {
		this.fechaVenc = fechaVenc;
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

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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
