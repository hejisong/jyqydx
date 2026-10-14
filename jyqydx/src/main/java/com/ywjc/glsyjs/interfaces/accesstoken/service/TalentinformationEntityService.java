package com.ywjc.glsyjs.interfaces.accesstoken.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.TalentinformationEntity;

public interface TalentinformationEntityService extends BaseService<TalentinformationEntity, String> {

    //根据解密之后的用户查询人才表
    public TalentinformationEntity findByTalentinformation(String phoneno, String personnelname, String idnumber);

    //新增人才信息表数据
    public void savetalentinformationEntity(TalentinformationEntity talentinformationEntity);

}
