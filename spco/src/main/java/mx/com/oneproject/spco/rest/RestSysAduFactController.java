package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.util.List;

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
import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.modelo.SysAduFactId;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMSysAduFactRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduFact;
import mx.com.oneproject.spco.respuesta.AnsSysAduFactList;
import mx.com.oneproject.spco.respuesta.SysAduFactPag;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/AduFact")
public class RestSysAduFactController {
	
	@Autowired
	private IMSysAduFactRepo aduFact;
	
	@Autowired
	private IMSysUserRepo sysUser;

	/**
	 * Esta clase define el método de consulta plana de facturas
	 * @author: Roberto Avila
	 * @version: 1/10/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<SysAduFact> listar(HttpServletRequest peticion){
		return aduFact.findAll();
	}

	
	/**
	 * Esta clase define el método de alta de SysAduFact
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@PostMapping
	public AnsSysAduFact altaSysAduFact(HttpServletRequest peticion,
									@RequestBody SysAduFact NuevoAduFact){
		
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = NuevoAduFact.getRecinto();
			String empresa = NuevoAduFact.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			NuevoAduFact.setRecinto(recinto);
			NuevoAduFact.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduFact respuesta = new AnsSysAduFact();
			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduFactController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
	    		llave.setIdCliProv(NuevoAduFact.getIdCliProv());
	    		llave.setNumPart(NuevoAduFact.getNumPart());
	    		llave.setNumFact(NuevoAduFact.getNumFact());
	    		llave.setiDImpoEexpo(NuevoAduFact.getiDImpoEexpo());
				if (aduFact.findById(llave).isEmpty()){
		    	//-------------
					SysAduFact facturaProc = aduFact.save(NuevoAduFact);
					System.out.print(" + RestSysAduFactController insertar Factura: " + facturaProc.getIdCliProv() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAduFact);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("Ya existe cliente / parte / factura / IndImpExp");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	
	/**
	 * Esta clase define el método de consulta paginada de partes del cliente
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/aduFactCliPag"})
    public AnsSysAduFactList listarPag(@RequestParam(required = false, value = "page") int page,
            @RequestParam(required = false, value = "perpage") int perPage, 
            @RequestParam(required = false, value = "cliente") String cliente, 
            @RequestParam(required = false, value = "parte") String parte, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysAduFactList respuesta = new AnsSysAduFactList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysAduFactController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + "\n ");
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
		empresaS = String.format("%04d", empresa.intValue());
		recintoS = String.format("%04d", recinto.intValue());
	// Preparación de la paginación.
		boolean enabled = true;
		SysAduFact SysAduFactCero = new SysAduFact();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysAduFact> todosSysAduFact;
		List<SysAduFact> paginaSysAduFacts; 
		Integer SysAduFactInicial, SysAduFactFinal;
		
		SysAduFactPag resultado = new SysAduFactPag();
                                                                      	System.out.print(" + RestSysAduFactController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = aduFact.countByTodoCP(cliente,parte);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysAduFactInicial = (perPage  * (page - 1) );
         SysAduFactFinal   = (SysAduFactInicial + perPage) - 1;
         todosSysAduFact  = aduFact.BuscarByTodoCP(cliente,parte);
         paginaSysAduFacts = aduFact.BuscarByTodoCP(cliente,parte);
         paginaSysAduFacts.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysAduFactController Apendice: " + i + " - " + todosSysAduFact.get(i).getClveProduc() + " - " + todosSysAduFact.get(i).getTipProd() + " - " + todosSysAduFact.get(i).getDescCorIng());
        	 if(i>=SysAduFactInicial && i<=SysAduFactFinal)
        	 {
        		 SysAduFactCero = todosSysAduFact.get(i);
        		 paginaSysAduFacts.add(SysAduFactCero);
//        		 System.out.print("  -- En lista  --" + SysAduFactCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysAduFactController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) aduFact.countByTodoCP(cliente,parte));
         resultado.setTotalPages(pagEntero);
         resultado.setSysAduFacturas(paginaSysAduFacts);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

	/**
	 * Esta clase define el método de consulta paginada de partes del cliente
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/aduFactCliPagBet"})
    public AnsSysAduFactList listarPagBet(@RequestParam(required = false, value = "page") int page,
            @RequestParam(required = false, value = "perpage") int perPage, 
            @RequestParam(required = false, value = "cliente") String cliente, 
            @RequestParam(required = false, value = "parte") String parte, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	String partei;
    	String partef;
    	if(parte.equals("0000000000")) 
    	{
    		partei = "0000000000";
    		partef = "9999999999";
    	} else
    	{
    		partei = parte;
    		partef = parte;    		
    	}
    	AnsSysAduFactList respuesta = new AnsSysAduFactList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysAduFactController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + "\n ");
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
		empresaS = String.format("%04d", empresa.intValue());
		recintoS = String.format("%04d", recinto.intValue());
	// Preparación de la paginación.
		boolean enabled = true;
		SysAduFact SysAduFactCero = new SysAduFact();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysAduFact> todosSysAduFact;
		List<SysAduFact> paginaSysAduFacts; 
		Integer SysAduFactInicial, SysAduFactFinal;
		
		SysAduFactPag resultado = new SysAduFactPag();
                                                                      	System.out.print(" + RestSysAduFactController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = aduFact.countByTodoCPB(cliente,partei,partef);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysAduFactInicial = (perPage  * (page - 1) );
         SysAduFactFinal   = (SysAduFactInicial + perPage) - 1;
         todosSysAduFact  = aduFact.BuscarByTodoCPB(cliente,partei,partef);
         paginaSysAduFacts = aduFact.BuscarByTodoCPB(cliente,partei,partef);
         paginaSysAduFacts.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysAduFactController Apendice: " + i + " - " + todosSysAduFact.get(i).getClveProduc() + " - " + todosSysAduFact.get(i).getTipProd() + " - " + todosSysAduFact.get(i).getDescCorIng());
        	 if(i>=SysAduFactInicial && i<=SysAduFactFinal)
        	 {
        		 SysAduFactCero = todosSysAduFact.get(i);
        		 paginaSysAduFacts.add(SysAduFactCero);
//        		 System.out.print("  -- En lista  --" + SysAduFactCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysAduFactController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) aduFact.countByTodoCPB(cliente,partei,partef));
         resultado.setTotalPages(pagEntero);
         resultado.setSysAduFacturas(paginaSysAduFacts);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

}
