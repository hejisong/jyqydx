package com.ywjc.glsyjs.sys.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Generalcode;

public interface GeneralcodeRepository extends
		PagingAndSortingRepository<Generalcode, String> {
	public Generalcode findByCodetableidAndCodeid(String codetableid,
			String codeid);
	
	@Query("select a from Generalcode a where a.isdisabled='0' order by a.usedfrequency")
	public List<Generalcode> findAllinfo();
	
	
	@Query("select a from Generalcode a where a.codetableid =?1")
	public Generalcode findByCodeid(String codeid);
		
	@Query("select a from Generalcode a where a.codetableid =?1 order by a.usedfrequency ")
	public Page<Generalcode> findByCodetableid(String codetableid,
			Pageable pageable);
	
	@Query("select a from Generalcode a where a.codetableid =?1 order by a.usedfrequency ")
	public List<Generalcode> findByCodetableid(String codetableid);

	@Query("select a from Generalcode a where a.generalcodename.codetablename like ?1 and a.codedesc like ?2 and a.ucode like ?3 and a.isdisabled like ?4")
	public Page<Generalcode> findByAll(String codetablename, String codedesc,
			String ucode, String isdisabled, Pageable pageable);

	@Query(value = " delete from Generalcode a where a.codetableid =?1 and a.codeid =?2")
	@Modifying
	public void deletecode(String codetableid, String codeid);
	
	@Query("select a from Generalcode a where a.generalcodename.codetablename = ?1")
	public List<Generalcode> findByCodetablename(String codetablename);
	
	@Query("select a from Generalcode a where a.codedesc like ?1")
	public Generalcode findByCodedesc(String codedesc);
	
	@Query("select a from Generalcode a where a.codetableid = ?1 and (a.ucode=?2 or a.codedesc=?3) and a.codeid <> ?4")
	public Generalcode  findGeneralcode(String codetableid,String ucode,String codetablename,String codeid);
	
	@Query("select a from Generalcode a where a.ucode=?1")
	public Generalcode  findByUcode(String ucode);
	
	/*@Query("select a from Generalcode a where a.codetableid = ?1 and a.codetablename=?2")
	public Generalcode findByCodetableidAndCodetablename(String codetableid,String codetablename);*/
	
	
	@Query("select a from Generalcode a where a.codetableid = ?1 order by a.codeid asc")
	public List<Generalcode> findGeneralcodes(String codetableid);
	
	@Query(value = "select max(codeid) from generalcode where codetableid=?1", nativeQuery = true)
	public Long findMaxNo(String codetableid);
	
	@Query("select a from Generalcode a where a.codedesc like ?1 and a.codetableid='0000000040'")
	public Generalcode findProvince(String codedesc);
	
	@Query(value = "select g.ucode,g.codedesc from generalcode g left join Generalcodename gn on g.codetableid=gn.codetableid where gn.codetablename=?1 order by usedfrequency ", nativeQuery = true)
	public List<Object> findObjectByCodetablename(String codetablename);
}