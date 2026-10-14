package com.ywjc.glsyjs.core.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface QueryService {
	
	public List<Object> queryForObject(String sql,List<Object> values);
	
	public List<Object[]> query(String sql,List<Object> values);
	
	public Object queryObject(String sql);
	
	public List<Object> queryObjectBySql(String sql);
	
	public List<Object[]> queryObjectsBySql(String sql);
	
	public Integer executeSql(String sql);
	
	public void batchInsert(List<Object> objs);
	
	public Page<Object> queryForPage(String sql,String order,List<Object> values,Class<?> cla,Pageable pageable);
	
	public Page<Object> queryObjectForPage(String columnsql,String sql,String order,List<Object> values,Pageable pageable);
	
	public Object queryObject(String columnsql,String sql,List<Object> values);
	
	public List<Object> queryObjectBySql(String columnsql,String sql,List<Object> values);

	public Page<Object> queryForPage3(String string, String order, List<Object> values, Class<?> cla,
			Pageable pageable); 
	
	public Integer executeBatchSql(List<String> params);
}
