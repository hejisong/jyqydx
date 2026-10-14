package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Corporatetaxinformation;
import com.ywjc.glsyjs.bigdata.entity.Groupinformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CorporatetaxinformationRepository extends PagingAndSortingRepository<Corporatetaxinformation, String> {


    @Query("select c from Corporatetaxinformation c where (c.companyname like ?1 or c.uscreditcode like ?1) and c.fullname like ?2 and c.tax like ?3  and  (c.state <>'0' or c.state is null) order by c.registration_date desc  ")
    public Page<Corporatetaxinformation> queryPageinfo(String companyname, String fullname, String tax, Pageable pageable);
	
}
