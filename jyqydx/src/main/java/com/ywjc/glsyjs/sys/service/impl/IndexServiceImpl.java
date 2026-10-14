package com.ywjc.glsyjs.sys.service.impl;

import com.ywjc.glsyjs.cache.CacheUtil;
import com.ywjc.glsyjs.cache.Cachemodel;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.PropertiesUtil;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.IndexService;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("indexServiceImpl")
public class IndexServiceImpl implements IndexService {


	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;





}
