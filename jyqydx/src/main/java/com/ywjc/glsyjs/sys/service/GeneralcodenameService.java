package com.ywjc.glsyjs.sys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Generalcodename;

public interface GeneralcodenameService extends
		BaseService<Generalcodename, String> {
	public Page<Generalcodename> findByCodetablenameLikeAndCodetabledescLikeAndIsusedLike(
			String codetablename, String codetabledesc, String isused,
			Pageable pageable);

	public Generalcodename findByCodetablename(String codetablename);
	
	public Long findMaxNo();
	
	public List<Generalcodename> findCities(String codetabledesc);

	public List<Object[]> findCitiesAll();

}
