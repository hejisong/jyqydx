package com.ywjc.glsyjs.interfaces.company.repositories;


import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;


public interface CompanypatentRepository extends PagingAndSortingRepository<Companypatent, String> {

    @Query("select c from Companypatent c where c.patentyear =?1 and (c.state is null or c.state <>'0') order by c.patentacquisitime desc ")
    public List<Companypatent> findByYear(String patentyear);


    @Query("select c from Companypatent c where c.patentyear is null or c.patentyear='' ")
    public List<Companypatent> findNoYear();

    @Query("select c from Companypatent c where c.companyid is null or c.companyid='' ")
    public List<Companypatent> findNoCompanyid();


    @Query("select c from Companypatent c where c.applicationnum =?1 and (c.state is null or c.state <>'0') ")
    public  List<Companypatent> findByapplicationnum(String applicationnum);


    @Query("select c from Companypatent c where c.patentname like ?1 and c.enterprisename like ?2   and c.applicationnum like ?3 and c.applicationtime >= ?4 and c.applicationtime <= ?5 and (c.state is null or c.state <>'0') order by c.applicationtime desc ")
    public Page<Companypatent> queryPageinfo(String patentname, String enterprisename, String applicationnum, Date apply_startdate, Date apply_enddate , Pageable pageable);

    @Query("select c from Companypatent c where c.patentname like ?1 and c.enterprisename like ?2   and c.applicationnum like ?3 and c.applicationtime>= ?4  and (c.state is null or c.state <>'0') order by c.applicationtime desc  ")
    public Page<Companypatent> queryPageinfoStartdate(String patentname, String enterprisename, String applicationnum, Date apply_startdate,  Pageable pageable);

    @Query("select c from Companypatent c where c.patentname like ?1 and c.enterprisename like ?2   and c.applicationnum like ?3 and c.applicationtime= ?4 and (c.state is null or c.state <>'0') order by c.applicationtime desc ")
    public Page<Companypatent> queryPageinfoEnddate(String patentname, String enterprisename, String applicationnum,Date apply_enddate , Pageable pageable);

    @Query("select c from Companypatent c where c.patentname like ?1 and c.enterprisename like ?2   and c.applicationnum like ?3 and (c.state is null or c.state <>'0') order by c.applicationtime desc ")
    public Page<Companypatent> queryPageinfoAll(String patentname, String enterprisename, String applicationnum, Pageable pageable);


    @Query("select c from Companypatent c where c.patentid =?1  ")
    public Companypatent findBypatentid(String patentid);

    @Query("select c from Companypatent c where c.pubannomentno =?1  ")
    public Companypatent findBypubannomentno(String pubannomentno);
}
