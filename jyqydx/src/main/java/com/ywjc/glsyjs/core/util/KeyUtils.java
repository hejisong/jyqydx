package com.ywjc.glsyjs.core.util;

import java.util.UUID;

public class KeyUtils {

	/**
	 * 获取32位的uuid作为主键
	 * @return
	 */
	public static String getKey(){
		String id = UUID.randomUUID().toString();
		id = id.replace("-", "");
		return id;
	}
	
	public static String getNumber20(){
		String str="";
		  for(int i=0;i<20;i++){
			   str+=String.valueOf((int)(Math.random()*9));
		  }
		 return str;
	}	
	
}
