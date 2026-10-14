package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Businessipinfotrademark;
import com.ywjc.glsyjs.bigdata.service.BusinessipinfotrademarkService;
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

import java.util.Date;



@Controller("businessipinfotrademarkController")
@RequestMapping("/businesstrademark")
public class BusinessipinfotrademarkController extends BaseController {


	@Resource(name = "businessipinfotrademarkServiceImpl")
	private BusinessipinfotrademarkService businessipinfotrademarkService;


	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;




	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String trademarkname,
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) Date apply_startdate,
			@RequestParam(required = false) Date apply_enddate,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/businesstrademark/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("trademarkname", trademarkname);
		model.addAttribute("companyname", companyname);
		model.addAttribute("apply_startdate", apply_startdate);
		model.addAttribute("apply_enddate", apply_enddate);
		trademarkname= StringUtils.isNotBlank(trademarkname)?"%"+trademarkname+"%":"%%";
		companyname= StringUtils.isNotBlank(companyname)?"%"+companyname+"%":"%%";
		Page<Businessipinfotrademark> page = businessipinfotrademarkService.queryPageinfo(trademarkname,companyname,apply_startdate,apply_enddate,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/bigdata/businesstrademark/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Businessipinfotrademark info = new Businessipinfotrademark();
		model.addAttribute("info", info);
		return "/bigdata/businesstrademark/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Businessipinfotrademark info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/businesstrademark/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
		}
		if(null == info.getId()){
			info.setId(KeyUtils.getKey());
			info.setDeletestate("1");
			businessipinfotrademarkService.save(info);
		}else{
			Businessipinfotrademark infoold=businessipinfotrademarkService.find(info.getId());
			infoold.setValue(info);
			businessipinfotrademarkService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/businesstrademark/edit",session, request,Constantval.LOGIN_VISIT);
		Businessipinfotrademark info = businessipinfotrademarkService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/businesstrademark/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/businesstrademark/updatestate",session, request,Constantval.LOGIN_VISIT);
		Businessipinfotrademark info = businessipinfotrademarkService.find(id);
		info.setDeletestate(state);
		businessipinfotrademarkService.update(info);
		return "redirect:/businesstrademark/list";
	}

}
