package com.ywjc.glsyjs.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import com.ywjc.glsyjs.core.entity.BaseEntity;

@SolrDocument(solrCoreName="generalcode")
@Entity
@IdClass(GeneralcodePK.class)
@Table(name = "generalcode")
public class Generalcode extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field(value="id")
	@Transient
	private String generalcodeid;

	@Field
	@Id
	@Column(name = "codetableid")
	private String codetableid;

	@Field
	@Id
	@Column(name = "codeid")
	private String codeid;
	
	@Field
	@Column(name = "codedesc")
	private String codedesc;
	
	@Field
	@Column(name = "ucode")
	private String ucode;
	
	@Field
	@Column(name = "usedfrequency")
	private Long usedfrequency;
	
	@Field
	@Column(name = "isdisabled")
	private String isdisabled;
	
	@Field
	@Column(name = "updatedate")
	private Date updatedate;
	
	@Field
	@Column(name = "fatherid")
	private String fatherid;
	
	
	@ManyToOne
	@JoinColumn(name = "codetableid", insertable = false, updatable = false)
	private Generalcodename generalcodename;

	public Generalcodename getGeneralcodename() {
		return generalcodename;
	}

	public void setGeneralcodename(Generalcodename generalcodename) {
		this.generalcodename = generalcodename;
	}

	public String getCodetableid() {
		return codetableid;
	}

	public void setCodetableid(String codetableid) {
		this.codetableid = codetableid;
	}

	public String getCodeid() {
		return codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}

	public String getCodedesc() {
		return codedesc;
	}

	public void setCodedesc(String codedesc) {
		this.codedesc = codedesc;
	}

	public String getUcode() {
		return ucode;
	}

	public void setUcode(String ucode) {
		this.ucode = ucode;
	}

	public Long getUsedfrequency() {
		return usedfrequency;
	}

	public void setUsedfrequency(Long usedfrequency) {
		this.usedfrequency = usedfrequency;
	}

	public String getIsdisabled() {
		return isdisabled;
	}

	public void setIsdisabled(String isdisabled) {
		this.isdisabled = isdisabled;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getFatherid() {
		return fatherid;
	}

	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}


	public String getGeneralcodeid() {
		return generalcodeid;
	}

	public void setGeneralcodeid(String generalcodeid) {
		this.generalcodeid = generalcodeid;
	}
	
	public void setDefaultGeneralcodeid(){
		this.generalcodeid = this.codeid + this.codetableid;
	}
}
