package com.ywjc.glsyjs.bigdata.entity;




import com.ywjc.glsyjs.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "gxcwbqytjj")
public class Gxcwbqytjj extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID =  1L;

	@Id
	@Column(name = "id")
	private String id;//ID
	@Column(name = "companyid")
	private String companyid;//企业id
	@Column(name = "companyname")
	private String companyname;//企业名称
	@Column(name = "financeid")
	private String financeid;//财务信息ID
	@Column(name = "year")
	private String year;//年份
	@Column(name = "sale_income")
	private Double sale_income;//营业总收入
	@Column(name = "debit_amount")
	private Double debit_amount;//负债总额
	@Column(name = "net_amount")
	private Double net_amount;//净利润
	@Column(name = "profit_reta")
	private String profit_reta;//所有者权益合计
	@Column(name = "profit_total")
	private Double profit_total;//利润总额
	@Column(name = "tax_total")
	private Double tax_total;//纳税总额
	@Column(name = "total_equity")
	private Double total_equity;//资产总额
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

	public String getFinanceid() {
		return financeid;
	}

	public void setFinanceid(String financeid) {
		this.financeid = financeid;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getSale_income() {
		return sale_income;
	}

	public void setSale_income(Double sale_income) {
		this.sale_income = sale_income;
	}

	public Double getDebit_amount() {
		return debit_amount;
	}

	public void setDebit_amount(Double debit_amount) {
		this.debit_amount = debit_amount;
	}

	public Double getNet_amount() {
		return net_amount;
	}

	public void setNet_amount(Double net_amount) {
		this.net_amount = net_amount;
	}

	public String getProfit_reta() {
		return profit_reta;
	}

	public void setProfit_reta(String profit_reta) {
		this.profit_reta = profit_reta;
	}

	public Double getProfit_total() {
		return profit_total;
	}

	public void setProfit_total(Double profit_total) {
		this.profit_total = profit_total;
	}

	public Double getTax_total() {
		return tax_total;
	}

	public void setTax_total(Double tax_total) {
		this.tax_total = tax_total;
	}

	public Double getTotal_equity() {
		return total_equity;
	}

	public void setTotal_equity(Double total_equity) {
		this.total_equity = total_equity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Gxcwbqytjj setValue(Gxcwbqytjj other){
		this.financeid=other.getFinanceid();
		this.year=other.getYear();
		this.sale_income=other.getSale_income();
		this.debit_amount=other.getDebit_amount();
		this.net_amount=other.getNet_amount();
		this.profit_reta=other.getProfit_reta();
		this.profit_total=other.getProfit_total();
		this.tax_total=other.getTax_total();
		this.total_equity=other.getTotal_equity();
		return this;
	}
}
