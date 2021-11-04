package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysRecintos;

public interface IMSysRecinRepo  extends JpaRepository<SysRecintos, BigDecimal> {

	@Query("select m from SysRecintos m where m.idEmpresa =:empresa and m.idRecinto =:recinto and m.estatus = 'A'")
	Optional<SysRecintos> findByClave(@Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);

	@Query("select m from SysRecintos m where m.idEmpresa =:empresa and m.idRecinto =:recinto and m.estatus = 'A'")
	SysRecintos findByRecinto(@Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);

	@Query("select distinct(m.idRecinto) from SysRecintos m where m.estatus = 'A'")
	List<String> findByUnicos();

}
