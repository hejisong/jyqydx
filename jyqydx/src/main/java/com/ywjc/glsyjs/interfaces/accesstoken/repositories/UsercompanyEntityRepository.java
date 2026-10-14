package com.ywjc.glsyjs.interfaces.accesstoken.repositories;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.repository.BaseRepository;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UsercompanyEntityRepository extends BaseRepository<UsercompanyEntity, String> {


	@Query(value = "select distinct u.* from user_company u where u.username = ?1 and u.linknum = ?2 and u.linktel = ?3",nativeQuery=true)
	public List<UsercompanyEntity> findCompanyByDecryptInfo(String username, String linknum, String linktel);




	@Query(value = "select u.* from user_company u where u.username = ?1 and u.linknum = ?2 and u.linktel = ?3 and u.companyid =?4 ",nativeQuery=true)
	public UsercompanyEntity findCompanyByDecryptInfo(String username, String linknum, String linktel,String companyid);

	@Query("select u from User u where u.username = ?1 and u.linknum = ?2 and u.linktel = ?3")
	public List<User> findUserByDecryptInfo(String username, String linknum, String linktel);

	@Query("select u from User u where u.username = ?1 and u.linknum = ?2 and u.linktel = ?3")
	public User findUserFromNj(String username, String linknum, String linktel);

	@Query(value = "select c.companyid,c.enterprisename from company c where c.phoneno = ?1 ",nativeQuery = true)
	public List<UsercompanyEntity> findCompanyByPhone(String phone);

	@Query(value = "select c.companyid,c.enterprisename,c.uscreditcode,c.lrepresentative,c.phoneno from company c where c.enterprisename like ?1 order by c.number desc  limit 5;",nativeQuery = true)
	public List<Object[]>  findCompanyByCompanyname(String enterprisename);

	@Query(value = "select u.* from user_company u where u.username = ?1 and u.linktel=?2",nativeQuery = true)
	public List<UsercompanyEntity> findCompanyByPhoneandname(String username,String phone);

	@Query(value="delete from user_company where companyid = ?1 and islegalperson='0'",nativeQuery=true)
	@Modifying
	public void deleteBycompanyid(String companyid);

	@Query(value = "select u.* from user_company u where u.username = ?1 and u.linknum=?2",nativeQuery = true)
	UsercompanyEntity findCompanyByNameAndLinknum(String username, String linknum);

	@Query(value = "select u.* from user_company u where u.userid = ?1 and u.companyid=?2 limit 1",nativeQuery = true)
	UsercompanyEntity findByuseridAndCompanyid(String userid, String companyid);

	@Modifying
	@Query("update UsercompanyEntity  set islegalperson='2' where companyid=?1 and islegalperson<>'1'")
    int  updateByCompanyId(String companyid);



	@Query(value = "select u.* from user_company u where u.username = ?1 and u.linknum = ?2  and u.companyid =?3 ",nativeQuery=true)
	public UsercompanyEntity findCompany(String username, String linknum,String companyid);


	@Query(value = "select u.* from user_company u where u.companyid =?1 and u.username = ?2 ",nativeQuery = true)
	public List<UsercompanyEntity> findUserByCompany(String companyid,String username);
}
