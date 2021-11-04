package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.repositorio.IMSysPerfilRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduPartListUni;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Perfiles")
public class RestSysPerfilesController {

	@Autowired
	private IMSysPerfilRepo perfil;
	
	/**
	 * Esta clase define el método de consulta de lista de perfiles
	 * @author: Roberto Avila
	 * @version: 3/11/2021/A
	 * @see 
	 */	

	@GetMapping
	public AnsSysAduPartListUni listar(HttpServletRequest peticion,
			@RequestParam(required = false, value = "recinto") String recinto,
            @RequestParam(required = false, value = "empresa") String empresa){
		BigDecimal recintoBD = BigDecimal.valueOf(Double.valueOf(recinto));
		BigDecimal empresaBD = BigDecimal.valueOf(Double.valueOf(empresa));
		AnsSysAduPartListUni respuesta = new AnsSysAduPartListUni();;
		respuesta.setCr("00");
		respuesta.setDescripcion("Correcto");
		respuesta.setContenido(perfil.findByER(empresaBD, recintoBD));
		return respuesta;
	}

}
