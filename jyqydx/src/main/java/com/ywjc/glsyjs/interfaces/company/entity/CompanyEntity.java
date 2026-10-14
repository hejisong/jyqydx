package com.ywjc.glsyjs.interfaces.company.entity;


import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
public class CompanyEntity extends BaseEntity {
    @Id
    @Column(name = "companyid")
    private String companyid;

    //
    @Column(name = "number")
    private String number;

    //
    @Column(name = "enterprisename")
    private String enterprisename;

    //
    @Column(name = "tpidentificationnum")
    private String tpidentificationnum;

    //
    @Column(name = "businessregisnum")
    private String businessregisnum;

    //
    @Column(name = "uscreditcode")
    private String uscreditcode;

    //
    @Column(name = "lrepresentative")
    private String lrepresentative;

    //
    @Column(name = "organcode")
    private String organcode;

    //
    @Column(name = "recapital")
    private String recapital;

    //
    @Column(name = "paidcapital")
    private String paidcapital;

    //
    @Column(name = "typesenterprises")
    private String typesenterprises;

    //
    @Column(name = "operationstatus")
    private String operationstatus;

    //
    @Column(name = "dateincorporation")
    private Date dateincorporation;

    //
    @Column(name = "nameusedbefore")
    private String nameusedbefore;

    //
    @Column(name = "registrationauthority")
    private String registrationauthority;

    //
    @Column(name = "companyregisaddress")
    private String companyregisaddress;

    //
    @Column(name = "naturebusiness")
    private String naturebusiness;

    //
    @Column(name = "staffsize")
    private String staffsize;

    //
    @Column(name = "numberinpersons")
    private String numberinpersons;

    //
    @Column(name = "operatorenterprise")
    private String operatorenterprise;

    //
    @Column(name = "enterpriseclassification")
    private String enterpriseclassification;

    //
    @Column(name = "classificationstartups")
    private String classificationstartups;

    //
    @Column(name = "lwddkithedistrict")
    private String lwddkithedistrict;

    //
    @Column(name = "phoneno")
    private String phoneno;

    @Column(name = "state")
    private String state;

    //收款账号
    @Column(name = "account")
    private String account;

    //电子账户
    @Column(name = "dzaccount")
    private String dzaccount;


    @Column(name = "term_startdate")
    private Date term_startdate;//营业开始日期
    @Column(name = "term_enddate")
    private Date term_enddate;//营业结束日期
    @Column(name = "end_date")
    private Date end_date;//注销日期
    @Column(name = "columcheck_date")
    private Date columcheck_date;//核准日期
    @Column(name = "province")
    private String province;//省份缩写
    @Column(name = "city")
    private String city;//城市编码
    @Column(name = "econ_kind")
    private String econ_kind;//企业字段别名
    @Column(name = "is_quoted")
    private String is_quoted;//是否上市
    @Column(name = "quoted_type")
    private String quoted_type;//上市字段别名

    @Column(name = "recapitaln")
    private Double recapitaln;//注册资本

    @Column(name = "parkid")
    private String parkid;//所属园区

    @Column(name = "parkname")
    private String parkname;//所属园区

    @Transient
    private  List<Accuratetag> atags;

    public List<Accuratetag> getAtags() {
        return atags;
    }

    public void setAtags(List<Accuratetag> atags) {
        this.atags = atags;
    }

    @Transient
    public List<TagEntity> ptags;

    @Transient
    public List<Businessscopetag> scopetags;

    public List<TagEntity> getPtags() {
        return ptags;
    }

    public void setPtags(List<TagEntity> ptags) {
        this.ptags = ptags;
    }

    public List<Businessscopetag> getScopetags() {
        return scopetags;
    }

    public void setScopetags(List<Businessscopetag> scopetags) {
        this.scopetags = scopetags;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEnterprisename() {
        return enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }

    public String getTpidentificationnum() {
        return tpidentificationnum;
    }

    public void setTpidentificationnum(String tpidentificationnum) {
        this.tpidentificationnum = tpidentificationnum;
    }

    public String getBusinessregisnum() {
        return businessregisnum;
    }

    public void setBusinessregisnum(String businessregisnum) {
        this.businessregisnum = businessregisnum;
    }

    public String getUscreditcode() {
        return uscreditcode;
    }

    public void setUscreditcode(String uscreditcode) {
        this.uscreditcode = uscreditcode;
    }

    public String getLrepresentative() {
        return lrepresentative;
    }

    public void setLrepresentative(String lrepresentative) {
        this.lrepresentative = lrepresentative;
    }

    public String getOrgancode() {
        return organcode;
    }

    public void setOrgancode(String organcode) {
        this.organcode = organcode;
    }

    public String getRecapital() {
        return recapital;
    }

    public void setRecapital(String recapital) {
        this.recapital = recapital;
    }

    public String getPaidcapital() {
        return paidcapital;
    }

    public void setPaidcapital(String paidcapital) {
        this.paidcapital = paidcapital;
    }

    public String getTypesenterprises() {
        return typesenterprises;
    }

    public void setTypesenterprises(String typesenterprises) {
        this.typesenterprises = typesenterprises;
    }

    public String getOperationstatus() {
        return operationstatus;
    }

    public void setOperationstatus(String operationstatus) {
        this.operationstatus = operationstatus;
    }

    public Date getDateincorporation() {
        return dateincorporation;
    }

    public void setDateincorporation(Date dateincorporation) {
        this.dateincorporation = dateincorporation;
    }

    public String getNameusedbefore() {
        return nameusedbefore;
    }

    public void setNameusedbefore(String nameusedbefore) {
        this.nameusedbefore = nameusedbefore;
    }

    public String getRegistrationauthority() {
        return registrationauthority;
    }

    public void setRegistrationauthority(String registrationauthority) {
        this.registrationauthority = registrationauthority;
    }

    public String getCompanyregisaddress() {
        return companyregisaddress;
    }

    public void setCompanyregisaddress(String companyregisaddress) {
        this.companyregisaddress = companyregisaddress;
    }

    public String getNaturebusiness() {
        return naturebusiness;
    }

    public void setNaturebusiness(String naturebusiness) {
        this.naturebusiness = naturebusiness;
    }

    public String getStaffsize() {
        return staffsize;
    }

    public void setStaffsize(String staffsize) {
        this.staffsize = staffsize;
    }

    public String getNumberinpersons() {
        return numberinpersons;
    }

    public void setNumberinpersons(String numberinpersons) {
        this.numberinpersons = numberinpersons;
    }

    public String getOperatorenterprise() {
        return operatorenterprise;
    }

    public void setOperatorenterprise(String operatorenterprise) {
        this.operatorenterprise = operatorenterprise;
    }

    public String getEnterpriseclassification() {
        return enterpriseclassification;
    }

    public void setEnterpriseclassification(String enterpriseclassification) {
        this.enterpriseclassification = enterpriseclassification;
    }

    public String getClassificationstartups() {
        return classificationstartups;
    }

    public void setClassificationstartups(String classificationstartups) {
        this.classificationstartups = classificationstartups;
    }

    public String getLwddkithedistrict() {
        return lwddkithedistrict;
    }

    public void setLwddkithedistrict(String lwddkithedistrict) {
        this.lwddkithedistrict = lwddkithedistrict;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDzaccount() {
        return dzaccount;
    }

    public void setDzaccount(String dzaccount) {
        this.dzaccount = dzaccount;
    }


    public Date getTerm_startdate() {
        return term_startdate;
    }

    public void setTerm_startdate(Date term_startdate) {
        this.term_startdate = term_startdate;
    }

    public Date getTerm_enddate() {
        return term_enddate;
    }

    public void setTerm_enddate(Date term_enddate) {
        this.term_enddate = term_enddate;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getColumcheck_date() {
        return columcheck_date;
    }

    public void setColumcheck_date(Date columcheck_date) {
        this.columcheck_date = columcheck_date;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEcon_kind() {
        return econ_kind;
    }

    public void setEcon_kind(String econ_kind) {
        this.econ_kind = econ_kind;
    }

    public String getIs_quoted() {
        return is_quoted;
    }

    public void setIs_quoted(String is_quoted) {
        this.is_quoted = is_quoted;
    }

    public String getQuoted_type() {
        return quoted_type;
    }

    public void setQuoted_type(String quoted_type) {
        this.quoted_type = quoted_type;
    }

    public Double getRecapitaln() {
        return recapitaln;
    }

    public void setRecapitaln(Double recapitaln) {
        this.recapitaln = recapitaln;
    }

    public String getParkid() {
        return parkid;
    }

    public void setParkid(String parkid) {
        this.parkid = parkid;
    }

    public String getParkname() {
        return parkname;
    }

    public void setParkname(String parkname) {
        this.parkname = parkname;
    }

    public CompanyEntity setValue(CompanyEntity other){

        this.enterprisename=other.getEnterprisename();
        this.nameusedbefore=other.getNameusedbefore();
        this.recapital=other.getRecapital();
        String r=other.getRecapital();
        if(r.indexOf("万")!=-1){
            r=r.substring(0,r.indexOf("万"));
            r=r.trim();
            try {
                Double b=Double.valueOf(r);
                this.recapitaln=b;
            }catch (NumberFormatException e){

            }


        }

        this.companyregisaddress=other.getCompanyregisaddress();
        this.lrepresentative=other.getLrepresentative();
        this.phoneno=other.getPhoneno();
        this.businessregisnum=other.getBusinessregisnum();
        this.naturebusiness=other.getNaturebusiness();
        this.term_startdate=other.getTerm_startdate();
        this.term_enddate=other.getTerm_enddate();
        this.registrationauthority=other.getRegistrationauthority();
        this.dateincorporation=other.getDateincorporation();
        this.end_date=other.getEnd_date();
        this.columcheck_date=other.getColumcheck_date();
        this.operationstatus=other.getOperationstatus();
        this.organcode=other.getOrgancode();
        this.uscreditcode=other.getUscreditcode();
        this.province=other.getProvince();
        this.city=other.getCity();
        this.is_quoted=other.getIs_quoted();
        this.typesenterprises=other.getTypesenterprises();
        this.tpidentificationnum=other.getTpidentificationnum();

//        this.parkid=other.getParkid();
//        this.parkname=other.getParkname();
        return this;
    }
}
