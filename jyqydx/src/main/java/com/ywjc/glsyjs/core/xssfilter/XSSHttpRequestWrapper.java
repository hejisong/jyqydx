package com.ywjc.glsyjs.core.xssfilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.centit.support.network.HtmlFormUtils;


/**
 * @author zhangxi
 * @date
 * @describe request信息封装类，用于判断、处理request请求中特殊字符
 */
public class XSSHttpRequestWrapper extends HttpServletRequestWrapper {

	/**
	 * 封装http请求
	 * 
	 * @param request
	 */
	public XSSHttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		name = HtmlFormUtils.htmlValue(name);
		String value = super.getHeader(name);
		value = HtmlFormUtils.htmlValue(value);
		// 若开启特殊字符替换，对特殊字符进行替换
		if (XSSSecurityConfig.REPLACE) {
			XSSSecurityManager.securityReplace(name);
		}
		return value;
	}

	@Override
	public String getParameter(String name) {
		name = HtmlFormUtils.htmlValue(name);
		String value = super.getParameter(name);
		value = HtmlFormUtils.htmlValue(value);
		// 若开启特殊字符替换，对特殊字符进行替换
		if (XSSSecurityConfig.REPLACE) {
			XSSSecurityManager.securityReplace(name);
		}
		return value;
	}

	/**
	 * 没有违规的数据，就返回false;
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkHeader() {
		Enumeration<String> headerParams = this.getHeaderNames();
		while (headerParams.hasMoreElements()) {
			String headerName = headerParams.nextElement();
			headerName = HtmlFormUtils.htmlValue(headerName);
			String headerValue = this.getHeader(headerName);
			headerValue = HtmlFormUtils.htmlValue(headerValue);
			if (XSSSecurityManager.matches(headerValue)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 没有违规的数据，就返回false;
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkParameter() {
		Map<String, String[]> submitParams = this.getParameterMap();
		Set<String> submitNames = submitParams.keySet();
		for (String submitName : submitNames) {
			submitName = HtmlFormUtils.htmlValue(submitName);
			Object submitValues = submitParams.get(submitName);
			if (XSSSecurityManager.matches((String) submitName)) {
				return true;
			}
			if (submitValues instanceof String) {
				submitValues = HtmlFormUtils.htmlValue((String) submitValues);
				if (XSSSecurityManager.matches((String) submitValues)) {
					return true;
				}
			} else if (submitValues instanceof String[]) {
				for (String submitValue : (String[]) submitValues) {
					submitValue = HtmlFormUtils.htmlValue(submitValue);
					if (XSSSecurityManager.matches((String) submitValue)) {
						return true;
					}

				}

			}
		}
		return false;
	}

	/**
	 * 没有违规的数据，就返回false; 若存在违规数据，根据配置信息判断是否跳转到错误页面
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public boolean validateParameter(HttpServletResponse response) throws ServletException, IOException {
		// 开始header校验，对header信息进行校验
		String url = super.getRequestURI().toString();
		if (XSSSecurityConfig.IS_CHECK_HEADER) {
			if (this.checkHeader()) {
				return true;
			}
		}
		// 开始parameter校验，对parameter信息进行校验
		if (XSSSecurityConfig.IS_CHECK_PARAMETER) {
			if (this.checkParameter()) {
				return true;
			}
		}
		return false;
	}

}
