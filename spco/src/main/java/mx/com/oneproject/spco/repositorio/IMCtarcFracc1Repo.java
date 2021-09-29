package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.ctarcFracc1;

public interface IMCtarcFracc1Repo extends JpaRepository<ctarcFracc1, String>{

	@Query("select m.factor from CatConversion m where m.iDUMO = :umOrigen and m.iDUMD = :umDestino")
	Float findByiDUMOAndiDUMD(@Param("umOrigen") String umOrigen, @Param("umDestino") String umDestino);
	
}
