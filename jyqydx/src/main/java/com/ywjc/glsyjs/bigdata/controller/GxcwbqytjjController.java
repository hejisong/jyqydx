package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.bigdata.entity.Gxcwbqytjj;
import com.ywjc.glsyjs.bigdata.service.GxcwbqytjjService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ywjc.glsyjs.core.util.Constantval.PAGE_SIZE;


@Controller("gxcwbqytjjController")
@RequestMapping("/gxcwbqytjj")
public class GxcwbqytjjController extends BaseController {



	@Resource(name = "gxcwbqytjjServiceImpl")
	private GxcwbqytjjService gxcwbqytjjService;


	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String year,
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) Double minsale_income,
			@RequestParam(required = false) Double maxsale_income,
			@RequestParam(required = false) Date apply_enddate,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/gxcwbqytjj/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("year", year);
		model.addAttribute("companyname", companyname);
		model.addAttribute("minsale_income", minsale_income);
		model.addAttribute("maxsale_income", maxsale_income);
		year= StringUtils.isNotBlank(year)?"%"+year+"%":"%%";
		companyname= StringUtils.isNotBlank(companyname)?"%"+companyname+"%":"%%";
		Page<Gxcwbqytjj> page = gxcwbqytjjService.queryPageinfo(companyname,year,minsale_income,maxsale_income,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer thisyear=Integer.valueOf(sdf.format(new Date()));
		List<String> years=new ArrayList<String>();
		for(int i=thisyear;i>=2018;i--){
			years.add(String.valueOf(i));
		}
		model.addAttribute("years", years);
		return "/bigdata/gxcwbqytjj/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Gxcwbqytjj info = new Gxcwbqytjj();
		model.addAttribute("info", info);
		return "/bigdata/gxcwbqytjj/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Gxcwbqytjj info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/gxcwbqytjj/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
		}
		if(StringUtils.isBlank(info.getId())){
			Gxcwbqytjj gxcwbqytjj=gxcwbqytjjService.findByYear(info.getCompanyid(),info.getYear());
			if(gxcwbqytjj!=null){
				return Constantval.CONSTANT_ERROR;
			}
			info.setId(KeyUtils.getKey());
			info.setState("1");
			gxcwbqytjjService.save(info);
		}else{
			Gxcwbqytjj gxcwbqytjj=gxcwbqytjjService.findByYear(info.getCompanyid(),info.getYear());
			if(gxcwbqytjj!=null&&!gxcwbqytjj.getId().equals(info.getId())){
				return Constantval.CONSTANT_ERROR;
			}
			Gxcwbqytjj infoold=gxcwbqytjjService.find(info.getId());
			infoold.setValue(info);
			gxcwbqytjjService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/gxcwbqytjj/edit",session, request,Constantval.LOGIN_VISIT);
		Gxcwbqytjj info = gxcwbqytjjService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/gxcwbqytjj/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/gxcwbqytjj/updatestate",session, request,Constantval.LOGIN_VISIT);
		Gxcwbqytjj info = gxcwbqytjjService.find(id);
		info.setState(state);
		gxcwbqytjjService.update(info);
		return "redirect:/gxcwbqytjj/list";
	}

}
