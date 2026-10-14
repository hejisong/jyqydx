package com.ywjc.glsyjs.Policyservices.repositories;


import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface PolicymateriallistRepository extends PagingAndSortingRepository<Policymateriallist, String> {


    @Query("select p from Policymateriallist p where p.materialid = ?1 order by p.sn  ")
    public List<Policymateriallist> findBymaterialid(String materialid);
	
}
