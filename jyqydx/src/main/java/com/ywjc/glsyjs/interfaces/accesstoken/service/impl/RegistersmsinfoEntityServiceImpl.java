package com.ywjc.glsyjs.interfaces.accesstoken.service.impl;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.RegistersmsinfoEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.repositories.PhonerodEntityRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.service.PhonerodEntityService;
import com.ywjc.glsyjs.interfaces.accesstoken.service.RegistersmsinfoEntityService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("registersmsinfoEntityServiceImpl")
public class RegistersmsinfoEntityServiceImpl extends BaseServiceImpl<RegistersmsinfoEntity, String> implements
        RegistersmsinfoEntityService {

    @Override
    public void saveRegistersmsinfo(RegistersmsinfoEntity registersmsinfoEntity) {
        save(registersmsinfoEntity);
    }


}
