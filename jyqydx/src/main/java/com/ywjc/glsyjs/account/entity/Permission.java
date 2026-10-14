package com.ywjc.glsyjs.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "permission")
public class Permission extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775060592187190940L;

	/**
	 * 
	 */
	
	@Id
	@Column(name="id")
 	private String id;
	
	@Column(name="create_date")
	private Date createdate;
	
	@Column(name="modify_date")
	private Date modifydate;

	@NotEmpty
	@Length(max = 32)
	@Column(length = 32, nullable = false, unique = true)
	private String name;

	@Length(max = 256)
	@Column(length = 256)
	private String description;
 
	@Length(max = 256)
	@Column(length = 256)
	private String value;

	@Length(max = 50)
	@Column(length = 50)
	private String permission;

	@ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
	private Set<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
