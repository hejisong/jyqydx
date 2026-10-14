package com.ywjc.glsyjs.sys.service;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Generalcode;

public interface GeneralcodeService extends BaseService<Generalcode, String> {
	public Generalcode findByCodetableidAndCodeid(String codetableid,
			String codeid);

	public Generalcode findByCodeid(String codeid);
		
	public Page<Generalcode> findByCodetableid(String codetableid,
			Pageable pageable);
	
	public List<Generalcode> findByCodetableid(String codetableid);

	public Page<Generalcode> findByAll(String codetablename, String codedesc,
			String ucode, String isdisabled, Pageable pageable);
	
	public void deletecode(String codetableid,String codeid);
	
	public List<Generalcode> findByCodetablename(String codetablename);
	
	public Generalcode findByCodedesc(String codedesc);
	
	public Generalcode findGeneralcode(String codetableid,String ucode,String codetablename,String codeid);
	
	public Generalcode findByUcode(String ucode);
	
	/*public Generalcode findByCodetableidAndCodetablename(String codetableid,String codetablename);*/
	
	public List<Generalcode> findGeneralcodes(String codetableid);
	
	public Long findMaxNo(String codetableid);

	public Object[] getGeneralcodeByCodetablenames(String[] Codetablenames);

	public String[] getCodenamesByUcodesCodetablenames(String[] ucodes,
			String[] codetablenames);

	public String getCodenameByUcodesCodetablenames(String ucode, String codetablename);
	//查询资质证书名称
	public List<Generalcode> findByCodetablenames(String codetablename);
	
	public Generalcode findProvince(String codedesc);
	
	public List<Generalcode> findAllinfo();
	
	public List<Object> findObjectByCodetablename(String codetablename);
}
