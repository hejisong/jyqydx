package com.ywjc.glsyjs.interfaces.flow.service;


import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.flow.entity.Flowauditinfo;

import java.util.List;

public interface FlowauditinfoService extends BaseService<Flowauditinfo, String> {

    public List<Flowauditinfo> findByauditid(String auditid);

    public Flowauditinfo findByauditidone(String auditid,String deptname);

}
