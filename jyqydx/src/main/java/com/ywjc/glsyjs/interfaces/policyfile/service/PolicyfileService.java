package com.ywjc.glsyjs.interfaces.policyfile.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PolicyfileService extends BaseService<Policyfile, String> {

    //查询
    public List<Policyfile> queryPolicyfileList(String policyname,String beginDate,String endDate,String fileLevel,String fileType);


    public Page<Policyfile> queryPageinfo(String policyname, String content, String filelevel,String state, Pageable pageable);

    //根据标签查原文
    public List<Policyfile> queryPolicyfileBytag(String tagid);

    public String counttotal();

    public String findMaxNum();

    public  List<Policyfile> totalAll();

    public  List<Policyfile> totalAllTwo();


    public List<Object[]> queryPolicyfileAll(String policyname, String content, String filelevel,String state);

}
