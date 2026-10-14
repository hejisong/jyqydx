package com.ywjc.glsyjs.task;



import com.ywjc.datashare.entity.Departmentinfo;
import com.ywjc.datashare.entity.Personinfo;
import com.ywjc.datashare.service.DepartmentinfoService;
import com.ywjc.datashare.service.PersoninfoService;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.service.OrganinfoService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component("datasharetask")
public class Datasharetask {

	private static Logger log = Logger.getLogger(Datasharetask.class);
	@Resource(name = "userServiceImpl")
	private UserService userService;

	@Resource(name = "organinfoServiceImpl")
	private OrganinfoService organinfoService;

	@Resource(name = "departmentinfoServiceImpl")
	private DepartmentinfoService departmentinfoService;

	@Resource(name = "personinfoServiceImpl")
	private PersoninfoService personinfoService;




	//每天定时同步用户数据和部门数据
	public void datashare() {
		//部门数据
		List<Departmentinfo>  departmentinfos=departmentinfoService.findListAll();
		if(departmentinfos!=null&&departmentinfos.size()>0){
			for (Departmentinfo dinfo:departmentinfos) {
				Organinfo organinfo=organinfoService.find(dinfo.getDeptid());
				if(organinfo==null){
					saveOrganinfo(dinfo);
					System.out.println(dinfo.getDeptname());
				}else{
					updateOrganinfo(dinfo,organinfo);
					System.out.println(dinfo.getDeptname());
				}
			}
		}
		//用户数据
		List<Personinfo> personinfos=personinfoService.findListAll();
		if(personinfos!=null&&personinfos.size()>0){
			for (Personinfo pinfo : personinfos) {
                User user=userService.findByLoginnameOnly(pinfo.getUserid());
				if(user==null){
					System.out.println(pinfo.getUsername());
					System.out.println(pinfo.getUserid()+"---"+pinfo.getStatus());
					saveUser(pinfo);
				}else{

					System.out.println(pinfo.getUsername());
					System.out.println(pinfo.getUserid()+"---"+pinfo.getStatus());
					updateUser(pinfo,user);
				}
			}
		}

		/*//删除的部门
		List<Organinfo> organinfos=organinfoService.findAll();
		for (Organinfo organinfo:organinfos) {
			if(StringUtils.isNotBlank(organinfo.getEdorder())){
				Departmentinfo departmentinfo=departmentinfoService.findByid(organinfo.getId());
				if(departmentinfo==null){
					organinfoService.delete(organinfo.getId());
				}
			}

		}

		//删除人员
		List<User> users=userService.findAll();
		for (User user:users) {
			if(StringUtils.isNotBlank(user.getPersonid())){
				Personinfo personinfo=personinfoService.findByid(user.getPersonid());
				if(personinfo==null){
					userService.delete(user.getId());
				}
			}

		}*/
	}








	private void saveUser(Personinfo pinfo){
		User user=new User();
		user.setId(pinfo.getUserid());
		user.setPersonid(pinfo.getPersonid());
		user.setUsername(pinfo.getUsername());
		user.setLoginname(pinfo.getUserid());
		user.setDisplayname(pinfo.getDisplayname());
		user.setPassword(pinfo.getUserpassword());
		user.setPersonpro(pinfo.getPersonpro());
		user.setTitle(pinfo.getTitle());
		user.setPostlevel(pinfo.getPostlevel());
		user.setSex(pinfo.getSex());
		user.setTelephonenumber(pinfo.getTelephonenumber());
		user.setOrdernum(pinfo.getOrdernum());
		user.setStatus(pinfo.getStatus());
		user.setHostname(pinfo.getHostname());
		user.setHostip(pinfo.getHostip());
		user.setRemark(pinfo.getRemark());
		user.setOrganid(pinfo.getDepartmentnumber());
		user.setUnitname(pinfo.getOu());
		user.setLinktel(pinfo.getMobile());
		user.setLinkemail(pinfo.getMail());
		user.setLinknum(pinfo.getIdentitynumber());
		user.setRemark(pinfo.getRemark());
		user.setOtherum(pinfo.getOtherum());
		if("1".equals(pinfo.getStatus())){
			user.setIsvaild("0");
		}else{
			user.setIsvaild("1");
		}
		user.setDatatype("1");
		user.setCreateDate(new Date());
		user.setModifyDate(new Date());
		userService.save(user);
	}

	private void updateUser(Personinfo pinfo, User user){
		user.setPersonid(pinfo.getPersonid());
		user.setUsername(pinfo.getUsername());
		user.setLoginname(pinfo.getUserid());
		user.setDisplayname(pinfo.getDisplayname());
		user.setPassword(pinfo.getUserpassword());
		user.setPersonpro(pinfo.getPersonpro());
		user.setTitle(pinfo.getTitle());
		user.setPostlevel(pinfo.getPostlevel());
		user.setSex(pinfo.getSex());
		user.setTelephonenumber(pinfo.getTelephonenumber());
		user.setOrdernum(pinfo.getOrdernum());
		user.setLinknum(pinfo.getIdentitynumber());
		user.setStatus(pinfo.getStatus());
		user.setHostname(pinfo.getHostname());
		user.setHostip(pinfo.getHostip());
		user.setRemark(pinfo.getRemark());
		user.setOrganid(pinfo.getDepartmentnumber());
		user.setUnitname(pinfo.getOu());
		user.setLinktel(pinfo.getMobile());
		user.setLinkemail(pinfo.getMail());
		user.setRemark(pinfo.getRemark());
		user.setOtherum(pinfo.getOtherum());
		if("1".equals(pinfo.getStatus())){
			user.setIsvaild("0");
		}else{
			user.setIsvaild("1");
		}
		user.setDatatype("1");
		user.setModifyDate(new Date());
		userService.update(user);
	}


	private void saveOrganinfo(Departmentinfo dinfo){
		Organinfo organinfo=new Organinfo();
		organinfo.setId(dinfo.getDeptid());
		organinfo.setOrgcode(dinfo.getUnitcode());
		organinfo.setOrgname(dinfo.getDeptname());
		organinfo.setDisplayname(dinfo.getDisplayname());
		organinfo.setSuperdepartment(dinfo.getSuperdepartment());
		organinfo.setOrgaddr(dinfo.getPostaladdress());
		organinfo.setMail(dinfo.getMail());
		organinfo.setPostalcode(dinfo.getPostalcode());
		organinfo.setTelephonenumber(dinfo.getTelephonenumber());
		organinfo.setFacsimiletelephonenumber(dinfo.getFacsimiletelephonenumber());
		organinfo.setEdorder(dinfo.getEdorder());
		organinfo.setStatus(dinfo.getStatus());
		organinfo.setRemark(dinfo.getRemark());
		organinfo.setManagerid(dinfo.getManagerid());
		organinfo.setIsleaf(dinfo.getIsleaf());
		organinfo.setFullouw(dinfo.getFullouw());
		organinfo.setCreatetime(new Date());
		organinfo.setUpdatedate(new Date());
		organinfoService.save(organinfo);
	}

	private void updateOrganinfo(Departmentinfo dinfo,Organinfo organinfo){
		organinfo.setOrgcode(dinfo.getUnitcode());
		organinfo.setOrgname(dinfo.getDeptname());
		organinfo.setOrgaddr(dinfo.getPostaladdress());
		organinfo.setDisplayname(dinfo.getDisplayname());
		organinfo.setSuperdepartment(dinfo.getSuperdepartment());
		organinfo.setMail(dinfo.getMail());
		organinfo.setPostalcode(dinfo.getPostalcode());
		organinfo.setTelephonenumber(dinfo.getTelephonenumber());
		organinfo.setFacsimiletelephonenumber(dinfo.getFacsimiletelephonenumber());
		organinfo.setEdorder(dinfo.getEdorder());
		organinfo.setStatus(dinfo.getStatus());
		organinfo.setRemark(dinfo.getRemark());
		organinfo.setManagerid(dinfo.getManagerid());
		organinfo.setIsleaf(dinfo.getIsleaf());
		organinfo.setFullouw(dinfo.getFullouw());
		organinfo.setUpdatedate(new Date());
		organinfoService.update(organinfo);
	}




}
