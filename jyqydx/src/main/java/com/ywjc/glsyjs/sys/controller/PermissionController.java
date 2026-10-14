package com.ywjc.glsyjs.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.core.util.Constantval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ywjc.glsyjs.account.entity.Permission;
import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.service.PermissionService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.sys.entity.Role_permission;
import com.ywjc.glsyjs.sys.service.Role_permissionService;


@Controller("permissionController")
@RequestMapping("/xyjsmgr/sys/sysinfo/permission")
public class PermissionController extends BaseController {

	
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;	
	 
	@Resource(name = "role_permissionServiceImpl")
	private Role_permissionService role_permissionService;
 
	@Resource(name = "permissionServiceImpl")
	private PermissionService permissionService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;


	private String url="/xyjsmgr/sys/sysinfo/permission";

	/**
	 * List
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list( ModelMap model, HttpServletRequest request,
						HttpSession session) {
		loginlogService.saveloginlog(url+"/list",session, request,Constantval.LOGIN_VISIT);
		List<Permission> permissionlist = permissionService.findAllPermissions();
		model.addAttribute("permissionlist", permissionlist);
 		return "/sys/permission/permissionlist";
	}

 
	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add(ModelMap model, HttpServletRequest request,
					  HttpSession session) {
		loginlogService.saveloginlog(url+"/new",session, request,Constantval.LOGIN_VISIT);
		Permission permission = new Permission();
		permission.setPermission("authc,perms[xyjsmgr]");
		model.addAttribute("permission", permission);
		List<Role> rolelist=roleService.findAllRoles();
		model.addAttribute("rolelist", rolelist);
		return "/sys/permission/new";
	}

	/**
	 * save
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(@Valid Permission permission, BindingResult bindingResult,String roleid, HttpServletRequest request,
					   HttpSession session) {
		loginlogService.saveloginlog(url+"/save",session, request,Constantval.LOGIN_VISIT);
		if (bindingResult.hasErrors()) {
			return "/sys/permission/new";
		}
		permission.setCreatedate(new Date());
		permission.setModifydate(new Date());
		permission.setId(KeyUtils.getKey());
		List<Role_permission> role_permissionlist=new ArrayList<Role_permission>() ;
		if (roleid!=null&&roleid.length()>0){
			String[]  roleids=roleid.split(",");
			for(String role_id:roleids){
				Role_permission  role_permis =new Role_permission();
				role_permis.setRole_id(role_id);;
				role_permis.setPermission_id(permission.getId());
				role_permissionlist.add(role_permis);
			}
		}
 		permissionService.savePermission(permission,role_permissionlist);
		return "redirect:/xyjsmgr/sys/sysinfo/permission/list";
	}

	/**
	 * eidt
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap model, HttpServletRequest request,
					   HttpSession session) {
		loginlogService.saveloginlog(url+"/edit",session, request,Constantval.LOGIN_VISIT);
		Permission permission = permissionService.find(id);
		if (permission == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("permission", permission);
		List<Role_permission> role_permissionlist=role_permissionService.findByPermission_id(id);
		model.addAttribute("role_permissionlist", role_permissionlist);
		List<Role> rolelist=roleService.findAllRoles();
		model.addAttribute("rolelist", rolelist);
		return "/sys/permission/edit";
	}

	/**
	 * update
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid Permission permission, String roleid,BindingResult bindingResult, HttpServletRequest request,
						 HttpSession session) {
		loginlogService.saveloginlog(url+"/update",session, request,Constantval.LOGIN_VISIT);
		if (bindingResult.hasErrors()) {
			return "/sys/permission/edit";
		}
		permission.setModifydate(new Date());
		if(permission.getCreatedate()==null){
			permission.setCreatedate(new Date());
		}
		List<Role_permission> role_permissionlist=new ArrayList<Role_permission>() ;
		if (roleid!=null&&roleid.length()>0){
			String[]  roleids=roleid.split(",");
			for(String role_id:roleids){
				Role_permission  role_permis =new Role_permission();
				role_permis.setRole_id(role_id);;
				role_permis.setPermission_id(permission.getId());
				role_permissionlist.add(role_permis);
			}
		}
		permissionService.updatePermission(permission,role_permissionlist);
		return "redirect:/xyjsmgr/sys/sysinfo/permission/list";
	}

	/**
	 * delete
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	String delete(@PathVariable String id, HttpServletRequest request,
				  HttpSession session) {
		loginlogService.saveloginlog(url+"/delete",session, request,Constantval.LOGIN_VISIT);
		Permission permission = permissionService.find(id);
		if (permission == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		permissionService.deletePermission(id);
		return "redirect:/xyjsmgr/sys/sysinfo/permission/list";
	}

	/**
	 * show
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	String show(@PathVariable String id, ModelMap model, HttpServletRequest request,
				HttpSession session) {
		loginlogService.saveloginlog(url+"/show",session, request,Constantval.LOGIN_VISIT);
		Permission permission = permissionService.find(id);
		if (permission == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("permission", permission);
		List<Role_permission> role_permissionlist=role_permissionService.findByPermission_id(id);
		model.addAttribute("role_permissionlist", role_permissionlist);
		List<Role> rolelist=roleService.findAllRoles();
		model.addAttribute("rolelist", rolelist);
		return "/sys/permission/show";
	}

}
