package mx.com.oneproject.spco.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.repositorio.IMCatApendicesRepo;
import mx.com.oneproject.spco.result.AnsCatApendices;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/CatApendices")
public class RestCatApendController {

	@Autowired
	private IMCatApendicesRepo catApend;


	// Consulta de lista de catálogos
	@GetMapping
	public AnsCatApendices listar(HttpServletRequest peticion) {
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController token: " + token + "\n ");
		AnsCatApendices Respuesta = new AnsCatApendices();
		System.out.print(" + RestCatApendController listar \n");

		if (token != null) {
			String user = Jwts.parser().setSigningKey("0neProj3ct").parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			System.out.print("\n\n + RestCatApendController Usuario: " + user + "\n ");
		} else {
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");
			return Respuesta;
		}

		Respuesta.setCr("00");
		Respuesta.setDescripcion("Correcto");
		Respuesta.setContenido(catApend.findAll());
		return Respuesta;
	}

}