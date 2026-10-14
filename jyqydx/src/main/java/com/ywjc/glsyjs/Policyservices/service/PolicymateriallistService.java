package com.ywjc.glsyjs.Policyservices.service;


import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.BaseService;

import java.util.List;

public interface PolicymateriallistService extends BaseService<Policymateriallist, String> {

    public List<Policymateriallist> findBymaterialid(String materialid);

    public Policymaterial saveinfo(Policymaterial policymaterial, User user);

    public String findmaterials(Policymaterial policymaterial);

}
