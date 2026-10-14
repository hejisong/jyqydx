package com.ywjc.glsyjs.Policyservices.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.Policyservices.entity.Intellectmatch;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.plycomresult.entity.PlycomresultdetailEntity;
import com.ywjc.glsyjs.interfaces.plycomresult.service.PlycomresultdetailEntityService;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.interfaces.policyfile.entity.Policyfile;
import com.ywjc.glsyjs.interfaces.policyfile.service.PolicydetailService;
import com.ywjc.glsyjs.interfaces.tag.entity.Accuratetag;
import com.ywjc.glsyjs.interfaces.tag.entity.TagEntity;
import com.ywjc.glsyjs.interfaces.tag.entity.TagRelationEntity;
import com.ywjc.glsyjs.interfaces.tag.service.AccuratetagService;
import com.ywjc.glsyjs.interfaces.tag.service.TagService;
import com.ywjc.glsyjs.sys.entity.Generalcode;
import com.ywjc.glsyjs.sys.entity.PolicyFileEntity;
import com.ywjc.glsyjs.sys.service.GeneralcodeService;
import com.ywjc.glsyjs.sys.service.PolicyFileEntityService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller("intellectmatchController")
@RequestMapping("/intellectmatch")
public class IntellectmatchController extends BaseController {

	@Resource(name = "policyFileEntityServiceImpl")
	private PolicyFileEntityService policyfileEntityService;

	private static final int PAGE_SIZE = 9;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "tagServiceImpl")
	private TagService tagService;

	@Resource(name = "policydetailServiceImpl")
	private PolicydetailService policydetailService;

	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "generalcodeServiceImpl")
	private GeneralcodeService generalcodeService;


	@Resource(name = "plycomresultdetailEntityServiceImpl")
	private PlycomresultdetailEntityService plycomresultdetailEntityService;

	@Resource(name = "accuratetagServiceImpl")
	private AccuratetagService accuratetagService;
	/**
	 * List
	 */
	@RequestMapping(value="/policycorplist",method = RequestMethod.GET)
	public String policycorplist(ModelMap model,HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/intellectmatch/policycorplist",session, request,Constantval.LOGIN_VISIT);
		List<TagEntity> tags=tagService.findAll();
		model.addAttribute("tags", tags);


		//政策文件
		List<Object> policyfiles=policyfileEntityService.findAccuratefiles();
		model.addAttribute("policyfiles", policyfiles);
		List<PolicydetailEntity> details=policydetailService.findAccuratePolicydetail();
		for (PolicydetailEntity detail:details) {

			for (Object file:policyfiles) {
				Object[] f=(Object[])file;
				if(f[0].equals(detail.getPlfidid())){
					detail.setPolicyname((String)f[1]);
				}
			}

		}

		model.addAttribute("details", details);
		return "/intellectmatch/policycorplist";
	}


	@RequestMapping(value = "/querytag", method = RequestMethod.POST)
	@ResponseBody
	public List<Object>  querytag(HttpSession session, String pldtailids, ModelMap model, HttpServletRequest request) {
		loginlogService.saveloginlog("/intellectmatch/querytag",session, request,Constantval.LOGIN_VISIT);
		String[] ids=pldtailids.split(",");
		List<Object> tags=accuratetagService.findtags(ids);
		return tags;
	}



	@RequestMapping(value = "/policyfile", method = RequestMethod.POST)
	@ResponseBody
	public Object[] queryfile(HttpSession session,String policyfileid, ModelMap model, HttpServletRequest request) {
		Object[] data=new Object[2];
		loginlogService.saveloginlog("/policydeclare/policyfile",session, request,Constantval.LOGIN_VISIT);
		String[] policyfileids=policyfileid.split(",");
		List<PolicydetailEntity> infos=policydetailService.findPolicyDetails(policyfileids);
		for (PolicydetailEntity info:infos) {
			List<Accuratetag> atags=accuratetagService.findByPid(info.getPldtailid());
			info.setAtags(atags);
		}
		data[0]=infos;
		List<Accuratetag> tags=accuratetagService.findAll();
		data[1]=tags;
		return data;
	}



	@RequestMapping(value = "/policydetail", method = RequestMethod.POST)
	@ResponseBody
	public Object[] policydetail(HttpSession session,String pldtailid, ModelMap model, HttpServletRequest request) {
		Object[] data=new Object[2];
		String[] pldtailids=pldtailid.split(",");
		List<PolicydetailEntity> infos=policydetailService.findBypldtailids(pldtailids);
		for (PolicydetailEntity info:infos) {
			List<Accuratetag> atags=accuratetagService.findByPid(info.getPldtailid());
			info.setAtags(atags);
		}
		data[0]=infos;
		List<Accuratetag> tags=accuratetagService.findAll();
		data[1]=tags;
		return data;
	}



	@RequestMapping(value = "/match", method = RequestMethod.POST)
	@ResponseBody
	public Object[] match(Intellectmatch match,HttpSession session, ModelMap model, HttpServletRequest request) {
		Object[] data=new Object[7];
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR)-1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startime=new Date();
        List<Object> companys=companyEntityService.queryCompanys(match);
		data[0]=sdf.format(startime);
		data[1]=companys.size();
		int i=0;
		List<Object> corpinfos=new ArrayList<>();
		Double minmoney=0d;
		Double maxmoney=0d;

        if(companys!=null&&companys.size()>0){
			String policytagids=match.getTagid();
			String pldtailid=match.getPldtailid();
			if(StringUtils.isNotBlank(policytagids)){
				String[] ptagids=policytagids.split(",");
				String[] pldtailids=pldtailid.split(",");
					for (Object company:companys) {
						Object[] c=(Object[])company;
						String companyid=(String)c[0];
						List<Object> tags=accuratetagService.findTagcount(ptagids,companyid,year);
						if(tags!=null&&tags.size()>0){
							i++;
							Object[] corp=new Object[4];
							corp[0]=companyid;
							corp[1]=c[1];
							List<String> ctags=accuratetagService.findTagidByCid(companyid);
							List<Object> details=accuratetagService.findPTagcount(ctags,pldtailids);
							if(details!=null&&details.size()>0){
								corp[2]=details.size();
								corp[3]=details;
								for (Object detail:details) {
									Object[] d=(Object[])detail;
									Double ramount=(Double)d[4];
									Double minramount=0d;
									Double manramount=0d;
									String minramountstr=(String)d[5];
									String manramountstr=(String)d[6];
									if(StringUtils.isNotBlank(minramountstr)){
										minramount=Double.parseDouble(minramountstr);
									}
									if(StringUtils.isNotBlank(manramountstr)){
										manramount=Double.parseDouble(manramountstr);
									}

									if(minramount!=null){
										minmoney+=minramount;
										maxmoney+=manramount;
									}else{
										minmoney+=ramount;
										maxmoney+=ramount;
									}

								}
							}

							corpinfos.add(corp);
						}

				    }
			}

		}else{
			data[2]=0;
		}
		data[4]=corpinfos;
		data[2]=i;
		Date endtime=new Date();
		data[3]=sdf.format(endtime);
		if(minmoney!=0){
			minmoney=minmoney/10000;
		}
		if(maxmoney!=0){
			maxmoney=maxmoney/10000;
		}
		data[5]=minmoney;
		data[6]=maxmoney;
		return data;
	}




	/**
	 * List
	 */
	@RequestMapping(value="/autoapplylist",method = RequestMethod.GET)
	public String autoapplylist(ModelMap model,HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/intellectmatch/autoapplylist",session, request,Constantval.LOGIN_VISIT);
        //政策文件
		List<Object> policyfiles=policyfileEntityService.findMSfiles();
		model.addAttribute("policyfiles", policyfiles);
		List<PolicydetailEntity> details=policydetailService.findMsjx();

		List<TagEntity> tags=tagService.findAll();
		model.addAttribute("tags", tags);
		List<Object>corptypes=companyEntityService.querytypesenterprises();
		model.addAttribute("corptypes", corptypes);

		List<Generalcode> status = generalcodeService.findByCodetableid("0000000103");
		model.addAttribute("status", status);
		for (PolicydetailEntity detail:details) {

			for (Object file:policyfiles) {
				Object[] f=(Object[])file;
				if(f[0].equals(detail.getPlfidid())){
					detail.setPolicyname((String)f[1]);
				}
			}

		}
		model.addAttribute("details", details);
		return "/intellectmatch/autoapplylist";
	}




	@RequestMapping(value = "/querycorp", method = RequestMethod.POST)
	@ResponseBody
	public List<CompanyEntity> querycorp(Intellectmatch match,String corpname, HttpSession session, ModelMap model, HttpServletRequest request) {
		List<CompanyEntity> companys=null;
	    if(StringUtils.isNotBlank(corpname)){
			companys=companyEntityService.queryCompanyList("%"+corpname.trim()+"%");
		}else{
			List<Object> companyids=companyEntityService.queryCompanysAuto(match);
			if(companyids!=null&&companyids.size()>0){
				companys=companyEntityService.queryCompanys(companyids);
			}

		}
		return companys;
	}

	@RequestMapping(value = "/querypolicyfilemsjx", method = RequestMethod.POST)
	@ResponseBody
	public List<PolicydetailEntity> querypolicyfilemsjx(String plfidid, HttpSession session, ModelMap model, HttpServletRequest request) {
		List<PolicydetailEntity> details=null;
		if(StringUtils.isNotBlank(plfidid)){
			details=policydetailService.findMSfilesByplfidid(plfidid);
		}else{
			details=policydetailService.findMsjx();
		}
		List<Object> policyfiles=policyfileEntityService.findMSfiles();
		for (PolicydetailEntity detail:details) {

			for (Object file:policyfiles) {
				Object[] f=(Object[])file;
				if(f[0].equals(detail.getPlfidid())){
					detail.setPolicyname((String)f[1]);
				}
			}

		}

		return details;
	}

	@RequestMapping(value = "/querypolicyfile", method = RequestMethod.POST)
	@ResponseBody
	public Page<PolicyFileEntity> queryfile(HttpSession session,String filename,@PageableDefault(page = 0, size = Constantval.PAGE_SIZE_TANCHU) Pageable pageable,
											ModelMap model, HttpServletRequest request) {
		filename= StringUtils.isNotBlank(filename)?"%"+filename+"%":"%%";
		Page<PolicyFileEntity> page=policyfileEntityService.findPolicyFilename(filename,pageable);
		for (PolicyFileEntity policyFile:page.getContent()) {
			List<PolicydetailEntity> policydetails=policydetailService.findPolicyFiles(policyFile.getPlfidid());
			policyFile.setDetails(policydetails);
		}

		return page;
	}


	@RequestMapping(value = "/queryfile", method = RequestMethod.POST)
	@ResponseBody
	public List<PolicydetailEntity> queryfile(String plfidid, HttpSession session, ModelMap model, HttpServletRequest request) {
		List<PolicydetailEntity> details=null;
		if(StringUtils.isNotBlank(plfidid)){
			details=policydetailService.findAccuratePolicydetailByplfidid(plfidid);
		}else{
			details=policydetailService.findAccuratePolicydetail();
		}
		List<Object> policyfiles=policyfileEntityService.findAccuratefiles();
		for (PolicydetailEntity detail:details) {

			for (Object file:policyfiles) {
				Object[] f=(Object[])file;
				if(f[0].equals(detail.getPlfidid())){
					detail.setPolicyname((String)f[1]);
				}
			}

		}

		return details;
	}


	@RequestMapping(value = "/msmatch", method = RequestMethod.POST)
	@ResponseBody
	public Page<PlycomresultdetailEntity> msmatch(String  pldtailids,String companyids,@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
							HttpSession session, ModelMap model, HttpServletRequest request) {
		String[] pldtailid=pldtailids.split(",");
		String[] companyid=companyids.split(",");
		Page<PlycomresultdetailEntity>  infos=plycomresultdetailEntityService.findinfoMsjx(pldtailid,companyid,pageable);
		return infos;
	}
}
