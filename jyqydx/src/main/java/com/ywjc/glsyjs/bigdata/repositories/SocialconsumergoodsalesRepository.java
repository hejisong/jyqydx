package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Socialconsumergoodsales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface SocialconsumergoodsalesRepository extends PagingAndSortingRepository<Socialconsumergoodsales, String> {


    @Query("select s from Socialconsumergoodsales s where s.companyname like ?1 and s.year like ?2 and s.totall_sales >= ?3 and  s.totall_sales <= ?4  and (s.state <>'0' or s.state is null)  order by s.year desc, s.totall_sales desc  ")
    public Page<Socialconsumergoodsales> queryPageinfo(String companyname,String year, Double min_sales, Double max_sales, Pageable pageable);


    @Query("select s from Socialconsumergoodsales s where s.companyname like ?1 and s.year like ?2 and s.totall_sales >= ?3  and  (s.state <>'0' or s.state is null) order by s.year desc, s.totall_sales desc  ")
    public Page<Socialconsumergoodsales> queryPageinfoMin(String companyname,String year , Double min_sales, Pageable pageable);

    @Query("select s from Socialconsumergoodsales s where s.companyname like ?1 and s.year like ?2 and s.totall_sales <= ?3  and  (s.state <>'0' or s.state is null) order by s.year desc, s.totall_sales desc  ")
    public Page<Socialconsumergoodsales> queryPageinfoMax( String companyname,String year ,  Double max_sales, Pageable pageable);

    @Query("select s from Socialconsumergoodsales s where s.companyname like ?1 and s.year like ?2  and  (s.state <>'0' or s.state is null) order by s.year desc, s.totall_sales desc  ")
    public Page<Socialconsumergoodsales> queryPageinfoAll( String companyname,String year,  Pageable pageable);
	
}
