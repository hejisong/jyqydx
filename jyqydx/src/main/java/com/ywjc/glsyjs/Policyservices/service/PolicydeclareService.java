package com.ywjc.glsyjs.Policyservices.service;


import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface PolicydeclareService extends BaseService<Policydeclare, String> {


    public Page<Policydeclare> queryPageinfo(String declarename, Date startdate, Date enddate, String state, Pageable pageable);

}
