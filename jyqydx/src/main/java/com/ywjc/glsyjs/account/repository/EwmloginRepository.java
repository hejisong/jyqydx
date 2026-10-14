package com.ywjc.glsyjs.account.repository;

import com.ywjc.glsyjs.account.entity.Ewmlogin;
import com.ywjc.glsyjs.account.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Set;


public interface EwmloginRepository extends PagingAndSortingRepository<Ewmlogin, String> {

	public Ewmlogin findByEwmname(String ewmname);

	public Ewmlogin findByQid(String qid);

}
