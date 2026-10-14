package com.ywjc.glsyjs.Policyservices.service.impl;


import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.Policyservices.repositories.PolicymateriallistRepository;
import com.ywjc.glsyjs.Policyservices.service.PolicymaterialService;
import com.ywjc.glsyjs.Policyservices.service.PolicymateriallistService;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service("policymateriallistServiceImpl")
public class PolicymateriallistServiceImpl extends BaseServiceImpl<Policymateriallist, String>
		implements PolicymateriallistService {

	@Resource(name = "policymateriallistRepository")
	private PolicymateriallistRepository repository;
	@Resource(name = "policymaterialServiceImpl")
	private PolicymaterialService policymaterialService;

	@Resource(name = "uploadfileServiceImpl")
	private UploadfileService uploadfileService;

	@Resource(name = "policymateriallistRepository")
	public void setReposity(
			PagingAndSortingRepository<Policymateriallist, String> repository) {
		super.setReposity(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Policymateriallist find(String id) {
		return super.find(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policymateriallist> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policymateriallist> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Policymateriallist> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Policymateriallist> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	@CacheEvict(value = "policymateriallist", allEntries = true)
	public void save(Policymateriallist entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Policymateriallist update(Policymateriallist entity) {
		Policymateriallist policymateriallist = super.find(entity.getListid());
		super.update(entity);
		return policymateriallist;
	}

	@Override
	@Transactional
	public Policymateriallist update(Policymateriallist entity, String... ignoreProperties) {
		Policymateriallist policymateriallist = super.update(entity, ignoreProperties);
		return policymateriallist;
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
	public void delete(Policymateriallist entity) {
		super.delete(entity);
	}

	@Override
	public List<Policymateriallist> findBymaterialid(String materialid){
       return repository.findBymaterialid(materialid);
	}
	@Override
	public String findmaterials(Policymaterial policymaterial){
		List<Policymateriallist> materiallists=findBymaterialid(policymaterial.getMaterialid());
		String materials="";
		if(!materiallists.isEmpty()){
			for (Policymateriallist list:materiallists) {
				materials+=list.getSn()+"."+list.getMaterialname();
			}
		}
		return materials;
	}

	@Override
	@Transactional
	public Policymaterial saveinfo(Policymaterial policymaterial, User user){
		List<Policymateriallist> newmateriallists=policymaterial.getMateriallists();
		List<Policymateriallist> oldmateriallists=new ArrayList<>();
		if(StringUtils.isNotBlank(policymaterial.getMaterialid())){
			oldmateriallists=findBymaterialid(policymaterial.getMaterialid());

		}else{
			policymaterial.setMaterialid(KeyUtils.getKey());
			policymaterial.setInputperid(user.getId());
			policymaterial.setInputper(user.getUsername());
			policymaterial.setCreattime(new Date());
			policymaterial.setUpdatetime(new Date());
			policymaterialService.save(policymaterial);
		}

		Map<String,List<Policymateriallist>> infomap=getFjMapByOldAndNew(oldmateriallists,newmateriallists);

		List<Policymateriallist> save=infomap.get("save");
		List<Policymateriallist> delete=infomap.get("delete");
		List<Policymateriallist> equal=infomap.get("equal");


		if(equal!=null&&equal.size()>0){
			for (Policymateriallist list:equal) {
				Policymateriallist oldlist=find(list.getListid());
				oldlist.setMaterialname(list.getMaterialname());
				if(StringUtils.isNotBlank(list.getModelfileid())&&StringUtils.isNotBlank(oldlist.getModelfileid())&&!list.getModelfileid().equals(oldlist.getModelfileid())){
					uploadfileService.updateFileIson("0",oldlist.getModelfileid());
				}
				oldlist.setSn(list.getSn());
				oldlist.setModelfileid(list.getModelfileid());
				oldlist.setModelfilename(list.getModelfilename());
				oldlist.setUpdatetime(new Date());
				update(oldlist);
			}

		}

		if(save!=null&&save.size()>0){
			for (Policymateriallist list:save) {
				list.setListid(KeyUtils.getKey());
				list.setMaterialid(policymaterial.getMaterialid());
				list.setInputperid(user.getId());
				list.setInputper(user.getUsername());
				list.setCreattime(new Date());
				list.setUpdatetime(new Date());
				save(list);

			}

		}

		if(delete!=null&&delete.size()>0){
			for (Policymateriallist list:delete) {
				uploadfileService.updateFileIson("0",list.getModelfileid());
				delete(list);

			}

		}

		return policymaterial;
	}





	private Map<String,List<Policymateriallist>> getFjMapByOldAndNew(List<Policymateriallist>  oldlist, List<Policymateriallist>  newlist){
		Map<String,List<Policymateriallist>> info=new HashMap<String,List<Policymateriallist>>();
		if(oldlist!=null&&oldlist.size()>0&&newlist!=null&&newlist.size()>0){
			List<Policymateriallist> equal=new ArrayList<Policymateriallist>();
			List<Policymateriallist> save=new ArrayList<Policymateriallist>();
			List<Policymateriallist> delete=new ArrayList<Policymateriallist>();

			for(Policymateriallist newinfo:newlist){
				if(StringUtils.isNotBlank(newinfo.getListid())){
					equal.add(newinfo);	//找出重复的
				}else{
					save.add(newinfo);//找出新增的
				}

			}
			//找出删除的
			for(Policymateriallist oldinfo:oldlist){
				boolean has=false;
				for(Policymateriallist equalinfo:equal){
					if(oldinfo.getListid().equals(equalinfo.getListid())){
						has=true;
					}
				}
				if(has==false){
					delete.add(oldinfo);
				}
			}


			info.put("save", save);
			info.put("delete", delete);
			info.put("equal", equal);
		}else{
			if(oldlist!=null&&oldlist.size()>0){
				info.put("delete", oldlist);
			}else if(newlist!=null&&newlist.size()>0){
				info.put("save", newlist);
			}
		}
		return info;
	}


}
