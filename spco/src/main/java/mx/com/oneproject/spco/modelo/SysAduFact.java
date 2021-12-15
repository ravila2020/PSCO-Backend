package mx.com.oneproject.spco.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@IdClass(SysAduFactId.class)
@Table(name = "SYS_ADU_FACT4")
@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(name = "ParteCliente",procedureName = "ParteCliente", parameters= 
{@StoredProcedureParameter(	mode = ParameterMode.IN, name = "cli",type=String.class), 
 @StoredProcedureParameter(mode = ParameterMode.IN, name = "part",type=String.class), 
 @StoredProcedureParameter(mode = ParameterMode.IN, name = "ind",type=String.class)}),
@NamedStoredProcedureQuery(name = "ParteClienteInv",procedureName = "ParteClienteInv", parameters= 
{@StoredProcedureParameter(	mode = ParameterMode.IN, name = "cli",type=String.class), 
 @StoredProcedureParameter(mode = ParameterMode.IN, name = "part",type=String.class), 
 @StoredProcedureParameter(mode = ParameterMode.IN, name = "ind",type=String.class)})
})
public class SysAduFact {

	
	@Id
	@Column(name = "Id_Cli_Prov", nullable = false, length = 10)
	private String IdCliProv;
	@Id
	@Column(name = "Num_Part", nullable = false, length = 10)
	private String numPart;
	@Id
	@Column(name = "Num_fact", nullable = false, length = 15)
	private String numFact;
	@Id
	@Column(name = "ID_impo_expo", nullable = false, length = 1)
	private String iDImpoEexpo;
	@Column(name = "Fecha_Entrada", nullable = false, length = 10)   
	private String fechaEntrada;   	
	@Column(name = "Tipo_Cambio", nullable = false)          
	private Float tipoCambio; 
	@Column(name = "Pais_Fact", nullable = false, length = 3)      
	private String paisFact; 	
	@Id
	@Column(name = "Num_Pedimento_entrada", nullable = false, length = 7)
	private String numPedimentoEntrada;	

	@Column(name = "Num_Pedimento_salida", nullable = false, length = 7)
	private String numPedimentoSalida;	
	@Column(name = "CLV_PEDI", nullable = false, length = 2)      
	private String cLVPedi; 	
	@Column(name = "Num_Pate", nullable = false, length = 4)      
	private String numPate; 	
	@Column(name = "ADUANA", nullable = false, length = 4)      
	private String aduana; 	
	@Column(name = "Producto", nullable = false, length = 10)      
	private String producto;                                          
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
	@Column(name = "UnidaddeMedida", nullable = false, length = 2)              
	private String unidadDeMedida;                                               
	@Column(name = "Fracc_Aranc", nullable = false, length = 8)      
	private String fraccAranc;                                        
	@Column(name = "Neto_Original", nullable = false)     
	private Float netoOriginal;                                      
	@Column(name = "Bruto_original", nullable = false)    
	private Float brutoOriginal;                                     
	@Column(name = "Neto_Conv", nullable = false)         
	private Float netoConv;                                          
	@Column(name = "Bruto_Conv", nullable = false)        
	private Float brutoConv;                                         
	@Column(name = "Transport", nullable = false, length = 10)
	private String transport;
	@Column(name = "Clie_orig", nullable = false, length = 10)
	private String clieOrig;
	@Column(name = "Clie_dest", nullable = false, length = 10)
	private String clieDest;
	@Column(name = "INCOTERM", nullable = false, length = 3)
	private String iNCOTERM;
	@Column(name = "NUM_PLACA_TR", nullable = false, length = 10)
	private String nUMPlacaTr;
	@Column(name = "NUM_GUIA", nullable = false, length = 20)
	private String nUMGuia;
	@Column(name = "Cont_Caja", nullable = false, length = 12)
	private String contCaja;
	@Column(name = "Sello_Cand1", nullable = false, length = 21)
	private String selloCand1;
	@Column(name = "Sello_Cand2", nullable = false, length = 21)
	private String selloCand2;
	@Column(name = "Sello_Cand3", nullable = false, length = 21)
	private String selloCand3;
	@Column(name = "Nomb_Chof_TR", nullable = false, length = 60)
	private String nombChofTr;
	@Column(name = "PO", nullable = false, length = 5)
	private String pO;
	@Column(name = "Empresa", nullable = false, length = 4)          
	private String empresa;                                           
	@Column(name = "Recinto", nullable = false, length = 4)          
	private String recinto;                                           
	@Column(name = "Observaciones", nullable = false, length = 250)          
	private String observaciones;                                           
	@Column(name = "Fecha_Alta", nullable = false, length = 10)       
	private String fechaAlta;                                          
	@Column(name = "Fecha_Mod", nullable = false, length = 10)       
	private String fechaMod;                                          
	@Column(name = "HORA", nullable = false, length = 8)             
	private String hora;                                              
	@Column(name = "USER_Mod", nullable = false, length = 8)     
	private String userMod;
	@Column(name = "Estatus", nullable = false, length = 2)     
	private String estatus;
	@Column(name = "EntSal", nullable = false, length = 1)     
	private String entSal;
	@Column(name = "Num_Fact_Ent", nullable = false, length = 15)
	private String numFactEnt;
	
	
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
	public String getNumFact() {
		return numFact;
	}
	public void setNumFact(String numFact) {
		this.numFact = numFact;
	}
	public String getiDImpoEexpo() {
		return iDImpoEexpo;
	}
	public void setiDImpoEexpo(String iDImpoEexpo) {
		this.iDImpoEexpo = iDImpoEexpo;
	}
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Float getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(Float tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public String getPaisFact() {
		return paisFact;
	}
	public void setPaisFact(String paisFact) {
		this.paisFact = paisFact;
	}
	public String getNumPedimentoEntrada() {
		return numPedimentoEntrada;
	}
	public void setNumPedimentoEntrada(String numPedimentoEntrada) {
		this.numPedimentoEntrada = numPedimentoEntrada;
	}
	public String getNumPedimentoSalida() {
		return numPedimentoSalida;
	}
	public void setNumPedimentoSalida(String numPedimentoSalida) {
		this.numPedimentoSalida = numPedimentoSalida;
	}
	public String getcLVPedi() {
		return cLVPedi;
	}
	public void setcLVPedi(String cLVPedi) {
		this.cLVPedi = cLVPedi;
	}
	public String getNumPate() {
		return numPate;
	}
	public void setNumPate(String numPate) {
		this.numPate = numPate;
	}
	public String getAduana() {
		return aduana;
	}
	public void setAduana(String aduana) {
		this.aduana = aduana;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
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
	public String getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(String unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public String getFraccAranc() {
		return fraccAranc;
	}
	public void setFraccAranc(String fraccAranc) {
		this.fraccAranc = fraccAranc;
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
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getClieOrig() {
		return clieOrig;
	}
	public void setClieOrig(String clieOrig) {
		this.clieOrig = clieOrig;
	}
	public String getClieDest() {
		return clieDest;
	}
	public void setClieDest(String clieDest) {
		this.clieDest = clieDest;
	}
	public String getiNCOTERM() {
		return iNCOTERM;
	}
	public void setiNCOTERM(String iNCOTERM) {
		this.iNCOTERM = iNCOTERM;
	}
	public String getnUMPlacaTr() {
		return nUMPlacaTr;
	}
	public void setnUMPlacaTr(String nUMPlacaTr) {
		this.nUMPlacaTr = nUMPlacaTr;
	}
	public String getnUMGuia() {
		return nUMGuia;
	}
	public void setnUMGuia(String nUMGuia) {
		this.nUMGuia = nUMGuia;
	}
	public String getContCaja() {
		return contCaja;
	}
	public void setContCaja(String contCaja) {
		this.contCaja = contCaja;
	}
	public String getSelloCand1() {
		return selloCand1;
	}
	public void setSelloCand1(String selloCand1) {
		this.selloCand1 = selloCand1;
	}
	public String getSelloCand2() {
		return selloCand2;
	}
	public void setSelloCand2(String selloCand2) {
		this.selloCand2 = selloCand2;
	}
	public String getSelloCand3() {
		return selloCand3;
	}
	public void setSelloCand3(String selloCand3) {
		this.selloCand3 = selloCand3;
	}
	public String getNombChofTr() {
		return nombChofTr;
	}
	public void setNombChofTr(String nombChofTr) {
		this.nombChofTr = nombChofTr;
	}
	public String getpO() {
		return pO;
	}
	public void setpO(String pO) {
		this.pO = pO;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getEntSal() {
		return entSal;
	}
	public void setEntSal(String entSal) {
		this.entSal = entSal;
	}
	public String getNumFactEnt() {
		return numFactEnt;
	}
	public void setNumFactEnt(String numFactEnt) {
		this.numFactEnt = numFactEnt;
	}
	

	
}
