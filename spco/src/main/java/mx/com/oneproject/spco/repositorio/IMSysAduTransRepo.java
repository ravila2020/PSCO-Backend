package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysAduTrasp;
import mx.com.oneproject.spco.modelo.SysAduTraspId;

public interface IMSysAduTransRepo extends JpaRepository<SysAduTrasp, SysAduTraspId>  {

	@Query("select m from SysAduTrasp m  where m.IdCliProv = :cliente")
	List<SysAduTrasp> BuscarByCliente(@Param("cliente") String cliente);

	@Query("select count(*)  from SysAduTrasp m  where m.IdCliProv = :cliente")
	Long ContarByCliente(@Param("cliente") String cliente);

	@Query("select m from SysAduTrasp m  where m.IdCliProv = :cliente and  m.numPart = :parte and  m.numFact = :factura and  m.numPedimentoEntrada = :pedimento and  m.empresa = :empresa and  m.recinto = :recinto")
	SysAduTrasp BuscarByLlave(@Param("cliente") String cliente, 
			                  @Param("parte") String parte,
			                  @Param("factura") String factura,
			                  @Param("pedimento") String pedimento,
			                  @Param("empresa") String empresa,
			                  @Param("recinto") String recinto);

	
	@Query("select distinct(m.numPart) from SysAduTrasp m  where m.IdCliProv = :cliente and m.empresa = :emp and m.recinto = :rec  ")
	List<String> BuscarByCliERP(@Param("cliente") String cliente,  @Param("emp") String emp, @Param("rec") String rec);

	@Query("select distinct(m.numFact) from SysAduTrasp m  where m.IdCliProv = :cliente and m.empresa = :emp and m.recinto = :rec  ")
	List<String> BuscarByCliERF(@Param("cliente") String cliente,  @Param("emp") String emp, @Param("rec") String rec);
	

}
