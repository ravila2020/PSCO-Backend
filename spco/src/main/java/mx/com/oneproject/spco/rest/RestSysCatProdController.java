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
import mx.com.oneproject.spco.modelo.SysCatProd;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMDetCatApRepo;
import mx.com.oneproject.spco.repositorio.IMSysCatProdRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.SysCatProdPag;
import mx.com.oneproject.spco.respuesta.SysCatProdUm;
import mx.com.oneproject.spco.result.AnsSysCatProd;
import mx.com.oneproject.spco.result.AnsSysCatProdList;
import mx.com.oneproject.spco.result.AnsSysCatProdListUm;
import mx.com.oneproject.spco.result.AnsSysCatProdTipo;
import mx.com.oneproject.spco.result.AnsSysCatProdUm;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/CatProd")
public class RestSysCatProdController {

	
	@Autowired
	private IMSysCatProdRepo sysProd;
	
	@Autowired
	private IMSysUserRepo sysUser;
	
	@Autowired
	private IMDetCatApRepo RepoDetCatAp;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<SysCatProd> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCatProdRepo listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCatProdRepo listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysProd.findAll();
	}

	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping(path = {"/TiposProd"})
	public AnsSysCatProdTipo Tipos(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCatProdRepo listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCatProdRepo listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsSysCatProdTipo respuesta = new AnsSysCatProdTipo();

		  // Validación de token    	
		String token = peticion.getHeader("Authorization");
                                                              		System.out.print("\n\n + RestSysCatProdRepo token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                          			System.out.print("\n\n + RestSysCatProdRepo Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}

		
		respuesta.setCr("00");
		respuesta.setDescripcion("Consulta correcta");
		respuesta.setContenido(sysProd.findByTipo());
		return (respuesta);
	}


	
	// Consulta de Productos  con paginacion y validacion de token.
    @GetMapping(path = {"/ProdPag"})
    public AnsSysCatProdList listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
//    		                    @RequestParam(required = false, value = "recinto") String recinto,
    		                    @RequestParam(required = false, value = "tipo") String tipo,
    		                    HttpServletRequest peticion) {
		/*
		 * BigDecimal limInferior = BigDecimal.valueOf(0.0); BigDecimal limSuperior =
		 * BigDecimal.valueOf(99999999.0); Double DRecinto = 0.0; if
		 * (!recinto.isEmpty()) { DRecinto = Double.valueOf(recinto); limInferior =
		 * BigDecimal.valueOf(DRecinto); limSuperior = BigDecimal.valueOf(DRecinto); }
		 * else { limInferior = BigDecimal.valueOf(0.0); limSuperior =
		 * BigDecimal.valueOf(99999999.0); }
		 */    	
    // Validación de token
    	String user;
    	AnsSysCatProdList respuesta = new AnsSysCatProdList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
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
		SysCatProd sysCatProdCero = new SysCatProd();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProd> todosSysCatProd;
		List<SysCatProd> paginaSysCatProds; 
		Integer sysCatProdInicial, sysCatProdFinal;
		
		SysCatProdPag resultado = new SysCatProdPag();
                                                                      	System.out.print(" + RestSysCatProdController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByTipo(tipo,empresa,recinto);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysCatProdInicial = (perPage  * (page - 1) );
         sysCatProdFinal   = (sysCatProdInicial + perPage) - 1;
         todosSysCatProd  = sysProd.findByTipo(tipo,empresa,recinto);
         paginaSysCatProds = sysProd.findByTipo(tipo,empresa,recinto);
         paginaSysCatProds.clear();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysCatProdController Apendice: " + i + " - " + todosSysCatProd.get(i).getClveProduc() + " - " + todosSysCatProd.get(i).getTipProd() + " - " + todosSysCatProd.get(i).getDescCorIng());
        	 if(i>=sysCatProdInicial && i<=sysCatProdFinal)
        	 {
        		 sysCatProdCero = todosSysCatProd.get(i);
        		 paginaSysCatProds.add(sysCatProdCero);
        		 System.out.print("  -- En lista  --" + sysCatProdCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProdController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByTipo(tipo,empresa,recinto));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProds);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

	// Alta de un Producto con validacion de token.
	@PostMapping
	public AnsSysCatProd altaSysCatProd(HttpServletRequest peticion,
									@RequestBody SysCatProd NuevoProducto){
		
									System.out.print("\n\n + RestSysCatProdController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatProdController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysCatProd respuesta = new AnsSysCatProd();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (sysProd.findByProducto(NuevoProducto.getClveProduc(), NuevoProducto.getEmpresa(), NuevoProducto.getRecinto()) == null)
				{
		    	//-------------
					SysCatProd productoProc = sysProd.save(NuevoProducto);
					System.out.print(" + RestSysCatProdController insertar Producto: " + productoProc.getClveProduc() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoProducto);
					return respuesta;
					}
			        else {
						respuesta.setCr("83");
						respuesta.setDescripcion("Ya existe empresa / recinto / producto");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	// Modificación de un Producto con validacion de token.
	@PutMapping
	public AnsSysCatProd ModSysCatProd(HttpServletRequest peticion,
									@RequestBody SysCatProd NuevoProducto){
		
									System.out.print("\n\n + RestSysCatProdController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatProdController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysCatProd respuesta = new AnsSysCatProd();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
			}	else	{
				respuesta.setCr("99");
				respuesta.setDescripcion("Petición sin token");		
				return respuesta;
				}
							
	    	try {
		    	//-------------existe el producto?
				if (sysProd.findByProducto(NuevoProducto.getClveProduc(), NuevoProducto.getEmpresa(), NuevoProducto.getRecinto()) != null)
				{
		    	//-------------
					SysCatProd productoProc = sysProd.save(NuevoProducto);
					System.out.print(" + RestSysCatProdController insertar Producto: " + productoProc.getClveProduc() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					respuesta.setContenido(NuevoProducto);
					return respuesta;
					}
			        else {
						respuesta.setCr("76");
						respuesta.setDescripcion("No existe empresa / recinto / producto");
				        return respuesta;
			    	}
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	// Consulta de sys_usuarios-sys_recinto con paginacion y validacion de token.
    @GetMapping(path = {"/ProdPagUm"})
    public AnsSysCatProdListUm listarPagUm(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    @RequestParam(required = false, value = "tipo") String tipo,
    		                    HttpServletRequest peticion) {
		/*
		 * BigDecimal limInferior = BigDecimal.valueOf(0.0); BigDecimal limSuperior =
		 * BigDecimal.valueOf(99999999.0); Double DRecinto = 0.0; if
		 * (!recinto.isEmpty()) { DRecinto = Double.valueOf(recinto); limInferior =
		 * BigDecimal.valueOf(DRecinto); limSuperior = BigDecimal.valueOf(DRecinto); }
		 * else { limInferior = BigDecimal.valueOf(0.0); limSuperior =
		 * BigDecimal.valueOf(99999999.0); }
		 */    	
    // Validación de token
    	String user;
    	AnsSysCatProdListUm respuesta = new AnsSysCatProdListUm();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
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
		SysCatProd sysCatProdCero = new SysCatProd();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProd> todosSysCatProd;
		List<SysCatProd> paginaSysCatProds; 
		Integer sysCatProdInicial, sysCatProdFinal;
		
		SysCatProdPag resultado = new SysCatProdPag();
		SysCatProdUm listaProd = new SysCatProdUm();
                                                                      	System.out.print(" + RestSysCatProdController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByTipo(tipo,empresa,recinto);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         AnsSysCatProdListUm Acumulado = new AnsSysCatProdListUm();
         sysCatProdInicial = (perPage  * (page - 1) );
         sysCatProdFinal   = (sysCatProdInicial + perPage) - 1;
         todosSysCatProd  = sysProd.findByTipo(tipo,empresa,recinto);
         paginaSysCatProds = sysProd.findByTipo(tipo,empresa,recinto);
         paginaSysCatProds.clear();
         
         Acumulado.objetoItem = sysProd.findByTipo(tipo,empresa,recinto);
         Acumulado.objetoItem.clear();
         
         Acumulado.uMDescripcion =  new ArrayList<String>();
         Acumulado.uMDescripcion.clear();
         
         String uMDescrip = new String();
         DetCatAp apendice07 = new DetCatAp();
  //       Acumulado.uMDescripcion = new SysCatProdUm();
  //       Acumulado.uMDescripcion = new 
         SysCatProdUm elementoItem = new SysCatProdUm();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysCatProdController Apendice: " + i + " - " + todosSysCatProd.get(i).getClveProduc() + " - " + todosSysCatProd.get(i).getTipProd() + " - " + todosSysCatProd.get(i).getDescCorIng());
        	 if(i>=sysCatProdInicial && i<=sysCatProdFinal)
        	 {
        		 sysCatProdCero = todosSysCatProd.get(i);
        		 														System.out.print("\n " + "          + RestSysCatProdController UM: " +  sysCatProdCero.getuM() + "\n ");
        		 apendice07 = RepoDetCatAp.findByCampos("AP07", sysCatProdCero.getuM(), "X");
        		 if (apendice07 == null)
        		   {
        			 uMDescrip = "Sin descripción"; 
        			 }
        		 else
        		 {
        			 uMDescrip = apendice07.getDesCorta();
        		 }
        		 paginaSysCatProds.add(sysCatProdCero);
        		 elementoItem.setObjetoItem(sysCatProdCero);
        		 elementoItem.setuMDescripcion(uMDescrip);
        		 Acumulado.objetoItem.add(sysCatProdCero);
        		 Acumulado.uMDescripcion.add(uMDescrip);
      //  		 Acumulado.ListaProductosUm.add(i,elementoItem);
      //  		 Acumulado.ListaProductosUm.add(elementoItem);
        		 System.out.print("  -- En lista  --" + sysCatProdCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProdController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByTipo(tipo,empresa,recinto));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProds);
	// 	 respuesta.setContenido(resultado);
	//x 	 respuesta.ListaProductosUm.add(resultado);
         
	 	 respuesta.setPage(page);
	 	 respuesta.setPerPage(perPage);
	     respuesta.setTotal(resultado.getTotal());
	     respuesta.setTotalPages(pagEntero);
	     respuesta.setObjetoItem(Acumulado.getObjetoItem());
	     respuesta.setuMDescripcion(Acumulado.getuMDescripcion());
	 	 
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

 
	// Consulta de Productos  con paginacion y validacion de token.
    @GetMapping(path = {"/ProdUnico"})
    public AnsSysCatProdList obtenerProducto(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
//    		                    @RequestParam(required = false, value = "recinto") String recinto,
    		                    @RequestParam(required = false, value = "clave") String clave,
    		                    HttpServletRequest peticion) {
		/*
		 * BigDecimal limInferior = BigDecimal.valueOf(0.0); BigDecimal limSuperior =
		 * BigDecimal.valueOf(99999999.0); Double DRecinto = 0.0; if
		 * (!recinto.isEmpty()) { DRecinto = Double.valueOf(recinto); limInferior =
		 * BigDecimal.valueOf(DRecinto); limSuperior = BigDecimal.valueOf(DRecinto); }
		 * else { limInferior = BigDecimal.valueOf(0.0); limSuperior =
		 * BigDecimal.valueOf(99999999.0); }
		 */    	
    // Validación de token
    	String user;
    	AnsSysCatProdList respuesta = new AnsSysCatProdList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
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
		SysCatProd sysCatProdCero = new SysCatProd();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProd> todosSysCatProd;
		List<SysCatProd> paginaSysCatProds; 
		Integer sysCatProdInicial, sysCatProdFinal;
		
		SysCatProdPag resultado = new SysCatProdPag();
                                                                      	System.out.print(" + RestSysCatProdController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByClave(clave,empresa,recinto);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysCatProdInicial = (perPage  * (page - 1) );
         sysCatProdFinal   = (sysCatProdInicial + perPage) - 1;
         todosSysCatProd  = sysProd.findByClave(clave,empresa,recinto);
         paginaSysCatProds = sysProd.findByClave(clave,empresa,recinto);
         paginaSysCatProds.clear();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysCatProdController Apendice: " + i + " - " + todosSysCatProd.get(i).getClveProduc() + " - " + todosSysCatProd.get(i).getTipProd() + " - " + todosSysCatProd.get(i).getDescCorIng());
        	 if(i>=sysCatProdInicial && i<=sysCatProdFinal)
        	 {
        		 sysCatProdCero = todosSysCatProd.get(i);
        		 paginaSysCatProds.add(sysCatProdCero);
        		 System.out.print("  -- En lista  --" + sysCatProdCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProdController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByClave(clave,empresa,recinto));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProds);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }
   
	// Consulta de sys_usuarios-sys_recinto con paginacion y validacion de token.
    @GetMapping(path = {"/ProdUnicoUm"})
    public AnsSysCatProdUm listarUnicoUm(@RequestParam(required = false, value = "clave") String clave,
    		                    HttpServletRequest peticion) {
		/*
		 * BigDecimal limInferior = BigDecimal.valueOf(0.0); BigDecimal limSuperior =
		 * BigDecimal.valueOf(99999999.0); Double DRecinto = 0.0; if
		 * (!recinto.isEmpty()) { DRecinto = Double.valueOf(recinto); limInferior =
		 * BigDecimal.valueOf(DRecinto); limSuperior = BigDecimal.valueOf(DRecinto); }
		 * else { limInferior = BigDecimal.valueOf(0.0); limSuperior =
		 * BigDecimal.valueOf(99999999.0); }
		 */    	
    	int page = 1;
    	int perPage = 1;
		 AnsSysCatProdUm Producto = new AnsSysCatProdUm();
    	// Validación de token
    	String user;
    	AnsSysCatProdListUm respuesta = new AnsSysCatProdListUm();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		

			 Producto.setCr(respuesta.getCr());
			 Producto.setDescripcion(respuesta.getDescripcion());
				return Producto;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recinto = usuarioProc.getIdRecinto();
		BigDecimal empresa = usuarioProc.getIdEmpresa();
		String recintoS = recinto.toString();
		String empresaS = empresa.toString();
	// Preparación de la paginación.
		boolean enabled = true;
		SysCatProd sysCatProdCero = new SysCatProd();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProd> todosSysCatProd;
		List<SysCatProd> paginaSysCatProds; 
		Integer sysCatProdInicial, sysCatProdFinal;
		
		SysCatProdPag resultado = new SysCatProdPag();
		SysCatProdUm listaProd = new SysCatProdUm();
                                                                      	System.out.print(" + RestSysCatProdController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByClave(clave,empresa,recinto);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         AnsSysCatProdListUm Acumulado = new AnsSysCatProdListUm();
         sysCatProdInicial = (perPage  * (page - 1) );
         sysCatProdFinal   = (sysCatProdInicial + perPage) - 1;
         todosSysCatProd  = sysProd.findByClave(clave,empresa,recinto);
         paginaSysCatProds = sysProd.findByClave(clave,empresa,recinto);
         paginaSysCatProds.clear();
         
         Acumulado.objetoItem = sysProd.findByClave(clave,empresa,recinto);
         Acumulado.objetoItem.clear();
         
         Acumulado.uMDescripcion =  new ArrayList<String>();
         Acumulado.uMDescripcion.clear();
         
         String uMDescrip = new String();
         DetCatAp apendice07 = new DetCatAp();
  //       Acumulado.uMDescripcion = new SysCatProdUm();
  //       Acumulado.uMDescripcion = new 
         SysCatProdUm elementoItem = new SysCatProdUm();
         for (int i=0; i<todos;i++) {
        	 															System.out.print("\n " + "          + RestSysCatProdController Apendice: " + i + " - " + todosSysCatProd.get(i).getClveProduc() + " - " + todosSysCatProd.get(i).getTipProd() + " - " + todosSysCatProd.get(i).getDescCorIng());
        	 if(i>=sysCatProdInicial && i<=sysCatProdFinal)
        	 {
        		 sysCatProdCero = todosSysCatProd.get(i);
        		 														System.out.print("\n " + "          + RestSysCatProdController UM: " +  sysCatProdCero.getuM() + "\n ");
        		 apendice07 = RepoDetCatAp.findByCampos("AP07", sysCatProdCero.getuM(), "X");
        		 uMDescrip = apendice07.getDesCorta();
        		 paginaSysCatProds.add(sysCatProdCero);
        		 elementoItem.setObjetoItem(sysCatProdCero);
        		 elementoItem.setuMDescripcion(uMDescrip);
        		 Acumulado.objetoItem.add(sysCatProdCero);
        		 Acumulado.uMDescripcion.add(uMDescrip);
        		 Producto.setContenido(sysCatProdCero);
        		 Producto.setuMDescripcion(uMDescrip);
        		 System.out.print("  -- En lista  --" + sysCatProdCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProdController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByClave(clave,empresa,recinto));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProds);
        
	 	 respuesta.setPage(page);
	 	 respuesta.setPerPage(perPage);
	     respuesta.setTotal(resultado.getTotal());
	     respuesta.setTotalPages(pagEntero);
	     respuesta.setObjetoItem(Acumulado.getObjetoItem());
	     respuesta.setuMDescripcion(Acumulado.getuMDescripcion());
	 	 
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
		 

		 Producto.setCr(respuesta.getCr());
		 Producto.setDescripcion(respuesta.getDescripcion());

		 
         return Producto;
    }

	// Consulta de Productos  con paginacion y validacion de token.
    @DeleteMapping(path = {"/ProdUnico"})
    public AnsSysCatProdList eliminarProducto(@RequestParam(required = false, value = "clave") String clave,
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysCatProdList respuesta = new AnsSysCatProdList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProdController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProdController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
	// parametros empresa y recinto del usuario
		SysUsuarios usuarioProc = sysUser.findByExiste(BigDecimal.valueOf(Double.valueOf(user)));
		BigDecimal recinto = usuarioProc.getIdRecinto();
		BigDecimal empresa = usuarioProc.getIdEmpresa();
	// Preparación de la paginación.
		Long todos = (long) 0;
    // obtener el total de sys_usuarios
         todos = sysProd.countByClave(clave,empresa,recinto);
         SysCatProd productoABorrar = sysProd.findByClaves(clave, empresa, recinto);
       																System.out.print(" + RestSysCatProdController listarPag todos: " + todos +"\n ");
	 try {
		if (todos == 1)
	         {
			//   sysProd.deleteById(clave); 
			//   sysProd.deleteByProducto(clave, empresa, recinto);
			     sysProd.delete(productoABorrar);
	        	 respuesta.setCr("00");
	    		 respuesta.setDescripcion("Correcto");
	             return respuesta;         
	         } else 
		         {
		        	 if (todos > 1) 
		        	 {
			    		 respuesta.setCr("97");
			    		 respuesta.setDescripcion("Hay más de un producto");
			             return respuesta;
		        		 
		        	 }
		        	 else 
		        	 {
			    		 respuesta.setCr("98");
			    		 respuesta.setDescripcion("No hay producto");
			             return respuesta;
		        	 }
		         }
	    } catch (Exception ex) {
			throw new ApiRequestException("Upsi");
		}
    }

    
}
