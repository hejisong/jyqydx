package com.ywjc.glsyjs.interfaces.company.service;


import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CompanypatentService extends BaseService<Companypatent, String> {

    public List<Companypatent> findByYear(String patentyear);

    public List<Companypatent> findNoYear();

    public List<Companypatent> findNoCompanyid();

    public Page<Companypatent> queryPageinfo(String patentname, String enterprisename, String applicationnum, Date applystartdate, Date applyenddate , Pageable pageable);


    public Companypatent findBypatentid(String patentid);

    public Companypatent findBypubannomentno(String pubannomentno);

}
