package mx.com.oneproject.spco.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/SysUser")
public class RestSysUsuarioController {

	@Autowired
	private IMSysUserRepo sysUser;
	
	// Consulta de la lista de roles con validacion de token.
	@GetMapping
	public List<SysUsuarios> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysUsuarioController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysUsuarioController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysUser.findAll();
	}

	
	
}
