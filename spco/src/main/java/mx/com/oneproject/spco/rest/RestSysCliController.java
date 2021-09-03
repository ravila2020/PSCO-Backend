package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.modelo.SysCatCli;
import mx.com.oneproject.spco.repositorio.IMSysCatCliRepo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Cli")
public class RestSysCliController {

	@Autowired
	private IMSysCatCliRepo sysCli;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<SysCatCli> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysCli.findAll();
	}

	
}
