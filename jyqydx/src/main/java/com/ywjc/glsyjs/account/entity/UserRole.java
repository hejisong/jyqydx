package com.ywjc.glsyjs.account.entity;

import com.ywjc.glsyjs.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntity implements Serializable {

	/**
	 * 
	 */

	
	@Id
	@Column(name = "user_id")
	private String user_id;

	@Column(name = "role_id")
	private String role_id;

	@Column(name = "data_source")
	private String data_source;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getData_source() {
		return data_source;
	}

	public void setData_source(String data_source) {
		this.data_source = data_source;
	}
}
