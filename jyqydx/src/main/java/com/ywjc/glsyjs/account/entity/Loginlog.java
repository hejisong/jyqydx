package com.ywjc.glsyjs.account.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "loginlog")
public class Loginlog extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "loginlogid")	
    private String loginlogid;
	@Column(name = "userid")
	private String userid;
	@Column(name = "loginname")
    private String loginname;
	@Column(name = "username")	
    private String username;
	@Column(name = "logintime")	
    private Date logintime;
	@Column(name = "unitname")
    private String unitname;
	@Column(name = "loginip")	
    private String loginip;
	@Column(name = "url")
	private String url;
	@Column(name = "onoff")
	private String onoff;
	
	public Loginlog(){
		
	}

	public Loginlog(String loginlogid,String userid, String loginname, String username, Date logintime,String url,
			 String unitname, String loginip,String onoff) {
		this.loginlogid=loginlogid;
		this.loginname=loginname;
		this.username=username;
		this.logintime=logintime;
		this.userid=userid;
		this.url=url;
		this.unitname=unitname;
		this.loginip=loginip;
		this.onoff=onoff;
	}

	public String getString(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(this.logintime)+"   "+this.loginip+"   "+this.loginname+"   "+this.username+"   "+this.unitname ;
		
	}
	
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getLoginlogid() {
		return loginlogid;
	}

	public void setLoginlogid(String loginlogid) {
		this.loginlogid = loginlogid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOnoff() {
		return onoff;
	}

	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
}
