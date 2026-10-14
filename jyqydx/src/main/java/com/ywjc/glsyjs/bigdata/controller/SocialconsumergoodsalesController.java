package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Addeddomesticcompany;
import com.ywjc.glsyjs.bigdata.entity.Socialconsumergoodsales;
import com.ywjc.glsyjs.bigdata.service.SocialconsumergoodsalesService;
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



@Controller("socialconsumergoodsalesController")
@RequestMapping("/socialgoodsales")
public class SocialconsumergoodsalesController extends BaseController {


	@Resource(name = "socialconsumergoodsalesServiceImpl")
	private SocialconsumergoodsalesService socialconsumergoodsalesService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String company_name,
			@RequestParam(required = false) String year,
			@RequestParam(required = false) Double min_sales,
			@RequestParam(required = false) Double max_sales,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/socialgoodsales/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("company_name", company_name);
		model.addAttribute("year", year);
		model.addAttribute("min_sales", min_sales);
		model.addAttribute("max_sales", max_sales);
		company_name= StringUtils.isNotBlank(company_name)?"%"+company_name+"%":"%%";
		year= StringUtils.isNotBlank(year)?"%"+year+"%":"%%";
		Page<Socialconsumergoodsales> page = socialconsumergoodsalesService.queryPageinfo(company_name,year,min_sales,max_sales,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer thisyear=Integer.valueOf(sdf.format(new Date()));
		List<String> years=new ArrayList<String>();
		for(int i=thisyear;i>=2018;i--){
			years.add(String.valueOf(i));
		}
		model.addAttribute("years", years);
		return "/bigdata/socialgoodsales/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Socialconsumergoodsales info = new Socialconsumergoodsales();
		model.addAttribute("info", info);
		return "/bigdata/socialgoodsales/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Socialconsumergoodsales info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/socialgoodsales/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
		}
		if(null == info.getId()){
			info.setId(KeyUtils.getKey());
			info.setState("1");
			socialconsumergoodsalesService.save(info);
		}else{
			Socialconsumergoodsales infoold=socialconsumergoodsalesService.find(info.getId());
			infoold.setValue(info);
			socialconsumergoodsalesService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/socialgoodsales/edit",session, request,Constantval.LOGIN_VISIT);
		Socialconsumergoodsales info = socialconsumergoodsalesService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/socialgoodsales/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/socialgoodsales/updatestate",session, request,Constantval.LOGIN_VISIT);
		Socialconsumergoodsales info = socialconsumergoodsalesService.find(id);
		info.setState(state);
		socialconsumergoodsalesService.update(info);
		return "redirect:/socialgoodsales/list";
	}


}
