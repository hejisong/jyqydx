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
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlyfileEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.*;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller("policydeclareController")
@RequestMapping("/policydeclare")
public class PolicydeclareController extends BaseController {


	@Resource(name = "policydeclareServiceImpl")
	private PolicydeclareService policydeclareService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;

	@Resource(name = "policyFileEntityServiceImpl")
	private PolicyFileEntityService policyfileEntityService;

	@Resource(name = "usercompanyEntityServiceImpl")
	private UsercompanyEntityService usercompanyEntityService;


	@Resource(name = "policydetailServiceImpl")
	private PolicydetailService policydetailService;


	@Resource(name = "policymaterialServiceImpl")
	private PolicymaterialService policymaterialService;

	@Resource(name = "policymateriallistServiceImpl")
	private PolicymateriallistService policymateriallistService;

	@Resource(name = "plyfileEntityServiceImpl")
	private PlyfileEntityService plyfileEntityService;

	@Resource(name = "plycomresultdetailEntityServiceImpl")
	private PlycomresultdetailEntityService plycomresultdetailEntityService;

	/**
	 * List
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			@RequestParam(required = false) String declarename,
			@RequestParam(required = false) Date startdate,
			@RequestParam(required = false) Date enddate,
			@RequestParam(required = false) String state,HttpSession session,
			ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("startdate", startdate);
		model.addAttribute("enddate", enddate);
		model.addAttribute("declarename", declarename);
		model.addAttribute("state", state);
		declarename= StringUtils.isNotBlank(declarename)?"%"+declarename+"%":"%%";
		Page<Policydeclare> page = policydeclareService.queryPageinfo(declarename,startdate,enddate,state,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/policydeclare/list";
	}



	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(HttpSession session,@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_TANCHU) Pageable pageable,
					  ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/new",session, request,Constantval.LOGIN_VISIT);
		Policydeclare policydeclare = new Policydeclare();
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user = principal.getUser();
		Organinfo organinfo=organinfoService.find(user.getOrganid());
		if(organinfo!=null){
			policydeclare.setReleaseorgname(organinfo.getOrgname());
			policydeclare.setReleaseorgid(organinfo.getId());
		}

		List<Organinfo> organinfos=organinfoService.findOrgs();
		model.addAttribute("organinfos", organinfos);
		model.addAttribute("policydeclare", policydeclare);
		Page<PolicyFileEntity> page=policyfileEntityService.findPolicyFilename("%%",pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/policydeclare/new";
	}


	/**
	 * add
	 */
	@RequestMapping(value = "/queryfile", method = RequestMethod.POST)
	@ResponseBody
	public Page<PolicyFileEntity> queryfile(HttpSession session,String filename,@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_TANCHU) Pageable pageable,
					  ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/queryfile",session, request,Constantval.LOGIN_VISIT);
		filename= StringUtils.isNotBlank(filename)?"%"+filename+"%":"%%";
		Page<PolicyFileEntity> page=policyfileEntityService.findPolicyFilename(filename,pageable);

		return page;
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Policydeclare info, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/save",session, request,Constantval.LOGIN_VISIT);
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user = principal.getUser();
		info.setFilecontent(StringEscapeUtils.unescapeHtml(info.getFilecontent()));
		if(null == info.getDeclareid()){
			info.setDeclareid(KeyUtils.getKey());
			info.setState("1");
			info.setReleaseperid(user.getId());
			info.setReleaseper(user.getUsername());
			info.setCreattime(new Date());
			policydeclareService.save(info);
		}else{
			Policydeclare infoold=policydeclareService.find(info.getDeclareid());
			infoold.setDeclarename(info.getDeclarename().trim());
			infoold.setReleaseorgid(info.getReleaseorgid());
			infoold.setReleaseorgname(info.getReleaseorgname());
			infoold.setReleasedate(info.getReleasedate());
			infoold.setPolicyfileid(info.getPolicyfileid());
			infoold.setPolicyfilename(info.getPolicyfilename());
			infoold.setIspublic(info.getIspublic());
			infoold.setDeclare_start_date(info.getDeclare_start_date());
			infoold.setDeclare_end_date(info.getDeclare_end_date());
			infoold.setFilecontent(info.getFilecontent());
			infoold.setApplyparkid(info.getApplyparkid());
			infoold.setApplyparkname(info.getApplyparkname());
			infoold.setUploadfileid(info.getUploadfileid());
			infoold.setUploadfilename(info.getUploadfilename());
			policydeclareService.update(infoold);
		}

		return Constantval.CONSTANT_SUCCESS;
	}

	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id,  HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/edit",session, request,Constantval.LOGIN_VISIT);
		Policydeclare policydeclare = policydeclareService.find(id);
		if (policydeclare == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		if(StringUtils.isNotBlank(policydeclare.getUploadfileid())){
			policydeclare.setUploadfileList(getUpLoadfile(policydeclare.getUploadfileid(),policydeclare.getUploadfilename()));
		}
		List<Organinfo> organinfos=organinfoService.findOrgs();
		model.addAttribute("organinfos", organinfos);
		model.addAttribute("policydeclare", policydeclare);
		return "/policydeclare/new";
	}


	/**
	 * eidt
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable String id,  HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/edit",session, request,Constantval.LOGIN_VISIT);
		Policydeclare policydeclare = policydeclareService.find(id);
		if (policydeclare == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		if(StringUtils.isNotBlank(policydeclare.getUploadfileid())){
			policydeclare.setUploadfileList(getUpLoadfile(policydeclare.getUploadfileid(),policydeclare.getUploadfilename()));
		}
		List<Organinfo> organinfos=organinfoService.findOrgs();
		model.addAttribute("organinfos", organinfos);
		model.addAttribute("policydeclare", policydeclare);
		return "/policydeclare/show";
	}


	private List<Uploadfile> getUpLoadfile(String uploadfileid, String uploadfilename){
		List<Uploadfile> files=new ArrayList<Uploadfile>();
		if(org.apache.commons.lang.StringUtils.isNotBlank(uploadfileid)&& org.apache.commons.lang.StringUtils.isNotBlank(uploadfilename)){
			String[] uploadfileids=uploadfileid.split(",");
			String[] uploadfilenames=uploadfilename.split(",");
			for (int i=0;i<uploadfileids.length;i++) {
				Uploadfile uploadfile=new Uploadfile();
				uploadfile.setId(uploadfileids[i]);
				uploadfile.setFilename(uploadfilenames[i]);
				files.add(uploadfile);
			}
		}
		return files;
	}


	private List<PolicyFileEntity> getPolicyfile(String policyfileid){
		List<PolicyFileEntity> files=new ArrayList<PolicyFileEntity>();
		if(StringUtils.isNotBlank(policyfileid)){
			String[] policyfileids=policyfileid.split(",");
			for (String fileid :policyfileids) {
				PolicyFileEntity policyfile=policyfileEntityService.find(fileid) ;
				if(policyfile!=null){
					files.add(policyfile);
				}
			}
		}
		return files;
	}


	@RequestMapping(value="/updatestate/{id}/{state}",method = RequestMethod.GET)
	public String updatestate(@PathVariable String id,@PathVariable String state, ModelMap model, HttpServletRequest request, HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/policydeclare/updatestate",session, request,Constantval.LOGIN_VISIT);
		Policydeclare policydeclare = policydeclareService.find(id);
		policydeclare.setState(state);
		policydeclareService.update(policydeclare);
		return "redirect:/policydeclare/list";
	}


	/**
	 * 申报材料管理
	 */
	@RequestMapping(value = "/materialmgr/{declareid}", method = RequestMethod.GET)
	public String materialmgr(@PathVariable String declareid, String plfidid, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/materialmgr",session, request,Constantval.LOGIN_VISIT);
		Policydeclare policydeclare = policydeclareService.find(declareid);
		if (policydeclare == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		String year= DateUtil.getCurrentYear();
		List<PolicyFileEntity> policyfiles=getPolicyfile(policydeclare.getPolicyfileid());
		//政策大类
		List<PolicyDalei> dls=new ArrayList<>();
		List<String> dals=new ArrayList<>();
		if(policyfiles!=null&&policyfiles.size()>0) {
			PolicyFileEntity policyfile=null;
			if(StringUtils.isBlank(plfidid)){
				policyfile=policyfiles.get(0);
			}else{
				for (PolicyFileEntity pfile:policyfiles) {
					if(pfile.getPlfidid().equals(plfidid)){
						policyfile=pfile;
						break;
					}
				}
			}

			model.addAttribute("policyfile", policyfile);
			List<PolicydetailEntity> details = policydetailService.findPolicyFiles(policyfile.getPlfidid());
			for (PolicydetailEntity detail: details) {
				if(StringUtils.isNotBlank(detail.getNplcategory())&&!dals.contains(detail.getNplcategory())){
					PolicyDalei policyDalei=new PolicyDalei();
					policyDalei.setPlfidid(policyfile.getPlfidid());
					policyDalei.setPolicyname(policyfile.getPolicyname());
					policyDalei.setNplcategory(detail.getNplcategory());
					dls.add(policyDalei);
					dals.add(detail.getNplcategory());
					//申报材料
					Policymaterial material=policymaterialService.findBypolicyfile(policyfile.getPlfidid(),detail.getNplcategory(),year);
					if(material!=null){
//						String materials=policymateriallistService.findmaterials(material);
						List<Policymateriallist> materiallists=policymateriallistService.findBymaterialid(material.getMaterialid());
						policyDalei.setMateriallists(materiallists);
					}
				}

			}

		}
		model.addAttribute("policyfiles", policyfiles);
		model.addAttribute("dls", dls);
		model.addAttribute("policydeclare", policydeclare);
		model.addAttribute("id", declareid);
		model.addAttribute("plfidid", plfidid);
		return "/policydeclare/materiallist";
	}


	/**
	 * 补充申报材料
	 */
	@RequestMapping(value = "/material/{declareid}/{plfidid}/{nplcategory}", method = RequestMethod.GET)
	public String material(@PathVariable String declareid,@PathVariable String plfidid,@PathVariable String nplcategory,  HttpSession session,
							  ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/material",session, request,Constantval.LOGIN_VISIT);
		PolicyFileEntity policyfile=policyfileEntityService.find(plfidid);
		model.addAttribute("policyname", policyfile.getPolicyname());
        String year= DateUtil.getCurrentYear();
		Policymaterial material=policymaterialService.findBypolicyfile(plfidid,nplcategory,year);
		if(material!=null){
			List<Policymateriallist> materiallists=policymateriallistService.findBymaterialid(material.getMaterialid());
			model.addAttribute("materiallists", materiallists);

		}else{
			material=new Policymaterial();
			material.setPolicyfileid(plfidid);
			material.setNplcategory(nplcategory);
			material.setPolicyyear(year);
		}
		model.addAttribute("material", material);
		model.addAttribute("declareid", declareid);
		return "/policydeclare/material";
	}


	/**
	 * 保存申报材料
	 */
	@RequestMapping(value="/materialsave",method = RequestMethod.POST)
	public String materialsave(@Valid Policymaterial info,String declareid, BindingResult bindingResult, HttpSession session,
					   ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/policydeclare/save",session, request,Constantval.LOGIN_VISIT);
		if (bindingResult.hasErrors()) {
			model.addAttribute("info", info);
			model.addAttribute("valid_error", bindingResult.getAllErrors());
			return "/policydeclare/new";
		}
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user = principal.getUser();
		Policymaterial material=policymateriallistService.saveinfo(info,user);
        //更新政策明细申报材料
		List<Policymateriallist> materiallists=policymateriallistService.findBymaterialid(material.getMaterialid());
		List<PolicydetailEntity> details=policydetailService.findPolicydetails(material.getPolicyfileid(),material.getNplcategory());
		if(!details.isEmpty()&&!materiallists.isEmpty()){
			plyfileEntityService.updateMaterial(materiallists,details);
		}
		return "redirect:/policydeclare/materialmgr/"+declareid;
	}

	/**
	 * webshow
	 */
	@RequestMapping(value = "/webshow/{id}", method = RequestMethod.GET)
	String show(@PathVariable String id, ModelMap model,HttpServletRequest request, HttpSession session) {
		Policydeclare policydeclare = policydeclareService.find(id);
		if (policydeclare == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		if(StringUtils.isNotBlank(policydeclare.getUploadfileid())){
			policydeclare.setUploadfileList(getUpLoadfile(policydeclare.getUploadfileid(),policydeclare.getUploadfilename()));
		}
		model.addAttribute("policydeclare", policydeclare);
		String content=policydeclare.getFilecontent();
		model.addAttribute("content", content);
		Date startdate=policydeclare.getDeclare_start_date();
		Date enddate=policydeclare.getDeclare_end_date();
		Date now=new Date();
		if(startdate.after(now)){
			model.addAttribute("mess", "申报工作还未开始，请等待！");
		}else if(enddate.before(now)){
			model.addAttribute("mess", "申报工作已结束，不可申报！");
		}
		model.addAttribute("infotype",Constantval.INDEX_NINE);
		return "/policydeclare/webshow";
	}


	/**
	 * webshow
	 */
	@RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
	String apply(@PathVariable String id,String plfidid,String dl,String key, ModelMap model,HttpServletRequest request, HttpSession session) {
		Policydeclare policydeclare = policydeclareService.find(id);
		if (policydeclare == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		List<PolicydetailEntity> alldetails=new ArrayList<>();
		List<PolicyFileEntity> policyfiles=getPolicyfile(policydeclare.getPolicyfileid());
		if(policyfiles!=null&&policyfiles.size()>0){
			List<String> dls=new ArrayList<>();
			for (PolicyFileEntity policyfile:policyfiles) {
				List<PolicydetailEntity> details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
				alldetails.addAll(details);
				for (PolicydetailEntity detail: details) {
					if(StringUtils.isNotBlank(detail.getNplcategory())&&!dls.contains(detail.getNplcategory())){
						dls.add(detail.getNplcategory());
					}
				}
			}
			model.addAttribute("dl", dls);
		}
		model.addAttribute("policyfiles", policyfiles);
		model.addAttribute("alldetails", alldetails);
		model.addAttribute("infotype",Constantval.INDEX_NINE);
		model.addAttribute("id", id);
		return "/policydeclare/applylist";
	}


	/**
	 * webshow
	 */
	@RequestMapping(value = "/applylist/{id}", method = RequestMethod.GET)
	String applylist(@PathVariable String id,String plfidid,String dl,String key,String companyid, ModelMap model,HttpServletRequest request, HttpSession session) {
		model.addAttribute("id", id);
		model.addAttribute("plfidid", plfidid);
		model.addAttribute("dl", dl);
		model.addAttribute("key", key);
		model.addAttribute("companyid", companyid);
		Policydeclare policydeclare = policydeclareService.find(id);
		if (policydeclare == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		List<Object> allinfos=new ArrayList<>();
		List<Object> alldetails=policydetailService.findInfopolicydeclare(policydeclare,plfidid, dl, key);
		if(StringUtils.isNotBlank(key)){
			for (Object o:alldetails) {
				Object[] oo=(Object[])o;
				oo[1]= KeyMatchUtil.getMatchedString(oo[1].toString(),key);
				oo[2]= KeyMatchUtil.getMatchedString(oo[2].toString(),key);
				oo[3]= KeyMatchUtil.getMatchedString(oo[3].toString(),key);
				oo[4]= KeyMatchUtil.getMatchedString(oo[4].toString(),key);
			}

		}
		List<PolicyFileEntity> policyfiles=getPolicyfile(policydeclare.getPolicyfileid());
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

		for (Object o:alldetails) {
			Object[] obj=new Object[7];
			Object[] oo=(Object[])o;


			obj[0]=oo[0];
			obj[1]=oo[1];
			obj[2]=oo[2];
			obj[3]=oo[3];
			obj[4]=oo[4];
			obj[5]=oo[5];
			if(StringUtils.isNotBlank(companyid)){
				//申报补充材料的政策
				Calendar date = Calendar.getInstance();
				String year = String.valueOf(date.get(Calendar.YEAR)-1);
				if(oo[1]!=null){
					String nplcategory=oo[1].toString();
					if(nplcategory.contains("专利")){
						obj[6]="2";
					}else{
						PlycomresultdetailEntity plycomresultdetail=plycomresultdetailEntityService.findPlycomresult(companyid,(String)oo[0],year);
						if(plycomresultdetail!=null){
							obj[6]="1";
						}
					}
				}


			}
			allinfos.add(obj);

		}
		model.addAttribute("dls", dls);
		model.addAttribute("policyfiles", policyfiles);
		model.addAttribute("alldetails", allinfos);
		model.addAttribute("infotype",Constantval.INDEX_NINE);
		model.addAttribute("id", id);
		return "/policydeclare/applylist";
	}









	/**
	 * add
	 */
	@RequestMapping(value = "/queryf", method = RequestMethod.POST)
	@ResponseBody
	public Object[] queryfile(HttpSession session,String filelevel, HttpServletRequest request) {
		Object[] data=new Object[2];
		List<PolicyFileEntity> policyfiles=policyfileEntityService.findByFilelevel(filelevel);

		//政策大类
		List<String> dl=new ArrayList<>();
		for (PolicyFileEntity policyfile :policyfiles) {
			List<PolicydetailEntity> details=policydetailService.findPolicyFiles(policyfile.getPlfidid());
			for (PolicydetailEntity detail: details) {
				if(StringUtils.isNotBlank(detail.getNplcategory())&& !dl.contains(detail.getNplcategory())){
					dl.add(detail.getNplcategory());
				}


			}
		}
		data[0]=policyfiles;
		data[1]=dl;
		return data;
	}



	/**
	 * add
	 */
	@RequestMapping(value = "/querydl", method = RequestMethod.POST)
	@ResponseBody
	public Object[] querydl(HttpSession session,String plfidid, HttpServletRequest request) {
		Object[] data=new Object[2];
		List<PolicydetailEntity> details=policydetailService.findPolicyFiles(plfidid);
			//政策大类
			List<String> dl=new ArrayList<>();
			List<String> xl=new ArrayList<>();
			for (PolicydetailEntity detail: details) {
				if(StringUtils.isNotBlank(detail.getNplcategory())&& !dl.contains(detail.getNplcategory())){
					dl.add(detail.getNplcategory());
				}
				if(StringUtils.isNotBlank(detail.getPlsubclassname())&&!xl.contains(detail.getPlsubclassname())){
					xl.add(detail.getPlsubclassname());
				}

			}
		data[0]=dl;
		data[1]=xl;
		return data;
	}

	@RequestMapping(value = "/queryxl", method = RequestMethod.POST)
	@ResponseBody
	public List<String> queryxl(HttpSession session,String dl,String plfidid, HttpServletRequest request) {
		Object[] data=new Object[2];
		List<PolicydetailEntity> details=policydetailService.findPolicyFiles(plfidid);
		//政策大类
		List<String> xl=new ArrayList<>();
		for (PolicydetailEntity detail: details) {
			if(StringUtils.isNotBlank(detail.getPlsubclassname())&&dl.equals(detail.getNplcategory())&&!xl.contains(detail.getPlsubclassname())){
				xl.add(detail.getPlsubclassname());
			}

		}
		return xl;
	}

}
