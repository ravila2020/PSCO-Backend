package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysCatProd;

public interface IMSysCatProdRepo extends JpaRepository<SysCatProd, String>{

	@Query("select distinct (m.tipProd) from SysCatProd m ")
	List<String> findByTipo();	
	
	@Query("select count(*) from SysCatProd m where m.tipProd = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	long countByTipo(@Param("tipo") String tipo, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);
	
	@Query("select m        from SysCatProd m where m.tipProd = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatProd> findByTipo(@Param("tipo") String tipo, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);

	@Query("select m        from SysCatProd m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	   SysCatProd   findByProducto(@Param("clave") String clave, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);

	@Query("select m        from SysCatProd m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatProd> findByClave(@Param("clave") String clave, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);

	@Query("select count(*) from SysCatProd m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	long countByClave(@Param("clave") String clave, @Param("empresa") BigDecimal empresa, @Param("recinto") BigDecimal recinto);

}
