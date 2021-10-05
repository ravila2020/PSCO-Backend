package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.modelo.SysAduFactId;

public interface IMSysAduFactRepo extends JpaRepository<SysAduFact, SysAduFactId> {

	@Query("select count(*) from SysAduFact m")
	long countByTodo();
	
	@Query("select m from SysAduFact m order by m.IdCliProv, m.numPart, m.numFact, m.iDImpoEexpo")
	List<SysAduFact> BuscarByTodo();


	@Query("select count(*) from SysAduFact m where m.IdCliProv = :cliente and m.numPart = :parte")
	long countByTodoCP(@Param("cliente") String cliente, @Param("parte") String parte);


	@Query("select m from SysAduFact m  where m.IdCliProv = :cliente and m.numPart = :parte order by m.IdCliProv, m.numPart, m.numFact, m.iDImpoEexpo")
	List<SysAduFact> BuscarByTodoCP(@Param("cliente") String cliente, @Param("parte") String parte);


	@Query("select count(*) from SysAduFact m where m.IdCliProv = :cliente and m.numPart between :partei and :partef")
	long countByTodoCPB(@Param("cliente") String cliente, @Param("partei") String partei, @Param("partef") String partef);


	@Query("select m from SysAduFact m  where m.IdCliProv = :cliente and m.numPart between :partei and :partef order by m.IdCliProv, m.numPart, m.numFact, m.iDImpoEexpo")
	List<SysAduFact> BuscarByTodoCPB(@Param("cliente") String cliente, @Param("partei") String partei, @Param("partef") String partef);

	@Query("select m from SysAduFact m  where m.IdCliProv = :cliente and m.numPart = :parte and m.numFact = :factura")
	SysAduFact BuscarByLlave(@Param("cliente") String cliente, @Param("parte") String parte, @Param("factura") String factura);

}
