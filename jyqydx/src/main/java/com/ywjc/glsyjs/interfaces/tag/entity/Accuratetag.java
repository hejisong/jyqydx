package com.ywjc.glsyjs.interfaces.tag.entity;



import java.util.Date;

import javax.persistence.*;

import com.ywjc.glsyjs.core.entity.BaseEntity;


@Entity
@Table(name = "accuratetag")
public class Accuratetag extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "tagid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tagid;
	@Column(name = "tagname")	
    private String tagname;
	@Column(name = "createtime")	
    private Date createtime;
	@Column(name = "createuser")	
    private String createuser;
	@Column(name = "state")	
    private String state;

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
