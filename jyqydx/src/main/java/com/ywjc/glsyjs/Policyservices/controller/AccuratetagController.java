package com.ywjc.glsyjs.Policyservices.controller;


import com.ywjc.glsyjs.Policyservices.entity.Corptag;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.bigdata.entity.Businessipinfotrademark;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetagrelation;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagService;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagrelationService;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller("accuratetagController")
@RequestMapping("/accuratetag")
public class AccuratetagController extends BaseController {

	private static Logger log = Logger.getLogger(PolicyFileController.class);

	@Resource(name = "accuratetagServiceImpl")
	private AccuratetagService accuratetagService;
	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;
	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "accuratetagrelationServiceImpl")
	private AccuratetagrelationService accuratetagrelationService;

	@Resource(name = "policyFileEntityServiceImpl")
	private PolicyFileEntityService policyfileEntityService;

	@Resource(name = "policydetailServiceImpl")
	private PolicydetailService policydetailService;

	@Resource(name = "uploadfileServiceImpl")
	private UploadfileService uploadfileService;
	/**
	 * List
	 */
	/**
	 * List
	 */
	@RequestMapping(value="/taglist",method = RequestMethod.GET)
	public String taglist(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String tagname,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/accuratetag/taglist",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("tagname", tagname);
		tagname= StringUtils.isNotBlank(tagname)?"%"+tagname.trim()+"%":"%%";
		Page<Accuratetag> page=accuratetagService.findPageAndSearch(tagname,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/accuratetag/taglist";
	}


	/**
	 * save
	 */
	@RequestMapping(value="/savetag",method = RequestMethod.POST)
	@ResponseBody
	public String savetag(String  tagid,String tagname, ModelMap model, HttpServletRequest request, HttpSession session) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/accuratetag/savetag", session, request, Constantval.LOGIN_VISIT);
		Accuratetag accuratetag = accuratetagService.findTagByTagname(tagname);
		if (accuratetag != null) {
			if (StringUtils.isNotBlank(tagid) && !tagid.equals(accuratetag.getTagid())) {
				return Constantval.CONSTANT_FAIL;
			}
		} else {
			if (StringUtils.isNotBlank(tagid)) {
				Accuratetag tagold = accuratetagService.find(tagid);
				tagold.setTagname(tagname);
				accuratetagService.update(tagold);
			} else {
				Accuratetag tag = new Accuratetag();
				tag.setTagname(tagname);
				tag.setCreateuser(principal.getUsername());
				tag.setCreatetime(new Date());
				tag.setState("1");
				accuratetagService.save(tag);
			}
		}
		return Constantval.CONSTANT_SUCCESS;
	}



	@RequestMapping(value="/deletetag/{tagid}",method = RequestMethod.GET)
	public String deletetag(@PathVariable String tagid, HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/accuratetag/deletetag",session, request,Constantval.LOGIN_VISIT);
		accuratetagService.deleteByTagid(tagid);
		return "redirect:/policytag/taglist";
	}

	@RequestMapping(value="/opentag/{tagid}",method = RequestMethod.GET)
	public String opentag(@PathVariable String tagid, HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/accuratetag/opentag",session, request,Constantval.LOGIN_VISIT);

		Accuratetag tagold=accuratetagService.find(tagid);
		if(tagold!=null){
			tagold.setState("1");
			accuratetagService.update(tagold);
		}

		return "redirect:/accuratetag/taglist";
	}

	@RequestMapping(value="/stoptag/{tagid}",method = RequestMethod.GET)
	public String stoptag(@PathVariable String tagid, HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/stoptag",session, request,Constantval.LOGIN_VISIT);
		Accuratetag tagold=accuratetagService.find(tagid);
		if(tagold!=null){
			tagold.setState("0");
			accuratetagService.update(tagold);
		}
		return "redirect:/accuratetag/taglist";
	}



	/**
	 * List
	 */
	@RequestMapping(value="/corplist",method = RequestMethod.GET)
	public String corplist(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String companyname,
			@RequestParam(required = false) String tagyear,
			@RequestParam(required = false) String tagname,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/accuratetag/corplist",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("companyname", companyname);
		model.addAttribute("tagyear", tagyear);
		model.addAttribute("tagname", tagname);
//		companyname=StringUtils.isNotBlank(companyname)?"%"+companyname.trim()+"%":"%%";
//		tagyear=StringUtils.isNotBlank(tagyear)?"%"+tagyear.trim()+"%":"%%";
//		tagname=StringUtils.isNotBlank(tagname)?"%"+tagname.trim()+"%":"%%";


		Page<Object> page=accuratetagrelationService.findPage(companyname,tagyear,tagname,pageable);
		List<String>corps=new ArrayList<>();
		for (Object obj:page.getContent()) {
			Object[] o=( Object[])obj;
			String corpname=(String)o[1];
			if (StringUtils.isNotBlank(corpname)) {
				if(!corps.contains(corpname)){
					corps.add(corpname);
				}
			}
		}
		List<Corptag> infos=new ArrayList<>();

		for (String corp:corps) {
			Corptag corptag=new Corptag();
			corptag.setCompanyname(corp);
			List<Object> tags=new ArrayList<>();
			for (Object obj:page.getContent()) {
				Object[] o=( Object[])obj;
				corptag.setUscreditcode((String)o[2]);
				String corpname=(String)o[1];
				if(corp.equals(corpname)){
					tags.add(obj);
				}

			}
			corptag.setTags(tags);
			infos.add(corptag);
		}
		model.addAttribute("infos", infos);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer year1=Integer.valueOf(sdf.format(d));
		List<Integer> years=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			years.add(year1);
			year1--;
		}
		model.addAttribute("years", years);

		List<Accuratetag> accuratetags=accuratetagService.findAll();
		model.addAttribute("accuratetags", accuratetags);
		return "/accuratetag/companylist";
	}


	@RequestMapping(value="/newcorptag",method = RequestMethod.GET)
	public String newcorptag( HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/newcorptag",session, request,Constantval.LOGIN_VISIT);
		Accuratetagrelation accuratetag=new Accuratetagrelation();
		model.addAttribute("accuratetag", accuratetag);
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		Integer year1=Integer.valueOf(sdf.format(d));
		List<Integer> years=new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			years.add(year1);
			year1--;
		}
		model.addAttribute("years", years);

		List<Accuratetag> accuratetags=accuratetagService.findAll();
		Accuratetag atag=accuratetags.get(0);
		accuratetag.setTagid(atag.getTagid());
		accuratetag.setTagname(atag.getTagname());
		model.addAttribute("accuratetags", accuratetags);
		return "/accuratetag/newcorptag";
	}
	@RequestMapping(value = "/savenewcorptag",method = RequestMethod.POST)
	@ResponseBody
	public String savenewcorptag(@Valid Accuratetagrelation entity, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
//		loginlogService.saveloginlog("/businesstrademark/save",session, request,Constantval.LOGIN_VISIT);
		String userid = (String) session.getAttribute("userid");
		if(StringUtils.isNotBlank(entity.getCompanyname())&&StringUtils.isNotBlank(entity.getUscreditcode())){
			CompanyEntity company=companyEntityService.queryCompany(entity.getCompanyname().trim(),entity.getUscreditcode().trim());
			if(company!=null){
				entity.setCid(company.getCompanyid());
			}else{
				CompanyEntity companynew =new CompanyEntity();
				companynew.setCompanyid(KeyUtils.getKey());
				companynew.setEnterprisename(entity.getCompanyname());
				companynew.setUscreditcode(entity.getUscreditcode());
				companyEntityService.save(companynew);
				entity.setCid(companynew.getCompanyid());
			}
		}
		if(StringUtils.isBlank(entity.getId())){
//			entity.setId(KeyUtils.getKey());
			entity.setCreatetime(new Date());
			if(StringUtils.isNotBlank(userid)){
				entity.setCreateuser(userid);
			}
			accuratetagrelationService.save(entity);
		}else{
			Accuratetagrelation oldentity=accuratetagrelationService.find(entity.getId());
			if(oldentity!=null){
				oldentity.setCid(entity.getCid());
				oldentity.setTagid(entity.getTagid());
				oldentity.setTagname(entity.getTagname());
				oldentity.setTagyear(entity.getTagyear());
			}
			oldentity.setUpdatetime(new Date());
			if(StringUtils.isNotBlank(userid)){
				oldentity.setUpdateuser(userid);
			}
			accuratetagrelationService.update(entity);
		}
		return Constantval.CONSTANT_SUCCESS;
	}

	/**
	 * List
	 */
	@RequestMapping(value="/policylist",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String plfidid,
			@RequestParam(required = false) String dl,
			@RequestParam(required = false) String flevel,
			HttpSession session, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policytag/list",session, request,Constantval.LOGIN_VISIT);
		if(StringUtils.isNotBlank(dl)){
			dl= StringEscapeUtils.unescapeHtml(dl);
		}
		model.addAttribute("plfidid", plfidid);
		model.addAttribute("dl", dl);
		model.addAttribute("flevel", flevel);
		Page<Object> page=policydetailService.findInfos(plfidid,flevel,dl, pageable);
		for (Object detail:page.getContent()) {
			Object[] obj=(Object[])detail;
			List<Accuratetag> ptags=accuratetagService.findByPid((String)obj[0]);
			obj[5]=ptags;
		}

		List<Accuratetag> tags=accuratetagService.findAll();
		model.addAttribute("tags", tags);

		List<PolicyFileEntity> policyfiles=policyfileEntityService.findByFilelevel(flevel);
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
		return "/accuratetag/policytaglist";
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
				accuratetagService.savetag(detail.getAtags(),detail.getPldtailid(),principal.getUser());
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
				accuratetagService.saveCorptag(company.getAtags(),company.getCompanyid(),principal.getUser());
			}

		}
		return Constantval.CONSTANT_SUCCESS;
	}



	/**
	 * 企业精准化标签标签批量导入
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	@ResponseBody
	public Object[] importResult(String uploadfileid, HttpSession session) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user=principal.getUser();
		Object[] returnmessage = new Object[2];
		ValidateBinding bind = null;
		int num = 0;
		Uploadfile file = uploadfileService.find(uploadfileid);
		if (file == null) {
			returnmessage[0] = Constantval.CONSTANT_FAIL;
			return returnmessage;
		}
		String filename = ConfigUtil.getConfigVal("file_save_path") + "\\doc\\upload_" + file.getId() + "."
				+ file.getFileext();
		// 创建对Excel工作簿文件的引用
		Workbook excelwork = null;
		FileInputStream fileio = null;
		if (file.getFileext().toLowerCase().equals("xls")) {
			try {
				fileio = new FileInputStream(filename);
				excelwork = new HSSFWorkbook(fileio);

				Object[] r = accuratetagService.importInfoExcel( excelwork,file, user);
				bind = (ValidateBinding) r[1];
				num = (int) r[0];
				if (bind.hasErrors()) {
					returnmessage[0] = Constantval.CONSTANT_FAIL;
					returnmessage[1] = bind.getErrorHtml();
					String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
					Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
					Matcher m_html = p_html.matcher(bind.getErrorHtml());
					returnmessage[1] = m_html.replaceAll(""); // 过滤html标签
					return returnmessage;
				} else {
					returnmessage[0] = Constantval.CONSTANT_SUCCESS;
					returnmessage[1] = Integer.toString(num);
					return returnmessage;
				}

			} catch (FileNotFoundException e) {
				log.error(e.getMessage());
				log.info(e);
				returnmessage[0] = Constantval.CONSTANT_FAIL;
				return returnmessage;
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
				returnmessage[0] = Constantval.CONSTANT_FAIL;
				return returnmessage;
			} finally {
				try {
					if (fileio != null) {
						fileio.close();
					}
				} catch (IOException e) {
					log.error(e.getMessage());
					log.info(e);
				}
			}
		} else if (file.getFileext().toLowerCase().equals("xlsx")) {
			try {
				fileio = new FileInputStream(filename);
				excelwork = new XSSFWorkbook(fileio);
				Object[] r = accuratetagService.importInfoExcel( excelwork,file, user);
				bind = (ValidateBinding) r[1];
				num = (int) r[0];
				if (bind.hasErrors()) {
					returnmessage[0] = Constantval.CONSTANT_FAIL;
					returnmessage[1] = bind.getErrorHtml();
					String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
					Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
					Matcher m_html = p_html.matcher(bind.getErrorHtml());
					returnmessage[1] = m_html.replaceAll(""); // 过滤html标签
					return returnmessage;
				} else {
					returnmessage[0] = Constantval.CONSTANT_SUCCESS;
					returnmessage[1] = Integer.toString(num);
					return returnmessage;
				}
			} catch (FileNotFoundException e) {
				log.error(e.getMessage());
				log.info(e);
				returnmessage[0] = Constantval.CONSTANT_FAIL;
				return returnmessage;
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
				returnmessage[0] = Constantval.CONSTANT_FAIL;
				return returnmessage;
			} finally {
				try {
					if (fileio != null) {
						fileio.close();
					}
				} catch (IOException e) {
					log.error(e.getMessage());
					log.info(e);
				}
			}
		} else {
			returnmessage[0] = Constantval.CONSTANT_FAIL;
			return returnmessage;
		}
	}





}
