package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Legalpersonsblacklist;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LegalpersonsblacklistService extends BaseService<Legalpersonsblacklist, String> {

    public Page<Legalpersonsblacklist> queryPageinfo(String company_name, String legalpersons, String legalcardid, Pageable pageable);

}
