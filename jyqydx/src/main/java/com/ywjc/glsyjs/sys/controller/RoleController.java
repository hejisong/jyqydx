package com.ywjc.glsyjs.sys.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.entity.UserRole;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.account.service.UserRoleService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.interfaces.policyfile.entity.PolicydetailEntity;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.core.controller.BaseController;

@Controller("roleController")
@RequestMapping("/xyjsmgr/sys/sysinfo/role")
public class RoleController extends BaseController{
	private static Logger log = Logger.getLogger(RoleController.class);
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "userRoleServiceImpl")
	private UserRoleService userRoleService;

	private String url="/xyjsmgr/sys/sysinfo/role";
	
	@RequestMapping(method = RequestMethod.GET)
	public String roleList(ModelMap model, HttpServletRequest request,
						   HttpSession session){
		loginlogService.saveloginlog(url,session, request, Constantval.LOGIN_VISIT);
		List<Role> list=roleService.findRoles();
		model.addAttribute("rolelist", list);		
		return "/sys/role/rolelist";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String addRole(@Valid Role role,BindingResult bindingResult, ModelMap model,
						  HttpServletRequest request, HttpSession session){
		loginlogService.saveloginlog(url+"/save",session, request, Constantval.LOGIN_VISIT);
		if (bindingResult.hasErrors()) {
			model.addAttribute("role", role);
			model.addAttribute("valid_error", bindingResult.getAllErrors());
			return "/sys/role/newrole";
		}
		System.out.println("角色名称="+role.getRolename());
		log.info("log角色名称=：" + role.getRolename());
		if(null == role.getId()){
			role = roleService.setRoleid(role);
			//roleService.saveRole(role2);	
		}
		if(null!=role.getRolename()){
			Role role2=roleService.findByRolecode(role.getRolecode());
			if(null != role2 && !role.getId().equals(role2.getId())){
				model.addAttribute("role", role);
				bindingResult.rejectValue("rolename", "角色编码唯一", "角色编码唯一");
				model.addAttribute("valid_error", bindingResult.getAllErrors());
				return "/sys/role/newrole";
			}
			}
		if(null==role.getCreate_Date()){
			role.setCreate_Date(new Date());
		}
		role.setModify_Date(new Date());
		if(null == role.getIsforbidden()){
			role.setIsforbidden("0");
		}
		role.setRoletype("1");
		roleService.saveRole(role);
		//roleService.updateRole(role);
		return "redirect:/xyjsmgr/sys/sysinfo/role?roletype="+role.getRoletype();
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updateRole(@Valid Role role,ModelMap model, HttpServletRequest request,
							 HttpSession session){
		loginlogService.saveloginlog(url+"/update",session, request, Constantval.LOGIN_VISIT);
		if(null!=role.getRolename()){
			Role role2=roleService.findByRolecode(role.getRolecode());
			if(null != role2){
				return "角色编码唯一！";
			}
		}
		if(null==role.getCreate_Date()){
			role.setCreate_Date(new Date());
		}
		role.setModify_Date(new Date());		
//		System.out.println(role.getRolename());
		roleService.updateRole(role);		
		List<Role> list=roleService.findAll();
		model.addAttribute("rolelist", list);		
		return "/sys/role/rolelist";
	}
	
	@RequestMapping(value="/isforbidden/{id}/{isforbidden}")
	@ResponseBody
	public String forbiddenRole(@PathVariable String id,@PathVariable String isforbidden, HttpServletRequest request,
								HttpSession session){
		loginlogService.saveloginlog(url+"/isforbidden",session, request, Constantval.LOGIN_VISIT);
		Role role = roleService.findById(id);
		role.setIsforbidden(isforbidden);
		roleService.updateRole(role);	
		return "SUCCESS";
	}
	
	@RequestMapping(value="/new")
	public String newRole(ModelMap model, HttpServletRequest request,
						  HttpSession session){
		loginlogService.saveloginlog(url+"/new",session, request, Constantval.LOGIN_VISIT);
		Role role = new Role();
		model.addAttribute("role", role);
		return "/sys/role/newrole";
	}
	
	@RequestMapping(value="/update/{id}")
	public String updateRole(@PathVariable String id, ModelMap model, HttpServletRequest request,
							 HttpSession session){
		loginlogService.saveloginlog(url+"/update",session, request, Constantval.LOGIN_VISIT);
		if(null != id){
			Role role =roleService.findById(id);
			model.addAttribute("role", role);			
		}		
		return "/sys/role/newrole";
	}


	@RequestMapping(value = "/setperson/{roleid}", method = RequestMethod.GET)
	public String setperson(@PathVariable String roleid,ModelMap model,HttpServletRequest request,
							HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/setperson",session, request,Constantval.LOGIN_VISIT);
		Role role = roleService.find(roleid);
//		List<User> users = userService.findByRolecode(role.getRolecode());
//		model.addAttribute("users", users);
		List<Organinfo> organs=organinfoService.findOrgnames();
		model.addAttribute("organs", organs);
		model.addAttribute("roleid", roleid);
		model.addAttribute("role", role);
		return "/sys/role/perlist";
	}


	/**
	 * 人员信息查询
	 */
	@RequestMapping(value="/findpers",method = RequestMethod.POST)
	@ResponseBody
	public  List<Object> findpers(
			 String organid, String roleid, HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/sys/organinfo/organ/users",session, request,Constantval.LOGIN_VISIT);
		List<Object> infos=userService.findOrganUsers(organid,"%%" , "0");
        Role role = roleService.find(roleid);
        List<User> users = userService.findByRolecode(role.getRolecode());
        for (Object o:infos) {
            Object[] obj=( Object[])o;
            for (User user:users) {
                if(user.getId().equals(obj[0])){
                    obj[3]=1;
                    break;
                }
            }

        }

		return infos;
	}

	/**
	 * （角色人员列表）
	 * 根据角色id查询人员信息
	 * @param
	 * @param roleid
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/findpersbyrole",method = RequestMethod.POST)
	@ResponseBody
	public  List<Object> findpersbyrole( String roleid,String u_username, HttpServletRequest request, HttpSession session) {
		loginlogService.saveloginlog("/sys/organinfo/organ/users",session, request,Constantval.LOGIN_VISIT);
		u_username=StringUtils.isBlank(u_username)?"%%":"%"+u_username+"%";
		List<Object> infos=userService.findUsersbyrole(roleid,u_username);
		Role role = roleService.find(roleid);
		return infos;
	}


	@RequestMapping(value="/saveperrole",method = RequestMethod.POST)
	@ResponseBody
	public String saveperrole( String ids,  String roleid){
		String[] idroles=ids.split(",");
		for (String idrole:idroles) {
			String[] id_role=idrole.split("-");
			String id=id_role[0];
			String role=id_role[1];
			//选择人员
			UserRole userRole=userRoleService.findUserRole(id,roleid);
			if("1".equals(role)){
				if(userRole==null){
					userRoleService.inserUserRole(id,roleid);
				}

			}else{
				if(userRole!=null){
					userRoleService.delete(userRole);
				}
			}


		}


		return Constantval.CONSTANT_SUCCESS;
	}


}
