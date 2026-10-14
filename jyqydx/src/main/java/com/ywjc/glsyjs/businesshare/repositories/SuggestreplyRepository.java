package com.ywjc.glsyjs.businesshare.repositories;


import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface SuggestreplyRepository extends PagingAndSortingRepository<Suggestreply, String> {

    @Query("select s from Suggestreply s where s.suggestid =?1 order by s.replytime  ")
    public List<Suggestreply> findBySuggest(String suggestid);
	
}
