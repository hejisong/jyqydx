package com.ywjc.glsyjs.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "menu")
public class Menu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;
	
	/**
	 * 禁用
	 */
	public static final String CONSTENT_ISFORBIDDEN = "1";
	
	/**
	 * 启用
	 */
	public static final String CONSTENT_UNFORBIDDEN = "0";

	@Id
	@Column(name = "id")	
    private String id;
	@Column(name = "menudesc")	
    private String menudesc;
	@Column(name = "menucode")	
    private String menucode;
	@Column(name = "menutype")	
	private String menutype;
	@Column(name = "pid")	
    private String pid;
	@Column(name = "orderno")	
    private Double orderno;
	@Column(name = "url")	
    private String url;
	@Column(name = "shortname")	
    private String shortname;
	@Column(name = "isforbidden")	
    private String isforbidden;
	
	@Transient
	private List<Menu> menus;
	
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

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

	public String getMenudesc() {
		return menudesc;
	}

	public void setMenudesc(String menudesc) {
		this.menudesc = menudesc;
	}

	public String getMenucode() {
		return menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Double getOrderno() {
		return orderno;
	}

	public void setOrderno(Double orderno) {
		this.orderno = orderno;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}
}