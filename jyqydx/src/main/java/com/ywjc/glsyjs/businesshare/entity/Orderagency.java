package com.ywjc.glsyjs.businesshare.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderagency")
public class Orderagency extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "orderid")	
    private String orderid;
	@Column(name = "ordermanid")	
    private String ordermanid;
	@Column(name = "orderman")	
    private String orderman;
	@Column(name = "contact")	
    private String contact;
	@Column(name = "agencymanid")	
    private String agencymanid;
	@Column(name = "agencyman")	
    private String agencyman;
	@Column(name = "ordercompanyid")
    private String ordercompanyid;
	@Column(name = "ordercompany")
	private String ordercompany;
	@Column(name = "ordertime")	
    private Date ordertime;
	@Column(name = "remark")	
    private String remark;
	// 0未处理 1已处理
	@Column(name = "state")
	private String state;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getOrdermanid() {
		return ordermanid;
	}

	public void setOrdermanid(String ordermanid) {
		this.ordermanid = ordermanid;
	}

	public String getOrderman() {
		return orderman;
	}

	public void setOrderman(String orderman) {
		this.orderman = orderman;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAgencymanid() {
		return agencymanid;
	}

	public void setAgencymanid(String agencymanid) {
		this.agencymanid = agencymanid;
	}

	public String getAgencyman() {
		return agencyman;
	}

	public void setAgencyman(String agencyman) {
		this.agencyman = agencyman;
	}

	public String getOrdercompany() {
		return ordercompany;
	}

	public void setOrdercompany(String ordercompany) {
		this.ordercompany = ordercompany;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrdercompanyid() {
		return ordercompanyid;
	}

	public void setOrdercompanyid(String ordercompanyid) {
		this.ordercompanyid = ordercompanyid;
	}
}
