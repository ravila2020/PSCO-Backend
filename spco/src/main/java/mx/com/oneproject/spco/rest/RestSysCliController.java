package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysCatCli;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMCodPosRepo;
import mx.com.oneproject.spco.repositorio.IMSysCatCliRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.SysCatCliPag;
import mx.com.oneproject.spco.result.AnsSysCatCli;
import mx.com.oneproject.spco.result.AnsSysCatCliList;
import mx.com.oneproject.spco.result.AnsSysCatCliTipo;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/Cli")
public class RestSysCliController {

	@Autowired
	private IMSysCatCliRepo sysCli;

	@Autowired
	private IMSysUserRepo sysUser;
	
	@Autowired
	private IMCodPosRepo codigoPostal;
	

	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<SysCatCli> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysCli.findAll();
	}

	// Alta de un Producto con validacion de token.
	@PostMapping
	public AnsSysCatCli altaSysCatCli(HttpServletRequest peticion,
									@RequestBody SysCatCli NuevoCliente){
		
									System.out.print("\n\n + RestSysCliController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCliController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysCatCli respuesta = new AnsSysCatCli();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCliController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCliController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (sysCli.findById(NuevoCliente.getIdCliProv()).isEmpty())
				{
		    	//-------------
					SysCatCli clienteProc = sysCli.save(NuevoCliente);
					System.out.print(" + RestSysCliController insertar Cliente: " + clienteProc.getNomDenov() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoCliente);
					return respuesta;
					}
			        else {
						respuesta.setCr("83");
						respuesta.setDescripcion("Ya existe cliente");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping(path = {"/TiposCli"})
	public AnsSysCatCliTipo Tipos(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCliController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsSysCatCliTipo respuesta = new AnsSysCatCliTipo();

		  // Validación de token    	
		String token = peticion.getHeader("Authorization");
                                                              		System.out.print("\n\n + RestSysCliController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                          			System.out.print("\n\n + RestSysCliController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}

		
		respuesta.setCr("00");
		respuesta.setDescripcion("Consulta correcta");
		respuesta.setContenido(sysCli.findByDistTipos());
		return (respuesta);
	}


	/**
	 * Esta clase define el método de consulta de clientes por tipo con paginación
	 * @author: Roberto Avila
	 * @version: 01/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/TipoCli"})
    public AnsSysCatCliList obtenerClientePorTipo(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    @RequestParam(required = false, value = "tipo") String tipo,
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysCatCliList respuesta = new AnsSysCatCliList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCliController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCliController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recinto = usuarioProc.getIdRecinto();
		BigDecimal empresa = usuarioProc.getIdEmpresa();
		String recintoS = recinto.toString();
		String empresaS = empresa.toString();
	// Preparación de la paginación.
		boolean enabled = true;
		SysCatCli sysCatCliCero = new SysCatCli();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatCli> todosSysCatCli;
		List<SysCatCli> paginaSysCatClientes; 
		Integer sysCatCliInicial, sysCatCliFinal;
		
		SysCatCliPag resultado = new SysCatCliPag();
                                                                      	System.out.print(" + RestSysCliController listarPag page: " + page + " perpage: " + perPage + " tipo: " + tipo  +"\n ");
    // obtener el total de sys_usuarios
         todos = sysCli.countByTipos(tipo); //, empresa, recinto);   
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysCatCliInicial = (perPage  * (page - 1) );
         sysCatCliFinal   = (sysCatCliInicial + perPage) - 1;
         todosSysCatCli  = sysCli.findByTipos(tipo); //,empresa,recinto);
         paginaSysCatClientes = sysCli.findByTipos(tipo);   //,empresa,recinto);
         paginaSysCatClientes.clear();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysCliController Apendice: " + i + " - " + todosSysCatCli.get(i).getEmpresa());
        	 if(i>=sysCatCliInicial && i<=sysCatCliFinal)
        	 {
        		 sysCatCliCero = todosSysCatCli.get(i);
        		 paginaSysCatClientes.add(sysCatCliCero);
        		 System.out.print("  -- En lista  --" + sysCatCliCero.getIdCliProv());
        	 }
         }
         
         																System.out.print("\n + RestSysCliController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysCli.countByTipos(tipo));   // , empresa, recinto));	
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatClientes(paginaSysCatClientes);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }


	/**
	 * Esta clase define el método de consulta de un cliente especifico
	 * @author: Roberto Avila
	 * @version: 01/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/Cliente"})
    public AnsSysCatCli  obtenerClientePorClave(@RequestParam(required = false, value = "Id") String cliente,
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysCatCli respuesta = new AnsSysCatCli();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCliController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCliController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recinto = usuarioProc.getIdRecinto();
		BigDecimal empresa = usuarioProc.getIdEmpresa();
		String recintoS = recinto.toString();
		String empresaS = empresa.toString();
	// Preparación de la paginación.
//		boolean enabled = true;
//		SysCatCli sysCatCliCero = new SysCatCli();
//		Long todos = (long) 0;
//		double paginas = (float) 0.0;
//		Integer pagEntero = 0;
//		List<SysCatCli> todosSysCatCli;
//		List<SysCatCli> paginaSysCatClientes; 
//		Integer sysCatCliInicial, sysCatCliFinal;
//		
//		SysCatCliPag resultado = new SysCatCliPag();
    //                                                                     	System.out.print(" + RestSysCliController listarPag page: " + page + " perpage: " + perPage + " tipo: " + tipo  +"\n ");
    // obtener el total de sys_usuarios
		 
		 Optional<SysCatCli> todos = sysCli.findById(cliente);
//         paginas = (double) todos / perPage;
//         pagEntero = (int) paginas;
//         if ((paginas-pagEntero)>0)
//         {
//        	 pagEntero++;
//         }
//    // Obtener la lista de sys_usuarios solicitada 
//         sysCatCliInicial = (perPage  * (page - 1) );
//         sysCatCliFinal   = (sysCatCliInicial + perPage) - 1;
//         todosSysCatCli  = sysCli.findByTipos(tipo); //,empresa,recinto);
//         paginaSysCatClientes = sysCli.findByTipos(tipo);   //,empresa,recinto);
//         paginaSysCatClientes.clear();
//         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysCliController Apendice: " + i + " - " + todosSysCatCli.get(i).getEmpresa());
//        	 if(i>=sysCatCliInicial && i<=sysCatCliFinal)
//        	 {
//        		 sysCatCliCero = todosSysCatCli.get(i);
//        		 paginaSysCatClientes.add(sysCatCliCero);
//        		 System.out.print("  -- En lista  --" + sysCatCliCero.getIdCliProv());
//        	 }
//         }
//         
//         																System.out.print("\n + RestSysCliController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
//         //
//         resultado.setPage(page);
//         resultado.setPerPage(perPage);
//         resultado.setTotal((int) sysCli.countByTipos(tipo));   // , empresa, recinto));	
//         resultado.setTotalPages(pagEntero);
//         resultado.setSysCatClientes(paginaSysCatClientes);
		 if (todos.isPresent()) {
			 SysCatCli actual = todos.get();
			 String estadoDesc = codigoPostal.findByClaveEstado(actual.getEstado());
			 String estadoCd   = codigoPostal.findByClaveCd(actual.getLocalidad(),actual.getEstado());
			 String estadoMpio = codigoPostal.findByClaveMpio(actual.getMunicipio(),actual.getLocalidad(),actual.getEstado());
			 															System.out.print("\n + RestSysCliController listarPag estado: " + estadoDesc + " - " + estadoCd + " - "  + estadoMpio  +"\n ");
			 actual.setEstado(estadoDesc);
			 actual.setMunicipio(estadoMpio);
			 actual.setLocalidad(estadoCd);
			 respuesta.setContenido(actual);
			 respuesta.setCr("00");
			 respuesta.setDescripcion("Correcto");
		 } else
		 {
			 respuesta.setCr("98");
			 respuesta.setDescripcion("No existe el cliente");			 
		 }
         return respuesta;
    }



	
}
