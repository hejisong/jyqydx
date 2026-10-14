package com.ywjc.glsyjs.bigdata.controller;


import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.Iihightechenterprises;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.service.IihightechenterprisesService;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Controller("iihightechenterprisesController")
@RequestMapping("/iihighprises")
public class IihightechenterprisesController extends BaseController {

   //高新技术企业认定

	@Resource(name = "iihightechenterprisesServiceImpl")
	private IihightechenterprisesService iihightechenterprisesService;

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
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) String certificateno,
			@RequestParam(required = false) String recognitionyear,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/iihighprises/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("companyname", companyname);
		model.addAttribute("certificateno", certificateno);
		model.addAttribute("recognitionyear", recognitionyear);
		companyname= StringUtils.isNotBlank(companyname)?"%"+companyname+"%":"%%";
		certificateno= StringUtils.isNotBlank(certificateno)?"%"+certificateno+"%":"%%";
		recognitionyear= StringUtils.isNotBlank(recognitionyear)?"%"+recognitionyear+"%":"%%";
		Page<Iihightechenterprises> page = iihightechenterprisesService.queryPageinfo(companyname,certificateno,recognitionyear,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer thisyear=Integer.valueOf(sdf.format(new Date()));
		List<String> years=new ArrayList<String>();
		for(int i=thisyear;i>=2018;i--){
			years.add(String.valueOf(i));
		}
		model.addAttribute("years", years);
		return "/bigdata/iihighprises/list";
	}





	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model) {
		Iihightechenterprises info = new Iihightechenterprises();
		model.addAttribute("info", info);
		return "/bigdata/iihighprises/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Iihightechenterprises info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/iihighprises/save",session, request,Constantval.LOGIN_VISIT);

		if(StringUtils.isBlank(info.getIiheeid())){
			CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getEnterprisename().trim());
			if(company==null){
				company=new CompanyEntity();
				company.setCompanyid(KeyUtils.getKey());
				company.setEnterprisename(info.getEnterprisename());
				companyEntityService.save(company);
			}
			info.setCompanyid(company.getCompanyid());
			info.setEnterprisenum(company.getUscreditcode());
			info.setIiheeid(KeyUtils.getKey());
			info.setState("1");
			iihightechenterprisesService.save(info);
		}else{
			Iihightechenterprises infoold=iihightechenterprisesService.find(info.getIiheeid());
			if(!infoold.getEnterprisename().trim().equals(info.getEnterprisename().trim())){
				CompanyEntity company=companyEntityService.queryCompanyBycorpname(info.getEnterprisename().trim());
				if(company==null){
					company=new CompanyEntity();
					company.setCompanyid(KeyUtils.getKey());
					company.setEnterprisename(info.getEnterprisename());
					companyEntityService.save(company);
				}

				infoold.setCompanyid(company.getCompanyid());
			}
			infoold.setValue(info);
			iihightechenterprisesService.update(infoold);
		}

		//自动给企业打标签
		addCorptag(info);
		return Constantval.CONSTANT_SUCCESS;
	}

	private void addCorptag(Iihightechenterprises icorp){
		Accuratetag tag=accuratetagService.findTagByTagname("高新技术企业");
		Accuratetagrelation tagRelation=accuratetagrelationService.findByCorp(icorp.getCompanyid(),tag.getTagid(),icorp.getRecognitionyear());
		if(tagRelation==null){
			tagRelation=new Accuratetagrelation();
			tagRelation.setCid(icorp.getCompanyid());
			tagRelation.setTagid(tag.getTagid());
			tagRelation.setCreatetime(new Date());
			tagRelation.setTagyear(icorp.getRecognitionyear());
			tagRelation.setTagname("高新技术企业");
			accuratetagrelationService.save(tagRelation);
		}else{
			if(!tagRelation.getTagyear().equals(icorp.getRecognitionyear())){
				tagRelation.setTagyear(icorp.getRecognitionyear());
				accuratetagrelationService.update(tagRelation);
			}
		}
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/iihighprises/edit",session, request,Constantval.LOGIN_VISIT);
		Iihightechenterprises info = iihightechenterprisesService.find(id);
		if (info == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("info", info);
		return "/bigdata/iihighprises/new";
	}

	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/iihighprises/updatestate",session, request,Constantval.LOGIN_VISIT);
		Iihightechenterprises info = iihightechenterprisesService.find(id);
		info.setState(state);
		iihightechenterprisesService.update(info);
		return "redirect:/iihighprises/list";
	}


}
