package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.SysUsuarios;
import mx.com.oneproject.spco.modelo.SysUsuariosId;

public interface IMSysUserRepo extends JpaRepository<SysUsuarios, SysUsuariosId> {

}
