/*
 */
package com.ywjc.glsyjs.core.util;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Utils - Spring
 * 
 * @author
 * @version 1.0
 */
@Component("springUtils")
@Lazy(false)
public  class SpringUtils implements ApplicationContextAware,
		DisposableBean {

	/** applicationContext */
	private  ApplicationContext applicationContext;

	public SpringUtils() {
	}

	public void setApplicationContext(ApplicationContext applicationContext)  throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void destroy()  throws BeansException  {
		this.applicationContext = null;
	}

	public  ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public  Object getBean(String name) {
		Assert.hasText(name);
		return applicationContext.getBean(name);
	}

	public  <T> T getBean(String name, Class<T> type) {
		Assert.hasText(name);
		Assert.notNull(type);
		return applicationContext.getBean(name, type);
	}

	public  String getMessage(String code, Object... args) {
		LocaleResolver localeResolver = getBean("localeResolver",
				LocaleResolver.class);
		Locale locale = localeResolver.resolveLocale(null);
		return applicationContext.getMessage(code, args, locale);
	}

}