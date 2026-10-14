package com.ywjc.glsyjs.bigdata.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Companypatent;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.service.CompanypatentService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagService;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagrelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller("businessipinfopatentController")
@RequestMapping("/businessipinfopatent")
public class BusinessipinfopatentController extends BaseController {

	/*
	 *   专利信息
	 */

	@Resource(name = "companypatentServiceImpl")
	private CompanypatentService companypatentService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "accuratetagrelationServiceImpl")
	private AccuratetagrelationService accuratetagrelationService;

	@Resource(name = "accuratetagServiceImpl")
	private AccuratetagService accuratetagService;

	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String patentname,
			@RequestParam(required = false) String enterprisename,
			@RequestParam(required = false) String applicationnum,
			@RequestParam(required = false) Date apply_startdate,
			@RequestParam(required = false) Date apply_enddate,
		    HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/businessipinfopatent/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("patentname", patentname);
		model.addAttribute("enterprisename", enterprisename);
		model.addAttribute("applicationnum", applicationnum);
		model.addAttribute("apply_startdate", apply_startdate);
		model.addAttribute("apply_enddate", apply_enddate);
		patentname= StringUtils.isNotBlank(patentname)?"%"+patentname+"%":"%%";
		enterprisename= StringUtils.isNotBlank(enterprisename)?"%"+enterprisename+"%":"%%";
		applicationnum= StringUtils.isNotBlank(applicationnum)?"%"+applicationnum+"%":"%%";
		Page<Companypatent> page = companypatentService.queryPageinfo(patentname,enterprisename,applicationnum,apply_startdate,apply_enddate,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/bigdata/businessipinfopatent/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Companypatent info = new Companypatent();
		model.addAttribute("info", info);
		return "/bigdata/businessipinfopatent/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Companypatent info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/businessipinfopatent/save",session, request,Constantval.LOGIN_VISIT);
		//授权年份
		String pubannomentday=info.getPubannomentday();
		if(StringUtils.isNotBlank(pubannomentday)&&pubannomentday.length()>0){
			pubannomentday=pubannomentday.substring(0,4);
			info.setPatentyear(pubannomentday);
		}

		if(StringUtils.isBlank(info.getCompanypatentid())){
			CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getEnterprisename().trim());
			if(company==null){
				company=new CompanyEntity();
				company.setCompanyid(KeyUtils.getKey());
				company.setEnterprisename(info.getEnterprisename());
				companyEntityService.save(company);
			}
			info.setCompanyid(company.getCompanyid());
			info.setCredit_no(company.getUscreditcode());
			info.setCompanypatentid(KeyUtils.getKey());
			info.setState("1");
			companypatentService.save(info);
		}else{
			Companypatent infoold=companypatentService.find(info.getCompanypatentid());
			if(!infoold.getEnterprisename().trim().equals(info.getEnterprisename().trim())){
				CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getEnterprisename().trim());
				if(company==null){
					company=new CompanyEntity();
					company.setCompanyid(KeyUtils.getKey());
					company.setEnterprisename(info.getEnterprisename());
					companyEntityService.save(company);
				}

				infoold.setCompanyid(company.getCompanyid());
				infoold.setCredit_no(company.getUscreditcode());

			}
			infoold.setValue(info);
			companypatentService.update(infoold);


		}

		addCorptag(info);
		return Constantval.CONSTANT_SUCCESS;
	}


	private void addCorptag(Companypatent companypatent){
		String tagname="企业授权发明专利";
		if(companypatent.getPatenttype().contains("外观设计")){
			tagname="授权外观专利";
		}else if(companypatent.getPatenttype().contains("实用新型")){
			tagname="授权实用新型专利";
		}
		Accuratetag tag=accuratetagService.findTagByTagname(tagname);
		if(tag==null){
			tag = new Accuratetag();
			tag.setTagname(tagname);
			tag.setCreatetime(new Date());
			tag.setState("1");
			accuratetagService.save(tag);
		}
		tag=accuratetagService.findTagByTagname(tagname);
		Accuratetagrelation tagRelation=accuratetagrelationService.findByCorp(companypatent.getCompanyid(),tag.getTagid(),companypatent.getPatentyear());
		if(tagRelation==null){
			tagRelation=new Accuratetagrelation();
			tagRelation.setCid(companypatent.getCompanyid());
			tagRelation.setTagid(tag.getTagid());
			tagRelation.setCreatetime(new Date());
			tagRelation.setTagyear(companypatent.getPatentyear());
			tagRelation.setTagname(tagname);
			accuratetagrelationService.save(tagRelation);
		}else{
			if(!tagRelation.getTagyear().equals(companypatent.getPatentyear())){
				tagRelation.setTagyear(companypatent.getPatentyear());
				accuratetagrelationService.update(tagRelation);
			}
		}
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/businessinstitution/edit",session, request,Constantval.LOGIN_VISIT);
		Companypatent info = companypatentService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/businessipinfopatent/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/businessipinfopatent/updatestate",session, request,Constantval.LOGIN_VISIT);
		Companypatent info = companypatentService.find(id);
		info.setState(state);
		companypatentService.update(info);
		return "redirect:/businessipinfopatent/list";
	}



}
