package com.ywjc.glsyjs.account.service.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import com.ywjc.glsyjs.account.entity.UserRole;
import com.ywjc.glsyjs.account.repository.UserRoleRepository;
import com.ywjc.glsyjs.core.util.Constantval;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.repository.RoleRepository;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;


@Service("roleServiceImpl")
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements
		RoleService {
	
	
	@Resource(name = "roleRepository")
	private RoleRepository repository;

	@Resource(name = "userRoleRepository")
	private UserRoleRepository userRoleRepository;
	
	@Resource(name = "roleRepository")
	public void setReposity(PagingAndSortingRepository<Role, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Role> findByIdIn(String[] roleIdArr) {
		return repository.findByIdIn(roleIdArr);
	}

	@Override
	@Transactional(readOnly = true)
	public Role findByRolename(String rolename) {
		return repository.findByRolename(rolename);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findByUserid(String userid) {
		return repository.findByUserid(userid);
	}
	
	@Override
	@Transactional(readOnly = false)
	public List<Role> findUserroles(String userid){
		return repository.findUserroles(userid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll(){
		return super.findAll();		
	}
	
	@Override
	@Transactional
	public String saveRole(Role role){
		super.save(role);
		return "success";	
	}
	
	@Override
	public Role setRoleid(Role role) {
		String id = UUID.randomUUID().toString();
		id = id.replace("-", "");
		role.setId(id);
		return role;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Role findById(String id){
		return super.find(id);		
	}
	
	@Transactional
	public void updateRole(Role role){
		super.update(role);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Role findByRolecode(String rolecode){
		return repository.findByRolecode(rolecode);
	}


	@Override
	@Transactional(readOnly = true)
	public List<Role> findAllRoles(){
		return repository.findAllRoles();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> findAllNotAdminRoles(){
		return repository.findAllNotAdminRoles();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> findRoles(){
		return repository.findRoles();
	}
	

	@Override
	public void updateUserRole(String role_id,String user_id){
		repository.updateUserRole(role_id,user_id);
	}

	@Override
	public void deleteByUserRole(String user_id){
		repository.deleteByUserRole(user_id);
	}

	@Override
	public void inserUserRole(String user_id,String role_id){
		repository.inserUserRole(user_id,role_id);
	}

	@Override
	public List<UserRole> findUserRoles(String user_id){
      return userRoleRepository.findUserRoles(user_id);
	}

}
