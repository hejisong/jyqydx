package com.ywjc.glsyjs.core.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.core.util.JsonUtils;



public abstract  class BaseRestController<T, ID extends Serializable> extends BaseController {
	

	protected BaseService<T, ID> baseService;
    
    private static final int DEFALUT_PAGE_SIZE = 15;
    
    
    /**
	 * List
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Page<T> list(
			@PageableDefault(page = 0, size = DEFALUT_PAGE_SIZE) Pageable pageable,
			ModelMap model) {
		Page<T> page = baseService.findPage(pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return page;
	}


	/**
	 * save
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid T T, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errstring = JsonUtils.toJson(bindingResult.getAllErrors());
			return "{\"rs\":\"err\",\"err\":"+errstring+"}";
		}
		baseService.save(T);
		return "{\"rs\":\"ok\"}";
	}

	/**
	 * edit
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public T edit(@PathVariable ID id) {
		return baseService.find(id);
	}

	/**
	 * update
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@Valid T T, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errstring = JsonUtils.toJson(bindingResult.getAllErrors());
			return "{\"rs\":\"err\",\"err\":"+errstring+"}";
		}
		baseService.update(T);
		return "{\"rs\":\"ok\"}";
	}

	/**
	 * delete
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	String delete(@PathVariable ID id) {
		baseService.delete(id);
		return "{\"rs\":\"ok\"}";
	}

   

}
