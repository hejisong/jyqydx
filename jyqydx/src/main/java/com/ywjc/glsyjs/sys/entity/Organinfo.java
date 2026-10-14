package com.ywjc.glsyjs.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "organinfo")
public class Organinfo  extends BaseEntity implements Serializable {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")	
    private String id;
	@Column(name = "orgcode")	
    private String orgcode;
	@Column(name = "orgname")	
    private String orgname;
	@Column(name = "superdepartment")
	private String superdepartment;
	@Column(name = "orgaddr")
	private String orgaddr;
	@Column(name = "linkman")
	private String linkman;
	@Column(name = "linkphone")
	private String linkphone;
	@Column(name = "updatedate")
    private Date updatedate;
	@Column(name = "createtime")
    private Date createtime;

	@Column(name = "displayname")
	private String displayname;
	@Column(name = "mail")
	private String mail;
	@Column(name = "postalcode")
	private String postalcode;
	@Column(name = "telephonenumber")
	private String telephonenumber;
	@Column(name = "facsimiletelephonenumber")
	private String facsimiletelephonenumber;
	@Column(name = "edorder")
	private String edorder;
	@Column(name = "status")
	private String status;
	@Column(name = "remark")
	private String remark;
	@Column(name = "managerid")
	private String managerid;
	@Column(name = "isleaf")
	private String isleaf;
	@Column(name = "fullouw")
	private String fullouw;
	@Column(name = "ddorgid")
	private String ddorgid;

	@Transient
	private String superorg;

	public String getSuperorg() {
		return superorg;
	}

	public void setSuperorg(String superorg) {
		this.superorg = superorg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getFacsimiletelephonenumber() {
		return facsimiletelephonenumber;
	}

	public void setFacsimiletelephonenumber(String facsimiletelephonenumber) {
		this.facsimiletelephonenumber = facsimiletelephonenumber;
	}

	public String getEdorder() {
		return edorder;
	}

	public void setEdorder(String edorder) {
		this.edorder = edorder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public String getFullouw() {
		return fullouw;
	}

	public void setFullouw(String fullouw) {
		this.fullouw = fullouw;
	}

	public String getSuperdepartment() {
		return superdepartment;
	}

	public void setSuperdepartment(String superdepartment) {
		this.superdepartment = superdepartment;
	}

	public String getOrgaddr() {
		return orgaddr;
	}

	public void setOrgaddr(String orgaddr) {
		this.orgaddr = orgaddr;
	}

	public String getDdorgid() {
		return ddorgid;
	}

	public void setDdorgid(String ddorgid) {
		this.ddorgid = ddorgid;
	}
}
