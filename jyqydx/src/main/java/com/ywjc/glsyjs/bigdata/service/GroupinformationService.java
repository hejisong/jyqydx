package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Groupinformation;
import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupinformationService extends BaseService<Groupinformation, String> {

    public Page<Groupinformation> queryPageinfo(String groupname, String pcompanyname, String registrationnumber, Pageable pageable);

}
