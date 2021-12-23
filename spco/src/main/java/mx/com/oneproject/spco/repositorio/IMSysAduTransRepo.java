package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysAduTrans;
import mx.com.oneproject.spco.modelo.SysAduTransId;

public interface IMSysAduTransRepo extends JpaRepository<SysAduTrans, SysAduTransId>  {

	@Query("select m from SysAduTrans m  where m.IdCliProv = :cliente")
	List<SysAduTrans> BuscarByCliente(@Param("cliente") String cliente);

}
