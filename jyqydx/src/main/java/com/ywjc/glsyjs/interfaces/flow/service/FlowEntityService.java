package com.ywjc.glsyjs.interfaces.flow.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowEntity;

import java.util.List;

public interface FlowEntityService extends BaseService<FlowEntity, String> {

    //查询首节点
    public FlowEntity queryBeginFlowByType(String type);

    //查询当前节点
    public FlowEntity queryCurrentFlowById(int id);
    public List<FlowEntity> queryFlowListBytype(String type);

    //查询下一节点
    public FlowEntity queryNextFlowByNextid(int nextid);

    public FlowEntity queryFlowBytypeandname(String type,String flowname);

}
