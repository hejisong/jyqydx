package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Businessipinfotrademark;
import com.ywjc.glsyjs.bigdata.repositories.BusinessipinfotrademarkRepository;
import com.ywjc.glsyjs.bigdata.service.BusinessipinfotrademarkService;
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

@Service("businessipinfotrademarkServiceImpl")
public class BusinessipinfotrademarkServiceImpl extends BaseServiceImpl<Businessipinfotrademark, String>
		implements BusinessipinfotrademarkService {

	@Resource(name = "businessipinfotrademarkRepository")
	private BusinessipinfotrademarkRepository repository;


	@Resource(name = "businessipinfotrademarkRepository")
	public void setReposity(
			PagingAndSortingRepository<Businessipinfotrademark, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Businessipinfotrademark find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Businessipinfotrademark> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Businessipinfotrademark> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Businessipinfotrademark> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Businessipinfotrademark> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Businessipinfotrademark entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Businessipinfotrademark update(Businessipinfotrademark entity) {
		Businessipinfotrademark businessipinfotrademark = super.find(entity.getId());
		super.update(entity);
		return businessipinfotrademark;
	}

	@Override
	@Transactional
	public Businessipinfotrademark update(Businessipinfotrademark entity, String... ignoreProperties) {
		Businessipinfotrademark businessipinfotrademark = super.update(entity, ignoreProperties);
		return businessipinfotrademark;
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
	public void delete(Businessipinfotrademark entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Businessipinfotrademark> queryPageinfo(String trademarkname, String companyname, Date applystartdate, Date applyenddate, Pageable pageable){
		if(applystartdate!=null&&applyenddate!=null){
			return repository.queryPageinfo(trademarkname,companyname,applystartdate,applyenddate,pageable);
		}else if(applystartdate!=null){
			return repository.queryPageinfoStartdate(trademarkname,companyname,applystartdate,pageable);
		}else if(applyenddate!=null){
			return repository.queryPageinfoEnddate(trademarkname,companyname,applyenddate,pageable);
		}
        return repository.queryPageinfoAll(trademarkname,companyname,pageable);
	}
	@Override
	@Transactional
	public Businessipinfotrademark findBytrademarkid(String trademarkid){
		return repository.findBytrademarkid(trademarkid);
	}
}
