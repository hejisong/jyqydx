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
@Table(name = "iihightechenterprises")
public class Iihightechenterprises extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID =  1L;

    @Id
    @Column(name = "iiheeid")
    private String iiheeid;//IIHEEID
    @Column(name = "companyid")
    private String companyid;//CompanyID
    @Column(name = "serialnum")
    private String serialnum;//序号
    @Column(name = "enterprisenum")
    private String enterprisenum;//企业编号
    @Column(name = "enterprisename")
    private String enterprisename;//企业名称
    @Column(name = "recognitionyear")
    private String recognitionyear;//认定年份
    @Column(name = "certificateno")
    private String certificateno;//证书编号
    @Column(name = "issuingtime")
    private Date issuingtime;//发证时间
    @Column(name = "validityterm")
    private String validityterm;//有效期
    @Column(name = "expirydate")
    private Date expirydate;//有效期截止日期
    @Column(name = "approvalauthority")
    private String approvalauthority;//批准机关
    @Column(name = "enclosure")
    private String enclosure;//附件
    @Column(name = "enclosurename")
    private String enclosurename;//附件名称
    @Column(name = "zcdz")
    private String zcdz;//注册地址
    @Column(name = "income_tax_agency")
    private String income_tax_agency;//所得税征管机关
    @Column(name = "economic_nature")
    private String economic_nature;//经济性质
    @Column(name = "area_a")
    private String area_a;//领域A
    @Column(name = "area_b")
    private String area_b;//领域B
    @Column(name = "administrative_divisions")
    private String administrative_divisions;//行政区划

    @Column(name = "state")
    private String state;//状态

    public String getIiheeid() {
        return iiheeid;
    }

    public void setIiheeid(String iiheeid) {
        this.iiheeid = iiheeid;
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

    public String getRecognitionyear() {
        return recognitionyear;
    }

    public void setRecognitionyear(String recognitionyear) {
        this.recognitionyear = recognitionyear;
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno;
    }

    public Date getIssuingtime() {
        return issuingtime;
    }

    public void setIssuingtime(Date issuingtime) {
        this.issuingtime = issuingtime;
    }

    public String getValidityterm() {
        return validityterm;
    }

    public void setValidityterm(String validityterm) {
        this.validityterm = validityterm;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Date expirydate) {
        this.expirydate = expirydate;
    }

    public String getApprovalauthority() {
        return approvalauthority;
    }

    public void setApprovalauthority(String approvalauthority) {
        this.approvalauthority = approvalauthority;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getEnclosurename() {
        return enclosurename;
    }

    public void setEnclosurename(String enclosurename) {
        this.enclosurename = enclosurename;
    }

    public String getZcdz() {
        return zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz;
    }

    public String getIncome_tax_agency() {
        return income_tax_agency;
    }

    public void setIncome_tax_agency(String income_tax_agency) {
        this.income_tax_agency = income_tax_agency;
    }

    public String getEconomic_nature() {
        return economic_nature;
    }

    public void setEconomic_nature(String economic_nature) {
        this.economic_nature = economic_nature;
    }

    public String getArea_a() {
        return area_a;
    }

    public void setArea_a(String area_a) {
        this.area_a = area_a;
    }

    public String getArea_b() {
        return area_b;
    }

    public void setArea_b(String area_b) {
        this.area_b = area_b;
    }

    public String getAdministrative_divisions() {
        return administrative_divisions;
    }

    public void setAdministrative_divisions(String administrative_divisions) {
        this.administrative_divisions = administrative_divisions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Iihightechenterprises setValue(Iihightechenterprises other){
        this.companyid=other.getCompanyid();
        this.serialnum=other.getSerialnum();
        this.enterprisenum=other.getEnterprisenum();
        this.enterprisename=other.getEnterprisename();
        this.recognitionyear=other.getRecognitionyear();
        this.certificateno=other.getCertificateno();
//        this.issuingtime=other.getIssuingtime();
        this.validityterm=other.getValidityterm();
//        this.expirydate=other.getExpirydate();
        this.approvalauthority=other.getApprovalauthority();
        this.enclosure=other.getEnclosure();
        this.enclosurename=other.getEnclosurename();
        this.zcdz=other.getZcdz();
        this.income_tax_agency=other.getIncome_tax_agency();
        this.economic_nature=other.getEconomic_nature();
        this.area_a=other.getArea_a();
        this.area_b=other.getArea_b();
        this.administrative_divisions=other.getAdministrative_divisions();
        return this;
    }
}
