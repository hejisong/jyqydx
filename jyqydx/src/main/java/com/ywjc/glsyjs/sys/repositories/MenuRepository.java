package com.ywjc.glsyjs.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Menu;

public interface MenuRepository extends PagingAndSortingRepository<Menu, String> {
	
	@Query(value ="select menu_id from ROLE_MENU  where role_id = ?1", nativeQuery = true)
	@Modifying
	public List<String> findMenuByroleid(String role_id);
	
	@Query(value ="delete from ROLE_MENU  where role_id = ?1", nativeQuery = true)
	@Modifying
	public void deleteByroleid(String role_id);
	
	@Query(value ="insert into ROLE_MENU (role_id,menu_id) values (?1 , ?2)", nativeQuery = true)
	@Modifying
	public void saveMenu(String role_id,String menu_id);
	
	@Query(value ="delete from menu where id = ?1", nativeQuery = true)
	@Modifying
	public void deleteByid(String id);
	
	@Query(value ="delete from menu where pid = ?1", nativeQuery = true)
	@Modifying
	public void deleteBypid(String id);
	
	@Query("select m from Menu m where m.isforbidden = '0'  order by m.orderno asc")
	public List<Menu> findVaidMenus( );
	
	@Query(value="select m.* from menu m inner join ROLE_MENU rm on m.id = rm.menu_id where m.isforbidden = '0' and rm.ROLE_ID = ?1 order by m.orderno asc",nativeQuery = true)
	public List<Menu> findUserRoleMenus(String roleid);
	
	@Query(value ="update menu set isforbidden = ?1 where id = ?2", nativeQuery = true)
	@Modifying
	public void forbidmenu(String isforbidden,String id);
	
	@Query(value ="select * from menu where pid = ?1 and isforbidden = '0'", nativeQuery = true)
	@Modifying
	public List<Menu> findChildMenuUnforbidden(String pid);
	
	@Query(value ="select distinct m.* from menu m inner join ROLE_MENU rm on m.id = rm.menu_id where rm.role_id in (select role_id from USER_ROLE ur where ur.USER_ID=?1) and m.isforbidden = '0' order by orderno asc", nativeQuery = true)
	public List<Menu> findMenusByUserid(String userid);
	
	@Query("select m from Menu m order by m.orderno asc")
	public List<Menu> findALlMenus( );
	
	@Query(value="select m.* from Menu m where m.isforbidden = '0' order by m.orderno asc",nativeQuery = true)
	public List<Menu> findByMenutype();
	
	@Query(value ="select distinct m.* from menu m inner join ROLE_MENU rm on m.id = rm.menu_id where rm.role_id in ?1 and m.isforbidden = '0' order by orderno asc", nativeQuery = true)
	public List<Menu> findUserMenus(List<String> roleids);
}
