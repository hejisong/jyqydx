package com.ywjc.glsyjs.interfaces.tag.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.repositories.AccuratetagrelationRepository;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagrelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("accuratetagrelationServiceImpl")
public class AccuratetagrelationServiceImpl extends BaseServiceImpl<Accuratetagrelation, String>
		implements AccuratetagrelationService {

	@Resource(name = "accuratetagrelationRepository")
	private AccuratetagrelationRepository repository;
	@Resource(name = "queryServiceImpl")
	private QueryService queryService;

	@Resource(name = "accuratetagrelationRepository")
	public void setReposity(
			PagingAndSortingRepository<Accuratetagrelation, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Accuratetagrelation find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accuratetagrelation> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accuratetagrelation> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accuratetagrelation> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Accuratetagrelation> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Accuratetagrelation entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Accuratetagrelation update(Accuratetagrelation entity) {
		Accuratetagrelation accuratetagrelation = super.find(entity.getId());
		super.update(entity);
		return accuratetagrelation;
	}

	@Override
	@Transactional
	public Accuratetagrelation update(Accuratetagrelation entity, String... ignoreProperties) {
		Accuratetagrelation accuratetagrelation = super.update(entity, ignoreProperties);
		return accuratetagrelation;
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
	public void delete(Accuratetagrelation entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Object> findPage(String companyname,String tagyear,String tagname, Pageable pageable){

		List<Object> values = new ArrayList<Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String columsql=" a.id, c.enterprisename,c.uscreditcode,a.tagyear,a.tagname,a.tagid ";
		String sql= " from (select * from accuratetagrelation where cid is not null) a  left join company c on a.cid=c.companyid where a.pid is  null   ";


		if(StringUtils.isNotBlank(companyname)){
			sql = sql + " and c.enterprisename like ?  ";
			companyname = "%" + companyname.trim() + "%";
			values.add(companyname);
//			values.add(companyname);
		}
		if(StringUtils.isNotBlank(tagyear)){
			sql = sql + " and a.tagyear like ?  ";
			tagyear = "%" + tagyear.trim() + "%";
			values.add(tagyear);
		}
		if(StringUtils.isNotBlank(tagname)){
			sql = sql + " and a.tagname like ?  ";
			tagname = "%" + tagname.trim() + "%";
			values.add(tagname);
		}



		String order=" order by  a.tagyear desc,a.cid, a.createtime desc ";
		Page<Object> page = queryService.queryObjectForPage(columsql,sql,order,values,pageable);
		return  page;
	}


	@Override
	@Transactional
	public  Accuratetagrelation findByCorp(String cid,String tagid,String tagyear){
         return repository.findByCorp(cid,tagid,tagyear);
	}


	@Override
	@Transactional
	public void updateCompanyid(String cid,String companyid){
		repository.updateCompanyid(cid,companyid);
	}


}
