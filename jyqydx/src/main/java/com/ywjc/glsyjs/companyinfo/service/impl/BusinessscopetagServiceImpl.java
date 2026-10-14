package com.ywjc.glsyjs.companyinfo.service.impl;


import java.util.List;

import javax.annotation.Resource;


import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import com.ywjc.glsyjs.companyinfo.repositories.BusinessscopetagRepository;
import com.ywjc.glsyjs.companyinfo.service.BusinessscopetagService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("businessscopetagServiceImpl")
public class BusinessscopetagServiceImpl extends BaseServiceImpl<Businessscopetag, String>
		implements BusinessscopetagService {

	@Resource(name = "businessscopetagRepository")
	private BusinessscopetagRepository repository;


	@Resource(name = "businessscopetagRepository")
	public void setReposity(
			PagingAndSortingRepository<Businessscopetag, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Businessscopetag find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Businessscopetag> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Businessscopetag> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Businessscopetag> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Businessscopetag> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Businessscopetag entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Businessscopetag update(Businessscopetag entity) {
		Businessscopetag businessscopetag = super.find(entity.getId());
		super.update(entity);
		return businessscopetag;
	}

	@Override
	@Transactional
	public Businessscopetag update(Businessscopetag entity, String... ignoreProperties) {
		Businessscopetag businessscopetag = super.update(entity, ignoreProperties);
		return businessscopetag;
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
	public void delete(Businessscopetag entity) {
		super.delete(entity);
	}


	@Override
	@Transactional
	public Page<Businessscopetag> queryPage(String businessscope, Pageable pageable){
		return repository.queryPage(businessscope,pageable);
	}

	@Override
	@Transactional
	public List<String> findscope(){
        return repository.findscope();
	}

	@Override
	@Transactional
	public Businessscopetag findByscope(String businessscope){
		return repository.findByscope(businessscope);
	}


}
