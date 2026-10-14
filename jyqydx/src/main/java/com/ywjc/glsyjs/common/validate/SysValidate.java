package com.ywjc.glsyjs.common.validate;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.sys.entity.Organinfo;
import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class SysValidate {

	

 
	public static ValidateBinding validate_user(User user){
		ValidateBinding bindingResult = new ValidateBinding(new ArrayList<ErrorEntity>());
		Validate.valiString( "username", user.getUsername(), 	"姓名", true, 20, bindingResult);
		Validate.valiString( "loginname", user.getLoginname(), 	"登录名", true, 20, bindingResult);
		Validate.valiString( "linktel", user.getLinktel(), 	"手机号码", true, 50, bindingResult);
		if(StringUtils.isNotBlank(user.getDatatype())&&"1".equals(user.getDatatype())){

		}else{
			Validate.valiString( "linknum", user.getLinknum(), 	"身份证号码", true, 50, bindingResult);
			Validate.valiString( "linkemail", user.getLinkemail(), 	"联系人电子邮箱", true, 50, bindingResult);
		}

		return bindingResult;
	}
 
	public static ValidateBinding validate_organinfo(Organinfo organinfo){
		ValidateBinding bindingResult = new ValidateBinding(new ArrayList<ErrorEntity>());
		Validate.valiString( "orgname",organinfo.getOrgname(), 	"单位名称", true, 50, bindingResult);
		Validate.valiString( "linkman", organinfo.getLinkman(),  "单位联系人", true, 20, bindingResult);
		Validate.valiString( "linkphone", organinfo.getLinkphone(), 	"联系人手机", true, 20, bindingResult);
 		return bindingResult;
	}	
	


 
}
