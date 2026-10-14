package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Businessipinfotrademark;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface BusinessipinfotrademarkService extends BaseService<Businessipinfotrademark, String> {


    public Page<Businessipinfotrademark> queryPageinfo(String trademarkname, String companyname, Date applystartdate,Date applyenddate, Pageable pageable);


    public Businessipinfotrademark findBytrademarkid(String trademarkid);

}
