package com.ywjc.glsyjs.sys.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Taskdorecord;
import com.ywjc.glsyjs.sys.repositories.TaskdorecordRepository;
import com.ywjc.glsyjs.sys.service.TaskdorecordService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("taskdorecordServiceImpl")
public class TaskdorecordServiceImpl extends BaseServiceImpl<Taskdorecord, String>
		implements TaskdorecordService {

	@Resource(name = "taskdorecordRepository")
	private TaskdorecordRepository repository;


	@Resource(name = "taskdorecordRepository")
	public void setReposity(
			PagingAndSortingRepository<Taskdorecord, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Taskdorecord find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Taskdorecord> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Taskdorecord> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Taskdorecord> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Taskdorecord> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Taskdorecord entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Taskdorecord update(Taskdorecord entity) {
		Taskdorecord taskdorecord = super.find(entity.getId());
		super.update(entity);
		return taskdorecord;
	}

	@Override
	@Transactional
	public Taskdorecord update(Taskdorecord entity, String... ignoreProperties) {
		Taskdorecord taskdorecord = super.update(entity, ignoreProperties);
		return taskdorecord;
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
	public void delete(Taskdorecord entity) {
		super.delete(entity);
	}

	@Override
	public Page<Taskdorecord> findByTasktype(String tasktype, Pageable pageable){
        return repository.findByTasktype(tasktype,  pageable);
	}


}
