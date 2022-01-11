package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
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
import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.modelo.SysAduFactId;
import mx.com.oneproject.spco.modelo.SysAduTrasp;
import mx.com.oneproject.spco.modelo.SysCatProducto;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.repositorio.IMDetCatApRepo;
import mx.com.oneproject.spco.repositorio.IMSysAduFactRepo;
import mx.com.oneproject.spco.repositorio.IMSysAduTransRepo;
import mx.com.oneproject.spco.repositorio.IMSysCatProductoRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.respuesta.AnsSysAduFact;
import mx.com.oneproject.spco.respuesta.AnsSysAduFactCons;
import mx.com.oneproject.spco.respuesta.AnsSysAduFactCuantos;
import mx.com.oneproject.spco.respuesta.AnsSysAduFactList;
import mx.com.oneproject.spco.respuesta.AnsSysAduTrans;
import mx.com.oneproject.spco.respuesta.SysAduFactPag;
import mx.com.oneproject.spco.respuesta.SysAduTrasPag;
import mx.com.oneproject.spco.result.AnsSysAduTraspList;
import mx.com.oneproject.spco.service.SysAduFactService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/AduFact")
public class RestSysAduFactController {
	
	@Autowired
	private IMSysAduFactRepo aduFact;

	@Autowired
	private IMSysAduTransRepo aduTrans;

	@Autowired
	private IMSysUserRepo sysUser;

	@Autowired
	private IMDetCatApRepo RepoDetCatAp;
	
	@Autowired
	private IMSysCatProductoRepo sysProd;

	@Autowired
	private SysAduFactService sysAFServ;

	/**
	 * Esta clase define el método de consulta plana de facturas
	 * @author: Roberto Avila
	 * @version: 1/10/2021/A
	 * @see 
	 */	

	@GetMapping
//	public AnsSysAduFactCuantos listar(HttpServletRequest peticion){
//		String cli = "1234567891";
//		String part = "1234567891";
//		String ind = "1";
//		AnsSysAduFactCuantos respuesta = new AnsSysAduFactCuantos();
//		respuesta.setCr("00");
//  		respuesta.setDescripcion("Exitoso");
//  		respuesta.setContenido(sysAFServ.getRemanente(cli,part,ind));
//		return respuesta;
	
	
    public List<SysAduTrasp> listar(HttpServletRequest peticion){ 
		return aduTrans.findAll(); 

	}

	/**
	 * Esta clase define el método de consulta de traspasos 
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/Traspasos"})
	public AnsSysAduTrans Traspasos( HttpServletRequest peticion,
			@RequestParam(required = false, value = "cli") String cli)
			{
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
		  // Validación de token    	
			AnsSysAduTrans respuesta = new AnsSysAduTrans();
//			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	    	String user;
//	    	Integer resultado = 0;
//	    	String ind = "1";
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
			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + " " + empresaS + " " + recintoS + "\n ");
							
	    	try {
		    	//-------------existe el producto?		
	    		respuesta.setCr("00");
	      		respuesta.setDescripcion("Exitoso");
	      		System.out.print(" + RestSysAduFactController traspaso - cli : " + cli + "\n ");
	      		respuesta.setContenido(aduTrans.BuscarByCliente(cli));
			        return respuesta;
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de consulta de traspasos 
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/TraspasosPag"})
	public AnsSysAduTraspList TraspasosPag( HttpServletRequest peticion,
			@RequestParam(required = false, value = "page") int page,
            @RequestParam(required = false, value = "perpage") int perPage, 
			@RequestParam(required = false, value = "cli") String cli)
			{
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
		  // Validación de token    	
			AnsSysAduTraspList respuesta = new AnsSysAduTraspList();
			SysAduTrasPag pagina = new SysAduTrasPag();
//			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	    	String user;
//	    	Integer resultado = 0;
//	    	String ind = "1";
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
			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + " " + empresaS + " " + recintoS + "\n ");

			// Preparación de la paginación.
			boolean enabled = true;
			SysAduTrasp sysAduTraspCero = new SysAduTrasp();
			Long todos = (long) 0;
			double paginas = (float) 0.0;
			Integer pagEntero = 0;
			List<SysAduTrasp> todosSysAduTrasp;
			List<SysAduTrasp> paginaSysAduTrasp; 
			Integer sysAduTraspInicial, sysAduTraspFinal;
			
			SysAduTrasPag resultado = new SysAduTrasPag();
	                                                                      	System.out.print(" + RestSysAduFactController listarPag page: " + page + " perpage: " + perPage +"\n ");
    // obtener el total de sys_usuarios
            todos = aduTrans.ContarByCliente(cli);
            paginas = (double) todos / perPage;
            pagEntero = (int) paginas;
            if ((paginas-pagEntero)>0)
            {
           	 pagEntero++;
            }

            sysAduTraspInicial = (perPage  * (page - 1) );
            sysAduTraspFinal   = (sysAduTraspInicial + perPage) - 1;
            todosSysAduTrasp  = aduTrans.BuscarByCliente(cli);
            paginaSysAduTrasp = aduTrans.BuscarByCliente(cli);
            paginaSysAduTrasp.clear();
            for (int i=0; i<todos;i++) {
           	 															System.out.print("\n " + "          + RestSysAduFactController Apendice: " + i + " - " + todosSysAduTrasp.get(i).getNumFact() );
           	 if(i>=sysAduTraspInicial && i<=sysAduTraspFinal)
           	 {
           		sysAduTraspCero = todosSysAduTrasp.get(i);
           		paginaSysAduTrasp.add(sysAduTraspCero);
           		 System.out.print("  -- En lista  --" + sysAduTraspCero.getNumFact() );
           	 }
            }

            resultado.setPage(page);
            resultado.setPerPage(perPage);
            resultado.setTotal((int) todos.intValue());
            resultado.setTotalPages(pagEntero);
            resultado.setSysCatTrasp(paginaSysAduTrasp);
            respuesta.setContenido(resultado);
            respuesta.setCr("00");
            respuesta.setDescripcion("Correcto");
            return respuesta;

	}

	
	/*
	 * public List<SysAduFact> listar(HttpServletRequest peticion){ return
	 * aduFact.findAll(); }
	 */
	/**
	 * Esta clase define el método de alta de SysAduFact
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/Cuantos"})
	public AnsSysAduFactCuantos cuantosHay( HttpServletRequest peticion,
			@RequestParam(required = false, value = "cli") String cli,
			@RequestParam(required = false, value = "prod") String part){
		
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
		  // Validación de token    	
			AnsSysAduFactCuantos respuesta = new AnsSysAduFactCuantos();
			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	    	String user;
	    	Integer resultado = 0;
	    	String ind = "1";
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
			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + " " + empresaS + " " + recintoS + "\n ");
							
	    	try {
		    	//-------------existe el producto?		
	    		respuesta.setCr("00");
	      		respuesta.setDescripcion("Exitoso");
	      		System.out.print(" + RestSysAduFactController traspaso - cli : " + cli + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - part : " + part + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - ind : " + ind + "\n ");	      		
	      		respuesta.setContenido(sysAFServ.getRemanente(cli,part,ind));
			        return respuesta;
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/*
	 * public List<SysAduFact> listar(HttpServletRequest peticion){ return
	 * aduFact.findAll(); }
	 */
	/**
	 * Esta clase define el método de alta de SysAduFact
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/CuantosInv"})
	public AnsSysAduFactCuantos cuantosHayInv( HttpServletRequest peticion,
			@RequestParam(required = false, value = "cli") String cli,
			@RequestParam(required = false, value = "prod") String part){
		
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
		  // Validación de token    	
			AnsSysAduFactCuantos respuesta = new AnsSysAduFactCuantos();
			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	    	String user;
	    	Integer resultado = 0;
	    	String ind = "1";
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
			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + " " + empresaS + " " + recintoS + "\n ");
							
	    	try {
		    	//-------------existe el producto?		
	    		respuesta.setCr("00");
	      		respuesta.setDescripcion("Exitoso");
	      		System.out.print(" + RestSysAduFactController traspaso - cli : " + cli + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - part : " + part + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - ind : " + ind + "\n ");	      		
	      		respuesta.setContenido(sysAFServ.getRemanenteInv(cli,part,ind));
			        return respuesta;
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}

	/**
	 * Esta clase define el método de alta de SysAduFact
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/CuantosInvTot"})
	public AnsSysAduFactCuantos cuantosHayInvTot( HttpServletRequest peticion,
			@RequestParam(required = false, value = "cli") String cli,
			@RequestParam(required = false, value = "prod") String part){
		
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
		  // Validación de token    	
			AnsSysAduFactCuantos respuesta = new AnsSysAduFactCuantos();
			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	    	String user;
	    	Integer resultado = 0;
	    	String ind = "1";
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
			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + " " + empresaS + " " + recintoS + "\n ");
							
	    	try {
		    	//-------------existe el producto?		
	    		respuesta.setCr("00");
	      		respuesta.setDescripcion("Exitoso");
	      		System.out.print(" + RestSysAduFactController traspaso - cli : " + cli + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - part : " + part + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - ind : " + ind + "\n ");	      		
	      		respuesta.setContenido(sysAFServ.getRemanenteInvTot(cli,part,ind));
			        return respuesta;
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
	}


	/**
	 * Esta clase define el método de alta de SysAduFact
	 * @author: Roberto Avila
	 * @version: 01/10/2021/A
	 * @see 
	 */	
	@GetMapping(path = {"/Traspaso"})
	public AnsSysAduFactCuantos traspaso( HttpServletRequest peticion,
//			@RequestParam(required = false, value = "empresa") String empresa,
//			@RequestParam(required = false, value = "recinto") String recinto,
			@RequestParam(required = false, value = "cli") String cli,
			@RequestParam(required = false, value = "prod") String prod,
			@RequestParam(required = false, value = "cantidad") Integer cantidad,
			@RequestParam(required = false, value = "recintoD") String recintoD,
			@RequestParam(required = false, value = "cliD") String cliD,
			@RequestParam(required = false, value = "numFactN") String numFactN){
		
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Alta: " + peticion.getHeader("Authorization")+ "\n ");	
		  // Validación de token    	
			AnsSysAduFactCuantos respuesta = new AnsSysAduFactCuantos();
			SysAduFactId llave = new SysAduFactId();
	    	String token = peticion.getHeader("Authorization");
	    	String user;
	    	Integer resultado = 0;
	    	String ind = "1";
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
			System.out.print("\n\n + RestSysAduFactController Usuario: " + user + " " + empresaS + " " + recintoS + "\n ");
							
	    	try {
		    	//-------------existe el producto?		
	    		respuesta.setCr("00");
	      		respuesta.setDescripcion("Exitoso");
	      		System.out.print("\n\n + RestSysAduFactController traspaso - empresaS : " + empresaS + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - recintoS : " + recintoS + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - cli : " + cli + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - prod : " + prod + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - cantidad : " + cantidad + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - recintoD : " + recintoD + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - cliD : " + cliD + "\n ");
	      		System.out.print(" + RestSysAduFactController traspaso - numFactN : " + numFactN + "\n ");
	      		respuesta.setContenido(sysAFServ.getTraspasoCliente(empresaS, recintoS, cli, prod, cantidad, recintoD, cliD, numFactN));
			        return respuesta;
		    	} catch (Exception ex) {
		    		throw new ApiRequestException("Upsi");
		    	}
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
	    		llave.setNumPedimentoEntrada(NuevoAduFact.getNumPedimentoEntrada());
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
	 * Esta clase define el método de modificacion de SysAduFact
	 * @author: Roberto Avila
	 * @version: 15/10/2021/A
	 * @see 
	 */	
	@PutMapping
	public AnsSysAduFact modifSysAduFact(HttpServletRequest peticion,
									@RequestBody SysAduFact ModifAduFact){
		
									System.out.print("\n\n + RestSysAduFactController Modificacion: " + peticion.getRequestURI() + " " + peticion.getRequestURL()+ "\n ");	
									System.out.print("\n\n + RestSysAduFactController Modificacion: " + peticion.getHeader("Authorization")+ "\n ");	
			//-----------
			String recinto = ModifAduFact.getRecinto();
			String empresa = ModifAduFact.getEmpresa();
			empresa = String.format("%04d", Integer.valueOf(empresa));
			recinto = String.format("%04d", Integer.valueOf(recinto));
			ModifAduFact.setRecinto(recinto);
			ModifAduFact.setEmpresa(empresa);
			
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
	    		llave.setIdCliProv(ModifAduFact.getIdCliProv());
	    		llave.setNumPart(ModifAduFact.getNumPart());
	    		llave.setNumFact(ModifAduFact.getNumFact());
//	    		llave.setiDImpoEexpo(ModifAduFact.getiDImpoEexpo());
	    		llave.setNumPedimentoEntrada(ModifAduFact.getNumPedimentoEntrada());

	    		if (aduFact.findById(llave).isEmpty()){
		    	//-------------
					respuesta.setCr("83");
					respuesta.setDescripcion("No existe cliente / parte / factura / IndImpExp");
			        return respuesta;
				  } else {
						SysAduFact facturaProc = aduFact.save(ModifAduFact);
						System.out.print(" + RestSysAduFactController modifica Factura: " + facturaProc.getIdCliProv() + "\n ");
						respuesta.setCr("00");
						respuesta.setDescripcion("Correcto");
						respuesta.setContenido(ModifAduFact);
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

	/**
	 * Esta clase define el método de consulta paginada de partes del cliente
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @GetMapping(path = {"/FactCliPartFact"})
    public AnsSysAduFactCons ConsultaUnica(
            @RequestParam(required = false, value = "cliente") String cliente, 
            @RequestParam(required = false, value = "parte") String parte, 
            @RequestParam(required = false, value = "factura") String factura, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysAduFactCons respuesta = new AnsSysAduFactCons();
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
		SysAduFact SysAduFactCero = new SysAduFact();
    // obtener el total de sys_usuarios
		SysAduFactCero = aduFact.BuscarByLlave(cliente,parte,factura);
		if(SysAduFactCero == null) {
			respuesta.setContenido(SysAduFactCero);
			respuesta.setCr("97");
			respuesta.setDescripcion("No existe cliente / parte / factura");		
		}
		else {
			 DetCatAp apendice07T = new DetCatAp();
			 List<SysCatProducto> todosSysCatProducto;
			 SysCatProducto producto = new SysCatProducto();

	   		 apendice07T = RepoDetCatAp.findByCampos("AP04", SysAduFactCero.getPaisFact(), "X");
	   		 if (apendice07T == null)
	   		   {
	   			 respuesta.setDescPais("Clave de país sin descripción");
	   			 }
	   		 else
	   		 {
	   			 respuesta.setDescPais(apendice07T.getDesCorta());
	   		 }			
	   		 apendice07T = RepoDetCatAp.findByCampos("AP07", SysAduFactCero.getUnidadDeMedida(), "X");
	   		 if (apendice07T == null)
	   		   {
	   			 respuesta.setDescUMC("Clave de unidad de medida sin descripción");
	   			 }
	   		 else
	   		 {
	   			 respuesta.setDescUMC(apendice07T.getDesCorta());
	   		 }	
			 todosSysCatProducto  = sysProd.findByClave(SysAduFactCero.getProducto(),empresaS,recintoS);
	   		 if (todosSysCatProducto.isEmpty())
	   		   {
	   			 respuesta.setDescCortaProd("Producto sin descripción corta");
	   			 }
	   		 else
	   		 {
	   			producto = todosSysCatProducto.get(0);
	   			respuesta.setDescCortaProd(producto.getDescCorta());
	   		 }	
	   		 
			 respuesta.setContenido(SysAduFactCero);
			 respuesta.setCr("00");
			 respuesta.setDescripcion("Correcto");		
		}

        return respuesta;
    }

	/**
	 * Esta clase define el método de borrado de partes del cliente
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @DeleteMapping(path = {"/FactCliPartFact"})
    public AnsSysAduFact Borrado(
            @RequestParam(required = false, value = "cliente") String cliente, 
            @RequestParam(required = false, value = "parte") String parte, 
            @RequestParam(required = false, value = "factura") String factura, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysAduFact respuesta = new AnsSysAduFact();
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
		SysAduFact SysAduFactCero = new SysAduFact();
    // obtener el total de sys_usuarios
		SysAduFactCero = aduFact.BuscarByLlave(cliente,parte,factura);
		if(SysAduFactCero == null) {
			respuesta.setContenido(SysAduFactCero);
			respuesta.setCr("97");
			respuesta.setDescripcion("No existe cliente / parte / factura");		
		}
		else {
//			SysAduFactId llaveBorrado = new SysAduFactId();
//			llaveBorrado.setIdCliProv(cliente);
//			llaveBorrado.setiDImpoEexpo("1");
//			llaveBorrado.setNumFact(factura);
//			llaveBorrado.setNumPart(parte);
//			aduFact.deleteById(llaveBorrado);
			aduFact.delete(SysAduFactCero);
			 respuesta.setContenido(SysAduFactCero);
			 respuesta.setCr("00");
			 respuesta.setDescripcion("Correcto");		
		}

        return respuesta;
    }
    
    
	/**
	 * Esta clase define el método de borrado de productos del cliente
	 * @author: Roberto Avila
	 * @version: 21/09/2021/A
	 * @see 
	 */	
    @DeleteMapping(path = {"/FactCliProdFact"})
    public AnsSysAduFact BorradoCPF(
            @RequestParam(required = false, value = "cliente") String cliente, 
            @RequestParam(required = false, value = "producto") String producto, 
            @RequestParam(required = false, value = "factura") String factura, 
    		                    HttpServletRequest peticion) {
    // Validación de token
    	String user;
    	AnsSysAduFact respuesta = new AnsSysAduFact();
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
	//	SysAduFact SysAduFactCero = new SysAduFact();
    // obtener el total de sys_usuarios
 //-   	try {
			Integer SysAduFactCero = aduFact.ContarByLlaveCPF(cliente,producto,factura,empresaS,recintoS);
			if(SysAduFactCero == 0) {
	//			respuesta.setContenido(SysAduFactCero.get());
				respuesta.setCr("97");
				respuesta.setDescripcion("No existe cliente / producto / factura");		
			}
			else {
				aduFact.BorradoByLlaveCPF(cliente,producto,factura,empresaS,recintoS);
	//			 respuesta.setContenido(SysAduFactCero.get());
				 respuesta.setCr("00");
				 respuesta.setDescripcion("Correcto");		
			}
//-    	} catch (Exception ex) {
//-			throw new ApiRequestException("Upsi");
//-    	}
        return respuesta;
    }

    
}
