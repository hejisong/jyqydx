package com.ywjc.glsyjs.interfaces.tag.controller;

import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController("tagController")
@RequestMapping("/interface/tag")
@CrossOrigin
public class TagController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "tagServiceImpl")
    private TagService tagEntityService;


    @RequestMapping("/queryList")
    public MessageUtil queryList(@RequestParam(required = false, value = "search") String search,
                                 @PageableDefault Pageable pageable) {
        MessageUtil messageUtil = new MessageUtil();
        search = (search == null ? "%%" : "%" + search + "%");
        Page<TagEntity> pageAndSearch = tagEntityService.findPageAndSearch(pageable, search);
        messageUtil.setResultObj(pageAndSearch);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("列表获取成功");
        return messageUtil;
    }

    @RequestMapping("/addTag")
    public MessageUtil addTag(@RequestBody TagEntity tagEntity) {
        MessageUtil messageUtil = new MessageUtil();
        tagEntity.setCreatetime(new Date());
        tagEntity = tagEntityService.addTag(tagEntity);
        messageUtil.setResultObj(tagEntity);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("添加成功");
        return messageUtil;
    }

    @RequestMapping("/modifyTag")
    public MessageUtil modifyTag(@RequestBody TagEntity tagEntity) {
        MessageUtil messageUtil = new MessageUtil();
        tagEntityService.modifyTag(tagEntity);
        messageUtil.setResultObj(tagEntity);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("修改成功");
        return messageUtil;
    }


    @RequestMapping("/removeTag/{tagid}")
    public MessageUtil removeTag(@PathVariable String tagid) {
        MessageUtil messageUtil = new MessageUtil();
        tagEntityService.deleteTag(tagid);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("删除成功");
        return messageUtil;
    }


    @RequestMapping("bindList")
    public MessageUtil tagRelationList(@RequestParam(required = false, value = "search") String search,
                                       @PageableDefault Pageable pageable) {
        search = (search == null ? "%%" : "%" + search + "%");
        MessageUtil messageUtil = tagEntityService.tagRelationList(search, pageable);
        return messageUtil;
    }

    @RequestMapping("bindTag")
    public MessageUtil bindTag(@RequestBody TagRelationEntity tagRelationEntity) {
        MessageUtil messageUtil = tagEntityService.bindTag(tagRelationEntity);
        return messageUtil;
    }

    @RequestMapping("bindRemove/{id}")
    public MessageUtil removeTagRelation(@PathVariable String id) {
        MessageUtil messageUtil = tagEntityService.removeTagRelation(id);
        return messageUtil;
    }

    @RequestMapping("rebind")
    public MessageUtil rebind(@RequestBody TagRelationEntity tagRelationEntity) {
        MessageUtil messageUtil = tagEntityService.modifyTagRelation(tagRelationEntity);
        return messageUtil;
    }


    //查询公司符合的tag
    @RequestMapping("queryTagByCompany")
    public MessageUtil queryTagByCompany(String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        List<TagEntity> tagEntities = tagEntityService.queryTagByCompany(companyid);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("查询成功");
        messageUtil.setResultObj(tagEntities);
        return messageUtil;
    }

    //查询公司符合的政策文件
    @RequestMapping("queryPolicyFileByCompany")
    public MessageUtil queryPolicyFileByCompany(String companyid, String tagid) {
        MessageUtil messageUtil = new MessageUtil();
        List<PolicyFileEntity> policyFileEntities = tagEntityService.queryPolicyFileByCompany(companyid, tagid);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("查询成功");
        messageUtil.setResultObj(policyFileEntities);
        return messageUtil;
    }

}
