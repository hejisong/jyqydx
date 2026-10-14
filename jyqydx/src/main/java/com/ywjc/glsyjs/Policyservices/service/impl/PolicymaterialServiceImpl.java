package com.ywjc.glsyjs.Policyservices.service.impl;


import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.Policyservices.repositories.PolicymaterialRepository;
import com.ywjc.glsyjs.Policyservices.service.PolicymaterialService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("policymaterialServiceImpl")
public class PolicymaterialServiceImpl extends BaseServiceImpl<Policymaterial, String>
		implements PolicymaterialService {

	@Resource(name = "policymaterialRepository")
	private PolicymaterialRepository repository;


	@Resource(name = "policymaterialRepository")
	public void setReposity(
			PagingAndSortingRepository<Policymaterial, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Policymaterial find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policymaterial> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policymaterial> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policymaterial> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Policymaterial> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Policymaterial entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Policymaterial update(Policymaterial entity) {
		Policymaterial policymaterial = super.find(entity.getMaterialid());
		super.update(entity);
		return policymaterial;
	}

	@Override
	@Transactional
	public Policymaterial update(Policymaterial entity, String... ignoreProperties) {
		Policymaterial policymaterial = super.update(entity, ignoreProperties);
		return policymaterial;
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
	public void delete(Policymaterial entity) {
		super.delete(entity);
	}

	@Override
	public Policymaterial findBypolicyfile(String policyfileid,String nplcategory,String policyyear){
		return repository.findBypolicyfile(policyfileid,nplcategory,policyyear);
	}
}
