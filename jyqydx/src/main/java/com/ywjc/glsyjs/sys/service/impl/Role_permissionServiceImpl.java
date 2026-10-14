package com.ywjc.glsyjs.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Role_permission;
import com.ywjc.glsyjs.sys.entity.Role_permissionPK;
import com.ywjc.glsyjs.sys.repositories.Role_permissionRepository;
import com.ywjc.glsyjs.sys.service.Role_permissionService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("role_permissionServiceImpl")
public class Role_permissionServiceImpl extends BaseServiceImpl<Role_permission, Role_permissionPK>
		implements Role_permissionService {

	@Resource(name = "role_permissionRepository")
	private Role_permissionRepository repository;


	@Resource(name = "role_permissionRepository")
	public void setReposity(
			PagingAndSortingRepository<Role_permission, Role_permissionPK> repository) {
		super.setReposity(repository);
	}

 

	@Override
	@Transactional(readOnly = true)
 	public List<Role_permission> findAll() {
		return super.findAll();
	}
 
 

	@Override
	@Transactional
 	public void save(Role_permission entity) {
		super.save(entity);
	}
 
 

	@Override
	@Transactional
 	public void delete(Role_permission entity) {
		super.delete(entity);
	}
	@Override
  	public List<Role_permission>  findByRole_id(String role_id) {
		return repository.findByRole_id(role_id);
	}
	@Override
	public List<Role_permission> findByPermission_id(String permission_id) {
 		return repository.findByPermission_id(permission_id); 
	}  	
	@Override
  	public void deleteByRole_id(String role_id) {
		List<Role_permission> permissionlistold=repository.findByRole_id(role_id);
		if(permissionlistold!=null&&permissionlistold.size()>0){
			for(Role_permission rolepermission:permissionlistold)
			delete(rolepermission);
		}
 	}
	
	@Override
  	public void deleteByPermission_id(String permission_id) {
		List<Role_permission> permissionlistold=repository.findByPermission_id(permission_id);
		if(permissionlistold!=null&&permissionlistold.size()>0){
			for(Role_permission rolepermission:permissionlistold)
			delete(rolepermission);
		}
 	}
	
	
	@Override
	@Transactional 	
  	public void addRole_permissionlistByRole(String role_id ,List<Role_permission> role_permissionlist) {
		deleteByRole_id(role_id);
		if(role_permissionlist!=null&&role_permissionlist.size()>0){
			for(Role_permission role_permission:role_permissionlist)
			save(role_permission);
		}
 	}
	
	
	@Override
   	public void addRole_permissionlistByPermission(String permission_id ,List<Role_permission> role_permissionlist) {
		deleteByPermission_id(permission_id);
		if(role_permissionlist!=null&&role_permissionlist.size()>0){
			for(Role_permission role_permission:role_permissionlist)
			save(role_permission);
		}
 	}




}
