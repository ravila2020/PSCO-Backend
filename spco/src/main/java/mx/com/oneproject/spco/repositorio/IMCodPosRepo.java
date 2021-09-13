package mx.com.oneproject.spco.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.CodPost;
import mx.com.oneproject.spco.modelo.CodPostId;

public interface IMCodPosRepo extends JpaRepository<CodPost, CodPostId>{
	
	@Query("select distinct (m.dEstado) from CodPost m where  m.cEstado = :claveEdo")
	String findByClaveEstado(@Param("claveEdo") String claveEdo);

	@Query("select distinct (m.dCiudad) from CodPost m where  m.dCodigo = :codigo and  m.cMnpio = :claveMunicipio and  m.cEstado = :claveEdo")
	String findByClaveCd(@Param("codigo") String codigo, @Param("claveMunicipio") String claveMunicipio, @Param("claveEdo") String claveEdo);
//                                                                                 getMunicipio() +              getLocalidad() +                 getEstado()
//          select distinct (m.D_mnpio) from cod_post2 m where   m.[d_codigo] = '20020' and m.[c_mnpio] = '001' and m.[id_asenta_cpcons] = '0016' and  m.[c_estado] = '01'
//**	@Query("select distinct (m.dMnpio)  from CodPost   m where   m.dCodigo =    :codigo and m.cMnpio = :claveCd and m.idAsentaCpcons = :claveMpio and  m.cEstado = :claveEdo")
//**	String findByClaveMpio(@Param("codigo") String codigo, @Param("claveMpio") String claveMpio, @Param("claveCd") String claveCd, @Param("claveEdo") String claveEdo);

	@Query("select distinct (m.dMnpio) from CodPost m where  m.dCodigo = :codigo and  m.cMnpio = :claveMunicipio and  m.cEstado = :claveEdo and m.idAsentaCpcons = :cLocalidad")
	String findByClaveMpio(@Param("codigo") String codigo, @Param("claveMunicipio") String claveMunicipio, @Param("claveEdo") String claveEdo, @Param("cLocalidad") String cLocalidad);

	@Query("select m from CodPost m where  m.dCodigo = :codigo")
	Optional<CodPost> findByCodigo(@Param("codigo") String codigo);

	@Query("select m from CodPost m where  m.dCodigo = :codigo and m.idAsentaCpcons between '0000' and '9999'")
	List<CodPost> findByCodigoMult(@Param("codigo") String codigo);
}
