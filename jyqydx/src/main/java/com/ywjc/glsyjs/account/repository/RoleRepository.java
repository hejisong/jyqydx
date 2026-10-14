package com.ywjc.glsyjs.account.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.account.entity.Role;
import org.springframework.transaction.annotation.Transactional;


public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

	Set<Role> findByIdIn(String[] roleIdArr);

	Role findByRolename(String rolename);
	
	@Query(value="select r.* from Role r inner join USER_ROLE ur on r.id = ur.ROLE_ID where r.isforbidden = '0' and ur.user_id=?1",nativeQuery=true)
	public List<Role> findByUserid(String userid);
	
	public Role findByRolecode(String rolecode);
	
	@Query(value="select r.* from Role r inner join USER_ROLE ur on r.id = ur.ROLE_ID where r.isforbidden = '0'  and ur.user_id=?1",nativeQuery=true)
	public List<Role> findUserroles(String userid);
	
	@Query("select r from Role r where r.isforbidden='0' order by r.roletype desc")
	public List<Role> findAllRoles();
	
	@Query("select r from Role r where r.isforbidden = '0'  and r.rolecode <> 'SYS_ADMIN' order by r.roletype desc")
	public List<Role> findAllNotAdminRoles();
	
	@Query("select r from Role r where r.isforbidden = '0'  order by r.modify_Date desc")
	public List<Role> findRoles();


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




	
//	@Query("select r from Role r where r.rolecode in ?1 amd r.isforbidden = '0' ")
//	public List<Role> findRolesByRolecode(List<String> rolecode);
}
