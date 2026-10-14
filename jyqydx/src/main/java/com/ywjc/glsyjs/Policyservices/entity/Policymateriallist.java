package com.ywjc.glsyjs.Policyservices.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "policymateriallist")
public class Policymateriallist extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "listid")	
    private String listid;
	@Column(name = "materialid")	
    private String materialid;
	@Column(name = "materialname")	
    private String materialname;
	@Column(name = "sn")	
    private Long sn;
	@Column(name = "modelfileid")	
    private String modelfileid;
	@Column(name = "modelfilename")	
    private String modelfilename;
	@Column(name = "inputperid")	
    private String inputperid;
	@Column(name = "inputper")	
    private String inputper;
	@Column(name = "creattime")	
    private Date creattime;
	@Column(name = "updatetime")	
    private Date updatetime;

	public String getListid() {
		return listid;
	}

	public void setListid(String listid) {
		this.listid = listid;
	}

	public String getMaterialid() {
		return materialid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

	public String getMaterialname() {
		return materialname;
	}

	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getModelfileid() {
		return modelfileid;
	}

	public void setModelfileid(String modelfileid) {
		this.modelfileid = modelfileid;
	}

	public String getModelfilename() {
		return modelfilename;
	}

	public void setModelfilename(String modelfilename) {
		this.modelfilename = modelfilename;
	}

	public String getInputperid() {
		return inputperid;
	}

	public void setInputperid(String inputperid) {
		this.inputperid = inputperid;
	}

	public String getInputper() {
		return inputper;
	}

	public void setInputper(String inputper) {
		this.inputper = inputper;
	}

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
