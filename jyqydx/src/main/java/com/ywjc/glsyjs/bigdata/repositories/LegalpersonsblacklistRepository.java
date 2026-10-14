package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Legalpersonsblacklist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface LegalpersonsblacklistRepository extends PagingAndSortingRepository<Legalpersonsblacklist, String> {


    @Query("select l from Legalpersonsblacklist l where (l.company_name like ?1 or l.uscreditcode like ?1)  and l.legalpersons like ?2 and l.legalcardid like ?3  and  (l.state <>'0' or l.state is null) order by l.serious_confirm_date desc  ")
    public Page<Legalpersonsblacklist> queryPageinfo(String company_name, String legalpersons, String legalcardid, Pageable pageable);
	
}
