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

}
