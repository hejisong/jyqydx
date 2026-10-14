package com.ywjc.glsyjs.interfaces.policyfile.entity;

import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlyfileEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "policydetail")
public class PolicydetailEntity  extends BaseEntity {
    //主键id
    @Id
    @Column(name = "pldtailid")
    private String pldtailid;
    @Column(name = "plfidid")
    private String plfidid;
    @Column(name = "serialnum")
    private Integer serialnum;
    @Column(name = "pldserialnum")
    private String pldserialnum;
    @Column(name = "plcategoryno")
    private String plcategoryno;
    @Column(name = "nplcategory")
    private String nplcategory;
    @Column(name = "plsubclassnum")
    private String plsubclassnum;
    @Column(name = "plsubclassname")
    private String plsubclassname;
    @Column(name = "plconditions")
    private String plconditions;
    @Column(name = "rdescription")
    private String rdescription;
    @Column(name = "ramount")
    private Double ramount;
    @Column(name = "pevaluationdepartment")
    private String pevaluationdepartment;
    @Column(name = "isdefine")
    private String isdefine;
    @Column(name = "feaunit")
    private String feaunit;
    @Column(name = "thpath")
    private String thpath;
    @Column(name = "cjtype")
    private String cjtype;

    //0或空已确认 1未确认
    @Column(name = "state")
    private String state;
    @Column(name = "declareconditions")
    private String declareconditions;
    @Column(name = "minamount")
    private String minamount;
    @Column(name = "maxamount")
    private String maxamount;

    //1申报过了
    @Transient
    private String sbstate;
    @Transient
    private  List<Policymateriallist>  materiallists;

    @Transient
    private int corpcount;

    public int getCorpcount() {
        return corpcount;
    }

    public void setCorpcount(int corpcount) {
        this.corpcount = corpcount;
    }

    @Transient
    private  List<Object>  corps;

    public List<Object> getCorps() {
        return corps;
    }

    public void setCorps(List<Object> corps) {
        this.corps = corps;
    }

    @Transient
    private String policyname;

    public String getPolicyname() {
        return policyname;
    }

    public void setPolicyname(String policyname) {
        this.policyname = policyname;
    }

    @Transient
    private  List<TagEntity> ptags;

    public List<TagEntity> getPtags() {
        return ptags;
    }

    @Transient
    private  List<Accuratetag> atags;

    public List<Accuratetag> getAtags() {
        return atags;
    }

    public void setAtags(List<Accuratetag> atags) {
        this.atags = atags;
    }

    public String getSbstate() {
        return sbstate;
    }

    public void setSbstate(String sbstate) {
        this.sbstate = sbstate;
    }

    public void setPtags(List<TagEntity> ptags) {
        this.ptags = ptags;
    }

    public List<Policymateriallist> getMateriallists() {
        return materiallists;
    }

    public void setMateriallists(List<Policymateriallist> materiallists) {
        this.materiallists = materiallists;
    }

    public String getDeclareconditions() {
        return declareconditions;
    }

    public void setDeclareconditions(String declareconditions) {
        this.declareconditions = declareconditions;
    }

    public String getPldtailid() {
        return pldtailid;
    }

    public void setPldtailid(String pldtailid) {
        this.pldtailid = pldtailid;
    }

    public String getPlfidid() {
        return plfidid;
    }

    public void setPlfidid(String plfidid) {
        this.plfidid = plfidid;
    }

    public Integer getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(Integer serialnum) {
        this.serialnum = serialnum;
    }

    public String getPldserialnum() {
        return pldserialnum;
    }

    public void setPldserialnum(String pldserialnum) {
        this.pldserialnum = pldserialnum;
    }

    public String getPlcategoryno() {
        return plcategoryno;
    }

    public void setPlcategoryno(String plcategoryno) {
        this.plcategoryno = plcategoryno;
    }

    public String getNplcategory() {
        return nplcategory;
    }

    public void setNplcategory(String nplcategory) {
        this.nplcategory = nplcategory;
    }

    public String getPlsubclassnum() {
        return plsubclassnum;
    }

    public void setPlsubclassnum(String plsubclassnum) {
        this.plsubclassnum = plsubclassnum;
    }

    public String getPlsubclassname() {
        return plsubclassname;
    }

    public void setPlsubclassname(String plsubclassname) {
        this.plsubclassname = plsubclassname;
    }

    public String getPlconditions() {
        return plconditions;
    }

    public void setPlconditions(String plconditions) {
        this.plconditions = plconditions;
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription;
    }

    public Double getRamount() {
        return ramount;
    }

    public void setRamount(Double ramount) {
        this.ramount = ramount;
    }

    public String getPevaluationdepartment() {
        return pevaluationdepartment;
    }

    public void setPevaluationdepartment(String pevaluationdepartment) {
        this.pevaluationdepartment = pevaluationdepartment;
    }

    public String getIsdefine() {
        return isdefine;
    }

    public void setIsdefine(String isdefine) {
        this.isdefine = isdefine;
    }

    public String getFeaunit() {
        return feaunit;
    }

    public void setFeaunit(String feaunit) {
        this.feaunit = feaunit;
    }

    public String getThpath() {
        return thpath;
    }

    public void setThpath(String thpath) {
        this.thpath = thpath;
    }

    public String getCjtype() {
        return cjtype;
    }

    public void setCjtype(String cjtype) {
        this.cjtype = cjtype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Transient
    private String nplcategorystr;
    @Transient
    private String plsubclassnamestr;
    @Transient
    private String plconditionsstr;
    @Transient
    private String rdescriptionstr;

    public String getNplcategorystr() {
        return nplcategorystr;
    }

    public void setNplcategorystr(String nplcategorystr) {
        this.nplcategorystr = nplcategorystr;
    }

    public String getPlsubclassnamestr() {
        return plsubclassnamestr;
    }

    public void setPlsubclassnamestr(String plsubclassnamestr) {
        this.plsubclassnamestr = plsubclassnamestr;
    }

    public String getPlconditionsstr() {
        return plconditionsstr;
    }

    public void setPlconditionsstr(String plconditionsstr) {
        this.plconditionsstr = plconditionsstr;
    }

    public String getRdescriptionstr() {
        return rdescriptionstr;
    }

    public void setRdescriptionstr(String rdescriptionstr) {
        this.rdescriptionstr = rdescriptionstr;
    }

    public String getMinamount() {
        return minamount;
    }

    public void setMinamount(String minamount) {
        this.minamount = minamount;
    }

    public String getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(String maxamount) {
        this.maxamount = maxamount;
    }
}
