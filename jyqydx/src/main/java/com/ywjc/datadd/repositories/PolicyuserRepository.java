package com.ywjc.datadd.repositories;


import com.ywjc.datadd.entity.Policyuser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PolicyuserRepository extends PagingAndSortingRepository<Policyuser, String> {


    @Query("select p from Policyuser p where p.user_name = ?1   and p.mobile =?2  ")
    public Policyuser findUser(String name,String mobile);
	
}
