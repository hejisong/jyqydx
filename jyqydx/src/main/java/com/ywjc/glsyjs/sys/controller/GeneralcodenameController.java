package com.ywjc.glsyjs.sys.controller;

import com.ywjc.glsyjs.account.service.LoginlogService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.sys.entity.Generalcode;
import com.ywjc.glsyjs.sys.entity.Generalcodename;
import com.ywjc.glsyjs.sys.service.GeneralcodeService;
import com.ywjc.glsyjs.sys.service.GeneralcodenameService;
import com.ywjc.glsyjs.sys.service.IndexService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Date;





/*
 * 数据字典
 */
@Controller("generalcodenameController")
@RequestMapping("/xyjsmgr/sys/generalcodename")
public class GeneralcodenameController extends BaseController {
	
	private static Logger log = Logger.getLogger(GeneralcodenameController.class); 

	
	@Resource(name = "generalcodenameServiceImpl")
	private GeneralcodenameService generalcodenameService;

	@Resource(name = "generalcodeServiceImpl")
	private GeneralcodeService generalcodeService;
	@Resource(name = "indexServiceImpl")
	private IndexService indexService;
	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;
	
	@Resource(name = "abstractDataSource")
	private ComboPooledDataSource dataSource;
	/**
	 * 查看数据字典列表
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE,sort = "codetableid") Pageable pageable,
					   ModelMap model, HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename",session, request,Constantval.LOGIN_VISIT);
		Page<Generalcodename> page = generalcodenameService.findPage(pageable);
		model.addAttribute("page", page);
		model.addAttribute("pageable", pageable);
	return "/sys/generalcode/list";
	}
	
	/**
	 * 条件查看数据字典列表
	 */
	@RequestMapping(value="/findList",method = RequestMethod.GET)
	public String Findlist(@PageableDefault(page = 0, size = Constantval.PAGE_SIZE) Pageable pageable,
			ModelMap model,@RequestParam(required = false) String codetablename,
						   @RequestParam(required = false) String codetabledesc,
						   @RequestParam(required = false) String isused,
						   HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/findList",session, request,Constantval.LOGIN_VISIT);
		 model.addAttribute("codetablename", codetablename);
		 model.addAttribute("codetabledesc", codetabledesc);
		 model.addAttribute("isused", isused);
		 codetablename = StringUtils.isBlank(codetablename) ? "%%" : "%" + codetablename + "%";
		 codetabledesc = StringUtils.isBlank(codetabledesc) ? "%%" : "%" + codetabledesc + "%";
		 isused = StringUtils.isBlank(isused) ? "%%" : "%" + isused + "%"; 
		 Page<Generalcodename> page = generalcodenameService.findByCodetablenameLikeAndCodetabledescLikeAndIsusedLike(codetablename, codetabledesc, isused, pageable);
		 model.addAttribute("page", page);
		 model.addAttribute("pageable", pageable);
	     return "/sys/generalcode/list";
	}
	
	
	/**
	 * 新增公共代码类别
	 */
	@RequestMapping(value="/new")
	public String newGeneralcodename(HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/new",session, request,Constantval.LOGIN_VISIT);
		return "/sys/generalcode/new";
	}
	
	/**
	 * 新增公共代码类别--保存/更新
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ResponseBody
	public String addGeneralcodename(@Valid Generalcodename generalcodename,BindingResult bindingResult, HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/save",session, request,Constantval.LOGIN_VISIT);
		if(generalcodename.getCodetableid().length()!=0){
			if(generalcodename.getCodetablename()!=null){
				Generalcodename generalcodename1=generalcodenameService.findByCodetablename(generalcodename.getCodetablename());
				if(generalcodename1!=null&&!generalcodename1.getCodetableid().equals(generalcodename.getCodetableid())){
					return "FAIL";
				}
				generalcodename.setUpdatedate(new Date());
				generalcodenameService.update(generalcodename);
				return "SUCCESS";
		}
		}

		Long max_No=generalcodenameService.findMaxNo();
		if(max_No==null){
			max_No=0l;
		}
		Long maxNo=max_No+1;
		String no="0000000000";
		generalcodename.setCodetableid(no.substring(0, 10-(maxNo.toString().length()))+maxNo);
		if(generalcodename.getCodetablename()!=null){
			Generalcodename generalcodename2=generalcodenameService.findByCodetablename(generalcodename.getCodetablename());
			if(generalcodename2!=null){
				return "FAIL";
			}
		}
		generalcodename.setUpdatedate(new Date());
		generalcodenameService.save(generalcodename);
		return "SUCCESS";
	}
	
	
	/**
	 * 公共代码类别--删除
	 */
	@RequestMapping(value = "/{codetableid}/delete", method = RequestMethod.GET)
	String deleteGeneralcodename(@PathVariable String codetableid, HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/delete",session, request,Constantval.LOGIN_VISIT);
		generalcodenameService.delete(codetableid);
		return "redirect:/xyjsmgr/sys/generalcodename";
	}
	
	/**
	 * 公共代码类别--编辑
	 */
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editGeneralcodename(@PathVariable String id, ModelMap model, HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/{id}",session, request,Constantval.LOGIN_VISIT);
		Generalcodename generalcodename = generalcodenameService.find(id);
		model.addAttribute("generalcodename", generalcodename);
		return "/sys/generalcode/new";
	}
	/**
	 * 公共代码类别--启用/禁用
	 */
	@RequestMapping(value="/setgeneralcodename/{codetableid}/{isused}",method = RequestMethod.POST)
	@ResponseBody
	public String setGeneralcodename(@PathVariable String isused,@PathVariable String codetableid, HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/setgeneralcodename",session, request,Constantval.LOGIN_VISIT);
		Generalcodename generalcodename=generalcodenameService.find(codetableid);
		generalcodename.setIsused(isused);
		generalcodenameService.update(generalcodename);
		return "SUCCESS";
	}
	
	/**
	 * 公共代码类别--代码管理
	 */
	@RequestMapping(value = "/manage/{codetableid}", method = RequestMethod.GET)
	public String codeManage(@PathVariable String codetableid, ModelMap model,
			@PageableDefault(page = 0, size = 100) Pageable pageable, HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/manage",session, request,Constantval.LOGIN_VISIT);
		Generalcodename generalcodename = generalcodenameService.find(codetableid);
		
		if (generalcodename == null) {
			return Constantval.ERRORPAGE_SOURCENOTFOUND;
		}
		Page<Generalcode> page = generalcodeService.findByCodetableid(codetableid, pageable);
		model.addAttribute("generalcodename", generalcodename);
		model.addAttribute("page", page);
			return "/sys/generalcode/codemanage";
		}
	

	/**
	 * 公共代码--新增
	 */
	
	@RequestMapping(value="/newgeneralcode/{codetableid}")
	public String newGeneralcode(@PathVariable String codetableid,ModelMap model, HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/newgeneralcode",session, request,Constantval.LOGIN_VISIT);
		Generalcodename generalcodename = generalcodenameService.find(codetableid);
		model.addAttribute("generalcodename", generalcodename);
		return "/sys/generalcode/newgeneralcode";
	}
	
	/**
	 * 公共代码--编辑
	 */
	
	@RequestMapping(value="/editgeneralcode/{codetableid}/{codeid}")
	public String editGeneralcode(@PathVariable String codetableid,@PathVariable String codeid,ModelMap model, HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/editgeneralcode",session, request,Constantval.LOGIN_VISIT);
		Generalcodename generalcodename = generalcodenameService.find(codetableid);
		Generalcode generalcode = generalcodeService.findByCodetableidAndCodeid(codetableid, codeid);
		model.addAttribute("generalcodename", generalcodename);
		model.addAttribute("generalcode", generalcode);
		return "/sys/generalcode/newgeneralcode";
	}
	
	/**
	 * 公共代码--保存/更新
	 */
	@RequestMapping(value="/savegeneralcode",method = RequestMethod.POST)
	@ResponseBody
	public String addGeneralcode(@Valid Generalcode generalcode,BindingResult bindingResult,
			@RequestParam(required = false) String codetableid,@RequestParam(required = false) String codeid,
								 HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/savegeneralcode",session, request,Constantval.LOGIN_VISIT);
        if(codeid.length()!=0){
        	Generalcode generalcode1=generalcodeService.findGeneralcode(codetableid, generalcode.getUcode(), generalcode.getCodedesc(),codeid);
        	if(generalcode1!=null && !generalcode1.getCodeid().equals(generalcode.getCodeid())){
				return "FAIL";
			}
        	generalcode.setUpdatedate(new Date());
    		generalcodeService.update(generalcode);
    		return "SUCCESS";
        }

		generalcode.setCodetableid(codetableid);

		Long max_No=generalcodeService.findMaxNo(codetableid);
		if(max_No==null){
			max_No=0l;
		}
		Long maxNo=max_No+1;
		String no="0000000000";
		generalcode.setCodeid(no.substring(0, 10-(maxNo.toString().length()))+maxNo);
		if(generalcode.getCodedesc()!=null){
			Generalcode generalcode2=generalcodeService.findGeneralcode(codetableid, generalcode.getUcode(), generalcode.getCodedesc(),generalcode.getCodeid());
			if(generalcode2!=null){
				return "FAIL";
			}
		}
		generalcode.setUpdatedate(new Date());
		generalcodeService.save(generalcode);
		return "SUCCESS";
	}
	
	/**
	 * 公共代码--启用/禁用
	 */
	@RequestMapping(value="/setgeneralcode/{codetableid}/{codeid}/{isdisabled}",method = RequestMethod.POST)
	@ResponseBody
	public String setGeneralcode(@PathVariable String codeid,@PathVariable String isdisabled,
								 @PathVariable String codetableid, HttpServletRequest request,HttpSession session){
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/setgeneralcode",session, request,Constantval.LOGIN_VISIT);
		Generalcode generalcode=generalcodeService.findByCodetableidAndCodeid(codetableid, codeid);
		generalcode.setIsdisabled(isdisabled);
		generalcodeService.update(generalcode);
		return "SUCCESS";
	}
	
	/**
	 * 公共代码--删除
	 */
	@RequestMapping(value = "/{codetableid}/deletegeneralcode/{codeid}", method = RequestMethod.GET)
	String deleteGeneralcode(@PathVariable String codeid,@PathVariable String codetableid, HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/deletegeneralcode",session, request,Constantval.LOGIN_VISIT);
		generalcodeService.deletecode(codetableid, codeid);
		return "redirect:/xyjsmgr/sys/generalcodename/manage/"+codetableid;
	}
	
	/**
	 * 刷新缓存
	 */
	@RequestMapping(value = "/flushCache", method = RequestMethod.GET)
	@ResponseBody
	String   flushCache(HttpServletRequest request,HttpSession session) {
		loginlogService.saveloginlog("/xyjsmgr/sys/generalcodename/flushCache",session, request,Constantval.LOGIN_VISIT);
        try{
        	log.info("正在使用连接数:"+dataSource.getNumBusyConnections());
        	log.info("空闲连接数:"+dataSource.getNumIdleConnections());
        	log.info("总连接数:"+dataSource.getNumConnections());
        	log.info("当前系统时间:"+new Date());
        }catch(SQLException e){
        	log.error(e.getMessage());
     		return "FAIL";
        	
        }
        try{
        	log.info("正在使用连接数:"+dataSource.getNumBusyConnections());
        	log.info("空闲连接数:"+dataSource.getNumIdleConnections());
        	log.info("总连接数:"+dataSource.getNumConnections());
        	log.info("当前系统时间:"+new Date());
        }catch(SQLException e){
        	log.error(e.getMessage());
     		return "FAIL";
        }
        try{
        	log.info("正在使用连接数:"+dataSource.getNumBusyConnections());
        	log.info("空闲连接数:"+dataSource.getNumIdleConnections());
        	log.info("总连接数:"+dataSource.getNumConnections());
        	log.info("当前系统时间:"+new Date());
        }catch(SQLException e){
        	log.error(e.getMessage());
     		return "FAIL";
        }
        try{
        	log.info("正在使用连接数:"+dataSource.getNumBusyConnections());
        	log.info("空闲连接数:"+dataSource.getNumIdleConnections());
        	log.info("总连接数:"+dataSource.getNumConnections());
        	log.info("当前系统时间:"+new Date());
        }catch(SQLException e){
        	log.error(e.getMessage());
     		return "FAIL";
        }
        try{
        	log.info("正在使用连接数:"+dataSource.getNumBusyConnections());
        	log.info("空闲连接数:"+dataSource.getNumIdleConnections());
        	log.info("总连接数:"+dataSource.getNumConnections());
        	log.info("当前系统时间:"+new Date());
        }catch(SQLException e){
        	log.error(e.getMessage());
     		return "FAIL";
        }

		return "SUCCESS";
	}
	
	@RequestMapping(value = "/flushQianyi", method = RequestMethod.GET)
	@ResponseBody
	String   flushQianyi() {

		return "SUCCESS";
	}
}
