package com.ywjc.glsyjs.businesshare.entity;


import com.ywjc.glsyjs.core.entity.BaseEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "suggestreply")
public class Suggestreply extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

    @Id
	@Column(name = "replyid")	
    private String replyid;
	@Column(name = "suggestid")	
    private String suggestid;
	@Column(name = "replymanid")	
    private String replymanid;
	@Column(name = "replyman")	
    private String replyman;
	@Column(name = "content")	
    private String content;
	@Column(name = "replytime")	
    private Date replytime;
	//1回复 2追问
	@Column(name = "infotype")
	private String infotype;

	public String getReplyid() {
		return replyid;
	}

	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}

	public String getSuggestid() {
		return suggestid;
	}

	public void setSuggestid(String suggestid) {
		this.suggestid = suggestid;
	}

	public String getReplymanid() {
		return replymanid;
	}

	public void setReplymanid(String replymanid) {
		this.replymanid = replymanid;
	}

	public String getReplyman() {
		return replyman;
	}

	public void setReplyman(String replyman) {
		this.replyman = replyman;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReplytime() {
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}
}
