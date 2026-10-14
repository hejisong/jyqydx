package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Groupinformation;
import com.ywjc.glsyjs.bigdata.service.GroupinformationService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;




@Controller("groupinformationController")
@RequestMapping("/groupinformation")
public class GroupinformationController extends BaseController {



	@Resource(name = "groupinformationServiceImpl")
	private GroupinformationService groupinformationService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;



	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String groupname,
			@RequestParam(required = false) String pcompanyname,
			@RequestParam(required = false) String registrationnumber,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/groupinformation/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("groupname", groupname);
		model.addAttribute("pcompanyname", pcompanyname);
		model.addAttribute("registrationnumber", registrationnumber);
		groupname= StringUtils.isNotBlank(groupname)?"%"+groupname+"%":"%%";
		pcompanyname= StringUtils.isNotBlank(pcompanyname)?"%"+pcompanyname+"%":"%%";
		registrationnumber= StringUtils.isNotBlank(registrationnumber)?"%"+registrationnumber+"%":"%%";
		Page<Groupinformation> page = groupinformationService.queryPageinfo(groupname,pcompanyname,registrationnumber,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/bigdata/groupinformation/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Groupinformation info = new Groupinformation();
		model.addAttribute("info", info);
		return "/bigdata/groupinformation/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Groupinformation info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/groupinformation/save",session, request,Constantval.LOGIN_VISIT);
		if(null == info.getGroupid()){
			info.setGroupid(KeyUtils.getKey());
			info.setState("1");
			groupinformationService.save(info);
		}else{
			Groupinformation infoold=groupinformationService.find(info.getGroupid());
			infoold.setValue(info);
			groupinformationService.update(infoold);
		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/groupinformation/edit",session, request,Constantval.LOGIN_VISIT);
		Groupinformation info = groupinformationService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/groupinformation/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/groupinformation/updatestate",session, request,Constantval.LOGIN_VISIT);
		Groupinformation info = groupinformationService.find(id);
		info.setState(state);
		groupinformationService.update(info);
		return "redirect:/groupinformation/list";
	}

}
