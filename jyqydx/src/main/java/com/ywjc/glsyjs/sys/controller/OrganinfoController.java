package com.ywjc.glsyjs.sys.controller;

import cn.hutool.core.util.IdUtil;
import com.ywjc.datadd.entity.Policyuser;
import com.ywjc.datadd.service.PolicyuserService;
import com.ywjc.glsyjs.account.entity.*;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserRoleService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.common.validate.SysValidate;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller("organinfoController")
@RequestMapping("/sys/organinfo")
public class OrganinfoController extends BaseController {

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;
	
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	@Resource(name = "passwordHelper")
	private PasswordHelper passwordHelper;
	
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

	@Resource(name = "userRoleServiceImpl")
	private UserRoleService userRoleService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;


	@Resource(name = "policyuserServiceImpl")
	private PolicyuserService policyuserService;

	/**
	 * 查询所有组织/
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
					   ModelMap model,HttpSession session,
					   @RequestParam(required = false) String orgname,@RequestParam(required = false) String linkman,
					   @RequestParam(required = false) String linkphone,@RequestParam(required = false) String orgcode,
					   HttpServletRequest request

	) {
		loginlogService.saveloginlog("/sys/organinfo/list",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("orgname", orgname);
		model.addAttribute("orgcode", orgcode);
		orgname=StringUtils.isNotBlank(orgname)?"%"+orgname+"%":"%%";
		orgcode=StringUtils.isNotBlank(orgcode)?"%"+orgcode+"%":"%%";
		List<Organinfo> organs=organinfoService.findOrgnames();
		model.addAttribute("organs", organs);
		Organinfo organinfo = organinfoService.find("0");
		model.addAttribute("organinfo", organinfo);
		return "/sys/organinfo/list";
	}

	/**
	 * 新增区县机构信息
	 */
	@RequestMapping(value="/new")
	public String addOrganinfo(ModelMap model,HttpSession session,HttpServletRequest request)
	{
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/sys/organinfo/new",session, request,Constantval.LOGIN_VISIT);
		return "/sys/organinfo/new";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String addOrg(@Valid Organinfo organinfo, BindingResult bindingResult,HttpSession session,
						 ModelMap model,HttpServletRequest request){
		loginlogService.saveloginlog("/sys/organinfo/save",session, request,Constantval.LOGIN_VISIT);
		if (bindingResult.hasErrors()) {
			model.addAttribute("organinfo", organinfo);
			model.addAttribute("valid_error", bindingResult.getAllErrors());
			return "/sys/organinfo/new";
		}

		if(null!=organinfo.getOrgcode()){
			Organinfo organ=organinfoService.findByOrgcode(organinfo.getOrgcode());
			if(null != organ && !organinfo.getId().equals(organ.getId())){
				model.addAttribute("organinfo", organinfo);
				bindingResult.rejectValue("orgname", "机构代码唯一", "机构代码唯一");
				model.addAttribute("valid_error", bindingResult.getAllErrors());
				return "/sys/organinfo/new";
			}
		}

		if(null == organinfo.getId()){
			organinfo.setId(KeyUtils.getKey());
			organinfo.setCreatetime(new Date());
			organinfo.setUpdatedate(new Date());
			organinfoService.save(organinfo);
		}else{
			Organinfo organold=organinfoService.find(organinfo.getId());
			organold.setOrgname(organinfo.getOrgname());
			organold.setOrgcode(organinfo.getOrgcode());
			organold.setLinkman(organinfo.getLinkman());
			organold.setLinkphone(organinfo.getLinkphone());
			organold.setUpdatedate(new Date());
			organinfoService.update(organold);
		}

		return "redirect:/sys/organinfo/list";
	}

	/*
	 *编辑组织信息
	 */
	@RequestMapping(value="/edit/{organId}",method = RequestMethod.GET)
	public String editOrganinfo(@PathVariable String organId,ModelMap model,HttpServletRequest request,HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/sys/organinfo/edit",session, request,Constantval.LOGIN_VISIT);
		Organinfo organinfo = organinfoService.find(organId);
		model.addAttribute("organinfo", organinfo);
		return "/sys/organinfo/new";
	}


	/*
	 *编辑组织信息
	 */
	@RequestMapping(value="/findOrg/{organid}",method = RequestMethod.POST)
	@ResponseBody
	public Organinfo findOrg(@PathVariable String organid,ModelMap model,HttpServletRequest request,HttpSession session){
		Organinfo organinfo = organinfoService.find(organid);
		Organinfo porganinfo = organinfoService.find(organinfo.getSuperdepartment());
		if(porganinfo!=null){
			organinfo.setSuperorg(porganinfo.getOrgname());
		}
		model.addAttribute("organinfo", organinfo);
		return organinfo;
	}

	@RequestMapping(value="/updateorgan",method = RequestMethod.POST)
	@ResponseBody
	public String updateorgan(Organinfo organinfo,ModelMap model,HttpServletRequest request,HttpSession session){
		Organinfo organold=organinfoService.find(organinfo.getId());
		organold.setOrgname(organinfo.getOrgname());
		organold.setOrgcode(organinfo.getOrgcode());
		organold.setDisplayname(organinfo.getDisplayname());
		organold.setLinkman(organinfo.getLinkman());
		organold.setTelephonenumber(organinfo.getTelephonenumber());
		organold.setFacsimiletelephonenumber(organinfo.getFacsimiletelephonenumber());
		organold.setPostalcode(organinfo.getPostalcode());
		organold.setMail(organinfo.getMail());
		organold.setUpdatedate(new Date());
		organinfoService.update(organold);
		return Constantval.CONSTANT_SUCCESS;
	}





	/**
	 * 下级机构
	 */

	@RequestMapping(value="/childorg/{organId}",method = RequestMethod.GET)
	public String childorg(@PathVariable String organId,ModelMap model,HttpServletRequest request,HttpSession session){
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/sys/organinfo/childorg",session, request,Constantval.LOGIN_VISIT);
		Organinfo organinfo = organinfoService.find(organId);
		model.addAttribute("organinfo", organinfo);
		List<Organinfo> organinfos = organinfoService.findChildOrgs(organId);
		model.addAttribute("organinfos", organinfos);
		return "/sys/organinfo/childlist";
	}


	/**
	 * 失效区县机构信息
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	String cancelOrganinfo(@PathVariable String id,HttpServletRequest request,HttpSession session) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/sys/organinfo/delete",session, request,Constantval.LOGIN_VISIT);
		organinfoService.delete(id);
		return "redirect:/sys/organinfo/list";
	}


	/*
	 *检验组织机构代码
	 */
	@RequestMapping(value="/find/{id}/{orgcode}",method = RequestMethod.POST)
	@ResponseBody
	public String findOrganinfo(@PathVariable String id,@PathVariable String orgcode,ModelMap model){
		Organinfo organinfo = organinfoService.findByOrgcode(orgcode);
		//如果organId不为1，则为修改，如果查询到的id和organId一样，则是原来的自己，如果不一样，则orgcode在其他条数据中
		if(!"1".equals(id)) {
			if(null!=organinfo) {
				if(id.equals(organinfo.getId())) {
					return Constantval.CONSTANT_SUCCESS;
				}else {
					return Constantval.CONSTANT_ERROR;
				}
			}else {
				return Constantval.CONSTANT_SUCCESS;
			}

		}else{//如果organId为1，则是增加，如果查到数据库中有信息，则已经存在
			if(null!=organinfo) {
				return Constantval.CONSTANT_ERROR;
			}else {
				return Constantval.CONSTANT_SUCCESS;
			}
		}
	}







	/**
	 * 单位查询
	 */
	@RequestMapping(value="/orguser",method = RequestMethod.GET)
	public String list(
			@RequestParam(required=false) String orgname,
			@RequestParam(required=false) String username,
			HttpSession session,HttpServletRequest request,
			ModelMap model) {
		Principal principal = (Principal) session.getAttribute("userinfo");
		loginlogService.saveloginlog("/sys/organinfo/orguser",session, request,Constantval.LOGIN_VISIT);
		model.addAttribute("orgname",orgname);
		model.addAttribute("username", username);
		orgname = StringUtils.isBlank(orgname)?"%%":"%"+orgname+"%";
		username = StringUtils.isBlank(username)?"%%":"%"+username+"%";
		String organid = principal.getUser().getOrganid();
		Organinfo organinfo =  organinfoService.find(organid);
		model.addAttribute("organinfo", organinfo);
		List<Role> roles=roleService.findAllNotAdminRoles();
		model.addAttribute("roles", roles);
        List<Organinfo> organs=organinfoService.findOrgnames();
        model.addAttribute("organs", organs);
		return "/sys/usermgr/list";
	}

	/**
	 * 保存区县机构信息
	 */
	@RequestMapping(value="/organ",method = RequestMethod.POST)
	@ResponseBody
	public String save(Organinfo organinfo) {
   		ValidateBinding bindingResult=SysValidate.validate_organinfo(organinfo);
    	if (bindingResult.hasErrors()) {
    		return Constantval.CONSTANT_FAIL;
    	}
		if(StringUtils.isBlank(organinfo.getId())){
			organinfo.setId(KeyUtils.getKey());
			organinfo.setCreatetime(new Date());
			organinfo.setUpdatedate(new Date());
			organinfoService.save(organinfo);
		}else{
			Organinfo org = organinfoService.find(organinfo.getId());
			if(org==null){
				return Constantval.CONSTANT_FAIL;
			}
			org.setOrgname(organinfo.getOrgname());
			org.setLinkman(organinfo.getLinkman());
			org.setLinkphone(organinfo.getLinkphone());
			//org.setOrgaddr(organinfo.getOrgaddr());
			org.setUpdatedate(new Date());
			organinfoService.update(org);
		}

		return Constantval.CONSTANT_SUCCESS;
	}
	/*
	 * 查询单个单位信息
	 */
	@RequestMapping(value="/toEdit/{organId}",method = RequestMethod.GET)
	@ResponseBody
	public Object editOrg(@PathVariable String organId){
		Organinfo org = organinfoService.find(organId);
		return org;
	}

	/**
	 * 删除区县机构信息
	 */
	@RequestMapping(value = "/organ/{id}/delete", method = RequestMethod.GET)
	String cancelOrg(@PathVariable String id) {
		Organinfo org = organinfoService.find(id);
		org.setUpdatedate(new Date());
		organinfoService.update(org);
		return "redirect:/sys/organinfo/orguser";
	}

	/**
	 * 人员信息查询
	 */
	@RequestMapping(value="/organ/findusers",method = RequestMethod.GET)
	@ResponseBody
	public Object[] findusers( String orgname,String username,
			HttpServletRequest request,
			HttpSession session) {
		List<User> users = userService.findUser(orgname,username);
		Object[] data=new Object[4];
		if(users!=null&&users.size()>0){
			data[0]=users.get(0).getId();
			Organinfo organinfo=organinfoService.find(users.get(0).getOrganid());
			data[1]=organinfo.getId();
			if(StringUtils.isNotBlank(organinfo.getSuperdepartment())&&!"0".equals(organinfo.getSuperdepartment())){
				Organinfo organinfo1=organinfoService.find(organinfo.getSuperdepartment());
				data[2]=organinfo1.getId();
				if(StringUtils.isNotBlank(organinfo1.getSuperdepartment())&&!"0".equals(organinfo1.getSuperdepartment())){
					Organinfo organinfo2=organinfoService.find(organinfo1.getSuperdepartment());
					data[3]=organinfo2.getId();
				}
			}
		}
		return data;
	}
	
	/**
	 * 人员信息查询
	 */
	@RequestMapping(value="/organ/users/{organid}",method = RequestMethod.GET)
	@ResponseBody
	public List<Object> listman(
			@PathVariable String organid,
			@RequestParam(required=false) String isvalid,
			@RequestParam(required = false) String username,
			HttpServletRequest request,
			HttpSession session) {
		loginlogService.saveloginlog("/sys/organinfo/organ/users",session, request,Constantval.LOGIN_VISIT);
		username = StringUtils.isNotBlank(username)?"%"+username+"%":"%%";
		List<Object> page=userService.findOrganUsers(organid, username, isvalid);
		for (Object obj:page) {
			Object[] o=(Object[])obj;
			String linknum=(String)o[3];
			if(StringUtils.isNotBlank(linknum)){
				linknum=linknum.substring(0,3)+"***********"+linknum.substring(linknum.length()-4,linknum.length());
				o[3]=linknum;
			}
			List<Role> roles=roleService.findUserroles((String)o[0]);
			if(roles!=null&&roles.size()>0){
				String rolename="";
				for (Role role:roles) {
					rolename+=role.getRolename()+"、";
				}
				rolename=rolename.substring(0,rolename.length()-1);
				o[6]=rolename;
			}
		}
		
		return page;
	}


	/**
	 * 注销/启用人员信息
	 */
	@RequestMapping(value = "/organ/users/edit", method = RequestMethod.POST)
	@ResponseBody
	public Object[] edit( @Valid String userid,	HttpServletRequest request,
					  HttpSession session) {
		Object[] data=new Object[3];
		loginlogService.saveloginlog("/sys/organinfo/organ/users/edit",session, request,Constantval.LOGIN_VISIT);
		User user=userService.find(userid);
		List<Role> roles=roleService.findUserroles(userid);
		user.setRoles(null);
		if(roles!=null&&roles.size()>0){
			user.setRoleid(roles.get(0).getId());
			for (Role role:roles) {
				role.setUsers(null);
				role.setPermissions(null);
			}
		}
		data[0]=user;
		data[1]=roles;
		List<Role> rolelist=roleService.findAllNotAdminRoles();
		for (Role role:rolelist) {
			role.setUsers(null);
			role.setPermissions(null);
		}
		data[2]=rolelist;
		return data;
	}



	/**
	 * 保存人员信息
	 */
	@RequestMapping(value="/organ/users/save",method = RequestMethod.POST)
	@ResponseBody
	public String saveuser(User user,String roleid,	HttpServletRequest request,
						   HttpSession session){
		loginlogService.saveloginlog("/sys/organinfo/organ/users/save",session, request,Constantval.LOGIN_VISIT);
   		ValidateBinding bindingResult=SysValidate.validate_user(user);
    	if (bindingResult.hasErrors()) {
    		return Constantval.CONSTANT_FAIL;
    	}

		if(StringUtils.isBlank(user.getId())){
			User userhistory=userService.findByLoginname(user.getLoginname());
			if(userhistory!=null){
				return Constantval.CONSTANT_FAIL;
			}
			user.setId(IdUtil.simpleUUID());
			//公路
			String salt = KeyUtils.getKey();
			user.setSalt(salt);
			user.setPassword(passwordHelper.getPassword(salt, "000000"));
			user.setIsnewpwd("N");
			//user.setUsertype("0");
			user.setIsvaild(Constantval.CONSTANT_ISVALID);
			user.setModifyDate(new Date());
			user.setCreateDate(new Date());
			Organinfo organinfo = organinfoService.find(user.getOrganid());
			if(organinfo==null){
				return Constantval.CONSTANT_FAIL;
			}
			String unitname= organinfo.getOrgname();
			user.setUnitname(unitname);
			String orgcode = organinfo.getOrgcode();
			Set<Role> roles = new HashSet<Role>();
			user.setUsertype("5");
			if(StringUtils.isBlank(roleid)){
				roleid="4028099574b3ae4f0174b3b2b5dc0000";
			}else{
				String[] roleids=roleid.split(",");
				for (String rid:roleids) {
					//审批角色，验证钉钉
					Role role =roleService.find(rid);
					if("workcheck".equals(role.getRolecode())){
						Policyuser policyuser=policyuserService.findUser(user.getUsername(),user.getTelephonenumber());
						if(policyuser==null){
							return "noddper";
						}

					}
				}
			}
			userService.saveinfo(user,roleid);
		}else{
			User ouser = userService.find(user.getId());
			User usercheck=userService.findByLoginname(user.getLoginname());
	        if(usercheck!=null && !usercheck.getId().equals(ouser.getId())){
	        	return Constantval.CONSTANT_FAIL;
			}
			ouser.setUsername(user.getUsername());
			ouser.setLoginname(user.getLoginname());
			ouser.setLinknum(user.getLinknum());
			ouser.setLinktel(user.getLinktel());
			ouser.setLinkemail(user.getLinkemail());
			userRoleService.deleteByUserRole(user.getId());
			if(StringUtils.isBlank(roleid)){
				roleid="4028099574b3ae4f0174b3b2b5dc0000";
			}else{
				String[] roleids=roleid.split(",");
				for (String rid:roleids) {
					//审批角色，验证钉钉
					Role role=roleService.findById(rid);
					if("workcheck".equals(role.getRolecode())){
						Policyuser policyuser=policyuserService.findUser(user.getUsername(),user.getLinktel());
						if(policyuser==null){
							return "noddper";
						}

					}
				}

			}

			String[] roleids=roleid.split(",");
			for (String rid:roleids) {
				roleService.inserUserRole(user.getId(),rid);
			}

			ouser.setModifyDate(new Date());
			userService.update(ouser);
		}
		return Constantval.CONSTANT_SUCCESS;
	}
	
	/**
	 * 初始化人员登录密码
	 */
	@RequestMapping(value = "/organ/users/resetpassword/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public String resetPassword(@PathVariable String userid,HttpServletRequest request,
								HttpSession session) {
		loginlogService.saveloginlog("/sys/organinfo/organ/users/resetpassword",session, request,Constantval.LOGIN_VISIT);
		User user=userService.find(userid);
		String salt = user.getSalt();
		user.setPassword(passwordHelper.getPassword(salt, "000000"));
		user.setIsnewpwd("N");
		user.setModifyDate(new Date());
		userService.updateinfo(user);
		return Constantval.CONSTANT_SUCCESS;
	}
	
	
	/**
	 * 注销/启用人员信息
	 */
	@RequestMapping(value = "/organ/users/forbidden/{userid}/{isvaild}", method = RequestMethod.GET)
	@ResponseBody
	public String forbidden(@PathVariable String userid,@PathVariable String isvaild,HttpServletRequest request,
							HttpSession session) {
		loginlogService.saveloginlog("/sys/organinfo/organ/users/forbidden",session, request,Constantval.LOGIN_VISIT);
		User user=userService.find(userid);
		user.setIsvaild(isvaild);
		user.setModifyDate(new Date());
		userService.update(user);
		return Constantval.CONSTANT_SUCCESS;
	}



	
	
}
