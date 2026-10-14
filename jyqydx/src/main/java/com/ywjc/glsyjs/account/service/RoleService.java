package com.ywjc.glsyjs.account.service;

import java.util.List;
import java.util.Set;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.UserRole;
import com.ywjc.glsyjs.core.service.BaseService;

public interface RoleService extends BaseService<Role, String> {

	Set<Role> findByIdIn(String[] roleIdArr);

	Role findByRolename(String rolename);
	
	public List<Role> findByUserid(String userid);
	
	List<Role> findAll();
	
	String saveRole(Role role);
	
	Role setRoleid(Role role);
	
	Role findById(String id);
	
	void updateRole(Role role);
	
	public Role findByRolecode(String rolecode);
	

	public List<Role> findUserroles(String userid);
	
	public List<Role> findAllRoles();
	
	public List<Role> findAllNotAdminRoles();
	
	public List<Role> findRoles();
	

	public void updateUserRole(String role_id,String user_id);

	public void inserUserRole(String user_id,String role_id);

	public void deleteByUserRole(String user_id);

	public List<UserRole> findUserRoles(String user_id);
}
