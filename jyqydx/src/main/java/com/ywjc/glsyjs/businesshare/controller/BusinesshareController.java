package com.ywjc.glsyjs.businesshare.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.AuthorizedrecordService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.businesshare.entity.Orderagency;
import com.ywjc.glsyjs.businesshare.entity.Suggestreply;
import com.ywjc.glsyjs.businesshare.service.OrderagencyService;
import com.ywjc.glsyjs.businesshare.service.SuggestreplyService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.suggestions.entity.SuggestionsEntity;
import com.ywjc.glsyjs.interfaces.suggestions.service.SuggestionsEntityService;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller("businesshareController")
@RequestMapping("/businesshare")
public class BusinesshareController extends BaseController {

	private Logger logger = Logger.getLogger(this.getClass());


	private static final int PAGE_SIZE = 15;

	@Resource(name = "orderagencyServiceImpl")
	private OrderagencyService orderagencyService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "suggestionsEntityServiceImpl")
	private SuggestionsEntityService suggestionsEntityService;

	@Resource(name = "usercompanyEntityServiceImpl")
	private UsercompanyEntityService usercompanyEntityService;


	@Resource(name = "suggestreplyServiceImpl")
	private SuggestreplyService suggestreplyService;


	@Resource(name = "companyEntityServiceImpl")
	private CompanyEntityService companyEntityService;

	@Resource(name = "authorizedrecordServiceImpl")
	private AuthorizedrecordService authorizedrecordService;

	/**
	 * 预约代办列表
	 */
	@RequestMapping(value = "/orderagency/list",method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = PAGE_SIZE) Pageable pageable,
			@RequestParam(required=false) String  orderman,
			String ordercompanyid, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("orderman", orderman);
		model.addAttribute("ordercompanyid", ordercompanyid);
		ordercompanyid=StringUtils.isNotBlank(ordercompanyid)?ordercompanyid.trim():ordercompanyid;
		orderman = StringUtils.isBlank(orderman)?"%%":"%"+orderman+"%";
		Page<Orderagency> page=orderagencyService.findPageByordercompanyid(ordercompanyid,orderman,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/orderagency/list";
	}

	/**
	 * List 预约代码处理列表
	 */
	@RequestMapping(value = "/orderagency/checklist", method = RequestMethod.GET)
	public String checklist(@PageableDefault(page = 0, size = PAGE_SIZE) Pageable pageable,
							@RequestParam(required=false) String  orderman,
							@RequestParam(required=false) String  ordercompany,
							HttpSession session, ModelMap model, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("orderman", orderman);
		model.addAttribute("ordercompany", ordercompany);
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user=principal.getUser();
		orderman = StringUtils.isBlank(orderman)?"%%":"%"+orderman+"%";
		ordercompany = StringUtils.isBlank(ordercompany)?"%%":"%"+ordercompany+"%";
		Page<Orderagency> page = orderagencyService.findOrderagencys(orderman,ordercompany,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/businesshare/orderagency/checklist";
	}




	/**
	 * save 预约代办保存
	 */
	@RequestMapping(value = "/orderagency/oldsave",method = RequestMethod.POST)
	public String save(@Valid Orderagency orderagency, BindingResult bindingResult,
					   ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "redirect:/yqlzcinfo/order";
		}
//		Principal principal = (Principal) session.getAttribute("userinfo");
//		User user=principal.getUser();

        String username=(String)session.getAttribute("username");
		if(StringUtils.isNotBlank(orderagency.getOrdercompanyid())){
			CompanyEntity companyEntity=companyEntityService.find(orderagency.getOrdercompanyid().trim());
			orderagency.setOrdercompany(companyEntity.getEnterprisename());
			orderagency.setOrdercompanyid(companyEntity.getCompanyid());
		}

		orderagency.setOrderman(username);

		orderagency.setOrderid(KeyUtils.getKey());
		orderagency.setState("0");
		orderagency.setOrdertime(new Date());
		orderagencyService.save(orderagency);
		return "redirect:/yqlzcinfo/order";
	}


	@ResponseBody
	@RequestMapping(value = "/orderagency/save",method = RequestMethod.POST)
	public MessageUtil orderagencysave(@Valid Orderagency orderagency,String phone,String mess, HttpSession session) {
		MessageUtil messageUtil = new MessageUtil();
		String ordermanid=null;
		UsercompanyEntity usercompany=(UsercompanyEntity)session.getAttribute("logincompany");
		if(usercompany!=null){
			ordermanid=usercompany.getUserid();
		}
		try {

           /* if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(mess) ) {
                PhonerodEntity phonerodEntity = phonerodEntityService.queryPhoneRod(phone);
                if (phonerodEntity != null) {
                    Date nowTime = new Date();
                    Date exTime = phonerodEntity.getExtime();
                    if (nowTime.before(exTime)) {
                        if (mess.equals(phonerodEntity.getRod())) {*/
			//查看当前企业id是否有法人手机号，没有进行关联执行更新操作
			if(StringUtils.isNotBlank(orderagency.getOrdercompanyid())){
				CompanyEntity companyEntity=companyEntityService.find(orderagency.getOrdercompanyid().trim());
				User user=userService.find(orderagency.getAgencymanid());
				if(companyEntity!=null){
					orderagency.setOrdercompany(companyEntity.getEnterprisename());
					orderagency.setOrdercompanyid(companyEntity.getCompanyid());
				}

				String username=(String)session.getAttribute("username");
				orderagency.setOrderman(username);
				orderagency.setOrderid(KeyUtils.getKey());
				orderagency.setState("0");
				orderagency.setOrdertime(new Date());
				orderagency.setOrdermanid(ordermanid);
				orderagencyService.save(orderagency);
				companyEntityService.updateCompanyPhoneno(phone, orderagency.getOrdercompanyid());

                //该人员是否关联企业
				UsercompanyEntity usercompanyEntity=usercompanyEntityService.findByuseridAndCompanyid(orderagency.getAgencymanid(),companyEntity.getCompanyid());
				if(usercompanyEntity==null){
					usercompanyEntity=new UsercompanyEntity();
					usercompanyEntity.setId(KeyUtils.getKey());
					usercompanyEntity.setUserid(orderagency.getAgencymanid());
					usercompanyEntity.setUsername(orderagency.getAgencyman());
					usercompanyEntity.setCompanyid(companyEntity.getCompanyid());
					usercompanyEntity.setEnterprisename(companyEntity.getEnterprisename());
					if(user!=null){
						usercompanyEntity.setLinktel(user.getLinktel());
						usercompanyEntity.setLinknum(user.getLinknum());
					}

					usercompanyEntity.setIslegalperson("0");
					usercompanyEntityService.save(usercompanyEntity);
				}


				//查看当前企业授权人
				AuthorizedrecordEntity au=authorizedrecordService.findlistBycompanyidLast(orderagency.getOrdercompanyid());
				if(au==null){//当前企业无授权人
					AuthorizedrecordEntity authorizedrecordEntity=new AuthorizedrecordEntity();
					String id = UUID.randomUUID().toString();
					id = id.replace("-", "");
					authorizedrecordEntity.setAuthorizedid(id);
					authorizedrecordEntity.setCompanyid(companyEntity.getCompanyid());
					authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
					authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
					authorizedrecordEntity.setAuthorizedpersonid(orderagency.getAgencymanid());
					authorizedrecordEntity.setAuthorizedperson(orderagency.getAgencyman());
					authorizedrecordEntity.setAuthorizedtime(new Date());
					authorizedrecordEntity.setState("1");
					authorizedrecordService.save(authorizedrecordEntity);
					messageUtil.setResult(true);
				}else{
					if(au.getAuthorizedpersonid().equals(orderagency.getAgencymanid())){
						messageUtil.setResult(false);
						messageUtil.setErrorMsg("该人员已授权为代办人，请勿重复预约！");
					}else{//先取消授权，再重新授权
						authorizedrecordService.updateshqu(companyEntity.getCompanyid());
						AuthorizedrecordEntity authorizedrecordEntity=new AuthorizedrecordEntity();
						String id = UUID.randomUUID().toString();
						id = id.replace("-", "");
						authorizedrecordEntity.setAuthorizedid(id);
						authorizedrecordEntity.setCompanyid(companyEntity.getCompanyid());
						authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
						authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
						authorizedrecordEntity.setAuthorizedpersonid(orderagency.getAgencymanid());
						authorizedrecordEntity.setAuthorizedperson(orderagency.getAgencyman());
						authorizedrecordEntity.setAuthorizedtime(new Date());
						authorizedrecordEntity.setState("1");
						authorizedrecordService.save(authorizedrecordEntity);
						messageUtil.setResult(true);
					}
				}



			}


                       /* } else {
                            messageUtil.setResult(false);
                            messageUtil.setErrorMsg("验证码错误，请重新输入！");

                        }
                    } else {
                        messageUtil.setResult(false);
                        messageUtil.setErrorMsg("验证码已过期，请重新获取验证码！");
                    }
                } else {
                    messageUtil.setResult(false);
                    messageUtil.setErrorMsg("验证码为空");
                }

            } else {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("手机号或者验证码为空!");

            }*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("服务端程序处理异常！异常信息为：%s.", e.getMessage()));
		}
		return messageUtil;
	}




	@RequestMapping(value = "/orderagency/doorder/{orderid}", method = RequestMethod.GET)
	public String doorder(@PathVariable String orderid, ModelMap model) {
		Orderagency orderagency = orderagencyService.find(orderid);
		if (orderagency == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		orderagency.setState("1");
		orderagencyService.update(orderagency);
		UsercompanyEntity usercompanyEntity=new UsercompanyEntity();
		usercompanyEntity.setId(KeyUtils.getKey());
		usercompanyEntity.setUserid(orderagency.getAgencymanid());
		usercompanyEntity.setUsername(orderagency.getAgencyman());
		usercompanyEntity.setCompanyid(orderagency.getOrdercompanyid());
		usercompanyEntity.setEnterprisename(orderagency.getOrdercompany());
		usercompanyEntity.setIslegalperson("2");
		usercompanyEntityService.save(usercompanyEntity);
		return "redirect:/businesshare/orderagency/checklist";
	}



	/**
	 * 意见建议列表
	 */
	@RequestMapping(value = "/suggest/list",method = RequestMethod.GET)
	public String suggestlist(
			@PageableDefault(page = 0, size = 8) Pageable pageable,
			@RequestParam(required=false) String  title,
			ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("title", title);
		title = StringUtils.isBlank(title)?"%%":"%"+title+"%";
		UsercompanyEntity logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
		Page<SuggestionsEntity> page=null;
		if(logincompany!=null){
			page = suggestionsEntityService.queryPageByCompany(title,logincompany.getCompanyid(),pageable);
		}else{
			page = suggestionsEntityService.queryPage(title,pageable);
		}
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/suggest/list";
	}


	@RequestMapping(value = "/suggest/add",method = RequestMethod.GET)
	public String add(ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/suggest/new";
	}



	/**
	 * save 意见建议
	 */
	@RequestMapping(value = "/suggest/save",method = RequestMethod.POST)
	public String suggestsave(@Valid SuggestionsEntity info, BindingResult bindingResult,
					   ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "redirect:/businesshare/suggest/list";
		}
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setId(KeyUtils.getKey());
		info.setState("0");
		info.setInfotype("1");
		info.setCreatetime(new Date());
		info.setUpdatetime(new Date());
        info.setIsshow("0");
		UsercompanyEntity logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
		if(logincompany!=null){
			info.setCompanyid(logincompany.getCompanyid());
			info.setCreaterid(logincompany.getUserid());
			info.setCreater(logincompany.getUsername());
		}else{
			if(StringUtils.isNotBlank(info.getCompanyname())){
				CompanyEntity companyEntity=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
				if(companyEntity!=null){
					info.setCompanyid(companyEntity.getCompanyid());
				}

			}
		}
		suggestionsEntityService.save(info);
		return "redirect:/businesshare/suggest/list";
	}

	@RequestMapping(value = "/suggest/{id}", method = RequestMethod.GET)
	public String suggest(@PathVariable String id, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		SuggestionsEntity suggest = suggestionsEntityService.find(id);
		if (suggest == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		suggest.setReadcount(suggest.getReadcount()+1);
		suggestionsEntityService.update(suggest);
		model.addAttribute("suggest",suggest);
		List<Suggestreply> replys=suggestreplyService.findBySuggest(id);
		model.addAttribute("replys",replys);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/suggest/show";
	}


	/**
	 * 意见建议列表
	 */
	@RequestMapping(value = "/suggest/checklist",method = RequestMethod.GET)
	public String checklist(
			@PageableDefault(page = 0, size = PAGE_SIZE) Pageable pageable,
			@RequestParam(required=false) String  title,
			@RequestParam(required=false) String  companyname,
			ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("companyname", companyname);
		model.addAttribute("title", title);
		companyname = StringUtils.isBlank(companyname)?"%%":"%"+companyname+"%";
		title = StringUtils.isBlank(title)?"%%":"%"+title+"%";
		Page<SuggestionsEntity> page = suggestionsEntityService.queryPageinfo(companyname,title,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/businesshare/suggest/checklist";
	}

	@RequestMapping(value = "/suggestcheck/{id}", method = RequestMethod.GET)
	public String suggestcheck(@PathVariable String id, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		SuggestionsEntity suggest = suggestionsEntityService.find(id);
		if (suggest == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		suggestionsEntityService.update(suggest);
		model.addAttribute("suggest",suggest);
		List<Suggestreply> replys=suggestreplyService.findBySuggest(id);
		model.addAttribute("replys",replys);
		return "/businesshare/suggest/check";
	}

	@RequestMapping(value = "/suggestcheckshow/{id}", method = RequestMethod.GET)
	public String suggestcheckshow(@PathVariable String id, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		SuggestionsEntity suggest = suggestionsEntityService.find(id);
		if (suggest == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		suggestionsEntityService.update(suggest);
		model.addAttribute("suggest",suggest);
		List<Suggestreply> replys=suggestreplyService.findBySuggest(id);
		model.addAttribute("replys",replys);
		model.addAttribute("infotype","checkshow");
		return "/businesshare/suggest/checkshow";
	}


	/**
	 * save 意见回复保存
	 */
	@RequestMapping(value = "/suggest/replysave",method = RequestMethod.POST)
	public String replysave(@Valid Suggestreply info,String isshow, BindingResult bindingResult,
							ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user=principal.getUser();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setReplyid(KeyUtils.getKey());
		info.setReplymanid(user.getId());
		info.setReplyman(user.getUsername());
		info.setReplytime(new Date());
		suggestreplyService.save(info);
		SuggestionsEntity suggest = suggestionsEntityService.find(info.getSuggestid());
		suggest.setState("2");
		suggest.setDonetime(new Date());
		suggest.setDoneresult(info.getContent());
		suggest.setIsshow(isshow);
		suggest.setUpdatetime(new Date());
		suggestionsEntityService.update(suggest);
		return "redirect:/businesshare/suggest/checklist";
	}


	/**
	 * save 意见追问保存
	 */
	@RequestMapping(value = "/suggest/questsave",method = RequestMethod.POST)
	public String questsave(@Valid Suggestreply info) {
		info.setReplyid(KeyUtils.getKey());
		info.setReplytime(new Date());
		suggestreplyService.save(info);
		SuggestionsEntity suggest = suggestionsEntityService.find(info.getSuggestid());
		suggest.setState("0");
		suggest.setUpdatetime(new Date());
		suggestionsEntityService.update(suggest);
		return "redirect:/businesshare/suggest/list";
	}



	/**投诉 列表
	 */
	@RequestMapping(value = "/complaint/list",method = RequestMethod.GET)
	public String complaintlist(
			@PageableDefault(page = 0, size = PAGE_SIZE) Pageable pageable,
			@RequestParam(required=false) String  title,
			ModelMap model,HttpSession session, HttpServletRequest req) {
		model.addAttribute("title", title);
		refreshCodenew(model, session, req);
		title = StringUtils.isBlank(title)?"%%":"%"+title+"%";
		UsercompanyEntity logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
		Page<SuggestionsEntity> page=null;
		if(logincompany!=null){
			page = suggestionsEntityService.queryPagecomplaintByCompany(title,logincompany.getCompanyid(),pageable);
		}else{
			page = suggestionsEntityService.queryPagecomplaint(title,pageable);
		}
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/complaint/list";
	}


	@RequestMapping(value = "/complaint/add",method = RequestMethod.GET)
	public String complaintadd(ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/complaint/new";
	}



	/**
	 * save 投诉保存
	 */
	@RequestMapping(value = "/complaint/save",method = RequestMethod.POST)
	public String complaintsave(@Valid SuggestionsEntity info, BindingResult bindingResult,
							  ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "redirect:/businesshare/complaint/list";
		}
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setId(KeyUtils.getKey());
		UsercompanyEntity logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
		if(logincompany!=null){
			info.setCompanyid(logincompany.getCompanyid());
			info.setCreaterid(logincompany.getUserid());
			info.setCreater(logincompany.getUsername());
		}else{
			if(StringUtils.isNotBlank(info.getCompanyname())){
				CompanyEntity companyEntity=companyEntityService.queryCompanyBycorpname(info.getCompanyname().trim());
				if(companyEntity!=null){
					info.setCompanyid(companyEntity.getCompanyid());
				}

			}
		}
		info.setState("0");
        info.setIsshow("0");
		info.setCreatetime(new Date());
		info.setUpdatetime(new Date());
		suggestionsEntityService.save(info);
		return "redirect:/businesshare/complaint/list";
	}


	@RequestMapping(value = "/complaint/{id}", method = RequestMethod.GET)
	public String complaint(@PathVariable String id, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		SuggestionsEntity suggest = suggestionsEntityService.find(id);
		if (suggest == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		suggest.setReadcount(suggest.getReadcount()+1);
		suggestionsEntityService.update(suggest);
		model.addAttribute("suggest",suggest);
		List<Suggestreply> replys=suggestreplyService.findBySuggest(id);
		model.addAttribute("replys",replys);
		model.addAttribute("infotype",Constantval.INDEX_SEVEN);
		return "/businesshare/complaint/show";
	}


	/**
	 * 意见建议列表
	 */
	@RequestMapping(value = "/complaint/checklist",method = RequestMethod.GET)
	public String complaintlist(
			@PageableDefault(page = 0, size = PAGE_SIZE) Pageable pageable,
			@RequestParam(required=false) String  title,
			@RequestParam(required=false) String  companyname,
			@RequestParam(required=false) String  infotype,
			ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		model.addAttribute("companyname", companyname);
		model.addAttribute("title", title);
		companyname = StringUtils.isBlank(companyname)?"%%":"%"+companyname+"%";
		title = StringUtils.isBlank(title)?"%%":"%"+title+"%";
		Page<SuggestionsEntity> page = suggestionsEntityService.queryPageinfocomplaint(companyname,title,infotype,pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/businesshare/complaint/checklist";
	}

	@RequestMapping(value = "/complaintcheck/{id}", method = RequestMethod.GET)
	public String complaintcheck(@PathVariable String id, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		SuggestionsEntity suggest = suggestionsEntityService.find(id);
		if (suggest == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		suggestionsEntityService.update(suggest);
		model.addAttribute("suggest",suggest);
		List<Suggestreply> replys=suggestreplyService.findBySuggest(id);
		model.addAttribute("replys",replys);
		return "/businesshare/complaint/check";
	}

	@RequestMapping(value = "/complaintcheckshow/{id}", method = RequestMethod.GET)
	public String complaintcheckshow(@PathVariable String id, ModelMap model,HttpSession session, HttpServletRequest req) {
		refreshCodenew(model, session, req);
		SuggestionsEntity suggest = suggestionsEntityService.find(id);
		if (suggest == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		suggestionsEntityService.update(suggest);
		model.addAttribute("suggest",suggest);
		List<Suggestreply> replys=suggestreplyService.findBySuggest(id);
		model.addAttribute("replys",replys);
		model.addAttribute("infotype","checkshow");
		return "/businesshare/complaint/checkshow";
	}


	/**
	 * save 意见回复保存
	 */
	@RequestMapping(value = "/complaint/replysave",method = RequestMethod.POST)
	public String complaintreplysave(@Valid Suggestreply info,String isshow, BindingResult bindingResult,
							ModelMap model, HttpSession session, RedirectAttributes redirectAttributes) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		User user=principal.getUser();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setReplyid(KeyUtils.getKey());
		info.setReplymanid(user.getId());
		info.setReplyman(user.getUsername());
		info.setReplytime(new Date());
		suggestreplyService.save(info);
		SuggestionsEntity suggest = suggestionsEntityService.find(info.getSuggestid());
		suggest.setState("2");
		suggest.setDonetime(new Date());
		suggest.setUpdatetime(new Date());
		suggest.setDoneresult(info.getContent());
		suggest.setIsshow(isshow);
		suggestionsEntityService.update(suggest);
		return "redirect:/businesshare/complaint/checklist";
	}


	/**
	 * 判断是否绑定企业
	 * @param model
	 * @param session
	 * @param req
	 * @return
	 */
	private Object[] refreshCodenew(ModelMap model, HttpSession session, HttpServletRequest req) {
		Object[] data = new Object[2];
		//TODO
		//添加登录信息（测试使用用后删除）
//        session.setAttribute("username", "黄康");
//        session.setAttribute("linknum", "320922199607126316");
//        session.setAttribute("linktel", "17366256643");
//        session.setAttribute("username", "赵洋");
//        session.setAttribute("linknum", "411281199303173513");
//        session.setAttribute("linktel", "15621365228");
		//

/*		String username = (String) session.getAttribute("username");
		String linknum = (String) session.getAttribute("linknum");
		String linktel = (String) session.getAttribute("linktel");
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum) && StringUtils.isNotBlank(linktel)) {
//                判断是否绑定企业
			List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel);
			if (CollectionUtils.isEmpty(usercompanyEntityList)) {
				model.put("isReletedCompanyFlag", false);
			} else {
				model.put("isReletedCompanyFlag", true);
				model.put("companyList", usercompanyEntityList);
				model.put("search_companyid",usercompanyEntityList.get(0).getCompanyid());
				model.put("search_companyname",usercompanyEntityList.get(0).getEnterprisename());
			}
		}*/
		return data;
	}





}
