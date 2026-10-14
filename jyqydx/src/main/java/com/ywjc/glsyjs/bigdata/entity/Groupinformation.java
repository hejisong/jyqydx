package com.ywjc.glsyjs.bigdata.entity;



import com.ywjc.glsyjs.core.entity.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "groupinformation")
public class Groupinformation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "groupid")
	private String groupid;//集团信息主键
	@Column(name = "group_abbreviation")
	private String group_abbreviation;//集团简称
	@Column(name = "instrument_number")
	private String instrument_number;//文书编号
	@Column(name = "personnel")
	private String personnel;//人员
	@Column(name = "telephone")
	private String telephone;//电话
	@Column(name = "approva_number")
	private String approva_number;//名称核准号
	@Column(name = "registration_number")
	private String registration_number;//集团登记证号
	@Column(name = "registered_ttotal_capital")
	private String registered_ttotal_capital;//注册总资本
	@Column(name = "registered_capital")
	private String registered_capital;//注册资本
	@Column(name = "pcompany_registered_capital")
	private String pcompany_registered_capital;//母公司注册资本
	@Column(name = "pcompany_name")
	private String pcompany_name;//母公司名称
	@Column(name = "pcompany_domicile")
	private String pcompany_domicile;//母公司住所
	@Column(name = "pcompany_economic_nature")
	private String pcompany_economic_nature;//母公司经济性质
	@Column(name = "members_number")
	private String members_number;//成员总数
	@Column(name = "establishdate")
	private Date establishdate;//成立日期
	@Column(name = "registration_authority")
	private String registration_authority;//登记机关
	@Column(name = "belong_district")
	private String belong_district;//所属管区
	@Column(name = "agency_logo")
	private String agency_logo;//代理标志
	@Column(name = "group_name")
	private String group_name;//集团全称
	@Column(name = "approved_date")
	private Date approved_date;//核准日期
	@Column(name = "state")
	private String state;//状态



	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroup_abbreviation() {
		return group_abbreviation;
	}

	public void setGroup_abbreviation(String group_abbreviation) {
		this.group_abbreviation = group_abbreviation;
	}

	public String getInstrument_number() {
		return instrument_number;
	}

	public void setInstrument_number(String instrument_number) {
		this.instrument_number = instrument_number;
	}

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getApprova_number() {
		return approva_number;
	}

	public void setApprova_number(String approva_number) {
		this.approva_number = approva_number;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getRegistered_ttotal_capital() {
		return registered_ttotal_capital;
	}

	public void setRegistered_ttotal_capital(String registered_ttotal_capital) {
		this.registered_ttotal_capital = registered_ttotal_capital;
	}

	public String getRegistered_capital() {
		return registered_capital;
	}

	public void setRegistered_capital(String registered_capital) {
		this.registered_capital = registered_capital;
	}

	public String getPcompany_registered_capital() {
		return pcompany_registered_capital;
	}

	public void setPcompany_registered_capital(String pcompany_registered_capital) {
		this.pcompany_registered_capital = pcompany_registered_capital;
	}

	public String getPcompany_name() {
		return pcompany_name;
	}

	public void setPcompany_name(String pcompany_name) {
		this.pcompany_name = pcompany_name;
	}

	public String getPcompany_domicile() {
		return pcompany_domicile;
	}

	public void setPcompany_domicile(String pcompany_domicile) {
		this.pcompany_domicile = pcompany_domicile;
	}

	public String getPcompany_economic_nature() {
		return pcompany_economic_nature;
	}

	public void setPcompany_economic_nature(String pcompany_economic_nature) {
		this.pcompany_economic_nature = pcompany_economic_nature;
	}

	public String getMembers_number() {
		return members_number;
	}

	public void setMembers_number(String members_number) {
		this.members_number = members_number;
	}

	public Date getEstablishdate() {
		return establishdate;
	}

	public void setEstablishdate(Date establishdate) {
		this.establishdate = establishdate;
	}

	public String getRegistration_authority() {
		return registration_authority;
	}

	public void setRegistration_authority(String registration_authority) {
		this.registration_authority = registration_authority;
	}

	public String getBelong_district() {
		return belong_district;
	}

	public void setBelong_district(String belong_district) {
		this.belong_district = belong_district;
	}

	public String getAgency_logo() {
		return agency_logo;
	}

	public void setAgency_logo(String agency_logo) {
		this.agency_logo = agency_logo;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public Date getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(Date approved_date) {
		this.approved_date = approved_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Groupinformation setValue(Groupinformation other){
		this.group_abbreviation=other.getGroup_abbreviation();
		this.instrument_number=other.getInstrument_number();
		this.personnel=other.getPersonnel();
		this.telephone=other.getTelephone();
		this.approva_number=other.getApprova_number();
		this.registration_number=other.getRegistration_number();
		this.registered_ttotal_capital=other.getRegistered_ttotal_capital();
		this.registered_capital=other.getRegistered_capital();
		this.pcompany_registered_capital=other.getPcompany_registered_capital();
		this.pcompany_name=other.getPcompany_name();
		this.pcompany_domicile=other.getPcompany_domicile();
		this.pcompany_economic_nature=other.getPcompany_economic_nature();
		this.members_number=other.getMembers_number();
		this.establishdate=other.getEstablishdate();
		this.registration_authority=other.getRegistration_authority();
		this.belong_district=other.getBelong_district();
		this.agency_logo=other.getAgency_logo();
		this.group_name=other.getGroup_name();
		this.approved_date=other.getApproved_date();
		return this;
	}
}
