package com.ywjc.glsyjs.sys.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.repositories.OrganinfoRepository;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("organinfoServiceImpl")
public class OrganinfoServiceImpl extends BaseServiceImpl<Organinfo, String>
		implements OrganinfoService {

	@Resource(name = "organinfoRepository")
	private OrganinfoRepository repository;


	@Resource(name = "organinfoRepository")
	public void setReposity(
			PagingAndSortingRepository<Organinfo, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = false)
	public Organinfo find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organinfo> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organinfo> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organinfo> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Organinfo> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Organinfo entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Organinfo update(Organinfo entity) {
		Organinfo organinfo = super.find(entity.getId());
		super.update(entity);
		return organinfo;
	}

	@Override
	@Transactional
	public Organinfo update(Organinfo entity, String... ignoreProperties) {
		Organinfo organinfo = super.update(entity, ignoreProperties);
		return organinfo;
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
	public void delete(Organinfo entity) {
		super.delete(entity);
	}

	


	@Override
	@Transactional(readOnly = true)
//	@Cacheable("organinfo")
	public Organinfo findByOrgcode(String orgcode) {
		return repository.findByOrgcode(orgcode);
	}


	@Override
	public List<Organinfo> findOrgnames() {
		return repository.findOrgnames();
	}
	@Override
	public List<Organinfo> findOrgList(String orgname,String orgcode ){
		return repository.findOrgList(orgname,orgcode);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Organinfo>  findOrgans(String orgname){
		return repository.findOrgans(orgname);
	}

	@Override
	public List<Organinfo> findChildOrgs(String orgid ){
        return repository.findChildOrgs(orgid);
	}



	@Override
	@Transactional(readOnly = true)
	public Page<Object> findPage2(String orgname,String orgcode ,Pageable pageable) {
		List<Object> values=new ArrayList<Object>();
		String columnsql="tbl.id,tbl.orgname,tbl.orgcode,tbl.linkman,tbl.linkphone ";
		StringBuffer sql=new StringBuffer("from organinfo tbl where 1=1  ");
		
		if(StringUtils.isNotBlank(orgname)){
			 sql.append("and tbl.orgname like ? ");
			 values.add("%"+orgname.trim()+"%");
		}

		if(StringUtils.isNotBlank(orgcode)){
			 sql.append("and tbl.orgcode like ? ");
			 values.add("%"+orgcode.trim()+"%");
		}
		sql.append("order by tbl.createtime desc ,tbl.id asc");
		Query query =  entityManager.createNativeQuery("select count(*) from ( select "  +columnsql + sql+")");
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		Integer total = ((BigDecimal)query.getSingleResult()).intValue();
		query =  entityManager.createNativeQuery("select " + columnsql + " " + sql);
		for (int i = 0; i < values.size(); i++) {
			query.setParameter(i + 1, values.get(i));
		}
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		List<Object> content = total > pageable.getOffset() ? query.getResultList() : Collections.<Object> emptyList();
		return new PageImpl<Object>(content, pageable, total);
	}


	public Page<Organinfo> findOrgList(String organname,String orgcode,Pageable pageable){
         return repository.findOrgList(organname,orgcode,pageable);
	}

	@Override
	public List<Organinfo> findOrgs(){
		return repository.findOrgs();
	}

	@Override
	public List<Organinfo> findOpOrgs(){
		return repository.findOpOrgs();
	}

	
}
