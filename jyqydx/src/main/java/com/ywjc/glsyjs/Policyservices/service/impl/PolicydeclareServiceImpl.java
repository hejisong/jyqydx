package com.ywjc.glsyjs.Policyservices.service.impl;


import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.repositories.PolicydeclareRepository;
import com.ywjc.glsyjs.Policyservices.service.PolicydeclareService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("policydeclareServiceImpl")
public class PolicydeclareServiceImpl extends BaseServiceImpl<Policydeclare, String>
		implements PolicydeclareService {

	@Resource(name = "policydeclareRepository")
	private PolicydeclareRepository repository;


	@Resource(name = "policydeclareRepository")
	public void setReposity(
			PagingAndSortingRepository<Policydeclare, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Policydeclare find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policydeclare> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policydeclare> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policydeclare> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Policydeclare> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Policydeclare entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Policydeclare update(Policydeclare entity) {
		Policydeclare policydeclare = super.find(entity.getDeclareid());
		super.update(entity);
		return policydeclare;
	}

	@Override
	@Transactional
	public Policydeclare update(Policydeclare entity, String... ignoreProperties) {
		Policydeclare policydeclare = super.update(entity, ignoreProperties);
		return policydeclare;
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
	public void delete(Policydeclare entity) {
		super.delete(entity);
	}

	@Override
	public Page<Policydeclare> queryPageinfo(String declarename, Date startdate, Date enddate, String state, Pageable pageable){
		if(StringUtils.isNotBlank(state)){
			if(startdate!=null&&enddate!=null){
				return repository.queryPageinfoAll(declarename,startdate,enddate,state,pageable);
			}else if(startdate!=null){
				return repository.queryPageinfoStartdate(declarename,startdate,state,pageable);
			}else if(enddate!=null){
				return repository.queryPageinfoenddate(declarename,enddate,state,pageable);
			}else{
				return repository.queryPageinfo(declarename,state,pageable);
			}
		}else{
			if(startdate!=null&&enddate!=null){
				return repository.queryPageinfoAllNostate(declarename,startdate,enddate,pageable);
			}else if(startdate!=null){
				return repository.queryPageinfoStartdateNostate(declarename,startdate,pageable);
			}else if(enddate!=null){
				return repository.queryPageinfoenddateNostate(declarename,enddate,pageable);
			}else{
				return repository.queryPageinfoNostate(declarename,pageable);
			}
		}

	}

}
