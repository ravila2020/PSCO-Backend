package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.CliDupla;
import mx.com.oneproject.spco.modelo.SysCatCli;	

public interface IMSysCatCliRepo extends JpaRepository<SysCatCli, String> {

	@Query("select distinct (m.Tipo) from SysCatCli m ")
	List<String> findByDistTipos();	
	/*
	@Query("select count(*) from SysCatCli m where m.tipo = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	long countByTipos(@Param("tipo") String tipo, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);
	
	@Query("select m        from SysCatCli m where m.tipo = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatCli> findByTipos(@Param("tipo") String tipo, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);
*/

	@Query("select count(*) from SysCatCli m where m.Tipo = :tipo")
	long countByTipos(@Param("tipo") String tipo);
	
	@Query("select m        from SysCatCli m where m.Tipo = :tipo")
	List<SysCatCli> findByTipos(@Param("tipo") String tipo);

	@Query("select distinct(m.IdCliProv) from SysCatCli m")
	List<String> findByClientesUnicos();
	
	@Query("select n from SysCatCli n where n.IdCliProv in (select distinct(m.IdCliProv) from SysCatCli m)")
	List<SysCatCli> findByClientesNombresUnicos();

	@Query("select count(*) from SysCatCli m where m.Tipo = :tipo and m.Empresa = :empresa and m.Recinto = :recinto")
	long countByCliTipos(@Param("tipo") String tipo,@Param("empresa") String empresa, @Param("recinto") String recinto);
	
	@Query("select m        from SysCatCli m where m.Tipo = :tipo and m.Empresa = :empresa and m.Recinto = :recinto")
	List<SysCatCli> findByCliTipos(@Param("tipo") String tipo,@Param("empresa") String empresa, @Param("recinto") String recinto);


	
}
