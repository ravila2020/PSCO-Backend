package mx.com.oneproject.spco.rest;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.modelo.CodPost;
import mx.com.oneproject.spco.repositorio.IMCodPosRepo;
import mx.com.oneproject.spco.result.AnsCodPostal;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/CP")
public class RestCodPostController {


		@Autowired
		private IMCodPosRepo codPostal;
		
		// Consulta de la lista de sys_usuarios con validacion de token.
		@GetMapping
		public AnsCodPostal listar(HttpServletRequest peticion,
				@RequestParam(required = false, value = "cp") String cp){
			
			System.out.print("\n\n + RestcodPoslController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
			System.out.print("\n\n + RestcodPoslController listar: " + peticion.getHeader("Authorization")+ "\n ");	
			
			AnsCodPostal resultado = new AnsCodPostal();
			Optional<CodPost> informacionCP = codPostal.findById(cp);
			if(informacionCP.isPresent())
			{
				resultado.setCr("00");
				resultado.setDescripcion("Correcto");
			//	resultado.setContenido(informacionCP.get());
				resultado.setContenido(informacionCP);
			}
			else
			{
				resultado.setCr("99");
				resultado.setDescripcion("Sin información");
			}
			
			return resultado;
		}

		
}
