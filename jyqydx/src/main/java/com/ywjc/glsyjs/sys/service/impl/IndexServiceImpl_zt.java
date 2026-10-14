//package com.centit.glsyjs.sys.service.impl;
//
//import com.centit.glsyjs.account.entity.User;
//import com.centit.glsyjs.cache.CacheUtil;
//import com.centit.glsyjs.cache.Cachemodel;
//import com.centit.glsyjs.core.util.Constantval;
//import com.centit.glsyjs.core.util.KeyUtils;
//import com.centit.glsyjs.core.util.PropertiesUtil;
//import com.centit.glsyjs.corpinfo.entity.Qydjxx;
//import com.centit.glsyjs.corpinfo.entity.Xmfjxx;
//import com.centit.glsyjs.corpinfo.entity.Xmjbxxreply;
//import com.centit.glsyjs.corpinfo.repositories.CorpinfocheckRepository;
//import com.centit.glsyjs.corpinfo.service.CreditgradeService;
//import com.centit.glsyjs.corpinfo.service.QydjxxService;
//import com.centit.glsyjs.corpinfo.service.XmfjxxService;
//import com.centit.glsyjs.corpinfo.service.XmjbxxreplyService;
//import com.centit.glsyjs.crdteval.entity.Evalresult;
//import com.centit.glsyjs.crdteval.entity.Evalresult_jscorp;
//import com.centit.glsyjs.crdteval.entity.Evalresult_qx;
//import com.centit.glsyjs.crdteval.service.EvalresultService;
//import com.centit.glsyjs.crdteval.service.Evalresult_jscorpService;
//import com.centit.glsyjs.crdteval.service.Evalresult_qxService;
//import com.centit.glsyjs.evalmanage.entity.Companydateeval;
//import com.centit.glsyjs.evalmanage.entity.Companyrank;
//import com.centit.glsyjs.evalmanage.entity.Tevalmanageinfo;
//import com.centit.glsyjs.evalmanage.service.CompanydateevalService;
//import com.centit.glsyjs.evalmanage.service.CompanyrankService;
//import com.centit.glsyjs.evalmanage.service.TevalmanageinfoService;
//import com.centit.glsyjs.goverinfo.entity.Lawinfo;
//import com.centit.glsyjs.goverinfo.entity.Workinfo;
//import com.centit.glsyjs.goverinfo.repositories.WorkinfoRepository;
//import com.centit.glsyjs.goverinfo.service.GoodunitService;
//import com.centit.glsyjs.goverinfo.service.LawinfoService;
//import com.centit.glsyjs.goverinfo.service.WorkinfoService;
//import com.centit.glsyjs.sys.entity.*;
//import com.centit.glsyjs.sys.repositories.CorpregisterRepository;
//import com.centit.glsyjs.sys.service.*;
//import com.centit.glsyjs.web.entity.*;
//import com.centit.glsyjs.web.service.*;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.ModelMap;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Service("indexServiceImpl")
//public class IndexServiceImpl_zt implements IndexService {
//
//	@Resource(name = "lawinfoServiceImpl")
//	private LawinfoService lawinfoService;
//
//	@Resource(name = "workinfoServiceImpl")
//	private WorkinfoService workinfoService;
//
//	@Resource(name = "noticeinfoServiceImpl")
//	private NoticeinfoService noticeinfoService;
//
//	@Resource(name = "initIndexServiceImpl")
//	private InitIndexService initIndexService;
//
//	@Resource(name = "registernoticeServiceImpl")
//	private RegisternoticeService registernoticeService;
//
//	@Resource(name = "organinfoServiceImpl")
//	private OrganinfoService organinfoService;
//
//	@Resource(name = "evalresultServiceImpl")
//	private EvalresultService evalresultService;
//
//	@Resource(name = "evalresult_qxServiceImpl")
//	private Evalresult_qxService evalresult_qxService;
//
//	@Resource(name = "corpinfocheckRepository")
//	private CorpinfocheckRepository corpinfocheckRepository;
//
//	@Resource(name = "corpregisterRepository")
//	private CorpregisterRepository corpregisterRepository;
//
//	@Resource(name = "workinfoRepository")
//	private WorkinfoRepository workinfoRepository;
//
//	@Resource(name = "evalresult_jscorpServiceImpl")
//	private Evalresult_jscorpService evalresult_jscorpService;
//
//	@Resource(name = "credit_questionsServiceImpl")
//	private Credit_questionsService credit_questionsService;
//
//	@Resource(name = "credittypeServiceImpl")
//	private CredittypeService credittypeService;
//
//	@Resource(name = "creditscoreServiceImpl")
//	private CreditscoreService creditscoreService;
//
//	@Resource(name = "credittypeScoreServiceImpl")
//	private CredittypeScoreService credittypeScoreService;
//
//	@Resource(name = "creditscoreJCServiceImpl")
//	private CreditscoreJCService creditscoreJCService;
//
//	@Resource(name = "creditscoreHisServiceImpl")
//	private CreditscoreHisService creditscoreHisService;
//
//	@Resource(name = "credittypeScoreHisServiceImpl")
//	private CredittypeScoreHisService credittypeScoreHisService;
//	// ---------------------web-----------------
//	@Resource(name = "badunitwebServiceImpl")
//	private BadunitwebService badunitwebService;
//
//	@Resource(name = "goodunitwebServiceImpl")
//	private GoodunitwebService goodunitwebService;
//
//	@Resource(name = "qydjxxwebServiceImpl")
//	private QydjxxwebService qydjxxwebService;
//
//	@Resource(name = "xmjbxxwebServiceImpl")
//	private XmjbxxwebService xmjbxxwebService;
//
//	@Resource(name = "badwjwebServiceImpl")
//	private BadwjwebService badwjwebService;
//
//	@Resource(name = "goodwjwebServiceImpl")
//	private GoodwjwebService goodwjwebService;
//
//	@Resource(name = "qydjxxServiceImpl")
//	private QydjxxService qydjxxService;
//
//	@Resource(name = "creditgradeServiceImpl")
//	private CreditgradeService creditgradeService;
//
//	@Resource(name = "faithfulcorpServiceImpl")
//	private FaithfulcorpService faithfulcorpService;
//	@Resource(name = "companydateevalServiceImpl")
//	private CompanydateevalService companydateevalService;
//
//
//	@Resource(name = "companyrankServiceImpl")
//	private CompanyrankService companyrankService;
//
//	@Resource(name = "goodunitServiceImpl")
//	private GoodunitService goodunitService;
//
//	@Resource(name = "tevalmanageinfoServiceImpl")
//	private TevalmanageinfoService tevalmanageinfoService;
//
//	@Resource(name = "xmjbxxreplyServiceImpl")
//	private XmjbxxreplyService xmjbxxreplyService;
//
//	@Resource(name = "xmjbxxreplywebServiceImpl")
//	private XmjbxxreplywebService xmjbxxreplywebService;
//
//	@Resource(name = "xmfjxxServiceImpl")
//	private XmfjxxService xmfjxxService;
//
//	@Resource(name = "xmfjxxwebServiceImpl")
//	private XmfjxxwebService xmfjxxwebService;
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initWebIndexinfo(ModelMap model, HttpSession session) {
//		initWebindexinfo2(model);
//		initPublicNotice(session);
//		initVisitcount(session, "gl");
//		// initCredit();
//	}
//
//	/**
//	 * @param model
//	 */
//	private void initVisitcount(HttpSession session, String industrytype) {
//
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		Map<String, Object> cacheMap = cacheUtil.getCache("visitcountInfo" + industrytype);
//		Long visitcount = 0L;
//		if (cacheMap == null) {
//			Cachemodel cachemodel = new Cachemodel();
//			cacheMap = new HashMap<String, Object>();
//			visitcount = Long.valueOf(PropertiesUtil.getValue("visitcount" + industrytype)) + 1;
//			cacheMap.put("visitcount" + industrytype, visitcount.toString());
//			cachemodel.setCacheMap(cacheMap);
//			cachemodel.setCdate(new Date());
//			cachemodel.setVdate(24);
//			cacheUtil.addCache("visitcountInfo" + industrytype, cachemodel);
//		} else {
//			visitcount = Long.valueOf(cacheMap.get("visitcount" + industrytype).toString()) + 1l;
//			cacheMap.put("visitcount" + industrytype, visitcount);
//		}
//		session.setAttribute("visitcount" + industrytype, visitcount);
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("key", "visitcount" + industrytype);
//		map.put("value", visitcount.toString());
//		PropertiesUtil.setValue(map);
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initSyWebIndexinfo(ModelMap model, HttpSession session) {
//		initSyWebindexinfo2(model);
//		initSyPublicNotice(session);
//		initVisitcount(session, "sy");
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrCorpGl(ModelMap model, String current_industry) {
//		List<Workinfo> workinfolist = workinfoRepository.findByIndustryTopn(current_industry);
//		model.addAttribute("workinfolist", workinfolist);
//		Long countcheck = 0l;
//		Long countsucess = 0l;
//		Long countfail = 0l;
//		Long countpunish = 0l;
//		model.addAttribute("countcheck", countcheck);
//		model.addAttribute("countsucess", countsucess);
//		model.addAttribute("countfail", countfail);
//		model.addAttribute("countpunish", countpunish);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrCorpSy(ModelMap model, String current_industry) {
//		List<Workinfo> workinfolist = workinfoRepository.findByIndustryTopn(current_industry);
//		model.addAttribute("workinfolist", workinfolist);
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrZjj(ModelMap model, User user) {
//		String organid = user.getOrganid();
//		Organinfo organinfo = organinfoService.find(organid);
//		model.addAttribute("organinfo", organinfo);
//		List<Workinfo> workinfolist = workinfoRepository.findAllTopn();
//		model.addAttribute("workinfolist", workinfolist);
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrGlj(ModelMap model, User user) {
//		String organid = user.getOrganid();
//		Organinfo organinfo = organinfoService.find(organid);
//		model.addAttribute("organinfo", organinfo);
//		List<Workinfo> workinfolist = workinfoRepository.findAllTopn();
//		model.addAttribute("workinfolist", workinfolist);
//
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrJgc(ModelMap model, User user) {
//		String industry = Constantval.CONSTANT_INDUSTRY_ONE;
//		String organid = user.getOrganid();
//		Organinfo organinfo = organinfoService.find(organid);
//		String orgcode = organinfo.getOrgcode();
//		Long countcorpcheck = corpinfocheckRepository.findCountByIndex(industry);
//		Long countregistercheck = corpregisterRepository.findCountByIndex(orgcode, "1");
//		model.addAttribute("countcorpcheck", countcorpcheck);
//		model.addAttribute("countregistercheck", countregistercheck);
//		model.addAttribute("organinfo", organinfo);
//		List<Workinfo> workinfolist = workinfoRepository.findByIndustryTopn(industry);
//		model.addAttribute("workinfolist", workinfolist);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrGjc(ModelMap model, User user) {
//		String industry = Constantval.CONSTANT_INDUSTRY_TWO;
//		String organid = user.getOrganid();
//		Organinfo organinfo = organinfoService.find(organid);
//		String orgcode = organinfo.getOrgcode();
//		Long countcorpcheck = corpinfocheckRepository.findCountByIndex(industry);
//		Long countregistercheck = corpregisterRepository.findCountByIndex(orgcode, "1");
//		model.addAttribute("countcorpcheck", countcorpcheck);
//		model.addAttribute("countregistercheck", countregistercheck);
//		model.addAttribute("organinfo", organinfo);
//		List<Workinfo> workinfolist = workinfoRepository.findByIndustryTopn(industry);
//		model.addAttribute("workinfolist", workinfolist);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public void initMgrQxjtj(ModelMap model, User user) {
//		String organid = user.getOrganid();
//		Organinfo organinfo = organinfoService.find(organid);
//		model.addAttribute("organinfo", organinfo);
//		List<Workinfo> workinfolist = workinfoRepository.findAllTopn();
//		model.addAttribute("workinfolist", workinfolist);
//	}
//
//	private void initWebindexinfo2(ModelMap model) {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		Map<String, Object> cacheMap = cacheUtil.getCache("webInfo");
//		initinfo2(cacheUtil, cacheMap, Constantval.CONSTANT_INDUSTRY_ONE, model);
//
//	}
//
//	private void initPublicNotice(HttpSession session) {
//		Noticeinfo noticegl = noticeinfoService.findPublicNotice(Constantval.CONSTANT_INDUSTRY_ONE);
//		session.setAttribute("noticegl", noticegl);
//		List<Credit_questions> credit_questions = credit_questionsService.findNum(Constantval.CONSTANT_INDUSTRY_ONE,
//				"1");
//		session.setAttribute("credit_questions", credit_questions);
//		Organinfo organinfo = organinfoService.findByOrgcode(Constantval.RoadOrgcode);
//		session.setAttribute("organinfogl", organinfo);
//		Registernotice registernotice = registernoticeService.findByIndustry(Constantval.CONSTANT_INDUSTRY_ONE);
//		session.setAttribute("registernotice", registernotice);
//	}
//
//	private void initSyWebindexinfo2(ModelMap model) {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		Map<String, Object> cacheMap = cacheUtil.getCache("webInfosy");
//		initinfo2(cacheUtil, cacheMap, Constantval.CONSTANT_INDUSTRY_TWO, model);
//
//	}
//
//	private void initinfo2(CacheUtil cacheUtil, Map<String, Object> cacheMap, String industry, ModelMap model) {
//		List<Qydjxxweb> qydjxxs = null;
//		List<Qydjxxweb> sgqydjxxs = null;
//		List<Qydjxxweb> jlqydjxxs = null;
//		List<Qydjxxweb> sjqydjxxs = null;
//		List<Qydjxxweb> jsqydjxxs = null;
//		List<Qydjxxweb> qtqydjxxs = null;
//		List<Xmjbxxweb> xmjbxxhighway = null;
//		List<Xmjbxxweb> xmjbxxlocalway = null;
//		List<Evalresult> evalresults1 = null;
//		List<Evalresult> evalresults2 = null;
//		List<Evalresult> evalresults3 = null;
//		List<Evalresult_jscorp> evalresults4 = null;
//		List<Evalresult_qx> evalresults_qx1 = null;
//		List<Evalresult_qx> evalresults_qx2 = null;
//		List<Evalresult_qx> evalresults_qx3 = null;
//		List<Badunitweb> badunit = null;
//		List<Goodunitweb> goodunit = null;
//		List<Workinfo> workinfogongshi = null;
//		List<Workinfo> workinfogonggao = null;
//		List<Workinfo> workinfotongzhi = null;
//		List<Workinfo> workinfoshiji = null;
//		List<Workinfo> workinfoquxian = null;
//		List<Lawinfo> lawbuwei = null;
//		List<Lawinfo> lawfalv = null;
//		List<Lawinfo> lawdifang = null;
//		List<Lawinfo> law = null;
//		Organinfo organinfo = null;
//		List<Object> creditscores = qydjxxwebService.findCreditscore(industry);
//		List<Xmjbxxweb> xmjbxx = new ArrayList<>();
//		if (cacheMap == null) {
//			qydjxxs = qydjxxwebService.findQydjxxinfos(industry);
//			sgqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_THREE);
//			jlqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_FOUR);
//			sjqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_TWO);
//			jsqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_ONE);
//			qtqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_ZERO);
//			xmjbxxhighway = xmjbxxwebService.findXmjbxxinfos(Constantval.CONSTANT_PROJECT_TYPE_ONE, industry);
//			xmjbxxlocalway = xmjbxxwebService.findXmjbxxinfos(Constantval.CONSTANT_PROJECT_TYPE_TWO, industry);
//			if(null!=xmjbxxhighway) {
//				xmjbxx.addAll(xmjbxxhighway);
//			}
//			if(null!=xmjbxxlocalway) {
//				xmjbxx.addAll(xmjbxxlocalway);
//			}
//
//
//			badunit = badunitwebService.findBadunit(industry);
//			for (Badunitweb badunit2 : badunit) {
//				badunit2.setWj(badwjwebService.find(badunit2.getDocument_id()));
//			}
//			goodunit = goodunitwebService.findGoodunit1(industry);
//			for (Goodunitweb goodunit2 : goodunit) {
//				goodunit2.setWj(goodwjwebService.find(goodunit2.getDocument_id()));
//			}
//
//			evalresults1 = evalresultService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_THREE);
//			evalresults2 = evalresultService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_FOUR);
//			evalresults3 = evalresultService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_TWO);
//			evalresults4 = evalresult_jscorpService.findJsByCorp_type(industry);
//			evalresults_qx1 = evalresult_qxService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_THREE);
//			evalresults_qx2 = evalresult_qxService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_FOUR);
//			evalresults_qx3 = evalresult_qxService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_TWO);
//
//			organinfo = organinfoService.findByOrgcode(Constantval.RoadOrgcode);
//			Cachemodel cachemodel = new Cachemodel();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("qydjxxs", qydjxxs);
//			map.put("sgqydjxxs", sgqydjxxs);
//			map.put("jlqydjxxs", jlqydjxxs);
//			map.put("sjqydjxxs", sjqydjxxs);
//			map.put("jsqydjxxs", jsqydjxxs);
//			map.put("qtqydjxxs", qtqydjxxs);
//			map.put("xmjbxxs", xmjbxx);
//			map.put("evalresults1", evalresults1);
//			map.put("evalresults2", evalresults2);
//			map.put("evalresults3", evalresults3);
//			map.put("evalresults4", evalresults4);
//			map.put("evalresults_qx1", evalresults_qx1);
//			map.put("evalresults_qx2", evalresults_qx2);
//			map.put("evalresults_qx3", evalresults_qx3);
//			map.put("badunit", badunit);
//			map.put("goodunit", goodunit);
//			// map.put("workinfogongshi", workinfogongshi);
//			// map.put("workinfogonggao", workinfogonggao);
//			// map.put("workinfotongzhi", workinfotongzhi);
//			// map.put("workinfoshiji", workinfoshiji);
//			// map.put("workinfoquxian", workinfoquxian);
//			// map.put("lawbuwei", lawbuwei);
//			// map.put("lawfalv", lawfalv);
//			// map.put("lawdifang", lawdifang);
//			/*
//			 * //信用分值计算 if(Constantval.CONSTANT_INDUSTRY_ONE.equals(industry)) {
//			 * SimpleDateFormat sdf=new SimpleDateFormat("yyyy"); List<Qydjxx> corps =
//			 * qydjxxService.findCorpsByindustry(Constantval.CONSTANT_INDUSTRY_ONE); for
//			 * (Qydjxx qydjxx : corps) { initCreditScore(qydjxx); } for (Qydjxxweb qydjxx :
//			 * qydjxxs) { Creditscore
//			 * creditScore=creditscoreService.findByCorp(Constantval.CONSTANT_INDUSTRY_ONE,
//			 * qydjxx.getCorp_id(),sdf.format(new Date()));
//			 * qydjxx.setCreditscore(creditScore); } }
//			 */
//			map.put("organinfo", organinfo);
//			cachemodel.setCacheMap(map);
//			cachemodel.setCdate(new Date());
//			cachemodel.setVdate(24);
//			if (Constantval.CONSTANT_INDUSTRY_ONE.equals(industry)) {
//				cacheUtil.addCache("webInfo", cachemodel);
//			} else {
//				cacheUtil.addCache("webInfosy", cachemodel);
//			}
//
//		} else {
//			qydjxxs = (List<Qydjxxweb>) cacheMap.get("qydjxxs");
//			sgqydjxxs = (List<Qydjxxweb>) cacheMap.get("sgqydjxxs");
//			jlqydjxxs = (List<Qydjxxweb>) cacheMap.get("jlqydjxxs");
//			sjqydjxxs = (List<Qydjxxweb>) cacheMap.get("sjqydjxxs");
//			jsqydjxxs = (List<Qydjxxweb>) cacheMap.get("jsqydjxxs");
//			qtqydjxxs = (List<Qydjxxweb>) cacheMap.get("qtqydjxxs");
//			xmjbxxhighway = xmjbxxwebService.findXmjbxxinfos(Constantval.CONSTANT_PROJECT_TYPE_ONE, industry);
//			xmjbxxlocalway = xmjbxxwebService.findXmjbxxinfos(Constantval.CONSTANT_PROJECT_TYPE_TWO, industry);
//			badunit=(List<Badunitweb>)cacheMap.get("badunit");
//			if(null==badunit||badunit.isEmpty()) {
//				badunit = badunitwebService.findBadunit(industry);
//				for (Badunitweb badunit2 : badunit) {
//					badunit2.setWj(badwjwebService.find(badunit2.getDocument_id()));
//				}
//			}
//			goodunit=(List<Goodunitweb>)cacheMap.get("goodunit");
//			if(null!=xmjbxxhighway) {
//				xmjbxx.addAll(xmjbxxhighway);
//			}
//			if(null!=xmjbxxlocalway) {
//				xmjbxx.addAll(xmjbxxlocalway);
//			}
//
//			// badunit=badunitwebService.findBadunit(industry);
//			// for (Badunitweb badunit2 : badunit) {
//			// badunit2.setWj(badwjwebService.find(badunit2.getDocument_id()));
//			// }
//			//
//			// goodunit=goodunitwebService.findGoodunit1(industry);
//			// for (Goodunitweb goodunit2 : goodunit) {
//			// goodunit2.setWj(goodwjwebService.find(goodunit2.getDocument_id()));
//			// }
//
//			evalresults1 = (List<Evalresult>) cacheMap.get("evalresults1");
//			evalresults2 = (List<Evalresult>) cacheMap.get("evalresults2");
//			evalresults3 = (List<Evalresult>) cacheMap.get("evalresults3");
//			evalresults4 = (List<Evalresult_jscorp>) cacheMap.get("evalresults4");
//			evalresults_qx1 = (List<Evalresult_qx>) cacheMap.get("evalresults_qx1");
//			evalresults_qx2 = (List<Evalresult_qx>) cacheMap.get("evalresults_qx2");
//			evalresults_qx3 = (List<Evalresult_qx>) cacheMap.get("evalresults_qx3");
//
//			// workinfogongshi=(List<Workinfo>)cacheMap.get("workinfogongshi");
//			// workinfogonggao=(List<Workinfo>)cacheMap.get("workinfogonggao");
//			// workinfotongzhi=(List<Workinfo>)cacheMap.get("workinfotongzhi");
//			// workinfoshiji=(List<Workinfo>)cacheMap.get("workinfoshiji");
//			// workinfoquxian=(List<Workinfo>)cacheMap.get("workinfoquxian");
//
//			// workinfogongshi=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_GONGSHI);
//			// workinfogonggao=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_GONGGAO);
//			// workinfotongzhi=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_TONGZHI);
//			// workinfoshiji=workinfoService.findIndexByIndustryShiji(industry);
//			// workinfoquxian=workinfoService.findIndexByIndustryQuxian(industry);
//
//			// lawbuwei=(List<Lawinfo>)cacheMap.get("lawbuwei");
//			// lawfalv=(List<Lawinfo>)cacheMap.get("lawfalv");
//			// lawdifang=(List<Lawinfo>)cacheMap.get("lawdifang");
//			// organinfo=(Organinfo)cacheMap.get("organinfo");
//		}
//		model.addAttribute("qydjxxs", qydjxxs);
//		model.addAttribute("sgqydjxxs", sgqydjxxs);
//		model.addAttribute("jlqydjxxs", jlqydjxxs);
//		model.addAttribute("sjqydjxxs", sjqydjxxs);
//		model.addAttribute("jsqydjxxs", jsqydjxxs);
//		model.addAttribute("qtqydjxxs", qtqydjxxs);
//		model.addAttribute("xmjbxxs", xmjbxx);
//		model.addAttribute("badunit", badunit);
//		model.addAttribute("goodunit", goodunit);
//
//		model.addAttribute("evalresults1", evalresults1);
//		model.addAttribute("evalresults2", evalresults2);
//		model.addAttribute("evalresults3", evalresults3);
//		model.addAttribute("evalresults4", evalresults4);
//		model.addAttribute("evalresults_qx1", evalresults_qx1);
//		model.addAttribute("evalresults_qx2", evalresults_qx2);
//		model.addAttribute("evalresults_qx3", evalresults_qx3);
//
//		model.addAttribute("creditscores", creditscores);
//
//		workinfogongshi=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_GONGSHI);
//		workinfogonggao=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_GONGGAO);
//		 workinfotongzhi=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_TONGZHI);
//		workinfoshiji = workinfoService.findIndexByIndustryShiji(industry);
//		workinfoquxian=workinfoService.findIndexByIndustryQuxian(industry);
//		lawbuwei=lawinfoService.findIndexByIndustryLawtype(industry,Constantval.LAWTYPE_BUWEI_GUIDING);
//		lawfalv=lawinfoService.findIndexByIndustryLawtype(industry,Constantval.LAWTYPE_FALV_FAGEI);
//		lawdifang=lawinfoService.findIndexByIndustryLawtype(industry,Constantval.LAWTYPE_DIFANG_GUIDING);
//
//		law = lawinfoService.findIndexByIndustry(industry);
//
//		model.addAttribute("workinfogongshi", workinfogongshi);
//		model.addAttribute("workinfogonggao", workinfogonggao);
//		model.addAttribute("workinfotongzhi", workinfotongzhi);
//		model.addAttribute("workinfoshiji", workinfoshiji);
//		model.addAttribute("workinfoquxian", workinfoquxian);
//
//		model.addAttribute("lawbuwei", lawbuwei);
//		model.addAttribute("lawfalv", lawfalv);
//		model.addAttribute("lawdifang", lawdifang);
//		model.addAttribute("law", law);
//		model.addAttribute("organinfogl", organinfo);
//	}
//
//	private void initSyPublicNotice(HttpSession session) {
//		Noticeinfo noticesy = noticeinfoService.findPublicNotice(Constantval.CONSTANT_INDUSTRY_TWO);
//		session.setAttribute("noticesy", noticesy);
//		List<Credit_questions> credit_questions = credit_questionsService.findNum(Constantval.CONSTANT_INDUSTRY_TWO,
//				"1");
//		Organinfo organinfo = organinfoService.findByOrgcode(Constantval.WaterOrgcode);
//		session.setAttribute("organinfo", organinfo);
//		session.setAttribute("credit_questions", credit_questions);
//	}
//
//	@Override
//	public void initInfo(ModelMap model) {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		Map<String, Object> cacheMap = cacheUtil.getCache("info");
//		initInfoInner(cacheUtil, cacheMap, Constantval.CONSTANT_INDUSTRY_ONE, model);
//	}
//
//	@Override
//	public void initInfo1(ModelMap model, User user) {
//		initInfoInner1(Constantval.CONSTANT_INDUSTRY_ONE, model, user);
//	}
//
//	@Override
//	public void initInfoSy(ModelMap model) {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		Map<String, Object> cacheMap = cacheUtil.getCache("infosy");
//		initInfoInner(cacheUtil, cacheMap, Constantval.CONSTANT_INDUSTRY_TWO, model);
//	}
//
//	private void initInfoInner(CacheUtil cacheUtil, Map<String, Object> cacheMap, String industry, ModelMap model) {
//		Object corpcount = null;
//		Object projectcount = null;
//		Object period_code = null;
//		Object evalCorpcount = null;
//		Object evalProjectcount = null;
//		Object corp_js = null;
//		Object corp_sj = null;
//		Object corp_sg = null;
//		Object corp_jl = null;
//		Object corp_jc = null;
//		Object corp_other = null;
//		List<Object> corpGrade = null;
//		List<Object> projects = null;
//		List<Object> projectsOrg = null;
//		if (cacheMap == null) {
//			corpcount = initIndexService.corpcount(industry);
//			projectcount = initIndexService.projectcount(industry);
//			period_code = initIndexService.findEvaling(industry);
//			evalCorpcount = initIndexService.evalCorpcount(industry, (String) period_code);
//			evalProjectcount = initIndexService.evalProjectcount(industry, (String) period_code);
//			corp_js = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_ONE + "%");
//			corp_sj = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_TWO + "%");
//			corp_sg = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_THREE + "%");
//			corp_jl = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_FOUR + "%");
//			corp_jc = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_FIVE + "%");
//			corp_other = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_ZERO + "%");
//			corpGrade = initIndexService.corpByGrade(industry, (String) period_code);
//			projects = initIndexService.projectByType(industry);
//			projectsOrg = initIndexService.projectByOrg(industry);
//			Cachemodel cachemodel = new Cachemodel();
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("corpcount", corpcount);
//			map.put("projectcount", projectcount);
//			map.put("period_code", period_code);
//			map.put("evalCorpcount", evalCorpcount);
//			map.put("evalProjectcount", evalProjectcount);
//			map.put("corp_js", corp_js);
//			map.put("corp_sj", corp_sj);
//			map.put("corp_sg", corp_sg);
//			map.put("corp_jl", corp_jl);
//			map.put("corp_jc", corp_jc);
//			map.put("corp_other", corp_other);
//			map.put("corpGrade", corpGrade);
//			map.put("projects", projects);
//			map.put("projectsOrg", projectsOrg);
//			cachemodel.setCacheMap(map);
//			cachemodel.setCdate(new Date());
//			cachemodel.setVdate(24);
//			if (Constantval.CONSTANT_INDUSTRY_ONE.equals(industry)) {
//				cacheUtil.addCache("info", cachemodel);
//			} else {
//				cacheUtil.addCache("infosy", cachemodel);
//			}
//		} else {
//			corpcount = cacheMap.get("corpcount");
//			projectcount = cacheMap.get("projectcount");
//			period_code = cacheMap.get("period_code");
//			evalCorpcount = cacheMap.get("evalCorpcount");
//			evalProjectcount = cacheMap.get("evalProjectcount");
//			corp_js = cacheMap.get("corp_js");
//			corp_sj = cacheMap.get("corp_sj");
//			corp_sg = cacheMap.get("corp_sg");
//			corp_jl = cacheMap.get("corp_jl");
//			corp_jc = cacheMap.get("corp_jc");
//			corp_other = cacheMap.get("corp_other");
//			corpGrade = (List<Object>) cacheMap.get("corpGrade");
//			projects = (List<Object>) cacheMap.get("projects");
//			projectsOrg = (List<Object>) cacheMap.get("projectsOrg");
//		}
//		model.addAttribute("period_code", period_code);
//		model.addAttribute("corpcount", corpcount);
//		model.addAttribute("projectcount", projectcount);
//		model.addAttribute("evalCorpcount", evalCorpcount);
//		model.addAttribute("evalProjectcount", evalProjectcount);
//		model.addAttribute("corp_js", corp_js);
//		model.addAttribute("corp_sj", corp_sj);
//		model.addAttribute("corp_sg", corp_sg);
//		model.addAttribute("corp_jl", corp_jl);
//		model.addAttribute("corp_jc", corp_jc);
//		model.addAttribute("corp_other", corp_other);
//		model.addAttribute("corpGrade", corpGrade);
//		model.addAttribute("projects", projects);
//		model.addAttribute("projectsOrg", projectsOrg);
//
//	}
//
//	private void initInfoInner1(String industry, ModelMap model, User user) {
//		String thisorgcode = organinfoService.find(user.getOrganid()).getOrgcode();
//		String thisorgcode2 = "%" + thisorgcode + "%";
//		Object qydjxxcheckcount = null;
//		Object new_corp_check_viewcount = null;
//		Object newqyyjxxcount = null;
//		Object change_corp_check_viewcount = null;
//		Object changeqyyjxxcount = null;
//		Object xmjbxxcount = null;
//		Object xmbdxxcount = null;
//		Object xmbdfbxxcount = null;
//		Object xmbdryxxcount = null;
//		Object jch_check_viewcount = null;
//		Object reportinfocount = null;
//		Object corpuser_viewcount = null;
//		if (Constantval.CONSTANT_USERTYPE_THREE.equals(user.getUsertype())) {
//			qydjxxcheckcount = initIndexService.qydjxxcheckcount(industry);
//			new_corp_check_viewcount = initIndexService.corp_check_viewcount(industry, Constantval.DATETYPE_NEW);
//			newqyyjxxcount = initIndexService.qyyjxxcount(industry, Constantval.DATETYPE_NEW);
//			change_corp_check_viewcount = initIndexService.corp_check_viewcount(industry, Constantval.DATETYPE_UPDATE);
//			changeqyyjxxcount = initIndexService.qyyjxxcount(industry, Constantval.DATETYPE_UPDATE);
//			jch_check_viewcount = initIndexService.jch_check_viewcount(industry);
//			corpuser_viewcount = initIndexService.corpuser_viewcount(industry);
//		}
//		xmjbxxcount = initIndexService.xmjbxxcount(industry, thisorgcode, thisorgcode2);
//		xmbdxxcount = initIndexService.xmbdxxcount(industry, thisorgcode, thisorgcode2);
//		xmbdfbxxcount = initIndexService.xmbdfbxxcount(industry, thisorgcode, thisorgcode2);
//		xmbdryxxcount = initIndexService.xmbdryxxcount(industry, thisorgcode, thisorgcode2);
//		if (Constantval.CONSTANT_USERTYPE_THREE.equals(user.getUsertype())
//				|| Constantval.CONSTANT_USERTYPE_FOUR.equals(user.getUsertype())) {
//			reportinfocount = initIndexService.reportinfocount1(industry, thisorgcode);
//		} else {
//			reportinfocount = initIndexService.reportinfocount2(industry, thisorgcode);
//		}
//
//		model.addAttribute("qydjxxcheckcount", qydjxxcheckcount);
//		model.addAttribute("new_corp_check_viewcount", new_corp_check_viewcount);
//		model.addAttribute("newqyyjxxcount", newqyyjxxcount);
//		model.addAttribute("change_corp_check_viewcount", change_corp_check_viewcount);
//		model.addAttribute("changeqyyjxxcount", changeqyyjxxcount);
//		model.addAttribute("xmjbxxcount", xmjbxxcount);
//		model.addAttribute("xmbdxxcount", xmbdxxcount);
//		model.addAttribute("xmbdfbxxcount", xmbdfbxxcount);
//		model.addAttribute("xmbdryxxcount", xmbdryxxcount);
//		model.addAttribute("jch_check_viewcount", jch_check_viewcount);
//		model.addAttribute("reportinfocount", reportinfocount);
//		model.addAttribute("corpuser_viewcount", corpuser_viewcount);
//	}
//
//	@Override
//	public void initWebinfo() {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		cacheUtil.clearModel("webInfo");
//		initWeb(cacheUtil, Constantval.CONSTANT_INDUSTRY_ONE);
//		organinfoService.findByOrgcode(Constantval.RoadOrgcode);
//	}
//
//	@Override
//	public void initWebinfoSy() {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		cacheUtil.clearModel("webInfosy");
//		initWeb(cacheUtil, Constantval.CONSTANT_INDUSTRY_TWO);
//		organinfoService.findByOrgcode(Constantval.RoadOrgcode);
//	}
//
//	private void initWeb(CacheUtil cacheUtil, String industry) {
//		List<Qydjxxweb> qydjxxs = null;
//		List<Qydjxxweb> sgqydjxxs = null;
//		List<Qydjxxweb> jlqydjxxs = null;
//		List<Qydjxxweb> sjqydjxxs = null;
//		List<Qydjxxweb> jsqydjxxs = null;
//		List<Xmjbxxweb> xmjbxxhighway = null;
//		List<Xmjbxxweb> xmjbxxlocalway = null;
//		List<Badunitweb> badunit = null;
//		List<Goodunitweb> goodunit = null;
//		List<Evalresult> evalresults1 = null;
//		List<Evalresult> evalresults2 = null;
//		List<Evalresult> evalresults3 = null;
//		List<Evalresult_jscorp> evalresults4 = null;
//		List<Evalresult_qx> evalresults_qx1 = null;
//		List<Evalresult_qx> evalresults_qx2 = null;
//		List<Evalresult_qx> evalresults_qx3 = null;
//		// List<Workinfo> workinfogongshi= null;
//		// List<Workinfo> workinfogonggao= null;
//		// List<Workinfo> workinfotongzhi= null;
//		// List<Workinfo> workinfoshiji= null;
//		// List<Workinfo> workinfoquxian= null;
//		// List<Lawinfo> lawbuwei= null;
//		// List<Lawinfo> lawfalv= null;
//		// List<Lawinfo> lawdifang= null;
//		Organinfo organinfo = null;
//		qydjxxs = qydjxxwebService.findQydjxxinfos(industry);
//		sgqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_THREE);
//		jlqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_FOUR);
//		sjqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_TWO);
//		jsqydjxxs = qydjxxwebService.findQydjxxinfos(industry, Constantval.CONSTANT_CORPTYPE_ONE);
//		xmjbxxhighway = xmjbxxwebService.findXmjbxxinfos(Constantval.CONSTANT_PROJECT_TYPE_ONE, industry);
//		xmjbxxlocalway = xmjbxxwebService.findXmjbxxinfos(Constantval.CONSTANT_PROJECT_TYPE_TWO, industry);
//		badunit = badunitwebService.findBadunit(industry);
//		for (Badunitweb badunit2 : badunit) {
//			badunit2.setWj(badwjwebService.find(badunit2.getDocument_id()));
//		}
//		// goodunit=goodunitwebService.findGoodunitweb(industry);
//		goodunit = goodunitwebService.findGoodunit1(industry);
//		for (Goodunitweb goodunit2 : goodunit) {
//			goodunit2.setWj(goodwjwebService.find(goodunit2.getDocument_id()));
//		}
//		evalresults1 = evalresultService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_THREE);
//		evalresults2 = evalresultService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_FOUR);
//		evalresults3 = evalresultService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_TWO);
//		evalresults4 = evalresult_jscorpService.findJsByCorp_type(industry);
//		evalresults_qx1 = evalresult_qxService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_THREE);
//		evalresults_qx2 = evalresult_qxService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_FOUR);
//		evalresults_qx3 = evalresult_qxService.findByCorp_type(industry, Constantval.CONSTANT_CORPTYPE_TWO);
//		// workinfogongshi=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_GONGSHI);
//		// workinfogonggao=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_GONGGAO);
//		// workinfotongzhi=workinfoService.findIndexByIndustrySort(industry,Constantval.WORKINFOSORT_TONGZHI);
//		// workinfoshiji=workinfoService.findIndexByIndustryShiji(industry);
//		// workinfoquxian=workinfoService.findIndexByIndustryQuxian(industry);
//		// lawbuwei=lawinfoService.findIndexByIndustryLawtype(industry,Constantval.LAWTYPE_BUWEI_GUIDING);
//		// lawfalv=lawinfoService.findIndexByIndustryLawtype(industry,Constantval.LAWTYPE_FALV_FAGEI);
//		// lawdifang=lawinfoService.findIndexByIndustryLawtype(industry,Constantval.LAWTYPE_DIFANG_GUIDING);
//		organinfo = organinfoService.findByOrgcode(Constantval.RoadOrgcode);
//		Cachemodel cachemodel = new Cachemodel();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("qydjxxs", qydjxxs);
//		map.put("sgqydjxxs", sgqydjxxs);
//		map.put("jlqydjxxs", jlqydjxxs);
//		map.put("sjqydjxxs", sjqydjxxs);
//		map.put("jsqydjxxs", jsqydjxxs);
//		map.put("xmjbxxhighway", xmjbxxhighway);
//		map.put("xmjbxxlocalway", xmjbxxlocalway);
//		map.put("evalresults1", evalresults1);
//		map.put("evalresults2", evalresults2);
//		map.put("evalresults3", evalresults3);
//		map.put("evalresults4", evalresults4);
//		map.put("evalresults_qx1", evalresults_qx1);
//		map.put("evalresults_qx2", evalresults_qx2);
//		map.put("evalresults_qx3", evalresults_qx3);
//		map.put("badunit", badunit);
//		map.put("goodunit", goodunit);
//		// map.put("workinfogongshi", workinfogongshi);
//		// map.put("workinfogonggao", workinfogonggao);
//		// map.put("workinfotongzhi", workinfotongzhi);
//		// map.put("workinfoshiji", workinfoshiji);
//		// map.put("workinfoquxian", workinfoquxian);
//		// map.put("lawbuwei", lawbuwei);
//		// map.put("lawfalv", lawfalv);
//		// map.put("lawdifang", lawdifang);
//		map.put("organinfo", organinfo);
//		cachemodel.setCacheMap(map);
//		cachemodel.setCdate(new Date());
//		cachemodel.setVdate(24);
//		if (Constantval.CONSTANT_INDUSTRY_ONE.equals(industry)) {
//			cacheUtil.addCache("webInfo", cachemodel);
//		} else {
//			cacheUtil.addCache("webInfosy", cachemodel);
//		}
//
//	}
//
//	private void initCredit() {
//		// CacheUtil cacheUtil=CacheUtil.newInstance();
//		// Map<String, Object> cacheMap=cacheUtil.getCache("webInfo");
//		// List<Qydjxxweb> qydjxxs = null;
//		/*
//		 * if(cacheMap==null){ qydjxxs =
//		 * qydjxxwebService.findQydjxxinfos(Constantval.CONSTANT_INDUSTRY_ONE); }else {
//		 * qydjxxs = (List<Qydjxxweb>) cacheMap.get("qydjxxs"); }
//		 */
//		// 信用分值计算
//		if (Constantval.CONSTANT_INDUSTRY_ONE.equals(Constantval.CONSTANT_INDUSTRY_ONE)) {
//			// SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
//			List<Qydjxx> corps = qydjxxService.findCorpsByindustry(Constantval.CONSTANT_INDUSTRY_ONE);
//			for (Qydjxx qydjxx : corps) {
//				String[] corptypes = qydjxx.getCorp_type().split(",");
//				if (corptypes.length == 1) {
//					initCreditScore(qydjxx, corptypes[0], false);
//				} else {
//					for (String corptype : corptypes) {
//						initCreditScore(qydjxx, corptype, true);
//					}
//				}
//			}
//			/*
//			 * for (Qydjxxweb qydjxx : qydjxxs) { Creditscore
//			 * creditScore=creditscoreService.findByCorp(Constantval.CONSTANT_INDUSTRY_ONE,
//			 * qydjxx.getCorp_id()); qydjxx.setCreditscore(creditScore); }
//			 */
//		}
//	}
//
//	@Override
//	public void statinitInfo() {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		cacheUtil.clearModel("info");
//		initInfo(cacheUtil, Constantval.CONSTANT_INDUSTRY_ONE);
//	}
//
//	@Override
//	public void statinitInfoSy() {
//		CacheUtil cacheUtil = CacheUtil.newInstance();
//		cacheUtil.clearModel("infosy");
//		initInfo(cacheUtil, Constantval.CONSTANT_INDUSTRY_TWO);
//	}
//
//	/**
//	 * 定时器方法
//	 */
//	private void initInfo(CacheUtil cacheUtil, String industry) {
//		Object corpcount = initIndexService.corpcount(industry);
//		Object projectcount = initIndexService.projectcount(industry);
//		Object period_code = initIndexService.findEvaling(industry);
//		Object evalCorpcount = initIndexService.evalCorpcount(industry, (String) period_code);
//		Object evalProjectcount = initIndexService.evalProjectcount(industry, (String) period_code);
//		Object corp_js = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_ONE + "%");
//		Object corp_sj = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_TWO + "%");
//		Object corp_sg = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_THREE + "%");
//		Object corp_jl = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_FOUR + "%");
//		Object corp_jc = initIndexService.countByCorptype(industry, "%" + Constantval.CONSTANT_CORPTYPE_FIVE + "%");
//		List<Object> corpGrade = initIndexService.corpByGrade(industry, (String) period_code);
//		List<Object> projects = initIndexService.projectByType(industry);
//		List<Object> projectsOrg = initIndexService.projectByOrg(industry);
//		Cachemodel cachemodel = new Cachemodel();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("corpcount", corpcount);
//		map.put("projectcount", projectcount);
//		map.put("period_code", period_code);
//		map.put("evalCorpcount", evalCorpcount);
//		map.put("evalProjectcount", evalProjectcount);
//		map.put("corp_js", corp_js);
//		map.put("corp_sj", corp_sj);
//		map.put("corp_sg", corp_sg);
//		map.put("corp_jl", corp_jl);
//		map.put("corp_jc", corp_jc);
//		map.put("corpGrade", corpGrade);
//		map.put("projects", projects);
//		map.put("projectsOrg", projectsOrg);
//		cachemodel.setCacheMap(map);
//		cachemodel.setCdate(new Date());
//		cachemodel.setVdate(24);
//		if (Constantval.CONSTANT_INDUSTRY_ONE.equals(industry)) {
//			cacheUtil.addCache("info", cachemodel);
//		} else {
//			cacheUtil.addCache("infosy", cachemodel);
//		}
//
//	}
//
//	@Override
//	public void deleteCorpCreditscore() {
//		creditscoreJCService.deleteAll();
//		credittypeScoreService.deleteAll();
//		creditscoreService.deleteAll();
//	}
//
//	@Override
//	@Transactional
//	public void initCorpCreditscore() {
//		// 信用分值计算
//		List<Qydjxx> corps = qydjxxService.findCorpsByindustry(Constantval.CONSTANT_INDUSTRY_ONE);
////		List<Qydjxx> corps = new ArrayList<>();
////		Qydjxx qydjxx2=qydjxxService.find("A56410C41B6E44AEB52052FCCD03009E");
////		Qydjxx qydjxx3=qydjxxService.find("0fdc4f95005d4c51ad85c9651a11ff0e");
////		corps.add(qydjxx2);
////		corps.add(qydjxx3);
//		int i=0;
//		for (Qydjxx qydjxx : corps) {
//			i++;
////			System.out.println("i="+i+",sum="+corps.size());
//			String[] corptypes = qydjxx.getCorp_type().split(",");
//			if (corptypes.length == 1) {
//				initCreditScore(qydjxx, corptypes[0], false);
//			} else {
//				for (String corptype : corptypes) {
//					initCreditScore(qydjxx, corptype, true);
//				}
//			}
//
//		}
//	}
//
//	@Override
//	public void initCorpCreditscoreHis() {
//		// 信用分值计算
//		List<Creditscore> creditscores = creditscoreService.findAll(Constantval.CONSTANT_INDUSTRY_ONE);
//		for (Creditscore creditscore : creditscores) {
//			CreditscoreHis creditScorehis = creditscoreHisService.findByCorp(Constantval.CONSTANT_INDUSTRY_ONE,
//					creditscore.getCorp_id(), creditscore.getCorptype());
//			boolean isinert = false;
//			if (creditScorehis == null) {
//				isinert = true;
//			} else {
//				BigDecimal data1 = new BigDecimal(creditScorehis.getScore());
//				BigDecimal data2 = new BigDecimal(creditscore.getScore());
//				if (data1.compareTo(data2) != 0) {
//					isinert = true;
//				}
//			}
//			if (isinert) {
//				creditScorehis = new CreditscoreHis();
//				creditScorehis.setHisscoreid(KeyUtils.getKey());
//				creditScorehis.setIndustry(Constantval.CONSTANT_INDUSTRY_ONE);
//				creditScorehis.setCorp_id(creditscore.getCorp_id());
//				creditScorehis.setCorp_name(creditscore.getCorp_name());
//				creditScorehis.setEvalyear(creditscore.getEvalyear());
//				creditScorehis.setScore(creditscore.getScore());
//				creditScorehis.setGradetype(creditscore.getGradetype());
//				creditScorehis.setCorptype(creditscore.getCorptype());
//				creditScorehis.setScoredate(new Date());
//				creditscoreHisService.save(creditScorehis);
//				List<CredittypeScore> credittypeScores = credittypeScoreService.findByScoreid(creditscore.getScoreid());
//				for (CredittypeScore credittypeScore : credittypeScores) {
//					CredittypeScoreHis info = new CredittypeScoreHis();
//					info.setId(KeyUtils.getKey());
//					info.setHisscoreid(creditScorehis.getHisscoreid());
//					info.setCredittypeid(credittypeScore.getCredittypeid());
//					info.setScorecount(credittypeScore.getScorecount());
//					info.setScoregrade(credittypeScore.getScoregrade());
//					credittypeScoreHisService.save(info);
//				}
//			}
//
//		}
//	}
//
//	private void initCreditScore(Qydjxx qydjxx, String corptype, Boolean flag) {
//		if(!"5".equals(corptype)) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//			Creditscore creditScore = creditscoreService.findByCorp(Constantval.CONSTANT_INDUSTRY_ONE, qydjxx.getCorp_id(),
//					corptype);
////			if (creditScore == null && !Constantval.CONSTANT_CORPTYPE_ONE.equals(qydjxx.getCorp_type())) {
//			if (creditScore == null) {
//				creditScore = new Creditscore();
//				creditScore.setScoreid(KeyUtils.getKey());
//				creditScore.setIndustry(Constantval.CONSTANT_INDUSTRY_ONE);
//				creditScore.setCorp_id(qydjxx.getCorp_id());
//				creditScore.setCorp_name(qydjxx.getCorp_name());
//				creditScore.setEvalyear(sdf.format(new Date()));
//				creditScore.setCorptype(corptype);
//				Object[] result = getCreditScore(qydjxx, creditScore, corptype, flag);
//				creditScore.setScore((Double) result[1]);
//				creditScore.setGradetype((String) result[0]);
//				creditScore.setCdate(new Date());
//				creditScore.setUdate(new Date());
//				creditscoreService.save(creditScore);
//			}
//		}
//
//	}
//
//	private Object[] getCreditScore(Qydjxx qydjxx, Creditscore creditScore, String corptype, Boolean flag) {
//		String corpid = qydjxx.getCorp_id();
//		// 信用分值
//		Double gradescore = 0.0;
//		// 动态奖惩分值
//		Double jcscore = 0.0;
//		Object[] result = new Object[2];
//		Object[] data = credittypeService.initGradeScore(qydjxx, creditScore, gradescore, corptype, flag);
//		result[0] = data[1];
//		gradescore = (Double) data[0];
//		// 奖励记录 1 国家级奖项 2 重庆市级奖项 3通报表彰4.通报表扬5.全国公路守信典型
//		List<CredittypeScore> typeScores = new ArrayList<CredittypeScore>();
//		List<Object[]> goods = goodunitwebService.findListByCorp(corpid, Constantval.CONSTANT_INDUSTRY_ONE, corptype);
//		if (goods != null && goods.size() > 0) {
//			for (Object[] good : goods) {
//				Credittype credittypegood =null;
//				Goodunitweb goodunitweb=goodunitwebService.find((String) good[2]);
//				if(null!=goodunitweb) {
//					if(StringUtils.isNotBlank(goodunitweb.getGood_type())) {
//						if("5".equals(goodunitweb.getGood_type())) {
//							if(credittypegood == null) {
//								credittypegood = credittypeService.getCredittypeBygood((String) good[0], "守信典型",
//										creditScore.getScoreid());
//							}
//						}else {
//							 credittypegood = credittypeService.getCredittypeBygood((String) good[0], "通报表",
//									creditScore.getScoreid());
//						}
//					}
//				}
//				if (credittypegood != null) {
//					credittypeService.initCredittypeScore(creditScore, credittypegood.getCredittypeid(), null,
//							(String) good[2], "1", typeScores);
//					jcscore = (jcscore * 10 + credittypegood.getScore() * 10) / 10;
//				}
//			}
//		}
//
//		List<Object[]> bads = badunitwebService.findListByCorp(corpid, Constantval.CONSTANT_INDUSTRY_ONE, corptype);
//		if (bads != null && bads.size() > 0) {
//			for (Object[] bad : bads) {
//				String issuedunit = (String) bad[0];
//				if (StringUtils.isBlank(issuedunit)) {
//					issuedunit = (String) bad[7];
//				}
//				Credittype credittypebad = credittypeService.getCredittypeBybad(issuedunit, (String) bad[3],
//						(String) bad[1], (String) bad[5], creditScore.getScoreid());
//				if (credittypebad != null) {
//					credittypeService.initCredittypeScore(creditScore, credittypebad.getCredittypeid(), null,
//							(String) bad[6], "2", typeScores);
//					jcscore = (jcscore * 10 + credittypebad.getScore() * 10) / 10;
//				}
//			}
//		}
////		//获取是否有由守信典型生成 奖励信息
////		List<Object[]> goodss = goodunitwebService.findListByCorpAndGood_type(corpid, Constantval.CONSTANT_INDUSTRY_ONE, corptype,"5");
////		if(null==goodss) {
////			// 守信典型企业名录
////			Calendar cal = Calendar.getInstance();
////			Faithfulcorp corp = faithfulcorpService.findByCorp(qydjxx.getCorp_id(), String.valueOf(cal.get(Calendar.YEAR)),
////					cal.getTime(), Constantval.CONSTANT_INDUSTRY_ONE, corptype);
////			if (corp != null) {
////				Credittype credittype = credittypeService.getCredittype(Constantval.CONSTANT_INDUSTRY_ONE);
////				if (credittype != null) {
////					credittypeService.initCredittypeScore(creditScore, credittype.getCredittypeid(), null,
////							corp.getFaithfulcorpid(), "1", typeScores);
////					jcscore = (jcscore * 10 + credittype.getScore() * 10) / 10;
////				}
////			}
////		}
//
//
//		result[1] = gradescore + jcscore;
//		return result;
//	}
//
//	@Override
//	public void corpCreditscore(Qydjxx corp) {
//		String corp_name=corp.getCorp_name();
//		String creditcode=corp.getCreditcode();
//		List<Tevalmanageinfo> tevalmanageinfolist=null;
//		if(StringUtils.isNotBlank(corp_name)&&StringUtils.isNotBlank(creditcode)) {
//				if (creditcode.length() == 18) {
//					tevalmanageinfolist = tevalmanageinfoService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(8, 16), Constantval.CONSTANT_INDUSTRY_ONE);
//				} else if (creditcode.length() == 10) {
//					tevalmanageinfolist = tevalmanageinfoService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(0, 8), Constantval.CONSTANT_INDUSTRY_ONE);
//				}
//		}
//		if(null!=tevalmanageinfolist&&tevalmanageinfolist.size()>0&&null!=corp&&StringUtils.isNotBlank(corp.getCorp_id())) {
//			for(Tevalmanageinfo tevalmanageinfo:tevalmanageinfolist) {
//				tevalmanageinfo.setCardid(corp.getCorp_id());
//				tevalmanageinfoService.save(tevalmanageinfo);
//			}
//		}
//		List<Evalresult> evalresultlist=null;
//		if(StringUtils.isNotBlank(corp_name)&&StringUtils.isNotBlank(creditcode)) {
//				if (creditcode.length() == 18) {
//					evalresultlist = evalresultService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(8, 16), Constantval.CONSTANT_INDUSTRY_ONE);
//				} else if (creditcode.length() == 10) {
//					evalresultlist = evalresultService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(0, 8), Constantval.CONSTANT_INDUSTRY_ONE);
//				}
//		}
//		List<Companyrank> companyranklist=null;
//		if(StringUtils.isNotBlank(corp_name)&&StringUtils.isNotBlank(creditcode)) {
//			if (creditcode.length() == 18) {
//				companyranklist = companyrankService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(8, 16), Constantval.CONSTANT_INDUSTRY_ONE);
//			} else if (creditcode.length() == 10) {
//				companyranklist = companyrankService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(0, 8), Constantval.CONSTANT_INDUSTRY_ONE);
//			}
//		}
//		if(null!=companyranklist&&companyranklist.size()>0&&null!=corp&&StringUtils.isNotBlank(corp.getCorp_id())) {
//			for(Companyrank companyrank:companyranklist) {
//				companyrank.setCorp_id(corp.getCorp_id());
//				companyrankService.save(companyrank);
//			}
//		}
//		if(null!=evalresultlist&&evalresultlist.size()>0&&null!=corp&&StringUtils.isNotBlank(corp.getCorp_id())) {
//			for(Evalresult evalresult:evalresultlist) {
//				evalresult.setCorp_id(corp.getCorp_id());
//				evalresultService.save(evalresult);
//			}
//			String dqevalyear1=companydateevalService.findEvalyear2();
//			String dtevalyear1=companyrankService.findEvalyear2();
//			if(StringUtils.isNotBlank(dqevalyear1)&&StringUtils.isNotBlank(dtevalyear1)) {
//				int dqevalyear =Integer.parseInt(dqevalyear1);//获取数据库中的定期评价最大的评价年度
//				int dtevalyear =Integer.parseInt(dtevalyear1);//获取数据库中的动态评价最大的评价年度
//				if(dqevalyear>0&&dtevalyear>0) {
//					//获取该从业单位的动态评价年
//					List <String[]> arrlist=companyrankService.findbycorp_id4(corp.getCorp_id(),Constantval.CONSTANT_INDUSTRY_ONE);
//					if(null!=arrlist&&arrlist.size()>0) {
//						initEvalresult(arrlist,dqevalyear,dtevalyear);
//					}
//				}
//			}
//		}
//		String[] corptypes = corp.getCorp_type().split(",");
//		if (corptypes.length == 1) {
//			initCreditScore(corp, corptypes[0], false);
//		} else {
//			for (String corptype : corptypes) {
//				initCreditScore(corp, corptype, true);
//			}
//		}
//	}
//
//	@Override
//	public void corpCreditscore2(Qydjxx qydjxx,String old_corp_type, String new_corp_type) {
//		//匹配信用典型，计算信用分值
//		Calendar cal = Calendar.getInstance();
//		Faithfulcorp faithfulcorp=faithfulcorpService.findbyCreditcode3(qydjxx.getCorp_name(), qydjxx.getCorp_use_name(), qydjxx.getCreditcode(),Constantval.CONSTANT_INDUSTRY_ONE);
//		if(faithfulcorp!=null) {
//			goodunitService.creatgoodunit(qydjxx ,faithfulcorp );	//增加奖励记录
//		}
//		if(StringUtils.isNotBlank(old_corp_type)&&StringUtils.isNotBlank(new_corp_type)) {
//			String[] old_type=old_corp_type.split(",");
//			String[] new_type=new_corp_type.split(",");
//			String corp="";
//			String a;
//			boolean result;
//			for (int i = 0; i < new_type.length; i++) {
//	            a = new_type[i];
//	            result = false;
//	            for (int j = 0; j < old_type.length; j++) {   //遍历查找
//	                if (old_type[j] .equals(a) ) {
//	                    result = true;
//	                    break;
//	                }
//	            }
//	            if (!result) {
//	            	if(StringUtils.isBlank(corp)) {
//	            		corp=a;
//	            	}else {
//	            		corp=corp+","+a;
//	            	}
//
//	            }
//	        }
//			if(StringUtils.isNotBlank(corp)) {
//				String[] corptypes=corp.split(",");
//				for (String corptype : corptypes) {
//					initCreditScore(qydjxx, corptype, true);
//				}
//			}
//		}
//	}
//
////	触发规则：
////	①.从业企业按企业类型不同，分别计算信用分值；（如一家企业同时是建设管理单位，施工企业，则计算信用分值时根据奖惩记录，信用评价结果，守信典型名录中的企业类型分别计算该单位两个企业类型的信用分值；）
////	②.重庆市交通局、区县交通局、重庆市公路局、质监局奖惩记录发布、撤销发布均触发计算。注意：奖惩记录有效期到期后自动撤销发布时需触发计算；从业单位自行填报的奖惩记录发布时不触发计算;
////	③.重庆市信用评价结果发布时触发计算，企业自行填报的全国或注册地信用评价结果审核发布、撤销发布时触发计算；
////	④.全国守信典型企业名录导入与系统中企业匹配成功后时触发计算；注意：守信典型名录有效期，直到下一年度守信典型企业名录导入前均有效。
////	⑤.所有在系统中注册的企业，只要填报了基本信息，则触发第一次信用分值计算，注此时仅提供在后台信用分值查询中查询；
////	3）.信用分值发布规则：
////	①.企业在首页上发布，则信用分值在首页发布；（不同企业的发布规则：建设管理单位基本信息发布，则在首页发布；施工，监理，设计企业基本信息和资质信息同时发布，则在首页发布；其他从业单位，基本信息发布则在首页发布；）
////	②.所有在系统中注册的企业，只要填报了基本信息，则触发信用分值计算，所有信用分值的变动都可以在信用分值查询中查询到历史记录；
//	@Override
//	public void initCredit(String corp_id ) {
//		Qydjxx qydjxx=qydjxxService.find(corp_id);
//		if(null!=qydjxx) {
//			//删除
//			deleteCorpCreditscore(corp_id);
//			String dqevalyear1=companydateevalService.findEvalyear2();
//			String dtevalyear1=companyrankService.findEvalyear2();
//			if(StringUtils.isNotBlank(dqevalyear1)&&StringUtils.isNotBlank(dtevalyear1)) {
//				int dqevalyear =Integer.parseInt(dqevalyear1);//获取数据库中的定期评价最大的评价年度
//				int dtevalyear =Integer.parseInt(dtevalyear1);//获取数据库中的动态评价最大的评价年度
//				if(dqevalyear>0&&dtevalyear>0) {
//					//获取该年度中所有的企业arr[0] corp_id,arr[1]  corp_type
//					List <String[]> arrlist=companyrankService.findbycorp_id4(corp_id,Constantval.CONSTANT_INDUSTRY_ONE);
//					if(null!=arrlist&&arrlist.size()>0) {
//						initEvalresult(arrlist,dqevalyear,dtevalyear);
//
//					}
//				}
//			}
//			initCorpCreditscore(corp_id);
//			initCorpCreditscoreHis(corp_id);
//		}
//
//	}
//
//
//
//
//
//	private void deleteCorpCreditscore(String corp_id) {
//		List<Creditscore> creditScorelist = creditscoreService.findByCorp_id(Constantval.CONSTANT_INDUSTRY_ONE,
//				corp_id);//信用分值
//
//		CredittypeScore credittypescore = null;//信用类型分次关联
//		Creditscore creditScore = null;
//		List<CreditscoreJC> creditscoreJClist = null;//信用分值奖惩关联
//		if (null != creditScorelist && creditScorelist.size() > 0) {
//			for (int i = 0; i < creditScorelist.size(); i++) {
//				creditScore = creditScorelist.get(i);
//				credittypescore = credittypeScoreService.findByGrade(creditScore.getScoreid());
//				if (null != credittypescore) {
//					creditscoreJClist = creditscoreJCService.findByCredittypeid(credittypescore.getId());
//					if (null != creditscoreJClist&&creditscoreJClist.size()>0) {
//						for(CreditscoreJC creditscorejc:creditscoreJClist) {
//							creditscoreJCService.delete(creditscorejc);
//						}
//					}
//				}
//				credittypeScoreService.deleteByScore_id(creditScore.getScoreid());
//			}
//		}
//		creditscoreService.deleteByCorp_id(corp_id);
//	}
//
//	private void initCorpCreditscore(String corp_id) {
//		Qydjxx qydjxx = qydjxxService.find(corp_id);
//		if (null != qydjxx) {
//			String industry = StringUtils.isBlank(qydjxx.getIndustry()) ? "" : qydjxx.getIndustry();
//			if ("1".equals(industry)||"3".equals(industry)) {
//				String[] corptypes = qydjxx.getCorp_type().split(",");
//				if (corptypes.length == 1) {
//					initCreditScore(qydjxx, corptypes[0], false);
//				} else {
//					for (String corptype : corptypes) {
//						initCreditScore(qydjxx, corptype, true);
//					}
//				}
//			}
//
//		}
//	}
//
//	private void initCorpCreditscoreHis(String corp_id) {
//		// 信用分值计算
//		List<Creditscore> creditscores = creditscoreService.findByCorp_id(Constantval.CONSTANT_INDUSTRY_ONE, corp_id);
//		for (Creditscore creditscore : creditscores) {
//			CreditscoreHis creditScorehis = creditscoreHisService.findByCorp(Constantval.CONSTANT_INDUSTRY_ONE,
//					creditscore.getCorp_id(), creditscore.getCorptype());
//			boolean isinert = false;
//			if (creditScorehis == null) {
//				isinert = true;
//			} else {
//				BigDecimal data1 = new BigDecimal(creditScorehis.getScore());
//				BigDecimal data2 = new BigDecimal(creditscore.getScore());
//				if (data1.compareTo(data2) != 0) {
//					isinert = true;
//				}
//			}
//			if (isinert) {
//				creditScorehis = new CreditscoreHis();
//				creditScorehis.setHisscoreid(KeyUtils.getKey());
//				creditScorehis.setIndustry(Constantval.CONSTANT_INDUSTRY_ONE);
//				creditScorehis.setCorp_id(creditscore.getCorp_id());
//				creditScorehis.setCorp_name(creditscore.getCorp_name());
//				creditScorehis.setEvalyear(creditscore.getEvalyear());
//				creditScorehis.setScore(creditscore.getScore());
//				creditScorehis.setGradetype(creditscore.getGradetype());
//				creditScorehis.setCorptype(creditscore.getCorptype());
//				creditScorehis.setScoredate(new Date());
//				creditscoreHisService.save(creditScorehis);
//				List<CredittypeScore> credittypeScores = credittypeScoreService.findByScoreid(creditscore.getScoreid());
//				for (CredittypeScore credittypeScore : credittypeScores) {
//					CredittypeScoreHis info = new CredittypeScoreHis();
//					info.setId(KeyUtils.getKey());
//					info.setHisscoreid(creditScorehis.getHisscoreid());
//					info.setCredittypeid(credittypeScore.getCredittypeid());
//					info.setScorecount(credittypeScore.getScorecount());
//					info.setScoregrade(credittypeScore.getScoregrade());
//					credittypeScoreHisService.save(info);
//				}
//			}
//
//		}
//	}
//
//	@Override
//	public void initCredit2(String corp_id ) {
//		if(StringUtils.isNotBlank(corp_id)) {
//			Qydjxx corp=qydjxxService.find(corp_id);
//			String corp_name=corp.getCorp_name();
//			String creditcode=corp.getCreditcode();
//			List<Evalresult> list=null;
//			if(StringUtils.isNotBlank(corp_name)&&StringUtils.isNotBlank(creditcode)) {
//				if(null==list) {
//					if (creditcode.length() == 18) {
//						list = evalresultService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(8, 16), Constantval.CONSTANT_INDUSTRY_ONE);
//					} else if (creditcode.length() == 10) {
//						list = evalresultService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(0, 8), Constantval.CONSTANT_INDUSTRY_ONE);
//					}
//				}
//			}
//			List<Companyrank> companyranklist=null;
//			if(StringUtils.isNotBlank(corp_name)&&StringUtils.isNotBlank(creditcode)) {
//				if (creditcode.length() == 18) {
//					companyranklist = companyrankService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(8, 16), Constantval.CONSTANT_INDUSTRY_ONE);
//				} else if (creditcode.length() == 10) {
//					companyranklist = companyrankService.findByCreditcodeCorp_name(corp_name,corp.getCorp_use_name(),creditcode.trim().substring(0, 8), Constantval.CONSTANT_INDUSTRY_ONE);
//				}
//			}
//			if(null!=companyranklist&&companyranklist.size()>0&&null!=corp&&StringUtils.isNotBlank(corp.getCorp_id())) {
//				for(Companyrank companyrank:companyranklist) {
//					companyrank.setCorp_id(corp.getCorp_id());
//					companyrankService.save(companyrank);
//				}
//			}
//			if(null!=list&&list.size()>0&&null!=corp&&StringUtils.isNotBlank(corp.getCorp_id())) {
//				for(Evalresult evalresult:list) {
//					evalresult.setCorp_id(corp.getCorp_id());
//					evalresultService.save(evalresult);
//				}
//				String dqevalyear1=companydateevalService.findEvalyear2();
//				String dtevalyear1=companyrankService.findEvalyear2();
//				if(StringUtils.isNotBlank(dqevalyear1)&&StringUtils.isNotBlank(dtevalyear1)) {
//					int dqevalyear =Integer.parseInt(dqevalyear1);//获取数据库中的定期评价最大的评价年度
//					int dtevalyear =Integer.parseInt(dtevalyear1);//获取数据库中的动态评价最大的评价年度
//					if(dqevalyear>0&&dtevalyear>0) {
//						//获取该从业单位的动态评价年
//						List <String[]> arrlist=companyrankService.findbycorp_id4(corp.getCorp_id(),Constantval.CONSTANT_INDUSTRY_ONE);
//						if(null!=arrlist&&arrlist.size()>0) {
//							initEvalresult(arrlist,dqevalyear,dtevalyear);
//						}
//					}
//				}
//			}
//			if(null!=corp) {
//				//删除
//				String dqevalyear1=companydateevalService.findEvalyear2();
//				String dtevalyear1=companyrankService.findEvalyear2();
//				if(StringUtils.isNotBlank(dqevalyear1)&&StringUtils.isNotBlank(dtevalyear1)) {
//					int dqevalyear =Integer.parseInt(dqevalyear1);//获取数据库中的定期评价最大的评价年度
//					int dtevalyear =Integer.parseInt(dtevalyear1);//获取数据库中的动态评价最大的评价年度
//					if(dqevalyear>0&&dtevalyear>0) {
//						//获取该从业单位的动态评价年
//						List <String[]> arrlist=companyrankService.findbycorp_id4(corp_id,Constantval.CONSTANT_INDUSTRY_ONE);
//						if(null!=arrlist&&arrlist.size()>0) {
//							initEvalresult(arrlist,dqevalyear,dtevalyear);
//						}
//					}
//				}
//				deleteCorpCreditscore(corp_id);
//				initCorpCreditscore(corp_id);
//				initCorpCreditscoreHis(corp_id);
//			}
//		}
//
//	}
//
//
//
//
//	//获取当前定期评价年度，获取上上一年及以后的所有动态评价里的企业信息，遍历企业信息
//	//1.获取下下年度的动态评价，取最低（无定期评价）
//	//2.获取下年度的动态评价，取最低（无定期评价）
//	//3.获取本年度的动态评价和定期评价。取最低等级，如果无定期和动态。则去上一年度的定期评价和动态评价延长一年
//	@Override
//	public void initevaluationresult() {
//		String dqevalyear1=companydateevalService.findEvalyear2();
//		String dtevalyear1=companyrankService.findEvalyear2();
//		if(StringUtils.isNotBlank(dqevalyear1)&&StringUtils.isNotBlank(dtevalyear1)) {
//			int dqevalyear =Integer.parseInt(dqevalyear1);//获取数据库中的定期评价最大的评价年度
//			int dtevalyear =Integer.parseInt(dtevalyear1);//获取数据库中的动态评价最大的评价年度
//			if(dqevalyear>0&&dtevalyear>0) {
//				//获取该年度中所有的企业arr[0] corp_id,arr[1]  corp_type
//				List <String[]> arrlist=companyrankService.findAll2(( dqevalyear -2)+"",Constantval.CONSTANT_INDUSTRY_ONE);
//				if(null!=arrlist&&arrlist.size()>0) {
//					initEvalresult(arrlist,dqevalyear,dtevalyear);
//
//				}
//			}
//		}
//	}
//
//	private void initEvalresult(List <String[]> arrlist,int dqevalyear,int dtevalyear) {
//		String[] arr=new String[2];
//		Evalresult evalresult=null;
//		Evalresult newevalresult2=null;//相对于定期评价的评价年度的下下年度的评价结果，比如定期为2018年，2020.1.1发布的是2020年度的评价结果
//		Evalresult newevalresult1=null;//相对于定期评价的评价年度的下年度的评价结果，比如定期为2018年，2020.1.1发布的是2020年度的评价结果
//		Evalresult oldevalresult=null;//上一年度的评价结果
//		Companydateeval companydateeval=null;//当前年度的定期评价
//		Companydateeval oldcompanydateeval=null;//上年度的定期评价
//		List<Companyrank> newcompanyranklist1=null;//相对于定期评价的评价年度的下年度的动态评价，比如定期为2018年，2020.1.1发布的是2020年度的动态评价
//		List<Companyrank> newcompanyranklist2=null;//相对于定期评价的评价年度的下下年度的动态评价，比如定期为2018年，2020.1.1发布的是2020年度的动态评价
//		List<Companyrank> companyranklist=null;
//		List<Companyrank> oldcompanyranklist=null;
//		Date issuetime=null;//定期评价发布时间
//		Date issuetime2=null;//动态评价生效时间
////		Double dqsum_score=null;//定期分数
//		Companyrank companyrank2=null;
//
//		for(Object obj:arrlist) {
//			System.arraycopy(obj, 0, arr, 0, 2);
//			if(StringUtils.isNotBlank(arr[0])&&StringUtils.isNotBlank(arr[1])) {
//				//获取该企业的下下年度的评价结果
//				newevalresult2=evalresultService.findbycorp_idandcorp_type(arr[0], arr[1] ,(dqevalyear+2)+"",Constantval.CONSTANT_INDUSTRY_ONE);
//				if(null!=newevalresult2) {
//					if(StringUtils.isNotBlank(newevalresult2.getIsrank())) {
//						if("1".equals(newevalresult2.getIsrank())) {//是否是直接定级
//							newcompanyranklist2=companyrankService.findByCorp2(arr[0],arr[1],  (dqevalyear+2)+"", Constantval.CONSTANT_INDUSTRY_ONE);//下下年度的动态评价
//							if(null!=newcompanyranklist2&&newcompanyranklist2.size()>0) {//获取动态评价中。等级最低的等级
//								String minG="";//最低等级
//								if(newcompanyranklist2.size()>1) {
//									List<String> listG=new ArrayList<String>();
//									for(Companyrank companyrank:newcompanyranklist2) {
//										if(StringUtils.isNotBlank(companyrank.getGrade())) {
//											listG.add(companyrank.getGrade());
//										}
//									}
//									if(listG.size()>0) {
//										 minG=Collections.max(listG);//取出最低等级的
//									}
//								}else {
//									if(null!=newcompanyranklist2.get(0)) {
//										minG= StringUtils.isBlank(newcompanyranklist2.get(0).getGrade())?"":newcompanyranklist2.get(0).getGrade();
//									}
//
//								}
//								newevalresult2.setGrade(minG);
//								newevalresult2.setIsrank("1");
//								newevalresult2.setBid_score(null);
//								newevalresult2.setDo_score(null);
//								newevalresult2.setOther_score(null);
//								newevalresult2.setGood_score(null);
//								newevalresult2.setSum_score(null);
//								evalresultService.save(newevalresult2);
//							}
//						}
//					}
//				}
//				newevalresult1=evalresultService.findbycorp_idandcorp_type(arr[0], arr[1] ,(dqevalyear+1)+"",Constantval.CONSTANT_INDUSTRY_ONE);//下年度的评价结果
//				if(null!=newevalresult1) {
//					if(StringUtils.isNotBlank(newevalresult1.getIsrank())) {
//						if("1".equals(newevalresult1.getIsrank())) {//是否是直接定级
//							newcompanyranklist1=companyrankService.findByCorp2(arr[0],arr[1],  (dqevalyear+1)+"", Constantval.CONSTANT_INDUSTRY_ONE);//下年度的动态评价
//							if(null!=newcompanyranklist1&&newcompanyranklist1.size()>0) {//获取动态评价中。等级最低的等级
//								String minG="";//最低等级
//								if(newcompanyranklist1.size()>1) {
//									List<String> listG=new ArrayList<String>();
//									for(Companyrank companyrank:newcompanyranklist1) {
//										if(StringUtils.isNotBlank(companyrank.getGrade())) {
//											listG.add(companyrank.getGrade());
//										}
//									}
//									if(listG.size()>0) {
//										 minG=Collections.max(listG);//取出最低等级的
//									}
//								}else {
//									if(null!=newcompanyranklist1.get(0)) {
//										minG= StringUtils.isBlank(newcompanyranklist1.get(0).getGrade())?"":newcompanyranklist1.get(0).getGrade();
//									}
//
//								}
//								newevalresult1.setGrade(minG);
//								newevalresult1.setIsrank("1");
//								newevalresult1.setBid_score(null);
//								newevalresult1.setDo_score(null);
//								newevalresult1.setOther_score(null);
//								newevalresult1.setGood_score(null);
//								newevalresult1.setSum_score(null);
//								evalresultService.save(newevalresult1);
//
//							}
//						}
//					}
//				}
//				evalresult=evalresultService.findbycorp_idandcorp_type(arr[0], arr[1] ,dqevalyear+"",Constantval.CONSTANT_INDUSTRY_ONE);//本年度的评价结果
//				if(null!=evalresult) {
//					String mingrade="";//最低等级
//					String dtgrade="";//动态最低等级
//					String dqgrade="";//定期最低等级
//					//获取该年度定期评价
//					companydateeval=companydateevalService.findResult3(arr[0], arr[1], dqevalyear+"" , Constantval.CONSTANT_INDUSTRY_ONE);
//					companyranklist=companyrankService.findByCorp2(arr[0],arr[1],  dqevalyear+"", Constantval.CONSTANT_INDUSTRY_ONE);//本年度的动态评价
////					dqsum_score=(null==companydateeval.getSum_score()?0.0:companydateeval.getSum_score());//定期分数
//					if(null!=companyranklist&&companyranklist.size()>0) {//获取动态评价中。等级最低的等级
//						if(companyranklist.size()>1) {
//							List<String> listG=new ArrayList<String>();
//							for(Companyrank companyrank:companyranklist) {
//								if(StringUtils.isNotBlank(companyrank.getGrade())) {
//									listG.add(companyrank.getGrade());
//								}
//							}
//							if(listG.size()>0) {
//								dtgrade=Collections.max(listG);//取出最低等级的
//							}
////							for(Companyrank companyrank:companyranklist) {
////								if(StringUtils.isNotBlank(companyrank.getGrade())) {
////									 if(dtgrade.equals(companyrank.getGrade())) {
////										 companyrank2= companyrank;
////										 break;
////									 }
////								}
////							}
//						}else {
//							if(null!=companyranklist.get(0)) {
//								dtgrade= StringUtils.isBlank(companyranklist.get(0).getGrade())?"":companyranklist.get(0).getGrade();
//							}
//
//						}
//					}
//					if(null!=companydateeval) {
//						dqgrade=StringUtils.isBlank(companydateeval.getGrade())?"":companydateeval.getGrade();
//					}
//
//					if(StringUtils.isNotBlank(dtgrade)&&StringUtils.isNotBlank(dqgrade)) {//动态和定期全有
//						if(null!=companydateeval) {
//							if(dqgrade.compareTo(dtgrade)<0) {//如果定期等级大于动态等级
//								evalresult.setGrade(dtgrade);
//								evalresult.setIsrank("1");
//								evalresult.setBid_score(null);
//								evalresult.setDo_score(null);
//								evalresult.setOther_score(null);
//								evalresult.setGood_score(null);
//								evalresult.setSum_score(null);
//							}else if(dqgrade.compareTo(dtgrade)>0) {
//								evalresult.setGrade(dqgrade);
//								if(null!=companydateeval.getBid_score()) {
//									evalresult.setBid_score( companydateeval.getBid_score());
//								}
//								if(null!=companydateeval.getDo_score()) {
//									evalresult.setDo_score( companydateeval.getDo_score());
//								}
//								if(null!=companydateeval.getOther_score()) {
//									evalresult.setOther_score( companydateeval.getOther_score());
//								}
//								if(null!=companydateeval.getGood_score()) {
//									evalresult.setGood_score( companydateeval.getGood_score());
//								}
//								if(null!=companydateeval.getSum_score()) {
//									evalresult.setSum_score( companydateeval.getSum_score());
//								}
//								evalresult.setIsrank("");
//							}else {//等级一样的时候选择最新的:比如2018年的动态评价发布日期是2018年，而2018年度定期评价发布日期是2019年
//								evalresult.setGrade(dqgrade);
//								if(null!=companydateeval.getBid_score()) {
//									evalresult.setBid_score( companydateeval.getBid_score());
//								}
//								if(null!=companydateeval.getDo_score()) {
//									evalresult.setDo_score( companydateeval.getDo_score());
//								}
//								if(null!=companydateeval.getOther_score()) {
//									evalresult.setOther_score( companydateeval.getOther_score());
//								}
//								if(null!=companydateeval.getGood_score()) {
//									evalresult.setGood_score( companydateeval.getGood_score());
//								}
//								if(null!=companydateeval.getSum_score()) {
//									evalresult.setSum_score( companydateeval.getSum_score());
//								}
//								evalresult.setIsrank("");
//							}
//							evalresultService.save(evalresult);
//						}
//
//					}else if(StringUtils.isBlank(dtgrade)&&StringUtils.isNotBlank(dqgrade)) {//只有定期评价等级
//						if(null!=companydateeval) {
//							evalresult.setGrade(dqgrade);
//							evalresult.setIsrank("");
//							if(null!=companydateeval.getBid_score()) {
//								evalresult.setBid_score( companydateeval.getBid_score());
//							}
//							if(null!=companydateeval.getDo_score()) {
//								evalresult.setDo_score( companydateeval.getDo_score());
//							}
//							if(null!=companydateeval.getOther_score()) {
//								evalresult.setOther_score( companydateeval.getOther_score());
//							}
//							if(null!=companydateeval.getGood_score()) {
//								evalresult.setGood_score( companydateeval.getGood_score());
//							}
//							if(null!=companydateeval.getSum_score()) {
//								evalresult.setSum_score( companydateeval.getSum_score());
//							}
//							evalresultService.save(evalresult);
//						}
//					}else if(StringUtils.isNotBlank(dtgrade)&&StringUtils.isBlank(dqgrade)) {//只有动态评价等级
//						evalresult.setGrade(dtgrade);
//						evalresult.setIsrank("1");
//						evalresult.setBid_score(null);
//						evalresult.setDo_score(null);
//						evalresult.setOther_score(null);
//						evalresult.setGood_score(null);
//						evalresult.setSum_score(null);
//						evalresultService.save(evalresult);
//					}
//				}
//				if(null==newevalresult2&&null==newevalresult1&&null==evalresult) {//2018.2019.2020都为空的时候，采用2017年的结果，延期1年
//					oldevalresult=evalresultService.findbycorp_idandcorp_type(arr[0], arr[1] ,(dqevalyear-1)+"",Constantval.CONSTANT_INDUSTRY_ONE);//本年度的评价结果
//					if(null!=oldevalresult) {
//						oldcompanydateeval=companydateevalService.findResult3(arr[0],arr[1], (dqevalyear-1)+"" , Constantval.CONSTANT_INDUSTRY_ONE);
//						oldcompanyranklist=companyrankService.findByCorp3(arr[0],arr[1],  (dqevalyear-1)+"", Constantval.CONSTANT_INDUSTRY_ONE);//上年度的动态评价
//						String mingrade="";//最低等级
//						String dtgrade="";//动态最低等级
//						String dqgrade="";//定期最低等级
//						if(null!=oldevalresult) {
//							if(null!=oldcompanyranklist&&oldcompanyranklist.size()>0) {//获取动态评价中。等级最低的等级
//								if(oldcompanyranklist.size()>1) {
//									List<String> listG=new ArrayList<String>();
//									for(Companyrank companyrank:oldcompanyranklist) {
//										if(StringUtils.isNotBlank(companyrank.getGrade())) {
//											listG.add(companyrank.getGrade());
//										}
//									}
//									if(listG.size()>0) {
//										dtgrade=Collections.max(listG);//取出最低等级的
//									}
//									for(Companyrank companyrank:oldcompanyranklist) {
//										if(StringUtils.isNotBlank(companyrank.getGrade())) {
//											 if(dtgrade.equals(companyrank.getGrade())) {
//												//oldcompanyranklist排序的顺序是发布时间大的排在前面，所以第一个就是最新的
//												 companyrank2= companyrank;
//												 break;
//											 }
//										}
//									}
//								}else {
//									if(null!=oldcompanyranklist.get(0)) {
//										dtgrade= StringUtils.isBlank(oldcompanyranklist.get(0).getGrade())?"":oldcompanyranklist.get(0).getGrade();
//									}
//
//								}
//							}
//							if(null!=oldcompanydateeval) {
//								dqgrade=StringUtils.isBlank(oldcompanydateeval.getGrade())?"":oldcompanydateeval.getGrade();
//							}
//							if(StringUtils.isNotBlank(dtgrade)&&StringUtils.isNotBlank(dqgrade)) {//动态和定期全有
//								if(dqgrade.compareTo(dtgrade)<0) {//如果定期等级小于动态等级
//									oldevalresult.setGrade(dtgrade);
//									oldevalresult.setIsrank("1");
//									oldevalresult.setBid_score(null);
//									oldevalresult.setDo_score(null);
//									oldevalresult.setOther_score(null);
//									oldevalresult.setGood_score(null);
//									oldevalresult.setSum_score(null);
//								}else if(dqgrade.compareTo(dtgrade)>0) {
//									oldevalresult.setGrade(dqgrade);
//									if(null!=oldcompanydateeval.getBid_score()) {
//										oldevalresult.setBid_score( oldcompanydateeval.getBid_score());
//									}
//									if(null!=oldcompanydateeval.getDo_score()) {
//										oldevalresult.setDo_score( oldcompanydateeval.getDo_score());
//									}
//									if(null!=oldcompanydateeval.getOther_score()) {
//										oldevalresult.setOther_score( oldcompanydateeval.getOther_score());
//									}
//									if(null!=oldcompanydateeval.getGood_score()) {
//										oldevalresult.setGood_score( oldcompanydateeval.getGood_score());
//									}
//									if(null!=oldcompanydateeval.getSum_score()) {
//										oldevalresult.setSum_score( oldcompanydateeval.getSum_score());
//									}
//									oldevalresult.setIsrank("");
//								}else {//等级一样的时候选择最新的
//									issuetime=oldcompanydateeval.getIssuetime();//定期评价发布日期
//									if(null!=companyrank2) {
//										issuetime2=companyrank2.getIssuetime();//动态评价发布日期
//									}
//									if(null!=issuetime&&null!=issuetime2) {
//										if(issuetime.before(issuetime2)) {//date1.before(date)，当date1小于date2时，返回true，当大于等于时，返回false
//											oldevalresult.setGrade(dtgrade);
//											oldevalresult.setIsrank("1");
//											oldevalresult.setBid_score(null);
//											oldevalresult.setDo_score(null);
//											oldevalresult.setOther_score(null);
//											oldevalresult.setGood_score(null);
//											oldevalresult.setSum_score(null);
//										}else {
//											oldevalresult.setGrade(dqgrade);
//											if(null!=oldcompanydateeval.getBid_score()) {
//												oldevalresult.setBid_score( oldcompanydateeval.getBid_score());
//											}
//											if(null!=oldcompanydateeval.getDo_score()) {
//												oldevalresult.setDo_score( oldcompanydateeval.getDo_score());
//											}
//											if(null!=oldcompanydateeval.getOther_score()) {
//												oldevalresult.setOther_score( oldcompanydateeval.getOther_score());
//											}
//											if(null!=oldcompanydateeval.getGood_score()) {
//												oldevalresult.setGood_score( oldcompanydateeval.getGood_score());
//											}
//											if(null!=oldcompanydateeval.getSum_score()) {
//												oldevalresult.setSum_score( oldcompanydateeval.getSum_score());
//											}
//											oldevalresult.setIsrank("");
//										}
//									}
//								}
//								evalresultService.save(oldevalresult);
//							}else if(StringUtils.isBlank(dtgrade)&&StringUtils.isNotBlank(dqgrade)) {//只有定期评价等级
//								oldevalresult.setGrade(dqgrade);
//								oldevalresult.setIsrank("");
//								if(null!=oldcompanydateeval.getBid_score()) {
//									oldevalresult.setBid_score( oldcompanydateeval.getBid_score());
//								}
//								if(null!=oldcompanydateeval.getDo_score()) {
//									oldevalresult.setDo_score( oldcompanydateeval.getDo_score());
//								}
//								if(null!=oldcompanydateeval.getOther_score()) {
//									oldevalresult.setOther_score( oldcompanydateeval.getOther_score());
//								}
//								if(null!=oldcompanydateeval.getGood_score()) {
//									oldevalresult.setGood_score( oldcompanydateeval.getGood_score());
//								}
//								if(null!=oldcompanydateeval.getSum_score()) {
//									oldevalresult.setSum_score( oldcompanydateeval.getSum_score());
//								}
//								evalresultService.save(oldevalresult);
//							}else if(StringUtils.isNotBlank(dtgrade)&&StringUtils.isBlank(dqgrade)) {//只有动态评价等级
//								oldevalresult.setGrade(dtgrade);
//								oldevalresult.setIsrank("1");
//								oldevalresult.setBid_score(null);
//								oldevalresult.setDo_score(null);
//								oldevalresult.setOther_score(null);
//								oldevalresult.setGood_score(null);
//								oldevalresult.setSum_score(null);
//								evalresultService.save(oldevalresult);
//							}else if(StringUtils.isBlank(dtgrade)&&StringUtils.isBlank(dqgrade)) {//2017年度没有定期评价，没有动态评价，有评价结果
//								List<Companyrank> list=companyrankService.findByCorp4(arr[0],arr[1], (dqevalyear-1)+"" , Constantval.CONSTANT_INDUSTRY_ONE);
////								if(null!=list&&list.size()>0) {//动态评价超过2年如何处理,如果list不为空，则不是2017年的
////
////								}
//							}
//						}
//					}
//				}
//			}
//		}
//
//	}
//
//
//	@Override
//	public void initXmjbxxreply() {
//			List<Xmjbxxreply> list=xmjbxxreplyService.findAll();
//			List<Xmjbxxreplyweb> list2=xmjbxxreplywebService.findAll();
//			String ids="";
//			String names="";
//			if(null!=list&&list.size()>0){
//				List<Xmfjxx> xmfjxxlist=null;
//				for(Xmjbxxreply xmjbxxreply :list){
//					if(null!=xmjbxxreply&&StringUtils.isBlank(xmjbxxreply.getPfuploadfileid())&&StringUtils.isNotBlank(xmjbxxreply.getProject_id())){
//						xmfjxxlist=xmfjxxService.findByOwneridFileType(xmjbxxreply.getProject_id(),xmjbxxreply.getAllow_type());
//						if (null!=xmfjxxlist&&xmfjxxlist.size()>0){
//							if(xmfjxxlist.size()==1){
//								if(null!=xmfjxxlist.get(0)&&StringUtils.isNotBlank(xmfjxxlist.get(0).getFileid())&&StringUtils.isNotBlank(xmfjxxlist.get(0).getFilename())){
//									xmjbxxreply.setPfuploadfileid(xmfjxxlist.get(0).getFileid());
//									xmjbxxreply.setPfuploadfilename(xmfjxxlist.get(0).getFilename());
//								}
//								xmjbxxreplyService.save(xmjbxxreply);
//							}else{
//								for (Xmfjxx xmfjxx:xmfjxxlist){
//									if(null!=xmfjxx&&StringUtils.isNotBlank(xmfjxx.getFileid())&&StringUtils.isNotBlank(xmfjxx.getFilename())){
//										if(StringUtils.isBlank(ids)){
//											ids=ids+xmfjxx.getFileid();
//											names=names+xmfjxx.getFilename();
//										}else{
//											ids=ids+","+xmfjxx.getFileid();
//											names=ids+","+xmfjxx.getFilename();
//										}
//									}
//
//								}
//								xmjbxxreply.setPfuploadfileid(ids);
//								xmjbxxreply.setPfuploadfilename(names);
//								xmjbxxreplyService.save(xmjbxxreply);
//								ids="";
//								names="";
//							}
//
//						}
//					}
//				}
//			}
//		if(null!=list2&&list2.size()>0){
//			List<Xmfjxxweb> xmfjxxweblist=null;
//			for(Xmjbxxreplyweb xmjbxxreplyweb :list2){
//				if(null!=xmjbxxreplyweb&&StringUtils.isBlank(xmjbxxreplyweb.getPfuploadfileid())&&StringUtils.isNotBlank(xmjbxxreplyweb.getProject_id())){
//					xmfjxxweblist=xmfjxxwebService.findByOwneridFileType(xmjbxxreplyweb.getProject_id(),xmjbxxreplyweb.getAllow_type());
//					if (null!=xmfjxxweblist&&xmfjxxweblist.size()>0){
//						if(xmfjxxweblist.size()==1){
//							if(null!=xmfjxxweblist.get(0)&&StringUtils.isNotBlank(xmfjxxweblist.get(0).getFileid())&&
//									StringUtils.isNotBlank(xmfjxxweblist.get(0).getFilename())){
//								xmjbxxreplyweb.setPfuploadfileid(xmfjxxweblist.get(0).getFileid());
//								xmjbxxreplyweb.setPfuploadfilename(xmfjxxweblist.get(0).getFilename());
//							}
//							xmjbxxreplywebService.save(xmjbxxreplyweb);
//						}else{
//							for (Xmfjxxweb xmfjxxweb:xmfjxxweblist){
//								if(null!=xmfjxxweb&&StringUtils.isNotBlank(xmfjxxweb.getFileid())&&StringUtils.isNotBlank(xmfjxxweb.getFilename())){
//									if(StringUtils.isBlank(ids)){
//										ids=ids+xmfjxxweb.getFileid();
//										names=names+xmfjxxweb.getFilename();
//									}else{
//										ids=ids+","+xmfjxxweb.getFileid();
//										names=ids+","+xmfjxxweb.getFilename();
//									}
//								}
//							}
//							xmjbxxreplyweb.setPfuploadfileid(ids);
//							xmjbxxreplyweb.setPfuploadfilename(names);
//							xmjbxxreplywebService.save(xmjbxxreplyweb);
//							ids="";
//							names="";
//						}
//					}
//				}
//			}
//		}
//	}
//
//
//
//
//
//
//
//
//
//
//	/*
//	 * private CredittypeScore initCredittypeScore(Creditscore creditScore,String
//	 * typeid,String grade,String infoid,String infotype) { String
//	 * scoreid=creditScore.getScoreid(); CredittypeScore
//	 * credittypeScore=credittypeScoreService.findByScoreid(scoreid, typeid);
//	 * if(credittypeScore==null) { credittypeScore=new CredittypeScore();
//	 * credittypeScore.setId(KeyUtils.getKey());
//	 * credittypeScore.setScoreid(scoreid); credittypeScore.setCredittypeid(typeid);
//	 * if(grade!=null) { credittypeScore.setScoregrade(grade); }else {
//	 * credittypeScore.setScorecount(1); }
//	 * credittypeScoreService.save(credittypeScore); }else { if(grade!=null) {
//	 * credittypeScore.setScoregrade(grade); }else {
//	 * credittypeScore.setScorecount(credittypeScore.getScorecount()+1); }
//	 * credittypeScoreService.update(credittypeScore); }
//	 * addCreditScoreJC(credittypeScore.getId(), infoid, infotype); return
//	 * credittypeScore; }
//	 *
//	 * private void addCreditScoreJC(String typescoreid,String infoid,String
//	 * infotype) { CreditscoreJC creditscoreJC=new CreditscoreJC();
//	 * creditscoreJC.setId(KeyUtils.getKey());
//	 * creditscoreJC.setTypescoreid(typescoreid); creditscoreJC.setInfoid(infoid);
//	 * creditscoreJC.setInfotype(infotype);
//	 * creditscoreJCService.save(creditscoreJC); }
//	 */
//
//	/*
//	 * private CredittypeScore initCredittypeScore(String scoreid,String
//	 * typeid,String grade) { CredittypeScore
//	 * credittypeScore=credittypeScoreService.findByScoreid(scoreid, typeid);
//	 * if(credittypeScore==null) { credittypeScore=new CredittypeScore();
//	 * credittypeScore.setId(KeyUtils.getKey());
//	 * credittypeScore.setScoreid(scoreid); credittypeScore.setCredittypeid(typeid);
//	 * if(grade!=null) { credittypeScore.setScoregrade(grade); }else {
//	 * credittypeScore.setScorecount(1); }
//	 * credittypeScoreService.save(credittypeScore); }else { if(grade!=null) {
//	 * credittypeScore.setScoregrade(grade); }else {
//	 * credittypeScore.setScorecount(credittypeScore.getScorecount()+1); }
//	 * credittypeScoreService.update(credittypeScore); } return credittypeScore; }
//	 */
//
//}
