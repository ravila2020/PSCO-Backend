package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysCatCli;
import mx.com.oneproject.spco.repositorio.IMSysCatCliRepo;
import mx.com.oneproject.spco.result.AnsSysCatCli;

//(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Cli")
public class RestSysCliController {

	@Autowired
	private IMSysCatCliRepo sysCli;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<SysCatCli> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysCli.findAll();
	}

	// Alta de un Producto con validacion de token.
	@PostMapping
	public AnsSysCatCli altaSysCatCli(HttpServletRequest peticion,
									@RequestBody SysCatCli NuevoCliente){
		
									System.out.print("\n\n + RestSysCliController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCliController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysCatCli respuesta = new AnsSysCatCli();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCliController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCliController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (sysCli.findById(NuevoCliente.getIdCliProv()).isEmpty())
				{
		    	//-------------
					SysCatCli clienteProc = sysCli.save(NuevoCliente);
					System.out.print(" + RestSysCliController insertar Cliente: " + clienteProc.getNomDenov() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoCliente);
					return respuesta;
					}
			        else {
						respuesta.setCr("83");
						respuesta.setDescripcion("Ya existe cliente");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	
}
