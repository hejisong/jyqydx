package com.ywjc.glsyjs.account.service.impl;

import com.ywjc.glsyjs.account.entity.Role;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.repository.UserRepository;
import com.ywjc.glsyjs.account.service.RoleService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.service.QueryService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.IndexService;
import com.ywjc.glsyjs.sys.service.OrganinfoService;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements
		UserService {

	@Resource(name = "userRepository")
	private UserRepository repository;

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;

	@Resource(name = "roleServiceImpl")
	private RoleService roleService;

 	@Resource(name = "indexServiceImpl")
	private IndexService indexService;

	@Resource(name = "passwordHelper")
	private PasswordHelper passwordHelper;


	private UserService userService;
	@Resource(name = "queryServiceImpl")
	protected QueryService queryService;
	@Resource(name = "userRepository")
	public void setReposity(PagingAndSortingRepository<User, String> repository) {
		super.setReposity(repository);
	}

	@Override
	public User find(String id) {
		return super.find(id);
	}
	@Override
	@Transactional
	public void save(User entity) {
			super.save(entity);

	}
	@Override
	public void updateinfo(User user){
		super.update(user);
	}

	@Override
	@Transactional
	public User update(User entity) {
		super.update(entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findByNameLike(String name, Pageable pageable) {
		return repository.findByUsernameLikeIgnoreCase("%" + name + "%", pageable);
	}

	@Override
	public List<User> findByRole_Id(String id) {
		return repository.findByRole_Id(id);
	}

	@Override
	public List<User> findByRolecode(String rolecode){
		return repository.findByRolecode(rolecode);
	}

	@Override
	public User findByLoginname(String loginname) {
		return repository.findByLoginname(loginname);
	}

	@Override
	public User findByLoginnameOnly(String loginname) {
		return repository.findByLoginnameOnly(loginname);
	}

	@Override
	public List<User> findByLoginnameIgnoreCase(String loginname) {
		return repository.findByLoginnameIgnoreCase(loginname);
	}

	@Override
	public Page<User> findByLoginnameLikeIgnoreCase(String loginname,
			Pageable pageable) {
		return repository.findByLoginnameLikeIgnoreCase(loginname, pageable);
	}

	//add
	@Override
	@Transactional(readOnly = true)
	public Page<User> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findPageById(Pageable pageable){
		return repository.findPageById(pageable);
	}

	@Override
	@Transactional
	public void updatePassword(String passwordtemp,String salt,String id) {

	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findOrganUsers(String organid,String isvalid){
		if(StringUtils.isBlank(isvalid)||"0".equals(isvalid)){
			return repository.findOrganUsersValid(organid);
		}else{
			return repository.findOrganUsers(organid);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object> findOrganUsers(String organid,String username,String isvalid){
		List<Object> page=null;
		if(organid!=null&&organid.length()<32){
             page=repository.findUsersByOrgan(organid);
		}else{
			if(StringUtils.isBlank(isvalid)||"0".equals(isvalid)){
				page= repository.findOrganUsersValid(organid,username);
			}else{
				page= repository.findOrganUsers(organid,username);
			}
		}

		List<Object> list=new ArrayList<Object>();
		User user=null;
		if(page!=null && page.size()>0){
			 for (Object obj : page) {
				if(obj !=null){
					//将object转成数组
					Object[] objArr = (Object[]) obj;
					if(null!=user) {
						objArr[3]=(Object)(StringUtils.isBlank(user.getLinknum())?"":user.getLinknum());
						objArr[4]=(Object)(StringUtils.isBlank(user.getLinktel())?"":user.getLinktel());
						objArr[5]=(Object)(StringUtils.isBlank(user.getLinkemail())?"":user.getLinkemail());
					}
					list.add((Object)objArr);
				}
			  }
			}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object> findUsersbyrole(String roleid,String u_username){
		List<Object> page=null;
		if(StringUtils.isNotBlank(roleid)){
			page=repository.findUsersByrole(roleid,u_username);
		}
		List<Organinfo> orglists=organinfoService.findOrgnames();
		List<Object> list=new ArrayList<Object>();
		User user=null;

		if(page!=null && page.size()>0){
			for (Object obj : page) {
				String superdepartment="";
				String orgname="";
				String superdepartment2="";
				if(obj !=null){
					//将object转成数组
					Object[] objArr = (Object[]) obj;
					if(objArr[5]!=null){
						superdepartment=objArr[5].toString();
					}
					if(objArr[4]!=null){
						orgname=objArr[4].toString();
					}

					if(StringUtils.isNotBlank(superdepartment)){
						for(Organinfo org:orglists){
							if(superdepartment.equals(org.getId())){
								orgname=org.getOrgname()+"->"+orgname;
								if(StringUtils.isNotBlank(org.getSuperdepartment())){
									for(Organinfo org2:orglists){
										if(org.getSuperdepartment().equals(org2.getId())){
											orgname=org2.getOrgname()+"->"+orgname;
											if(StringUtils.isNotBlank(org2.getSuperdepartment())){
												for(Organinfo org3:orglists){
													if(org2.getSuperdepartment().equals(org3.getId())){
														orgname=org3.getOrgname()+"->"+orgname;
													}
												}
											}
										}
									}
								}
							}
						}
					}
					objArr[5]=(Object)orgname;
					list.add((Object)objArr);
				}
			}
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<User> findUsers(String username, String unitname,
			 String isvaild,Pageable pageable) {
		Page<User> users = null;
		users = repository.findUsers(username, unitname,isvaild, pageable);
		List<Role> rs = null;
		for(User u:users.getContent()){
			rs = roleService.findUserroles(u.getId());
			u.setRolename(initRoleinfo(rs));
		}
		return users;
	}

	@Override
	@Transactional
	public String saveNewUserinfo(String roleid,String loginname,String username,
			String unitname,String linknum,String linktel,String linkemail) {
		User user = new User();
		user.setUsertype(Constantval.CONSTANT_USERTYPE_TWO);
		user.setLoginname(loginname);
		user.setUsername(username);
		user.setUnitname(unitname);
		user.setLinknum(linknum);
		user.setLinktel(linktel);
		user.setLinkemail(linkemail);
		user.setIsvaild(Constantval.ISVAILD);
		user.setCreateDate(new Date());
		user.setModifyDate(new Date());
		String salt = KeyUtils.getKey();
		user.setSalt(salt);
		user.setIsnewpwd("N");
		repository.save(user);
		roleService.updateUserRole(roleid,user.getId());
		return Constantval.CONSTANT_SUCCESS;
	}

	@Override
	@Transactional
	public String updateNewUserinfo(User user,String roleid) {
		user.setModifyDate(new Date());
		this.update(user);
        roleService.updateUserRole(roleid,user.getId());
		return Constantval.CONSTANT_SUCCESS;
	}



	private String initRoleinfo(List<Role> roles){
		String roleinfo = null;
		StringBuilder sb = new StringBuilder();
		for(Role role:roles){
			sb.append(role.getRolename()).append(",");
		}
		roleinfo = sb.toString();
		roleinfo = roleinfo.substring(0,roleinfo.length()-1);
		return roleinfo;
	}

	@Override
	public Page<User> findOrganUsers(String username,String loginname,String organid,String isvalid, Pageable pageable) {
		if(isvalid==null){
			return repository.findOrganUsersAll(username,loginname,organid, pageable);
		}else{
			return repository.findOrganUsersByValid(username,loginname,organid, isvalid, pageable);
		}
	}


	@Override
	public Page<User> QueryOrganUsers(String organid, String username,
			 String loginname, Pageable pageable) {
		return repository.QueryOrganUsers(organid,username,loginname,pageable);
	}



	@Override
	public List<User> findOrganUsersSms(String organid) {
		return repository.findOrganUsersSms(organid);
	}


	@Override
	public List<Object> findlock(String loginname,String unitname) {
		StringBuffer sql= new StringBuffer();
		List<Object> values = new ArrayList<Object>();

		sql.append(" select u.id ");
		sql.append(" from userinfo u ");
		sql.append(" where u.isunlock='N' ");

		if(StringUtils.isNotBlank(loginname)) {
			 sql.append(" and u.loginname  like '%"+loginname+"%'");
		}
		if(StringUtils.isNotBlank(unitname)) {
			 sql.append(" and u.unitname  like '%"+unitname+"%'");
		}

		return queryService.queryObjectBySql(sql.toString());
	}

	@Override
	public User findByIdNotDecrypt(String id) {
		return repository.findByIdNotDecrypt(id);
	}

	@Override
	@Transactional
	public void saveinfo(User user,String roleid){
        save(user);
		String[] roleids=roleid.split(",");
		for (String rid:roleids) {
			roleService.inserUserRole(user.getId(),rid);
		}

	}

	@Override
	public List<User> findOnlineUsers(String loginname,String organname,String isonline){
		return repository.findOnlineUsers(loginname,organname,isonline);
	}

	@Override
	public Page <User> findPageOnlineUsers(String loginname,String organname,Pageable pageable){
		return repository.findPageOnlineUsers(loginname,organname,pageable);
	}

	@Override
	public List<User> findusersinnameorlinknum(String username,String linknum,String linktel) {
		return repository.findusersinnameorlinknum(username,linknum,linktel);
	}
	@Override
	public User findUserBynNameAndLinknum(String username,String linknum) {
		List<User> users=repository.findUserBynNameAndLinknum(username,linknum);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<Object> findUsers(String orgname,String username){
        return repository.findUsers(orgname,username);
	}

	public List<User> findUser(String orgname,String username){
		if(StringUtils.isNotBlank(orgname)&& StringUtils.isNotBlank(username)){
			return repository.findUser(orgname,username);
		}else if(StringUtils.isNotBlank(orgname)){
			return repository.findUserOrgname(orgname);
		}
		return repository.findUserUsername(username);
	}

	@Override
	public User findByUserIdAndDataType(String userid) {
		return  repository.findByUserIdAndDataType(userid);
	}

	@Override
	public List<Object[]> findAdminUsers(String rolecode) {
		return repository.findAdminUsers(rolecode);
	}

	@Override
	public User finduserbynameandphone(String username,String phone) {
		List<User> users=repository.finduserbynameandphone(username,phone);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public User findUserByLinknum(String linknum){
		return repository.findUserByLinknum(linknum);
	}

	@Override
	public List<Object[]> findusersinname(String username) {
		return repository.findusersinname(username);
	}

	@Override
	public List<User> findCheckUsers(String organid){
		return repository.findCheckUsers(organid);
	}
}
