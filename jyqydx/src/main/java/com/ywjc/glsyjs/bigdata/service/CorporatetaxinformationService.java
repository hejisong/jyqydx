package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Corporatetaxinformation;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CorporatetaxinformationService extends BaseService<Corporatetaxinformation, String> {


    public Page<Corporatetaxinformation> queryPageinfo(String companyname, String fullname, String tax, Pageable pageable);

}
