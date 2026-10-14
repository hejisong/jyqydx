package com.ywjc.glsyjs.shiro.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class RoleUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5099650468910170199L;

    public RoleUsernamePasswordToken(final String username, final String password, final boolean rememberMe) {
       super(username, password, rememberMe);
    }
}
