package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysAduTrasp;
import mx.com.oneproject.spco.modelo.SysAduTraspId;
import mx.com.oneproject.spco.repositorio.IMSysAduTransRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduTrasp;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/AduTrasp")
public class RestSysAduTraspController {

	@Autowired
	private IMSysAduTransRepo aduTrasp;

	
	/**
	 * Esta clase define el método de consulta de registro de traspasos
	 * @author: Roberto Avila
	 * @version: 01/01/2022/A
	 * @see 
	 */	

	@GetMapping	
    public List<SysAduTrasp> listar(HttpServletRequest peticion){ 
		return aduTrasp.findAll(); 

	}

	/**
	 * Esta clase define el método de alta de SysAduTrasp
	 * @author: Roberto Avila
	 * @version: 01/01/2022/A
	 * @see 
	 */	
	@PostMapping
	public AnsSysAduTrasp altaSysAduTrasp(HttpServletRequest peticion,
									@RequestBody SysAduTrasp NuevoAduTrasp){
		
									System.out.print("\n\n + RestSysAduTraspController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduTraspController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = NuevoAduTrasp.getRecinto();
			String empresa = NuevoAduTrasp.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			NuevoAduTrasp.setRecinto(recinto);
			NuevoAduTrasp.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduTrasp respuesta = new AnsSysAduTrasp();
			SysAduTraspId llave = new SysAduTraspId();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduTraspController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduTraspController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
	    		// [Empresa] ASC,
	    		// [Recinto] ASC,
	    		// [Id_Cli_Prov] ASC,
	    		// [Num_Part] ASC,
	    		// [Num_fact] ASC,
	    		// [Num_Pedimento_entrada] ASC
	    		
	    		llave.setEmpresa(NuevoAduTrasp.getEmpresa());
	    		llave.setRecinto(NuevoAduTrasp.getRecinto());
	    		llave.setIdCliProv(NuevoAduTrasp.getIdCliProv());
	    		llave.setNumPart(NuevoAduTrasp.getNumPart());
	    		llave.setNumFact(NuevoAduTrasp.getNumFact());
	    		llave.setNumPedimentoEntrada(NuevoAduTrasp.getNumPedimentoEntrada());
				if (aduTrasp.findById(llave).isEmpty()){
		    	//-------------
					SysAduTrasp traspasoProc = aduTrasp.save(NuevoAduTrasp);
					System.out.print(" + RestSysAduTraspController insertar Traspaso: " + traspasoProc.getIdCliProv() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAduTrasp);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("Ya existe Empresa / Recinto / cliente / parte / factura / IndImpExp");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de modificacion de SysAduTrasp
	 * @author: Roberto Avila
	 * @version: 01/01/2022/A
	 * @see 
	 */	
	@PutMapping
	public AnsSysAduTrasp modifSysAduTrasp(HttpServletRequest peticion,
									@RequestBody SysAduTrasp ModifAduTrasp){
		
									System.out.print("\n\n + RestSysAduTraspController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduTraspController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = ModifAduTrasp.getRecinto();
			String empresa = ModifAduTrasp.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			ModifAduTrasp.setRecinto(recinto);
			ModifAduTrasp.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduTrasp respuesta = new AnsSysAduTrasp();
			SysAduTraspId llave = new SysAduTraspId();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduTraspController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduTraspController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
	    		// [Empresa] ASC,
	    		// [Recinto] ASC,
	    		// [Id_Cli_Prov] ASC,
	    		// [Num_Part] ASC,
	    		// [Num_fact] ASC,
	    		// [Num_Pedimento_entrada] ASC
	    		
	    		llave.setEmpresa(ModifAduTrasp.getEmpresa());
	    		llave.setRecinto(ModifAduTrasp.getRecinto());
	    		llave.setIdCliProv(ModifAduTrasp.getIdCliProv());
	    		llave.setNumPart(ModifAduTrasp.getNumPart());
	    		llave.setNumFact(ModifAduTrasp.getNumFact());
	    		llave.setNumPedimentoEntrada(ModifAduTrasp.getNumPedimentoEntrada());
				if (aduTrasp.findById(llave).isPresent()){
		    	//-------------
					SysAduTrasp traspasoProc = aduTrasp.save(ModifAduTrasp);
					System.out.print(" + RestSysAduTraspController modifica Traspaso: " + traspasoProc.getIdCliProv() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(ModifAduTrasp);
					return respuesta;
				  } else {
					respuesta.setCr("89");
					respuesta.setDescripcion("No existe Empresa / Recinto / cliente / parte / factura / IndImpExp");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de borrado de SysAduTrasp
	 * @author: Roberto Avila
	 * @version: 01/01/2022/A
	 * @see 
	 */	
	@DeleteMapping
	public AnsSysAduTrasp borradoSysAduTrasp(HttpServletRequest peticion,
									@RequestBody SysAduTrasp AduTrasp){
		
									System.out.print("\n\n + RestSysAduTraspController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduTraspController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = AduTrasp.getRecinto();
			String empresa = AduTrasp.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			AduTrasp.setRecinto(recinto);
			AduTrasp.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduTrasp respuesta = new AnsSysAduTrasp();
			SysAduTraspId llave = new SysAduTraspId();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduTraspController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduTraspController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
	    		// [Empresa] ASC,
	    		// [Recinto] ASC,
	    		// [Id_Cli_Prov] ASC,
	    		// [Num_Part] ASC,
	    		// [Num_fact] ASC,
	    		// [Num_Pedimento_entrada] ASC
	    		
	    		llave.setEmpresa(AduTrasp.getEmpresa());
	    		llave.setRecinto(AduTrasp.getRecinto());
	    		llave.setIdCliProv(AduTrasp.getIdCliProv());
	    		llave.setNumPart(AduTrasp.getNumPart());
	    		llave.setNumFact(AduTrasp.getNumFact());
	    		llave.setNumPedimentoEntrada(AduTrasp.getNumPedimentoEntrada());
				if (aduTrasp.findById(llave).isPresent()){
		    	//-------------
					aduTrasp.delete(AduTrasp);
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(AduTrasp);
					return respuesta;
				  } else {
					respuesta.setCr("89");
					respuesta.setDescripcion("No existe Empresa / Recinto / cliente / parte / factura / IndImpExp");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

}
