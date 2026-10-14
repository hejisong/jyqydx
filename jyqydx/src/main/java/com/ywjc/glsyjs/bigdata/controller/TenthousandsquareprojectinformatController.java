package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Tenthousandsquareprojectinformat;
import com.ywjc.glsyjs.bigdata.service.TenthousandsquareprojectinformatService;
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



@Controller("tenthousandsquareprojectinformatController")
@RequestMapping("/squareproject")
public class TenthousandsquareprojectinformatController extends BaseController {



	@Resource(name = "tenthousandsquareprojectinformatServiceImpl")
	private TenthousandsquareprojectinformatService tenthousandsquareprojectinformatService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) String year,
			@RequestParam(required = false) String name_of_fair,

			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/squareproject/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("companyname", companyname);
		model.addAttribute("year", year);
		model.addAttribute("name_of_fair", name_of_fair);
		companyname= StringUtils.isNotBlank(companyname)?"%"+companyname+"%":"%%";
		year= StringUtils.isNotBlank(year)?"%"+year+"%":"%%";
		name_of_fair= StringUtils.isNotBlank(name_of_fair)?"%"+name_of_fair+"%":"%%";
		Page<Tenthousandsquareprojectinformat> page = tenthousandsquareprojectinformatService.queryPageinfo(companyname,year,name_of_fair,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer thisyear=Integer.valueOf(sdf.format(new Date()));
		List<String> years=new ArrayList<String>();
		for(int i=thisyear;i>=2018;i--){
			years.add(String.valueOf(i));
		}
		model.addAttribute("years", years);
		return "/bigdata/squareproject/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Tenthousandsquareprojectinformat info = new Tenthousandsquareprojectinformat();
		model.addAttribute("info", info);
		return "/bigdata/squareproject/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Tenthousandsquareprojectinformat info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/squareproject/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
		}
		if(null == info.getId()){
			info.setId(KeyUtils.getKey());
			info.setState("1");
			tenthousandsquareprojectinformatService.save(info);
		}else{
			Tenthousandsquareprojectinformat infoold=tenthousandsquareprojectinformatService.find(info.getId());
			infoold.setValue(info);
			tenthousandsquareprojectinformatService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/squareproject/edit",session, request,Constantval.LOGIN_VISIT);
		Tenthousandsquareprojectinformat info = tenthousandsquareprojectinformatService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/squareproject/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/squareproject/updatestate",session, request,Constantval.LOGIN_VISIT);
		Tenthousandsquareprojectinformat info = tenthousandsquareprojectinformatService.find(id);
		info.setState(state);
		tenthousandsquareprojectinformatService.update(info);
		return "redirect:/squareproject/list";
	}

	

}
