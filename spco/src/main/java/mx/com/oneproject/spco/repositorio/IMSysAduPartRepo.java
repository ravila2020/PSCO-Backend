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
	
	@Query("select m from SysAduPart m where m.IdCliProv = :cliente and m.numPart = :parte and m.numPedimento = :pedimento ")
	List<SysAduPart> findByLlave(@Param("cliente") String cliente, @Param("parte") String parte, @Param("pedimento") String pedimento);

	@Query("select m from SysAduPart m where m.IdCliProv = :cliente and m.numPart = :parte and m.numPedimento = :pedimento ")
	SysAduPart findByLlaveUnica(@Param("cliente") String cliente, @Param("parte") String parte, @Param("pedimento") String pedimento);

	@Query("select distinct(m.IdCliProv) from SysAduPart m")
	List<String> findByClientesUnicos();

}
