package com.ywjc.glsyjs.sys.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
 
 
public class Role_permissionPK implements Serializable {
	
	private static final long serialVersionUID =  1L;
 
	@Id
	@Column(name = "role_id")	
    private String role_id;
	@Id
	@Column(name = "permission_id")	
    private String permission_id;

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(String permission_id) {
		this.permission_id = permission_id;
	}


}
