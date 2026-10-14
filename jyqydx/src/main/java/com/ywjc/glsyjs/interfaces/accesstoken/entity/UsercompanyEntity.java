package com.ywjc.glsyjs.interfaces.accesstoken.entity;

import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_company")
public class UsercompanyEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Column(length = 32, nullable = false,unique=true)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    //用户id
    @Column(name = "userid")
    private String userid;
    //企业id
    @Column(name = "companyid")
    private String companyid;

    //企业名称
    @Column(name = "enterprisename")
    private String enterprisename;
    //用户名
    @Column(name = "username")
    private String username;
    //身份证
    @Column(name = "linknum")
    private String linknum;
    //手机号
    @Column(name = "linktel")
    private String linktel;

    @Column(name = "islegalperson")
    private String islegalperson;

    public String getIslegalperson() {
        return islegalperson;
    }

    public void setIslegalperson(String islegalperson) {
        this.islegalperson = islegalperson;
    }

    public UsercompanyEntity(){

    }

    public UsercompanyEntity(String id,String userid,String companyid,String enterprisename,String username,String linknum,String linktel){
        this.id = id;
        this.userid = userid;
        this.companyid = companyid;
        this.enterprisename = enterprisename;
        this.username = username;
        this.linknum = linknum;
        this.linktel = linktel;
    }

    public UsercompanyEntity(String id, String userid, String companyid, String enterprisename, String username, String linknum, String linktel, String islegalperson) {
        this.id = id;
        this.userid = userid;
        this.companyid = companyid;
        this.enterprisename = enterprisename;
        this.username = username;
        this.linknum = linknum;
        this.linktel = linktel;
        this.islegalperson = islegalperson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getEnterprisename() {
        return enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLinknum() {
        return linknum;
    }

    public void setLinknum(String linknum) {
        this.linknum = linknum;
    }

    public String getLinktel() {
        return linktel;
    }

    public void setLinktel(String linktel) {
        this.linktel = linktel;
    }
}
