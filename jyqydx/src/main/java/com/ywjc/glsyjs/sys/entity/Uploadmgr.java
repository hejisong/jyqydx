package com.ywjc.glsyjs.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "uploadmgr")
public class Uploadmgr extends BaseEntity {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")	
    private String id;
	@Column(name = "infoid")	
    private String infoid;
	@Column(name = "infotype")	
    private String infotype;
	@Column(name = "actiontype")	
    private String actiontype;
	@Column(name = "year")	
    private String year;
	@Column(name = "cdate")	
    private Date cdate;
	@Column(name = "infoupdate")	
    private Date infoupdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	public String getInfotype() {
		return infotype;
	}
	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}
	
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getInfoupdate() {
		return infoupdate;
	}
	public void setInfoupdate(Date infoupdate) {
		this.infoupdate = infoupdate;
	}
	
}