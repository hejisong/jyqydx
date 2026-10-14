package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Legalpersonsblacklist;
import com.ywjc.glsyjs.bigdata.repositories.LegalpersonsblacklistRepository;
import com.ywjc.glsyjs.bigdata.service.LegalpersonsblacklistService;
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


@Service("legalpersonsblacklistServiceImpl")
public class LegalpersonsblacklistServiceImpl extends BaseServiceImpl<Legalpersonsblacklist, String>
		implements LegalpersonsblacklistService {

	@Resource(name = "legalpersonsblacklistRepository")
	private LegalpersonsblacklistRepository repository;


	@Resource(name = "legalpersonsblacklistRepository")
	public void setReposity(
			PagingAndSortingRepository<Legalpersonsblacklist, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Legalpersonsblacklist find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Legalpersonsblacklist> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Legalpersonsblacklist> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Legalpersonsblacklist> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Legalpersonsblacklist> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Legalpersonsblacklist entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Legalpersonsblacklist update(Legalpersonsblacklist entity) {
		Legalpersonsblacklist legalpersonsblacklist = super.find(entity.getId());
		super.update(entity);
		return legalpersonsblacklist;
	}

	@Override
	@Transactional
	public Legalpersonsblacklist update(Legalpersonsblacklist entity, String... ignoreProperties) {
		Legalpersonsblacklist legalpersonsblacklist = super.update(entity, ignoreProperties);
		return legalpersonsblacklist;
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
	public void delete(Legalpersonsblacklist entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Legalpersonsblacklist> queryPageinfo(String company_name, String legalpersons, String legalcardid, Pageable pageable){
		 return  repository.queryPageinfo(company_name,legalpersons,legalcardid,pageable);
	}


}
