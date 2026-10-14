package com.ywjc.glsyjs.account.repository;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends BaseRepository<User, String> {

    User findByUsername(String username);

    @Query("select u from User u where u.loginname = ?1 and u.isvaild = '0'  ")
    User findByLoginname(String loginname);

    @Query("select u from User u where u.loginname = ?1 ")
    User findByLoginnameOnly(String loginname);

    List<User> findByLoginnameIgnoreCase(String loginname);

    Page<User> findByLoginnameLikeIgnoreCase(String loginname, Pageable pageable);

    Page<User> findByUsernameLikeIgnoreCase(String name, Pageable pageable);

    @Query(value = "select a.* from Userinfo a inner join user_role  b on a.id = b.user_id "
            + " where b.role_id =?1", nativeQuery = true)
    List<User> findByRole_Id(String id);

    @Query(value = "select a.* from Userinfo a left join user_role  b  on a.id = b.user_id left join role  r   on r.id = b.role_id where r.rolecode =?1 and a.status='1' ", nativeQuery = true)
    public List<User> findByRolecode(String rolecode);

    @Query(value = "select count(id) from User")
    public Page<User> findPageById(Pageable pageable);


    @Query("select u from User u where u.organid = ?1 and u.isvaild = '0' order by u.createDate desc")
    List<User> findOrganUsersValid(String organid);

    @Query("select u from User u where u.organid = ?1 order by u.createDate desc")
    List<User> findOrganUsers(String organid);


    @Query(value = "select u.id,u.username,u.loginname,u.linknum,u.linktel,u.linkemail,'' as ROLENAME,u.isvaild,u.isunlock from Userinfo u where u.organid = ?1 and u.status='1' order by u.create_date desc", nativeQuery = true)
    List<Object> findUsersByOrgan(String organid);

    @Query(value = "SELECT u.id,u.username,u.loginname,o.id as orgid,o.ORGNAME,o.superdepartment FROM user_role ur left join Userinfo u on ur.USER_ID=u.id LEFT JOIN organinfo o on u.ORGANID=o.id WHERE ur.role_id=?1 and u.username like ?2 and u.status='1' ORDER BY u.create_date DESC", nativeQuery = true)
    List<Object> findUsersByrole(String roleid,String u_username);

    @Query(value = "select u.id,u.username,u.loginname,u.linknum,u.linktel,u.linkemail,rr.rolename,u.isvaild,u.isunlock from Userinfo u inner join USER_ROLE ur on u.id=ur.USER_ID inner join ROLE rr on rr.ID=ur.ROLE_ID where u.organid = ?1 and u.username like ?2 and u.isvaild = '0' and u.status='1'  order by u.create_date desc", nativeQuery = true)
    List<Object> findOrganUsersValid(String organid, String username);

    @Query(value = "select u.id,u.username,u.loginname,u.linknum,u.linktel,u.linkemail,rr.ROLENAME,u.isvaild,u.isunlock from Userinfo u inner join USER_ROLE ur on u.id=ur.USER_ID inner join ROLE rr on rr.ID=ur.ROLE_ID where u.organid = ?1 and u.username like ?2 and u.status='1' order by u.create_date desc", nativeQuery = true)
    List<Object> findOrganUsers(String organid, String username);

    @Query("select u from User u where  u.username like ?1 and u.unitname like ?2  and u.isvaild like ?3 order by u.createDate desc")
    public Page<User> findUsers(String username, String unitname, String isvaild, Pageable pageable);


    @Query("select u from User u where u.username like ?1 and u.loginname like ?2 and u.organid = ?3  order by u.createDate desc")
    Page<User> findOrganUsersAll(String username, String loginname, String organid, Pageable pageable);

    @Query("select u from User u where u.username like ?1 and u.loginname like ?2 and u.organid = ?3 and u.isvaild = ?4 order by u.createDate desc")
    Page<User> findOrganUsersByValid(String username, String loginname, String organid, String isvalid, Pageable pageable);


    @Query(value = "select u from User u where u.organid = ?1 and u.username like ?2  and u.loginname!=?3 and u.isvaild = '0' order by u.createDate desc")
    public Page<User> QueryOrganUsers(String organid, String username, String loginname, Pageable pageable);


    @Query("select u from User u where u.organid = ?1 and u.isvaild = '0' and  u.accept_sms='1' order by u.createDate desc")
    List<User> findOrganUsersSms(String organid);


    @Query("select u from User u where u.isunlock = 'N' and u.loginname  like ?1 ")
    public List<User> findlock(String loginname);

    @Query("select U from User U where U.id=?1")
    public User findByIdNotDecrypt(String id);

    @Query(value = "update userinfo set unitname =?1 where corpid = ?2 ", nativeQuery = true)
    @Modifying
    void updateCorpName(String corp_name, String corp_id);


    @Query("select u from User u where u.loginname like ?1 and u.unitname  like ?2 and u.isonline =?3 ")
    public List<User> findOnlineUsers(String loginname, String organname, String isonline);


    @Query("select u from User u where u.loginname like ?1 and u.unitname  like ?2 and u.isonline ='1' ")
    public Page<User> findPageOnlineUsers(String loginname, String organname, Pageable pageable);

    @Query("select u from User u where  (u.username  like ?1 or u.linknum =?2 or u.linktel = ?3)")
    public List<User> findusersinnameorlinknum(String username, String linknum, String linktel);

    @Query("select u from User u where  (u.username  = ?1 and u.linknum =?2 )")
    public List<User> findUserBynNameAndLinknum(String username, String linknum);


    @Query("select u from User u where u.username = ?1 and u.linktel  = ?2 ")
    public List<User> finduserbynameandphone(String username, String phone);

    @Query(value = "SELECT u.id,u.username,u.linknum,u.linktel FROM userinfo u WHERE u.username LIKE ?1 AND u.linknum IS NOT NULL", nativeQuery = true)
    public List<Object[]> findusersinname(String username);

    @Query(value = "select u.id,u.username,u.loginname,u.linknum,u.linktel,u.linkemail,'' as ROLENAME,u.isvaild,u.isunlock from Userinfo u where u.unitname like ?1 and u.username like ?2 order by u.create_date desc", nativeQuery = true)
    public List<Object> findUsers(String orgname, String username);


    @Query("select u from User u where u.unitname = ?1 and u.username  = ?2 ")
    public List<User> findUser(String orgname, String username);

    @Query("select u from User u where u.unitname = ?1  ")
    public List<User> findUserOrgname(String orgname);

    @Query("select u from User u where  u.username  = ?1 ")
    public List<User> findUserUsername(String username);

    @Query("select u from User u where  u.linknum  = ?1 ")
    public User findUserByLinknum(String linknum);


    @Query(value = "select u.* from userinfo u where  u.id  = ?1 and (u.datatype is null or u.datatype='0' or u.datatype='' ) limit 1", nativeQuery = true)
    User findByUserIdAndDataType(String userid);

    @Query(value = "select a.id,a.username,a.linknum,a.linktel,a.unitname from Userinfo a left join user_role  b  on a.id = b.user_id left join role  r   on r.id = b.role_id where r.rolecode =?1 ", nativeQuery = true)
    List<Object[]> findAdminUsers(String rolecode);

    @Query(value = "select * from userinfo u left join organinfo o on u.organid=o.id left join user_role ur on u.id=ur.user_id left join role r on ur.role_id=r.id where o.id=?1 and r.rolecode='workcheck' ", nativeQuery = true)
    public List<User> findCheckUsers(String organid);
}
