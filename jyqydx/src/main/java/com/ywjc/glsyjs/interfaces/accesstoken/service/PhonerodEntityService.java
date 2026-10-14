package com.ywjc.glsyjs.interfaces.accesstoken.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.RegistersmsinfoEntity;

public interface PhonerodEntityService extends BaseService<PhonerodEntity, String> {

    //新增
    public void replacePhoneRod(String phone, String rod, String extime);

    //查询
    public PhonerodEntity queryPhoneRod(String phone);

}
