package com.ywjc.glsyjs.sys.service.impl;

import javax.annotation.Resource;

import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.sys.entity.Loginrecord;
import com.ywjc.glsyjs.sys.repositories.LoginrecordRepository;
import com.ywjc.glsyjs.sys.service.LoginrecordService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

@Service("loginrecordServiceImpl")
public class LoginrecordServiceImpl extends BaseServiceImpl<Loginrecord, String>
		implements LoginrecordService {

	@Resource(name = "loginrecordRepository")
	private LoginrecordRepository repository;
	
	@Resource(name = "loginrecordRepository")
	public void setReposity(
			PagingAndSortingRepository<Loginrecord, String> repository) {
		super.setReposity(repository);
	}



}
