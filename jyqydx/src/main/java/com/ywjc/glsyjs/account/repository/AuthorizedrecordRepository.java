package com.ywjc.glsyjs.account.repository;

import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuthorizedrecordRepository extends PagingAndSortingRepository<AuthorizedrecordEntity, String> {

    @Query(value="select a.* from authorizedrecord a  where a.companyid =?1 order by a.authorizedtime desc", nativeQuery=true)
    public List<AuthorizedrecordEntity> findlistBycompanyid(String companyid);

    @Query(value="select a.* from authorizedrecord a  where a.state='1' and a.companyid =?1   order by a.authorizedtime desc", nativeQuery=true)
    public List<AuthorizedrecordEntity> findlistBycompanyidLast(String companyid);

    @Query(value="select a from AuthorizedrecordEntity a  where a.companyid =?1 order by a.authorizedtime desc")
    public Page<AuthorizedrecordEntity> findpageListBycompanyid(String companyid , Pageable pageable);

    @Query(value = "select u.USERNAME,u.LINKTEL from userinfo u where u.UNITNAME ='秒触发政策服务系统' and (u.USERNAME like ?1 and u.LINKTEL like ?2) and u.islegalperson='0' order by u.USERNAME desc  limit 5;",nativeQuery = true)
    public List<Object[]>  findCompanyuserlistBynameorphone(String personname,String phonenum);

    @Query(value = "select u.USERNAME,u.LINKTEL from userinfo u where u.organid =?1 and (u.USERNAME like ?2 and u.LINKTEL like ?3) and u.islegalperson='0' order by u.USERNAME desc ",nativeQuery = true)
    public List<Object[]>  findCompanyuserSq(String companyid,String personname,String phonenum);


    @Query(value ="update authorizedrecord set state = '2',cancellationtime = now() where state = '1' and companyid=?1" , nativeQuery = true)
    @Modifying
    public void updateshqu(String companyid);

    @Query(value="select 1 from authorizedrecord a  where a.state =?2 and authorizedpersonid=?1  limit 1", nativeQuery=true)
    Integer findUserIdAndState(String userid, String state);


    @Query(value="select a from AuthorizedrecordEntity a  where a.companyid =?1 and a.authorizedpersonid=?2 and a.state='1' ")
    public List<AuthorizedrecordEntity> findsqList(String companyid,String userid);
}
