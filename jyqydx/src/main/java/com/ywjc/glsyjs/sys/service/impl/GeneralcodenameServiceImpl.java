package com.ywjc.glsyjs.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Generalcodename;
import com.ywjc.glsyjs.sys.repositories.GeneralcodenameRepository;
import com.ywjc.glsyjs.sys.service.GeneralcodenameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("generalcodenameServiceImpl")
public class GeneralcodenameServiceImpl extends
        BaseServiceImpl<Generalcodename, String> implements
        GeneralcodenameService {

	@Resource(name = "generalcodenameRepository")
	private GeneralcodenameRepository repository;

	@Resource(name = "generalcodenameRepository")
	public void setReposity(
			PagingAndSortingRepository<Generalcodename, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Generalcodename find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Generalcodename> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Generalcodename> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Generalcodename> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Generalcodename> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Generalcodename entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Generalcodename update(Generalcodename entity) {
		Generalcodename generalcodename = super.find(entity.getCodetableid());
		super.update(entity);
		return generalcodename;
	}

	@Override
	@Transactional
	public Generalcodename update(Generalcodename entity,
			String... ignoreProperties) {
		Generalcodename generalcodename = super
				.update(entity, ignoreProperties);
		return generalcodename;
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
	public void delete(Generalcodename entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Generalcodename> findByCodetablenameLikeAndCodetabledescLikeAndIsusedLike(
			String codetablename, String codetabledesc, String isused,
			Pageable pageable) {
		return repository.findByCodetablenameLikeAndCodetabledescLikeAndIsusedLike(codetablename, codetabledesc, isused, pageable);
	}

	@Override
	@Transactional
	public Generalcodename findByCodetablename(String codetablename) {
		return repository.findByCodetablename(codetablename);
	}
	
	@Override
	@Transactional
	public Long findMaxNo() {
		return repository.findMaxNo();
	}
	
	@Override
	@Transactional
	public List<Generalcodename> findCities(String codetabledesc){
		return repository.findCities(codetabledesc);
	}

	@Override
	public List<Object[]> findCitiesAll() {
		
        List<Generalcodename> provinces= findCities("%城市代码%");
        List<Object[]> provincess=new ArrayList<Object[]>();
        if(provinces!=null&&!provinces.isEmpty()){
        	for(Generalcodename province:provinces){
        		if(province.getCodetabledesc()!=null&&province.getCodetabledesc().trim().length()>0){
        			String provin=province.getCodetabledesc().substring(0,province.getCodetabledesc().length()-4);
        			provincess.add(new Object[]{province.getCodetableid(),provin});
        		}
        	}
        }
		return provincess;
	}
}
