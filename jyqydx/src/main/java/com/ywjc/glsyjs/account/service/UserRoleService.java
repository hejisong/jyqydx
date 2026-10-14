package com.ywjc.glsyjs.account.service;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.UserRole;
import com.ywjc.glsyjs.core.service.BaseService;

import java.util.List;
import java.util.Set;

public interface UserRoleService extends BaseService<UserRole, String> {

	public void updateUserRole(String role_id,String user_id);

	public void inserUserRole(String user_id,String role_id);

	public void deleteByUserRole(String user_id);

	public List<UserRole> findUserRoles(String user_id);

	public UserRole findUserRole(String user_id,String role_id);

}
