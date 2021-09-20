package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysCatProducto;

public interface IMSysCatProductoRepo extends JpaRepository<SysCatProducto, String>{


	@Query("select distinct (m.tipProd) from SysCatProducto m ")
	List<String> findByTipo();	
	
	@Query("select count(*) from SysCatProducto m where m.tipProd = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	long countByTipo(@Param("tipo") String tipo, @Param("empresa") String empresa, @Param("recinto") String recinto);
	
	@Query("select m        from SysCatProducto m where m.tipProd = :tipo and m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatProducto> findByTipo(@Param("tipo") String tipo, @Param("empresa") String empresa, @Param("recinto") String recinto);

	@Query("select m        from SysCatProducto m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	SysCatProducto   findByProducto(@Param("clave") String clave, @Param("empresa") String empresa, @Param("recinto") String recinto);

	@Query("select m        from SysCatProducto m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatProducto> findByClave(@Param("clave") String clave, @Param("empresa") String empresa, @Param("recinto") String recinto);

	@Query("select m        from SysCatProducto m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	SysCatProducto findByClaves(@Param("clave") String clave, @Param("empresa") String empresa, @Param("recinto") String recinto);
	
	@Query("select count(*) from SysCatProducto m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	long countByClave(@Param("clave") String clave, @Param("empresa") String empresa, @Param("recinto") String recinto);

	@Modifying
	@Query("delete          from SysCatProducto m where m.clveProduc = :clave and m.empresa = :empresa and m.recinto = :recinto")
	int  deleteByProducto(@Param("clave") String clave, @Param("empresa") String empresa, @Param("recinto") String recinto);


}
