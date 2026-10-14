package com.ywjc.glsyjs.account.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ewmlogin")
public class Ewmlogin implements Serializable {

    @Id
    @Column(name = "ewmid")
    private String ewmid;
    @Column(name = "ewmname")
    private String ewmname;
    @Column(name = "ewmstate")
    private String ewmstate;
    @Column(name = "ewmmessage")
    private String ewmmessage;
    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "qid")
    private String qid;

    @Column(name = "username")
    private String username;

    @Column(name = "loginname")
    private String loginname;
    @Column(name = "linknum")
    private String linknum;
    @Column(name = "linktel")
    private String linktel;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getEwmid() {
        return ewmid;
    }

    public void setEwmid(String ewmid) {
        this.ewmid = ewmid;
    }

    public String getEwmname() {
        return ewmname;
    }

    public void setEwmname(String ewmname) {
        this.ewmname = ewmname;
    }

    public String getEwmstate() {
        return ewmstate;
    }

    public void setEwmstate(String ewmstate) {
        this.ewmstate = ewmstate;
    }

    public String getEwmmessage() {
        return ewmmessage;
    }

    public void setEwmmessage(String ewmmessage) {
        this.ewmmessage = ewmmessage;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
