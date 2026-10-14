package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.bigdata.repositories.GxcwbqytjjRepository;
import com.ywjc.glsyjs.bigdata.service.GxcwbqytjjService;
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


@Service("gxcwbqytjjServiceImpl")
public class GxcwbqytjjServiceImpl extends BaseServiceImpl<Gxcwbqytjj, String>
		implements GxcwbqytjjService {

	@Resource(name = "gxcwbqytjjRepository")
	private GxcwbqytjjRepository repository;


	@Resource(name = "gxcwbqytjjRepository")
	public void setReposity(
			PagingAndSortingRepository<Gxcwbqytjj, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Gxcwbqytjj find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Gxcwbqytjj> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Gxcwbqytjj> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Gxcwbqytjj> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Gxcwbqytjj> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Gxcwbqytjj entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Gxcwbqytjj update(Gxcwbqytjj entity) {
		Gxcwbqytjj gxcwbqytjj = super.find(entity.getId());
		super.update(entity);
		return gxcwbqytjj;
	}

	@Override
	@Transactional
	public Gxcwbqytjj update(Gxcwbqytjj entity, String... ignoreProperties) {
		Gxcwbqytjj gxcwbqytjj = super.update(entity, ignoreProperties);
		return gxcwbqytjj;
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
	public void delete(Gxcwbqytjj entity) {
		super.delete(entity);
	}


	@Override
	@Transactional
	public Page<Gxcwbqytjj> queryPageinfo( String companyname,String year, Double minsale_income, Double maxsale_income, Pageable pageable){

		if(minsale_income!=null&&maxsale_income!=null){
			return repository.queryPageinfo(companyname,year,minsale_income,maxsale_income,pageable);
		}else if(minsale_income!=null){
			return repository.queryPageinfoMin(companyname,year,minsale_income,pageable);
		}else if(maxsale_income!=null){
			return repository.queryPageinfoMax(companyname,year,maxsale_income,pageable);
		}
		return repository.queryPageinfoAll(companyname,year,pageable);
	}

	@Override
	@Transactional
	public Gxcwbqytjj findByYear(String  companyid, String year){
		return repository.findByYear(companyid,year);
	}

	@Override
	@Transactional
	public Gxcwbqytjj findBycompanyid(String  companyid){
		return repository.findBycompanyid(companyid);
	}





}
