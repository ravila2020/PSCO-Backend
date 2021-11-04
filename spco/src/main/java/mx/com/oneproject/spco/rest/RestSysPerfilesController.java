package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.repositorio.IMSysPerfilRepo;

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
	public List<String> listar(HttpServletRequest peticion,
			@RequestParam(required = false, value = "recinto") String recinto,
            @RequestParam(required = false, value = "empresa") String empresa){
		BigDecimal recintoBD = BigDecimal.valueOf(Double.valueOf(recinto));
		BigDecimal empresaBD = BigDecimal.valueOf(Double.valueOf(empresa));

		return perfil.findByER(empresaBD, recintoBD);
	}

}
