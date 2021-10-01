package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.SysAduFact;
import mx.com.oneproject.spco.modelo.SysAduFactId;

public interface IMSysAduFactRepo extends JpaRepository<SysAduFact, SysAduFactId> {

}
