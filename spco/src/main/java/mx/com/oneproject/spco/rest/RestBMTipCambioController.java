package mx.com.oneproject.spco.rest;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/TipoCambio")
public class RestBMTipCambioController {


	
		@GetMapping
		public String listar(){
			final String uri2 = "https://www.banxico.org.mx/SieAPIRest/service/v1/series/SF43718/datos/oportuno";
		    String consulta = ""; 
		    String consulta2 = ""; 

		    RestTemplate restTemplateHost = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//			headers.add("Accept-Encoding", "gzip");
			headers.add("Bmx-Token", "12eda874d3287fd527e506669cfd27ddff7e2a4ca56aff70a1b74e1ada5d55f3");
		    
			HttpEntity<String> entity = new HttpEntity<String>("{ }", headers);
   
			ResponseEntity<String> result = restTemplateHost.exchange(uri2, HttpMethod.GET, entity, String.class);
			
			consulta2 = result.getBody()  ;
 //			Integer elemento = consulta2.indexOf("]");

//			consulta = "[ {" + consulta2.substring(315,elemento) + "] ";
 			
  			return consulta2;
		}
			
}