package mx.com.oneproject.spco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.oneproject.spco.modelo.AppUser;
import mx.com.oneproject.spco.modelo.Permission;

public interface IMPermissionRepo extends JpaRepository<Permission,Integer>{

	@Transactional	
	   void deleteById(Integer Id);
	 
	@Transactional	
		Permission save(Permission x);
}
