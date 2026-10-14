package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Groupinformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface GroupinformationRepository extends PagingAndSortingRepository<Groupinformation, String> {

    @Query("select g from Groupinformation g where (g.group_name like ?1 or g.group_abbreviation like ?1) and g.pcompany_name like ?2 and g.registration_number like ?3  and  (g.state <>'0' or g.state is null) order by g.establishdate desc  ")
    public Page<Groupinformation> queryPageinfo(String companyname, String pcompany_name, String registrationnumber, Pageable pageable);
	
}
