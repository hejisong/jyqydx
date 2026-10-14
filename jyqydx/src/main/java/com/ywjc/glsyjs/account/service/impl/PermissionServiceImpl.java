package com.ywjc.glsyjs.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.sys.entity.Role_permission;
import com.ywjc.glsyjs.sys.service.Role_permissionService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ywjc.glsyjs.account.entity.Permission;
import com.ywjc.glsyjs.account.repository.PermissionRepository;
import com.ywjc.glsyjs.account.service.PermissionService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;


@Service("permissionServiceImpl")
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String>
		implements PermissionService {
		
	
	@Resource(name = "role_permissionServiceImpl")
	private Role_permissionService role_permissionService;
	
	@Resource(name = "permissionRepository")
	private PermissionRepository repository;
	
	@Resource(name = "permissionRepository")
	public void setReposity(
			PagingAndSortingRepository<Permission, String> repository) {
		super.setReposity(repository);
	}
	
	@Override
	@Transactional
	public void delete(String id) {
		super.delete(id);
	}
	
	@Override
	@Transactional(readOnly = true)	
	public List<Permission> findAllPermissions(){
		return repository.findAllPermissions();
	}
	@Override
	@Transactional(readOnly = true)
	public List<Permission> findUserPermissions(String id) {
		return repository.findUserPermissions(id);
	}

	@Override
	@Transactional
	public void savePermission(Permission permission, List<Role_permission> role_permissionlist) {
		save(permission);
		role_permissionService.addRole_permissionlistByPermission(permission.getId(), role_permissionlist);
	}

	@Override
	@Transactional
	public void updatePermission(Permission permission, List<Role_permission> role_permissionlist) {
		update(permission);
		role_permissionService.addRole_permissionlistByPermission(permission.getId(), role_permissionlist);
	}

	@Override
	@Transactional
	public void deletePermission(String id) {
		delete(id);
		role_permissionService.deleteByPermission_id(id);
	}
}
