package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
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



@Controller("companyBaseinfoController")
@RequestMapping("/businessinstitution")
public class CompanyBaseinfoController extends BaseController {
    /*
     *   工商信息
     */

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) String uscreditcode,
		    HttpSession session,
			ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/businessinstitution/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("companyname", companyname);
		model.addAttribute("uscreditcode", uscreditcode);
		Page<CompanyEntity> page = companyEntityService.queryCompanyPage(companyname,uscreditcode,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/bigdata/businessinstitution/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		CompanyEntity info = new CompanyEntity();
		model.addAttribute("info", info);
		return "/bigdata/businessinstitution/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid CompanyEntity info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/save",session, request,Constantval.LOGIN_VISIT);
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user = principal.getUser();
		if(null == info.getCompanyid()){
			info.setCompanyid(KeyUtils.getKey());
			info.setState("1");
			companyEntityService.save(info);
		}else{
			CompanyEntity infoold=companyEntityService.find(info.getCompanyid());
			infoold.setValue(info);
			companyEntityService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/businessinstitution/edit",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity info = companyEntityService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/businessinstitution/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/businessinstitution/updatestate",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity info = companyEntityService.find(id);
		info.setState(state);
		companyEntityService.update(info);
		return "redirect:/businessinstitution/list";
	}



}
