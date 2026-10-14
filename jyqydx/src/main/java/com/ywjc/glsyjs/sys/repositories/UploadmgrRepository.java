package com.ywjc.glsyjs.sys.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Uploadmgr;

public interface UploadmgrRepository extends PagingAndSortingRepository<Uploadmgr, String> {
	
	@Query("select u from Uploadmgr u where u.infoid=?1 and u.infotype=?2 and u.year=?3 and u.actiontype=?4")
	public Uploadmgr findmgr(String infoid, String infotype, String year,String actiontype);
	
	
}
