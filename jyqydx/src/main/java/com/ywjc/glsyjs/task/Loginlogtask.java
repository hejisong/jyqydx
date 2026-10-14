package com.ywjc.glsyjs.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.shiro.security.RetryLimitHashedCredentialsMatcher;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.core.util.Constantval;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ywjc.glsyjs.account.entity.Loginlog;
import com.ywjc.glsyjs.account.service.LoginlogService;

@Component("loginlogtask")
public class Loginlogtask {

	private static Logger log = Logger.getLogger(Loginlogtask.class);
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "loginlogServiceImpl")
	private LoginlogService loginlogService;

	@Resource(name = "queryServiceImpl")
	private QueryService queryService;

	public static final String file_save_path = ConfigUtil
			.getConfigVal("file_save_path");
	public static final String dirpath = file_save_path
			+ "\\ywjcdatafile\\loginlog\\gl\\";


	public void initLoginlog() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		String datee = sdf.format(date);

		String filepath = dirpath + "loginloggl." + datee;
		List<Loginlog> lolist = loginlogService.findLoginlogByDate(datee);
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {

			File file = new File(dirpath);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			if (lolist != null && !lolist.isEmpty()) {
				for (Loginlog l : lolist) {
					String log = l.getString();
					bw.write(log, 0, log.length() - 1);
					bw.newLine() ;

				}
			}

		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		} finally {
			if (bw != null) {
				try {
					bw.flush();
				} catch (IOException e) {
					log.error(e.getMessage());
					log.info(e);
				}
				try {
					bw.close();
				} catch (IOException e) {
					log.error(e.getMessage());
					log.info(e);
				}
			}
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					log.error(e.getMessage());
					log.info(e);
				}
			}


		}

	}
	//每天定时解锁帐号
	public void unlockloginmae() {
		List<Object>  list=userService.findlock("","");
		User user=null;
		if(null!=list&&list.size()>0){
			for(Object obj:list) {
				user=userService.find(obj+"");
				if(null!=user) {
					RetryLimitHashedCredentialsMatcher.unlockAccount(user.getLoginname());
					if(null!=user) {
						String sql = "update userinfo  set isunlock='Y' where loginname='"+user.getLoginname()+"'";
						queryService.executeSql(sql);
					}
				}
			}
		}
	}

}
