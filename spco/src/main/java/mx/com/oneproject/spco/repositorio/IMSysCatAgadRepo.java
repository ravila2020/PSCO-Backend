package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysCatAgad;
import mx.com.oneproject.spco.modelo.SysCatAgad;

public interface IMSysCatAgadRepo extends JpaRepository<SysCatAgad, String>{

	
	@Query("select count(*) from SysCatAgad m where m.empresa = :empresa and m.recinto = :recinto")
	long countByER(@Param("empresa") String empresa, @Param("recinto") String recinto);
	
	@Query("select m        from SysCatAgad m where m.empresa = :empresa and m.recinto = :recinto")
	List<SysCatAgad> findByER(@Param("empresa") String empresa, @Param("recinto") String recinto);
}
