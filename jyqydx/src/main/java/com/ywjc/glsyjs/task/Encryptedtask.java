package com.ywjc.glsyjs.task;

import com.ywjc.glsyjs.sys.service.DecryptionService;
import com.ywjc.glsyjs.sys.service.EncryptionService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("encryptedtask")
public class Encryptedtask {
	
	private static Logger log = Logger.getLogger(Encryptedtask.class); 
	
	@Resource(name = "encryptionServiceImpl")
	private EncryptionService encryptionService;
	
	@Resource(name = "decryptionServiceImpl")
	private DecryptionService decryptionService;
	
	private  Integer NUM_COUNT=0;

	//定时任务-加密
//	public void initEncrypt(){
//		if(NUM_COUNT==0) {
//			System.out.println("开始执行");
//			encryptionService.encrypted();
//			NUM_COUNT++;
//			System.out.println("加密完成");
//		}	
//	}
	//定时任务-解密
//	public void initDecrypt(){
//		if(NUM_COUNT==0) {
//			decryptionService.Decrypted();
//			NUM_COUNT++;
//			System.out.println("加密完成");
//		}
//	}
}
