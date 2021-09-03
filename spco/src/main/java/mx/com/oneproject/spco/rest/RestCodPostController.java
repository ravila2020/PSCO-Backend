package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.modelo.CodPost;
import mx.com.oneproject.spco.repositorio.IMCodPosRepo;

//(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/CP")
public class RestCodPostController {


		@Autowired
		private IMCodPosRepo codPostal;
		
		// Consulta de la lista de sys_usuarios con validacion de token.
		@GetMapping
		public List<CodPost> listar(HttpServletRequest peticion){
			
			System.out.print("\n\n + RestcodPoslController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
			System.out.print("\n\n + RestcodPoslController listar: " + peticion.getHeader("Authorization")+ "\n ");	
			
			return codPostal.findAll();
		}

		
}
