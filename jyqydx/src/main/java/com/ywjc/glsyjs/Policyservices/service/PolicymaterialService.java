package com.ywjc.glsyjs.Policyservices.service;


import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.core.service.BaseService;

public interface PolicymaterialService extends BaseService<Policymaterial, String> {

    public Policymaterial findBypolicyfile(String policyfileid,String nplcategory,String policyyear);

}
