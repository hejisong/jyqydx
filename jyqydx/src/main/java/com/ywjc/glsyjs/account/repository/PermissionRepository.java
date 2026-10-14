package com.ywjc.glsyjs.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.account.entity.Permission;



public interface PermissionRepository extends
		PagingAndSortingRepository<Permission, String> {
	
	@Query(value="select distinct p.* from permission p inner join ROLE_PERMISSION rp on p.id = rp.PERMISSION_ID inner join USER_ROLE ur on rp.ROLE_ID = ur.ROLE_ID where ur.USER_ID = ?1", nativeQuery=true)
	public List<Permission> findUserPermissions(String id);
	@Query("select p from Permission p order by createdate asc ")
	public List<Permission> findAllPermissions();
}
