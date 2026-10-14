package com.ywjc.glsyjs.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "userinfobf")
public class Userbf implements Serializable{
 
	private static final long serialVersionUID = -857881974826851181L;

	@Id
    private String id;
	
	@Column(name = "organid")
	private String organid;

	@Column(name = "usertype")	
	private String usertype;
	
	@Column(name = "username")	
	private String username;
	
	@Column(name="linknum")	
	private String linknum;
	
	@Column(name="linktel")	
    private String linktel;
	
	@Column(name="linkemail")	
    private String linkemail;
	
	@Column(name = "unitname")	
	private String unitname;
	
	@Column(name="loginname")
	private String loginname;	
	
	@JsonIgnore
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="salt")
	private String salt;
	
	@Column(name="isvaild",nullable = false, length = 1)
	private String isvaild;		
	
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date createDate;

	@Column(name = "modify_date", nullable = false)
	private Date modifyDate;
	
	@Column(name = "isnewpwd", length = 1)
	private String isnewpwd;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@Transient
	private String rolename;
	//是否有权接受短信
	@Column(name="accept_sms")
	private String accept_sms;
	
	@Column(name="linknum_en")
	private String linknum_en;
	@Column(name="linktel_en")
	private String linktel_en;
	@Column(name="linkemail_en")
	private String linkemail_en;

	//N为锁住，Y或空为解锁
	@Column(name="isunlock")
	private String isunlock;
	
	
	
	public String getIsunlock() {
		return isunlock;
	}

	public void setIsunlock(String isunlock) {
		this.isunlock = isunlock;
	}
	public String getLinknum_en() {
		return linknum_en;
	}

	public void setLinknum_en(String linknum_en) {
		this.linknum_en = linknum_en;
	}

	public String getLinktel_en() {
		return linktel_en;
	}

	public void setLinktel_en(String linktel_en) {
		this.linktel_en = linktel_en;
	}

	public String getLinkemail_en() {
		return linkemail_en;
	}

	public void setLinkemail_en(String linkemail_en) {
		this.linkemail_en = linkemail_en;
	}

	public String getAccept_sms() {
		return accept_sms;
	}

	public void setAccept_sms(String accept_sms) {
		this.accept_sms = accept_sms;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getOrganid() {
		return organid;
	}

	public void setOrganid(String organid) {
		this.organid = organid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSalt() {
		if(salt==null){
			salt = "";
		}
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIsvaild() {
		return isvaild;
	}

	public void setIsvaild(String isvaild) {
		this.isvaild = isvaild;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getIsnewpwd() {
		return isnewpwd;
	}

	public void setIsnewpwd(String isnewpwd) {
		this.isnewpwd = isnewpwd;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getLinknum() {
		return linknum;
	}

	public void setLinknum(String linknum) {
		this.linknum = linknum;
	}

	public String getLinktel() {
		return linktel;
	}

	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	public String getLinkemail() {
		return linkemail;
	}

	public void setLinkemail(String linkemail) {
		this.linkemail = linkemail;
	}

}
