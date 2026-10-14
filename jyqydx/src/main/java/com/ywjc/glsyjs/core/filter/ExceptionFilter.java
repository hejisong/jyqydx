package com.ywjc.glsyjs.core.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;
  
/** 
 * 
 * @author lee 
 * 
 */  
public class ExceptionFilter extends OncePerRequestFilter {  

    FilterConfig filterConfig = null;


    public void destroy() {
        this.filterConfig = null;
    }
  


	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getRequestURI().endsWith("/maint/glmaint.html")||request.getRequestURI().endsWith("/maint/symaint.html")
				||request.getRequestURI().endsWith("/assets/images/head-cq-gl2.jpg")||request.getRequestURI().endsWith("/assets/images/head-cq-sy.jpg")){
			filterChain.doFilter(request, response);
			return;
		}else{
			if(request.getRequestURI().contains("/sy")){
				response.sendRedirect(request.getContextPath() + "/maint/symaint.html");
			}else{
				response.sendRedirect(request.getContextPath() + "/maint/glmaint.html");
			}
			
		}
		

	}

 
  
}  