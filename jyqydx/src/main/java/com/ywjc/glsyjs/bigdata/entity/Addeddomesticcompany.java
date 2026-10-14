package com.ywjc.glsyjs.bigdata.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "addeddomesticcompany")
public class Addeddomesticcompany extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;


	@Id
	@Column(name = "id")
	private String id;//新增上市公司主键
	@Column(name = "stock_code")
	private String stock_code;//股票代码
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "company_name")
	private String company_name;//企业名称
	@Column(name = "region")
	private String region;//地区
	@Column(name = "listing_date")
	private Date listing_date;//上市日期
	@Column(name = "state")
	private String state;//状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStock_code() {
		return stock_code;
	}

	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Date getListing_date() {
		return listing_date;
	}

	public void setListing_date(Date listing_date) {
		this.listing_date = listing_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public Addeddomesticcompany setValue(Addeddomesticcompany other){
		this.stock_code=other.getStock_code();
		this.companyid=other.getCompanyid();
		this.company_name=other.getCompany_name();
		this.region=other.getRegion();
		this.listing_date=other.getListing_date();
		return this;
	}

}
