package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysEmpresa;

public interface IMSysEmpRepo extends JpaRepository<SysEmpresa, BigDecimal> {

	@Query("select m        from SysEmpresa m where m.idEmpresa = :clave and  m.estatus = 'A'")
	Optional<SysEmpresa> findByClave(@Param("clave") BigDecimal clave);
}
