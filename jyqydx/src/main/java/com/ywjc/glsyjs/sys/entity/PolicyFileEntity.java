package com.ywjc.glsyjs.sys.entity;

import com.ywjc.glsyjs.Policyservices.entity.Corp;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "policyfile")
public class PolicyFileEntity extends BaseEntity {
    @Id
    @Column(name = "plfidid")
    private String plfidid;
    @Column(name = "serialnum")
    private String serialnum;
    @Column(name = "policyname")
    private String policyname;
    @Column(name = "fwunits")
    private String fwunits;
    @Column(name = "dispatchtime")
    private Date dispatchtime;
    @Column(name = "fwtitanic")
    private String fwtitanic;
    @Column(name = "releasetime")
    private Date releasetime;
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "policytype")
    private String policytype;
    //ftl页面名称
    @Column(name = "ftlname")
    private String ftlname;
    //政策类型1人力资源 2市场拓展 3税收 4产业发展 5转型升级 6投融资 7科技创新 0其他
    @Column(name = "filetype")
    private String filetype;
    //政策级别1市级 2区级
    @Column(name = "filelevel")
    private String filelevel;

    @Column(name = "filecontent")
    private String filecontent;

    //政策状态：0删除 1录入 2发布 3失效
    @Column(name = "state")
    private String state;

    //失效日期
    @Column(name = "invalidtime")
    private Date invalidtime;

    @Transient
    private String policynametemp;


    @Column(name = "uploadfileid")
    private String uploadfileid;

    @Column(name = "uploadfilename")
    private String uploadfilename;

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

    @Transient
    private List<Uploadfile> uploadfileList;

    @Transient
    private List<CompanyEntity> companys;

    public List<CompanyEntity> getCompanys() {
        return companys;
    }

    public void setCompanys(List<CompanyEntity> companys) {
        this.companys = companys;
    }

    public List<Uploadfile> getUploadfileList() {
        return uploadfileList;
    }

    public void setUploadfileList(List<Uploadfile> uploadfileList) {
        this.uploadfileList = uploadfileList;
    }

    public String getPolicynametemp() {
        return policynametemp;
    }

    public void setPolicynametemp(String policynametemp) {
        this.policynametemp = policynametemp;
    }

    public Date getInvalidtime() {
        return invalidtime;
    }

    public void setInvalidtime(Date invalidtime) {
        this.invalidtime = invalidtime;
    }

    public String getPlfidid() {
        return plfidid;
    }

    public void setPlfidid(String plfidid) {
        this.plfidid = plfidid;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getPolicyname() {
        return policyname;
    }

    public void setPolicyname(String policyname) {
        this.policyname = policyname;
    }

    public String getFwunits() {
        return fwunits;
    }

    public void setFwunits(String fwunits) {
        this.fwunits = fwunits;
    }

    public Date getDispatchtime() {
        return dispatchtime;
    }

    public void setDispatchtime(Date dispatchtime) {
        this.dispatchtime = dispatchtime;
    }

    public String getFwtitanic() {
        return fwtitanic;
    }

    public void setFwtitanic(String fwtitanic) {
        this.fwtitanic = fwtitanic;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getPolicytype() {
        return policytype;
    }

    public void setPolicytype(String policytype) {
        this.policytype = policytype;
    }

    public String getFtlname() {
        return ftlname;
    }

    public void setFtlname(String ftlname) {
        this.ftlname = ftlname;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilelevel() {
        return filelevel;
    }

    public void setFilelevel(String filelevel) {
        this.filelevel = filelevel;
    }

    public String getFilecontent() {
        return filecontent;
    }

    public void setFilecontent(String filecontent) {
        this.filecontent = filecontent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Transient
    private List<PolicydetailEntity> details;

    public List<PolicydetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<PolicydetailEntity> details) {
        this.details = details;
    }

    @Transient
    private List<Corp> corpids;

    public List<Corp> getCorpids() {
        return corpids;
    }

    public void setCorpids(List<Corp> corpids) {
        this.corpids = corpids;
    }
}
