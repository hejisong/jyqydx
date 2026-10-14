package com.ywjc.glsyjs.socketio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class LocalIp {

	private static final Logger log = LoggerFactory.getLogger(LocalIp.class);

	public static String LOCALIP = null;

	static {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			LOCALIP=addr.getHostAddress().toString();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		System.out.println(LOCALIP);
	}
}
