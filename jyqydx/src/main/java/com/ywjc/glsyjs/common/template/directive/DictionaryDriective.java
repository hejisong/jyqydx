package com.ywjc.glsyjs.common.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ywjc.glsyjs.sys.entity.Generalcode;
import com.ywjc.glsyjs.sys.entity.Generalcodename;
import com.ywjc.glsyjs.sys.service.GeneralcodeService;
import com.ywjc.glsyjs.sys.service.GeneralcodenameService;
import org.springframework.stereotype.Component;

import com.ywjc.glsyjs.core.template.directive.BaseDirective;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("dictionaryDriective")
public class DictionaryDriective  extends BaseDirective {

	private static final String VARIABLE_NAME = "dic_";

	@Resource(name = "generalcodenameServiceImpl")
	private GeneralcodenameService generalcodenameService;
	
	@Resource(name = "generalcodeServiceImpl")
	private GeneralcodeService generalcodeService;
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		List<Generalcodename> datacatalogList = generalcodenameService.findAll();
		List<Generalcode> datadictionaryList = generalcodeService.findAllinfo();
		for (Generalcodename datacatalog: datacatalogList){
			String catalogcode = datacatalog.getCodetablename().trim();
			String codetableid = datacatalog.getCodetableid().trim();
			List<Generalcode> catalogDictionaryList = new ArrayList<Generalcode>();
			for (Generalcode datadictionary: datadictionaryList){
				if (codetableid.equals(datadictionary.getCodetableid().trim())){					
					catalogDictionaryList.add(datadictionary);
				}
			}
		    setLocalVariable(VARIABLE_NAME+catalogcode, 
		    		catalogDictionaryList, env, body);
		}		
	}

}
