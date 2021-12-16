package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.modelo.SysAduFactGB;
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

	@Modifying
	@Query("delete  from SysAduFact m  where m.IdCliProv = :cliente and m.numPart = :parte and m.numFact = :factura")
	int  BorradoByProducto(@Param("cliente") String cliente, @Param("parte") String parte, @Param("factura") String factura);

	@Query("select sum(m.cantidad) from SysAduFact m  where m.numPart = :parte and m.producto = :producto and m.iDImpoEexpo = :indicador")
	Integer contarPartes(@Param("parte") String parte, @Param("producto") String producto, @Param("indicador") String indicador);

	@Query("select m from SysAduFact m  where m.numPart = :parte and m.producto = :producto and m.iDImpoEexpo = :indicador")
	SysAduFact contarPartesFis(@Param("parte") String parte, @Param("producto") String producto, @Param("indicador") String indicador);
	
	@Query("select m.IdCliProv, m.numPart, m.numPedimentoEntrada, m.producto, m.iDImpoEexpo, sum(m.cantidad) as cantidad from SysAduFact m " +
		       "where m.IdCliProv = :cliente and m.iDImpoEexpo = :indicador and m.producto = :producto group by m.IdCliProv, m.numPart, m.numPedimentoEntrada, m.producto," + 
			   " m.iDImpoEexpo ")
		List<Object[]> contarPartesEntSal(@Param("cliente") String cliente, @Param("producto") String producto, @Param("indicador") String indicador);

	@Query("select m.IdCliProv, m.numPart, m.numPedimentoEntrada, m.producto, m.iDImpoEexpo from SysAduFact m " +
		       "where m.IdCliProv = :cliente and m.iDImpoEexpo = :indicador and m.producto = :producto group by m.IdCliProv, m.numPart, m.numPedimentoEntrada, m.producto," + 
			   " m.iDImpoEexpo ")
		List<SysAduFactGB> contarPartesEntSal2(@Param("cliente") String cliente, @Param("producto") String producto, @Param("indicador") String indicador);

	@Procedure(procedureName = "ParteCliente")
    int parteCliente();

	@Procedure(procedureName = "ParteClienteInv")
    int parteClienteInv();
	
	@Procedure(procedureName = "TraspasoCliente")
    int traspasoCliente();
	

}
