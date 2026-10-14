package com.ywjc.glsyjs.companyinfo.controller;


import com.ywjc.glsyjs.Policyservices.entity.PolicyDalei;
import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.Policyservices.service.PolicydeclareService;
import com.ywjc.glsyjs.Policyservices.service.PolicymaterialService;
import com.ywjc.glsyjs.Policyservices.service.PolicymateriallistService;
import com.ywjc.glsyjs.Policyservices.utils.KeyMatchUtil;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.companyinfo.entity.Businessscopetag;
import com.ywjc.glsyjs.companyinfo.service.BusinessscopetagService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Corpbusinessscope;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.service.CorpbusinessscopeService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import com.ywjc.glsyjs.sys.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller("companymgrController")
@RequestMapping("/companymgr")
public class CompanymgrController extends BaseController {

	/**
	 * 系统日志工具
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "businessscopetagServiceImpl")
	private BusinessscopetagService businessscopetagService;

	@Resource(name = "corpbusinessscopeServiceImpl")
	private CorpbusinessscopeService corpbusinessscopeService;

	@Resource(name = "tagServiceImpl")
	private TagService tagService;

	/**
	 * List
	 */
	@RequestMapping(value="/companylist",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String enterprisename,
			@RequestParam(required = false) String uscreditcode, HttpSession session,
			ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/companymgr/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("enterprisename", enterprisename);
		model.addAttribute("uscreditcode", uscreditcode);
		enterprisename= StringUtils.isNotBlank(enterprisename)?"%"+enterprisename+"%":"%%";
		uscreditcode= StringUtils.isNotBlank(uscreditcode)?"%"+uscreditcode+"%":"%%";
		Page<CompanyEntity> page=companyEntityService.queryCompanyPage( enterprisename, uscreditcode,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/companymgr/companylist";
	}




	@RequestMapping(value="/scopetaglist",method = RequestMethod.GET)
	public String scopetaglist(
			@PageableDefault(page = 0, size = 12) Pageable pageable,
			@RequestParam(required = false) String scope, HttpSession session,
			ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/companymgr/scopetaglist",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("scope", scope);
		scope= StringUtils.isNotBlank(scope)?"%"+scope+"%":"%%";

		Page<Businessscopetag> page=businessscopetagService.queryPage( scope,pageable);
		for (Businessscopetag  businessscopetag:page.getContent()) {
			if(StringUtils.isNotBlank(businessscopetag.getTag())){
				String[] tags=businessscopetag.getTag().split(",");
				businessscopetag.setTags(tags);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		List<TagEntity> tags=tagService.findAll();
		model.addAttribute("tags", tags);
		return "/policytag/scopetaglist";
	}




	/**
	 * save
	 */
	@RequestMapping(value="/savetag",method = RequestMethod.POST)
	@ResponseBody
	public String savecorptag(@Valid CompanyEntity info, ModelMap model, HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog( "/policytag/savetag", session, request, Constantval.LOGIN_VISIT);
		List<Businessscopetag> businessscopetags=info.getScopetags();
		if(businessscopetags!=null&&businessscopetags.size()>0){
			for (Businessscopetag businessscopetag:businessscopetags) {
				Businessscopetag scopetag=businessscopetagService.find(businessscopetag.getId());
				scopetag.setTag(businessscopetag.getTag());
				businessscopetagService.update(scopetag);
			}

		}
		return Constantval.CONSTANT_SUCCESS;
	}



	/**
	 * save
	 */
	@RequestMapping(value="/savescope",method = RequestMethod.POST)
	@ResponseBody
	public String savescope(String  businessscope,String tag, ModelMap model, HttpServletRequest request, HttpSession session) {
		Principal principal = (Principal) session.getAttribute("userinfo");

		Businessscopetag businessscopetag=businessscopetagService.findByscope(businessscope);
		if(businessscopetag!=null){
				return Constantval.CONSTANT_FAIL;
		}else{
			businessscopetag=new Businessscopetag();
			businessscopetag.setBusinessscope(businessscope);
			businessscopetag.setTag(tag);
			businessscopetagService.save(businessscopetag);
		}
		return Constantval.CONSTANT_SUCCESS;
	}



	@RequestMapping(value="/initinfo",method = RequestMethod.GET)
	public String initinfo(HttpSession session, ModelMap model, HttpServletRequest request) {
	    List<String> infos=businessscopetagService.findscope();
		for (String info:infos) {
			Businessscopetag btag=new Businessscopetag();
			btag.setBusinessscope(info);
			businessscopetagService.save(btag);
		}
		return "/companymgr/scopetaglist";
	}


	@RequestMapping(value="/initcorps",method = RequestMethod.GET)
	public String initcorps(HttpSession session, ModelMap model, HttpServletRequest request) {
		List<CompanyEntity> corps=companyEntityService.findAll();
		for (CompanyEntity corp :corps) {
			companyEntityService.updateInfo(corp);
		}

		//初始化营业范围
		for (CompanyEntity corp :corps) {
			String naturebusiness=corp.getNaturebusiness();
			if(StringUtils.isNotBlank(naturebusiness)){
				String[] naturebusinesss=naturebusiness.split("；");
				if(naturebusinesss!=null&&naturebusinesss.length>0){
					for (String business:naturebusinesss) {
						Corpbusinessscope scorp=corpbusinessscopeService.findByScorp(corp.getCompanyid(),business);
						if(scorp==null){
							scorp=new Corpbusinessscope();
							scorp.setId(KeyUtils.getKey());
							scorp.setCompanyid(corp.getCompanyid());
							scorp.setEnterprisename(corp.getEnterprisename());
							scorp.setBusinessscope(business);
							corpbusinessscopeService.save(scorp);
						}
					}
				}


			}
		}

		return "redirect:/companymgr/companylist";
	}


}
