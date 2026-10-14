package com.ywjc.glsyjs.core.entity;

import java.util.List;

public class QueryPo {
	private String sql;
	private List<Object> values;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public QueryPo(String sql, List<Object> values) {
		super();
		this.sql = sql;
		this.values = values;
	}
	
	
}
