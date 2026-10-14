package com.ywjc.glsyjs.sys.controller;

import com.ywjc.glsyjs.account.entity.Loginlog;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import com.ywjc.glsyjs.shiro.security.RetryLimitHashedCredentialsMatcher;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.DecryptionService;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller("onlineUserController")
@RequestMapping("/sys/onlineuser")
public class OnlineUserController extends BaseController {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;
	private String url="/sys/onlineuser";

	/**
	 * 锁住人员信息查询
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String listman(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
						  ModelMap model,
						  @RequestParam(required = false) String loginname,
						  @RequestParam(required = false) String organname,
						  HttpServletRequest request,
						  HttpSession session) {
		loginlogService.saveloginlog(url+"/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("loginname", loginname);
		model.addAttribute("organname", organname);
		loginname=StringUtils.isNotBlank(loginname)?"%"+loginname+"%":"%%";
		organname=StringUtils.isNotBlank(organname)?"%"+organname+"%":"%%";
		Page<User> page=userService.findPageOnlineUsers(loginname,organname,pageable);
		for (User user:page.getContent()) {
			List<Loginlog> logs=loginlogService.findLogs(user.getId());
			if(logs!=null&&logs.size()>0){
				user.setLoginlog(logs.get(0));
			}
			List<Loginlog> onlinelogs=loginlogService.findLogsOnline(user.getId());
			if(onlinelogs!=null&&onlinelogs.size()>0){
				user.setLastloginlog(onlinelogs.get(0));
			}

		}
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		List<Organinfo> organinfos=organinfoService.findAll();
		model.addAttribute("organinfos", organinfos);
		return "/sys/usermgr/onlinelist";
	}



	/**
	 * 解锁账户
	 */

	@RequestMapping(value = "/unlock/{id}/{isunlock}", method = RequestMethod.POST)
	@ResponseBody
	public String unlockuserinfo(@PathVariable String id,@PathVariable String isunlock, HttpServletRequest request,
								 HttpSession session) {
		loginlogService.saveloginlog(url+"/unlock",session, request,Constantval.LOGIN_VISIT);
		User user=userService.find(id);
		if(null!=user) {
			RetryLimitHashedCredentialsMatcher.unlockAccount(user.getLoginname());
			if(null!=user) {
				user.setIsunlock(isunlock);
				userService.update(user);
//				String sql = "update userinfo  set isunlock='"+isunlock+"' where loginname='"+user.getLoginname()+"'";
//				queryService.executeSql(sql);
			}

		}else {
			return Constantval.CONSTANT_FAIL;
		}
		return Constantval.CONSTANT_SUCCESS;
	}

	/**
	 * 锁住人员信息查询
	 */
	@RequestMapping(value="/unlock/list",method = RequestMethod.GET)
	public String unlocklistman(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable, ModelMap model,
						  @RequestParam(required = false) String loginname, @RequestParam(required = false) String organname,
						  HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/unlock/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("loginname",loginname);
		model.addAttribute("organname",organname);
		List<Object>  list=userService.findlock(loginname,organname);
		List<User>  newlist=new ArrayList<User>();
		User user=null;
		if(null!=list&&list.size()>0){
			for(Object obj:list) {
				user=userService.find(obj+"");
				if(null!=user) {
					newlist.add(user);
				}
			}
		}
		model.addAttribute("list",newlist);
		List<Organinfo> organinfos=organinfoService.findAll();
		model.addAttribute("organinfos", organinfos);
		return "/sys/lock/list";
	}
	
}
