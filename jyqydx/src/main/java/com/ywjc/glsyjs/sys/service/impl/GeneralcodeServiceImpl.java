package com.ywjc.glsyjs.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Generalcode;
import com.ywjc.glsyjs.sys.repositories.GeneralcodeRepository;
import com.ywjc.glsyjs.sys.repositories.GeneralcodenameRepository;
import com.ywjc.glsyjs.sys.service.GeneralcodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("generalcodeServiceImpl")
public class GeneralcodeServiceImpl extends
        BaseServiceImpl<Generalcode, String> implements GeneralcodeService {

	@Resource(name = "generalcodeRepository")
	private GeneralcodeRepository repository;
	
	@Resource(name = "generalcodenameRepository")
	private GeneralcodenameRepository generalcodenameRepository;

	@Resource(name = "generalcodeRepository")
	public void setReposity(
			PagingAndSortingRepository<Generalcode, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Generalcode> findAll(){
		return super.findAll();
	}

	@Override
	@Transactional
	public void save(Generalcode entity) {
		super.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Generalcode findByCodetableidAndCodeid(String codetableid,
			String codeid) {
		return repository.findByCodetableidAndCodeid(codetableid, codeid);
	}

	@Override
	@Transactional
	public Page<Generalcode> findByAll(String codetablename, String codedesc,
			String ucode, String isdisabled, Pageable pageable) {
		return repository.findByAll(codetablename, codedesc, ucode, isdisabled,
				pageable);
	}

	@Override
	@Transactional
	public Page<Generalcode> findByCodetableid(String codetableid,
			Pageable pageable) {
		return repository.findByCodetableid(codetableid, pageable);
	}
	
	@Override
	@Transactional
	public List<Generalcode> findByCodetableid(String codetableid) {
		return repository.findByCodetableid(codetableid);
	}

	@Override
	@Transactional
	public void deletecode(String codetableid, String codeid) {
		repository.deletecode(codetableid, codeid);
	}
	
	@Override
	@Transactional
	public List<Generalcode> findByCodetablename(String codetablename){
	    return repository.findByCodetablename(codetablename);
	}
	
	@Override
	@Transactional
	public List<Generalcode> findGeneralcodes(String codetableid){
		return repository.findGeneralcodes(codetableid);
	}

	@Override
	public Long findMaxNo(String codetableid) {	
		return repository.findMaxNo(codetableid);
	}

	@Override
	@Transactional
	public Generalcode findGeneralcode(String codetableid, String ucode,
			String codetablename,String codeid) {
		
		return repository.findGeneralcode(codetableid, ucode, codetablename,codeid);
	}

	@Override
	@Transactional
	public Generalcode findByCodeid(String codeid) {
		return repository.findByCodeid(codeid);
	}

	@Override
	@Transactional
	public Object[] getGeneralcodeByCodetablenames(String[] Codetablenames) {
		Object[] generalcodes=new Object[Codetablenames.length];
		for(int i=0;i<Codetablenames.length;i++){
			generalcodes[i]=findByCodetablename(Codetablenames[i]);
		}
		return generalcodes;
	}

	@Override
	@Transactional
	public String[] getCodenamesByUcodesCodetablenames(String[] ucodes,
			String[] codetablenames) {
		String [] generalcodenames=new String[ucodes.length];
		for(int i=0;i<ucodes.length;i++){
			List<Generalcode> listGeneralcode= findByCodetablename(codetablenames[i]);
			Map<String,Generalcode> mapGeneralcode=new HashMap<String,Generalcode>();
			for(Generalcode generalcode:listGeneralcode){
				mapGeneralcode.put(generalcode.getUcode(), generalcode);
			}	
			generalcodenames[i]=mapGeneralcode.get(ucodes[i]).getCodedesc();
		}
		return generalcodenames;
		
	}
	
	@Override
	@Transactional
	public String getCodenameByUcodesCodetablenames(String ucode,String codetablename) {
		
			List<Generalcode> listGeneralcode= findByCodetablename(codetablename);
			Map<String,Generalcode> mapGeneralcode=new HashMap<String,Generalcode>();
			for(Generalcode generalcode:listGeneralcode){
				mapGeneralcode.put(generalcode.getUcode(), generalcode);
			}	
			String generalcodename=mapGeneralcode.get(ucode).getCodedesc();
		 
		return generalcodename;
		
	}

	@Override
	@Transactional
	public Generalcode findByUcode(String ucode) {
		Generalcode generalcode=repository.findByUcode(ucode);
		return generalcode;
	}

	@Override
	@Transactional
	public List<Generalcode> findByCodetablenames(String codetablename) {
		return repository.findByCodetablename(codetablename);
	}

	@Override
	@Transactional
	public Generalcode findByCodedesc(String codedesc) {
		return repository.findByCodedesc(codedesc);
	}
	

	@Override
	public Generalcode findProvince(String codedesc) {
		return repository.findProvince(codedesc);
	}

	@Override
	public List<Generalcode> findAllinfo() {
		return repository.findAllinfo();
	}

	@Override
	public List<Object> findObjectByCodetablename(String codetablename) {
		return repository.findObjectByCodetablename(codetablename);
	}
}
