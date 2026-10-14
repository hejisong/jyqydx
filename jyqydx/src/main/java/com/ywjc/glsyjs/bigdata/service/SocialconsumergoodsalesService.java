package com.ywjc.glsyjs.bigdata.service;


import com.ywjc.glsyjs.bigdata.entity.Socialconsumergoodsales;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SocialconsumergoodsalesService extends BaseService<Socialconsumergoodsales, String> {


    public Page<Socialconsumergoodsales> queryPageinfo(String companyname,String year,  Double min_sales, Double max_sales, Pageable pageable);

}
