package com.ywjc.datashare.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.datashare.entity.Personinfo;
import com.ywjc.datashare.repositories.PersoninfoRepository;
import com.ywjc.datashare.service.PersoninfoService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("personinfoServiceImpl")
public class PersoninfoServiceImpl extends BaseServiceImpl<Personinfo, String>
		implements PersoninfoService {

	@Resource(name = "personinfoRepository")
	private PersoninfoRepository repository;


	@Resource(name = "personinfoRepository")
	public void setReposity(
			PagingAndSortingRepository<Personinfo, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Personinfo find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personinfo> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personinfo> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Personinfo> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Personinfo> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Personinfo entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Personinfo update(Personinfo entity) {
		Personinfo personinfo = super.find(entity.getUserid());
		super.update(entity);
		return personinfo;
	}

	@Override
	@Transactional
	public Personinfo update(Personinfo entity, String... ignoreProperties) {
		Personinfo personinfo = super.update(entity, ignoreProperties);
		return personinfo;
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
	public void delete(Personinfo entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public List<Personinfo> findListAll(){
		return repository.findListAll();
	}

	@Override
	@Transactional
	public Personinfo findByid(String personid){
		return repository.findByid(personid);
	}
}
