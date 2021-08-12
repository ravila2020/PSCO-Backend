package mx.com.oneproject.spco.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.oneproject.spco.modelo.RolePermission;
import mx.com.oneproject.spco.modelo.RolePermissionId;

public interface IMRolePermissionRepo extends JpaRepository<RolePermission, RolePermissionId>{

	
//	@Transactional	
//	public Optional<RolePermission> findByroleId(Integer Id);
	
	
	@Transactional	
	List<RolePermission> findAllByRoleId(Integer RolId);
	
	@Transactional	
	List<RolePermission> deleteByRoleId(Integer RolId);
	
}
