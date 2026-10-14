package com.ywjc.glsyjs.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.account.entity.User;
import org.springframework.web.filter.OncePerRequestFilter;

public class PasswdFilter extends OncePerRequestFilter {
	
	private static String[] str = null;
	
	static {
		str = new String[]{"/xyjsmgr/pwd","/xyjsmgr/changepwd"};
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring( request.getContextPath().length(),uri.length());
		Boolean flag = true;
		for(String s : str){
			if(uri.equals(s)){
				flag = false ;
				break;
			}
		}
		//changepwd
		if(flag){
			flag = false;
			HttpSession session = (HttpSession)request.getSession(false);
			Principal principal = (Principal)session.getAttribute("userinfo");
			if(principal!=null){
				User user = principal.getUser();
				if(user!=null){
					if(!"Y".equals(user.getIsnewpwd())){
						flag = true;
					}
				}
			}
		}
		if(flag){
			response.sendRedirect(request.getContextPath() + "/xyjsmgr/pwd");
		}else{
			filterChain.doFilter(request, response);
		}
	}
}
