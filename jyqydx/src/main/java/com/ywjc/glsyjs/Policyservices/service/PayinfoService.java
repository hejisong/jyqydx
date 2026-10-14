package com.ywjc.glsyjs.Policyservices.service;


import com.ywjc.glsyjs.Policyservices.entity.Payinfo;
import com.ywjc.glsyjs.core.service.BaseService;

public interface PayinfoService extends BaseService<Payinfo, String> {

    Payinfo findbyResultid(String plyresultid);

}
