package com.ywjc.datashare.repositories;


import com.ywjc.datashare.entity.Personinfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersoninfoRepository extends PagingAndSortingRepository<Personinfo, String> {


    @Query(value = "select * from ctwrite.t_person  ", nativeQuery = true)
    public List<Personinfo> findListAll();

    @Query(value = "select * from ctwrite.t_person where personid=?1  ", nativeQuery = true)
    public Personinfo findByid(String personid);
}
