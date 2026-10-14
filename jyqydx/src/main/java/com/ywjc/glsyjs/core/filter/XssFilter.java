package com.ywjc.glsyjs.core.filter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ywjc.glsyjs.core.util.Constantval;
import org.springframework.web.filter.OncePerRequestFilter;


public class XssFilter extends OncePerRequestFilter {
    FilterConfig filterConfig = null;

    protected static final String[] notFilter=new String[]{"content"};
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterConfig = filterConfig;
//    }



    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Enumeration<String> paraNames=request.getParameterNames();
        boolean flag = true;
        for(Enumeration e=paraNames;e.hasMoreElements();){
            String thisName=e.nextElement().toString();
//    	       String thisValue=request.getParameter(thisName);
            String thisValue = request.getParameter(thisName)==null?"": request.getParameter(thisName);
            if(!Arrays.asList(thisName).contains(thisName)){
                String value = cleanXSS(thisValue);
                if(value!=null&&value.equals("false")){
                    flag = false;
                }
            }
        }
        if(flag){
            String referer = request.getHeader("Referer");   //REFRESH
            String serverName = request.getServerName();
            if(null != referer&&referer.indexOf(serverName) < 0){
                request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
            }
            chain.doFilter(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/serviceError");
        }


    }

    private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		int oldValueLength=value.length();
		value = value.replaceAll("'", "");
		value = value.replaceAll("script", "");
		value = value.replaceAll("delete", "");
		value = value.replaceAll("select", "");
		value = value.replaceAll("update", "");
		value = value.replaceAll("drop", "");
		value = value.replaceAll("insert", "");
		value = value.replaceAll("export", "");
		value = value.replaceAll("insert", "");
		value = value.replaceAll("import", "");
		value = value.replaceAll("set", "");
		value = value.replaceAll("from", "");
		value = value.replaceAll("where", "");
		value = value.replaceAll("into", "");
		value = value.replaceAll(" or ", "");
		value = value.replaceAll(" and ", "");
		value = value.replaceAll("%", "");
		value = value.replaceAll("ltrim", "");
		value = value.replaceAll("load_file", "");
		value = value.replaceAll("union", "");
		value = value.replaceAll("\"", "");
		value = value.replaceAll("exec", "");
		value = value.replaceAll(";", "");
		value = value.replaceAll("||", "");
		value = value.replaceAll("&", "");
		value = value.replaceAll("$", "");
		value = value.replaceAll(";", "");
//		value = value.replaceAll("(", "");
//		value = value.replaceAll(")", "");
		value = value.replaceAll("CR", "");
		value = value.replaceAll("LF", "");
	//	value = value.replaceAll("+", "");
		//value = value.replaceAll("\\", "");
		int nowValueLength = value.length();
		if(nowValueLength!=oldValueLength){
			return "false";
		}
		return value;
	}



}