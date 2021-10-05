package mx.com.oneproject.spco.rest;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.modelo.HistTipoCambio;
import mx.com.oneproject.spco.repositorio.IMHistTipoCambioRepo;
import mx.com.oneproject.spco.result.AnsSysCatProductoTipo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/TipoCambio")
public class RestBMTipCambioController {


	@Autowired
	private IMHistTipoCambioRepo tipoCambio;

	/**
	 * Clase que define el método de consulta plana de tipos de cambio
	 * @author: Roberto Avila
	 * @version: 3/10/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<HistTipoCambio> listar(HttpServletRequest peticion){
		return tipoCambio.findAll();
	}

	/**
	 * Esta clase define el método de consulta de tipo de cambio por fecha/moneda
	 * @author: Roberto Avila
	 * @version: 3/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/TipCam"})
	public AnsSysCatProductoTipo Tipos(@RequestParam(required = false, value = "fecha") String fecha,
			                           @RequestParam(required = false, value = "moneda") String moneda,
										HttpServletRequest peticion){
		
		System.out.print("\n\n + RestBMTipCambioController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestBMTipCambioController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsSysCatProductoTipo respuesta = new AnsSysCatProductoTipo();

		  // Validación de token    	
		String token = peticion.getHeader("Authorization");
                                                              		System.out.print("\n\n + RestBMTipCambioController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                          			System.out.print("\n\n + RestBMTipCambioController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}

		
		respuesta.setCr("00");
		respuesta.setDescripcion("Consulta correcta");
//		respuesta.setContenido(sysProd.findByTipo());
		return (respuesta);
	}
	
	/**
	 * Esta clase define el método de consulta de tipo de cambio en linea con BMX
	 * @author: Roberto Avila
	 * @version: 3/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/TCLinea"})
	public String listar(){
		final String uri2 = "https://www.banxico.org.mx/SieAPIRest/service/v1/series/SF43718/datos/oportuno";
	    String consulta2 = ""; 

	    RestTemplate restTemplateHost = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Bmx-Token", "12eda874d3287fd527e506669cfd27ddff7e2a4ca56aff70a1b74e1ada5d55f3");
	    
		HttpEntity<String> entity = new HttpEntity<String>("{ }", headers);
		ResponseEntity<String> result = restTemplateHost.exchange(uri2, HttpMethod.GET, entity, String.class);
		consulta2 = result.getBody()  ;
		return consulta2;
		}

		
		
}