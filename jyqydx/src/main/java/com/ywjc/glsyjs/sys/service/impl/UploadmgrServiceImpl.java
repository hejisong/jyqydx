package com.ywjc.glsyjs.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Uploadmgr;
import com.ywjc.glsyjs.sys.repositories.UploadmgrRepository;
import com.ywjc.glsyjs.sys.service.UploadmgrService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("uploadmgrServiceImpl")
public class UploadmgrServiceImpl extends BaseServiceImpl<Uploadmgr, String>
		implements UploadmgrService {

	@Resource(name = "uploadmgrRepository")
	private UploadmgrRepository repository;


	@Resource(name = "uploadmgrRepository")
	public void setReposity(
			PagingAndSortingRepository<Uploadmgr, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Uploadmgr find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadmgr> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadmgr> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadmgr> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Uploadmgr> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Uploadmgr entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Uploadmgr update(Uploadmgr entity) {
		Uploadmgr uploadmgr = super.find(entity.getId());
		super.update(entity);
		return uploadmgr;
	}

	@Override
	@Transactional
	public Uploadmgr update(Uploadmgr entity, String... ignoreProperties) {
		Uploadmgr uploadmgr = super.update(entity, ignoreProperties);
		return uploadmgr;
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
	public void delete(Uploadmgr entity) {
		super.delete(entity);
	}

	@Override
	public Uploadmgr findmgr(String infoid, String infotype, String year,String actiontype) {
		return repository.findmgr(infoid, infotype, year, actiontype);
	}


}
