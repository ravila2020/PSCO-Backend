package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.modelo.SysRecintos;
import mx.com.oneproject.spco.repositorio.IMSysRecinRepo;
import mx.com.oneproject.spco.result.AnsSysRecintos;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/Recintos")
public class RestSysRecintosController {

	@Autowired
	private IMSysRecinRepo repoRecinto;

	// Consulta de la lista de recintos.
	@GetMapping
	public List<SysRecintos> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysRecintosController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysRecintosController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return repoRecinto.findAll();
	}

	// Consulta de la lista de recintos.
	@GetMapping(path = {"/Lista"})
	public List<String> lista(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysRecintosController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysRecintosController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return repoRecinto.findByUnicos();
	}

	
	// Consulta de la lista de recintos con validacion de token.
	@GetMapping(path = {"/ListaRecintos"})
	public AnsSysRecintos listaRecintos(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysRecintosController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysRecintosController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsSysRecintos respuesta = new AnsSysRecintos();

		  // Validación de token    	
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

		
		respuesta.setCr("00");
		respuesta.setDescripcion("Consulta correcta");
		respuesta.setContenido(repoRecinto.findAll());
		return (respuesta);
	}

}
