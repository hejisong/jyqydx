package com.ywjc.datadd.entity;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;


import freemarker.template.TemplateException;

@Entity
@Table(name = "policy_user")
public class Policyuser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "id")	
    private String id;
	@Column(name = "user_id")
	private String user_id;
	@Column(name = "type")
    private String type;
	@Column(name = "user_name")
    private String user_name;
	@Column(name = "mobile")	
    private String mobile;
	@Column(name = "department_id")
	private String department_id;
	@Column(name = "department_name")
	private String department_name;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
}
