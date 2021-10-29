package mx.com.oneproject.spco.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysAduPartId;
import mx.com.oneproject.spco.repositorio.IMCtarcFracc1Repo;
import mx.com.oneproject.spco.result.AnsCtarFracc1;
import mx.com.oneproject.spco.result.AnsCtarFraccCons;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/Fracc")
public class RestCtarcFracc1Controller {

	
	@Autowired
	private IMCtarcFracc1Repo fracAranc;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public AnsCtarFracc1 listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestCtarcFracc1Controller listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestCtarcFracc1Controller listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsCtarFracc1 respuesta = new AnsCtarFracc1();
		
		respuesta.setCr("00");
		respuesta.setDescripcion("Correcto");
		respuesta.setContenido(fracAranc.findAll());
		return respuesta;
	}

	/**
	 * Esta clase define el método de borrado de SysAduPart
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/ConsultaFrac"})
	public AnsCtarFraccCons EliminaSysAduPartUm(@RequestParam(required = false, value = "umo") String umo,
										         @RequestParam(required = false, value = "umd") String umd,
										         HttpServletRequest peticion){
		
									System.out.print("\n\n + RestCtarcFracc1Controller Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestCtarcFracc1Controller Alta: " + peticion.getHeader("Authorization")+ "\n ");	
     	  // Validación de token    	
			AnsCtarFraccCons respuesta = new AnsCtarFraccCons();
			respuesta.setUmOrigen(umo);
			respuesta.setUmDestino(umd);
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestCtarcFracc1Controller token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestCtarcFracc1Controller Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (fracAranc.findByiDUMOAndiDUMD(umo,umd) == null){
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe factor");
			        return respuesta;
				  } else {
				    	//-------------
						respuesta.setFactor(String.valueOf(fracAranc.findByiDUMOAndiDUMD(umo,umd)));
						respuesta.setCr("00");
						respuesta.setDescripcion("Correcto");
						return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}	
}
