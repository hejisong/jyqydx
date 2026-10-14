package com.ywjc.glsyjs.interfaces.plycomresult.service;

import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;

import java.util.List;

public interface PlyfileEntityService extends BaseService<PlyfileEntity, String> {


    //查询
    public List<PlyfileEntity> findPlyfileEntityByPldtailid(String pldtailid);


    public void updateMaterial(List<Policymateriallist> materiallists, List<PolicydetailEntity> details);

    public void updatePlyfile(List<Policymateriallist> materiallists,PolicydetailEntity detail);


}
