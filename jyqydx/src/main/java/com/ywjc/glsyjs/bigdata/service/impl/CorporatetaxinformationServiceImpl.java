package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Corporatetaxinformation;
import com.ywjc.glsyjs.bigdata.repositories.CorporatetaxinformationRepository;
import com.ywjc.glsyjs.bigdata.service.CorporatetaxinformationService;
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


@Service("corporatetaxinformationServiceImpl")
public class CorporatetaxinformationServiceImpl extends BaseServiceImpl<Corporatetaxinformation, String>
		implements CorporatetaxinformationService {

	@Resource(name = "corporatetaxinformationRepository")
	private CorporatetaxinformationRepository repository;


	@Resource(name = "corporatetaxinformationRepository")
	public void setReposity(
			PagingAndSortingRepository<Corporatetaxinformation, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Corporatetaxinformation find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Corporatetaxinformation> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Corporatetaxinformation> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Corporatetaxinformation> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Corporatetaxinformation> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Corporatetaxinformation entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Corporatetaxinformation update(Corporatetaxinformation entity) {
		Corporatetaxinformation corporatetaxinformation = super.find(entity.getId());
		super.update(entity);
		return corporatetaxinformation;
	}

	@Override
	@Transactional
	public Corporatetaxinformation update(Corporatetaxinformation entity, String... ignoreProperties) {
		Corporatetaxinformation corporatetaxinformation = super.update(entity, ignoreProperties);
		return corporatetaxinformation;
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
	public void delete(Corporatetaxinformation entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Corporatetaxinformation> queryPageinfo(String companyname, String fullname, String tax, Pageable pageable){
		return repository.queryPageinfo(companyname,fullname,tax,pageable);
	}


}
