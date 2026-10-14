package com.ywjc.glsyjs.sys.controller;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.shiro.security.LoginRealm;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import com.ywjc.glsyjs.shiro.security.RetryLimitHashedCredentialsMatcher;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.DecryptionService;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller("userController")
@RequestMapping("/xyjsmgr")
public class UserController extends BaseController {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

	@Resource(name = "passwordHelper")
	private PasswordHelper passwordHelper;
	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;
	@Resource(name = "queryServiceImpl")
	private QueryService queryService;

	@Resource(name = "decryptionServiceImpl")
	private DecryptionService decryptionService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	private String url="/xyjsmgr";
	
	@RequestMapping(value = "/initpwd/{data_source}", method = RequestMethod.GET)
	public String initpwd(String data_source, HttpServletRequest request,
						  HttpSession session) {
		loginlogService.saveloginlog(url+"/initpwd",session, request,Constantval.LOGIN_VISIT);
		String sql = null;
		if ("1".equals(data_source)) {
			sql = "select id,password from userinfo where data_source = '1'";
		} else if ("2".equals(data_source)) {
			sql = "select id,password from userinfo where data_source = '2'";
		}
		if (sql != null) {
			List<Object[]> users = queryService.queryObjectsBySql(sql);
			String salt = null;
			String id = null;
			String pwd = null;
			String p = null;
			for (Object[] u : users) {
				id = String.valueOf(u[0]);
				pwd = String.valueOf(u[1]);
				salt = KeyUtils.getKey();
				p = passwordHelper.getPassword(salt, pwd);
				User user = userService.find(id);
				if (null != user) {
					user.setPassword(p);
					user.setSalt(salt);
					userService.update(user);
				}
				
			}
		}
		return "common/pwd";
	}





	
}
