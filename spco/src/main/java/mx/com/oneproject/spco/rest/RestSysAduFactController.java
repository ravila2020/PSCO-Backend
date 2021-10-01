package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.modelo.SysAduFactId;
import mx.com.oneproject.spco.repositorio.IMSysAduFactRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduFact;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/AduFact")
public class RestSysAduFactController {
	
	@Autowired
	private IMSysAduFactRepo aduFact;

	/**
	 * Esta clase define el método de consulta plana de facturas
	 * @author: Roberto Avila
	 * @version: 1/10/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<SysAduFact> listar(HttpServletRequest peticion){
		return aduFact.findAll();
	}

	
	/**
	 * Esta clase define el método de alta de SysAduFact
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@PostMapping
	public AnsSysAduFact altaSysAduFact(HttpServletRequest peticion,
									@RequestBody SysAduFact NuevoAduFact){
		
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = NuevoAduFact.getRecinto();
			String empresa = NuevoAduFact.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			NuevoAduFact.setRecinto(recinto);
			NuevoAduFact.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduFact respuesta = new AnsSysAduFact();
			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduFactController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
	    		llave.setIdCliProv(NuevoAduFact.getIdCliProv());
	    		llave.setNumPart(NuevoAduFact.getNumPart());
	    		llave.setNumFact(NuevoAduFact.getNumFact());
	    		llave.setiDImpoEexpo(NuevoAduFact.getiDImpoEexpo());
				if (aduFact.findById(llave).isEmpty()){
		    	//-------------
					SysAduFact facturaProc = aduFact.save(NuevoAduFact);
					System.out.print(" + RestSysAduFactController insertar Factura: " + facturaProc.getIdCliProv() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAduFact);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("Ya existe cliente / parte / factura / IndImpExp");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

}
