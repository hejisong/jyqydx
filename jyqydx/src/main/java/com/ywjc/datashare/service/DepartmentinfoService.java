package com.ywjc.datashare.service;


import com.ywjc.datashare.entity.Departmentinfo;
import com.ywjc.glsyjs.core.service.BaseService;

import java.util.List;

public interface DepartmentinfoService extends BaseService<Departmentinfo, String> {
    public List<Departmentinfo> findListAll();

    public Departmentinfo findByid(String id);
}
