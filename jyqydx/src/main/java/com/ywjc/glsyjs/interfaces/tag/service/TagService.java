package com.ywjc.glsyjs.interfaces.tag.service;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService extends BaseService<TagEntity, String> {

    Page<TagEntity> findPageAndSearch(Pageable pageable, String search);

    TagEntity addTag(TagEntity tagEntity);

    int modifyTag(TagEntity tagEntity);

    int deleteTag(String tagid);

    MessageUtil bindTag(TagRelationEntity tagRelationEntity);

    MessageUtil removeTagRelation(String id);

    MessageUtil tagRelationList(String search, Pageable pageable);

    MessageUtil modifyTagRelation(TagRelationEntity tagRelationEntity);

    List<TagEntity> findAll();

    List<TagEntity> findByCompanyid(String companyid);

    boolean saveTagRelation(String companyid,String tagid);

    List<TagEntity> queryTagByCompany(String cid);

    List<PolicyFileEntity> queryPolicyFileByCompany(String companyid, String tagid);


    public List<Object> findtag(String plfidid);

    public List<Object> findtags(String[] plfidids);

    public List<Object> findtagsByComoany(List<Object> companyids);

    List<TagEntity> findByPid(String pid);


    void savetag(List<TagEntity> tags, String pldtailid, User user);

    void saveCorptag(List<TagEntity> tags, String companyid, User user);


    TagEntity findTagByTagname(String tagname);

    void deleteByTagid(String tagid);

    public List<Object> findtag(String tagid,String cid);

    public List<Object> findcount(List<String> tagids,List<Object> companyids);

    public List<Object> findTagcount(String[] tagids,String cid);


    public List<String> findTagidByPid(String pid);

    public List<String> findTagidByCid(String cid);

    void deleteByCid(String tagid,String cid);

}
