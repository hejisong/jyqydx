package com.ywjc.glsyjs.sys.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Generalcodename;

public interface GeneralcodenameRepository extends
		PagingAndSortingRepository<Generalcodename, String> {
	public Page<Generalcodename> findByCodetablenameLikeAndCodetabledescLikeAndIsusedLike(
			String codetablename, String codetabledesc, String isused,
			Pageable pageable);

	public Generalcodename findByCodetablename(String codetablename);
	
	@Query(value = "select max(codetableid) from generalcodename", nativeQuery = true)
	public Long findMaxNo();
	
	@Query("select i from Generalcodename i where  i.codetabledesc like ?1 ")
	public List<Generalcodename> findCities(String codetabledesc);
}
