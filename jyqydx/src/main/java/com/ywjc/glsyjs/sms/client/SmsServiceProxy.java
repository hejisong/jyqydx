package com.ywjc.glsyjs.sms.client;

import com.ywjc.glsyjs.sms.client.model.DeliverMsg;
import com.ywjc.glsyjs.sms.client.model.RespInfo;
import com.ywjc.glsyjs.sms.client.model.SendRespMsg;
import org.apache.log4j.Logger;

public class SmsServiceProxy implements SmsService {
	private static Logger log = Logger.getLogger(SmsServiceProxy.class); 
  private String _endpoint = null;
  private SmsService smsService = null;
  
  public SmsServiceProxy() {
    _initSmsServiceProxy();
  }
  
  public SmsServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initSmsServiceProxy();
  }
  
  private void _initSmsServiceProxy() {
    try {
      smsService = (new SmsServiceServiceLocator()).getSmsService();
      if (smsService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)smsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)smsService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {
    	 log.error(serviceException.getMessage());
     	 log.info(serviceException);
    }
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (smsService != null)
      ((javax.xml.rpc.Stub)smsService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public SmsService getSmsService() {
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService;
  }
  
  public RespInfo auth(java.lang.String spid, java.lang.String passwd) throws java.rmi.RemoteException{
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService.auth(spid, passwd);
  }
  
  public RespInfo terminate(java.lang.String passport) throws java.rmi.RemoteException{
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService.terminate(passport);
  }
  
  public RespInfo sendSms(java.lang.String passport, java.lang.String sequenceId, java.lang.String srcId, java.lang.String[] receiverList, java.lang.String content, java.lang.String sendtime, boolean reportFlag) throws java.rmi.RemoteException{
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService.sendSms(passport, sequenceId, srcId, receiverList, content, sendtime, reportFlag);
  }
  
  public RespInfo sendActive(java.lang.String passport) throws java.rmi.RemoteException{
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService.sendActive(passport);
  }
  
  public DeliverMsg[] getDeliver(java.lang.String passport) throws java.rmi.RemoteException{
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService.getDeliver(passport);
  }
  
  public SendRespMsg[] getSendResp(java.lang.String passport) throws java.rmi.RemoteException{
    if (smsService == null)
      _initSmsServiceProxy();
    return smsService.getSendResp(passport);
  }
  
  
}