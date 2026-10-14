package com.ywjc.glsyjs.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.hutool.core.util.IdUtil;
import com.ywjc.glsyjs.core.util.Constantval;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.DecryptionService;
import com.ywjc.glsyjs.sys.service.OrganinfoService;

@Controller("usermgrController")
@RequestMapping("/sys/usermgr")
public class UsermgrController extends BaseController {

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;
	
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	@Resource(name = "passwordHelper")
	private PasswordHelper passwordHelper;
	
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	
	@Resource(name = "decryptionServiceImpl")
	private DecryptionService decryptionService;


	/**
	 * 人员信息查询
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String listman(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			ModelMap model,
			@RequestParam(required = false) String username,
			HttpSession session) {
		model.addAttribute("username",username);
		Principal principal = (Principal)session.getAttribute("userinfo");
		String organid = principal.getUser().getOrganid();
		String industry = principal.getCurrent_industry();
		username = StringUtils.isNotBlank(username)?"%"+username+"%":"%%";
		industry = StringUtils.isNotBlank(industry)?"%"+industry+"%":"%%";
		Page<User> page=userService.QueryOrganUsers(organid, username, principal.getLoginname(),pageable);
		if(page!=null&&page.getContent()!=null&&page.getContent().size()>0){
			for(User user:page.getContent()){
				List<Role> roleList = roleService.findByUserid(user.getId());
				if(roleList != null && roleList.size() > 0) {
					user.setRolename(roleList.get(0).getRolename());
				}
			}
		}
		//Organinfo org = organinfoService.find(organid);
		Organinfo organinfo = organinfoService.find(organid);		
		if(organinfo==null){
			return Constantval.CONSTANT_FAIL;
		}		
		String orgcode = organinfo.getOrgcode();
		List<Role> rolelist=roleService.findAllNotAdminRoles();
		model.addAttribute("page",decryptionService.decryptforPage2(page, pageable));
		model.addAttribute("pageable",pageable);
		model.addAttribute("rolelist",rolelist);
		return "/sys/gl/usermgr/list";
	}
	
	
	/**
	 * 保存人员信息
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody
	public String saveuser(@RequestParam(required = false) String username,
			@RequestParam(required = false) String loginname,
			@RequestParam(required = false) String linktel,
			@RequestParam(required = false) String linknum,
			@RequestParam(required = false) String linkemail,
			@RequestParam(required = false) String userId,
			@RequestParam(required = false) String roleid,
			@RequestParam(required = false) String accept_sms,
			
			HttpSession session){
		User user = new User();
		Principal principal = (Principal)session.getAttribute("userinfo");
		String organid = principal.getUser().getOrganid();
		if(StringUtils.isBlank(userId)){
			User usercheck=userService.findByLoginname(loginname);
			if(usercheck!=null){
				return Constantval.CONSTANT_FAIL;
			}
			user.setId(IdUtil.simpleUUID());
			user.setUsername(username);
			user.setLinkemail(linkemail);
			user.setLinknum(linknum);
			user.setLinktel(linktel);
			user.setAccept_sms(accept_sms);
			user.setLoginname(loginname);
			user.setOrganid(organid);
			//公路
			String salt = KeyUtils.getKey();
			user.setSalt(salt);
			user.setPassword(passwordHelper.getPassword(salt, "Jtyxjcyyjdd@2019"));
			user.setIsnewpwd("N");
			//user.setUsertype("0");
			user.setIsvaild(Constantval.CONSTANT_ISVALID);
			user.setModifyDate(new Date());
			user.setCreateDate(new Date());
			Organinfo organinfo = organinfoService.find(organid);
			if(organinfo==null){
				return Constantval.CONSTANT_FAIL;
			}

			userService.saveinfo(user,roleid);
		}else{
			User ouser = userService.find(userId);
			User usercheck=userService.findByLoginname(loginname);
	        if(usercheck!=null && !usercheck.getId().equals(ouser.getId())){
	        	return Constantval.CONSTANT_FAIL;
			}
			ouser.setUsername(username);
			ouser.setLoginname(loginname);
			ouser.setLinkemail(linkemail);
			ouser.setLinknum(linknum);
			ouser.setLinktel(linktel);
			ouser.setModifyDate(new Date());
			ouser.setAccept_sms(accept_sms);
			roleService.updateUserRole(roleid,ouser.getId());
			userService.update(ouser);
		}
		return Constantval.CONSTANT_SUCCESS;
	}
	
	@RequestMapping(value="/toEdit/{userId}",method = RequestMethod.GET)
	public String editUser(@PathVariable String userId,HttpSession session,ModelMap model){
		Principal principal = (Principal)session.getAttribute("userinfo");
		String organid = principal.getUser().getOrganid();
		Organinfo organinfo = organinfoService.find(organid);		
		if(organinfo==null){
			return Constantval.CONSTANT_FAIL;
		}		
		String orgcode = organinfo.getOrgcode();
		List<Role> rolelist=roleService.findAllNotAdminRoles();
		User user = userService.find(userId);
		List<Role> roleanolist =roleService.findByUserid(userId);
		String rolecode="";
		if(roleanolist!=null&&!roleanolist.isEmpty()&&roleanolist.size()>0){
			rolecode=roleanolist.get(0).getRolecode();
		}
		model.addAttribute("user",user);
		model.addAttribute("rolecode",rolecode);
		model.addAttribute("rolelist",rolelist);
		return "/sys/gl/usermgr/new";
	}
	
	/**
	 * 初始化人员登录密码
	 */
	@RequestMapping(value = "/resetpassword/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public String resetPassword(@PathVariable String userid) {
		User user=userService.find(userid);
		String salt = user.getSalt();
		user.setPassword(passwordHelper.getPassword(salt, "Jtyxjcyyjdd@2019"));
		user.setIsnewpwd("N");
		user.setModifyDate(new Date());
		userService.update(user);
		return Constantval.CONSTANT_SUCCESS;
	}
	
	
	/**
	 * 注销/启用人员信息
	 */
	@RequestMapping(value = "/forbidden/{userid}")
	public String forbidden(@PathVariable String userid) {
		User user=userService.find(userid);
		user.setIsvaild("1");
		user.setModifyDate(new Date());
		userService.update(user);
		return "redirect:/sys/usermgr/list";
	}
	
	
	
	
	/**
	 * 是否接受和发送短信
	 */
	@RequestMapping(value="/sms",method = RequestMethod.POST)
	@ResponseBody
	public String sms(@RequestParam(required = false) String id,
			@RequestParam(required = false) String checked,HttpSession session){
		User user=userService.find(id);
		if(null!=user) {
			user.setAccept_sms(StringUtils.isBlank(checked)?"":checked);
			userService.save(user);
			return Constantval.CONSTANT_SUCCESS;
		}else {
			return Constantval.CONSTANT_FAIL;
		}
	}
	
	
	
	

}
