package com.ywjc.glsyjs.sys.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywjc.glsyjs.sys.entity.Organinfo;

public interface OrganinfoRepository extends PagingAndSortingRepository<Organinfo, String> {

	@Query(value="select o from Organinfo o where o.orgcode = ?1")
	public Organinfo findByOrgcode(String orgcode);
	
	@Query(value ="select m.* from Organinfo m where m.status='1' order by m.edorder+0 ", nativeQuery = true)
	public List<Organinfo> findOrgnames();

	@Query("select m from Organinfo m where m.orgname like ?1 and m.orgcode like ?2 and (m.isleaf is null or m.isleaf='N') order by m.updatedate desc ")
	public Page<Organinfo> findOrgList(String organname, String orgcode, Pageable pageable);

	@Query(value ="select k.* from Organinfo k where k.orgname like ?1 and k.orgcode like ?2 order by k.edorder+0 ", nativeQuery = true)
	public List<Organinfo> findOrgList(String orgname,String orgcode );


	@Query(value ="select m.* from Organinfo m where m.orgname like ?1  order by m.edorder+0 ", nativeQuery = true)
	public List<Organinfo>  findOrgans(String orgname);

	@Query(value ="select m.* from Organinfo m where m.superdepartment = ?1  order by m.edorder+0 ", nativeQuery = true)
	public List<Organinfo> findChildOrgs(String orgid );


	@Query(value ="select m.* from Organinfo m where m.id in('196','340','384','879','2370')  order by m.edorder+0 ", nativeQuery = true)
	public List<Organinfo> findOrgs();

	@Query(value ="select m.* from Organinfo m where m.id in('196','340','384','879','2370')  order by m.edorder+0 ", nativeQuery = true)
	public List<Organinfo> findOpOrgs();
	
}
