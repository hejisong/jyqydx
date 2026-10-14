package com.ywjc.glsyjs.interfaces.tag.service;


import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccuratetagrelationService extends BaseService<Accuratetagrelation, String> {


    Page<Object> findPage(String companyname,String tagyear,String tagname, Pageable pageable);

    Accuratetagrelation findByCorp(String cid,String tagid,String tagyear);

    void updateCompanyid(String cid,String companyid);

}
