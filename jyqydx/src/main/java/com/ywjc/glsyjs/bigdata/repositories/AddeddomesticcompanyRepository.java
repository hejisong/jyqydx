package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Addeddomesticcompany;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;


public interface AddeddomesticcompanyRepository extends PagingAndSortingRepository<Addeddomesticcompany, String> {






    @Query("select c from Addeddomesticcompany c where c.company_name like ?1 and c.stock_code like ?2   and c.region like ?3 and c.listing_date >= ?4 and c.listing_date <= ?5 and (c.state is null or c.state <>'0') order by c.listing_date desc ")
    public Page<Addeddomesticcompany> queryPageinfo(String company_name, String stock_code, String region, Date startdate, Date enddate , Pageable pageable);

    @Query("select c from Addeddomesticcompany c where c.company_name like ?1 and c.stock_code like ?2   and c.region like ?3 and c.listing_date>= ?4  and (c.state is null or c.state <>'0') order by c.listing_date desc  ")
    public Page<Addeddomesticcompany> queryPageinfoStartdate(String company_name, String stock_code, String region, Date startdate,  Pageable pageable);

    @Query("select c from Addeddomesticcompany c where c.company_name like ?1 and c.stock_code like ?2   and c.region like ?3 and c.listing_date= ?4 and (c.state is null or c.state <>'0') order by c.listing_date desc ")
    public Page<Addeddomesticcompany> queryPageinfoEnddate(String company_name, String stock_code, String region,Date enddate , Pageable pageable);

    @Query("select c from Addeddomesticcompany c where c.company_name like ?1 and c.stock_code like ?2   and c.region like ?3 and (c.state is null or c.state <>'0') order by c.listing_date desc ")
    public Page<Addeddomesticcompany> queryPageinfoAll(String company_name, String stock_code, String region,  Pageable pageable);
	
}
