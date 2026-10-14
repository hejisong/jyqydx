package com.ywjc.glsyjs.core.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64Util {
	//加密
	public static String encoder(String date ) {  
        Encoder encoder = Base64.getEncoder();
		byte[] data = encoder.encode(date.getBytes());
		return new String(data);
    }  
	//解码
	public static String decoder(String date ) {  
		Decoder decoder = Base64.getDecoder();
        byte[] data = decoder.decode(date);
		return new String(data);
    }  
}
