package com.ywjc.glsyjs.interfaces.company.repositories;


import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface IihightechenterprisesRepository extends PagingAndSortingRepository<Iihightechenterprises, String> {

    @Query("select i from Iihightechenterprises i where i.recognitionyear =?1 and  (i.state <>'0' or i.state is null)  order by i.recognitionyear desc,i.certificateno desc ")
    public List<Iihightechenterprises> findByYear(String recognitionyear);



    @Query("select i from Iihightechenterprises i where i.companyid =?1 and  (i.state <>'0' or i.state is null) order by i.recognitionyear desc,i.certificateno desc ")
    public List<Iihightechenterprises> findBycompanyid(String companyid);

    @Query("select i from Iihightechenterprises i where i.certificateno =?1  ")
    public Iihightechenterprises findBycertificateno(String certificateno);


    @Query("select i from Iihightechenterprises i where i.enterprisename like ?1 and i.certificateno like ?2 and i.recognitionyear like ?3 and  (i.state <>'0' or i.state is null)  order by i.recognitionyear desc,i.certificateno desc ")
    public Page<Iihightechenterprises> queryPageinfo(String companyname, String certificateno, String recognitionyear, Pageable pageable);


    @Query("select i from Iihightechenterprises i where i.companyid is null or  i.companyid='' ")
    public List<Iihightechenterprises> findNoCompanyid();

}