package com.ywjc.glsyjs.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.core.util.Constantval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ywjc.glsyjs.account.entity.Permission;
import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.service.PermissionService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.sys.entity.Menu;
import com.ywjc.glsyjs.sys.entity.Role_permission;
import com.ywjc.glsyjs.sys.service.MenuService;
import com.ywjc.glsyjs.sys.service.Role_permissionService;

@Controller("menuController")
@RequestMapping("/xyjsmgr/sys/sysinfo/menu")
public class MenuController extends BaseController {

	@Resource(name = "menuServiceImpl")
	private MenuService menuService;
	
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	
	@Resource(name = "permissionServiceImpl")
	private PermissionService permissionService;
	
	@Resource(name = "role_permissionServiceImpl")
	private Role_permissionService role_permissionService;
	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;
	/**
	 * 查看菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getMenu(ModelMap model,@RequestParam(required = false) String isforbidden, HttpServletRequest request,
						  HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu",session, request,Constantval.LOGIN_VISIT);
		List<Menu> menulist = null;
		if(null!=isforbidden&&isforbidden.equals(Menu.CONSTENT_ISFORBIDDEN)){
			menulist = menuService.findALlMenus();
		}else{
			menulist = menuService.findVaidMenus();
		}
		model.addAttribute("menulist", menulist);
		model.addAttribute("isforbidden", isforbidden);
		return "/sys/menu/list";
	}
	
	/**
	 * 禁用菜单
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/forbidmenu/{id}",method = RequestMethod.GET)
	public String forbidMenu(@PathVariable String id,HttpServletRequest request,
							 HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/forbidmenu",session, request, Constantval.LOGIN_VISIT);
		menuService.forbidmenu(Menu.CONSTENT_ISFORBIDDEN, id);
		return "redirect:/xyjsmgr/sys/sysinfo/menu";
	}
	
	/**
	 * 启用菜单
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/unforbidmenu/{id}",method = RequestMethod.GET)
	public String unforbidMenu(@PathVariable String id,HttpServletRequest request,
							   HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/unforbidmenu",session, request, Constantval.LOGIN_VISIT);
		menuService.forbidmenu(Menu.CONSTENT_UNFORBIDDEN, id);
		return "redirect:/xyjsmgr/sys/sysinfo/menu";
	}
	
	/**
	 * 新建父菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/newfathermenu",method = RequestMethod.GET)
	public String newFathermenu(ModelMap model,HttpServletRequest request,
								HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/newfathermenu",session, request, Constantval.LOGIN_VISIT);
		Menu menu = menuService.newMenu();
		model.addAttribute("menu", menu);
		return "/sys/menu/form";
	}
		
	/**
	 * 新建子菜单
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/newchildmenu/{pid}",method = RequestMethod.GET)
	public String newChildmenu(@PathVariable String pid,ModelMap model,HttpServletRequest request,
							   HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/newchildmenu",session, request, Constantval.LOGIN_VISIT);
		Menu menu = menuService.newMenu();
		Menu fmenu = menuService.find(pid);
		model.addAttribute("menu", menu);
		model.addAttribute("fmenu", fmenu);
		return "/sys/menu/form";
	}
	
	/**
	 * 编辑父菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editfathermenu/{id}",method = RequestMethod.GET)
	public String editfathermenu(@PathVariable String id,ModelMap model,HttpServletRequest request,
								 HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/editfathermenu",session, request, Constantval.LOGIN_VISIT);
		Menu menu = menuService.find(id);
		model.addAttribute("menu", menu);
		return "/sys/menu/form";
	}
	
	/**
	 * 编辑子菜单
	 * @param id
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editchildmenu/{id}/{pid}",method = RequestMethod.GET)
	public String editchildmenu(@PathVariable String id,@PathVariable String pid,ModelMap model,HttpServletRequest request,
								HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/editchildmenu",session, request, Constantval.LOGIN_VISIT);
		Menu menu = menuService.find(id);
		Menu fmenu = menuService.find(pid);
		model.addAttribute("menu", menu);
		model.addAttribute("fmenu", fmenu);
		return "/sys/menu/form";
	}
		
	/**
	 * 保存菜单
	 * @param menu
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String savemenu(@Valid Menu menu,HttpServletRequest request,
						   HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/save",session, request, Constantval.LOGIN_VISIT);
		Menu menu2 = menuService.setMenuid(menu);
		menuService.save(menu2);
		return "redirect:/xyjsmgr/sys/sysinfo/menu";
	}
	
	/**
	 * 更新菜单
	 * @param menu
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updatemenu(@Valid Menu menu,RedirectAttributes redirectAttributes,HttpServletRequest request,
							 HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/update",session, request, Constantval.LOGIN_VISIT);
		menuService.update(menu);
		return "redirect:/xyjsmgr/sys/sysinfo/menu";
	}
	
	/**
	 * 校验子类业务是否已全部禁用
	 */
	@RequestMapping(value = "/ischildmenuforbidden/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public String isChildMenuForbidden(@PathVariable String pid,HttpServletRequest request,
									   HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/ischildmenuforbidden",session, request,Constantval.LOGIN_VISIT);
		List<Menu> list = menuService.findChildMenuUnforbidden(pid);
		if(null!=list&&list.size()>0){
			return "FAIL";
		}else{
			return "SUCCESS";
		}
	}
	
	/**
	 * 校验父类业务是否已启用
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/isfathermenuforbidden/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String isFatherMenuForbidden(@PathVariable String id,HttpServletRequest request,
										HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/isfathermenuforbidden",session, request,Constantval.LOGIN_VISIT);
		Menu cmenu = menuService.find(id);
		Menu fmenu = menuService.find(cmenu.getPid());
		if(fmenu.getIsforbidden().equals(Menu.CONSTENT_ISFORBIDDEN)){
			return "FAIL";
		}else{
			return "SUCCESS";
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveMenu2(@RequestParam(required = true) String roleid,@RequestParam(required = false)List<String> menuid,HttpServletRequest request,
							HttpSession session) {
		menuService.deleteByroleid(roleid);
		menuService.saveMenu(roleid, menuid);
		return "redirect:/xyjsmgr/sys/sysinfo/menu/"+ roleid+"/setmenu" ;
	}
	
	@RequestMapping(value = "/{roleid}/setmenu", method = RequestMethod.GET)
	public String setMenu(@PathVariable String roleid,ModelMap model,HttpServletRequest request,
						  HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/setmenu",session, request,Constantval.LOGIN_VISIT);
		List<Menu> menuexidlist = menuService.findUserRoleMenus(roleid);
		model.addAttribute("menuexidlist", menuexidlist);
		Role role = roleService.find(roleid);
		List<Menu> menulist = menuService.findByMenutype();
		model.addAttribute("menulist", menulist);
		model.addAttribute("roleid", roleid);
		model.addAttribute("role", role);
		return "/sys/menu/rolemenu";
	}
	
	@RequestMapping(value = "/{roleid}/setpermission", method = RequestMethod.GET)
	public String setpermission(@PathVariable String roleid,ModelMap model,HttpServletRequest request,
								HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/setpermission",session, request,Constantval.LOGIN_VISIT);
		List<Permission> permissionlist = permissionService.findAll() ;
 		Role role = roleService.find(roleid);
 		List<Role_permission> role_permissionlist=role_permissionService.findByRole_id(roleid);
 		model.addAttribute("permissionlist", permissionlist);
		model.addAttribute("roleid", roleid);
		model.addAttribute("role", role);
		model.addAttribute("role_permissionlist", role_permissionlist);
		return "/sys/menu/rolepermission";
	}
	
	
	@RequestMapping(value = "/savepermission",method = RequestMethod.POST)
	public String savepermission(
			@RequestParam(required = true) String roleid,
			@RequestParam(required = false)String permissionid,HttpServletRequest request,
			HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/sysinfo/menu/savepermission",session, request,Constantval.LOGIN_VISIT);
		List<Role_permission> role_permissionlist=new ArrayList<Role_permission>() ;
		if (permissionid!=null&&permissionid.length()>0){
			String[]  permissionids=permissionid.split(",");
			for(String perid:permissionids){
				Role_permission  role_permis =new Role_permission();
				role_permis.setRole_id(roleid);;
				role_permis.setPermission_id(perid);
				role_permissionlist.add(role_permis);
			}
		}
		role_permissionService.addRole_permissionlistByRole(roleid, role_permissionlist); 
		return "redirect:/xyjsmgr/sys/sysinfo/menu/"+ roleid+"/setpermission" ;
	}

}

