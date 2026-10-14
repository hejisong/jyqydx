package com.ywjc.glsyjs.interfaces.oauth.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OauthEntityRepository extends BaseRepository<OauthEntity, String> {


	@Query(value = "update om_oauth set access_token= ?1,expires_in = ?2 where client_id = ?3 and client_secret = ?4 and grant_type = ?5 and scope = ?6",nativeQuery=true)
	@Modifying
	void updateOmoauth(String access_token, String expires_in,String client_id,String client_secret,String grant_type,String scope);

	@Query(value = "select o.* from om_oauth o where o.client_id = ?1 and o.client_secret = ?2 and o.grant_type = ?3 and o.scope = ?4",nativeQuery=true)
	public OauthEntity queryOne(String client_id,String client_secret,String grant_type,String scope);

	@Query(value = "select o.* from om_oauth o where o.access_token = ?1",nativeQuery=true)
	public OauthEntity queryAccesstoken(String access_token);




}
