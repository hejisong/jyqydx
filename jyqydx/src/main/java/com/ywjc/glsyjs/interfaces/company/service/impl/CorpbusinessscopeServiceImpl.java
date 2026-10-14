package com.ywjc.glsyjs.interfaces.company.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.company.entity.Corpbusinessscope;
import com.ywjc.glsyjs.interfaces.company.repositories.CorpbusinessscopeRepository;
import com.ywjc.glsyjs.interfaces.company.service.CorpbusinessscopeService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;


@Service("corpbusinessscopeServiceImpl")
public class CorpbusinessscopeServiceImpl extends BaseServiceImpl<Corpbusinessscope, String>
		implements CorpbusinessscopeService {

	@Resource(name = "corpbusinessscopeRepository")
	private CorpbusinessscopeRepository repository;


	@Resource(name = "corpbusinessscopeRepository")
	public void setReposity(
			PagingAndSortingRepository<Corpbusinessscope, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Corpbusinessscope find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Corpbusinessscope> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Corpbusinessscope> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Corpbusinessscope> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Corpbusinessscope> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Corpbusinessscope entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Corpbusinessscope update(Corpbusinessscope entity) {
		Corpbusinessscope corpbusinessscope = super.find(entity.getId());
		super.update(entity);
		return corpbusinessscope;
	}

	@Override
	@Transactional
	public Corpbusinessscope update(Corpbusinessscope entity, String... ignoreProperties) {
		Corpbusinessscope corpbusinessscope = super.update(entity, ignoreProperties);
		return corpbusinessscope;
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
	public void delete(Corpbusinessscope entity) {
		super.delete(entity);
	}

	@Override
	public Corpbusinessscope findByScorp(String companyid ,String scorp){
       return  repository.findByScorp(companyid,scorp);
	}

	@Override
	public List<Corpbusinessscope> findByScorpBycompanyid(String companyid){
		return  repository.findByScorpBycompanyid(companyid);
	}


}
