package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Addeddomesticcompany;
import com.ywjc.glsyjs.bigdata.service.AddeddomesticcompanyService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
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



@Controller("addeddomesticcompanyController")
@RequestMapping("/addedcompany")
public class AddeddomesticcompanyController extends BaseController {



	@Resource(name = "addeddomesticcompanyServiceImpl")
	private AddeddomesticcompanyService addeddomesticcompanyService;


	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String company_name,
			@RequestParam(required = false) String stock_code,
			@RequestParam(required = false) String region,
			@RequestParam(required = false) Date startdate,
			@RequestParam(required = false) Date enddate,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/addedcompany/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("company_name", company_name);
		model.addAttribute("stock_code", stock_code);
		model.addAttribute("region", region);
		model.addAttribute("startdate", startdate);
		model.addAttribute("enddate", enddate);
		company_name= StringUtils.isNotBlank(company_name)?"%"+company_name+"%":"%%";
		stock_code= StringUtils.isNotBlank(stock_code)?"%"+stock_code+"%":"%%";
		region= StringUtils.isNotBlank(region)?"%"+region+"%":"%%";
		Page<Addeddomesticcompany> page = addeddomesticcompanyService.queryPageinfo(company_name,stock_code,region,startdate,enddate,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/bigdata/addedcompany/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Addeddomesticcompany info = new Addeddomesticcompany();
		model.addAttribute("info", info);
		return "/bigdata/addedcompany/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Addeddomesticcompany info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/addedcompany/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompany_name().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
		}
		if(null == info.getId()){
			info.setId(KeyUtils.getKey());
			info.setState("1");
			addeddomesticcompanyService.save(info);
		}else{
			Addeddomesticcompany infoold=addeddomesticcompanyService.find(info.getId());
			infoold.setValue(info);
			addeddomesticcompanyService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/addedcompany/edit",session, request,Constantval.LOGIN_VISIT);
		Addeddomesticcompany info = addeddomesticcompanyService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/addedcompany/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/addedcompany/updatestate",session, request,Constantval.LOGIN_VISIT);
		Addeddomesticcompany info = addeddomesticcompanyService.find(id);
		info.setState(state);
		addeddomesticcompanyService.update(info);
		return "redirect:/addedcompany/list";
	}


	

}
