package com.ywjc.datashare.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;



import javax.persistence.*;


@Entity
@Table(name = "t_department")
public class Departmentinfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "deptid")	
    private String deptid;
	@Column(name = "deptname")	
    private String deptname;
	@Column(name = "displayname")	
    private String displayname;
	@Column(name = "unitcode")	
    private String unitcode;
	@Column(name = "superdepartment")	
    private String superdepartment;
	@Column(name = "mail")	
    private String mail;
	@Column(name = "postaladdress")	
    private String postaladdress;
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

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}

	public String getSuperdepartment() {
		return superdepartment;
	}

	public void setSuperdepartment(String superdepartment) {
		this.superdepartment = superdepartment;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPostaladdress() {
		return postaladdress;
	}

	public void setPostaladdress(String postaladdress) {
		this.postaladdress = postaladdress;
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
}
