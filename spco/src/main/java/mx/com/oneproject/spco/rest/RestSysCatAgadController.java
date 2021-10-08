package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysCatAgad;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMSysCatAgadRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.AnsSysCatAgad;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/CatAgad")
public class RestSysCatAgadController {

	@Autowired
	private IMSysCatAgadRepo catAgad;

	@Autowired
	private IMSysUserRepo sysUser;

	
	/**
	 * Esta clase define el método de consulta plana del catalogo de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 7/10/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<SysCatAgad> listar(HttpServletRequest peticion){
		return catAgad.findAll();
	}
	
	/**
	 * Esta clase define el método de alta de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 07/10/2021/A
	 * @see 
	 */	
	@PostMapping
	public AnsSysCatAgad altaSysCatAgad(HttpServletRequest peticion,
									@RequestBody SysCatAgad NuevoAgad){
		
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
 	  // Validación de token    	
			AnsSysCatAgad respuesta = new AnsSysCatAgad();
			String user = new String();
			String llave = new String();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
			if (token != null) {
				  user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
			SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
			BigDecimal recinto = usuarioProc.getIdRecinto();
			BigDecimal empresa = usuarioProc.getIdEmpresa();
			String recintoS = recinto.toString();
			String empresaS = empresa.toString();
			empresaS = String.format("%04d", empresa.intValue());
			recintoS = String.format("%04d", recinto.intValue());
							
	    	try {
		    	//-------------existe el producto?
	    		llave=NuevoAgad.getNumPat();
				if (catAgad.findById(llave).isEmpty()){
		    	//-------------
					SysCatAgad AgadProc = catAgad.save(NuevoAgad);
					System.out.print(" + RestSysCatAgadController insertar SysCatAgad: " + AgadProc.getNomAgAdu() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAgad);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("Ya existe agente");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de consulta de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 07/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/ConsAgente"})
	public AnsSysCatAgad consSysCatAgad(@RequestParam(required = false, value = "numPat") String numPat,
			                        HttpServletRequest peticion){
		
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			  // Validación de token    	
			AnsSysCatAgad respuesta = new AnsSysCatAgad();
			String user = new String();
			String llave = new String();
    		llave=numPat;
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
			if (token != null) {
			      user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
			SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
			BigDecimal recinto = usuarioProc.getIdRecinto();
			BigDecimal empresa = usuarioProc.getIdEmpresa();
			String recintoS = recinto.toString();
			String empresaS = empresa.toString();
			empresaS = String.format("%04d", empresa.intValue());
			recintoS = String.format("%04d", recinto.intValue());
							
	    	try {
		    	//-------------existe el producto?
				if (catAgad.findById(llave).isEmpty()){
		    	//-------------
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe agente");
			        return respuesta;
			     } else {
					Optional<SysCatAgad> AgadProc = catAgad.findById(llave);
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(AgadProc.get());
					return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

}
