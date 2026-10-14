package com.ywjc.glsyjs.account.service;

 import java.util.List;

import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpSession;

 import com.ywjc.glsyjs.account.entity.Loginlog;
import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.core.service.BaseService;

public interface LoginlogService extends BaseService<Loginlog, String> {

	/**
	 * @param datee
	 * @return
	 */
	List<Loginlog> findLoginlogByDate(String datee);

	/**
	 * @param principal
	 * @param request 
	 */
	void saveloginlog(String url, HttpSession session, HttpServletRequest request, String onoff);


	public List<Loginlog> findLogs(String userid);

	public List<Loginlog> findLogsOnline(String userid);

}
