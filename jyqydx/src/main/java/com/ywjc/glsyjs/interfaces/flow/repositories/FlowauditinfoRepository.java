package com.ywjc.glsyjs.interfaces.flow.repositories;


import com.ywjc.glsyjs.interfaces.flow.entity.Flowauditinfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface FlowauditinfoRepository extends PagingAndSortingRepository<Flowauditinfo, String> {


    @Query(value = "select f from Flowauditinfo f where f.auditid = ?1 order by f.audittime nulls last ")
    public List<Flowauditinfo> findByauditid(String auditid);


    @Query(value = "select f from Flowauditinfo f where f.auditid = ?1 and f.deptname =?2 ")
    public Flowauditinfo findByauditidone(String auditid,String deptname);
	
}
