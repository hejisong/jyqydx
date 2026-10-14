package com.ywjc.glsyjs.core.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.util.DateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.HtmlUtils;

import com.ywjc.glsyjs.core.util.TimestampEditor;




@Controller
public abstract  class BaseController {
	
	/**
	 * 绑定所有字符串参数绑定，将Html字符转义
	 * 
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new PropertiesEditor() {

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue(StringUtils.hasText(text) ? HtmlUtils.htmlEscape(text) : text);
			}

			@Override
			public String getAsText() {
				return StringUtils.hasText((String) getValue()) ? String.valueOf(getValue()) : (String) getValue();
			}

		});
		binder.registerCustomEditor(Date.class, new DateEditor(true));
		binder.registerCustomEditor(Timestamp.class, new TimestampEditor(true));
	}
	
	
	
	@Resource(name = "queryServiceImpl")
	protected QueryService queryService;
	//操作日志
	public void insertSysLog(String ip, String userid, String username, String operModel,
			String operContent,String projectid) {
		Object uuid = null;
		String sql = "select rawtohex(sys_guid()) as uuid from dual";
		try {
			uuid = queryService.queryObject(sql);
		} catch (NoResultException e) {
			uuid=null;
			System.err.println("NoResultException Occured");
		}

		sql = "insert into SYSLOG ( LOGID,IP,login_name,user_name,OPER_MODEL,OPER_CONTENT,projectid,CREATE_TIME) values ('"
				+ uuid
				+ "', '"
				+ ip
				+ "','"
				+ userid
				+ "','"
				+ username
				+ "','"
				+ operModel
				+ "','"
				+ operContent
				+ "','"
				+ projectid
				+ "',sysdate"
				+")";

		queryService.executeSql(sql);

	}
	
	
	//操作日志
		public List<Object> queryObjectBySql(String projectid) {
			String sql= "select TO_CHAR(create_time,'YYYY-MM-DD HH24:MI:SS'),user_name,oper_model,oper_content from SYSLOG "
					+ "where projectid='"+projectid+"' order by create_time desc ";
			return queryService.queryObjectBySql(sql);
		}
	
	
	

}
