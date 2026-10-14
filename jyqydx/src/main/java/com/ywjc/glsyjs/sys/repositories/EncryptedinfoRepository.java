package com.ywjc.glsyjs.sys.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Encryptedinfo;



public interface EncryptedinfoRepository extends PagingAndSortingRepository<Encryptedinfo, String> {

	
	@Query(value = "select distinct  entablename,p_key from encryptedinfo c ", nativeQuery = true)
	public List<Object []> findTable();

	@Query(value = "select distinct colmname from encryptedinfo c where  c.entablename=?1 ", nativeQuery = true)
	public List<String> findbytablename(String tablename);

	@Query(value = "select distinct p_key from encryptedinfo c where  c.entablename=?1 ", nativeQuery = true)
	public String findbytablename2(String tablename);

	@Query(value = "select distinct colmname from encryptedinfo c where  c.beanname=?1 ", nativeQuery = true)
	public List<String> findbybeanName1(String beanName);

	@Query(value = "select distinct entablename from encryptedinfo c where  c.beanname=?1 ", nativeQuery = true)
	public String findbybeanName2(String beanName);

	@Query(value = "select distinct p_key from encryptedinfo c where  c.beanname=?1 ", nativeQuery = true)
	public String findbybeanName3(String beanName);

	@Query(value = "select distinct colmname from encryptedinfo c where  c.entablename=?1 and (c.isencrypted is null or c.isencrypted!='1')", nativeQuery = true)
	public List<String> findbytablename3(String tablename);

	@Query(value = "select distinct  entablename  from encryptedinfo c  ", nativeQuery = true)
	public List<String> findTableName();

	@Query(value = "select distinct colmname from encryptedinfo c where  c.entablename=?1 and  c.isencrypted ='1'  ", nativeQuery = true)
	public List<String> findbytablename4(String tablename);

//	@Query("select c from Encryptedinfo c where c.tablename = ?1 and c.colmname = ?2 and c.recordid = ?3 ")
//	public Encryption findbyrecordid(String tablename, String colmname, String recordid);
	
 
}
