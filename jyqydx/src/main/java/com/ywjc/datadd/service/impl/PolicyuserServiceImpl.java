package com.ywjc.datadd.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.datadd.entity.Policyuser;
import com.ywjc.datadd.repositories.PolicyuserRepository;
import com.ywjc.datadd.service.PolicyuserService;
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


@Service("policyuserServiceImpl")
public class PolicyuserServiceImpl extends BaseServiceImpl<Policyuser, String>
		implements PolicyuserService {

	@Resource(name = "policyuserRepository")
	private PolicyuserRepository repository;


	@Resource(name = "policyuserRepository")
	public void setReposity(
			PagingAndSortingRepository<Policyuser, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Policyuser find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policyuser> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policyuser> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policyuser> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Policyuser> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Policyuser entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Policyuser update(Policyuser entity) {
		Policyuser policyuser = super.find(entity.getId());
		super.update(entity);
		return policyuser;
	}

	@Override
	@Transactional
	public Policyuser update(Policyuser entity, String... ignoreProperties) {
		Policyuser policyuser = super.update(entity, ignoreProperties);
		return policyuser;
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
	public void delete(Policyuser entity) {
		super.delete(entity);
	}

	@Override
	public Policyuser findUser(String name,String mobile){
       return repository.findUser(name,mobile);
	}


}
