package com.ywjc.glsyjs.account.service.impl;

import com.ywjc.glsyjs.account.entity.Ewmlogin;
import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.repository.EwmloginRepository;
import com.ywjc.glsyjs.account.repository.RoleRepository;
import com.ywjc.glsyjs.account.service.EwmloginService;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service("ewmloginServiceImpl")
public class EwmloginServiceImpl extends BaseServiceImpl<Ewmlogin, String> implements
		EwmloginService {
	
	
	@Resource(name = "ewmloginRepository")
	private EwmloginRepository repository;
	
	@Resource(name = "ewmloginRepository")
	public void setReposity(PagingAndSortingRepository<Ewmlogin, String> repository) {
		super.setReposity(repository);
	}


	@Override
	@Transactional(readOnly = true)
	public Ewmlogin findByEwmname(String ewmname) {
		return repository.findByEwmname(ewmname);
	}

	@Override
	@Transactional(readOnly = true)
	public Ewmlogin findByQid(String qid) {
		return repository.findByQid(qid);
	}

}
