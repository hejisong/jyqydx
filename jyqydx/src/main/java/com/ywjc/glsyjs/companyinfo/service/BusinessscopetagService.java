package com.ywjc.glsyjs.companyinfo.service;


import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BusinessscopetagService extends BaseService<Businessscopetag, String> {



    public Page<Businessscopetag> queryPage(String businessscope, Pageable pageable);

    public List<String> findscope();

    public Businessscopetag findByscope(String businessscope);

}
