package com.ywjc.glsyjs.interfaces.tag.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.policyfile.repositories.PolicydetailRepository;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import com.ywjc.glsyjs.interfaces.tag.repositories.TagRelationRepository;
import com.ywjc.glsyjs.interfaces.tag.repositories.TagRepository;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.repositories.PolicyFileEntityRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("tagServiceImpl")
public class TagServiceImpl extends BaseServiceImpl<TagEntity, String> implements
        TagService {

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;
    @Resource(name = "tagRelationRepository")
    private TagRelationRepository tagRelationRepository;
    @Resource(name = "policyFileEntityRepository")
    private PolicyFileEntityRepository policyFileEntityRepository;

    @Resource(name = "policydetailRepository")
    private PolicydetailRepository policydetailRepository;


    @Resource(name = "tagRepository")
    public void setReposity(PagingAndSortingRepository<TagEntity, String> tagRepository) {
        super.setReposity(tagRepository);
    }

    @Override
    public Page<TagEntity> findPageAndSearch(Pageable pageable, String search) {
        return tagRepository.queryPageBySearch(search, pageable);
    }

    @Override
    public TagEntity addTag(TagEntity tagEntity) {
        return tagRepository.save(tagEntity);
    }

    @Override
    @Transactional
    public int modifyTag(TagEntity tagEntity) {
        return tagRepository.update(tagEntity.getTagid(), tagEntity.getTagname());
    }

    @Override
    public int deleteTag(String tagid) {
        return tagRepository.deleteTag(Integer.parseInt(tagid));

    }

    @Override
    public MessageUtil bindTag(TagRelationEntity tagRelationEntity) {
        MessageUtil messageUtil = new MessageUtil();
        if ((StringUtils.isEmpty(tagRelationEntity.getCid()) && StringUtils.isEmpty(tagRelationEntity.getPid())) ||
                (StringUtils.isNotEmpty(tagRelationEntity.getCid()) && StringUtils.isNotEmpty(tagRelationEntity.getPid()))) {
            messageUtil.setErrorMsg("请检查政策或者公司");
            messageUtil.setResult(false);
        } else if (StringUtils.isEmpty(tagRelationEntity.getTagid())) {
            messageUtil.setErrorMsg("请检查标签");
            messageUtil.setResult(false);
        } else {
            TagRelationEntity data = tagRelationRepository.queryOne(tagRelationEntity.getTagid(), tagRelationEntity.getPid(), tagRelationEntity.getCid());
            if (data != null) {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("标签已经存在！请检查后重试");
            } else {
                tagRelationEntity.setCreatetime(new Date());
                tagRelationRepository.save(tagRelationEntity);
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("标签绑定成功");
            }
        }

        return messageUtil;
    }

    @Override
    public MessageUtil removeTagRelation(String id) {
        MessageUtil messageUtil = new MessageUtil();
        tagRelationRepository.deleteTagRelation(id);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("删除成功");
        return messageUtil;
    }

    @Override
    public MessageUtil tagRelationList(String search, Pageable pageable) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject bizData = new JSONObject();
        List<Object[]> tagRelationEntities = tagRelationRepository.queryPageBySearch(search, pageable.getOffset(), pageable.getPageSize());
        List<TagRelationEntity> result = new ArrayList<>();
        tagRelationEntities.forEach(info -> {
            TagRelationEntity tagRelationEntity = new TagRelationEntity();
            tagRelationEntity.setId(String.valueOf(info[0]));
            tagRelationEntity.setTagid(String.valueOf(info[1]));
            tagRelationEntity.setPid(String.valueOf(info[2]));
            tagRelationEntity.setCid(String.valueOf(info[3]));
            tagRelationEntity.setCreatetime((Date) info[4]);
            tagRelationEntity.setUpdatetime((Date) info[5]);
            tagRelationEntity.setCreateuser(String.valueOf(info[6]));
            tagRelationEntity.setUpdateuser(String.valueOf(info[7]));
            tagRelationEntity.setTagname(String.valueOf(info[8]));
            tagRelationEntity.setEnterprisename(String.valueOf(info[9]));
            tagRelationEntity.setPolicyname(String.valueOf(info[10]));
            result.add(tagRelationEntity);
        });

        int total = tagRelationRepository.queryPageBySearchTotal(search);
        bizData.put("objList", result);
        bizData.put("total", total);

        messageUtil.setResultObj(bizData);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("获取列表成功");

        return messageUtil;
    }

    @Override
    public MessageUtil modifyTagRelation(TagRelationEntity tagRelationEntity) {
        MessageUtil messageUtil = new MessageUtil();
        if ((StringUtils.isEmpty(tagRelationEntity.getCid()) && StringUtils.isEmpty(tagRelationEntity.getPid())) ||
                (StringUtils.isNotEmpty(tagRelationEntity.getCid()) && StringUtils.isNotEmpty(tagRelationEntity.getPid()))) {
            messageUtil.setErrorMsg("请检查政策或者公司");
            messageUtil.setResult(false);
        } else if (StringUtils.isEmpty(tagRelationEntity.getTagid())) {
            messageUtil.setErrorMsg("请检查标签");
            messageUtil.setResult(false);
        } else {
            TagRelationEntity data = tagRelationRepository.queryOne(tagRelationEntity.getTagid(), tagRelationEntity.getPid(), tagRelationEntity.getCid());
            if (data != null && !data.getId().equals(tagRelationEntity.getId())) {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("标签已经存在！请检查后重试");
            } else {
                tagRelationRepository.update(tagRelationEntity.getTagid(), tagRelationEntity.getCid(), tagRelationEntity.getPid(), tagRelationEntity.getId());
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("标签绑定成功");
            }
        }

        return messageUtil;
    }

    @Override
    public List<Object> findtag(String tagid,String cid){
        return tagRelationRepository.findtag(tagid,cid);
    }

    @Override
    public List<Object> findcount(List<String> tagids,List<Object> companyids){
        return tagRelationRepository.findcount(tagids,companyids);
    }
    @Override
    public List<Object> findTagcount(String[] tagids,String cid){
        return tagRelationRepository.findTagcount(tagids,cid);
    }


    @Override
    public List<TagEntity> queryTagByCompany(String companyid) {
        List<String> tags;
        if (StringUtils.isEmpty(companyid)) {
            tags = tagRelationRepository.queryAllTag();
        } else {
            tags = tagRelationRepository.queryTagByCompany(companyid);
        }
        List<TagEntity> tagEntities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tags)) {
            tagEntities = tagRepository.queryTagsById(tags);
        }

        return tagEntities;
    }

    @Override
    public List<PolicyFileEntity> queryPolicyFileByCompany(String companyid, String tagid) {
        List<String> tags = new ArrayList<>();

        if (StringUtils.isEmpty(tagid)) {
            List<TagEntity> tagEntities = queryTagByCompany(companyid);
            tagEntities.forEach(info -> {
                tags.add(info.getTagid());
            });
        } else {
            tags.add(tagid);
        }

        List<PolicyFileEntity> policyFileEntities = new ArrayList<>();
        if (CollectionUtils.isEmpty(tags)) {
            return policyFileEntities;
        }
        //政策细则id
        List<String> queryPolicyfileByTag = tagRelationRepository.queryPolicyfileByTag(tags);
        List<String> pid = new ArrayList<>();
        queryPolicyfileByTag.forEach(info -> {
            if (StringUtils.isNotEmpty(info)) {
                pid.add(info);
            }
        });
        if (!CollectionUtils.isEmpty(pid)) {
            //根据政策细则id找到政策原文id
            List<String> queryplfididsBypldtailid =policydetailRepository.queryplfididsBypldtailid(pid);
            if (!CollectionUtils.isEmpty(queryplfididsBypldtailid)) {
                policyFileEntities = policyFileEntityRepository.queryPolicyFileById(queryplfididsBypldtailid);
            }


        }
        return policyFileEntities;
    }



    @Override
    public List<TagEntity> findAll(){
        return tagRepository.findAll();
    }

    @Override
    public List<TagEntity> findByCompanyid(String companyid){
        return tagRepository.findByCompanyid(companyid);
    }


    @Override
    public List<TagEntity> findByPid(String pid){
        return tagRepository.findByPid(pid);
    }


    @Override
    public List<Object> findtag(String plfidid){
        return tagRepository.findtag(plfidid);
    }
    @Override
    public List<Object> findtags(String[] plfidids){
        List<String> pldtailids=tagRepository.findpldtailids(plfidids);
        if(pldtailids!=null&&pldtailids.size()>0){
            return tagRepository.findtags(pldtailids);
        }
        return null;
    }

    @Override
    public List<Object> findtagsByComoany(List<Object> companyids){
        if(companyids!=null&&companyids.size()>0){
            return tagRepository.findtagsByComoany(companyids);
        }
        return null;
    }

    public List<String> findTagidByPid(String pid){
        return tagRelationRepository.findTagidByPid(pid);
    }


    @Override
    public  TagEntity findTagByTagname(String tagname){
        return tagRepository.findTagByTagname(tagname);
    }


    @Override
    @Transactional
    public  void deleteByTagid(String tagid){
        tagRepository.deleteByTagid(tagid);
        delete(tagid);
    }

    @Override
    public List<String> findTagidByCid(String cid){
        return tagRelationRepository.findTagidByCid(cid);
    }
    @Override
    @Transactional
    public void deleteByCid(String tagid,String cid){
        tagRepository.deleteByCid(tagid,cid);
    }


    @Override
    @Transactional
    public  boolean saveTagRelation(String companyid,String tagid){
        boolean isadd=false;
        TagRelationEntity tagRelation=tagRelationRepository.queryByCid(tagid,companyid);
        if(tagRelation==null){
            tagRelation=new TagRelationEntity();
            tagRelation.setCid(companyid);
            tagRelation.setTagid(tagid);
            tagRelationRepository.save(tagRelation);
            isadd=true;
        }
        return isadd;
    }

    @Override
    @Transactional
    public void savetag(List<TagEntity> tags,String pldtailid,User user){
        List<String> tagidsolds=tagRelationRepository.findTagidByPid(pldtailid);
        List<String> tagids=new ArrayList<>();
         if(tags!=null&&tags.size()>0){
             for (TagEntity tag:tags) {
                 if(StringUtils.isNotBlank(tag.getTagid())){
                     TagRelationEntity tagRelation=tagRelationRepository.queryByPid(tag.getTagid(),pldtailid);
                     if(tagRelation==null){
                         tagRelation=new TagRelationEntity();
                         tagRelation.setPid(pldtailid);
                         tagRelation.setTagid(tag.getTagid());
                         tagRelation.setCreateuser(user.getUsername());
                         tagRelation.setCreatetime(new Date());
                         tagRelationRepository.save(tagRelation);
                     }else{
                         tagids.add(tag.getTagid());
                     }
                 }


             }
         }

        if(tagidsolds!=null&&tagidsolds.size()>0){
            for (String tagidsold:tagidsolds) {
                if(!tagids.contains(tagidsold)){
                    TagRelationEntity tagRelation=tagRelationRepository.queryByPid(tagidsold,pldtailid);
                    if(tagRelation!=null){
                        tagRelationRepository.delete(tagRelation);
                    }

                }
            }
        }
    }


    @Override
    @Transactional
    public void saveCorptag(List<TagEntity> tags, String companyid, User user){
        List<String> tagidsolds=tagRelationRepository.findTagidByCid(companyid);
        List<String> tagids=new ArrayList<>();
        if(tags!=null&&tags.size()>0){
            for (TagEntity tag:tags) {
                if(StringUtils.isNotBlank(tag.getTagid())){
                    TagRelationEntity tagRelation=tagRelationRepository.queryByCid(tag.getTagid(),companyid);
                    if(tagRelation==null){
                        tagRelation=new TagRelationEntity();
                        tagRelation.setCid(companyid);
                        tagRelation.setTagid(tag.getTagid());
                        tagRelation.setCreateuser(user.getUsername());
                        tagRelation.setCreatetime(new Date());
                        tagRelationRepository.save(tagRelation);
                    }else{
                        tagids.add(tag.getTagid());
                    }
                }


            }
        }

        if(tagidsolds!=null&&tagidsolds.size()>0){
            for (String tagidsold:tagidsolds) {
                if(!tagids.contains(tagidsold)){
                    TagRelationEntity tagRelation=tagRelationRepository.queryByCid(tagidsold,companyid);
                    if(tagRelation!=null){
                        tagRelationRepository.delete(tagRelation);
                    }

                }
            }
        }
    }

}
