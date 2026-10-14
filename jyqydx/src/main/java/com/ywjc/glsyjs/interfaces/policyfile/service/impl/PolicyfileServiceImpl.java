package com.ywjc.glsyjs.interfaces.policyfile.service.impl;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import com.ywjc.glsyjs.interfaces.policyfile.repositories.PolicyfileRepository;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicyfileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("policyfileServiceImpl")
public class PolicyfileServiceImpl extends BaseServiceImpl<Policyfile, String> implements
        PolicyfileService {

    @Resource(name = "policyfileRepository")
    private PolicyfileRepository policyfileRepository;

    @Resource(name = "policyfileRepository")
    public void setReposity(PagingAndSortingRepository<Policyfile, String> policyfileRepository) {
        super.setReposity(policyfileRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Policyfile> queryPolicyfileList(String policyname, String beginDate, String endDate, String fileLevel, String fileType) {
        return policyfileRepository.queryPolicyfileList(policyname, beginDate, endDate, fileLevel, fileType);
    }

    @Override
    public Page<Policyfile> queryPageinfo(String policyname, String content,String filelevel,String state, Pageable pageable){


        return policyfileRepository.queryPageinfo(policyname,content,filelevel,state,pageable);
    }

    @Override
    public List<Policyfile> queryPolicyfileBytag(String tagid){
        return policyfileRepository.queryPolicyfileBytag(tagid);
    }

    @Override
    public String counttotal(){
        return policyfileRepository.counttotal();
    }
    @Override
    public  List<Policyfile> totalAll(){
        return policyfileRepository.totalAll();
    }

    @Override
    public  List<Policyfile> totalAllTwo(){
        return policyfileRepository.totalAllTwo();
    }

    @Override
    public String findMaxNum(){
        return policyfileRepository.findMaxNum();
    }

    @Override
    public List<Object[]> queryPolicyfileAll(String policyname, String content, String filelevel,String state){
        if(StringUtils.isBlank(state)){
            return policyfileRepository.queryPolicyfileAll( policyname,  content,  filelevel);
        }
        return policyfileRepository.queryPolicyfileAllState( policyname,  content,  filelevel,state);
    }


}
