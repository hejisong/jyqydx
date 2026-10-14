package com.ywjc.glsyjs.bigdata.entity;




import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "socialconsumergoodsales")
public class Socialconsumergoodsales extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "id")
	private String id;//社会消费品零售主键
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "companyname")
	private String companyname;//企业名称
	@Column(name = "year")
	private String year;//年份
	@Column(name = "totall_sales")
	private Double totall_sales;//社会消费品零售总额
	@Column(name = "total_sales_increase")
	private String total_sales_increase;//社会消费品零售总额增幅
	@Column(name = "grouping")
	private String grouping;//分组
	@Column(name = "measureunit")
	private String measureunit;//计量单位
	@Column(name = "reporting_period")
	private String reporting_period;//报告期
	@Column(name = "state")
	private String state;//状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Double getTotall_sales() {
		return totall_sales;
	}

	public void setTotall_sales(Double totall_sales) {
		this.totall_sales = totall_sales;
	}

	public String getTotal_sales_increase() {
		return total_sales_increase;
	}

	public void setTotal_sales_increase(String total_sales_increase) {
		this.total_sales_increase = total_sales_increase;
	}

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}

	public String getMeasureunit() {
		return measureunit;
	}

	public void setMeasureunit(String measureunit) {
		this.measureunit = measureunit;
	}

	public String getReporting_period() {
		return reporting_period;
	}

	public void setReporting_period(String reporting_period) {
		this.reporting_period = reporting_period;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Socialconsumergoodsales setValue(Socialconsumergoodsales other){
		this.companyid=other.getCompanyid();
		this.companyname=other.getCompanyname();
		this.year=other.getYear();
		this.totall_sales=other.getTotall_sales();
		this.total_sales_increase=other.getTotal_sales_increase();
		this.grouping=other.getGrouping();
		this.measureunit=other.getMeasureunit();
		this.reporting_period=other.getReporting_period();
		return this;
	}
}
