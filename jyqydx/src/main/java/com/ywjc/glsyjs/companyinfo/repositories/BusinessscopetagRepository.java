package com.ywjc.glsyjs.companyinfo.repositories;


import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface BusinessscopetagRepository extends PagingAndSortingRepository<Businessscopetag, String> {


    @Query("select b from Businessscopetag b where b.businessscope like ?1  order by b.id ")
    public Page<Businessscopetag> queryPage(String businessscope, Pageable pageable);


    @Query(value="select DISTINCT businessscope from flags ",nativeQuery=true)
    public List<String> findscope();


    @Query("select b from Businessscopetag b where b.businessscope = ?1   ")
    public Businessscopetag findByscope(String businessscope);
	
}
