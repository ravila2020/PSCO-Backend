package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mx.com.oneproject.spco.modelo.DetCatAp;

public interface IMDetCatApRepo extends JpaRepository<DetCatAp, String> {

	@Query("select m from DetCatAp m where m.clvap like :clave and m.delLogico = 0")
	List<DetCatAp> findByClave(@Param("clave") String clave);
	
	@Transactional
	List<DetCatAp> findByClvap(@Param("clvap") String clvap);
	
	@Query("select m from DetCatAp m where m.clvap = :clave and id1 = :val1 and id2 = :val2")
	DetCatAp findByCampos(@Param("clave") String clave,@Param("val1") String val1,@Param("val2") String val2);

	
	
	@Query("select count(*) from DetCatAp m where m.clvap = :clave and m.delLogico = 0")
	   long countByActivos(@Param("clave") String clave);
	
//	@Query("select m from DetCatAp m where m.clvap = :clave and id1 = :val1 and id2 = :val2")
//	List<DetCatAp> findByActivos(@Param("clave") String clave,@Param("val1") String val1,@Param("val2") String val2);
}

