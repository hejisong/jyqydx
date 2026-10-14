package com.ywjc.glsyjs.Policyservices.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.Policyservices.entity.Payinfo;
import com.ywjc.glsyjs.Policyservices.repositories.PayinfoRepository;
import com.ywjc.glsyjs.Policyservices.service.PayinfoService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("payinfoServiceImpl")
public class PayinfoServiceImpl extends BaseServiceImpl<Payinfo, String>
		implements PayinfoService {

	@Resource(name = "payinfoRepository")
	private PayinfoRepository repository;


	@Resource(name = "payinfoRepository")
	public void setReposity(
			PagingAndSortingRepository<Payinfo, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Payinfo find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Payinfo> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Payinfo> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Payinfo> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Payinfo> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Payinfo entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Payinfo update(Payinfo entity) {
		Payinfo payinfo = super.find(entity.getInfoid());
		super.update(entity);
		return payinfo;
	}

	@Override
	@Transactional
	public Payinfo update(Payinfo entity, String... ignoreProperties) {
		Payinfo payinfo = super.update(entity, ignoreProperties);
		return payinfo;
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
	public void delete(Payinfo entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Payinfo findbyResultid(String plyresultid){
         return repository.findbyResultid(plyresultid);
	}


}
