package com.ywjc.glsyjs.interfaces.oauth.service.impl;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.oauth.repositories.OauthEntityRepository;
import com.ywjc.glsyjs.interfaces.oauth.service.OauthEntityService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("oauthEntityServiceImpl")
public class OauthEntityServiceImpl extends BaseServiceImpl<OauthEntity, String> implements
        OauthEntityService {

    @Resource(name = "oauthEntityRepository")
    private OauthEntityRepository oauthEntityRepository;

    @Resource(name = "oauthEntityRepository")
    public void setReposity(PagingAndSortingRepository<OauthEntity, String> oauthEntityRepository) {
        super.setReposity(oauthEntityRepository);
    }

    @Override
    public void updateOmoauth(OauthEntity oauthEntity) {
        oauthEntityRepository.updateOmoauth(oauthEntity.getAccess_token(), oauthEntity.getExpires_in(), oauthEntity.getClient_id(), oauthEntity.getClient_secret(), oauthEntity.getGrant_type(), oauthEntity.getScope());
    }

    @Override
    public void saveOmoauth(OauthEntity oauthEntity) {
        save(oauthEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public OauthEntity queryOne(OauthEntity oauthEntity) {
        return oauthEntityRepository.queryOne(oauthEntity.getClient_id(), oauthEntity.getClient_secret(), oauthEntity.getGrant_type(), oauthEntity.getScope());
    }

    @Override
    @Transactional(readOnly = true)
    public OauthEntity queryAccesstoken(String access_token) {
        return oauthEntityRepository.queryAccesstoken(access_token);
    }
}
