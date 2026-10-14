package com.ywjc.glsyjs.account.service;

import com.ywjc.glsyjs.account.entity.Ewmlogin;
import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.core.service.BaseService;

import java.util.List;
import java.util.Set;

public interface EwmloginService extends BaseService<Ewmlogin, String> {

	public Ewmlogin findByEwmname(String ewmname);

	public Ewmlogin findByQid(String qid);
}
