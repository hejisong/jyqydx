package com.ywjc.glsyjs.interfaces.company.repositories;

import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CompanyEntityRepository extends BaseRepository<CompanyEntity, String> {


	@Query(value = "update company set phoneno= ?1 where companyid = ?2 ", nativeQuery = true)
	@Modifying
	void updateCompanyPhoneno(String phone, String companyid);

	@Query(value = "select c.* from company c where c.companyid = ?1", nativeQuery = true)
	public CompanyEntity queryCompanyByid(String companyid);


	@Query(value = "select c.* from company c where c.enterprisename like ?1 and (c.state !='0' or c.state is null) ", nativeQuery = true)
	public List<CompanyEntity> queryCompanyList(String enterprisename);

	@Query("select c from CompanyEntity c where c.enterprisename like ?1 and c.uscreditcode like ?2 and (c.state <>'0' or c.state is null) order by c.dateincorporation desc ")
	public Page<CompanyEntity> queryCompanyPage(String enterprisename, String uscreditcode, Pageable pageable);


	@Query("select c from CompanyEntity c where c.enterprisename like ?1 and (c.state <>'0' or c.state is null) order by c.dateincorporation desc ")
	public Page<CompanyEntity> queryCompanyPageAll(String enterprisename,   Pageable pageable);

	@Query(value = "select c.* from company c where c.enterprisename like ?1 or c.uscreditcode like ?1 and (c.state !='0' or c.state is null)", nativeQuery = true)
	public List<CompanyEntity> queryCompanyListBySearchName(String searchName);

	@Query(value = "select c.* from company c where c.phoneno = ?1 and (c.state !='0' or c.state is null) ", nativeQuery = true)
	public List<CompanyEntity> queryCompanyListByPhone(String phone);

	@Query(value = "select c.* from company c where c.enterprisename = ?1", nativeQuery = true)
	public CompanyEntity queryCompanyByname(String corpanme);

	@Query(value = "select c.* from company c where c.enterprisename = ?1 or c.uscreditcode = ?2 ", nativeQuery = true)
	public CompanyEntity queryCompany(String corpname, String uscreditcode);


	@Query(value = "select typesenterprises from company where typesenterprises is not null group by typesenterprises ", nativeQuery = true)
	public List<Object> querytypesenterprises();

	@Query(value = "select * from company where companyid in (?1) ", nativeQuery = true)
	public List<CompanyEntity> queryCompanys(List<Object> companyids);

	@Query("select c from CompanyEntity c where  (c.state <>'0' or c.state is null)  ")
	public List<CompanyEntity> queryAll();

	@Query(value = "select c.* from company c where c.enterprisename = ?1 and c.uscreditcode = ?2 ", nativeQuery = true)
	public CompanyEntity queryCompanyOne(String corpname,String uscreditcode);
}