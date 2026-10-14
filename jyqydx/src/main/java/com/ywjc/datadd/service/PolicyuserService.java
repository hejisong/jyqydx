package com.ywjc.datadd.service;


import com.ywjc.datadd.entity.Policyuser;
import com.ywjc.glsyjs.core.service.BaseService;

public interface PolicyuserService extends BaseService<Policyuser, String> {

    public Policyuser findUser(String name,String mobile);

}
