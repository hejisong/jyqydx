package com.ywjc.glsyjs.core.filter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ywjc.glsyjs.core.util.Constantval;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * “跨站请求伪造”过滤
 * 
 * @author liu_k
 *
 */
public class CsrfFilter extends OncePerRequestFilter {

	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * 不需要过滤的uri
	 */
	private List<String> greenUriList = new ArrayList<String>() {
		private static final long serialVersionUID = 1616546967126520588L;
		{
			add(".css");
			add(".js");
			add(".png");
		}
	};

	@Override
	protected void initFilterBean() throws ServletException {
		FilterConfig filterConfig = getFilterConfig();
		// 安全地址
		String greenUriList = filterConfig.getInitParameter("greenUriList");
		if (greenUriList != null) {
			String[] greenUriArr = greenUriList.split(",");
			if (greenUriArr != null) {
				List<String> list = Arrays.asList(greenUriArr);
				this.greenUriList.addAll(list);
			}
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isLegalRequest(request)) {
			filterChain.doFilter(request, response);
		} else {
			String referer = request.getHeader("Referer");
			String url = request.getRequestURL().toString();
			logger.warn("疑似CSRF攻击：来源：[" + referer + "], 请求的url：[" + url + "]");
		}
	}

	/**
	 * 请求是否合法
	 * 
	 * @return
	 */
	private boolean isLegalRequest(HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		String uri = request.getRequestURI();

		// 不需要过滤的地址
		for (String greenUri : greenUriList) {
			if (uri.contains(greenUri)) {
				return true;
			}
		}

		// 直接请求（非外链）
		if (referer == null) {
			return true;
		}

		// 本站请求
		String serverName = request.getServerName();
		URL refererUrl;
		String refererHost = null;
		try {
			refererUrl = new URL(referer);
			refererHost = refererUrl.getHost();
			if (refererHost.equals(serverName)) {
				return true;
			} else {
				// 这里还不能断定是非法请求，比如有可能serverName得到的是内网地址，而referer是外网地址。所以继续往下判断。。
			}
		} catch (MalformedURLException e1) {
			logger.error("referer=" + referer, e1);
		}

		// 验证请求来源Host和登录时Host是否相同
		HttpSession session = request.getSession();
		String loginReferer = (String) session.getAttribute(Constantval.SESSION_LOGIN_REFERER);
		if (loginReferer != null) {
			try {
				URL url = new URL(loginReferer);
				String loginHost = url.getHost();
				boolean result = refererHost == null || refererHost.equals(loginHost);
				return result;
			} catch (MalformedURLException e) {
				logger.error("session中保存的loginReferer有误，loginReferer=" + loginReferer, e);
			}
		}
		return false;
	}

}
