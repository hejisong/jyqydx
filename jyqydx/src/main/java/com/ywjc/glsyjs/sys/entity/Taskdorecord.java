package com.ywjc.glsyjs.sys.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ywjc.glsyjs.core.entity.BaseEntity;


 



@Entity
@Table(name = "taskdorecord")
public class Taskdorecord  extends BaseEntity {


	private static final long serialVersionUID =  1L;


	@Id
	@Column(name = "id")
	private String id;//ID
	@Column(name = "tasktype")
	private String tasktype;//任务类型
	@Column(name = "taskclass")
	private String taskclass;//执行类名称
	@Column(name = "tasmethod")
	private String tasmethod;//执行方法名称
	@Column(name = "datacount")
	private String datacount;//处理数据条数
	@Column(name = "updatedatacount")
	private String updatedatacount;//更新数据条数
	@Column(name = "begintime")
	private Date begintime;//执行开始时间
	@Column(name = "endtime")
	private Date endtime;//执行结束时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getTaskclass() {
		return taskclass;
	}

	public void setTaskclass(String taskclass) {
		this.taskclass = taskclass;
	}

	public String getTasmethod() {
		return tasmethod;
	}

	public void setTasmethod(String tasmethod) {
		this.tasmethod = tasmethod;
	}

	public String getDatacount() {
		return datacount;
	}

	public void setDatacount(String datacount) {
		this.datacount = datacount;
	}

	public String getUpdatedatacount() {
		return updatedatacount;
	}

	public void setUpdatedatacount(String updatedatacount) {
		this.updatedatacount = updatedatacount;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
