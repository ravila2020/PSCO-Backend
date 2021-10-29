package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.repositorio.IMSysEmpRepo;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Empresa")
public class RestSysEmpresaController {

	@Autowired
	private IMSysEmpRepo empresa;
	
	/**
	 * Esta clase define el método de consulta de empresas
	 * @author: Roberto Avila
	 * @version: 29/10/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<String> listar(HttpServletRequest peticion){
		return empresa.findByUnicos();
	}

	
}
