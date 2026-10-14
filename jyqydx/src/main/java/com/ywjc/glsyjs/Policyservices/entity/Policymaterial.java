package com.ywjc.glsyjs.Policyservices.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "policymaterial")
public class Policymaterial extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "materialid")	
    private String materialid;
	@Column(name = "policyfileid")	
    private String policyfileid;
	@Column(name = "nplcategory")	
    private String nplcategory;
	@Column(name = "policyyear")	
    private String policyyear;
	@Column(name = "inputperid")	
    private String inputperid;
	@Column(name = "inputper")	
    private String inputper;
	@Column(name = "creattime")	
    private Date creattime;
	@Column(name = "updatetime")	
    private Date updatetime;

	@Transient
	private List<Policymateriallist> materiallists;

	public List<Policymateriallist> getMateriallists() {
		return materiallists;
	}

	public void setMateriallists(List<Policymateriallist> materiallists) {
		this.materiallists = materiallists;
	}

	public String getMaterialid() {
		return materialid;
	}

	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}

	public String getPolicyfileid() {
		return policyfileid;
	}

	public void setPolicyfileid(String policyfileid) {
		this.policyfileid = policyfileid;
	}

	public String getNplcategory() {
		return nplcategory;
	}

	public void setNplcategory(String nplcategory) {
		this.nplcategory = nplcategory;
	}

	public String getPolicyyear() {
		return policyyear;
	}

	public void setPolicyyear(String policyyear) {
		this.policyyear = policyyear;
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
