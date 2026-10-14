package com.ywjc.glsyjs.sys.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PolicyFileEntityService extends BaseService<PolicyFileEntity, String> {
    //查询所有政策文件
    public List<PolicyFileEntity> findPolicyFileEntitiesByall();

    public List<PolicyFileEntity> findPolicyFiles();

    public List<PolicyFileEntity> findPolicyFilesByCondition(Date startdate,Date enddate,String level,String content);

    public Page<Object> findPolicyFiles(Date startdate, Date enddate, String level, String tagid, String key,Pageable pageable);

    public List<Object> findPolicyFiles(Date startdate, Date enddate, String level, String content);

    public List<Object> findFiletypeSum();

    public List<Object> findPolicyFilesByFiletype(String filetype);

    public Page<Object> findPolicyFilesByFiletype(String filetype, Pageable pageable);

    public List<PolicyFileEntity> findPolicyFilesByTag(String tagid);

    public Page<PolicyFileEntity> findPolicyFilename(String policyname,Pageable pageable);


    public List<PolicyFileEntity> findByFilelevel(String filelevel);

    public List<Object> findMSfiles();

    public List<Object> findAccuratefiles();


}


