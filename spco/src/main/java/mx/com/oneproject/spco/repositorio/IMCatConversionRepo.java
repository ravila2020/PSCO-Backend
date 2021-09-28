package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.CatConversion;
import mx.com.oneproject.spco.modelo.CodPost;

public interface IMCatConversionRepo extends JpaRepository<CatConversion, String>{

	@Query("select m.factor from CatConversion m where  m.iDUMO = :iDUMO and m.iDUMD = :iDUMD")
	Float findByDual(@Param("iDUMO") String iDUMO, @Param("iDUMD") String iDUMD);
}
