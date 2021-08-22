package mx.com.oneproject.spco.rest;


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

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import mx.com.oneproject.spco.exception.ApiRequestException;
import mx.com.oneproject.spco.log.logRegistra;
import mx.com.oneproject.spco.modelo.CatApendices;
import mx.com.oneproject.spco.modelo.DetCatAp;
import mx.com.oneproject.spco.repositorio.IMDetCatApRepo;
import mx.com.oneproject.spco.respuesta.ApendPag;
import mx.com.oneproject.spco.result.AnsApenPagList;
import mx.com.oneproject.spco.result.AnsDetCatAp;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/DetCatAp")
public class RestDetCatApController {

	@Autowired
	private IMDetCatApRepo RepoDetCatAp;

	@Autowired
	private logRegistra registrar;
	//modificacion github desktop
	// Consulta de lista de catálogos
	@GetMapping
	public AnsDetCatAp listar(HttpServletRequest peticion) {
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController token: " + token + "\n ");
		AnsDetCatAp Respuesta = new AnsDetCatAp();
		System.out.print(" + RestCatApendController listar \n");

		if (token != null) {
			String user = Jwts.parser().setSigningKey("0neProj3ct").parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			System.out.print("\n\n + RestCatApendController Usuario: " + user + "\n ");
		} else {
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");
			return Respuesta;
		}

		Respuesta.setCr("00");
		Respuesta.setDescripcion("Correcto");
		Respuesta.setContenido(RepoDetCatAp.findAll());
		return Respuesta;
	}

	
	// Consulta de lista de catálogos
	@GetMapping(path = {"/{id}"})
	public AnsDetCatAp listarId(HttpServletRequest peticion, @PathVariable("id") String id) {
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController token: " + token + "\n ");
		AnsDetCatAp Respuesta = new AnsDetCatAp();
		System.out.print(" + RestCatApendController listar \n");

		if (token != null) {
			String user = Jwts.parser().setSigningKey("0neProj3ct").parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			System.out.print("\n\n + RestCatApendController Usuario: " + user + "\n ");
		} else {
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");
			return Respuesta;
		}

		Respuesta.setCr("00");
		Respuesta.setDescripcion("Correcto");
		Respuesta.setContenido(RepoDetCatAp.findByClave(id.concat("%")));
		if(Respuesta.getContenido().isEmpty())
		{
			Respuesta.setCr("01");
			Respuesta.setDescripcion("Sin contenido");
		}
		return Respuesta;
	}

	// Consulta de elementos por llave completa
	@GetMapping(path = {"/Unico/{c1}"})
	public AnsDetCatAp listIdC1C2(HttpServletRequest peticion,
            @PathVariable("c1") String c1,
			@RequestParam(required = false, value = "id1") String campoId1,
			@RequestParam(required = false, value = "id2") String campoId2) {
		String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController token: " + token + "\n ");
		AnsDetCatAp Respuesta = new AnsDetCatAp();
		System.out.print(" + RestCatApendController listar \n");

		if (token != null) {
			String user = Jwts.parser().setSigningKey("0neProj3ct").parseClaimsJws(token.replace("Bearer", ""))
					.getBody().getSubject();
			System.out.print("\n\n + RestCatApendController Usuario: " + user + "\n ");
		} else {
			Respuesta.setCr("99");
			Respuesta.setDescripcion("Petición sin token");
			return Respuesta;
		}

		Respuesta.setCr("00");
		Respuesta.setDescripcion("Correcto");
		List<DetCatAp> Datos = RepoDetCatAp.findByClave("SinDatos");
		Datos.add(RepoDetCatAp.findByCampos(c1,campoId1,campoId2));
		Respuesta.setContenido(Datos);
		if(Respuesta.getContenido().isEmpty())
		{
			Respuesta.setCr("01");
			Respuesta.setDescripcion("Sin contenido");
		}
		return Respuesta;
	}


	// Alta de apendice con validacion de token y registro de log.    
	@PostMapping
	public  AnsDetCatAp  creaApendice(HttpServletRequest peticion,
			                          @RequestBody DetCatAp NuevoApendice){
		AnsDetCatAp respuesta = new AnsDetCatAp();
    	String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController-creaApendice token: " + token + "\n ");

		registrar.registra("creaApendice", "DetCatAp", "/DetCatAp", NuevoApendice);
	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestCatApendController-creaApendice Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}

    	try {
	    	//-------------existe el apendice?
    		DetCatAp ApendiceProc = RepoDetCatAp.findByCampos(NuevoApendice.getClvap(),NuevoApendice.getId1(),NuevoApendice.getId2());
    		
			if (ApendiceProc == null)		{
					System.out.print("\n\n + RestCatApendController-creaApendice No hay registro: " + NuevoApendice.getClvap() + " "+ NuevoApendice.getId1() + " "+ NuevoApendice.getId2() + "\n ");
			    	//-------------
					ApendiceProc = RepoDetCatAp.save(NuevoApendice);
					System.out.print(" + estCatApendController-creaApendice insertar id: " + ApendiceProc.getDesCorta() + " " + ApendiceProc.getDesLarga() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					List<DetCatAp> Datos = RepoDetCatAp.findByClave("SinDatos");
					Datos.add(RepoDetCatAp.findByCampos(NuevoApendice.getClvap(),NuevoApendice.getId1(),NuevoApendice.getId2()));
					respuesta.setContenido(Datos);
			        return respuesta;
				} else {
						respuesta.setCr("84");
						respuesta.setDescripcion("Ya existe el apendice");
				        return respuesta;
			    	}
	    	} catch (Exception ex) {
	    		throw new ApiRequestException("Upsi");
	    	} 
		}
	
	// Modifica apendice con validacion de token y registro de log.    
	@PutMapping
	public  AnsDetCatAp  modificaApendice(HttpServletRequest peticion,
			                          @RequestBody DetCatAp NuevoApendice){
		AnsDetCatAp respuesta = new AnsDetCatAp();
    	String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController-modificaApendice token: " + token + "\n ");

		registrar.registra("modificaApendice", "DetCatAp", "/DetCatAp", NuevoApendice);
	
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestCatApendController-modificaApendice Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}

    	try {
	    	//-------------existe el apendice?
    		DetCatAp ApendiceProc = RepoDetCatAp.findByCampos(NuevoApendice.getClvap(),NuevoApendice.getId1(),NuevoApendice.getId2());
    		
			if (ApendiceProc != null)		{
					System.out.print("\n\n + RestCatApendController-modificaApendice Si hay registro: " + NuevoApendice.getClvap() + " "+ NuevoApendice.getId1() + " "+ NuevoApendice.getId2() + "\n ");
			    	//-------------
					ApendiceProc = RepoDetCatAp.save(NuevoApendice);
					System.out.print(" + estCatApendController-modificaApendice insertar id: " + ApendiceProc.getDesCorta() + " " + ApendiceProc.getDesLarga() + "\n ");
					respuesta.setCr("00");
					respuesta.setDescripcion("Correcto");
					List<DetCatAp> Datos = RepoDetCatAp.findByClave("SinDatos");
					Datos.add(RepoDetCatAp.findByCampos(NuevoApendice.getClvap(),NuevoApendice.getId1(),NuevoApendice.getId2()));
					respuesta.setContenido(Datos);
			        return respuesta;
				} else {
						respuesta.setCr("81");
						respuesta.setDescripcion("No existe el apendice");
				        return respuesta;
			    	}
	    	} catch (Exception ex) {
	    		throw new ApiRequestException("Upsi");
	    	} 
		}
	
	
	// Deshabilitacion de apendice con validacion de token y registro de log. 	
    @DeleteMapping(path = {"/{id}"})
    public AnsDetCatAp EliminarId(HttpServletRequest peticion,
                                    @PathVariable("id") String id,
    								@RequestParam(required = false, value = "id1") String campoId1,
    								@RequestParam(required = false, value = "id2") String campoId2)   {
    	System.out.print(" + RestCatApendController-EliminarId id: " + id + " "  + campoId1 + " "  + campoId2 + "\n");
    	AnsDetCatAp respuesta = new AnsDetCatAp();
    	
    	String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestCatApendController-EliminarId token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestCatApendController-EliminarId Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}

		try {
			DetCatAp Apendice = RepoDetCatAp.findByCampos(id,campoId1,campoId2);
			if(Apendice == null) {
				respuesta.setCr("94");
				respuesta.setDescripcion("No existe el Apendice");		
				return respuesta;		
				}
			
			if (Apendice.getDelLogico() == 1) {
				respuesta.setCr("95");
				respuesta.setDescripcion("Apendice ya inhabilitado");		
				return respuesta;		
				} 
    			
		      	Apendice.setDelLogico(1);
	
	    		ObjectMapper mapper = new ObjectMapper();
	    		String jsonInString =id;
	    		System.out.print(" + Objeto: " + jsonInString);		
	    		registrar.registra("eliminaApendice", "DetCatAp", "/DetCatAp/"+ id, Apendice);
	    			
	    		RepoDetCatAp.save(Apendice);
	//    		respuesta.setContenido(Apendice);
	    		respuesta.setCr("00");
	    		respuesta.setDescripcion("Correcto");
	    		return respuesta; }
    	catch (Exception e) {
	    		respuesta.setDescripcion("Registro con incidencia");
	    		return respuesta;}
			}	
    
    
	// Consulta de apendices con paginacion y validacion de token.
    @GetMapping(path = {"/pag"})
    public AnsApenPagList listarPag(@RequestParam(required = false, value = "page") int page,
    		                    @RequestParam(required = false, value = "perpage") int perPage, 
    		                    @RequestParam(required = false, value = "clvap") String claveClvap,
    		                    HttpServletRequest peticion) {
    	
    	AnsApenPagList respuesta = new AnsApenPagList();
    	String token = peticion.getHeader("Authorization");
		System.out.print("\n\n + RestDetCatApController token: " + token + "\n ");
		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey("0neProj3ct")
					.parseClaimsJws(token.replace("Bearer",  ""))
					.getBody()
					.getSubject();
			System.out.print("\n\n + RestDetCatApController Usuario: " + user + "\n ");
		}	else	{
			respuesta.setCr("99");
			respuesta.setDescripcion("Petición sin token");		
			return respuesta;
			}
		boolean enabled = true;
		DetCatAp apendiceCero = new DetCatAp();
		Long todos = (long) 0;
		double paginas = (float) 0.0;
		Integer pagEntero = 0;
		List<DetCatAp> todosApendices;
		List<DetCatAp> paginaApendices; 
		Integer ApendiceInicial, ApendiceFinal;
		
		ApendPag resultado = new ApendPag();
    	
    	System.out.print(" + RestDetCatApController listarPag page: " + page + " perpage: " + perPage +"\n ");

    	// obtener el total.
         todos = RepoDetCatAp.countByActivos(claveClvap);
         paginas = (double) todos / perPage;
         pagEntero = (int) paginas;
         if ((paginas-pagEntero)>0)
         {
        	 pagEntero++;
         }
         // Obtener la lista solicitada
         ApendiceInicial = (perPage  * (page - 1) );
         ApendiceFinal   = (ApendiceInicial + perPage) - 1;
         todosApendices  = RepoDetCatAp.findByClave(claveClvap);
         paginaApendices = RepoDetCatAp.findByClave(claveClvap);
         paginaApendices.clear();
         for (int i=0; i<todos;i++) {
        	 System.out.print("\n " + "          + RestDetCatApController Apendice: " + i + " - " + todosApendices.get(i).getDesCorta() );
        	 if(i>=ApendiceInicial && i<=ApendiceFinal)
        	 {
        		 apendiceCero = todosApendices.get(i);
        		 paginaApendices.add(apendiceCero);
        		 System.out.print("  -- En lista  --" + apendiceCero.getDesCorta() );
        	 }
         }
         
         
     	System.out.print("\n + RestDetCatApController listarPag todos: " + todos + " paginas: " + paginas + "  " + (paginas-pagEntero ) +"\n ");
         //
         resultado.setPage(page);
         resultado.setPerPage(perPage);
         resultado.setTotal((int) RepoDetCatAp.countByActivos(claveClvap));
         resultado.setTotalPages(pagEntero);
         resultado.setApendices(paginaApendices);

	 	 respuesta.setContenido(resultado);
		 respuesta.setCr("00");
		 respuesta.setDescripcion("Correcto");
         return respuesta;
    }

}
