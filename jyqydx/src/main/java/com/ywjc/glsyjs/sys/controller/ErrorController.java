package com.ywjc.glsyjs.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ErrorController {
	
	/**
	 * 404
	 */
	@RequestMapping(value="/common/404",method = RequestMethod.GET)
	public String error404() {
		return "/error/404";
	}
	
	/**
	 * 500
	 */
	@RequestMapping(value="/common/500",method = RequestMethod.GET)
	public String error500() {
		return "/error/500";
	}
	
	/**
	 * unauthorizedlogin
	 */
	@RequestMapping(value="/common/unauthorizedlogin",method = RequestMethod.GET)
	public String unauthorizedlogin() {
		return "/error/unauthorizedlogin";
	}
}
