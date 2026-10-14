package com.ywjc.glsyjs.Policyservices.controller;


import com.ywjc.glsyjs.Policyservices.entity.PolicyDalei;
import com.ywjc.glsyjs.Policyservices.entity.Policydeclare;
import com.ywjc.glsyjs.Policyservices.entity.Policymaterial;
import com.ywjc.glsyjs.Policyservices.entity.Policymateriallist;
import com.ywjc.glsyjs.Policyservices.service.PolicydeclareService;
import com.ywjc.glsyjs.Policyservices.service.PolicymaterialService;
import com.ywjc.glsyjs.Policyservices.service.PolicymateriallistService;
import com.ywjc.glsyjs.Policyservices.utils.KeyMatchUtil;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.entity.Taskdorecord;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import com.ywjc.glsyjs.sys.service.TaskdorecordService;
import com.ywjc.glsyjs.sys.util.DateUtil;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller("policytagController")
@RequestMapping("/policytag")
public class PolicytagController extends BaseController {

	/** Error View */
	protected static final String ERROR_VIEW = "/common/error";



	private static final int PAGE_SIZE = 9;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "policyFileEntityServiceImpl")
	private PolicyFileEntityService policyfileEntityService;

	@Resource(name = "policydetailServiceImpl")
	private PolicydetailService policydetailService;

	@Resource(name = "tagServiceImpl")
	private TagService tagService;

	@Resource(name = "taskdorecordServiceImpl")
	private TaskdorecordService taskdorecordService;

	/**
	 * List
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String plfidid,
			@RequestParam(required = false) String dl,
			@RequestParam(required = false) String filelevel,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/list",session, request,Constantval.LOGIN_VISIT);
		if(StringUtils.isNotBlank(dl)){
			dl=StringEscapeUtils.unescapeHtml(dl);
		}
		model.addAttribute("plfidid", plfidid);
		model.addAttribute("dl", dl);
		model.addAttribute("filelevel", filelevel);
		Page<Object> page=policydetailService.findInfos(plfidid,filelevel,dl, pageable);
		for (Object detail:page.getContent()) {
			Object[] obj=(Object[])detail;
			List<TagEntity> ptags=tagService.findByPid((String)obj[0]);
			obj[5]=ptags;
		}

		List<TagEntity> tags=tagService.findAll();
		model.addAttribute("tags", tags);

		List<PolicyFileEntity> policyfiles=policyfileEntityService.findByFilelevel(filelevel);
		List<String> dls=new ArrayList<>();
		if(StringUtils.isNotBlank(plfidid)){
			PolicyFileEntity policyfile=policyfileEntityService.find(plfidid);
			List<PolicydetailEntity> details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
			for (PolicydetailEntity detail: details) {
				if(StringUtils.isNotBlank(detail.getNplcategory())&&!dls.contains(detail.getNplcategory())){
					dls.add(detail.getNplcategory());
				}
			}
		}else{

			if(policyfiles!=null&&policyfiles.size()>0){
				for (PolicyFileEntity policyfile:policyfiles) {
					List<PolicydetailEntity> details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
					for (PolicydetailEntity detail: details) {
						if(StringUtils.isNotBlank(detail.getNplcategory())&&!dls.contains(detail.getNplcategory())){
							dls.add(detail.getNplcategory());
						}
					}
				}

			}


		}
		model.addAttribute("dls", dls);
		model.addAttribute("policyfiles", policyfiles);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/policytag/policytaglist";
	}


	/**
	 * List
	 */
	@RequestMapping(value="/corplist",method = RequestMethod.GET)
	public String corplist(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String enterprisename,
			@RequestParam(required = false) String uscreditcode,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/corplist",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("enterprisename", enterprisename);
		model.addAttribute("uscreditcode", uscreditcode);
		enterprisename=StringUtils.isNotBlank(enterprisename)?"%"+enterprisename.trim()+"%":"%%";
		uscreditcode=StringUtils.isNotBlank(uscreditcode)?"%"+uscreditcode.trim()+"%":"%%";
		Page<CompanyEntity> page=companyEntityService.queryCompanyPage(enterprisename,uscreditcode,pageable);
		for (CompanyEntity company:page.getContent()) {
			List<TagEntity> ptags=tagService.findByCompanyid(company.getCompanyid());
			company.setPtags(ptags);
		}

		List<TagEntity> tags=tagService.findAll();
		model.addAttribute("tags", tags);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/policytag/companylist";
	}



	/**
	 * save
	 */
	@RequestMapping(value="/savepolicytag",method = RequestMethod.POST)
	@ResponseBody
	public String savepolicytag(@Valid PolicyFileEntity info, ModelMap model, HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog( "/policytag/savepolicytag", session, request, Constantval.LOGIN_VISIT);
		Principal principal = (Principal) session.getAttribute("userinfo");
		List<PolicydetailEntity> details=info.getDetails();
		if(details!=null&&details.size()>0){
			for (PolicydetailEntity detail:details) {
				tagService.savetag(detail.getPtags(),detail.getPldtailid(),principal.getUser());
			}

		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * save
	 */
	@RequestMapping(value="/savecorptag",method = RequestMethod.POST)
	@ResponseBody
	public String savecorptag(@Valid PolicyFileEntity info, ModelMap model, HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog( "/policytag/savecorptag", session, request, Constantval.LOGIN_VISIT);
		Principal principal = (Principal) session.getAttribute("userinfo");
		List<CompanyEntity> companys=info.getCompanys();
		if(companys!=null&&companys.size()>0){
			for (CompanyEntity company:companys) {
				tagService.saveCorptag(company.getPtags(),company.getCompanyid(),principal.getUser());
			}

		}
		return Constantval.CONSTANT_SUCCESS;
	}


	/**
	 * List
	 */
	@RequestMapping(value="/taglist",method = RequestMethod.GET)
	public String taglist(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String tagname,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/taglist",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("tagname", tagname);
		tagname=StringUtils.isNotBlank(tagname)?"%"+tagname.trim()+"%":"%%";
		Page<TagEntity> page=tagService.findPageAndSearch(pageable,tagname);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/policytag/taglist";
	}


	/**
	 * save
	 */
	@RequestMapping(value="/savetag",method = RequestMethod.POST)
	@ResponseBody
	public String savetag(String  tagid,String tagname, ModelMap model, HttpServletRequest request, HttpSession session) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog( "/policytag/savetag", session, request, Constantval.LOGIN_VISIT);
		TagEntity tagEntity=tagService.findTagByTagname(tagname);
		if(tagEntity!=null){
			if(StringUtils.isNotBlank(tagid)&&!tagid.equals(tagEntity.getTagid())){
				return Constantval.CONSTANT_FAIL;
			}
		}else{
			if(StringUtils.isNotBlank(tagid)){
				TagEntity tagold=tagService.find(tagid);
				tagold.setTagname(tagname);
				tagService.update(tagold);
			}else{
				TagEntity tag=new TagEntity();
				tag.setTagname(tagname);
				tag.setCreateuser(principal.getUsername());
				tag.setCreatetime(new Date());
				tag.setState("1");
				tagService.save(tag);
			}
		}



		return Constantval.CONSTANT_SUCCESS;
	}


	@RequestMapping(value="/deletetag/{tagid}",method = RequestMethod.GET)
	public String deletetag(@PathVariable String tagid, HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/deletetag",session, request,Constantval.LOGIN_VISIT);
		tagService.deleteByTagid(tagid);
		return "redirect:/policytag/taglist";
	}

	@RequestMapping(value="/opentag/{tagid}",method = RequestMethod.GET)
	public String opentag(@PathVariable String tagid, HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/opentag",session, request,Constantval.LOGIN_VISIT);

		TagEntity tagold=tagService.find(tagid);
		if(tagold!=null){
			tagold.setState("1");
			tagService.update(tagold);
		}

		return "redirect:/policytag/taglist";
	}

	@RequestMapping(value="/stoptag/{tagid}",method = RequestMethod.GET)
	public String stoptag(@PathVariable String tagid, HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/stoptag",session, request,Constantval.LOGIN_VISIT);
		TagEntity tagold=tagService.find(tagid);
		if(tagold!=null){
			tagold.setState("0");
			tagService.update(tagold);
		}
		return "redirect:/policytag/taglist";
	}



	/**
	 * 查询日志
	 */
	@RequestMapping(value = "/querylog", method = RequestMethod.POST)
	@ResponseBody
	public Page<Taskdorecord> querylog(HttpSession session,String tasktype,@PageableDefault(page = 0, size = 10) Pageable pageable,
											ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/querylog",session, request,Constantval.LOGIN_VISIT);
		Page<Taskdorecord> page=taskdorecordService.findByTasktype(tasktype,pageable);
		return page;
	}



}
