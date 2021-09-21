package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysAduPart;
import mx.com.oneproject.spco.modelo.SysAduPartId;

public interface IMSysAduPartRepo extends JpaRepository<SysAduPart, SysAduPartId>{

	@Query("select count(*) from SysAduPart m")
	long countByTodo();

	@Query("select m from SysAduPart m order by m.IdCliProv, m.numPart, m.numPedimento")
	List<SysAduPart> BuscarByTodo();
	
	@Query("select m from SysAduPart m where m.IdCliProv = :pato and m.numPart = :parte and m.numPedimento = :pedimento ")
	List<SysAduPart> findByLlave(@Param("pato") String pato, @Param("parte") String parte, @Param("pedimento") String pedimento);

}
