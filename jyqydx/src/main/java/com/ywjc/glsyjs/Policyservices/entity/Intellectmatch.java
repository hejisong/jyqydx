package com.ywjc.glsyjs.Policyservices.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Intellectmatch {

    private String policyfileid;

    private String pldtailid;

    private String tagid;

    private String corptagid;

    private String corptype;

    private String status;

    private String minrecapital;

    private String maxrecapital;

    private Date startdate;

    private Date enddate;


    public String getPolicyfileid() {
        return policyfileid;
    }

    public void setPolicyfileid(String policyfileid) {
        this.policyfileid = policyfileid;
    }

    public String getPldtailid() {
        return pldtailid;
    }

    public void setPldtailid(String pldtailid) {
        this.pldtailid = pldtailid;
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public String getCorptagid() {
        return corptagid;
    }

    public void setCorptagid(String corptagid) {
        this.corptagid = corptagid;
    }

    public String getCorptype() {
        return corptype;
    }

    public void setCorptype(String corptype) {
        this.corptype = corptype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMinrecapital() {
        return minrecapital;
    }

    public void setMinrecapital(String minrecapital) {
        this.minrecapital = minrecapital;
    }

    public String getMaxrecapital() {
        return maxrecapital;
    }

    public void setMaxrecapital(String maxrecapital) {
        this.maxrecapital = maxrecapital;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
