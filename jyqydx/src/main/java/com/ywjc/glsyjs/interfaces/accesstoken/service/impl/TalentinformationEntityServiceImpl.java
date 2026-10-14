package com.ywjc.glsyjs.interfaces.accesstoken.service.impl;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.TalentinformationEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.repositories.TalentinformationEntityRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.service.TalentinformationEntityService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("talentinformationEntityServiceImpl")
public class TalentinformationEntityServiceImpl extends BaseServiceImpl<TalentinformationEntity, String> implements
        TalentinformationEntityService {

    @Resource(name = "talentinformationEntityRepository")
    private TalentinformationEntityRepository talentinformationEntityRepository;

    @Resource(name = "talentinformationEntityRepository")
    public void setReposity(PagingAndSortingRepository<TalentinformationEntity, String> talentinformationEntityRepository) {
        super.setReposity(talentinformationEntityRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public TalentinformationEntity findByTalentinformation(String phoneno,String personnelname, String idnumber){
        return talentinformationEntityRepository.findByTalentinformation(phoneno,personnelname,idnumber);
    }

    @Override
    public void savetalentinformationEntity(TalentinformationEntity talentinformationEntity) {
        save(talentinformationEntity);
    }
}
