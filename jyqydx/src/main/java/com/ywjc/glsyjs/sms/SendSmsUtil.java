package com.ywjc.glsyjs.sms;

import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.sms.client.SmsService;
import com.ywjc.glsyjs.sms.client.SmsServiceService;
import com.ywjc.glsyjs.sms.client.SmsServiceServiceLocator;
import com.ywjc.glsyjs.sms.client.model.RespInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.centit.glsyjs.sms.client.model.SendRespMsg;

/** -- 96096短信 Web Service 客户端模板
 * @author qw.max
 *
 */


public class SendSmsUtil extends Thread {
	private static Logger log = Logger.getLogger(SendSmsUtil.class);  

	/**
	 *  -- 短信信息
	 */

	/**
	 *  -- WS对象
	 */
	private SmsService smsservice = null;
	
	/**
	 *  -- 授权码
	 */
	private String passport = null;
	
	/**
	 *  -- WS授权状态,如果授权成功为:true
	 */
	private boolean passportflag = false;
	
	/**
	 *  -- 是否需要发短信
	 */
	private boolean sendflag = true;	
	
	
	
	
	
//	public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public SmsService getSmsservice() {
		return smsservice;
	}


	public void setSmsservice(SmsService smsservice) {
		this.smsservice = smsservice;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}


	public boolean isPassportflag() {
		return passportflag;
	}


	public void setPassportflag(boolean passportflag) {
		this.passportflag = passportflag;
	}


	public boolean isSendflag() {
		return sendflag;
	}


	public void setSendflag(boolean sendflag) {
		this.sendflag = sendflag;
	}







	
	


 
	public void run(){
 		//创建新线程接收上行短信接收和状态报告
		ClentResponse resp = new ClentResponse(this);
		resp.start();//判断通行证是否有效线程启动
		while(sendflag==true){
			try {
 				while(smsservice == null){//创建WS对象直到成功创建.
					SmsServiceService smsService = new SmsServiceServiceLocator();
					smsservice = smsService.getSmsService();
				}
 				while(auth()!=0){//用户信息鉴权，鉴权成功后，返回通行证信息.如果授权失败,休眠20秒(必须)
 					sleep1(20*1000);
				}
 				passportflag=true;
				sendSmsInfo();//授权成功.发送短信
  			} catch (ServiceException e) {
//				System.out.println("Service Init Error:"+e);
  				 log.error(e.getMessage());
				 log.info(e);
				sleep1(20*1000);
			}
 		}
	}
	/**
	 *  -- 注销WS接口,废弃通行证
	 */
	private void setterminate(){
		try {
//			System.out.println(sdf.format(new Date())+"通行证"+passport+"作废=====");
			smsservice.terminate(passport);

		} catch (RemoteException e) {
			 log.error(e.getMessage());
			 log.info(e);
		}
	}	
 

	/** -- 短信发送方法,如果发送失败,则注销后重新授权再发送5次.

	 */
	public void sendSmsInfo(){//用户鉴权成功后，进行短信下发，通讯方式：SOAP，行业用户->短信平台

 	}
	

	
	/** -- 授权
	 * @return
	 */
	private int auth(){
		RespInfo info = null;
		try {
// 				用户名 密码
//				接口说明：用户信息鉴权，鉴权成功后，返回通行证信息；
//				通讯方式：SOAP，行业用户->短信平台；
 			info = smsservice.auth("501010", "5010010");
			if(info != null && info.getRespCode()==0){
				passport = info.getRespMessage();
				return 0;
			}
		} catch (RemoteException e) {
			 log.error(e.getMessage());
			 log.info(e);
		}
 		return -1;
	}
	
	public void sleep1(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			 log.error(e.getMessage());
			 log.info(e);
			 Thread.currentThread().interrupt();
		}
	}

	

	
}

/** -- 短信接收和状态报告线程
 * @author qw.max
 *
 */
class ClentResponse  extends Thread {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public SendSmsUtil sendsmsutil = null;
	
	public ClentResponse(SendSmsUtil thissendsmsutil){
		this.sendsmsutil = thissendsmsutil;
	}
	
	public void run() {
		//发送心跳包线程.
		Thread active = new Thread(new Active());
		active.start();//判断通行证是否有效心跳包线程启动
		while (sendsmsutil.isSendflag()==true) {
			if(!sendsmsutil.isPassportflag()){
				sleep1(10*1000);
				continue;
			}
			//10秒接收一次.
			sleep1(10*1000);
 			//getSendResp();
		}
	}
 
	
	
//// 获取应答状态接口	
////		接口说明：用户上行短信发送接口
////		通讯方式：SOAP，行业用户->短信平台,建议30秒获取一次数据
//
//	public void getSendResp(){
//		try{
//			SendRespMsg msg[]=sendsmsutil.smsservice.getSendResp(sendsmsutil.passport);
//			if(msg != null){
//				for(int i=0;i<msg.length;i++){
//					if("0".equals(msg[i].getResult())){
//						sendsmsutil.sendflag=false;
//						System.out.println(sdf.format(new Date())+"通行证"+sendsmsutil.passport+"[序列号"+msg[i].getSequenceid()+"]成功发送到用户手机，应该结束。***********************");
//					}else{
//						System.out.println(sdf.format(new Date())+"通行证"+sendsmsutil.passport+"[序列号"+msg[i].getSequenceid()+"]没有发送到用户手机，等待。。。。。。");
//					}
//	  			}
//			}else{
//				System.out.println(sdf.format(new Date())+"通行证"+sendsmsutil.passport+"的96096尚未返回数据，不清楚短信有没有发送到用户手机，等待。。。。。。");
//			}
//  		}catch(Exception e){System.out.println("Service getSendResp:"+e.getMessage());sendsmsutil.passportflag = false;}
// 	}
	
	public void sleep1(long time){
		try{Thread.sleep(time);}catch(Exception e){
			Logger.getLogger(SendSmsUtil.class).info(e.getMessage());
		}
	}
	
	
	
	//心跳包线程
	 class Active implements Runnable {
 		public void run() {
			while(sendsmsutil.isSendflag()==true){
				if(!sendsmsutil.isPassportflag()){//判断授权失败，等10秒再判断
  					sleep1(10*1000);continue;
				}
 				sleep1(30*1000);//判断授权成功，等30秒
				try{
					//sendActive
					//接口说明：为了保证用户身份及通行证有效，每隔1分钟自动触发此接口
					//通讯方式：SOAP，行业用户->短信平台
					RespInfo auth = sendsmsutil.getSmsservice().sendActive(sendsmsutil.getPassport());//心跳接口发送通行证，返回响应信息
					if(auth.getRespCode() != 0){//0是成功，其它失败
						sendsmsutil.setPassportflag(false);//响应失败即是授权失败
//						System.out.println(sdf.format(new Date())+"通行证"+sendsmsutil.passport+"失效==========================================================");
 					}else{
// 						System.out.println(sdf.format(new Date())+"通行证"+sendsmsutil.getPassport()+"有效中.......");
 						Logger.getLogger(SendSmsUtil.class).info(sdf.format(new Date())+"通行证"+sendsmsutil.getPassport()+"有效中.......");
 					}
				}				
				catch(RemoteException e){
//					System.out.println(sdf.format(new Date())+"通行证"+sendsmsutil.passport+"验证出错:"+e.getMessage());
					Logger.getLogger(SendSmsUtil.class).info(e.getMessage());
					sendsmsutil.setPassportflag(false);
				}
			}
		}
		
	}
	
}
