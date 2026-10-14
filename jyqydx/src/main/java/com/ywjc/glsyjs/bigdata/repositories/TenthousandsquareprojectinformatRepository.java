package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Tenthousandsquareprojectinformat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TenthousandsquareprojectinformatRepository extends PagingAndSortingRepository<Tenthousandsquareprojectinformat, String> {

    @Query("select t from Tenthousandsquareprojectinformat t where t.companyname like ?1  and t.years like ?2 and t.name_of_fair like ?3  and  (t.state <>'0' or t.state is null) order by t.exhibition_time desc  ")
    public Page<Tenthousandsquareprojectinformat> queryPageinfo(String companyname, String year, String name_of_fair, Pageable pageable);
	
}
