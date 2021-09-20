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
import mx.com.oneproject.spco.modelo.SysCatProducto;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMDetCatApRepo;
import mx.com.oneproject.spco.repositorio.IMSysCatProductoRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.SysCatProductoPag;
import mx.com.oneproject.spco.respuesta.SysCatProductoUm;
import mx.com.oneproject.spco.result.AnsSysCatProducto;
import mx.com.oneproject.spco.result.AnsSysCatProductoList;
import mx.com.oneproject.spco.result.AnsSysCatProductoListUm;
import mx.com.oneproject.spco.result.AnsSysCatProductoTipo;
import mx.com.oneproject.spco.result.AnsSysCatProductoUm;

//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/CatProducto")
public class RestSysCatProductoController {


	
	@Autowired
	private IMSysCatProductoRepo sysProd;
	
	@Autowired
	private IMSysUserRepo sysUser;
	
	@Autowired
	private IMDetCatApRepo RepoDetCatAp;
	
	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping
	public List<SysCatProducto> listar(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		
		return sysProd.findAll();
	}

	// Consulta de la lista de sys_usuarios con validacion de token.
	@GetMapping(path = {"/TiposProd"})
	public AnsSysCatProductoTipo Tipos(HttpServletRequest peticion){
		
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
		System.out.print("\n\n + RestSysCatProductoController listar: " + peticion.getHeader("Authorization")+ "\n ");	
		AnsSysCatProductoTipo respuesta = new AnsSysCatProductoTipo();

		  // Validación de token    	
		String token = peticion.getHeader("Authorization");
                                                              		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                          			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
    public AnsSysCatProductoList listarPag(@RequestParam(required = false, value = "page") int page,
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
    	AnsSysCatProductoList respuesta = new AnsSysCatProductoList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
		SysCatProducto sysCatProductoCero = new SysCatProducto();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProducto> todosSysCatProducto;
		List<SysCatProducto> paginaSysCatProductos; 
		Integer sysCatProductoInicial, sysCatProductoFinal;
		
		SysCatProductoPag resultado = new SysCatProductoPag();
                                                                      	System.out.print(" + RestSysCatProductoController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByTipo(tipo,empresaS,recintoS);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysCatProductoInicial = (perPage  * (page - 1) );
         sysCatProductoFinal   = (sysCatProductoInicial + perPage) - 1;
         todosSysCatProducto  = sysProd.findByTipo(tipo,empresaS,recintoS);
         paginaSysCatProductos = sysProd.findByTipo(tipo,empresaS,recintoS);
         paginaSysCatProductos.clear();
         for (int i=0; i<todos;i++) {
//        	 															System.out.print("\n " + "          + RestSysCatProductoController Apendice: " + i + " - " + todosSysCatProducto.get(i).getClveProduc() + " - " + todosSysCatProducto.get(i).getTipProd() + " - " + todosSysCatProducto.get(i).getDescCorIng());
        	 if(i>=sysCatProductoInicial && i<=sysCatProductoFinal)
        	 {
        		 sysCatProductoCero = todosSysCatProducto.get(i);
        		 paginaSysCatProductos.add(sysCatProductoCero);
//        		 System.out.print("  -- En lista  --" + sysCatProductoCero.gegetClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProductoController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByTipo(tipo,empresaS,recintoS));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProductos);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

	// Alta de un Producto con validacion de token.
	@PostMapping
	public AnsSysCatProducto altaSysCatProducto(HttpServletRequest peticion,
									@RequestBody SysCatProducto NuevoProducto){
		
									System.out.print("\n\n + RestSysCatProductoController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatProductoController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysCatProducto respuesta = new AnsSysCatProducto();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
					SysCatProducto productoProc = sysProd.save(NuevoProducto);
					System.out.print(" + RestSysCatProductoController insertar Producto: " + productoProc.getClveProduc() + "\n ");
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
	public AnsSysCatProducto ModSysCatProducto(HttpServletRequest peticion,
									@RequestBody SysCatProducto NuevoProducto){
		
									System.out.print("\n\n + RestSysCatProductoController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysCatProductoController Alta: " + peticion.getHeader("Authorization")+ "\n ");	

			  // Validación de token    	
			AnsSysCatProducto respuesta = new AnsSysCatProducto();
	    	String token = peticion.getHeader("Authorization");
	                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
			if (token != null) {
				String user = Jwts.parser()
						.setSigningKey("0neProj3ct")
						.parseClaimsJws(token.replace("Bearer",  ""))
						.getBody()
						.getSubject();
	                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
					SysCatProducto productoProc = sysProd.save(NuevoProducto);
					System.out.print(" + RestSysCatProductoController insertar Producto: " + productoProc.getClveProduc() + "\n ");
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
    public AnsSysCatProductoListUm listarPagUm(@RequestParam(required = false, value = "page") int page,
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
    	AnsSysCatProductoListUm respuesta = new AnsSysCatProductoListUm();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
		SysCatProducto sysCatProductoCero = new SysCatProducto();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProducto> todosSysCatProducto;
		List<SysCatProducto> paginaSysCatProductos; 
		Integer sysCatProductoInicial, sysCatProductoFinal;
		
		SysCatProductoPag resultado = new SysCatProductoPag();
		SysCatProductoUm listaProd = new SysCatProductoUm();
                                                                      	System.out.print(" + RestSysCatProductoController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByTipo(tipo,empresaS,recintoS);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         AnsSysCatProductoListUm Acumulado = new AnsSysCatProductoListUm();
         sysCatProductoInicial = (perPage  * (page - 1) );
         sysCatProductoFinal   = (sysCatProductoInicial + perPage) - 1;
         todosSysCatProducto  = sysProd.findByTipo(tipo,empresaS,recintoS);
         paginaSysCatProductos = sysProd.findByTipo(tipo,empresaS,recintoS);
         paginaSysCatProductos.clear();
         
         Acumulado.objetoItem = sysProd.findByTipo(tipo,empresaS,recintoS);
         Acumulado.objetoItem.clear();
         
         Acumulado.uMDescripcion =  new ArrayList<String>();
         Acumulado.uMDescripcion.clear();
         
         String uMDescrip = new String();
         DetCatAp apendice07 = new DetCatAp();
  //       Acumulado.uMDescripcion = new SysCatProductoUm();
  //       Acumulado.uMDescripcion = new 
         SysCatProductoUm elementoItem = new SysCatProductoUm();
         for (int i=0; i<todos;i++) {
  //      	 															System.out.print("\n " + "          + RestSysCatProductoController Apendice: " + i + " - " + todosSysCatProducto.get(i).getClveProduc() + " - " + todosSysCatProducto.get(i).getTipProd() + " - " + todosSysCatProducto.get(i).getDescCorIng());
        	 if(i>=sysCatProductoInicial && i<=sysCatProductoFinal)
        	 {
        		 sysCatProductoCero = todosSysCatProducto.get(i);
  //      		 														System.out.print("\n " + "          + RestSysCatProductoController UM: " +  sysCatProductoCero.getuM() + "\n ");
        		 apendice07 = RepoDetCatAp.findByCampos("AP07", sysCatProductoCero.getuMC(), "X");
        		 if (apendice07 == null)
        		   {
        			 uMDescrip = "Sin descripción"; 
        			 }
        		 else
        		 {
        			 uMDescrip = apendice07.getDesCorta();
        		 }
        		 paginaSysCatProductos.add(sysCatProductoCero);
        		 elementoItem.setObjetoItem(sysCatProductoCero);
        		 elementoItem.setuMDescripcion(uMDescrip);
        		 Acumulado.objetoItem.add(sysCatProductoCero);
        		 Acumulado.uMDescripcion.add(uMDescrip);
      //  		 Acumulado.ListaProductosUm.add(i,elementoItem);
      //  		 Acumulado.ListaProductosUm.add(elementoItem);
        		 System.out.print("  -- En lista  --" + sysCatProductoCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProductoController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByTipo(tipo,empresaS,recintoS));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProductos);
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
    public AnsSysCatProductoList obtenerProducto(@RequestParam(required = false, value = "page") int page,
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
    	AnsSysCatProductoList respuesta = new AnsSysCatProductoList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
		SysCatProducto sysCatProductoCero = new SysCatProducto();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProducto> todosSysCatProducto;
		List<SysCatProducto> paginaSysCatProductos; 
		Integer sysCatProductoInicial, sysCatProductoFinal;
		
		SysCatProductoPag resultado = new SysCatProductoPag();
                                                                      	System.out.print(" + RestSysCatProductoController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByClave(clave,empresaS,recintoS);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         sysCatProductoInicial = (perPage  * (page - 1) );
         sysCatProductoFinal   = (sysCatProductoInicial + perPage) - 1;
         todosSysCatProducto  = sysProd.findByClave(clave,empresaS,recintoS);
         paginaSysCatProductos = sysProd.findByClave(clave,empresaS,recintoS);
         paginaSysCatProductos.clear();
         for (int i=0; i<todos;i++) {
  //      	 															System.out.print("\n " + "          + RestSysCatProductoController Apendice: " + i + " - " + todosSysCatProducto.get(i).getClveProduc() + " - " + todosSysCatProducto.get(i).getTipProd() + " - " + todosSysCatProducto.get(i).getDescCorIng());
        	 if(i>=sysCatProductoInicial && i<=sysCatProductoFinal)
        	 {
        		 sysCatProductoCero = todosSysCatProducto.get(i);
        		 paginaSysCatProductos.add(sysCatProductoCero);
   //     		 System.out.print("  -- En lista  --" + sysCatProductoCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProductoController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByClave(clave,empresaS,recintoS));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProductos);
	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }
   
	// Consulta de sys_usuarios-sys_recinto con paginacion y validacion de token.
    @GetMapping(path = {"/ProdUnicoUm"})
    public AnsSysCatProductoUm listarUnicoUm(@RequestParam(required = false, value = "clave") String clave,
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
		 AnsSysCatProductoUm Producto = new AnsSysCatProductoUm();
    	// Validación de token
    	String user;
    	AnsSysCatProductoListUm respuesta = new AnsSysCatProductoListUm();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
		empresaS = String.format("%04d", empresa.intValue());
		recintoS = String.format("%04d", recinto.intValue());
	// Preparación de la paginación.
		boolean enabled = true;
		SysCatProducto sysCatProductoCero = new SysCatProducto();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<SysCatProducto> todosSysCatProducto;
		List<SysCatProducto> paginaSysCatProductos; 
		Integer sysCatProductoInicial, sysCatProductoFinal;
		
		SysCatProductoPag resultado = new SysCatProductoPag();
		SysCatProductoUm listaProd = new SysCatProductoUm();
                                                                      	System.out.print(" + RestSysCatProductoController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
         todos = sysProd.countByClave(clave,empresaS,recintoS);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
    // Obtener la lista de sys_usuarios solicitada 
         AnsSysCatProductoListUm Acumulado = new AnsSysCatProductoListUm();
         sysCatProductoInicial = (perPage  * (page - 1) );
         sysCatProductoFinal   = (sysCatProductoInicial + perPage) - 1;
         todosSysCatProducto  = sysProd.findByClave(clave,empresaS,recintoS);
         paginaSysCatProductos = sysProd.findByClave(clave,empresaS,recintoS);
         paginaSysCatProductos.clear();
         
         Acumulado.objetoItem = sysProd.findByClave(clave,empresaS,recintoS);
         Acumulado.objetoItem.clear();
         
         Acumulado.uMDescripcion =  new ArrayList<String>();
         Acumulado.uMDescripcion.clear();
         
         String uMDescrip = new String();
         DetCatAp apendice07 = new DetCatAp();
  //       Acumulado.uMDescripcion = new SysCatProductoUm();
  //       Acumulado.uMDescripcion = new 
         SysCatProductoUm elementoItem = new SysCatProductoUm();
         for (int i=0; i<todos;i++) {
  //      	 															System.out.print("\n " + "          + RestSysCatProductoController Apendice: " + i + " - " + todosSysCatProducto.get(i).getClveProduc() + " - " + todosSysCatProducto.get(i).getTipProd() + " - " + todosSysCatProducto.get(i).getDescCorIng());
        	 if(i>=sysCatProductoInicial && i<=sysCatProductoFinal)
        	 {
        		 sysCatProductoCero = todosSysCatProducto.get(i);
  //      		 														System.out.print("\n " + "          + RestSysCatProductoController UM: " +  sysCatProductoCero.getuM() + "\n ");
        		 apendice07 = RepoDetCatAp.findByCampos("AP07", sysCatProductoCero.getuMC(), "X");
        		 if (apendice07 == null)
	      		   {
        			 	uMDescrip = "Sin descripción"; 
	      			 }
	      		 else
		      		 {
		      			 uMDescrip = apendice07.getDesCorta();
		      		 }
        	//	 uMDescrip = apendice07.getDesCorta();
        		 paginaSysCatProductos.add(sysCatProductoCero);
        		 elementoItem.setObjetoItem(sysCatProductoCero);
        		 elementoItem.setuMDescripcion(uMDescrip);
        		 Acumulado.objetoItem.add(sysCatProductoCero);
        		 Acumulado.uMDescripcion.add(uMDescrip);
        		 Producto.setContenido(sysCatProductoCero);
        		 Producto.setuMDescripcion(uMDescrip);
        		 System.out.print("  -- En lista  --" + sysCatProductoCero.getClveProduc() );
        	 }
         }
         
         																System.out.print("\n + RestSysCatProductoController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) sysProd.countByClave(clave,empresaS,recintoS));
         resultado.setTotalPages(pagEntero);
         resultado.setSysCatProductos(paginaSysCatProductos);
        
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
    public AnsSysCatProductoList eliminarProducto(@RequestParam(required = false, value = "clave") String clave,
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysCatProductoList respuesta = new AnsSysCatProductoList();
    	String token = peticion.getHeader("Authorization");
                                                                		System.out.print("\n\n + RestSysCatProductoController token: " + token + "\n ");
		if (token != null) {
			user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
                                                            			System.out.print("\n\n + RestSysCatProductoController Usuario: " + user + "\n ");
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
			System.out.print(" + RestSysCatProductoController delete recinto/empresa/clave: " + recintoS + " -- " + empresaS   + " -- " + clave + "\n ");

	// Preparación de la paginación.
		Long todos = (long) 0;
    // obtener el total de sys_usuarios
         todos = sysProd.countByClave(clave,empresaS,recintoS);
         SysCatProducto productoABorrar = sysProd.findByClaves(clave, empresaS, recintoS);
       																System.out.print(" + RestSysCatProductoController listarPag todos: " + todos +"\n ");
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
