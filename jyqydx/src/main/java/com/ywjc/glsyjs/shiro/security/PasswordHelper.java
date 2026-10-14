package com.ywjc.glsyjs.shiro.security;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component("passwordHelper")
public class PasswordHelper {

	private String algorithmName = "md5";
	
	private int hashIterations = 2;

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public String getPassword(String salt, String password) {
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();
		return newPassword;
	}
	

}
