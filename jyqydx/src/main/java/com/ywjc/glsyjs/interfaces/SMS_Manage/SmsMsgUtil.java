package com.ywjc.glsyjs.interfaces.SMS_Manage;

import java.rmi.RemoteException;

public class SmsMsgUtil {

    //短信注册module
    public static final String common_SMS = "common";  //一般业务
    public static final String regist_SMS = "regist";  //注册
    public static final String login_SMS = "login";  //登录
    public static final String updatePwd_SMS = "updatePwd";  //忘记密码
    public static final String checkMobile_SMS = "checkMobile";  //验证当前手机号
    public static final String updateMobile_SMS = "updateMobile";  //修改手机号
    public static final String userRegistSuccess_SMS = "userRegistSuccess";  //注册成功
    public static final String userRegistFailed_SMS = "userRegistFailed";  //注册失败



    /**
     * SMS短信发送
     * @param type 发送类型：0短信单发；1短信群发
     * @param messageContent  短信内容（最长支持220个汉字）
     * @param sender  短信发送人（本系统固定为：移动政务）
     * @param receivers 短信接收号码（小灵通要加上区号，多个号码用半角分号“;”间隔）
     * @param singn  短信签名（最长25个汉字，本系统固定为：智慧建邺）
     * @return
     */
    public static String smsMsgSend(String type, String messageContent, String sender, String receivers, String singn) throws RemoteException {
        String execSendMessage = "短信发送失败！";
        SendShortMessage sendShortMessage = new SendShortMessage();
        SendShortMessageSoap sendShortMessageSoap = sendShortMessage.getSendShortMessageSoap();

        if("0".equals(type)){
            execSendMessage = sendShortMessageSoap.execSendMessage(messageContent, sender, receivers, singn);
        }else if("1".equals(type)){
            execSendMessage = sendShortMessageSoap.execSendBatchMessage(messageContent, sender, receivers, singn);
        }

        System.out.println("SMS短信推送返回数据,请求手机号：" + receivers + "：--------------------------------------------" + execSendMessage);
        return execSendMessage;
    }





	public static void main(String[] args) throws RemoteException {
        SendShortMessage sendShortMessage = new SendShortMessage();
//		sendShortMessage.setEndpoint("http://198.18.129.152:90/Webservice/SendShortMessage.asmx?wsdl");
        SendShortMessageSoap sendShortMessageSoap = sendShortMessage.getSendShortMessageSoap();
		String messageContent = "本条短信为系统测试短信，无需处理回复，请忽略。如有打扰，请包涵。";  //短信内容（最长支持220个汉字）
		String sender = "移动政务";  //短信发送人（如 计生系统）
        String singn = "智慧建邺";  //短信签名（最长25个汉字 如 计生平台）
		/**  01
		 * 本方法用于发送短信到指定的手机号码，发送成功返回[成功],否则返回错误原因。messageContent：短信内容（最长支持220个汉字）
		 * sender：短信发送人（如 计生系统） receivers： 短信接收号码（小灵通要加上区号） singn： 短信签名（最长25个汉字
		 * 如 计生平台）
		 */
//        String receivers = "15656561462";  //短信接收号码（小灵通要加上区号）
//		String execSendMessage1 = sendShortMessageSoap.execSendMessage(messageContent, sender, receivers, singn);
//      System.out.println(execSendMessage1);




		/**  02
		 * 本方法用于群送短信到指定的手机号码。messageContent：短信内容（最长支持220个汉字） sender：短信发送人（如
		 * 计生系统） receivers： 短信接收号码（小灵通要加上区号，多个号码用半角分号“;”间隔） singn： 短信签名（最长25个汉字
		 * 如 计生平台）
		 */
        String receivers = "15656561462;15366186986;15366186995";  //短信接 收号码（小灵通要加上区号）
        String execSendMessage2 = sendShortMessageSoap.execSendBatchMessage(messageContent, sender, receivers, singn);
        System.out.println(execSendMessage2);

	}
}
