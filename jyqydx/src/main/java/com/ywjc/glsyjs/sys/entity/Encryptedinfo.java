package com.ywjc.glsyjs.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "encryptedinfo")
public class Encryptedinfo extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;
	@Id
	@Column(name = "encryptid")	
    private String encryptid;
	//bean名称
	@Column(name = "beanname")	
	private String beanname;
	//表名称
	@Column(name = "entablename")	
    private String entablename;
	//字段名称
	@Column(name = "colmname")	
    private String colmname;
	
	//主键id的名称
	@Column(name = "p_key")	
	private String p_key;
	
	//加密情况，1为加密，其他未加密
	@Column(name = "isencrypted")	
	private String isencrypted;
}
