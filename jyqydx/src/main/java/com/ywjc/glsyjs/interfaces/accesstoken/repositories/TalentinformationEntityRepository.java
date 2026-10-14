package com.ywjc.glsyjs.interfaces.accesstoken.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.TalentinformationEntity;
import org.springframework.data.jpa.repository.Query;


public interface TalentinformationEntityRepository extends BaseRepository<TalentinformationEntity, String> {


	@Query("select t from TalentinformationEntity t where t.phoneno = ?1 and t.personnelname = ?2 and t.idnumber = ?3")
	TalentinformationEntity findByTalentinformation(String phoneno, String personnelname, String idnumber);

}
