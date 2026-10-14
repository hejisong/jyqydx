package com.ywjc.glsyjs.interfaces.tag.entity;



import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "accuratetagrelation")
public class Accuratetagrelation extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	@Column(name = "tagid")	
    private String tagid;
	@Column(name = "tagname")
	private String tagname;
	@Column(name = "pid")	
    private String pid;
	@Column(name = "cid")	
    private String cid;
	@Column(name = "tagyear")	
    private String tagyear;
	@Column(name = "uploadfileid")
	private String uploadfileid;
	@Column(name = "uploadfilename")
	private String uploadfilename;

	@Column(name = "createtime")	
    private Date createtime;
	@Column(name = "createuser")	
    private String createuser;
	@Column(name = "updatetime")	
    private Date updatetime;
	@Column(name = "updateuser")	
    private String updateuser;

	@Transient
	private String companyname;
	@Transient
	private String uscreditcode;

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getUscreditcode() {
		return uscreditcode;
	}

	public void setUscreditcode(String uscreditcode) {
		this.uscreditcode = uscreditcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getTagyear() {
		return tagyear;
	}

	public void setTagyear(String tagyear) {
		this.tagyear = tagyear;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getUploadfileid() {
		return uploadfileid;
	}

	public void setUploadfileid(String uploadfileid) {
		this.uploadfileid = uploadfileid;
	}

	public String getUploadfilename() {
		return uploadfilename;
	}

	public void setUploadfilename(String uploadfilename) {
		this.uploadfilename = uploadfilename;
	}
}
