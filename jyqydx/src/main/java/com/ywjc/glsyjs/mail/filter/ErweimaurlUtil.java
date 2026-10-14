package com.ywjc.glsyjs.mail.filter;

import com.ywjc.glsyjs.core.util.ConfigUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class ErweimaurlUtil {
	private static Logger log = Logger.getLogger(ConfigUtil.class);
	private static  Properties prop = null;
	
	public static  String BASEURL = null;
	
	static {
		prop = new Properties();
		try {
			prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("centit.properties"));
			BASEURL = prop.getProperty("createerweimaurl");
	 		if(BASEURL==null){
	 			BASEURL="";
	 		}
		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		}
	}
	
	public static String getConfigVal(String key){
 		String propValue=prop.getProperty(key);
 		if(propValue==null){
 			propValue="";
 		}
		return propValue;
	}
}
