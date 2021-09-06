package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysCatCli;	

public interface IMSysCatCliRepo extends JpaRepository<SysCatCli, String> {

	@Query("select distinct (m.tipo) from SysCatCli m ")
	List<String> findByDistTipos();	
	/*
	@Query("select count(*) from SysCatCli m where m.tipo = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	long countByTipos(@Param("tipo") String tipo, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);
	
	@Query("select m        from SysCatCli m where m.tipo = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatCli> findByTipos(@Param("tipo") String tipo, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);
*/

	@Query("select count(*) from SysCatCli m where m.tipo = :tipo")
	long countByTipos(@Param("tipo") String tipo);
	
	@Query("select m        from SysCatCli m where m.tipo = :tipo")
	List<SysCatCli> findByTipos(@Param("tipo") String tipo);


}
