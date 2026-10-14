package com.ywjc.glsyjs.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "userinfo")
public class User implements Serializable{
 
	private static final long serialVersionUID = -857881974826851181L;

	@Id
	@Column(name = "id")
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
	@Transient
	private String roleid;
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

	//0不在线 1在线
	@Column(name="isonline")
	private String isonline;
//0 被授权人，1 法人
	@Column(name="islegalperson")
	private String islegalperson;


	@Column(name = "personid")
	private String personid;

	@Column(name = "displayname")
	private String displayname;
	@Column(name = "personpro")
	private String personpro;
	@Column(name = "title")
	private String title;
	@Column(name = "postlevel")
	private String postlevel;
	@Column(name = "sex")
	private String sex;
//
	@Column(name = "telephonenumber")
	private String telephonenumber;

	@Column(name = "ordernum")
	private String ordernum;

	@Column(name = "status")
	private String status;
	@Column(name = "hostname")
	private String hostname;
	@Column(name = "hostip")
	private String hostip;
	@Column(name = "remark")
	private String remark;
	@Column(name = "otherum")
	private String otherum;
	//1共享数据 0或空 系统数据
	@Column(name = "datatype")
	private String datatype;
	@Column(name = "dduserid")
	private String dduserid;


	@Transient
	private Loginlog loginlog;

	@Transient
	private Loginlog lastloginlog;

	public String getIslegalperson() {
		return islegalperson;
	}

	public void setIslegalperson(String islegalperson) {
		this.islegalperson = islegalperson;
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

	public String getSalt() {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getAccept_sms() {
		return accept_sms;
	}

	public void setAccept_sms(String accept_sms) {
		this.accept_sms = accept_sms;
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

	public String getIsunlock() {
		return isunlock;
	}

	public void setIsunlock(String isunlock) {
		this.isunlock = isunlock;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public Loginlog getLoginlog() {
		return loginlog;
	}

	public void setLoginlog(Loginlog loginlog) {
		this.loginlog = loginlog;
	}

	public Loginlog getLastloginlog() {
		return lastloginlog;
	}

	public void setLastloginlog(Loginlog lastloginlog) {
		this.lastloginlog = lastloginlog;
	}


	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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



	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}



	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
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


	public String getOtherum() {
		return otherum;
	}

	public void setOtherum(String otherum) {
		this.otherum = otherum;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getDduserid() {
		return dduserid;
	}

	public void setDduserid(String dduserid) {
		this.dduserid = dduserid;
	}
}
