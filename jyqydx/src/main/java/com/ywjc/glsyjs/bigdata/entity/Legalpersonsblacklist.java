package com.ywjc.glsyjs.bigdata.entity;



import com.ywjc.glsyjs.core.entity.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "legalpersonsblacklist")
public class Legalpersonsblacklist extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "id")
	private String id;//法人黑名单主键
	@Column(name = "legalpersons")
	private String legalpersons;//法人名称
	@Column(name = "legalcardid")
	private String legalcardid;//法人身份证号
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "company_name")
	private String company_name;//企业名称
	@Column(name = "uscreditcode")
	private String uscreditcode;//统一社会信用代码
	@Column(name = "type")
	private String type;//类型
	@Column(name = "record_id")
	private String record_id;//记录ID
	@Column(name = "serious_confirm_date")
	private Date serious_confirm_date;//确认严重失信时间
	@Column(name = "recognized_unit")
	private String recognized_unit;//认定单位
	@Column(name = "data_source_unit")
	private String data_source_unit;//数据来源单位
	@Column(name = "major_untrustworthy_facts")
	private String major_untrustworthy_facts;//主要失信事实
	@Column(name = "state")
	private String state;//状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLegalpersons() {
		return legalpersons;
	}

	public void setLegalpersons(String legalpersons) {
		this.legalpersons = legalpersons;
	}

	public String getLegalcardid() {
		return legalcardid;
	}

	public void setLegalcardid(String legalcardid) {
		this.legalcardid = legalcardid;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getUscreditcode() {
		return uscreditcode;
	}

	public void setUscreditcode(String uscreditcode) {
		this.uscreditcode = uscreditcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public Date getSerious_confirm_date() {
		return serious_confirm_date;
	}

	public void setSerious_confirm_date(Date serious_confirm_date) {
		this.serious_confirm_date = serious_confirm_date;
	}

	public String getRecognized_unit() {
		return recognized_unit;
	}

	public void setRecognized_unit(String recognized_unit) {
		this.recognized_unit = recognized_unit;
	}

	public String getData_source_unit() {
		return data_source_unit;
	}

	public void setData_source_unit(String data_source_unit) {
		this.data_source_unit = data_source_unit;
	}

	public String getMajor_untrustworthy_facts() {
		return major_untrustworthy_facts;
	}

	public void setMajor_untrustworthy_facts(String major_untrustworthy_facts) {
		this.major_untrustworthy_facts = major_untrustworthy_facts;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}



	public Legalpersonsblacklist setValue(Legalpersonsblacklist other){
		this.legalpersons=other.getLegalpersons();
		this.legalcardid=other.getLegalcardid();
		this.companyid=other.getCompanyid();
		this.company_name=other.getCompany_name();
		this.uscreditcode=other.getUscreditcode();
		this.type=other.getType();
		this.record_id=other.getRecord_id();
		this.serious_confirm_date=other.getSerious_confirm_date();
		this.recognized_unit=other.getRecognized_unit();
		this.data_source_unit=other.getData_source_unit();
		this.major_untrustworthy_facts=other.getMajor_untrustworthy_facts();
		return this;
	}
}
