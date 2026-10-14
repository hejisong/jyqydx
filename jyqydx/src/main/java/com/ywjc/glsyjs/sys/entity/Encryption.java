package com.ywjc.glsyjs.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "encryption")//公钥信息表
public class Encryption extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;
	//公钥加密主键
	@Id
	@Column(name = "encryptid")	
    private String encryptid;
	//表名称
	@Column(name = "entablename")
    private String entablename;
    	
	//字段名称
	@Column(name = "colmname")	
    private String colmname;
	//记录id
	@Column(name = "recordid")	
    private String recordid;
	//公钥id
	@Column(name = "encryptcode")	
    private String encryptcode;
	//创建人
	@Column(name = "createpersonid")		
    private String createpersonid;
	//创建人名称
	@Column(name = "createperson")
    private String createperson;
	//创建时间
	@Column(name = "cdate")	
    private Date cdate;
	@Column(name = "updatepersonid")		
    private String updatepersonid;
	@Column(name = "updateperson")
    private String updateperson;
	@Column(name = "udate")	
    private Date udate;
	public String getEncryptid() {
		return encryptid;
	}
	public void setEncryptid(String encryptid) {
		this.encryptid = encryptid;
	}
	public String getEntablename() {
		return entablename;
	}
	public void setEntablename(String entablename) {
		this.entablename = entablename;
	}
	public String getColmname() {
		return colmname;
	}
	public void setColmname(String colmname) {
		this.colmname = colmname;
	}
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getEncryptcode() {
		return encryptcode;
	}
	public void setEncryptcode(String encryptcode) {
		this.encryptcode = encryptcode;
	}
	public String getCreatepersonid() {
		return createpersonid;
	}
	public void setCreatepersonid(String createpersonid) {
		this.createpersonid = createpersonid;
	}
	public String getCreateperson() {
		return createperson;
	}
	public void setCreateperson(String createperson) {
		this.createperson = createperson;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getUpdatepersonid() {
		return updatepersonid;
	}
	public void setUpdatepersonid(String updatepersonid) {
		this.updatepersonid = updatepersonid;
	}
	public String getUpdateperson() {
		return updateperson;
	}
	public void setUpdateperson(String updateperson) {
		this.updateperson = updateperson;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
 
	


}
