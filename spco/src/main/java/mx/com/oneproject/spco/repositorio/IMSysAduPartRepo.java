package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysAduPart;
import mx.com.oneproject.spco.modelo.SysAduPartId;

public interface IMSysAduPartRepo extends JpaRepository<SysAduPart, SysAduPartId>{

	@Query("select count(*) from SysAduPart m")
	long countByTodo();

	@Query("select count(*) from SysAduPart m where m.IdCliProv = :cliente and m.recinto = :recinto ")
	long countByTodoCteRecinto(@Param("cliente") String cliente, @Param("recinto") String recinto);

	@Query("select count(*) from SysAduPart m where m.empresa = :empresa and m.recinto = :recinto")
	long countByTodoER(@Param("empresa") String empresa, @Param("recinto") String recinto);

	@Query("select m from SysAduPart m order by m.IdCliProv, m.numPart, m.numPedimento")
	List<SysAduPart> BuscarByTodo();

	@Query("select m from SysAduPart m where m.empresa = :empresa and m.recinto = :recinto order by m.IdCliProv, m.numPart, m.numPedimento")
	List<SysAduPart> BuscarByTodoER(@Param("empresa") String empresa, @Param("recinto") String recinto);

	
	@Query("select m from SysAduPart m where m.IdCliProv = :cliente and m.recinto = :recinto order by m.IdCliProv, m.numPart, m.numPedimento")
	List<SysAduPart> BuscarByCteRecinto(@Param("cliente") String cliente, @Param("recinto") String recinto);
	
	@Query("select m from SysAduPart m where m.IdCliProv = :cliente and m.numPart = :parte and m.numPedimento = :pedimento ")
	List<SysAduPart> findByLlave(@Param("cliente") String cliente, @Param("parte") String parte, @Param("pedimento") String pedimento);

	@Query("select m from SysAduPart m where m.IdCliProv = :cliente and m.numPart = :parte and m.numPedimento = :pedimento ")
	SysAduPart findByLlaveUnica(@Param("cliente") String cliente, @Param("parte") String parte, @Param("pedimento") String pedimento);

	@Modifying
	@Query("delete          from SysAduPart m where m.IdCliProv = :cliente and m.numPart = :parte and m.numPedimento = :pedimento ")
	int BorraByLlaveUnica(@Param("cliente") String cliente, @Param("parte") String parte, @Param("pedimento") String pedimento);

	@Query("select distinct(m.IdCliProv) from SysAduPart m")
	List<String> findByClientesUnicos();

}
