package com.ywjc.glsyjs.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ywjc.glsyjs.account.entity.Userbf;
import com.ywjc.glsyjs.core.repository.BaseRepository;



public interface UserbfRepository extends BaseRepository<Userbf, String> {

	@Query("select u from Userbf u where u.loginname = ?1  ")
	public List<Userbf> findbyloginname(String loginname);

	@Query(value="delete from Userinfobf where loginname = ?1 ",nativeQuery=true)
	@Modifying
	void deleteByloginname(String loginname);

	
}
