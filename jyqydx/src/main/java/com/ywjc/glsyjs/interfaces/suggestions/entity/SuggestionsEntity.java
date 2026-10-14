package com.ywjc.glsyjs.interfaces.suggestions.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "suggestions")
public class SuggestionsEntity extends BaseEntity {
    //主键ID
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "companyid")
    private String companyid;

    //单位名称
    @Column(name = "companyname")
    private String companyname;

    //联系电话
    @Column(name = "mobile")
    private String mobile;

    //标题
    @Column(name = "title")
    private String title;

    //内容
    @Column(name = "content")
    private String content;

    //0：待处理  1处理中  2:处理完成
    @Column(name = "state")
    private String state;

    //浏览量
    @Column(name = "readcount")
    private int readcount;
    //创建人ID
    @Column(name = "createrid")
    private String createrid;

    @Column(name = "creater")
    private String creater;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "createtime")
    private Date createtime;

    //处理人ID
    @Column(name = "donerid")
    private String donerid;

    //处理结果
    @Column(name = "doneresult")
    private String doneresult;

    //处理时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "donetime")
    private Date donetime;

    //信息类型 1意见与建议 2投诉 3咨询
    @Column(name = "infotype")
    private String infotype;

    //是否公开 1公开 0不公开
    @Column(name = "isshow")
    private String isshow;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "updatetime")
    private Date updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDonerid() {
        return donerid;
    }

    public void setDonerid(String donerid) {
        this.donerid = donerid;
    }

    public String getDoneresult() {
        return doneresult;
    }

    public void setDoneresult(String doneresult) {
        this.doneresult = doneresult;
    }

    public Date getDonetime() {
        return donetime;
    }

    public void setDonetime(Date donetime) {
        this.donetime = donetime;
    }

    public String getInfotype() {
        return infotype;
    }

    public void setInfotype(String infotype) {
        this.infotype = infotype;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
