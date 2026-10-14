package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface GxcwbqytjjRepository extends PagingAndSortingRepository<Gxcwbqytjj, String> {


    @Query("select g from Gxcwbqytjj g where g.companyname like ?1 and g.year like ?2 and g.sale_income >= ?3 and  g.sale_income <= ?4  and (g.state <>'0' or g.state is null)  order by g.year desc  ")
    public Page<Gxcwbqytjj> queryPageinfo(String companyname, String year, Double minsale_income, Double maxsale_income, Pageable pageable);


    @Query("select g from Gxcwbqytjj g where g.companyname like ?1 and g.year like ?2 and g.sale_income >= ?3  and  (g.state <>'0' or g.state is null) order by g.year desc  ")
    public Page<Gxcwbqytjj> queryPageinfoMin( String companyname,String year, Double minsale_income, Pageable pageable);

    @Query("select g from Gxcwbqytjj g where g.companyname like ?1 and g.year like ?2 and g.sale_income <= ?3  and  (g.state <>'0' or g.state is null) order by g.year desc  ")
    public Page<Gxcwbqytjj> queryPageinfoMax( String companyname,String year, Double maxsale_income, Pageable pageable);

    @Query("select g from Gxcwbqytjj g where g.companyname like ?1 and g.year like ?2  and  (g.state <>'0' or g.state is null) order by g.year desc  ")
    public Page<Gxcwbqytjj> queryPageinfoAll( String companyname,String year, Pageable pageable);

    @Query("select g from Gxcwbqytjj g where g.companyid = ?1 and g.year = ?2 and g.state <>'0' ")
    public Gxcwbqytjj findByYear(String  companyid,String year);


    @Query("select g from Gxcwbqytjj g where g.companyid = ?1  and g.state <>'0' ")
    public Gxcwbqytjj findBycompanyid(String  companyid);
	
}
