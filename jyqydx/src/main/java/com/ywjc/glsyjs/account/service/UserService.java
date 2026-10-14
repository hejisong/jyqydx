package com.ywjc.glsyjs.account.service;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;



public interface UserService extends BaseService<User, String> {
	public User findByUsername(String username);

	public User findByLoginname(String loginname);

	public User findByLoginnameOnly(String loginname);
	
	public List<User> findByLoginnameIgnoreCase(String loginname);
	
	public Page<User> findByLoginnameLikeIgnoreCase(String loginname, Pageable pageable);
	
	public Page<User> findByNameLike(String name, Pageable pageable);

	public List<User> findByRole_Id(String id);

	public List<User> findByRolecode(String rolecode);
	
	public Page<User> findPage(Pageable pageable);

	public Page<User> findPageById(Pageable pageable);

	void updatePassword(String passwordtemp,String salt,String id);
	
	public List<User> findOrganUsers(String organid,String isvalid);
	
	public List<Object> findOrganUsers(String organid,String username,String isvalid);

	public List<Object> findUsersbyrole(String roleid,String u_username);


	public Page<User> QueryOrganUsers(String organid,String username,String loginname,Pageable pageable);
	
	public Page<User> findUsers(
			String username, String unitname,String isvaild,Pageable pageable);
	
	public String saveNewUserinfo(String roleid,String loginname,String username,String unitname,String linknum,String linktel,String linkemail);

	public String updateNewUserinfo(User user,String roleid);


	public Page<User> findOrganUsers(String username,String loginname,String organid,String isvalid,Pageable pageable);
	



	//能够接受和发送短信的本单位的用户信息集合
	public List<User> findOrganUsersSms(String organid);


	//未解密
	public User findByIdNotDecrypt(String id);
	//获取锁住的帐号loginname,creditcode,unitname
	public List<Object> findlock(String loginname,String unitname);

	public void saveinfo(User user,String roleid);


	public List<User> findOnlineUsers(String loginname,String organname,String isonline);

	public Page <User> findPageOnlineUsers(String loginname,String organname,Pageable pageable);

	public void updateinfo(User user);

	public List<User> findusersinnameorlinknum(String username,String linknum,String linktel);

	public User findUserBynNameAndLinknum(String username,String linknum);

	public User findUserByLinknum(String linknum);

	public User finduserbynameandphone(String username,String phone);

	public List<Object[]> findusersinname(String username);

	public List<Object> findUsers(String orgname,String username);

	public List<User> findUser(String orgname,String username);

    User findByUserIdAndDataType(String userid);

	List<Object[]> findAdminUsers(String rolecode);


	public List<User> findCheckUsers(String organid);
}
