package com.ywjc.glsyjs.account.repository;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.UserRole;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, String> {




	@Query(value="update USER_ROLE set role_id= ?1 where user_id = ?2 ",nativeQuery=true)
	@Modifying
	void updateUserRole(String role_id,String user_id);


	@Query(value="delete from  USER_ROLE  where user_id = ?1 ",nativeQuery=true)
	@Modifying
	void deleteByUserRole(String user_id);


	@Query(value="insert into  USER_ROLE (user_id,role_id) values (?1,?2) ",nativeQuery=true)
	@Modifying
	@Transactional
	void inserUserRole(String user_id,String role_id);


	@Query("select u from UserRole u where u.user_id = ?1 ")
	public List<UserRole> findUserRoles(String user_id);


	@Query("select u from UserRole u where u.user_id = ?1 and u.role_id = ?2 ")
	public UserRole findUserRole(String user_id,String role_id);


}
