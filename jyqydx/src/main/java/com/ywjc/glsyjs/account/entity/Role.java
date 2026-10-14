package com.ywjc.glsyjs.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4543017171320150602L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 32, nullable = false, unique = true)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	/** Create Date */
	@Column(name = "create_date", nullable = false, updatable = false)
	private Date create_Date;

	/** Modify Date */
	@Column(name = "modify_date", nullable = false)
	private Date modify_Date;

	@NotEmpty(message = "请输入角色名！")
	@Length(max = 200,message = "角色名长度不能超过200字符！")
	@Column(nullable = false)
	private String rolename;

	@NotEmpty(message = "请输入角色编码！")
	@Length(max = 200,message = "角色编码长度不能超过200字符！")
	@Column(nullable = false)
	private String rolecode;
	
	@Column(nullable = false)
	private String roletype;
	
	@Column(name="servicetype")
	private String servicetype;

	@NotEmpty(message = "请输入主页面！")
	@Length(max = 200,message = "主页面长度不能超过200字符！")
	@Column(nullable = false, length = 200)
	private String mainpage;

	@Column(nullable = false)
	private String isforbidden;

	public String getIsforbidden() {
		return isforbidden;
	}

	public void setIsforbidden(String isforbidden) {
		this.isforbidden = isforbidden;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreate_Date() {
		return create_Date;
	}

	public void setCreate_Date(Date create_Date) {
		this.create_Date = create_Date;
	}

	public Date getModify_Date() {
		return modify_Date;
	}

	public void setModify_Date(Date modify_Date) {
		this.modify_Date = modify_Date;
	}

	public String getMainpage() {
		return mainpage;
	}

	public void setMainpage(String mainpage) {
		this.mainpage = mainpage;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Length(max = 200,message = "角色描述长度不能超过200字符！")
	private String description;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users;

	@ManyToMany
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissions;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
}
