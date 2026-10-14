package com.ywjc.glsyjs.Policyservices.repositories;


import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

public interface PolicydeclareRepository extends PagingAndSortingRepository<Policydeclare, String> {







    @Query("select p from Policydeclare p where p.declarename like ?1  and p.declare_start_date >= ?2  and p.state = ?3 order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoStartdate(String declarename, Date startdate,  String state, Pageable pageable);

    @Query("select p from Policydeclare p where p.declarename like ?1  and p.declare_end_date <= ?2  and p.state = ?3 order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoenddate(String declarename, Date enddate,  String state, Pageable pageable);

    @Query("select p from Policydeclare p where p.declarename like ?1  and p.state = ?2 order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfo(String declarename,String state, Pageable pageable);

    @Query("select p from Policydeclare p where p.declarename like ?1  and p.declare_start_date >= ?2 and p.declare_end_date <= ?3 and p.state = ?4 order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoAll(String declarename, Date startdate, Date enddate, String state, Pageable pageable);


    @Query("select p from Policydeclare p where p.declarename like ?1  and p.declare_start_date >= ?2  and p.state <> '0' order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoStartdateNostate(String declarename, Date startdate,  Pageable pageable);

    @Query("select p from Policydeclare p where p.declarename like ?1  and p.declare_end_date <= ?2  and p.state <> '0' order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoenddateNostate(String declarename, Date enddate,   Pageable pageable);

    @Query("select p from Policydeclare p where p.declarename like ?1   and p.state <> '0' order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoNostate(String declarename,Pageable pageable);

    @Query("select p from Policydeclare p where p.declarename like ?1  and p.declare_start_date >= ?2 and p.declare_end_date <= ?3 and p.state <> '0' order by p.releasedate desc ")
    public Page<Policydeclare> queryPageinfoAllNostate(String declarename, Date startdate, Date enddate,Pageable pageable);
	
}
