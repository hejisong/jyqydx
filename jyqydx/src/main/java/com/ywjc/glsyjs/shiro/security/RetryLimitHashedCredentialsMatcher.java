package com.ywjc.glsyjs.shiro.security;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.ywjc.glsyjs.core.service.QueryService;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private static Cache<String, AtomicInteger> passwordRetryCache;
	@Resource(name = "queryServiceImpl")
	protected QueryService queryService;
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if(retryCount.incrementAndGet() ==500) {
			User user=userService.findByLoginname(username);
			if(null!=user) {
				String sql = "update userinfo  set isunlock='N' where loginname='"+username+"'";
				queryService.executeSql(sql);

			}
		}
		if (retryCount.incrementAndGet() > 500) {
			throw new ExcessiveAttemptsException();
		}
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			passwordRetryCache.remove(username);
		}
		return matches;
	}

	/**
	 * 根据用户名 解锁用户
	 * 
	 * @param username
	 * @return
	 */
	public static void unlockAccount(String username) {
		if (StringUtils.isNotBlank(username)) {
			passwordRetryCache.remove(username);
			
		}
	}
}
