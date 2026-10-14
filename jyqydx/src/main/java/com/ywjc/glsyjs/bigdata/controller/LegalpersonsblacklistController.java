package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Legalpersonsblacklist;
import com.ywjc.glsyjs.bigdata.service.LegalpersonsblacklistService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Controller("legalpersonsblacklistController")
@RequestMapping("/legalpersonsblack")
public class LegalpersonsblacklistController extends BaseController {



	@Resource(name = "legalpersonsblacklistServiceImpl")
	private LegalpersonsblacklistService legalpersonsblacklistService;



	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String company_name,
			@RequestParam(required = false) String legalpersons,
			@RequestParam(required = false) String legalcardid,

			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/legalpersonsblack/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("company_name", company_name);
		model.addAttribute("legalpersons", legalpersons);
		model.addAttribute("legalcardid", legalcardid);
		company_name= StringUtils.isNotBlank(company_name)?"%"+company_name+"%":"%%";
		legalpersons= StringUtils.isNotBlank(legalpersons)?"%"+legalpersons+"%":"%%";
		legalcardid= StringUtils.isNotBlank(legalcardid)?"%"+legalcardid+"%":"%%";
		Page<Legalpersonsblacklist> page = legalpersonsblacklistService.queryPageinfo(company_name,legalpersons,legalcardid,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer thisyear=Integer.valueOf(sdf.format(new Date()));
		List<String> years=new ArrayList<String>();
		for(int i=thisyear;i>=2018;i--){
			years.add(String.valueOf(i));
		}
		model.addAttribute("years", years);
		return "/bigdata/legalpersonsblack/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Legalpersonsblacklist info = new Legalpersonsblacklist();
		model.addAttribute("info", info);
		return "/bigdata/legalpersonsblack/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Legalpersonsblacklist info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/legalpersonsblack/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompany_name().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
		}
		if(null == info.getId()){
			info.setId(KeyUtils.getKey());
			info.setState("1");
			legalpersonsblacklistService.save(info);
		}else{
			Legalpersonsblacklist infoold=legalpersonsblacklistService.find(info.getId());
			infoold.setValue(info);
			legalpersonsblacklistService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/legalpersonsblack/edit",session, request,Constantval.LOGIN_VISIT);
		Legalpersonsblacklist info = legalpersonsblacklistService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/legalpersonsblack/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/legalpersonsblack/updatestate",session, request,Constantval.LOGIN_VISIT);
		Legalpersonsblacklist info = legalpersonsblacklistService.find(id);
		info.setState(state);
		legalpersonsblacklistService.update(info);
		return "redirect:/legalpersonsblack/list";
	}
	
	



}
