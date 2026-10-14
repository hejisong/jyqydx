package com.ywjc.glsyjs.interfaces.tag.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.Policyservices.utils.CorptagValidate;
import com.ywjc.glsyjs.Policyservices.utils.PolicydetailValidate;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import com.ywjc.glsyjs.interfaces.tag.repositories.AccuratetagRepository;
import com.ywjc.glsyjs.interfaces.tag.repositories.AccuratetagrelationRepository;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;


@Service("accuratetagServiceImpl")
public class AccuratetagServiceImpl extends BaseServiceImpl<Accuratetag, String>
		implements AccuratetagService {

	@Resource(name = "accuratetagRepository")
	private AccuratetagRepository repository;

	@Resource(name = "accuratetagrelationRepository")
	private AccuratetagrelationRepository accuratetagrelationRepository;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;


	@Resource(name = "accuratetagRepository")
	public void setReposity(
			PagingAndSortingRepository<Accuratetag, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Accuratetag find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accuratetag> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accuratetag> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Accuratetag> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Accuratetag> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Accuratetag entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Accuratetag update(Accuratetag entity) {
		Accuratetag accuratetag = super.find(entity.getTagid());
		super.update(entity);
		return accuratetag;
	}

	@Override
	@Transactional
	public Accuratetag update(Accuratetag entity, String... ignoreProperties) {
		Accuratetag accuratetag = super.update(entity, ignoreProperties);
		return accuratetag;
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
	public void delete(Accuratetag entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public Page<Accuratetag> findPageAndSearch(String tagname,Pageable pageable){
         return repository.findPageAndSearch(tagname,pageable);
	}


	@Override
	@Transactional
	public Accuratetag findTagByTagname(String tagname){
		return repository.findTagByTagname(tagname);
	}


	@Override
	@Transactional
	public  void deleteByTagid(String tagid){
		accuratetagrelationRepository.deleteByTagid(tagid);
		delete(tagid);
	}

	@Override
	public List<Object> findtags(String[] pids){
		return repository.findtags(pids);
	}


	@Override
	public List<Accuratetag> findByPid(String pid){
		return repository.findByPid(pid);
	}

	@Override
	public List<Object> findTagcount(String[] tagids,String cid,String year){
		return repository.findTagcount(tagids,cid,year);
	}


	@Override
	public List<String> findTagidByPid(String pid){
		return repository.findTagidByPid(pid);
	}

	@Override
	public List<Object> findcount(List<String> tagids,List<Object> companyids){
		return repository.findcount(tagids,companyids);
	}

	@Override
	public List<String> findTagidByCid(String cid){
		return repository.findTagidByCid(cid);
	}

	@Override
	public List<Object> findPTagcount(List<String>tagids,String[] pids){
		return repository.findPTagcount(tagids,pids);
	}

	@Override
	@Transactional
	public void savetag(List<Accuratetag> tags, String pldtailid, User user){

		List<String> tagids=new ArrayList<>();
		if(tags!=null&&tags.size()>0){
			List<String> tagidsolds=accuratetagrelationRepository.findTagidByPid(pldtailid);
			for (Accuratetag tag:tags) {
				if(StringUtils.isNotBlank(tag.getTagid())){
					String[] tagidname=tag.getTagid().split("-");
					Accuratetagrelation tagRelation=accuratetagrelationRepository.queryByPid(tagidname[0],pldtailid);
					if(tagRelation==null){
						tagRelation=new Accuratetagrelation();
						tagRelation.setPid(pldtailid);
						tagRelation.setTagid(tagidname[0]);
						tagRelation.setTagname(HtmlUtils.htmlUnescape(tagidname[1]));
						tagRelation.setCreateuser(user.getUsername());
						tagRelation.setCreatetime(new Date());
						accuratetagrelationRepository.save(tagRelation);
					}else{
						tagids.add(tagidname[0]);
					}
				}


			}
			if(tagidsolds!=null&&tagidsolds.size()>0){
				for (String tagidsold:tagidsolds) {
					if(!tagids.contains(tagidsold)){
						Accuratetagrelation tagRelation=accuratetagrelationRepository.queryByPid(tagidsold,pldtailid);
						if(tagRelation!=null){
							accuratetagrelationRepository.delete(tagRelation);
						}

					}
				}
			}
		}


	}


	@Override
	@Transactional
	public void saveCorptag(List<Accuratetag> tags, String companyid, User user){
		List<String> tagidsolds=accuratetagrelationRepository.findTagidByCid(companyid);
		List<String> tagids=new ArrayList<>();
		if(tags!=null&&tags.size()>0){
			for (Accuratetag tag:tags) {
				if(StringUtils.isNotBlank(tag.getTagid())){
					Accuratetagrelation tagRelation=accuratetagrelationRepository.queryByCid(tag.getTagid(),companyid);
					if(tagRelation==null){
						tagRelation=new Accuratetagrelation();
						tagRelation.setCid(companyid);
						tagRelation.setTagid(tag.getTagid());
						tagRelation.setCreateuser(user.getUsername());
						tagRelation.setCreatetime(new Date());
						accuratetagrelationRepository.save(tagRelation);
					}else{
						tagids.add(tag.getTagid());
					}
				}


			}
		}

		if(tagidsolds!=null&&tagidsolds.size()>0){
			for (String tagidsold:tagidsolds) {
				if(!tagids.contains(tagidsold)){
					Accuratetagrelation tagRelation=accuratetagrelationRepository.queryByCid(tagidsold,companyid);
					if(tagRelation!=null){
						accuratetagrelationRepository.delete(tagRelation);
					}

				}
			}
		}
	}


	@Override
	public Object[] importInfoExcel(Workbook workbook, Uploadfile file,User user ){
		Object[] r=new Object[3];
		String companyname=null;
		String uscreditcode=null;
		String tagyear=null;
		String tagname=null;

		ValidateBinding bind=null;
		List<PolicydetailEntity> corps=new ArrayList<>();
		int num=-1;
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 循环sheet
			//获取第一张Sheet表
			Sheet sheet =workbook.getSheetAt(i);
			bind= CorptagValidate.validateCorptag(sheet);
			if(bind.hasErrors()){
				r[0]=num;
				r[1]=bind;
				return r;
			}
			//获取Sheet表中所包含的总行数
			int numberrow=sheet.getPhysicalNumberOfRows();
			num=0;
			for(int j=1;j<numberrow;j++){
				Row row = sheet.getRow(j); // 获得工作薄的第j行
				if(row!=null){
					for(int k=0;k<8;k++){// 获得工作薄的第j行的第k列的值
						Cell cell = row.getCell(k);
						if(cell!=null){
							cell.setCellType(Cell.CELL_TYPE_STRING);
							if(k==0){//企业名称
								companyname=publicExcel(cell).trim();
							}else if(k==1){//统一社会信用代码
								uscreditcode=publicExcel(cell).trim();
							}else if(k==2){//年份
								tagyear=publicExcel(cell).trim();
							}else if(k==3){//标签名称
								tagname=publicExcel(cell).trim();
							}


						}
					}
					//保存到数据库
					num=num+1;
					//查找企业
					CompanyEntity companyEntity=companyEntityService.queryCompanyOne(companyname,uscreditcode);
					if(companyEntity==null){
						companyEntity=new CompanyEntity();
						companyEntity.setCompanyid(KeyUtils.getKey());
						companyEntity.setUscreditcode(uscreditcode);
						companyEntity.setEnterprisename(companyname);
						companyEntityService.save(companyEntity);
					}
					Accuratetag tag=findTagByTagname(tagname);
					if(tag==null){
						tag.setTagname(tagname);
						tag.setCreateuser(user.getUsername());
						tag.setCreatetime(new Date());
						tag.setState("1");
						save(tag);
						tag=findTagByTagname(tagname);

					}
					Accuratetagrelation tagRelation=new Accuratetagrelation();
					tagRelation.setCid(companyEntity.getCompanyid());
					tagRelation.setTagid(tag.getTagid());
					tagRelation.setTagname(tagname);
					tagRelation.setTagyear(tagyear);
					tagRelation.setUploadfileid(file.getId());
					tagRelation.setUploadfilename(file.getFilename());
					tagRelation.setCreateuser(user.getUsername());
					tagRelation.setCreatetime(new Date());
					accuratetagrelationRepository.save(tagRelation);

				}
			}
		}

		r[0]=num;
		r[1]=bind;
		r[2]=corps;
		return r;
	}


	private static String publicExcel( Cell cell){
		String value = null;
		if(cell!=null){
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					value =Double.toString(cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					;
					break;
				default:
			}
		}

		return value;
	}


}
