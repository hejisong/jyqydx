package com.ywjc.glsyjs.bigdata.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.bigdata.entity.Socialconsumergoodsales;
import com.ywjc.glsyjs.bigdata.repositories.SocialconsumergoodsalesRepository;
import com.ywjc.glsyjs.bigdata.service.SocialconsumergoodsalesService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("socialconsumergoodsalesServiceImpl")
public class SocialconsumergoodsalesServiceImpl extends BaseServiceImpl<Socialconsumergoodsales, String>
		implements SocialconsumergoodsalesService {

	@Resource(name = "socialconsumergoodsalesRepository")
	private SocialconsumergoodsalesRepository repository;


	@Resource(name = "socialconsumergoodsalesRepository")
	public void setReposity(
			PagingAndSortingRepository<Socialconsumergoodsales, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Socialconsumergoodsales find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Socialconsumergoodsales> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Socialconsumergoodsales> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Socialconsumergoodsales> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Socialconsumergoodsales> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Socialconsumergoodsales entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Socialconsumergoodsales update(Socialconsumergoodsales entity) {
		Socialconsumergoodsales socialconsumergoodsales = super.find(entity.getId());
		super.update(entity);
		return socialconsumergoodsales;
	}

	@Override
	@Transactional
	public Socialconsumergoodsales update(Socialconsumergoodsales entity, String... ignoreProperties) {
		Socialconsumergoodsales socialconsumergoodsales = super.update(entity, ignoreProperties);
		return socialconsumergoodsales;
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
	public void delete(Socialconsumergoodsales entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Socialconsumergoodsales> queryPageinfo(String companyname,String year,  Double min_sales, Double max_sales, Pageable pageable){
		if(min_sales!=null&&max_sales!=null){
			return repository.queryPageinfo(companyname,year,min_sales,max_sales,pageable);
		}else if(min_sales!=null){
			return repository.queryPageinfoMin(companyname,year,min_sales,pageable);
		}else if(max_sales!=null){
			return repository.queryPageinfoMax(companyname,year,max_sales,pageable);
		}
		return repository.queryPageinfoAll(companyname,year,pageable);
	}


}
