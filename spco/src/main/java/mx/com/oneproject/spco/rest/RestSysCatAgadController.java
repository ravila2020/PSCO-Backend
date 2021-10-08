package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.SysCatAgad;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMSysCatAgadRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.AnsSysCatAgad;
import mx.com.oneproject.spco.respuesta.AnsSysCatAgadLU;
import mx.com.oneproject.spco.respuesta.AnsSysCatAgagList;
import mx.com.oneproject.spco.respuesta.SysCatAgadPag;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/CatAgad")
public class RestSysCatAgadController {

	@Autowired
	private IMSysCatAgadRepo catAgad;

	@Autowired
	private IMSysUserRepo sysUser;

	
	/**
	 * Esta clase define el método de consulta plana del catalogo de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 7/10/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<SysCatAgad> listar(HttpServletRequest peticion){
		return catAgad.findAll();
	}
	
	/**
	 * Esta clase define el método de alta de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 07/10/2021/A
	 * @see 
	 */	
	@PostMapping
	public AnsSysCatAgad altaSysCatAgad(HttpServletRequest peticion,
									@RequestBody SysCatAgad NuevoAgad){
		
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
 	  // Validación de token    	
			AnsSysCatAgad respuesta = new AnsSysCatAgad();
			String user = new String();
			String llave = new String();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
			if (token != null) {
				  user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
			SysUsuarios agadProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
			BigDecimal recinto = agadProc.getIdRecinto();
			BigDecimal empresa = agadProc.getIdEmpresa();
			String recintoS = recinto.toString();
			String empresaS = empresa.toString();
			empresaS = String.format("%04d", empresa.intValue());
			recintoS = String.format("%04d", recinto.intValue());
							
	    	try {
		    	//-------------existe el producto?
	    		llave=NuevoAgad.getNumPat();
				if (catAgad.findById(llave).isEmpty()){
		    	//-------------
					SysCatAgad AgadProc = catAgad.save(NuevoAgad);
					System.out.print(" + RestSysCatAgadController insertar SysCatAgad: " + AgadProc.getNomAgAdu() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAgad);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("Ya existe agente");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de consulta de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 07/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/ConsAgente"})
	public AnsSysCatAgad consSysCatAgad(@RequestParam(required = false, value = "numPat") String numPat,
			                        HttpServletRequest peticion){
		
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			  // Validación de token    	
			AnsSysCatAgad respuesta = new AnsSysCatAgad();
			String user = new String();
			String llave = new String();
    		llave=numPat;
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
			if (token != null) {
			      user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
			SysUsuarios agadProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
			BigDecimal recinto = agadProc.getIdRecinto();
			BigDecimal empresa = agadProc.getIdEmpresa();
			String recintoS = recinto.toString();
			String empresaS = empresa.toString();
			empresaS = String.format("%04d", empresa.intValue());
			recintoS = String.format("%04d", recinto.intValue());
							
	    	try {
		    	//-------------existe el producto?
				if (catAgad.findById(llave).isEmpty()){
		    	//-------------
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe agente");
			        return respuesta;
			     } else {
					Optional<SysCatAgad> AgadProc = catAgad.findById(llave);
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(AgadProc.get());
					return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de consulta paginada de agentes
	 * @author: Roberto Avila
	 * @version: 0721/10/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/agAduPag"})
    public AnsSysCatAgagList listarPag(@RequestParam(required = false, value = "page") int page,
                                       @RequestParam(required = false, value = "perpage") int perPage, 
    		                            HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysCatAgagList respuesta = new AnsSysCatAgagList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios agadProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recinto = agadProc.getIdRecinto();
		BigDecimal empresa = agadProc.getIdEmpresa();
		String recintoS = recinto.toString();
		String empresaS = empresa.toString();
		empresaS = String.format("%04d", empresa.intValue());
		recintoS = String.format("%04d", recinto.intValue());
	// Preparación de la paginación.
		boolean enabled = true;
		SysCatAgad SysCatAgadCero = new SysCatAgad();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatAgad> todosSysCatAgad;
		List<SysCatAgad> paginaSysCatAgads; 
		Integer SysCatAgadInicial, SysCatAgadFinal;
		
		SysCatAgadPag resultado = new SysCatAgadPag();
                                                                      	System.out.print(" + RestSysCatAgadController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = catAgad.countByER(empresaS,recintoS);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysCatAgadInicial = (perPage  * (page - 1) );
         SysCatAgadFinal   = (SysCatAgadInicial + perPage) - 1;
         todosSysCatAgad  = catAgad.findByER(empresaS,recintoS);
         paginaSysCatAgads = catAgad.findByER(empresaS,recintoS);
         paginaSysCatAgads.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysCatAgadController Apendice: " + i + " - " + todosSysCatAgad.get(i).getClveProduc() + " - " + todosSysCatAgad.get(i).getTipProd() + " - " + todosSysCatAgad.get(i).getDescCorIng());
        	 if(i>=SysCatAgadInicial && i<=SysCatAgadFinal)
        	 {
        		 SysCatAgadCero = todosSysCatAgad.get(i);
        		 paginaSysCatAgads.add(SysCatAgadCero);
//        		 System.out.print("  -- En lista  --" + SysCatAgadCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatAgadController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) catAgad.countByER(empresaS,recintoS));
         resultado.setTotalPages(pagEntero);
         resultado.setSysAgads(paginaSysCatAgads);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }
	
	/**
	 * Esta clase define el método de consulta paginada de agentes
	 * @author: Roberto Avila
	 * @version: 0721/10/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/Lista"})
    public AnsSysCatAgadLU listarAgad( HttpServletRequest peticion) {
    // Validación de token
    	int page=1;
    	int perPage=9999;
    	String user;
    	AnsSysCatAgadLU respuesta = new AnsSysCatAgadLU();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios agadProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recinto = agadProc.getIdRecinto();
		BigDecimal empresa = agadProc.getIdEmpresa();
		String recintoS = recinto.toString();
		String empresaS = empresa.toString();
		empresaS = String.format("%04d", empresa.intValue());
		recintoS = String.format("%04d", recinto.intValue());
	// Preparación de la paginación.
		boolean enabled = true;
		SysCatAgad SysCatAgadCero = new SysCatAgad();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatAgad> todosSysCatAgad;
		List<SysCatAgad> paginaSysCatAgads; 
		Integer SysCatAgadInicial, SysCatAgadFinal;
		ArrayList<String> inicialListaERC = new ArrayList();
		
		SysCatAgadPag resultado = new SysCatAgadPag();
                                                                      	System.out.print(" + RestSysCatAgadController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = catAgad.countByER(empresaS,recintoS);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysCatAgadInicial = (perPage  * (page - 1) );
         SysCatAgadFinal   = (SysCatAgadInicial + perPage) - 1;
         todosSysCatAgad  = catAgad.findByER(empresaS,recintoS);
         paginaSysCatAgads = catAgad.findByER(empresaS,recintoS);
         paginaSysCatAgads.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysCatAgadController Apendice: " + i + " - " + todosSysCatAgad.get(i).getClveProduc() + " - " + todosSysCatAgad.get(i).getTipProd() + " - " + todosSysCatAgad.get(i).getDescCorIng());
        	 if(i>=SysCatAgadInicial && i<=SysCatAgadFinal)
        	 {
        		 SysCatAgadCero = todosSysCatAgad.get(i);
        		 paginaSysCatAgads.add(SysCatAgadCero);
        		 inicialListaERC.add(SysCatAgadCero.getNumPat());
//        		 System.out.print("  -- En lista  --" + SysCatAgadCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatAgadController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) catAgad.countByER(empresaS,recintoS));
         resultado.setTotalPages(pagEntero);
         resultado.setSysAgads(paginaSysCatAgads);
	 	 respuesta.setContenido(inicialListaERC);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

	/**
	 * Esta clase define el método de consulta de agentes aduanales
	 * @author: Roberto Avila
	 * @version: 07/10/2021/A
	 * @see 
	 */	
	@DeleteMapping(path = {"/BorraAgente"})
	public AnsSysCatAgad borraAgente(@RequestParam(required = false, value = "numPat") String numPat,
			                        HttpServletRequest peticion){
		
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatAgadController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			  // Validación de token    	
			AnsSysCatAgad respuesta = new AnsSysCatAgad();
			String user = new String();
			String llave = new String();
    		llave=numPat;
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatAgadController token: " + token + "\n ");
			if (token != null) {
			      user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatAgadController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
			SysUsuarios agadProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
			BigDecimal recinto = agadProc.getIdRecinto();
			BigDecimal empresa = agadProc.getIdEmpresa();
			String recintoS = recinto.toString();
			String empresaS = empresa.toString();
			empresaS = String.format("%04d", empresa.intValue());
			recintoS = String.format("%04d", recinto.intValue());
							
	    	try {
		    	//-------------existe el producto?
				if (catAgad.findById(llave).isEmpty()){
		    	//-------------
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe agente");
			        return respuesta;
			     } else {
					catAgad.deleteById(numPat);
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	
}
