package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.oneproject.spco.modelo.SysDec;
import mx.com.oneproject.spco.modelo.SysDecId;

public interface IMSysDecRepo extends JpaRepository<SysDec, SysDecId>{
}
