package com.ywjc.glsyjs.shiro.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.ywjc.glsyjs.account.entity.Permission;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.PermissionService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

/**
 * The Spring/Hibernate sample application's one and only configured Apache
 * Shiro Realm.
 * 
 * <p>
 * Because a Realm is really just a security-specific DAO, we could have just
 * made Hibernate calls directly in the implementation and named it a
 * 'HibernateRealm' or something similar.
 * </p>
 * 
 * <p>
 * But we've decided to make the calls to the database using a UserDAO, since a
 * DAO would be used in other areas of a 'real' application in addition to here.
 * We felt it better to use that same DAO to show code re-use.
 * </p>
 */

public class LoginRealm extends AuthorizingRealm {

	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

	@Resource(name = "permissionServiceImpl")
	private PermissionService permissionService;


	public LoginRealm() {
		setName("LoginRealm");
		// setCredentialsMatcher(new AllowAllCredentialsMatcher());
	}

	// 认证信息，主要针对用户登录，（下文讲述在action或者controller登录过程代码）
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		RoleUsernamePasswordToken token = (RoleUsernamePasswordToken) authcToken;
		String username = (String) token.getUsername();
		if (username == null) {
			throw new UnknownAccountException();
		}

		User user = userService.findByLoginname(username);
		
		if (user == null) {
			throw new UnknownAccountException();
		}
		if ("N".equals(user.getIsunlock())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		List<Role> roles=roleService.findByUserid(user.getId());
		List<String> rolecodes=new ArrayList<String>();
		for(Role role:roles){
			rolecodes.add(role.getRolecode());
		}
		//用来标记已登录
		Principal principal = new Principal(user.getId(), user.getLoginname(),
				user.getUsername(), "1","3",rolecodes);
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				principal, 
				user.getPassword(), 
				ByteSource.Util.bytes(user
						.getSalt()), getName());
		return authenticationInfo;
	}

	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Principal principal = (Principal) principals.getPrimaryPrincipal();
		if (principal == null) {
			return null;
		}else if("1".equals(principal.getLoginstate())){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			Set<Role> roles = user.getRoles();
//			for (Role r : roles) {
//				info.addRole(r.getRolecode());
//			}
			List<Permission> permissions = permissionService
					.findUserPermissions(principal.getId());
			addPermissions(info, permissions);
			return info;
		}else{
			return null;
		}
	}

	private void addPermissions(SimpleAuthorizationInfo info,
			List<Permission> permissions) {
		List<String> authorities = new ArrayList<String>();

		for (Permission permission : permissions) {
			if (StringUtils.isEmpty(permission.getPermission())) {
				authorities.add(permission.getValue());
			} else {
				authorities.add(getValue(permission.getPermission(),
						"perms\\[(.*?)\\]"));
			}
		}
		info.addStringPermissions(authorities);

	}

	private String getValue(String permission, String regex) {
		if (permission == null) {
			return null;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(permission);

		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return permission;
		}

		// return null;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}



}
