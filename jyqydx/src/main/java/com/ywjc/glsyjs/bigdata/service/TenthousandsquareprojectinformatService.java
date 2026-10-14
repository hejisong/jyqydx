package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Tenthousandsquareprojectinformat;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TenthousandsquareprojectinformatService extends BaseService<Tenthousandsquareprojectinformat, String> {


    public Page<Tenthousandsquareprojectinformat> queryPageinfo(String companyname, String year, String name_of_fair, Pageable pageable);
}
