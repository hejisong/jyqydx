package com.ywjc.glsyjs.interfaces.accesstoken.service.impl;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.RegistersmsinfoEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.repositories.PhonerodEntityRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.repositories.UsercompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.service.PhonerodEntityService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("phonerodEntityServiceImpl")
public class PhonerodEntityServiceImpl extends BaseServiceImpl<PhonerodEntity, String> implements
        PhonerodEntityService {

    @Resource(name = "phonerodEntityRepository")
    private PhonerodEntityRepository phonerodEntityRepository;

    @Resource(name = "phonerodEntityRepository")
    public void setReposity(PagingAndSortingRepository<PhonerodEntity, String> phonerodEntityRepository) {
        super.setReposity(phonerodEntityRepository);
    }

    @Override
    public void replacePhoneRod(String phone, String rod, String extime) {
        phonerodEntityRepository.replacePhoneRod(phone, rod, extime);
    }

    @Override
    @Transactional(readOnly = true)
    public PhonerodEntity queryPhoneRod(String phone) {
        return phonerodEntityRepository.queryPhoneRod(phone);
    }

}
