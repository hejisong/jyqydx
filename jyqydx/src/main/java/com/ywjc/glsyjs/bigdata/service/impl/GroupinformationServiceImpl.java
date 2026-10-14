package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Groupinformation;
import com.ywjc.glsyjs.bigdata.repositories.GroupinformationRepository;
import com.ywjc.glsyjs.bigdata.service.GroupinformationService;
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


@Service("groupinformationServiceImpl")
public class GroupinformationServiceImpl extends BaseServiceImpl<Groupinformation, String>
		implements GroupinformationService {

	@Resource(name = "groupinformationRepository")
	private GroupinformationRepository repository;


	@Resource(name = "groupinformationRepository")
	public void setReposity(
			PagingAndSortingRepository<Groupinformation, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Groupinformation find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Groupinformation> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Groupinformation> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Groupinformation> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Groupinformation> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Groupinformation entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Groupinformation update(Groupinformation entity) {
		Groupinformation groupinformation = super.find(entity.getGroupid());
		super.update(entity);
		return groupinformation;
	}

	@Override
	@Transactional
	public Groupinformation update(Groupinformation entity, String... ignoreProperties) {
		Groupinformation groupinformation = super.update(entity, ignoreProperties);
		return groupinformation;
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
	public void delete(Groupinformation entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Groupinformation> queryPageinfo(String groupname, String pcompanyname, String registrationnumber, Pageable pageable){
           return repository.queryPageinfo(groupname,pcompanyname,registrationnumber,pageable);
	}


}
