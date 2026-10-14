package com.ywjc.glsyjs.account.service.impl;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.UserRole;
import com.ywjc.glsyjs.account.repository.RoleRepository;
import com.ywjc.glsyjs.account.repository.UserRoleRepository;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserRoleService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service("userRoleServiceImpl")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String> implements
		UserRoleService {
	
	
	@Resource(name = "userRoleRepository")
	private UserRoleRepository repository;


	
	@Resource(name = "userRoleRepository")
	public void setReposity(PagingAndSortingRepository<UserRole, String> repository) {
		super.setReposity(repository);
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
      return repository.findUserRoles(user_id);
	}

	public UserRole findUserRole(String user_id,String role_id){
		return repository.findUserRole(user_id,role_id);
	}

}
