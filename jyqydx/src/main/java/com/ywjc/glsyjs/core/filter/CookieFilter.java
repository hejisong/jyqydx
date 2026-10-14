package com.ywjc.glsyjs.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFilter implements Filter{
    public void doFilter(ServletRequest request, ServletResponse response,    
            FilterChain chain) throws IOException, ServletException {    
        HttpServletRequest req = (HttpServletRequest) request;    
        HttpServletResponse resp = (HttpServletResponse) response;    
        resp.setHeader("X-Frame-Options", "SAMEORIGIN");
        resp.setHeader("Set-Cookie", "cookiename=value;Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly"); 
        chain.doFilter(req, resp);    
    }    
    
    public void destroy() {    
    }    
    
    public void init(FilterConfig arg0) throws ServletException {    
    }    

}
