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

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.SysUserPag;
import mx.com.oneproject.spco.result.AnsSysUser;
import mx.com.oneproject.spco.result.AnsSysUserPagList;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/SysUser")
public class RestSysUsuarioController {

	@Autowired
	private IMSysUserRepo sysUser;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<SysUsuarios> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysUsuarioController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysUsuarioController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysUser.findAll();
	}



	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping(path = {"/SysUs"})
	public AnsSysUser consultaSysUsuario(HttpServletRequest peticion,
								@RequestParam(required = false, value = "sysUser") String Usuario){
		
									System.out.print("\n\n + RestSysUsuarioController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysUsuarioController listar: " + peticion.getHeader("Authorization")+ "\n ");	
    	Double dUsuario = Double.valueOf(Usuario);
    	BigDecimal bDUsuario = BigDecimal.valueOf(dUsuario);
									System.out.print("\n\n + RestSysUsuarioController Usuario: " + Usuario + "\n ");	

    	AnsSysUser respuesta = new AnsSysUser();
    	respuesta.setCr("00");
    	respuesta.setDescripcion("Operacion correcta");
    	respuesta.setContenido(sysUser.findByUsuario(bDUsuario));
    	if (respuesta.getContenido() == null)
    	{
        	respuesta.setCr("01");
        	respuesta.setDescripcion("Usuario no existente o no activo");
    	}
		return (respuesta); 
	}



	
	
	// Consulta de sys_usuarios con paginacion y validacion de token.
    @GetMapping(path = {"/SysUsPag"})
    public AnsSysUserPagList listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
//    		                    @RequestParam(required = false, value = "recinto") String recinto,
    		                    HttpServletRequest peticion) {
  // Validación de token    	
    	AnsSysUserPagList respuesta = new AnsSysUserPagList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysUsuarioController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysUsuarioController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// Preparación de la paginación.
		boolean enabled = true;
		SysUsuarios sysUsuarioCero = new SysUsuarios();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysUsuarios> todosSysUsuarios;
		List<SysUsuarios> paginaSysUsuarios; 
		Integer sysUsuarioInicial, sysUsuarioFinal;
		
		SysUserPag resultado = new SysUserPag();
                                                                      	System.out.print(" + RestSysUsuarioController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysUser.countByActivos();
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysUsuarioInicial = (perPage  * (page - 1) );
         sysUsuarioFinal   = (sysUsuarioInicial + perPage) - 1;
         todosSysUsuarios  = sysUser.findByClave();
         paginaSysUsuarios = sysUser.findByClave();
         paginaSysUsuarios.clear();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysUsuarioController Apendice: " + i + " - " + todosSysUsuarios.get(i).getIdEmpresa() + " - " + todosSysUsuarios.get(i).getIdRecinto() + " - " + todosSysUsuarios.get(i).getIdUsuario());
        	 if(i>=sysUsuarioInicial && i<=sysUsuarioFinal)
        	 {
        		 sysUsuarioCero = todosSysUsuarios.get(i);
        		 paginaSysUsuarios.add(sysUsuarioCero);
        		 System.out.print("  -- En lista  --" + sysUsuarioCero.getIdUsuario() );
        	 }
         }
         
         																System.out.print("\n + RestSysUsuarioController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysUser.countByActivos());
         resultado.setTotalPages(pagEntero);
         resultado.setSysUsuarios(paginaSysUsuarios);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }
	
	
	// Consulta de sys_usuarios con paginacion y validacion de token.
    @GetMapping(path = {"/RecintPag"})
    public AnsSysUserPagList listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    @RequestParam(required = false, value = "recinto") String recinto,
    		                    HttpServletRequest peticion) {
    	BigDecimal limInferior = BigDecimal.valueOf(0.0);
    	BigDecimal limSuperior = BigDecimal.valueOf(99999999.0);
    	Double DRecinto = 0.0;
    if (!recinto.isEmpty())
    		{ 
    	DRecinto = Double.valueOf(recinto);
    	limInferior = BigDecimal.valueOf(DRecinto);
    	limSuperior = BigDecimal.valueOf(DRecinto);
    		} else
    		{
    			limInferior = BigDecimal.valueOf(0.0);
    			limSuperior = BigDecimal.valueOf(99999999.0);
    		}
    	
    // Validación de token    	
    	AnsSysUserPagList respuesta = new AnsSysUserPagList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysUsuarioController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysUsuarioController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// Preparación de la paginación.
		boolean enabled = true;
		SysUsuarios sysUsuarioCero = new SysUsuarios();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysUsuarios> todosSysUsuarios;
		List<SysUsuarios> paginaSysUsuarios; 
		Integer sysUsuarioInicial, sysUsuarioFinal;
		
		SysUserPag resultado = new SysUserPag();
                                                                      	System.out.print(" + RestSysUsuarioController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysUser.countByRecinto(limInferior,limSuperior);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysUsuarioInicial = (perPage  * (page - 1) );
         sysUsuarioFinal   = (sysUsuarioInicial + perPage) - 1;
         todosSysUsuarios  = sysUser.findByRecinto(limInferior,limSuperior);
         paginaSysUsuarios = sysUser.findByRecinto(limInferior,limSuperior);
         paginaSysUsuarios.clear();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysUsuarioController Apendice: " + i + " - " + todosSysUsuarios.get(i).getIdEmpresa() + " - " + todosSysUsuarios.get(i).getIdRecinto() + " - " + todosSysUsuarios.get(i).getIdUsuario());
        	 if(i>=sysUsuarioInicial && i<=sysUsuarioFinal)
        	 {
        		 sysUsuarioCero = todosSysUsuarios.get(i);
        		 paginaSysUsuarios.add(sysUsuarioCero);
        		 System.out.print("  -- En lista  --" + sysUsuarioCero.getIdUsuario() );
        	 }
         }
         
         																System.out.print("\n + RestSysUsuarioController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysUser.countByRecinto(limInferior,limSuperior));
         resultado.setTotalPages(pagEntero);
         resultado.setSysUsuarios(paginaSysUsuarios);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }
	
}
