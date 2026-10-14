package com.ywjc.glsyjs.interfaces.flow.service.impl;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowEntity;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.flow.repositories.FlowEntityRepository;
import com.ywjc.glsyjs.interfaces.flow.service.FlowEntityService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("flowEntityServiceImpl")
public class FlowEntityServiceImpl extends BaseServiceImpl<FlowEntity, String> implements
        FlowEntityService {

    @Resource(name = "flowEntityRepository")
    private FlowEntityRepository repository;

    @Resource(name = "flowEntityRepository")
    public void setReposity(PagingAndSortingRepository<FlowEntity, String> repository) {
        super.setReposity(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public FlowEntity queryBeginFlowByType(String type) {
        return repository.queryBeginFlowByType(type);
    }

    @Override
    public List<FlowEntity> queryFlowListBytype(String type){
        return repository.queryFlowListBytype(type);
    }


    @Override
    @Transactional(readOnly = true)
    public FlowEntity queryCurrentFlowById(int id) {
        return repository.queryCurrentFlowById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FlowEntity queryNextFlowByNextid(int nextid) {
        return repository.queryNextFlowByNextid(nextid);

    }

    @Override
    public FlowEntity queryFlowBytypeandname(String type,String flowname){
        return repository.queryFlowBytypeandname(type,flowname);
    }

}
