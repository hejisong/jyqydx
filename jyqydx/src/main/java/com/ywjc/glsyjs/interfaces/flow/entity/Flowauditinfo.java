package com.ywjc.glsyjs.interfaces.flow.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "flowauditinfo")
public class Flowauditinfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "infoid")
    private String infoid;

	//流程节点id
	@Column(name = "auditid")	
    private String auditid;
	//审核部门id
	@Column(name = "deptid")	
    private String deptid;
	//审核部门
	@Column(name = "deptname")	
    private String deptname;
	//审核人id
	@Column(name = "auditperid")	
    private String auditperid;
	//审核人
	@Column(name = "auditpername")	
    private String auditpername;
	//审核结果 1同意 2驳回 3退回
	@Column(name = "auditstate")	
    private String auditstate;

	@Column(name = "auditamount")
	private String auditamount;
	//审核意见
	@Column(name = "auditidea")
    private String auditidea;
	//审核时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@Column(name = "audittime")	
    private Date audittime;


	//流程状态  1 进行中 2 结束
	@Column(name = "flowstate")
	private String flowstate;
	@Column(name = "nextnode")
	private String nextnode;

	public String getFlowstate() {
		return flowstate;
	}

	public void setFlowstate(String flowstate) {
		this.flowstate = flowstate;
	}

	public String getNextnode() {
		return nextnode;
	}

	public void setNextnode(String nextnode) {
		this.nextnode = nextnode;
	}

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getAuditperid() {
		return auditperid;
	}

	public void setAuditperid(String auditperid) {
		this.auditperid = auditperid;
	}

	public String getAuditpername() {
		return auditpername;
	}

	public void setAuditpername(String auditpername) {
		this.auditpername = auditpername;
	}

	public String getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}

	public String getAuditamount() {
		return auditamount;
	}

	public void setAuditamount(String auditamount) {
		this.auditamount = auditamount;
	}

	public String getAuditidea() {
		return auditidea;
	}

	public void setAuditidea(String auditidea) {
		this.auditidea = auditidea;
	}

	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}

	@Override
	public String toString() {
		return "Flowauditinfo{" +
				"infoid='" + infoid + '\'' +
				", auditid='" + auditid + '\'' +
				", deptid='" + deptid + '\'' +
				", deptname='" + deptname + '\'' +
				", auditperid='" + auditperid + '\'' +
				", auditpername='" + auditpername + '\'' +
				", auditstate='" + auditstate + '\'' +
				", auditamount='" + auditamount + '\'' +
				", auditidea='" + auditidea + '\'' +
				", audittime=" + audittime +
				", flowstate='" + flowstate + '\'' +
				", nextnode='" + nextnode + '\'' +
				'}';
	}
}
