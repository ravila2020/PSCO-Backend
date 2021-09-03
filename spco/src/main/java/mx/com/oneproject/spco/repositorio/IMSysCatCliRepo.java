package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.com.oneproject.spco.modelo.SysCatCli;

public interface IMSysCatCliRepo extends JpaRepository<SysCatCli, String> {

	@Query("select distinct (m.Tipo) from SysCatCli m ")
	List<String> findByTipo();	
	
}
