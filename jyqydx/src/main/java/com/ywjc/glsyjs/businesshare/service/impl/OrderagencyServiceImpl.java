package com.ywjc.glsyjs.businesshare.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.businesshare.entity.Orderagency;
import com.ywjc.glsyjs.businesshare.repositories.OrderagencyRepository;
import com.ywjc.glsyjs.businesshare.service.OrderagencyService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("orderagencyServiceImpl")
public class OrderagencyServiceImpl extends BaseServiceImpl<Orderagency, String>
		implements OrderagencyService {

	@Resource(name = "orderagencyRepository")
	private OrderagencyRepository repository;


	@Resource(name = "orderagencyRepository")
	public void setReposity(
			PagingAndSortingRepository<Orderagency, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Orderagency find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orderagency> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orderagency> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orderagency> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Orderagency> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Orderagency entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Orderagency update(Orderagency entity) {
		Orderagency orderagency = super.find(entity.getOrderid());
		super.update(entity);
		return orderagency;
	}

	@Override
	@Transactional
	public Orderagency update(Orderagency entity, String... ignoreProperties) {
		Orderagency orderagency = super.update(entity, ignoreProperties);
		return orderagency;
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
	public void delete(Orderagency entity) {
		super.delete(entity);
	}

	@Override
	public List<Orderagency> findByordermanid(String ordermanid){
      return  repository.findByordermanid(ordermanid);
	}
	@Override
	public Page<Orderagency> findByagencymanid(String agencymanid,String orderman,String ordercompany,  Pageable pageable){
		return  repository.findByagencymanid(agencymanid,orderman,ordercompany,pageable);
	}


	@Override
	public Page<Orderagency> findOrderagencys(String orderman,String ordercompany, Pageable pageable){
		return  repository.findOrderagencys(orderman,ordercompany,pageable);
	}




	@Override
	public List<Orderagency> findByordercompanyid(String ordercompanyid){
		return  repository.findByordercompanyid(ordercompanyid);
	}

	@Override
	public Page<Orderagency> findPageByordercompanyid(String ordercompanyid,String orderman, Pageable pageable){
       return repository.findPageByordercompanyid( ordercompanyid, orderman,  pageable);
	}


}
