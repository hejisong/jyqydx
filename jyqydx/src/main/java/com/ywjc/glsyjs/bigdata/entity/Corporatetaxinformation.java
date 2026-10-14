package com.ywjc.glsyjs.bigdata.entity;



import com.ywjc.glsyjs.core.entity.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "corporatetaxinformation")
public class Corporatetaxinformation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "id")
	private String id;//企业税主键
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "companyname")
	private String companyname;//企业名称
	@Column(name = "uscreditcode")
	private String uscreditcode;//统一社会信用代码
	@Column(name = "fullname")
	private String fullname;//机构全称
	@Column(name = "tax")
	private String tax;//税种
	@Column(name = "tax_actually_paid")
	private String tax_actually_paid;//实缴税额
	@Column(name = "administrative_divisions")
	private String administrative_divisions;//行政区划
	@Column(name = "registered_address")
	private String registered_address;//注册地址
	@Column(name = "registration_date")
	private Date registration_date;//登记日期
	@Column(name = "tax_authorities")
	private String tax_authorities;//主管税务机关
	@Column(name = "tax_district")
	private String tax_district;//纳税区县
	@Column(name = "storage_time")
	private Date storage_time;//入库时间
	@Column(name = "state")
	private String state;//状态


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

	public String getUscreditcode() {
		return uscreditcode;
	}

	public void setUscreditcode(String uscreditcode) {
		this.uscreditcode = uscreditcode;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTax_actually_paid() {
		return tax_actually_paid;
	}

	public void setTax_actually_paid(String tax_actually_paid) {
		this.tax_actually_paid = tax_actually_paid;
	}

	public String getAdministrative_divisions() {
		return administrative_divisions;
	}

	public void setAdministrative_divisions(String administrative_divisions) {
		this.administrative_divisions = administrative_divisions;
	}

	public String getRegistered_address() {
		return registered_address;
	}

	public void setRegistered_address(String registered_address) {
		this.registered_address = registered_address;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	public String getTax_authorities() {
		return tax_authorities;
	}

	public void setTax_authorities(String tax_authorities) {
		this.tax_authorities = tax_authorities;
	}

	public String getTax_district() {
		return tax_district;
	}

	public void setTax_district(String tax_district) {
		this.tax_district = tax_district;
	}

	public Date getStorage_time() {
		return storage_time;
	}

	public void setStorage_time(Date storage_time) {
		this.storage_time = storage_time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public Corporatetaxinformation setValue(Corporatetaxinformation other){
		this.companyid=other.getCompanyid();
		this.companyname=other.getCompanyname();
		this.uscreditcode=other.getUscreditcode();
		this.fullname=other.getFullname();
		this.tax=other.getTax();
		this.tax_actually_paid=other.getTax_actually_paid();
		this.administrative_divisions=other.getAdministrative_divisions();
		this.registered_address=other.getRegistered_address();
		this.registration_date=other.getRegistration_date();
		this.tax_authorities=other.getTax_authorities();
		this.tax_district=other.getTax_district();
		this.storage_time=other.getStorage_time();
		return this;
	}

}
