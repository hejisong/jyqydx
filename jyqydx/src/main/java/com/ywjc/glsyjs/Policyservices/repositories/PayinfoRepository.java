package com.ywjc.glsyjs.Policyservices.repositories;


import com.ywjc.glsyjs.Policyservices.entity.Payinfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PayinfoRepository extends PagingAndSortingRepository<Payinfo, String> {

    @Query("select p from Payinfo p where p.plyresultid = ?1   ")
    public Payinfo findbyResultid(String plyresultid);
	
}
