package com.ywjc.glsyjs.Policyservices.repositories;


import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PolicymaterialRepository extends PagingAndSortingRepository<Policymaterial, String> {

    @Query("select p from Policymaterial p where p.policyfileid = ?1 and p.nplcategory =?2 and p.policyyear=?3  ")
    public Policymaterial findBypolicyfile(String policyfileid,String nplcategory,String policyyear);
}
