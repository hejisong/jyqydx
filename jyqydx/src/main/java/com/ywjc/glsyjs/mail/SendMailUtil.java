package com.ywjc.glsyjs.mail;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendMailUtil {
	private static Logger log = Logger.getLogger(SendMailUtil.class);

	public static void sendMessage(String to, String subject, String messageText) {
		new Thread(new Runnable() {
			@Override
			public void run() {

				try {
					sendMail(to, subject, messageText);
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage());
					log.info(e);
				} catch (MessagingException e) {
					log.error(e.getMessage());
					log.info(e);
				}

			}
		}).start();
	}

	public static void send(String to, String subject, String messageText) {
		new Thread() {
			public void run() {
				try {
					sendMail(to, subject, messageText);
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage());
					log.info(e);
				} catch (MessagingException e) {
					log.error(e.getMessage());
					log.info(e);
				}
			}
		}.start();

	}

	public static void sendMail(String to, String subject, String messageText)
			throws UnsupportedEncodingException, MessagingException {
		/*
		 * String from = "18652082362@163.com"; String fromUserPwd = parsePwd();
		 * Properties props = new Properties(); props.put("mail.smtp.host",
		 * "smtp.163.com"); props.put("mail.smtp.starttls.enable","true");
		 * //props.put("mail.smtp.port", "25"); props.put("mail.smtp.auth", "true");
		 * //props.put("mail.debug", "true"); Session mailSession =
		 * Session.getInstance(props,new EmailAuthenticator(from,fromUserPwd)); //
		 * System.out.println("发送信息:" + from + "----" + to); String nick =
		 * javax.mail.internet.MimeUtility.encodeText("重庆交委"); InternetAddress
		 * fromAddress = new InternetAddress(nick+"<"+from+">"); InternetAddress
		 * toAddress = new InternetAddress(to); MimeMessage message = new
		 * MimeMessage(mailSession); message.setFrom(fromAddress);
		 * message.addRecipient(RecipientType.TO, toAddress);
		 * message.setSentDate(Calendar.getInstance().getTime());
		 * message.setSubject(subject); message.setContent(messageText,
		 * "text/html;charset=utf-8"); Transport transport =
		 * mailSession.getTransport("smtp"); transport.connect("smtp.163.com","重庆交委",
		 * fromUserPwd); transport.send(message,
		 * message.getRecipients(RecipientType.TO)); //
		 * System.out.println("message yes");
		 */
		String from = getFrom();
		String fromUserPwd = getPassword();
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.cqjt.gov.cn");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.cqjt.gov.cn");
		props.put("mail.smtp.auth", "true");
		Session mailSession = Session.getInstance(props, new EmailAuthenticator(from, fromUserPwd));
//        System.out.println("发送信息:" + from + "----" + to);  
		String nick = javax.mail.internet.MimeUtility.encodeText("重庆交委");
		InternetAddress fromAddress = new InternetAddress(nick + "<" + from + ">");
		InternetAddress toAddress = new InternetAddress(to);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(fromAddress);
		message.addRecipient(RecipientType.TO, toAddress);
		message.setSentDate(Calendar.getInstance().getTime());
		message.setSubject(subject);
		message.setContent(messageText, "text/html;charset=utf-8");
		Transport transport = mailSession.getTransport("smtp");
		transport.connect("smtp.cqjt.gov.cn", "重庆交委", fromUserPwd);
		transport.send(message, message.getRecipients(RecipientType.TO));
	}

	public static String parsePwd() {
		String pAwAd = "asdqweqwqweqwewqw123456";
		StringBuffer sb = new StringBuffer();
		sb.append(pAwAd.substring(0, 3));
		sb.append(pAwAd.substring(pAwAd.length() - 6, pAwAd.length()));
		return sb.toString();
	}

	public static String getPassword() {
		return "11111111";
	}

	public static String getFrom() {
		return "jssc@cqjt.gov.cn";
	}

}
