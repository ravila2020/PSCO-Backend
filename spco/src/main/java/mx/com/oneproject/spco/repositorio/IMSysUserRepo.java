package mx.com.oneproject.spco.repositorio;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.modelo.SysUsuariosId;

public interface IMSysUserRepo extends JpaRepository<SysUsuarios, SysUsuariosId> {

	@Query("select m from SysUsuarios m where m.idUsuario like :clave and m.estatus = 'A'")
	Optional<SysUsuarios> findByClave(@Param("clave") BigDecimal clave);
}
