package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.modelo.SysUsuariosId;

public interface IMSysUserRepo extends JpaRepository<SysUsuarios, SysUsuariosId> {

	@Query("select m        from SysUsuarios m where m.idUsuario like :clave and m.estatus = 'A'")
	Optional<SysUsuarios> findByClave(@Param("clave") BigDecimal clave);

	@Query("select m        from SysUsuarios m where m.idUsuario = :clave and m.estatus = 'A'")
	SysUsuarios findByUsuario(@Param("clave") BigDecimal clave);
	
	@Query("select count(*) from SysUsuarios m where m.estatus = 'A'")
	   long countByActivos();
	
	@Query("select m        from SysUsuarios m where m.estatus = 'A'")
	List<SysUsuarios> findByClave();

	@Query("select count(*) from SysUsuarios m where m.estatus = 'A' and m.idRecinto between :inferior and :superior")
	   long countByRecinto(@Param("inferior") BigDecimal linInf, @Param("superior") BigDecimal clavelimSup);

	@Query("select m        from SysUsuarios m where m.estatus = 'A' and m.idRecinto between :inferior and :superior")
	List<SysUsuarios> findByRecinto(@Param("inferior") BigDecimal linInf, @Param("superior") BigDecimal clavelimSup);
}
