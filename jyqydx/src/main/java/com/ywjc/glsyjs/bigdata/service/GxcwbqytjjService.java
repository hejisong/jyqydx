package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface GxcwbqytjjService extends BaseService<Gxcwbqytjj, String> {


    public Page<Gxcwbqytjj> queryPageinfo( String companyname,String year, Double minsale_income, Double maxsale_income, Pageable pageable);

    public Gxcwbqytjj findByYear(String  companyid, String year);

    public Gxcwbqytjj findBycompanyid(String  companyid);

}
