package com.ywjc.glsyjs.interfaces.company.entity;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;


import freemarker.template.TemplateException;

@Entity
@Table(name = "companypatent")
public class Companypatent extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID =  1L;

    @Id
    @Column(name = "companypatentid")
    private String companypatentid;//COMPANYPATENTID
    @Column(name = "companyid")
    private String companyid;//CompanyID
    @Column(name = "serialnum")
    private String serialnum;//序号
    @Column(name = "enterprisenum")
    private String enterprisenum;//企业编号
    @Column(name = "enterprisename")
    private String enterprisename;//企业名称
    @Column(name = "policycategorynum")
    private String policycategorynum;//政策大类编号
    @Column(name = "policycategoryname")
    private String policycategoryname;//政策大类名称
    @Column(name = "policysubclassno")
    private String policysubclassno;//政策小类编号
    @Column(name = "nameplcategory")
    private String nameplcategory;//政策小类名称
    @Column(name = "policyconditions")
    private String policyconditions;//政策条件
    @Column(name = "patentacquisitime")
    private Date patentacquisitime;//专利获取时间
    @Column(name = "certificationauthority")
    private String certificationauthority;//证书颁发机构
    @Column(name = "dateauthorization")
    private Date dateauthorization;//授权日期
    @Column(name = "patentee")
    private String patentee;//专利权人
    @Column(name = "status")
    private String status;//状态（0 无效 1 有效）
    @Column(name = "awardcategory")
    private String awardcategory;//奖励类别（0 一次性 1每年奖励）
    @Column(name = "receiverewardnot")
    private String receiverewardnot;//是否领取奖励（0 否 1 是）
    @Column(name = "patentyear")
    private String patentyear;//专利年份
    @Column(name = "enjoyplcategory")
    private String enjoyplcategory;//享受政策类别（1 免申即享 2 少量补录即享 3 满足条件即享）
    @Column(name = "patenttype")
    private String patenttype;//专利类型
    @Column(name = "patentname")
    private String patentname;//专利名称
    @Column(name = "applicationnum")
    private String applicationnum;//申请编号
    @Column(name = "applicationtime")
    private Date applicationtime;//申请时间
    @Column(name = "pubannomentno")
    private String pubannomentno;//公开（公告）号
    @Column(name = "pubannomentday")
    private String pubannomentday;//公开（公告）日
    @Column(name = "credit_no")
    private String credit_no;//统一社会信用代码
    @Column(name = "patentid")
    private String patentid;//专利id
    @Column(name = "outhor_nmm")
    private String outhor_nmm;//授权号
    @Column(name = "state")
    private String state;//状态
    public String getCompanypatentid() {
        return companypatentid;
    }

    public void setCompanypatentid(String companypatentid) {
        this.companypatentid = companypatentid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public String getEnterprisenum() {
        return enterprisenum;
    }

    public void setEnterprisenum(String enterprisenum) {
        this.enterprisenum = enterprisenum;
    }

    public String getEnterprisename() {
        return enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }

    public String getPolicycategorynum() {
        return policycategorynum;
    }

    public void setPolicycategorynum(String policycategorynum) {
        this.policycategorynum = policycategorynum;
    }

    public String getPolicycategoryname() {
        return policycategoryname;
    }

    public void setPolicycategoryname(String policycategoryname) {
        this.policycategoryname = policycategoryname;
    }

    public String getPolicysubclassno() {
        return policysubclassno;
    }

    public void setPolicysubclassno(String policysubclassno) {
        this.policysubclassno = policysubclassno;
    }

    public String getNameplcategory() {
        return nameplcategory;
    }

    public void setNameplcategory(String nameplcategory) {
        this.nameplcategory = nameplcategory;
    }

    public String getPolicyconditions() {
        return policyconditions;
    }

    public void setPolicyconditions(String policyconditions) {
        this.policyconditions = policyconditions;
    }

    public Date getPatentacquisitime() {
        return patentacquisitime;
    }

    public void setPatentacquisitime(Date patentacquisitime) {
        this.patentacquisitime = patentacquisitime;
    }

    public String getCertificationauthority() {
        return certificationauthority;
    }

    public void setCertificationauthority(String certificationauthority) {
        this.certificationauthority = certificationauthority;
    }

    public Date getDateauthorization() {
        return dateauthorization;
    }

    public void setDateauthorization(Date dateauthorization) {
        this.dateauthorization = dateauthorization;
    }

    public String getPatentee() {
        return patentee;
    }

    public void setPatentee(String patentee) {
        this.patentee = patentee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAwardcategory() {
        return awardcategory;
    }

    public void setAwardcategory(String awardcategory) {
        this.awardcategory = awardcategory;
    }

    public String getReceiverewardnot() {
        return receiverewardnot;
    }

    public void setReceiverewardnot(String receiverewardnot) {
        this.receiverewardnot = receiverewardnot;
    }

    public String getPatentyear() {
        return patentyear;
    }

    public void setPatentyear(String patentyear) {
        this.patentyear = patentyear;
    }

    public String getEnjoyplcategory() {
        return enjoyplcategory;
    }

    public void setEnjoyplcategory(String enjoyplcategory) {
        this.enjoyplcategory = enjoyplcategory;
    }

    public String getPatenttype() {
        return patenttype;
    }

    public void setPatenttype(String patenttype) {
        this.patenttype = patenttype;
    }

    public String getPatentname() {
        return patentname;
    }

    public void setPatentname(String patentname) {
        this.patentname = patentname;
    }

    public String getApplicationnum() {
        return applicationnum;
    }

    public void setApplicationnum(String applicationnum) {
        this.applicationnum = applicationnum;
    }

    public Date getApplicationtime() {
        return applicationtime;
    }

    public void setApplicationtime(Date applicationtime) {
        this.applicationtime = applicationtime;
    }

    public String getPubannomentno() {
        return pubannomentno;
    }

    public void setPubannomentno(String pubannomentno) {
        this.pubannomentno = pubannomentno;
    }

    public String getPubannomentday() {
        return pubannomentday;
    }

    public void setPubannomentday(String pubannomentday) {
        this.pubannomentday = pubannomentday;
    }

    public String getCredit_no() {
        return credit_no;
    }

    public void setCredit_no(String credit_no) {
        this.credit_no = credit_no;
    }

    public String getPatentid() {
        return patentid;
    }

    public void setPatentid(String patentid) {
        this.patentid = patentid;
    }

    public String getOuthor_nmm() {
        return outhor_nmm;
    }

    public void setOuthor_nmm(String outhor_nmm) {
        this.outhor_nmm = outhor_nmm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Companypatent setValue(Companypatent other){
/*        this.companyid=other.getCompanyid();
        this.serialnum=other.getSerialnum();
        this.enterprisenum=other.getEnterprisenum();
        this.enterprisename=other.getEnterprisename();
        this.policycategorynum=other.getPolicycategorynum();
        this.policycategoryname=other.getPolicycategoryname();
        this.policysubclassno=other.getPolicysubclassno();
        this.nameplcategory=other.getNameplcategory();
        this.policyconditions=other.getPolicyconditions();
        this.patentacquisitime=other.getPatentacquisitime();
        this.certificationauthority=other.getCertificationauthority();
        this.dateauthorization=other.getDateauthorization();
        this.patentee=other.getPatentee();
        this.status=other.getStatus();
        this.awardcategory=other.getAwardcategory();
        this.receiverewardnot=other.getReceiverewardnot();
        this.patentyear=other.getPatentyear();
        this.enjoyplcategory=other.getEnjoyplcategory();
        this.patenttype=other.getPatenttype();
        this.patentname=other.getPatentname();
        this.applicationnum=other.getApplicationnum();
        this.applicationtime=other.getApplicationtime();
        this.pubannomentno=other.getPubannomentno();
        this.pubannomentday=other.getPubannomentday();
        this.credit_no=other.getCredit_no();
        this.patentid=other.getPatentid();
        this.outhor_nmm=other.getOuthor_nmm();*/

        this.patenttype=other.getPatenttype();
        this.patentname=other.getPatentname();
        this.patentee=other.getPatentee();
        this.patentyear=other.getPatentyear();
        this.enterprisename=other.getEnterprisename();
        this.applicationnum=other.getApplicationnum();
        this.applicationtime=other.getApplicationtime();
        this.pubannomentno=other.getPubannomentno();
        this.pubannomentday=other.getPubannomentday();

        return this;
    }
}
