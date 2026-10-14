package com.ywjc.datashare.entity;




import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "t_person")
public class Personinfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "userid")	
    private String userid;
	@Column(name = "personid")	
    private String personid;
	@Column(name = "username")	
    private String username;
	@Column(name = "displayname")	
    private String displayname;
	@Column(name = "userpassword")	
    private String userpassword;
	@Column(name = "personpro")	
    private String personpro;
	@Column(name = "title")	
    private String title;
	@Column(name = "postlevel")	
    private String postlevel;
	@Column(name = "sex")	
    private String sex;
	@Column(name = "mail")	
    private String mail;
	@Column(name = "telephonenumber")	
    private String telephonenumber;
	@Column(name = "mobile")	
    private String mobile;
	@Column(name = "ordernum")	
    private String ordernum;
	@Column(name = "identitynumber")	
    private String identitynumber;
	@Column(name = "status")	
    private String status;
	@Column(name = "hostname")	
    private String hostname;
	@Column(name = "hostip")	
    private String hostip;
	@Column(name = "remark")	
    private String remark;
	@Column(name = "departmentnumber")	
    private String departmentnumber;
	@Column(name = "ou")	
    private String ou;
	@Column(name = "otherum")	
    private String otherum;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getPersonpro() {
		return personpro;
	}

	public void setPersonpro(String personpro) {
		this.personpro = personpro;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostlevel() {
		return postlevel;
	}

	public void setPostlevel(String postlevel) {
		this.postlevel = postlevel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getIdentitynumber() {
		return identitynumber;
	}

	public void setIdentitynumber(String identitynumber) {
		this.identitynumber = identitynumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentnumber() {
		return departmentnumber;
	}

	public void setDepartmentnumber(String departmentnumber) {
		this.departmentnumber = departmentnumber;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getOtherum() {
		return otherum;
	}

	public void setOtherum(String otherum) {
		this.otherum = otherum;
	}
}
