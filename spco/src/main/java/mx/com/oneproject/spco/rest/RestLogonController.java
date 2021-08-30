package mx.com.oneproject.spco.rest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.modelo.VigenciaToken;
import mx.com.oneproject.spco.repositorio.IMSysEmpRepo;
import mx.com.oneproject.spco.repositorio.IMSysPerfilRepo;
import mx.com.oneproject.spco.repositorio.IMSysRecinRepo;
import mx.com.oneproject.spco.repositorio.IMSysUserRepo;
import mx.com.oneproject.spco.repositorio.IMVigTokenRepo;
import mx.com.oneproject.spco.respuesta.AnsLogonIn;
import mx.com.oneproject.spco.respuesta.AnsLogonOut;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/Logon")
public class RestLogonController {

	@Autowired
	private BCryptPasswordEncoder codificador;
	
	@Autowired
	private IMSysUserRepo repAppUser;

	@Autowired
	private IMVigTokenRepo vigencia;
		
	@Autowired
	private IMSysEmpRepo empresa;

	@Autowired
	private IMSysRecinRepo recinto;
	
	@Autowired
	private IMSysPerfilRepo perfil;
	
	@PostMapping
	public AnsLogonOut usuarioPorAutorizaracion(@RequestBody AnsLogonIn entrada){
    	String usuarioPorAutorizar = entrada.getUser();
    	String pass = entrada.getPassword();
    	Double dUsuario = Double.valueOf(usuarioPorAutorizar);
    	BigDecimal bDUsuario = BigDecimal.valueOf(dUsuario);
	    System.out.print(" + RestLogonController usuarioPorAutorizaracion Entrada user: " + usuarioPorAutorizar + "  " + "Entrada password: " + pass + "\n");
    	
    	String CryptoPass = codificador.encode(entrada.getPassword());
	    System.out.print(" + RestLogonController usuarioPorAutorizaracion Password encriptado: " + CryptoPass + "\n \n");

	    // -------------------  Instancia de objetos
    	AnsLogonOut Respuesta = new AnsLogonOut();
    	
    	SysUsuarios Usuario = new SysUsuarios();
    	
    	VigenciaToken vToken = new VigenciaToken();
    	
	    // -------------------  carga de objetos
    	Respuesta.setCr("00");
    	Optional<SysUsuarios> respuestaSysUsuario = repAppUser.findByClave(bDUsuario);
    	
    	if (respuestaSysUsuario.isPresent()) {
	    	Usuario = respuestaSysUsuario.get();
	    	//Valida si la empresa existe y/o esta activa.
	    	if(empresa.findByClave(Usuario.getIdEmpresa()).isPresent())
	    	{
		    	//Valida si el recinto existe y/o esta activa.
	    		if(recinto.findByClave(Usuario.getIdEmpresa(),Usuario.getIdRecinto()).isPresent()) 
	    		{
		    	System.out.print(" + RestLogonController usuarioPorAutorizaracion Usuario: " + Usuario.getIdUsuario() + " \n");                        
		    	Double intento = Usuario.getIntentos().doubleValue();
		 //* validación de password
		    	
		    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    	String existingPassword = pass;
		    	String dbPassword       = Usuario.getPassword();
		
		    	System.out.print(" + RestLogonController usuarioPorAutorizaracion existingPassword: " + existingPassword  +  " -- dbPassword :" + dbPassword +" \n \n");
		
		    	if (existingPassword.matches(dbPassword) && intento < 3) {
		    		if(perfil.findByClave(Usuario.getIdEmpresa(),Usuario.getIdRecinto(),Usuario.getIdPerfil()).isPresent()) {
			        	System.out.print(" + RestLogonController usuarioPorAutorizaracion Match!!");
			        	Respuesta.setAuthenticated(true);
		
			        		vToken.setFecha(ZonedDateTime.now());
			        		vToken.setToken("TOKEN");
			        		System.out.print("Secuencia de vigencia ID: "+vToken.getId()); 
			           		System.out.print("Secuencia de vigencia TOKEN: "+vToken.getToken()); 
			           		System.out.print("Secuencia de vigencia FECHA: "+vToken.getFecha()); 
			           	        		
			        		vigencia.save(vToken);
			        		System.out.print("Secuencia de vigencia: "+vToken.getId()); 
			        		Respuesta.setDescripcion("Acceso usuario Autorizado");
			        		String token = Jwts.builder()
			        				.setSubject(usuarioPorAutorizar)
			        				.setId(Integer.toString(vToken.getId()))
			        				.signWith(SignatureAlgorithm.HS512,"0neProj3ct")
			        				.compact();
			        		Respuesta.setToken("Bearer" + token);
			        		Respuesta.setIdEmpresa(String.valueOf(Usuario.getIdEmpresa()));
			        		Respuesta.setIdRecinto(String.valueOf(Usuario.getIdRecinto()));
			        		Respuesta.setIdUsuario(String.valueOf(Usuario.getIdUsuario()));
			        		Respuesta.setEstadoUsuario(Usuario.getEstatus());
			        		Respuesta.setIdPerfil(Usuario.getIdPerfil());
			        		Usuario.setIntentos(BigDecimal.valueOf(0));
			        		repAppUser.save(Usuario);
			        		// Se da de alta la vigencia del token
			        		vToken.setToken(token);
			        		vigencia.save(vToken);
			    		} else {
	            			Respuesta.setCr("07");
	            			Respuesta.setDescripcion("no se encuentra perfile para el usuario");			    			
			    		}
		        	} else {
				        	System.out.print(" + RestLogonController usuarioPorAutorizaracion No Match!!");
				        	Double intentos = Usuario.getIntentos().doubleValue();
				        	intentos++;
				        	if (intentos > 2)  	{
				        		Usuario.setIntentos(BigDecimal.valueOf(intentos));
				            	repAppUser.save(Usuario);
				            	Respuesta.setCr("01");
				            	Respuesta.setDescripcion("Usuario bloqueado");
				            	Respuesta.setBloqueado(true);
				        	}
				        	else      	{
				        		Respuesta.setCr("02");
				        		Respuesta.setDescripcion("Usuario/Password erroneo");
				        		Usuario.setIntentos(BigDecimal.valueOf(intentos));
				            	repAppUser.save(Usuario);
				            }
				    	 } 
		    			} else  {
            			Respuesta.setCr("04");
            			Respuesta.setDescripcion("La recinto no existe o no está activo");
            			} 
            	}  else  {
            			Respuesta.setCr("05");
            			Respuesta.setDescripcion("La empresa no existe o no está activa");
            			} 
    		} else {
    				Respuesta.setCr("03");
    				Respuesta.setDescripcion("Usuario/Password erroneo");
    		}
    	return Respuesta;
	}
}
