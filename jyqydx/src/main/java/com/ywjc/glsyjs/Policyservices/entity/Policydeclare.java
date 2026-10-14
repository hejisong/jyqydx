package com.ywjc.glsyjs.Policyservices.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "policydeclare")
public class Policydeclare extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "declareid")
    private String declareid;

    @Column(name = "declarename")
    private String declarename;
    @Column(name = "policyfileid")
    private String policyfileid;
    @Column(name = "policyfilename")
    private String policyfilename;


    @Column(name = "releaseorgid")
    private String releaseorgid;
    @Column(name = "releaseorgname")
    private String releaseorgname;
    @Column(name = "releasedate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date releasedate;
    @Column(name = "releaseperid")
    private String releaseperid;
    @Column(name = "releaseper")
    private String releaseper;
    //0不公开 1公开
    @Column(name = "ispublic")
    private String ispublic;
    @Column(name = "declare_start_date")
    private Date declare_start_date;
    @Column(name = "declare_end_date")
    private Date declare_end_date;
    //1已保存 2已发布 3撤销发布 0已删除
    @Column(name = "state")
    private String state;
    @Column(name = "creattime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date creattime;
    @Column(name = "filecontent")
    private String filecontent;

    @Column(name = "applyparkid")
    private String applyparkid;
    @Column(name = "applyparkname")
    private String applyparkname;

    @Column(name = "uploadfileid")
    private String uploadfileid;

    @Column(name = "uploadfilename")
    private String uploadfilename;

    @Transient
    private List<Uploadfile> uploadfileList;


    public List<Uploadfile> getUploadfileList() {
        return uploadfileList;
    }

    public void setUploadfileList(List<Uploadfile> uploadfileList) {
        this.uploadfileList = uploadfileList;
    }

    public String getDeclareid() {
        return declareid;
    }

    public void setDeclareid(String declareid) {
        this.declareid = declareid;
    }

    public String getPolicyfileid() {
        return policyfileid;
    }

    public void setPolicyfileid(String policyfileid) {
        this.policyfileid = policyfileid;
    }

    public String getReleaseorgid() {
        return releaseorgid;
    }

    public void setReleaseorgid(String releaseorgid) {
        this.releaseorgid = releaseorgid;
    }

    public String getReleaseorgname() {
        return releaseorgname;
    }

    public void setReleaseorgname(String releaseorgname) {
        this.releaseorgname = releaseorgname;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getReleaseperid() {
        return releaseperid;
    }

    public void setReleaseperid(String releaseperid) {
        this.releaseperid = releaseperid;
    }

    public String getReleaseper() {
        return releaseper;
    }

    public void setReleaseper(String releaseper) {
        this.releaseper = releaseper;
    }

    public String getIspublic() {
        return ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public Date getDeclare_start_date() {
        return declare_start_date;
    }

    public void setDeclare_start_date(Date declare_start_date) {
        this.declare_start_date = declare_start_date;
    }

    public Date getDeclare_end_date() {
        return declare_end_date;
    }

    public void setDeclare_end_date(Date declare_end_date) {
        this.declare_end_date = declare_end_date;
    }

    public String getDeclarename() {
        return declarename;
    }

    public void setDeclarename(String declarename) {
        this.declarename = declarename;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public String getPolicyfilename() {
        return policyfilename;
    }

    public void setPolicyfilename(String policyfilename) {
        this.policyfilename = policyfilename;
    }

    public String getFilecontent() {
        return filecontent;
    }

    public void setFilecontent(String filecontent) {
        this.filecontent = filecontent;
    }

    public String getApplyparkid() {
        return applyparkid;
    }

    public void setApplyparkid(String applyparkid) {
        this.applyparkid = applyparkid;
    }

    public String getApplyparkname() {
        return applyparkname;
    }

    public void setApplyparkname(String applyparkname) {
        this.applyparkname = applyparkname;
    }

    public String getUploadfileid() {
        return uploadfileid;
    }

    public void setUploadfileid(String uploadfileid) {
        this.uploadfileid = uploadfileid;
    }

    public String getUploadfilename() {
        return uploadfilename;
    }

    public void setUploadfilename(String uploadfilename) {
        this.uploadfilename = uploadfilename;
    }
}
