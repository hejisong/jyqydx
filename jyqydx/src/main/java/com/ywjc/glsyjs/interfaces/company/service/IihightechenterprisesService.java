package com.ywjc.glsyjs.interfaces.company.service;


import com.ywjc.glsyjs.bigdata.entity.Corporatetaxinformation;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IihightechenterprisesService extends BaseService<Iihightechenterprises, String> {

    public List<Iihightechenterprises> findByYear(String recognitionyear);

    public List<Iihightechenterprises> findBycompanyid(String companyid);

    public Iihightechenterprises findBycertificateno(String certificateno);

    public Page<Iihightechenterprises> queryPageinfo(String companyname, String certificateno, String recognitionyear, Pageable pageable);


    public List<Iihightechenterprises> findNoCompanyid();

}
