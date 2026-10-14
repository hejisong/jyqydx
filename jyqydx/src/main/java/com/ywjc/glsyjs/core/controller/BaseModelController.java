package com.ywjc.glsyjs.core.controller;

import java.io.Serializable;

import javax.validation.Valid;

import com.ywjc.glsyjs.core.util.Constantval;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ywjc.glsyjs.core.service.BaseService;


public abstract  class BaseModelController<T, ID extends Serializable> extends BaseController {
	

	protected BaseService<T, ID> baseService;
    
    private static final int DEFALUT_PAGE_SIZE = 15;

 
    protected String getModelName(){
    	ResolvableType resolvableType1 = ResolvableType.forClass(BaseModelController.class); 
    	return resolvableType1.getInterfaces()[0].getGeneric(1).resolve().getName();
    }

	/**
	 * List
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(
			@PageableDefault(page = 0, size = DEFALUT_PAGE_SIZE) Pageable pageable,
			ModelMap model) {
		Page<T> page = baseService.findPage(pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/"+getModelName()+"/list";
	}

	/**
	 * EditList
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editlist(
			@PageableDefault(page = 0, size = DEFALUT_PAGE_SIZE) Pageable pageable,
			ModelMap model) {
		Page<T> page = baseService.findPage(pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
		return "/"+getModelName()+"/listedit";
	}

	/**
	 * add
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String add() {	
		return "/"+getModelName()+"/new";
	}

	/**
	 * save
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid T T, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/"+getModelName()+"/new";
		}
		baseService.save(T);
		return "redirect:/"+getModelName()+"";
	}

	/**
	 * edit
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable ID id, ModelMap model) {
		T T = (T) baseService.find(id);
		if (T == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("T", T);
		return "/"+getModelName()+"/edit";
	}

	/**
	 * update
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String update(@Valid T T, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/"+getModelName()+"/edit";
		}		
		baseService.update(T);
		return "redirect:/"+getModelName()+"";
	}

	/**
	 * delete
	 */
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable ID id) {
		baseService.delete(id);
		return "redirect:/"+getModelName()+"";
	}

	/**
	 * show
	 */
	@RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
	public String show(@PathVariable ID id, ModelMap model) {
		T T = (T) baseService.find(id);
		if (T == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		model.addAttribute("T", T);
		return "/"+getModelName()+"/show";
	}
	
	
    
    
}
