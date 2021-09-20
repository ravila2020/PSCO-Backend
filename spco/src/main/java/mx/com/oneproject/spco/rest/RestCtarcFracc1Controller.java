package mx.com.oneproject.spco.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.repositorio.IMCtarcFracc1Repo;
import mx.com.oneproject.spco.result.AnsCtarFracc1;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Fracc")
public class RestCtarcFracc1Controller {

	
	@Autowired
	private IMCtarcFracc1Repo fracAranc;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public AnsCtarFracc1 listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsCtarFracc1 respuesta = new AnsCtarFracc1();
		
		respuesta.setCr("00");
		respuesta.setDescripcion("Correcto");
		respuesta.setContenido(fracAranc.findAll());
		return respuesta;
	}

	
}
