package com.ywjc.glsyjs.interfaces.company.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanypatentRepository;
import com.ywjc.glsyjs.interfaces.company.service.CompanypatentService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("companypatentServiceImpl")
public class CompanypatentServiceImpl extends BaseServiceImpl<Companypatent, String>
		implements CompanypatentService {

	@Resource(name = "companypatentRepository")
	private CompanypatentRepository repository;


	@Resource(name = "companypatentRepository")
	public void setReposity(
			PagingAndSortingRepository<Companypatent, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Companypatent find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Companypatent> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Companypatent> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Companypatent> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Companypatent> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Companypatent entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Companypatent update(Companypatent entity) {
		Companypatent companypatent = super.find(entity.getCompanypatentid());
		super.update(entity);
		return companypatent;
	}

	@Override
	@Transactional
	public Companypatent update(Companypatent entity, String... ignoreProperties) {
		Companypatent companypatent = super.update(entity, ignoreProperties);
		return companypatent;
	}

	@Override
	@Transactional
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	public void delete(Companypatent entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public List<Companypatent> findByYear(String patentyear){
        return repository.findByYear(patentyear);
	}
	@Override
	public List<Companypatent> findNoYear(){
		return repository.findNoYear();
	}


	public List<Companypatent> findNoCompanyid(){
		return repository.findNoCompanyid();
	}

	@Override
	@Transactional
	public Page<Companypatent> queryPageinfo(String patentname, String enterprisename, String applicationnum, Date applystartdate, Date applyenddate , Pageable pageable){
		if(applystartdate!=null&&applyenddate!=null){
			return repository.queryPageinfo(patentname,enterprisename,applicationnum,applystartdate,applyenddate,pageable);
		}else if(applystartdate!=null){
			return repository.queryPageinfoStartdate(patentname,enterprisename,applicationnum,applystartdate,pageable);
		}else if(applyenddate!=null){
			return repository.queryPageinfoEnddate(patentname,enterprisename,applicationnum,applyenddate,pageable);
		}
		return repository.queryPageinfoAll(patentname,enterprisename,applicationnum,pageable);
	}

	@Override
	@Transactional
	public Companypatent findBypatentid(String patentid){
		return repository.findBypatentid(patentid);
	}


	public Companypatent findBypubannomentno(String pubannomentno){
		return repository.findBypubannomentno(pubannomentno);
	}




}
