package com.ywjc.glsyjs.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Role_permission;
import com.ywjc.glsyjs.sys.entity.Role_permissionPK;

public interface Role_permissionRepository extends PagingAndSortingRepository<Role_permission, Role_permissionPK> {
 	@Query("select e from Role_permission e where e.role_id = ?1")
	List<Role_permission> findByRole_id(String role_id);
	@Query("select e from Role_permission e where e.permission_id = ?1")
	List<Role_permission> findByPermission_id(String permission_id);
}
