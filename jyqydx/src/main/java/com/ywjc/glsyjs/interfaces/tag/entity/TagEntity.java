package com.ywjc.glsyjs.interfaces.tag.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tag")
public class TagEntity {
    //主键ID
    @Id
    @Column(name = "tagid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tagid;

    //标签名称
    @Column(name = "tagname")
    private String tagname;
    //标签名称
    @Column(name = "createuser")
    private String createuser;

    //标签名称
    @Column(name = "createtime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createtime;

    //标签状态 0 停用 1启用
    @Column(name = "state")
    private String state;



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
