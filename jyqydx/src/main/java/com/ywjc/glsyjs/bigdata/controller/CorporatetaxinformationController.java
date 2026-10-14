package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Corporatetaxinformation;
import com.ywjc.glsyjs.bigdata.entity.Groupinformation;
import com.ywjc.glsyjs.bigdata.service.CorporatetaxinformationService;
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

import static com.ywjc.glsyjs.core.util.Constantval.PAGE_SIZE;


@Controller("corporatetaxinformationController")
@RequestMapping("/corptaxinfo")
public class CorporatetaxinformationController extends BaseController {



	@Resource(name = "corporatetaxinformationServiceImpl")
	private CorporatetaxinformationService corporatetaxinformationService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) String fullname,
			@RequestParam(required = false) String tax,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/corptaxinfo/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("companyname", companyname);
		model.addAttribute("fullname", fullname);
		model.addAttribute("tax", tax);
		companyname= StringUtils.isNotBlank(companyname)?"%"+companyname+"%":"%%";
		fullname= StringUtils.isNotBlank(fullname)?"%"+fullname+"%":"%%";
		tax= StringUtils.isNotBlank(tax)?"%"+tax+"%":"%%";
		Page<Corporatetaxinformation> page = corporatetaxinformationService.queryPageinfo(companyname,fullname,tax,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/bigdata/corptaxinfo/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Corporatetaxinformation info = new Corporatetaxinformation();
		model.addAttribute("info", info);
		return "/bigdata/corptaxinfo/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Corporatetaxinformation info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/corptaxinfo/save",session, request,Constantval.LOGIN_VISIT);
		CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
		if(company!=null){
			info.setCompanyid(company.getCompanyid());
			info.setUscreditcode(company.getUscreditcode());
		}
		if(null == info.getId()){
			info.setId(KeyUtils.getKey());
			info.setState("1");
			corporatetaxinformationService.save(info);
		}else{
			Corporatetaxinformation infoold=corporatetaxinformationService.find(info.getId());
			infoold.setValue(info);
			corporatetaxinformationService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/corptaxinfo/edit",session, request,Constantval.LOGIN_VISIT);
		Corporatetaxinformation info = corporatetaxinformationService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/corptaxinfo/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/corptaxinfo/updatestate",session, request,Constantval.LOGIN_VISIT);
		Corporatetaxinformation info = corporatetaxinformationService.find(id);
		info.setState(state);
		corporatetaxinformationService.update(info);
		return "redirect:/corptaxinfo/list";
	}


}
