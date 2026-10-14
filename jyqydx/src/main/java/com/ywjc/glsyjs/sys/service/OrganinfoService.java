package com.ywjc.glsyjs.sys.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Organinfo;

public interface OrganinfoService extends BaseService<Organinfo, String> {
	/*
	 * 建管处等进来查询单位列表
	 */

	public Organinfo findByOrgcode(String orgcode);

	public List<Organinfo>  findOrgans(String orgname);

	public List<Organinfo> findOrgnames();

	public List<Organinfo> findOrgList(String orgname,String orgcode );

	public Page<Object> findPage2(String orgname, String orgcode ,Pageable pageable);

	public Page<Organinfo> findOrgList(String organname,String orgcode,Pageable pageable);


	public List<Organinfo> findChildOrgs(String orgid );

	public List<Organinfo> findOrgs();

	public List<Organinfo> findOpOrgs();
}
