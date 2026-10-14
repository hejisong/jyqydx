package com.ywjc.glsyjs.interfaces.accesstoken.entity;

import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "talentinformation")
public class TalentinformationEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @Column(length = 32, nullable = false,unique=true)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String tainid;

    @Column(name = "companyid")
    private String companyid;
    //序号
    @Column(name = "serialnum")
    private String serialnum;
    //企业编号
    @Column(name = "enterprisenum")
    private String enterprisenum;
    //企业名称
    @Column(name = "enterprisename")
    private String enterprisename;
    //人才类型
    @Column(name = "talentstypes")
    private String talentstypes;
    //人员名称
    @Column(name = "personnelname")
    private String personnelname;
    //身份证号
    @Column(name = "idnumber")
    private String idnumber;
    //入职日期
    @Column(name = "entrydate")
    private String entrydate;
    //认定机构
    @Column(name = "accreditationbody")
    private String accreditationbody;
    //职务
    @Column(name = "post")
    private String post;
    //税前工资
    @Column(name = "grosspay")
    private String grosspay;
    //人才专业
    @Column(name = "talentmajor")
    private String talentmajor;
    //手机号码
    @Column(name = "phoneno")
    private String phoneno;

    public String getTainid() {
        return tainid;
    }

    public void setTainid(String tainid) {
        this.tainid = tainid;
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

    public String getTalentstypes() {
        return talentstypes;
    }

    public void setTalentstypes(String talentstypes) {
        this.talentstypes = talentstypes;
    }

    public String getPersonnelname() {
        return personnelname;
    }

    public void setPersonnelname(String personnelname) {
        this.personnelname = personnelname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getAccreditationbody() {
        return accreditationbody;
    }

    public void setAccreditationbody(String accreditationbody) {
        this.accreditationbody = accreditationbody;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGrosspay() {
        return grosspay;
    }

    public void setGrosspay(String grosspay) {
        this.grosspay = grosspay;
    }

    public String getTalentmajor() {
        return talentmajor;
    }

    public void setTalentmajor(String talentmajor) {
        this.talentmajor = talentmajor;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
