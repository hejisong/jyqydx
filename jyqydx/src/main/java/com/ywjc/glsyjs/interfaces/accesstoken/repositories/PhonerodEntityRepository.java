package com.ywjc.glsyjs.interfaces.accesstoken.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface PhonerodEntityRepository extends BaseRepository<PhonerodEntity, String> {


	@Query(value = "replace into phonerod(phone,rod,extime)VALUES(?1,?2,?3)",nativeQuery=true)
	@Modifying
	void replacePhoneRod(String phone, String rod, String extime);

	@Query(value = "select p.* from phonerod p where p.phone = ?1",nativeQuery=true)
	public PhonerodEntity queryPhoneRod(String phone);
}
