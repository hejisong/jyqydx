/**
 * SmsService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ywjc.glsyjs.sms.client;

import com.ywjc.glsyjs.sms.client.model.DeliverMsg;
import com.ywjc.glsyjs.sms.client.model.RespInfo;
import com.ywjc.glsyjs.sms.client.model.SendRespMsg;

public interface SmsService extends java.rmi.Remote {
    public RespInfo auth(java.lang.String spid, java.lang.String passwd) throws java.rmi.RemoteException;
    public RespInfo terminate(java.lang.String passport) throws java.rmi.RemoteException;
    public RespInfo sendSms(java.lang.String passport, java.lang.String sequenceId, java.lang.String srcId, java.lang.String[] receiverList, java.lang.String content, java.lang.String sendtime, boolean reportFlag) throws java.rmi.RemoteException;
    public RespInfo sendActive(java.lang.String passport) throws java.rmi.RemoteException;
    public DeliverMsg[] getDeliver(java.lang.String passport) throws java.rmi.RemoteException;
    public SendRespMsg[] getSendResp(java.lang.String passport) throws java.rmi.RemoteException;
}
