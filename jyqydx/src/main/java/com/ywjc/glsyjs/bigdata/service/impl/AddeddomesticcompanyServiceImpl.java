package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Addeddomesticcompany;
import com.ywjc.glsyjs.bigdata.repositories.AddeddomesticcompanyRepository;
import com.ywjc.glsyjs.bigdata.service.AddeddomesticcompanyService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("addeddomesticcompanyServiceImpl")
public class AddeddomesticcompanyServiceImpl extends BaseServiceImpl<Addeddomesticcompany, String>
		implements AddeddomesticcompanyService {

	@Resource(name = "addeddomesticcompanyRepository")
	private AddeddomesticcompanyRepository repository;


	@Resource(name = "addeddomesticcompanyRepository")
	public void setReposity(
			PagingAndSortingRepository<Addeddomesticcompany, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Addeddomesticcompany find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Addeddomesticcompany> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Addeddomesticcompany> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Addeddomesticcompany> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Addeddomesticcompany> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Addeddomesticcompany entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Addeddomesticcompany update(Addeddomesticcompany entity) {
		Addeddomesticcompany addeddomesticcompany = super.find(entity.getId());
		super.update(entity);
		return addeddomesticcompany;
	}

	@Override
	@Transactional
	public Addeddomesticcompany update(Addeddomesticcompany entity, String... ignoreProperties) {
		Addeddomesticcompany addeddomesticcompany = super.update(entity, ignoreProperties);
		return addeddomesticcompany;
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
	public void delete(Addeddomesticcompany entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Addeddomesticcompany> queryPageinfo(String company_name, String stock_code, String region, Date startdate, Date enddate , Pageable pageable){
		if(startdate!=null&&enddate!=null){
			return repository.queryPageinfo(company_name,stock_code,region,startdate,enddate,pageable);
		}else if(startdate!=null){
			return repository.queryPageinfoStartdate(company_name,stock_code,region,startdate,pageable);
		}else if(enddate!=null){
			return repository.queryPageinfoEnddate(company_name,stock_code,region,enddate,pageable);
		}
		return repository.queryPageinfoAll(company_name,stock_code,region,pageable);
	}


}
