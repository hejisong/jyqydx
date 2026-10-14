package com.ywjc.glsyjs.sys.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "generalcodename")
public class Generalcodename extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "codetableid", length = 10, nullable = false, unique = true)
	private String codetableid;
	
	@Column(name = "codetablename")
	private String codetablename;
	
	@Column(name = "codetabledesc")
	private String codetabledesc;
	
	@Column(name = "updatedate")
	private Date updatedate;
	
	@Column(name = "isused")
	private String isused;
	
	@OneToMany(mappedBy = "generalcodename", cascade = { CascadeType.ALL })
	private List<Generalcode> generalcodes;

	public List<Generalcode> getGeneralcodes() {
		return generalcodes;
	}

	public void setGeneralcodes(List<Generalcode> generalcodes) {
		this.generalcodes = generalcodes;
	}

	public String getCodetableid() {
		return codetableid;
	}

	public void setCodetableid(String codetableid) {
		this.codetableid = codetableid;
	}

	public String getCodetablename() {
		return codetablename;
	}

	public void setCodetablename(String codetablename) {
		this.codetablename = codetablename;
	}

	public String getCodetabledesc() {
		return codetabledesc;
	}

	public void setCodetabledesc(String codetabledesc) {
		this.codetabledesc = codetabledesc;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}
}
