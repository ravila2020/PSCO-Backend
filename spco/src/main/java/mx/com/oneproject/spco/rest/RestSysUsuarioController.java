package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.AnsLogonIn;
import mx.com.oneproject.spco.respuesta.SysUserPag;
import mx.com.oneproject.spco.result.AnsSysUser;
import mx.com.oneproject.spco.result.AnsSysUserPagList;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
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



	// Consulta de un sys_usuarios con validacion de token.
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


	// Alta de un sys_usuario con validacion de token.
	@PostMapping
	public AnsSysUser altaSysUsuario(HttpServletRequest peticion,
									@RequestBody SysUsuarios NuevoUsuario){
		
									System.out.print("\n\n + RestSysUsuarioController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysUsuarioController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysUser respuesta = new AnsSysUser();
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
							
	    	try {
		    	//-------------existe el uauario?
				if (sysUser.findByExiste(NuevoUsuario.getIdUsuario())== null)
				{
		    	//-------------
					SysUsuarios usuarioProc = sysUser.save(NuevoUsuario);
					System.out.print(" + RestSysUsuarioController insertar id: " + usuarioProc.getIdUsuario() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(sysUser.findByExiste(usuarioProc.getIdUsuario()));
					return respuesta;
					}
			        else {
						respuesta.setCr("83");
						respuesta.setDescripcion("Ya existe el usuario");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	// Modificacion de un sys_usuario con validacion de token.
	@PutMapping
	public AnsSysUser modifSysUsuario(HttpServletRequest peticion,
									@RequestBody SysUsuarios NuevoUsuario){
		
									System.out.print("\n\n + RestSysUsuarioController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysUsuarioController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysUser respuesta = new AnsSysUser();
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
							
	    	try {
		    	//-------------existe el usuario?
				if (sysUser.findByExiste(NuevoUsuario.getIdUsuario())!= null)
				{
		    	//-------------
					SysUsuarios usuarioProc = sysUser.save(NuevoUsuario);
					System.out.print(" + RestSysUsuarioController insertar id: " + usuarioProc.getIdUsuario() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(sysUser.findByExiste(usuarioProc.getIdUsuario()));
					return respuesta;
					}
			        else {
						respuesta.setCr("78");
						respuesta.setDescripcion("No existe el usuario");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	// Modificacion de un sys_usuario con validacion de token.
	@PutMapping(path = {"/Pass"})
	public AnsSysUser modPassSysUser(HttpServletRequest peticion,
									@RequestBody AnsLogonIn entrada){
		
									System.out.print("\n\n + RestSysUsuarioController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysUsuarioController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			Double dUsuario = Double.valueOf(entrada.getUser());
			BigDecimal bDUsuario = BigDecimal.valueOf(dUsuario);
			  // Validación de token    	
			AnsSysUser respuesta = new AnsSysUser();
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
							
	    	try {
		    	//-------------existe el usuario?
	    		SysUsuarios usuarioProc = sysUser.findByExiste(bDUsuario);
				if (usuarioProc != null)
				{
		    	//-------------
					usuarioProc.setPassword(entrada.getPassword());
					sysUser.save(usuarioProc);
					System.out.print(" + RestSysUsuarioController insertar id: " + usuarioProc.getIdUsuario() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(sysUser.findByExiste(usuarioProc.getIdUsuario()));
					return respuesta;
					}
			        else {
						respuesta.setCr("78");
						respuesta.setDescripcion("No existe el usuario");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

		
	// borrado logico de un sys_usuario con validacion de token.
	@DeleteMapping(path = {"/{id}"})
	public AnsSysUser bajaSysUsuario(HttpServletRequest peticion,
			 						@PathVariable("id") String id){
		
									System.out.print("\n\n + RestSysUsuarioController Baja: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysUsuarioController Baja: " + peticion.getHeader("Authorization")+ "\n ");	
	    	Double DSysUsuario = 0.0;
	    	DSysUsuario = Double.valueOf(id);
	    	BigDecimal BDid = BigDecimal.valueOf(DSysUsuario);

			// Validación de token    	
			AnsSysUser respuesta = new AnsSysUser();
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
							
	    	try {
		    	//-------------existe el usuario?
	    		SysUsuarios UsuConsultado = sysUser.findByExiste(BDid);
				if (UsuConsultado != null)
				{
					if (!UsuConsultado.getEstatus().matches("B"))
					{
						UsuConsultado.setEstatus("B");
						SysUsuarios usuarioProc = sysUser.save(UsuConsultado);
						System.out.print(" + RestSysUsuarioController insertar id: " + usuarioProc.getIdUsuario() + "\n ");
						respuesta.setCr("00");
						respuesta.setDescripcion("Correcto");
						respuesta.setContenido(sysUser.findByExiste(BDid));
						return respuesta;
					} else
					   {
						respuesta.setCr("80");
						respuesta.setDescripcion("Usuario  no activo");
				        return respuesta;						
					   }
					}
			     else {
						respuesta.setCr("81");
						respuesta.setDescripcion("No existe el usuario");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
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
	
	
	// Consulta de sys_usuarios-sys_recinto con paginacion y validacion de token.
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
