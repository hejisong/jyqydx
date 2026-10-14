package com.ywjc.glsyjs.interfaces.oauth.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;

public interface OauthEntityService extends BaseService<OauthEntity, String> {

    public void updateOmoauth(OauthEntity oauthEntity);

    public void saveOmoauth(OauthEntity oauthEntity);

    public OauthEntity queryOne(OauthEntity oauthEntity);

    public OauthEntity queryAccesstoken(String access_token);

}
