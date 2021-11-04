package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysPerfiles;

public interface IMSysPerfilRepo extends JpaRepository<SysPerfiles, String>  {

	@Query("select m from SysPerfiles m where m.idEmpresa =:empresa and m.idRecinto =:recinto and m.idPerfil =:perfil")
	Optional<SysPerfiles> findByClave(@Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto, @Param("perfil") String perfil);	

	@Query("select distinct(m.idPerfil) from SysPerfiles m where m.idEmpresa =:empresa and m.idRecinto =:recinto")
	List<String> findByER(@Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);	

}
