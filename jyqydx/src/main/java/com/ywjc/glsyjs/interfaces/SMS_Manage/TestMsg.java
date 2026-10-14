package com.ywjc.glsyjs.interfaces.SMS_Manage;

public class TestMsg {

	/**
	 *
	 * @param type 推送类型：0单推；1群推
	 * @param messageContent
	 * @param sender
	 * @param receivers
	 * @param singn
	 * @return
	 */
	public static String smsMsgSend(String type, String messageContent, String sender, String receivers, String singn){
		String execSendMessage = "短信发送失败！";

		SendShortMessage sendShortMessage = new SendShortMessage();
		SendShortMessageSoap sendShortMessageSoap = sendShortMessage.getSendShortMessageSoap();

		if("0".equals(type)){
			execSendMessage = sendShortMessageSoap.execSendMessage(messageContent, sender, receivers, singn);
		}else if("1".equals(type)){
			execSendMessage = sendShortMessageSoap.execSendMessage(messageContent, sender, receivers, singn);
		}
		return execSendMessage;
	}

	public static void main(String[] args) {
		String randomCode = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
		String messageContent = "【智慧建邺】验证码：" + randomCode + "，3分钟有效，过期需重新申请，请勿向任何人提供您收到的验证码。如非本人操作，请忽略此短信。";  //短信内容（最长支持220个汉字）
		String sender = "移动政务";  //短信发送人（如 计生系统）
		String singn = "智慧建邺";  //短信签名（最长25个汉字 如 计生平台）


		String receivers = "17366256643";  //短信接收号码（小灵通要加上区号）
		String smsMsgSend = smsMsgSend("0", messageContent, sender, receivers, singn);
		System.out.println(smsMsgSend);

	}



}
