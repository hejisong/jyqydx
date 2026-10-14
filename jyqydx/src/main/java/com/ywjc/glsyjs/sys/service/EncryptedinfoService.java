package com.ywjc.glsyjs.sys.service;


import java.util.List;

import com.ywjc.glsyjs.sys.entity.Encryptedinfo;
import com.ywjc.glsyjs.core.service.BaseService;

public interface EncryptedinfoService extends BaseService<Encryptedinfo, String> {
	//查询所有的需要加密的表的名字
	public List<Object []> findTable();

	//查询所有的需要加密的表的名字
	public List<String> findTableName();
		
	//根据表明查询所有表里需要加密的字段名
	public List<String> findbytablename(String tablename);

	public String findbytablename2(String tablename);

	//通过bean查询加密的字段
	public List<String> findbybeanName1(String beanName);
	//通过bean查询加密的表名称
	public String findbybeanName2(String beanName);
	//通过bean查询主键名称
	public String findbybeanName3(String beanName);
	//获取未加密的字段名
	public List<String> findbytablename3(String tablename);
	//获取加密的字段名
	public List<String> findbytablename4(String tablename);
}
