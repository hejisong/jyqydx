package com.ywjc.glsyjs.interfaces.policyfile.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "policyfile")
public class Policyfile extends BaseEntity {
    //主键id
    @Id
    @Column(name = "plfidid")
    private String plfidid;
    //序号
    @Column(name = "serialnum")
    private String serialnum;
    //政策名称
    @Column(name = "policyname")
    private String policyname;
    //发文单位
    @Column(name = "fwunits")
    private String fwunits;
    //发文时间
    @Column(name = "dispatchtime")
    private Date dispatchtime;
    //文号
    @Column(name = "fwtitanic")
    private String fwtitanic;
    //发布时间
    @Column(name = "releasetime")
    private Date releasetime;

    @Transient
    private String releasetimestr;

    //附件
    @Column(name = "attachment")
    private String attachment;
    //政策类型
    @Column(name = "policytype")
    private String policytype;

    //1人力资源 2市场拓展 3税收 4产业发展 5转型升级 6投融资 7科技创新 0其他
    @Column(name = "filetype")
    private String filetype;
    //1市级 2区级
    @Column(name = "filelevel")
    private String filelevel;

    @Column(name = "filecontent")
    private String filecontent;

    @Column(name = "ftlname")
    private String ftlname;

    //政策状态：0删除 1录入 2发布 3失效（撤销发布）
    @Column(name = "state")
    private String state;

    //失效日期
    @Column(name = "invalidtime")
    private Date invalidtime;

    @Transient
    private String invalidtimestr;

    //维护单位
    @Column(name = "oporgid")
    private String oporgid;

    @Column(name = "oporgname")
    private String oporgname;

    @Transient
    private List<Object> tags;

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Date getInvalidtime() {
        return invalidtime;
    }

    public void setInvalidtime(Date invalidtime) {
        this.invalidtime = invalidtime;
    }


    @Transient
    private Uploadfile uploadfile;
    @Transient
    private List<Uploadfile> uploadfiles;

    @Column(name = "uploadfileid")
    private String uploadfileid;

    @Column(name = "uploadfilename")
    private String uploadfilename;

    public List<Uploadfile> getUploadfiles() {
        return uploadfiles;
    }

    public void setUploadfiles(List<Uploadfile> uploadfiles) {
        this.uploadfiles = uploadfiles;
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

    @Transient
    private List<Uploadfile> uploadfileList;

    @Transient
    private String withoutnum;

    @Transient
    private String replenishnum;

    @Transient
    private String improving;

    @Transient
    private String prednum;

    @Transient
    private int detailnum;

    public String getWithoutnum() {
        return withoutnum;
    }

    public void setWithoutnum(String withoutnum) {
        this.withoutnum = withoutnum;
    }

    public String getReplenishnum() {
        return replenishnum;
    }

    public void setReplenishnum(String replenishnum) {
        this.replenishnum = replenishnum;
    }

    public String getImproving() {
        return improving;
    }

    public void setImproving(String improving) {
        this.improving = improving;
    }

    public String getPrednum() {
        return prednum;
    }

    public void setPrednum(String prednum) {
        this.prednum = prednum;
    }

    public int getDetailnum() {
        return detailnum;
    }

    public void setDetailnum(int detailnum) {
        this.detailnum = detailnum;
    }

    public List<Uploadfile> getUploadfileList() {
        return uploadfileList;
    }

    public void setUploadfileList(List<Uploadfile> uploadfileList) {
        this.uploadfileList = uploadfileList;
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



    public Uploadfile getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(Uploadfile uploadfile) {
        this.uploadfile = uploadfile;
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

    public String getOporgid() {
        return oporgid;
    }

    public void setOporgid(String oporgid) {
        this.oporgid = oporgid;
    }

    public String getOporgname() {
        return oporgname;
    }

    public void setOporgname(String oporgname) {
        this.oporgname = oporgname;
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


    public String getReleasetimestr() {
        return releasetimestr;
    }

    public void setReleasetimestr(String releasetimestr) {
        this.releasetimestr = releasetimestr;
    }

    public String getInvalidtimestr() {
        return invalidtimestr;
    }

    public void setInvalidtimestr(String invalidtimestr) {
        this.invalidtimestr = invalidtimestr;
    }
}
