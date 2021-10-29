package mx.com.oneproject.spco.rest;

import java.util.List;
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
import mx.com.oneproject.spco.result.AnsCodPostalMult;

@CrossOrigin(origins = "*",maxAge = 3600)
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
			Optional<CodPost> informacionCP = codPostal.findByCodigo(cp);
			if(informacionCP.isEmpty())
			{
				resultado.setCr("99");
				resultado.setDescripcion("Sin información");
			}
			else
			{
				resultado.setCr("00");
				resultado.setDescripcion("Correcto");
			//	resultado.setContenido(informacionCP.get());
				resultado.setContenido(informacionCP);
			}
			
			return resultado;
		}

		@GetMapping(path = {"/CPMultiple"})
		public AnsCodPostalMult consultar(HttpServletRequest peticion,
				@RequestParam(required = false, value = "cp") String cp){
			
			System.out.print("\n\n + RestcodPoslController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
			System.out.print("\n\n + RestcodPoslController listar: " + peticion.getHeader("Authorization")+ "\n ");	
			
			AnsCodPostalMult resultado = new AnsCodPostalMult();
			List<CodPost> informacionCP = codPostal.findByCodigoMult(cp);
			if(informacionCP.isEmpty())
			{
				resultado.setCr("99");
				resultado.setDescripcion("Sin información");
			}
			else
			{
				resultado.setCr("00");
				resultado.setDescripcion("Correcto");
			//	resultado.setContenido(informacionCP.get());
				resultado.setContenido(informacionCP);
			}
			
			return resultado;
		}

		
}
