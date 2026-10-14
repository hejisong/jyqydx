package com.ywjc.glsyjs.account.service;

import java.util.List;

import com.ywjc.glsyjs.account.entity.Permission;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Role_permission;

public interface PermissionService extends BaseService<Permission, String> {
	
	public List<Permission> findUserPermissions(String id);

	List<Permission> findAllPermissions();

	public void savePermission(Permission permission, List<Role_permission> role_permissionlist);

	public void updatePermission(Permission permission, List<Role_permission> role_permissionlist);

	public void deletePermission(String id);
}
