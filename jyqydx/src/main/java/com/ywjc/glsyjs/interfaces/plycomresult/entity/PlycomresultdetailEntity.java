package com.ywjc.glsyjs.interfaces.plycomresult.entity;

import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.entity.BaseEntity;
import com.ywjc.glsyjs.interfaces.flow.entity.FlowProcessEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "plycomresultdetail")
public class PlycomresultdetailEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "plycomresultdetailid")
    private String plycomresultdetailid;

    //
    @Column(name = "companyid")
    private String companyid;

    //
    @Column(name = "number")
    private String number;

    //
    @Column(name = "enterprisenum")
    private String enterprisenum;

    //企业名称
    @Column(name = "enterprisename")
    private String enterprisename;

    //统一社会信用代码
    @Column(name = "uscreditcode")
    private String uscreditcode;

    //政策大类编号
    @Column(name = "policycategorynum")
    private String policycategorynum;

    //政策大类名称
    @Column(name = "policycategoryname")
    private String policycategoryname;

    //政策小类编号
    @Column(name = "policysubclassno")
    private String policysubclassno;

    //政策小类名称
    @Column(name = "policysubclassname")
    private String policysubclassname;

    //政策条件
    @Column(name = "policyconditions")
    private String policyconditions;

    //奖励
    @Column(name = "rdescription")
    private String rdescription;

    //年份
    @Column(name = "particularyear")
    private String particularyear;

    //享受政策类别（1 免申即享 2 少量补录即享 3 满足条件即享 4 预告）
    @Column(name = "enjoypolicycategory")
    private String enjoypolicycategory;

    //金额
    @Column(name = "amount")
    private String amount;

    //状态 0或空 已录入 1提交审批 2审批通过 3退回 4驳回 5已支付，兑现完成 6已上传凭证，待支付 7审批中
    @Column(name = "cashflag")
    private String cashflag;

    //兑现日期
    @Column(name = "cashtime")
    private String cashtime;

    //发证时间
    @Column(name = "certificatetime")
    private String certificatetime;

    //政策文件id
    @Column(name = "plfidid")
    private String plfidid;
    //政策文件名
    @Column(name = "policyname")
    private String policyname;
    //专利id
    @Column(name = "companypatentid")
    private String companypatentid;
    //专利名称
    @Column(name = "patentname")
    private String patentname;

    //政策id
    @Column(name = "pldtailid")
    private String pldtailid;
    //是否生成流程 0-未生成  1-已生成 2-已推送
    @Column(name = "isflow")
    private String isflow;

    //是否线下纸质提交：1是 0或空否
    @Column(name = "issubmitoffline")
    private String issubmitoffline;

   //创建时间
    @Column(name = "inputtime")
    private Date inputtime;

    //更新时间
    @Column(name = "updatetime")
    private Date updatetime;



    @Transient
    private Uploadfile uploadfile;

    @Transient
    private FlowProcessEntity flowprocess;
    @Transient
    private String policydeclar;

    public String getPolicydeclar() {
        return policydeclar;
    }

    public void setPolicydeclar(String policydeclar) {
        this.policydeclar = policydeclar;
    }

    public FlowProcessEntity getFlowprocess() {
        return flowprocess;
    }

    public void setFlowprocess(FlowProcessEntity flowprocess) {
        this.flowprocess = flowprocess;
    }

    public String getIsflow() {
        return isflow;
    }

    public void setIsflow(String isflow) {
        this.isflow = isflow;
    }

    public String getPlycomresultdetailid() {
        return plycomresultdetailid;
    }

    public void setPlycomresultdetailid(String plycomresultdetailid) {
        this.plycomresultdetailid = plycomresultdetailid;
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

    public String getUscreditcode() {
        return uscreditcode;
    }

    public void setUscreditcode(String uscreditcode) {
        this.uscreditcode = uscreditcode;
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

    public String getPolicysubclassname() {
        return policysubclassname;
    }

    public void setPolicysubclassname(String policysubclassname) {
        this.policysubclassname = policysubclassname;
    }

    public String getPolicyconditions() {
        return policyconditions;
    }

    public void setPolicyconditions(String policyconditions) {
        this.policyconditions = policyconditions;
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription;
    }

    public String getParticularyear() {
        return particularyear;
    }

    public void setParticularyear(String particularyear) {
        this.particularyear = particularyear;
    }

    public String getEnjoypolicycategory() {
        return enjoypolicycategory;
    }

    public void setEnjoypolicycategory(String enjoypolicycategory) {
        this.enjoypolicycategory = enjoypolicycategory;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCashflag() {
        return cashflag;
    }

    public void setCashflag(String cashflag) {
        this.cashflag = cashflag;
    }

    public String getCashtime() {
        return cashtime;
    }

    public void setCashtime(String cashtime) {
        this.cashtime = cashtime;
    }

    public String getCertificatetime() {
        return certificatetime;
    }

    public void setCertificatetime(String certificatetime) {
        this.certificatetime = certificatetime;
    }

    public String getPlfidid() {
        return plfidid;
    }

    public void setPlfidid(String plfidid) {
        this.plfidid = plfidid;
    }

    public String getPolicyname() {
        return policyname;
    }

    public void setPolicyname(String policyname) {
        this.policyname = policyname;
    }

    public String getCompanypatentid() {
        return companypatentid;
    }

    public void setCompanypatentid(String companypatentid) {
        this.companypatentid = companypatentid;
    }

    public String getPatentname() {
        return patentname;
    }

    public void setPatentname(String patentname) {
        this.patentname = patentname;
    }

    public Uploadfile getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(Uploadfile uploadfile) {
        this.uploadfile = uploadfile;
    }

    public String getPldtailid() {
        return pldtailid;
    }

    public void setPldtailid(String pldtailid) {
        this.pldtailid = pldtailid;
    }

    public String getIssubmitoffline() {
        return issubmitoffline;
    }

    public void setIssubmitoffline(String issubmitoffline) {
        this.issubmitoffline = issubmitoffline;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }



    public PlycomresultdetailEntity() {

    }

    public PlycomresultdetailEntity(String plycomresultdetailid,
                                    String companyid,
                                    String number,
                                    String enterprisenum,
                                    String enterprisename,
                                    String policycategorynum,
                                    String policycategoryname,
                                    String policysubclassno,
                                    String policysubclassname,
                                    String policyconditions,
                                    String particularyear,
                                    String enjoypolicycategory,
                                    String amount) {
        this.plycomresultdetailid = plycomresultdetailid;
        this.companyid = companyid;
        this.number = number;
        this.enterprisenum = enterprisenum;
        this.enterprisename = enterprisename;
        this.policycategorynum = policycategorynum;
        this.policycategoryname = policycategoryname;
        this.policysubclassno = policysubclassno;
        this.policysubclassname = policysubclassname;
        this.policyconditions = policyconditions;
        this.particularyear = particularyear;
        this.enjoypolicycategory = enjoypolicycategory;
        this.amount = amount;

    }

    public PlycomresultdetailEntity(String plycomresultdetailid,
                                    String policycategoryname,
                                    String policysubclassname,
                                    String policyconditions,
                                    String particularyear,
                                    String enjoypolicycategory,
                                    String amount) {
        this.plycomresultdetailid = plycomresultdetailid;
        this.policycategoryname = policycategoryname;
        this.policysubclassname = policysubclassname;
        this.policyconditions = policyconditions;
        this.particularyear = particularyear;
        this.enjoypolicycategory = enjoypolicycategory;
        this.amount = amount;
    }
}
