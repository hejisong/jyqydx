package com.ywjc.glsyjs.Policyservices.entity;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "payinfo")
public class Payinfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "infoid")	
    private String infoid;
	@Column(name = "plyresultid")	
    private String plyresultid;
	@Column(name = "companyid")	
    private String companyid;
	@Column(name = "transid")	
    private String transid;
	@Column(name = "inpaymentaccount")	
    private String inpaymentaccount;
	@Column(name = "outpaymentaccount")	
    private String outpaymentaccount;
	@Column(name = "amount")	
    private String amount;
	@Column(name = "transstate")	
    private String transstate;
	@Column(name = "remark")	
    private String remark;
	@Column(name = "createtime")	
    private Date createtime;
	@Column(name = "updatetime")	
    private Date updatetime;

	@Column(name = "fileid")
	private String fileid;

	@Column(name = "filename")
	private String filename;

	@Column(name = "outpayaccount")
	private String outpayaccount;
	@Column(name = "inpayaccount")
	private String inpayaccount;


	@Column(name = "outpayname")
	private String outpayname;

	@Column(name = "inpayname")
	private String inpayname;
	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getPlyresultid() {
		return plyresultid;
	}

	public void setPlyresultid(String plyresultid) {
		this.plyresultid = plyresultid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public String getInpaymentaccount() {
		return inpaymentaccount;
	}

	public void setInpaymentaccount(String inpaymentaccount) {
		this.inpaymentaccount = inpaymentaccount;
	}

	public String getOutpaymentaccount() {
		return outpaymentaccount;
	}

	public void setOutpaymentaccount(String outpaymentaccount) {
		this.outpaymentaccount = outpaymentaccount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransstate() {
		return transstate;
	}

	public void setTransstate(String transstate) {
		this.transstate = transstate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOutpayname() {
		return outpayname;
	}

	public void setOutpayname(String outpayname) {
		this.outpayname = outpayname;
	}

	public String getInpayname() {
		return inpayname;
	}

	public void setInpayname(String inpayname) {
		this.inpayname = inpayname;
	}

	public String getOutpayaccount() {
		return outpayaccount;
	}

	public void setOutpayaccount(String outpayaccount) {
		this.outpayaccount = outpayaccount;
	}

	public String getInpayaccount() {
		return inpayaccount;
	}

	public void setInpayaccount(String inpayaccount) {
		this.inpayaccount = inpayaccount;
	}
}
