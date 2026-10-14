package com.ywjc.glsyjs.interfaces.company.repositories;


import com.ywjc.glsyjs.interfaces.company.entity.Corpbusinessscope;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface CorpbusinessscopeRepository extends PagingAndSortingRepository<Corpbusinessscope, String> {

    @Query("select c from Corpbusinessscope c where c.companyid=?1 and  c.businessscope =?2 ")
    public Corpbusinessscope findByScorp(String companyid ,String scorp);

    @Query("select c from Corpbusinessscope c where c.companyid=?1  ")
    public List<Corpbusinessscope> findByScorpBycompanyid(String companyid);
	
}
