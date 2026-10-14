package com.ywjc.glsyjs.interfaces.accesstoken.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.RegistersmsinfoEntity;

public interface RegistersmsinfoEntityService extends BaseService<RegistersmsinfoEntity, String> {

    //新增
    public void saveRegistersmsinfo(RegistersmsinfoEntity registersmsinfoEntity);
}
