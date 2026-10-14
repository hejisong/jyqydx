package com.ywjc.glsyjs.interfaces.flow.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.flow.entity.Flowauditinfo;
import com.ywjc.glsyjs.interfaces.flow.repositories.FlowauditinfoRepository;
import com.ywjc.glsyjs.interfaces.flow.service.FlowauditinfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("flowauditinfoServiceImpl")
public class FlowauditinfoServiceImpl extends BaseServiceImpl<Flowauditinfo, String>
		implements FlowauditinfoService {

	@Resource(name = "flowauditinfoRepository")
	private FlowauditinfoRepository repository;


	@Resource(name = "flowauditinfoRepository")
	public void setReposity(
			PagingAndSortingRepository<Flowauditinfo, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Flowauditinfo find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flowauditinfo> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flowauditinfo> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flowauditinfo> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Flowauditinfo> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Flowauditinfo entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Flowauditinfo update(Flowauditinfo entity) {
		Flowauditinfo flowauditinfo = super.find(entity.getInfoid());
		super.update(entity);
		return flowauditinfo;
	}

	@Override
	@Transactional
	public Flowauditinfo update(Flowauditinfo entity, String... ignoreProperties) {
		Flowauditinfo flowauditinfo = super.update(entity, ignoreProperties);
		return flowauditinfo;
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
	public void delete(Flowauditinfo entity) {
		super.delete(entity);
	}


	@Override
	public List<Flowauditinfo> findByauditid(String auditid){
      return repository.findByauditid(auditid);
	}

	@Override
	public Flowauditinfo findByauditidone(String auditid,String deptname){
		return repository.findByauditidone(auditid,deptname);
	}

}
