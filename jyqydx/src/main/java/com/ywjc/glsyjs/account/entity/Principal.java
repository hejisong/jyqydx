/*
 */
package com.ywjc.glsyjs.account.entity;

import java.io.Serializable;
import java.util.List;

import com.ywjc.glsyjs.sys.entity.Menu;

/**
 * Principal
 * 
 */
public class Principal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7386698598562543697L;

	/** ID */
	private String id;

	private String username;
	
	private String loginname;
	
	private String loginstate;
	
	private User user;
	
	private String current_industry;
	
	private List<String> current_rolecodes;
	
	private List<Menu> menus;
	
	public Principal(){}

	/**
	 * 
	 * @param id
	 * @param loginname
	 * @param username
	 * @param loginstate
	 */
	public Principal(String id, String loginname, String username,String loginstate,String current_industry,List<String> current_rolecodes) {
		this.id = id;
		this.loginname = loginname;
		this.username = username;
		this.loginstate = loginstate;
		this.current_industry = current_industry;
		this.current_rolecodes=current_rolecodes;
	}

	public List<String> getCurrent_rolecodes() {
		return current_rolecodes;
	}

	public void setCurrent_rolecodes(List<String> current_rolecodes) {
		this.current_rolecodes = current_rolecodes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return username;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginstate() {
		return loginstate;
	}

	public void setLoginstate(String loginstate) {
		this.loginstate = loginstate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getCurrent_industry() {
		return current_industry;
	}

	public void setCurrent_industry(String current_industry) {
		this.current_industry = current_industry;
	}
}