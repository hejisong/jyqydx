package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Addeddomesticcompany;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface AddeddomesticcompanyService extends BaseService<Addeddomesticcompany, String> {



    public Page<Addeddomesticcompany> queryPageinfo(String company_name, String stock_code,String region, Date startdate, Date enddate , Pageable pageable);

}
