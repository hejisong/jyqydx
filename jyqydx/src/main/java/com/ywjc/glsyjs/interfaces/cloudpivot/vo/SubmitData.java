package com.ywjc.glsyjs.interfaces.cloudpivot.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SubmitData {

    /**
     * 企业名称
     */
    @JSONField(name = "ENTERPRISENAME")
    private String ENTERPRISENAME;
    /**
     * 统一社会信用代码
     */
    @JSONField(name = "USCREDITCODE")
    private String USCREDITCODE;
    /**
     * 政策大类名称
     */
    @JSONField(name = "NPLCATEGORY")
    private String NPLCATEGORY;
    /**
     * 政策小类名称
     */
    @JSONField(name = "PLSUBCLASSNAME")

    private String PLSUBCLASSNAME;
    /**
     * 政策条件
     */
    @JSONField(name = "PLCONDITIONS")
    private String PLCONDITIONS;
    /**
     * 奖励描述
     */
    @JSONField(name = "RDESCRIPTION")
    private String RDESCRIPTION;
    /**
     * 政策名称
     */
    @JSONField(name = "POLICYNAME")
    private String POLICYNAME;
    /**
     * 奖励金额
     */
    @JSONField(name = "RAMOUNT")
    private String RAMOUNT;

    private String applyid;
    /**
     * 申请人姓名
     */
    private String applyname;
    /**
     * 申请人身份证号码
     */
    private String applycard;
    /**
     * 申请时间
     */
    private String applydate;

    private String user_department;
    private String user_park;
    private String user_finance;

    @JSONField(name = "Firstuser")
    private String Firstuser;

    /**
     * 附件
     */
    @JSONField(name = "Attachment")
    private List<Attachment> Attachment;

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }

    public String getFirstuser() {
        return Firstuser;
    }

    public void setFirstuser(String firstuser) {
        Firstuser = firstuser;
    }

    public String getENTERPRISENAME() {
        return ENTERPRISENAME;
    }

    public String getUser_department() {
        return user_department;
    }

    public void setUser_department(String user_department) {
        this.user_department = user_department;
    }

    public String getUser_park() {
        return user_park;
    }

    public void setUser_park(String user_park) {
        this.user_park = user_park;
    }

    public String getUser_finance() {
        return user_finance;
    }

    public void setUser_finance(String user_finance) {
        this.user_finance = user_finance;
    }

    public void setENTERPRISENAME(String ENTERPRISENAME) {
        this.ENTERPRISENAME = ENTERPRISENAME;
    }

    public String getUSCREDITCODE() {
        return USCREDITCODE;
    }

    public void setUSCREDITCODE(String USCREDITCODE) {
        this.USCREDITCODE = USCREDITCODE;
    }

    public String getNPLCATEGORY() {
        return NPLCATEGORY;
    }

    public void setNPLCATEGORY(String NPLCATEGORY) {
        this.NPLCATEGORY = NPLCATEGORY;
    }

    public String getPLSUBCLASSNAME() {
        return PLSUBCLASSNAME;
    }

    public void setPLSUBCLASSNAME(String PLSUBCLASSNAME) {
        this.PLSUBCLASSNAME = PLSUBCLASSNAME;
    }

    public String getPLCONDITIONS() {
        return PLCONDITIONS;
    }

    public void setPLCONDITIONS(String PLCONDITIONS) {
        this.PLCONDITIONS = PLCONDITIONS;
    }

    public String getRDESCRIPTION() {
        return RDESCRIPTION;
    }

    public void setRDESCRIPTION(String RDESCRIPTION) {
        this.RDESCRIPTION = RDESCRIPTION;
    }

    public String getPOLICYNAME() {
        return POLICYNAME;
    }

    public void setPOLICYNAME(String POLICYNAME) {
        this.POLICYNAME = POLICYNAME;
    }

    public String getRAMOUNT() {
        return RAMOUNT;
    }

    public void setRAMOUNT(String RAMOUNT) {
        this.RAMOUNT = RAMOUNT;
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname;
    }

    public String getApplycard() {
        return applycard;
    }

    public void setApplycard(String applycard) {
        this.applycard = applycard;
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    @Override
    public String toString() {
        return "SubmitDataVo{" +
                "ENTERPRISENAME='" + ENTERPRISENAME + '\'' +
                ", USCREDITCODE='" + USCREDITCODE + '\'' +
                ", NPLCATEGORY='" + NPLCATEGORY + '\'' +
                ", PLSUBCLASSNAME='" + PLSUBCLASSNAME + '\'' +
                ", PLCONDITIONS='" + PLCONDITIONS + '\'' +
                ", RDESCRIPTION='" + RDESCRIPTION + '\'' +
                ", POLICYNAME='" + POLICYNAME + '\'' +
                ", RAMOUNT='" + RAMOUNT + '\'' +
                ", applyname='" + applyname + '\'' +
                ", applycard='" + applycard + '\'' +
                ", applydate='" + applydate + '\'' +
                '}';
    }

    public List<com.ywjc.glsyjs.interfaces.cloudpivot.vo.Attachment> getAttachment() {
        return Attachment;
    }

    public void setAttachment(List<com.ywjc.glsyjs.interfaces.cloudpivot.vo.Attachment> attachment) {
        Attachment = attachment;
    }
}
