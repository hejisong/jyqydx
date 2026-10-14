package com.ywjc.glsyjs.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.account.entity.Loginlog;

public interface LoginlogRepository extends PagingAndSortingRepository<Loginlog, String> {

	/**
	 * @param datee
	 * @return
	 */
	@Query("select u from Loginlog u where to_char( u.logintime,'yyyy-mm-dd') = ?1 ")
	List<Loginlog> findLoginlogByDate(String datee);

	@Query(value = "select u.* from Loginlog u where u.userid = ?1  and u.onoff='1' order by u.logintime desc limit 2 ",nativeQuery = true)
	public List<Loginlog> findLogs(String userid);

	@Query(value = "select u.* from Loginlog u where u.userid = ?1 order by u.logintime desc limit 2 ",nativeQuery = true)
	public List<Loginlog> findLogsOnline(String userid);
	
}
