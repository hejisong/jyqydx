package com.ywjc.glsyjs.bigdata.entity;




import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "tenthousandsquareprojectinformat")
public class Tenthousandsquareprojectinformat extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;


	@Id
	@Column(name = "id")
	private String id;//主键
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "companyname")
	private String companyname;//企业名称
	@Column(name = "name_of_fair")
	private String name_of_fair;//展会名称
	@Column(name = "exhibition_time")
	private Date exhibition_time;//展会时间
	@Column(name = "years")
	private String years;//年份
	@Column(name = "exhibition_content")
	private String exhibition_content;//展出内容
	@Column(name = "category")
	private String category;//类别
	@Column(name = "exhibition_location")
	private String exhibition_location;//展会地点
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

	public String getName_of_fair() {
		return name_of_fair;
	}

	public void setName_of_fair(String name_of_fair) {
		this.name_of_fair = name_of_fair;
	}

	public Date getExhibition_time() {
		return exhibition_time;
	}

	public void setExhibition_time(Date exhibition_time) {
		this.exhibition_time = exhibition_time;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getExhibition_content() {
		return exhibition_content;
	}

	public void setExhibition_content(String exhibition_content) {
		this.exhibition_content = exhibition_content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getExhibition_location() {
		return exhibition_location;
	}

	public void setExhibition_location(String exhibition_location) {
		this.exhibition_location = exhibition_location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public Tenthousandsquareprojectinformat setValue(Tenthousandsquareprojectinformat other){
		this.companyid=other.getCompanyid();
		this.companyname=other.getCompanyname();
		this.name_of_fair=other.getName_of_fair();
		this.exhibition_time=other.getExhibition_time();
		this.years=other.getYears();
		this.exhibition_content=other.getExhibition_content();
		this.category=other.getCategory();
		this.exhibition_location=other.getExhibition_location();
		return this;
	}
}
