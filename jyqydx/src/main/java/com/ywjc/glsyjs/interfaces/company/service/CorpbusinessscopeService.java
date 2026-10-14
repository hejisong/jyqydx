package com.ywjc.glsyjs.interfaces.company.service;


import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.Corpbusinessscope;

import java.util.List;

public interface CorpbusinessscopeService extends BaseService<Corpbusinessscope, String> {

    public Corpbusinessscope findByScorp(String companyid ,String scorp);


    public List<Corpbusinessscope> findByScorpBycompanyid(String companyid);

}
