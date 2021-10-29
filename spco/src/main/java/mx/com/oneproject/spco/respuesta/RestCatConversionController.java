package mx.com.oneproject.spco.respuesta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.modelo.CatConversion;
import mx.com.oneproject.spco.repositorio.IMCatConversionRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/CatConversion")
public class RestCatConversionController {

	@Autowired
	private IMCatConversionRepo catConversion;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<CatConversion> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return catConversion.findAll();
	}

}
