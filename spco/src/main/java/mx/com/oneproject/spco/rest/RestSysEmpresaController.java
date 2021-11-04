package mx.com.oneproject.spco.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.repositorio.IMSysEmpRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduPartListUni;

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
	public AnsSysAduPartListUni listar(HttpServletRequest peticion){
		AnsSysAduPartListUni respuesta = new AnsSysAduPartListUni();;
		respuesta.setCr("00");
		respuesta.setDescripcion("Correcto");
		respuesta.setContenido(empresa.findByUnicos());
		return respuesta;
	}

	
}
