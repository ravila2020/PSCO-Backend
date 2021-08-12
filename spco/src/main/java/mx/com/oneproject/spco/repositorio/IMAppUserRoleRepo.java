package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.oneproject.spco.modelo.AppUserRole;
import mx.com.oneproject.spco.modelo.AppUserRoleId;

public interface IMAppUserRoleRepo extends JpaRepository<AppUserRole, AppUserRoleId>{

	
	@Transactional	
	List<AppUserRole> findAllByAppUserId(Integer RolId);

//	@Transactional	
//	Optional<AppUserRole> findAllByAppUserId(Integer RolId);	
	
	@Transactional	
	List<AppUserRole> findAllByRoleId(Integer RolId);
	
	@Transactional	
	   void deleteByAppUserId(Integer AppUserId);
	
}
