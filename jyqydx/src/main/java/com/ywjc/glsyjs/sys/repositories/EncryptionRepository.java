package com.ywjc.glsyjs.sys.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Encryption;



public interface EncryptionRepository extends PagingAndSortingRepository<Encryption, String> {

	@Query("select c from Encryption c where c.entablename = ?1 and c.colmname = ?2 and c.recordid = ?3 ")
	public Encryption findbyrecordid(String tablename, String colmname, String recordid);

	@Query(value = "select encryptcode from Encryption c where c.entablename = ?1 and c.colmname = ?2 and c.recordid = ?3 ", nativeQuery = true)
	public String findbyColnameAndtablename(String tableName, String colname, String p_key_value);
	
 
}
