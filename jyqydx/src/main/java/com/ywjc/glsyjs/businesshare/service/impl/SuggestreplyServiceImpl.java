package com.ywjc.glsyjs.businesshare.service.impl;


import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import com.ywjc.glsyjs.businesshare.repositories.SuggestreplyRepository;
import com.ywjc.glsyjs.businesshare.service.SuggestreplyService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("suggestreplyServiceImpl")
public class SuggestreplyServiceImpl extends BaseServiceImpl<Suggestreply, String>
		implements SuggestreplyService {

	@Resource(name = "suggestreplyRepository")
	private SuggestreplyRepository repository;


	@Resource(name = "suggestreplyRepository")
	public void setReposity(
			PagingAndSortingRepository<Suggestreply, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Suggestreply find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Suggestreply> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Suggestreply> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Suggestreply> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Suggestreply> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Suggestreply entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Suggestreply update(Suggestreply entity) {
		Suggestreply suggestreply = super.find(entity.getReplyid());
		super.update(entity);
		return suggestreply;
	}

	@Override
	@Transactional
	public Suggestreply update(Suggestreply entity, String... ignoreProperties) {
		Suggestreply suggestreply = super.update(entity, ignoreProperties);
		return suggestreply;
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
	public void delete(Suggestreply entity) {
		super.delete(entity);
	}

	@Override
	public List<Suggestreply> findBySuggest(String suggestid){
      return repository.findBySuggest(suggestid);
	}


}
