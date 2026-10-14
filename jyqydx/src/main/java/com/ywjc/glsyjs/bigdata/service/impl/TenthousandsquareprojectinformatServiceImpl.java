package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Tenthousandsquareprojectinformat;
import com.ywjc.glsyjs.bigdata.repositories.TenthousandsquareprojectinformatRepository;
import com.ywjc.glsyjs.bigdata.service.TenthousandsquareprojectinformatService;
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


@Service("tenthousandsquareprojectinformatServiceImpl")
public class TenthousandsquareprojectinformatServiceImpl extends BaseServiceImpl<Tenthousandsquareprojectinformat, String>
		implements TenthousandsquareprojectinformatService {

	@Resource(name = "tenthousandsquareprojectinformatRepository")
	private TenthousandsquareprojectinformatRepository repository;


	@Resource(name = "tenthousandsquareprojectinformatRepository")
	public void setReposity(
			PagingAndSortingRepository<Tenthousandsquareprojectinformat, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Tenthousandsquareprojectinformat find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tenthousandsquareprojectinformat> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tenthousandsquareprojectinformat> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tenthousandsquareprojectinformat> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tenthousandsquareprojectinformat> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Tenthousandsquareprojectinformat entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Tenthousandsquareprojectinformat update(Tenthousandsquareprojectinformat entity) {
		Tenthousandsquareprojectinformat tenthousandsquareprojectinformat = super.find(entity.getId());
		super.update(entity);
		return tenthousandsquareprojectinformat;
	}

	@Override
	@Transactional
	public Tenthousandsquareprojectinformat update(Tenthousandsquareprojectinformat entity, String... ignoreProperties) {
		Tenthousandsquareprojectinformat tenthousandsquareprojectinformat = super.update(entity, ignoreProperties);
		return tenthousandsquareprojectinformat;
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
	public void delete(Tenthousandsquareprojectinformat entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Tenthousandsquareprojectinformat> queryPageinfo(String companyname, String year, String name_of_fair, Pageable pageable){
       return repository.queryPageinfo(companyname,year,name_of_fair,pageable);
	}


}
