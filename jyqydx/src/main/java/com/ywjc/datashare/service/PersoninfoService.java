package com.ywjc.datashare.service;


import com.ywjc.datashare.entity.Personinfo;
import com.ywjc.glsyjs.core.service.BaseService;

import java.util.List;

public interface PersoninfoService extends BaseService<Personinfo, String> {
    public List<Personinfo> findListAll();

    public Personinfo findByid(String personid);
}
