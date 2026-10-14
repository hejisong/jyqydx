package com.ywjc.glsyjs.bigdata.entity;


import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "businessipinfotrademark")
public class Businessipinfotrademark extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "id")
	private String id;//主键ID
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "companyname")
	private String companyname;//企业名称
	@Column(name = "trademarkid")
	private String trademarkid;//商标ID
	@Column(name = "trademarkname")
	private String trademarkname;//商标名称
	@Column(name = "status")
	private String status;//状态
	@Column(name = "reg_number")
	private String reg_number;//注册号
	@Column(name = "apply_date")
	private Date apply_date;//申请日期
	@Column(name = "period")
	private String period;//专用权期限
	@Column(name = "agent")
	private String agent;//代理人名称
	@Column(name = "deletestate")
	private String deletestate;//删除状态

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

	public String getTrademarkid() {
		return trademarkid;
	}

	public void setTrademarkid(String trademarkid) {
		this.trademarkid = trademarkid;
	}

	public String getTrademarkname() {
		return trademarkname;
	}

	public void setTrademarkname(String trademarkname) {
		this.trademarkname = trademarkname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReg_number() {
		return reg_number;
	}

	public void setReg_number(String reg_number) {
		this.reg_number = reg_number;
	}

	public Date getApply_date() {
		return apply_date;
	}

	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getDeletestate() {
		return deletestate;
	}

	public void setDeletestate(String deletestate) {
		this.deletestate = deletestate;
	}


	public Businessipinfotrademark setValue(Businessipinfotrademark other){
		this.companyid=other.getCompanyid();
		this.companyname=other.getCompanyname();
		this.trademarkid=other.getTrademarkid();
		this.trademarkname=other.getTrademarkname();
		this.status=other.getStatus();
		this.reg_number=other.getReg_number();
		this.apply_date=other.getApply_date();
		this.period=other.getPeriod();
		this.agent=other.getAgent();
		return this;
	}
}
