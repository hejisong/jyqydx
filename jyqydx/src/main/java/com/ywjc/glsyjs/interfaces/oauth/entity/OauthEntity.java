package com.ywjc.glsyjs.interfaces.oauth.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "om_oauth")
public class OauthEntity extends BaseEntity {
    //客户端id
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @NotBlank(message = "client_id 不能为空!")
    @Column(name = "client_id")
    private String client_id;

    //客户端秘钥
    @NotBlank(message = "client_secret 不能为空!")
    @Column(name = "client_secret")
    private String client_secret;

    //授权类型
    @NotBlank(message = "grant_type 不能为空!")
    @Column(name = "grant_type")
    private String grant_type;

    //范围
    @NotBlank(message = "scope 不能为空!")
    @Column(name = "scope")
    private String scope;

    //认证信息
    @Column(name = "access_token")
    private String access_token;

    //有效期
    @Column(name = "expires_in")
    private String expires_in;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
