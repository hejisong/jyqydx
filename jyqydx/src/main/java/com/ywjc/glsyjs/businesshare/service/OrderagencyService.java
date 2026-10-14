package com.ywjc.glsyjs.businesshare.service;


import com.ywjc.glsyjs.businesshare.entity.Orderagency;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderagencyService extends BaseService<Orderagency, String> {

    public List<Orderagency> findByordermanid(String ordermanid);

    public Page<Orderagency> findPageByordercompanyid(String ordercompanyid,String orderman, Pageable pageable);

    public Page<Orderagency> findByagencymanid(String agencymanid,String orderman,String ordercompany, Pageable pageable);

    public Page<Orderagency> findOrderagencys(String orderman,String ordercompany, Pageable pageable);

    public List<Orderagency> findByordercompanyid(String ordercompanyid);

}
