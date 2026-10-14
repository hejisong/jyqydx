package com.ywjc.datashare.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.datashare.entity.Departmentinfo;
import com.ywjc.datashare.repositories.DepartmentinfoRepository;
import com.ywjc.datashare.service.DepartmentinfoService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("departmentinfoServiceImpl")
public class DepartmentinfoServiceImpl extends BaseServiceImpl<Departmentinfo, String>
		implements DepartmentinfoService {

	@Resource(name = "departmentinfoRepository")
	private DepartmentinfoRepository repository;


	@Resource(name = "departmentinfoRepository")
	public void setReposity(
			PagingAndSortingRepository<Departmentinfo, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Departmentinfo find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departmentinfo> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departmentinfo> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Departmentinfo> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Departmentinfo> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Departmentinfo entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Departmentinfo update(Departmentinfo entity) {
		Departmentinfo departmentinfo = super.find(entity.getDeptid());
		super.update(entity);
		return departmentinfo;
	}

	@Override
	@Transactional
	public Departmentinfo update(Departmentinfo entity, String... ignoreProperties) {
		Departmentinfo departmentinfo = super.update(entity, ignoreProperties);
		return departmentinfo;
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
	public void delete(Departmentinfo entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public List<Departmentinfo> findListAll(){
		return repository.findListAll();
	}

	@Override
	@Transactional
	public Departmentinfo findByid(String id){
		return repository.findByid(id);
	}
}
