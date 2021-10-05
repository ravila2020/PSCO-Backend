package mx.com.oneproject.spco.rest;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.modelo.DetCatAp;
import mx.com.oneproject.spco.modelo.SysAduPart;
import mx.com.oneproject.spco.modelo.SysAduPartId;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMDetCatApRepo;
import mx.com.oneproject.spco.repositorio.IMSysAduPartRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduPart;
import mx.com.oneproject.spco.respuesta.AnsSysAduPartList;
import mx.com.oneproject.spco.respuesta.AnsSysAduPartListDual;
import mx.com.oneproject.spco.respuesta.AnsSysAduPartUm;
import mx.com.oneproject.spco.respuesta.SysAduPartPag;
import mx.com.oneproject.spco.result.clienteParte;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/AduPart")
public class RestSysAduPartController {

	@Autowired
	private IMSysUserRepo sysUser;
	
	@Autowired
	private IMSysAduPartRepo aduPart;

	@Autowired
	private IMDetCatApRepo RepoDetCatAp;
	
	/**
	 * Esta clase define el método de consulta plana de partes
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	

	@GetMapping
	public List<SysAduPart> listar(HttpServletRequest peticion){
		return aduPart.findAll();
	}

	
	/**
	 * Esta clase define el método de consulta paginada de partes
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/AduPartPag"})
    public AnsSysAduPartList listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	page =1;
    	perPage = 9999;
    	String user;
    	AnsSysAduPartList respuesta = new AnsSysAduPartList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
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
		SysAduPart SysAduPartCero = new SysAduPart();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysAduPart> todosSysAduPart;
		List<SysAduPart> paginaSysAduParts; 
		Integer SysAduPartInicial, SysAduPartFinal;
		
		SysAduPartPag resultado = new SysAduPartPag();
                                                                      	System.out.print(" + RestSysAduPartController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = aduPart.countByTodo();
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysAduPartInicial = (perPage  * (page - 1) );
         SysAduPartFinal   = (SysAduPartInicial + perPage) - 1;
         todosSysAduPart  = aduPart.BuscarByTodo();
         paginaSysAduParts = aduPart.BuscarByTodo();
         paginaSysAduParts.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysAduPartController Apendice: " + i + " - " + todosSysAduPart.get(i).getClveProduc() + " - " + todosSysAduPart.get(i).getTipProd() + " - " + todosSysAduPart.get(i).getDescCorIng());
        	 if(i>=SysAduPartInicial && i<=SysAduPartFinal)
        	 {
        		 SysAduPartCero = todosSysAduPart.get(i);
        		 paginaSysAduParts.add(SysAduPartCero);
//        		 System.out.print("  -- En lista  --" + SysAduPartCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysAduPartController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) aduPart.countByTodo());
         resultado.setTotalPages(pagEntero);
         resultado.setSysAduPartes(paginaSysAduParts);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

    
	/**
	 * Esta clase define el método de consulta paginada de partes (lista parte/cliente)
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/AduPartCliPag"})
    public AnsSysAduPartListDual listarPagCombo(HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	int page = 1;
    	int perPage = 9999;
    	AnsSysAduPartListDual respuesta = new AnsSysAduPartListDual();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
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
		SysAduPart SysAduPartCero = new SysAduPart();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysAduPart> todosSysAduPart;
		List<SysAduPart> paginaSysAduParts; 
		Integer SysAduPartInicial, SysAduPartFinal;
		ArrayList<clienteParte> inicialLista = new ArrayList();
		

//		inicialLista.add(resultado);
//		inicialLista.clear();
                                                                      	System.out.print(" + RestSysAduPartController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = aduPart.countByTodo();
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysAduPartInicial = (perPage  * (page - 1) );
         SysAduPartFinal   = (SysAduPartInicial + perPage) - 1;
         todosSysAduPart  = aduPart.BuscarByTodo();
         paginaSysAduParts = aduPart.BuscarByTodo();

         paginaSysAduParts.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysAduPartController Apendice: " + i + " - " + todosSysAduPart.get(i).getClveProduc() + " - " + todosSysAduPart.get(i).getTipProd() + " - " + todosSysAduPart.get(i).getDescCorIng());
        	 if(i>=SysAduPartInicial && i<=SysAduPartFinal)
        	 {
        			clienteParte resultado = new clienteParte();
        			resultado.setIdCliProv("valor");
        			resultado.setNumPart("inicial");
        		 SysAduPartCero = todosSysAduPart.get(i);
        		 paginaSysAduParts.add(SysAduPartCero);
        		 resultado.setIdCliProv(SysAduPartCero.getIdCliProv());
        		 resultado.setNumPart(SysAduPartCero.getNumPart());
        		 inicialLista.add(resultado);
        		 inicialLista.get(i).setIdCliProv(SysAduPartCero.getIdCliProv());
        		 inicialLista.get(i).setNumPart(SysAduPartCero.getNumPart());
        	 }
         }
         
         																System.out.print("\n + RestSysAduPartController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
		 respuesta.setParteCliente(inicialLista);
		 return respuesta;
    }
    
	/**
	 * Esta clase define el método de consulta paginada de partes por cliente recinto
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/AduPartPagCte"})
    public AnsSysAduPartList listarPagCteRecinto(@RequestParam(required = false, value = "page") int page,
            @RequestParam(required = false, value = "perpage") int perPage, 
            @RequestParam(required = false, value = "cliente") String cliente, 
            @RequestParam(required = false, value = "recinto") String recinto, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysAduPartList respuesta = new AnsSysAduPartList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recintoU = usuarioProc.getIdRecinto();
		BigDecimal empresa = usuarioProc.getIdEmpresa();
		String recintoS = recintoU.toString();
		String empresaS = empresa.toString();
		empresaS = String.format("%04d", empresa.intValue());
		recintoS = String.format("%04d", recintoU.intValue());
	// Preparación de la paginación.
		boolean enabled = true;
		SysAduPart SysAduPartCero = new SysAduPart();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysAduPart> todosSysAduPart;
		List<SysAduPart> paginaSysAduParts; 
		Integer SysAduPartInicial, SysAduPartFinal;
		
		SysAduPartPag resultado = new SysAduPartPag();
                                                                      	System.out.print(" + RestSysAduPartController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = aduPart.countByTodoCteRecinto(cliente,recinto);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         SysAduPartInicial = (perPage  * (page - 1) );
         SysAduPartFinal   = (SysAduPartInicial + perPage) - 1;
         todosSysAduPart  = aduPart.BuscarByCteRecinto(cliente,recinto);
         paginaSysAduParts = aduPart.BuscarByCteRecinto(cliente,recinto);
         paginaSysAduParts.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysAduPartController Apendice: " + i + " - " + todosSysAduPart.get(i).getClveProduc() + " - " + todosSysAduPart.get(i).getTipProd() + " - " + todosSysAduPart.get(i).getDescCorIng());
        	 if(i>=SysAduPartInicial && i<=SysAduPartFinal)
        	 {
        		 SysAduPartCero = todosSysAduPart.get(i);
        		 paginaSysAduParts.add(SysAduPartCero);
//        		 System.out.print("  -- En lista  --" + SysAduPartCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysAduPartController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) aduPart.countByTodoCteRecinto(cliente,recinto));
         resultado.setTotalPages(pagEntero);
         resultado.setSysAduPartes(paginaSysAduParts);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

 
    
	/**
	 * Esta clase define el método de consulta de SysAduPart
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/Consulta"})
	public AnsSysAduPart consultaSysAduPart(@RequestParam(required = false, value = "cliente") String cliente,
										@RequestParam(required = false, value = "parte") String parte,
										@RequestParam(required = false, value = "pedimento") String pedimento,
										HttpServletRequest peticion){
		
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
     	  // Validación de token    	
			AnsSysAduPart respuesta = new AnsSysAduPart();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (aduPart.findByLlave(cliente, parte, pedimento).isEmpty()){
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe cliente / parte / pedimento");
			        return respuesta;
				  } else {
				    	//-------------
						SysAduPart productoProc = aduPart.findByLlaveUnica(cliente, parte, pedimento);
						System.out.print(" + RestSysAduPartController consultar  Producto: " + productoProc.getIdCliProv() + "\n ");
						respuesta.setCr("00");
						respuesta.setDescripcion("Correcto");
						respuesta.setContenido(productoProc);
						return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	
 

	
	/**
	 * Esta clase define el método de consulta de SysAduPart
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/ConsultaUm"})
	public AnsSysAduPartUm consultaSysAduPartUm(@RequestParam(required = false, value = "cliente") String cliente,
										@RequestParam(required = false, value = "parte") String parte,
										@RequestParam(required = false, value = "pedimento") String pedimento,
										HttpServletRequest peticion){
		
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
     	  // Validación de token    	
			AnsSysAduPartUm respuesta = new AnsSysAduPartUm();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (aduPart.findByLlave(cliente, parte, pedimento).isEmpty()){
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe cliente / parte / pedimento");
			        return respuesta;
				  } else {
				    	//-------------
						SysAduPart productoProc = aduPart.findByLlaveUnica(cliente, parte, pedimento);
						DetCatAp apendice07 = new DetCatAp();
						DetCatAp apendice07T = new DetCatAp();
		        		 apendice07 = RepoDetCatAp.findByCampos("AP07", productoProc.getuMC(), "X");
		        		 if (apendice07 == null)
		        		   {
		        			 respuesta.setuMCdescripcion("Sin descripción"); 
		        			 }
		        		 else
		        		 {
		        			 respuesta.setuMCdescripcion(apendice07.getDesCorta());
		        		 }
		        		 apendice07T = RepoDetCatAp.findByCampos("AP07", productoProc.getuMT(), "X");
		        		 if (apendice07T == null)
		        		   {
		        			 respuesta.setuMCdescripcion("Sin descripción"); 
		        			 }
		        		 else
		        		 {
		        			 respuesta.setuMTdescripcion(apendice07T.getDesCorta());
		        		 }
		        		 apendice07T = RepoDetCatAp.findByCampos("AP04", productoProc.getPaisOrigen(), "X");

		        		 if (apendice07T == null)
		        		   {
		        			 respuesta.setPaisdescripcion("Sin descripción");
		        			 }
		        		 else
		        		 {
		        			 respuesta.setPaisdescripcion(apendice07T.getDesCorta());
		        		 }
						
						System.out.print(" + RestSysAduPartController consultar  Producto: " + productoProc.getIdCliProv() + "\n ");
						respuesta.setCr("00");
						respuesta.setDescripcion("Correcto");
						respuesta.setContenido(productoProc);
						return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}
    
	/**
	 * Esta clase define el método de alta de SysAduPart
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
	@PostMapping
	public AnsSysAduPart altaSysAduPart(HttpServletRequest peticion,
									@RequestBody SysAduPart NuevoAduPart){
		
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = NuevoAduPart.getRecinto();
			String empresa = NuevoAduPart.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			NuevoAduPart.setRecinto(recinto);
			NuevoAduPart.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduPart respuesta = new AnsSysAduPart();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (aduPart.findByLlave(NuevoAduPart.getIdCliProv(), NuevoAduPart.getNumPart(), NuevoAduPart.getNumPedimento()).isEmpty()){
		    	//-------------
					SysAduPart productoProc = aduPart.save(NuevoAduPart);
					System.out.print(" + RestSysAduPartController insertar Producto: " + productoProc.getIdCliProv() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAduPart);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("Ya existe cliente / parte / pedimento");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de modificación de SysAduPart
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
	@PutMapping
	public AnsSysAduPart modiSysAduPart(HttpServletRequest peticion,
									@RequestBody SysAduPart NuevoAduPart){
		
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = NuevoAduPart.getRecinto();
			String empresa = NuevoAduPart.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			NuevoAduPart.setRecinto(recinto);
			NuevoAduPart.setEmpresa(empresa);
			
			//-----------
			  // Validación de token    	
			AnsSysAduPart respuesta = new AnsSysAduPart();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (!aduPart.findByLlave(NuevoAduPart.getIdCliProv(), NuevoAduPart.getNumPart(), NuevoAduPart.getNumPedimento()).isEmpty()){
		    	//-------------
					SysAduPart productoProc = aduPart.save(NuevoAduPart);
					System.out.print(" + RestSysAduPartController insertar Producto: " + productoProc.getIdCliProv() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoAduPart);
					return respuesta;
				  } else {
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe cliente / parte / pedimento");
			        return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de borrado de SysAduPart
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
	@DeleteMapping(path = {"/BorraParte"})
	public AnsSysAduPartList EliminaSysAduPartUm(@RequestParam(required = false, value = "cliente") String cliente,
										@RequestParam(required = false, value = "parte") String parte,
										@RequestParam(required = false, value = "pedimento") String pedimento,
										HttpServletRequest peticion){
		
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduPartController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
     	  // Validación de token    	
			AnsSysAduPartList respuesta = new AnsSysAduPartList();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysAduPartController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysAduPartController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
	    		SysAduPartId llaveParte = new SysAduPartId();
	    		llaveParte.setIdCliProv(cliente);
	    		llaveParte.setNumPart(parte);
	    		llaveParte.setNumPedimento(pedimento);
//				if (aduPart.findByLlave(cliente, parte, pedimento).isEmpty()){
				if (aduPart.findById(llaveParte).isEmpty()){
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe cliente / parte / pedimento");
			        return respuesta;
				  } else {
				    	//-------------
						aduPart.deleteById(llaveParte);
						respuesta.setCr("00");
						respuesta.setDescripcion("Correcto");
		//				respuesta.setContenido(productoProc);
						return respuesta;
			   	  }
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

}
