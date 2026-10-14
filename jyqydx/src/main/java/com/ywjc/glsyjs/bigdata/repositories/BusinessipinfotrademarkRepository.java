package com.ywjc.glsyjs.bigdata.repositories;


import com.ywjc.glsyjs.bigdata.entity.Businessipinfotrademark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;


public interface BusinessipinfotrademarkRepository extends PagingAndSortingRepository<Businessipinfotrademark, String> {


    @Query("select b from Businessipinfotrademark b where b.trademarkname like ?1 and b.companyname like ?2  and b.trademarkname<>'-'   and  (b.deletestate <>'0' or b.deletestate is null) order by b.apply_date desc ")
    public Page<Businessipinfotrademark> queryPageinfoAll(String trademarkname, String companyname, Pageable pageable);


    @Query("select b from Businessipinfotrademark b where b.trademarkname like ?1 and b.companyname like ?2 and b.apply_date >= ?3  and b.trademarkname<>'-'   and  (b.deletestate <>'0' or b.deletestate is null)  order by b.apply_date desc ")
    public Page<Businessipinfotrademark> queryPageinfoStartdate(String trademarkname, String companyname, Date applystartdate, Pageable pageable);


    @Query("select b from Businessipinfotrademark b where b.trademarkname like ?1 and b.companyname like ?2  and b.apply_date <= ?3  and b.trademarkname<>'-'  and  (b.deletestate <>'0' or b.deletestate is null)  order by b.apply_date desc ")
    public Page<Businessipinfotrademark> queryPageinfoEnddate(String trademarkname, String companyname,  Date applyenddate, Pageable pageable);

    @Query("select b from Businessipinfotrademark b where b.trademarkname like ?1 and b.companyname like ?2 and b.apply_date >= ?3  and  b.apply_date <= ?4 and b.trademarkname<>'-'   and  (b.deletestate <>'0' or b.deletestate is null)  order by b.apply_date desc ")
    public Page<Businessipinfotrademark> queryPageinfo(String trademarkname, String companyname, Date applystartdate, Date applyenddate, Pageable pageable);


    @Query("select b from Businessipinfotrademark b where b.trademarkid = ?1   ")
    public Businessipinfotrademark findBytrademarkid(String trademarkid);
	
}
