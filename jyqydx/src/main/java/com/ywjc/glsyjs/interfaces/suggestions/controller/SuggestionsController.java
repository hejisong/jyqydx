package com.ywjc.glsyjs.interfaces.suggestions.controller;

import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.interfaces.suggestions.entity.SuggestionsEntity;
import com.ywjc.glsyjs.interfaces.suggestions.repositories.SuggestionsRepository;
import com.ywjc.glsyjs.interfaces.suggestions.service.SuggestionsEntityService;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller("suggestionsController")
@RequestMapping("/interface/suggestions")
@CrossOrigin
public class SuggestionsController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "suggestionsEntityServiceImpl")
    private SuggestionsEntityService suggestionsEntityService;
    @Resource(name = "suggestionsRepository")
    private SuggestionsRepository suggestionsRepository;

    //获取所有记录
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public MessageUtil getSuggestionsList(@RequestParam(value = "title") String title, @RequestParam(value = "infotype") String infotype, @RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        title = StringUtils.isBlank(title) ? "%%" : "%" + title + "%";
        List<SuggestionsEntity> list = new ArrayList<>();
        if (StringUtils.isEmpty(companyid)) {
            list = suggestionsEntityService.querySuggestionsList(title, infotype);
        } else {
            list = suggestionsEntityService.querySuggestionsListByCompanyid(title, infotype, companyid);
        }
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("获取所有记录成功！");
        messageUtil.setResultObj(list);
        return messageUtil;
    }

    //获取所有记录
    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public MessageUtil detail(@RequestParam(value = "id") String id) {
        MessageUtil messageUtil = new MessageUtil();
        List<Suggestreply> list = suggestionsEntityService.queryDetail(id);
        messageUtil.setResultObj(list);

        messageUtil.setResult(true);
        messageUtil.setErrorMsg("获取所有记录成功！");
        messageUtil.setResultObj(list);
        return messageUtil;
    }

    //查询详情
    @ResponseBody
    @RequestMapping(value = "/queryone", method = RequestMethod.GET)
    public MessageUtil queryOneSuggestions(@RequestParam(value = "id") String id) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(id)) {
            SuggestionsEntity suggestionsEntity = suggestionsEntityService.queryOneSuggestions(id);
            suggestionsEntityService.updateReadcountByid(id);
            messageUtil.setResultObj(suggestionsEntity);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取记录详情成功！");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("id为空!");
        }
        return messageUtil;
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil saveSuggestions(SuggestionsEntity suggestionsEntity) {
        MessageUtil messageUtil = new MessageUtil();
        suggestionsEntity.setId(UUID.randomUUID().toString().replace("-", ""));
//        suggestionsEntity.setCompanyname(param.getString("companyname"));
//        suggestionsEntity.setMobile(param.getString("mobile"));
//        suggestionsEntity.setTitle(param.getString("title"));
//        suggestionsEntity.setContent(param.getString("content"));
        suggestionsEntity.setState("0");
        suggestionsEntity.setReadcount(0);
//        suggestionsEntity.setCreaterid(param.getString("create"));
        suggestionsEntity.setCreatetime(new Date());
        suggestionsEntity.setDonerid("");
        suggestionsEntity.setDonetime(new Date());
        suggestionsEntity.setDoneresult("");
//        suggestionsEntity.setInfotype(param.getString("infotype"));
        suggestionsEntityService.save(suggestionsEntity);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("新增记录成功！");

        return messageUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/addReply", method = RequestMethod.GET)
    public MessageUtil addReply(Suggestreply suggestreply) {
        MessageUtil messageUtil = new MessageUtil();
        suggestreply.setReplyid(UUID.randomUUID().toString().replace("-", ""));
        suggestreply.setReplytime(new Date());
        suggestreply.setInfotype("2");
        SuggestionsEntity suggestionsEntity = suggestionsRepository.findOne(suggestreply.getSuggestid());
        suggestionsEntity.setState("0");
        suggestionsEntity.setUpdatetime(new Date());

        suggestionsEntityService.addReply(suggestreply);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("追问成功！");
        return messageUtil;
    }

}
