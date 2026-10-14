package com.ywjc.glsyjs.sys.service;

import java.util.List;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Role_permission;
import com.ywjc.glsyjs.sys.entity.Role_permissionPK;

public interface Role_permissionService extends BaseService<Role_permission, Role_permissionPK> {

	public List<Role_permission>  findByRole_id(String role_id);
	public List<Role_permission>  findByPermission_id(String permission_id);

	void deleteByRole_id(String role_id);
 	void deleteByPermission_id(String permission_id);
  
	void addRole_permissionlistByRole(String role_id, List<Role_permission> role_permissionlist);

	void addRole_permissionlistByPermission(String permission_id, List<Role_permission> role_permissionlist);

}
