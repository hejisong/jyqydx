package com.ywjc.glsyjs.account.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.AuthorizedrecordService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.PhonerodEntityService;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.utils.LpApiNewUtil;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller("authorizedrecordController")
@RequestMapping("/account/authorizedrecord")
public class AuthorizedrecordController {
    private static Logger log = Logger.getLogger(AuthorizedrecordController.class);
    @Resource(name = "authorizedrecordServiceImpl")
    private AuthorizedrecordService authorizedrecordService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "passwordHelper")
    private PasswordHelper passwordHelper;

    @Resource(name = "usercompanyEntityServiceImpl")
    private UsercompanyEntityService usercompanyEntityService;

    private Logger logger = Logger.getLogger(this.getClass());


    @Resource(name = "phonerodEntityServiceImpl")
    private PhonerodEntityService phonerodEntityService;






    /**
     * 查询企业用户信息（待授权列表）
     * @param personname
     * @return
     *
     */
    @ResponseBody
    @RequestMapping(value = "/querySqper", method = RequestMethod.POST)
    public  List<Object> querySqper( String personname,HttpSession session) {
//        String userName = (String) session.getAttribute("username");
//        String idNumber = (String) session.getAttribute("linknum");
        JSONObject jsonObject = LpApiNewUtil.legalPersonOrStaffInfo("葛蓉", "320123199110013423");
        //统一社会信用码
        String uscreditcode = jsonObject.getJSONObject("data").getJSONArray("data").getJSONObject(0).getString("attrib_01");

        JSONObject jsonObjectper = LpApiNewUtil.StaffInfo(uscreditcode, personname);
        List<Object> result=new ArrayList<>();
        JSONArray ja = jsonObjectper.getJSONObject("data").getJSONArray("data");
        for(int i=0;i<ja.size();i++) {
            Object[] obj=new Object[3];
            String pername=ja.getJSONObject(i).getString("attrib_04");
            String pidNumber=ja.getJSONObject(i).getString("attrib_03");
            String pidN="";
            if(StringUtils.isNotBlank(pidNumber)){
                pidN=pidNumber.substring(0,3)+"***********"+pidNumber.substring(pidNumber.length()-4,pidNumber.length());
            }
            obj[0]=pername;
            obj[1]=pidN;
            obj[2]=pidNumber;
            result.add(obj);
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/querydqSqper", method = RequestMethod.POST)
    public  Object[] querySqper(HttpSession session) {
        Object[] data = new Object[1];
        String username = (String) session.getAttribute("username");
        String linknum = (String) session.getAttribute("linknum");
        String pidN="";
        if(StringUtils.isNotBlank(linknum)){
            pidN=linknum.substring(0,3)+"***********"+linknum.substring(linknum.length()-4,linknum.length());
        }
        data[0]=username+"("+pidN+")";
        return data;
    }



    /**
     * 查询企业用户信息（待授权列表）
     * @param personname
     * @param phonenum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchcompanyuserlist", method = RequestMethod.GET)
    public Object[] searchcompanyuserlist(@RequestParam(value = "companyid") String companyid,@RequestParam(value = "personname") String personname,@RequestParam(value = "phonenum") String phonenum) {
        Object[] data = new Object[2];
        personname = StringUtils.isBlank(personname) ? "%%" : "%" + personname.trim() + "%";
        phonenum = StringUtils.isBlank(phonenum) ? "%%" : "%" + phonenum.trim() + "%";
        List<Object[]> companyuserlist = authorizedrecordService.findCompanyuserSq(companyid,personname,phonenum);
        if (CollectionUtils.isNotEmpty(companyuserlist)) {
            data[0] = "1";
            data[1] = companyuserlist;
        } else {
            data[0] = "2";
            data[1] = "未获取到企业用户信息";
        }
        return data;
    }



    @RequestMapping(value = "/saveauthinfo", method = RequestMethod.POST)
    @ResponseBody
    public Object[] saveauthinfo(String authorizedperson,String pidNumber,String companyid,ModelMap model, HttpSession session,
                                         HttpServletRequest request) {
        Object[] data = new Object[2];
        companyid=companyid.trim();
        try {
            authorizedperson= StringUtils.isBlank(authorizedperson)?"":authorizedperson.trim();
            //根据人员姓名和手机号查询用户表是否存在
            User userinfo =userService.findUserBynNameAndLinknum(authorizedperson,pidNumber);
            CompanyEntity companyEntity=companyEntityService.find(companyid);
            UsercompanyEntity usercompany=null;
            if(userinfo==null){
                userinfo= new User();
                String id = UUID.randomUUID().toString();
                id = id.replace("-", "");
                userinfo.setId(id);
                userinfo.setUsertype("5");
                userinfo.setUsername(authorizedperson);
                userinfo.setLinknum(pidNumber);
                userinfo.setLoginname(authorizedperson);
                userinfo.setOrganid(companyid);
                userinfo.setUnitname(companyEntity.getEnterprisename());
                userinfo.setCreateDate(new Date());
                String salt = KeyUtils.getKey();
                userinfo.setSalt(salt);
                userinfo.setPassword(passwordHelper.getPassword(salt, "000000"));
                userinfo.setIsvaild(Constantval.CONSTANT_ISVALID);
                userinfo.setModifyDate(new Date());
                //用户登录名改用手机号
                userService.save(userinfo);
            }else{
                 usercompany=usercompanyEntityService.findByuseridAndCompanyid(userinfo.getId(),companyid);
                if(usercompany!=null&&"0".equals(usercompany.getIslegalperson())){//当前授权人已经是授权人
                    data[0] = "3";
                    data[1] = "该人员已授权，请勿重复授权！";
                    return data;
                }
            }
            //（需要先取消授权再重新授权）
            authorizedrecordService.updateshqu(companyid);
            AuthorizedrecordEntity authorizedrecordEntity=new AuthorizedrecordEntity();
            String id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            authorizedrecordEntity.setAuthorizedid(id);
            authorizedrecordEntity.setCompanyid(companyid);
            authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
            authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
            authorizedrecordEntity.setAuthorizedpersonid(userinfo.getId());
            authorizedrecordEntity.setAuthorizedperson(userinfo.getUsername());
            authorizedrecordEntity.setAuthorizedtime(new Date());
            authorizedrecordEntity.setState("1");
            authorizedrecordService.save(authorizedrecordEntity);
            //授权完插入企业用户关联关系
            if(usercompany==null){
                usercompany=usercompanyEntityService.findCompany(authorizedperson,pidNumber,companyid);
            }

            if(usercompany!=null){
                usercompany.setIslegalperson("0");
                usercompanyEntityService.update(usercompany);
            }else{
                usercompany= new UsercompanyEntity();
                String upid = UUID.randomUUID().toString();
                upid = upid.replace("-", "");
                usercompany.setId(upid);
                usercompany.setUserid(userinfo.getId());
                usercompany.setUsername(userinfo.getUsername());
                usercompany.setCompanyid(companyid);
                usercompany.setEnterprisename(companyEntity.getEnterprisename());
                usercompany.setLinktel(userinfo.getLinktel());
                usercompany.setLinknum(userinfo.getLinknum());
                usercompany.setIslegalperson("0");
                usercompanyEntityService.save(usercompany);
            }
            data[0] = "1";
            data[1] = "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            data[0] = "0";
            data[1] = "保存失败";
        }
        return data;
    }





    @RequestMapping(value = "/saveauthorpersoninfo", method = RequestMethod.POST)
    @ResponseBody
    public Object[] saveauthorpersoninfo(String authorizedperson,String phonenum,String companyid,ModelMap model, HttpSession session,
                                    HttpServletRequest request) {
        Object[] data = new Object[2];
        companyid=companyid.trim();
        try {
            authorizedperson= StringUtils.isBlank(authorizedperson)?"":authorizedperson.trim();
            //根据人员姓名和手机号查询用户表是否存在
            User userinfo1 =userService.finduserbynameandphone(authorizedperson,phonenum);

            CompanyEntity companyEntity=companyEntityService.find(companyid);
            if(userinfo1==null){
                userinfo1= new User();
                String id = UUID.randomUUID().toString();
                id = id.replace("-", "");
                userinfo1.setId(id);
                userinfo1.setUsertype("5");
                userinfo1.setUsername(authorizedperson);
                userinfo1.setLinknum("");
                userinfo1.setLinktel(phonenum);
                userinfo1.setCreateDate(new Date());
                String salt = KeyUtils.getKey();
                userinfo1.setSalt(salt);
                userinfo1.setPassword(passwordHelper.getPassword(salt, "000000"));
                userinfo1.setOrganid(companyid);
                userinfo1.setUnitname(companyEntity.getEnterprisename());
                userinfo1.setIsvaild(Constantval.CONSTANT_ISVALID);
                userinfo1.setModifyDate(new Date());
                //用户登录名改用手机号
                userinfo1.setLoginname(phonenum);
                userService.save(userinfo1);

    //            return data;
            }
            //（需要先取消授权再重新授权）
            authorizedrecordService.updateshqu(companyid);
            AuthorizedrecordEntity authorizedrecordEntity=new AuthorizedrecordEntity();
            String id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            authorizedrecordEntity.setAuthorizedid(id);
            authorizedrecordEntity.setCompanyid(companyid);
            authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
            authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
            authorizedrecordEntity.setAuthorizedpersonid(userinfo1.getId());
            authorizedrecordEntity.setAuthorizedperson(userinfo1.getUsername());
            authorizedrecordEntity.setAuthorizedtime(new Date());
            authorizedrecordEntity.setState("1");
            authorizedrecordService.save(authorizedrecordEntity);
            //授权完插入企业用户关联关系
            UsercompanyEntity usercompanyEntity=usercompanyEntityService.findCompanyByPhoneandname(authorizedperson,phonenum);
            if(usercompanyEntity!=null){
                usercompanyEntity.setIslegalperson("0");
                usercompanyEntityService.update(usercompanyEntity);
            }else{
                UsercompanyEntity usercompany= new UsercompanyEntity();
                String upid = UUID.randomUUID().toString();
                upid = upid.replace("-", "");
                usercompany.setId(upid);
                usercompany.setUserid(userinfo1.getId());
                usercompany.setUsername(userinfo1.getUsername());
                usercompany.setCompanyid(companyid);
                usercompany.setEnterprisename(companyEntity.getEnterprisename());
                usercompany.setLinktel(userinfo1.getLinktel());
                usercompany.setLinknum(userinfo1.getLinknum());
                usercompany.setIslegalperson("0");
                usercompanyEntityService.save(usercompany);
            }
            //授权之后更新企业用户表
            UsercompanyEntity  logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
            if(logincompany!=null){
                UsercompanyEntity logincompany1=usercompanyEntityService.find(logincompany.getId());
                session.setAttribute("logincompany",logincompany1);
            }

            data[0] = "1";
            data[1] = "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            data[0] = "0";
            data[1] = "保存失败";
        }
        return data;
    }


    @ResponseBody
    @RequestMapping(value = "/authorcancel", method = RequestMethod.GET)
    public Object[] authorcancel(@RequestParam(value = "authorizedid") String authorizedid,HttpSession session) {
        Object[] data = new Object[2];
        try {
            cancleAu(authorizedid);
            //授权/取消授权之后更新企业用户表
            UsercompanyEntity  logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
            if(logincompany!=null){
                UsercompanyEntity logincompany1=usercompanyEntityService.find(logincompany.getId());
                session.setAttribute("logincompany",logincompany1);
            }
            data[0] = "1";
            data[1] = "取消授权成功";
        } catch (Exception e) {
            e.printStackTrace();
            data[0] = "2";
            data[1] = "取消授权失败";
        }
        return data;
    }


    private void cancleAu(String authorizedid){
        AuthorizedrecordEntity authorizedrecord=authorizedrecordService.find(authorizedid);
        if(authorizedrecord!=null){
            authorizedrecord.setState("2");
            authorizedrecord.setCancellationtime(new Date());
            authorizedrecordService.update(authorizedrecord);
            UsercompanyEntity usercompanyEntity=usercompanyEntityService.findByuseridAndCompanyid(authorizedrecord.getAuthorizedpersonid(),authorizedrecord.getCompanyid());
            if(usercompanyEntity!=null){
                usercompanyEntity.setIslegalperson("2");
                usercompanyEntityService.update(usercompanyEntity);
            }

        }

    }




    @ResponseBody
    @RequestMapping(value = "/cancelauthor", method = RequestMethod.GET)
    public Object[] cancelauthor(@RequestParam(value = "companyid") String companyid) {
        Object[] data = new Object[2];
        try {
            authorizedrecordService.updateshqu(companyid);
//            usercompanyEntityService.deleteBycompanyid(companyid);
            data[0] = "1";
            data[1] = "取消授权成功";
        } catch (Exception e) {
            e.printStackTrace();
            data[0] = "2";
            data[1] = "取消授权失败";
        }
        return data;
    }


    @ResponseBody
    @RequestMapping(value = "/upstate", method = RequestMethod.GET)
    public String upstate(String authorizedid,String state) {
        try {
            AuthorizedrecordEntity authorizedrecord=authorizedrecordService.find(authorizedid);
            if(authorizedrecord!=null){
                authorizedrecord.setState(state);
                if("1".equals(state)){
                    //授权时，其他已授权人员自动取消授权
                    authorizedrecordService.updateshqu(authorizedrecord.getCompanyid());
                    authorizedrecord.setAuthorizedtime(new Date());

                }else{
                    authorizedrecord.setCancellationtime(new Date());
                }

            }
            authorizedrecordService.update(authorizedrecord);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return Constantval.CONSTANT_SUCCESS;
    }


    @ResponseBody
    @RequestMapping(value = "/legupstate", method = RequestMethod.GET)
    public MessageUtil legupstate(String phone,String mess, String authorizedid,String state, String companyid,HttpSession session) {
        MessageUtil messageUtil = new MessageUtil();

        try {

           /* if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(mess)  && StringUtils.isNotBlank(authorizedid) && StringUtils.isNotBlank(state)) {
                PhonerodEntity phonerodEntity = phonerodEntityService.queryPhoneRod(phone);
                if (phonerodEntity != null) {
                    Date nowTime = new Date();
                    Date exTime = phonerodEntity.getExtime();
                    if (nowTime.before(exTime)) {
                        if (mess.equals(phonerodEntity.getRod())) {*/
                            //查看当前企业id是否有法人手机号，没有进行关联执行更新操作
                            companyEntityService.updateCompanyPhoneno(phone, companyid);

                            if("1".equals(state)){//授权
                                String username = (String) session.getAttribute("username");
                                String linknum = (String) session.getAttribute("linknum");
                                String linktel = (String) session.getAttribute("linktel");
                                setAu(username,linknum,companyid);
                            }else{//取消授权
                                cancleAu(authorizedid);
                            }
                            //授权/取消授权之后更新企业用户表
                            UsercompanyEntity  logincompany=(UsercompanyEntity)session.getAttribute("logincompany");
                            if(logincompany!=null){
                                UsercompanyEntity logincompany1=usercompanyEntityService.find(logincompany.getId());
                                session.setAttribute("logincompany",logincompany1);
                            }
                            messageUtil.setResult(true);

                       /* } else {
                            messageUtil.setResult(false);
                            messageUtil.setErrorMsg("验证码错误，请重新输入！");

                        }
                    } else {
                        messageUtil.setResult(false);
                        messageUtil.setErrorMsg("验证码已过期，请重新获取验证码！");
                    }
                } else {
                    messageUtil.setResult(false);
                    messageUtil.setErrorMsg("验证码为空");
                }

            } else {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("手机号或者验证码为空!");

            }*/
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("服务端程序处理异常！异常信息为：%s.", e.getMessage()));
        }
        return messageUtil;
    }


    private void setAu(String authorizedperson,String pidNumber,String companyid){
        User userinfo =userService.findUserBynNameAndLinknum(authorizedperson,pidNumber);
        CompanyEntity companyEntity=companyEntityService.find(companyid);
        //（需要先取消授权再重新授权）
        authorizedrecordService.updateshqu(companyid);
        AuthorizedrecordEntity authorizedrecordEntity=new AuthorizedrecordEntity();
        String id = UUID.randomUUID().toString();
        id = id.replace("-", "");
        authorizedrecordEntity.setAuthorizedid(id);
        authorizedrecordEntity.setCompanyid(companyid);
        authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
        authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
        authorizedrecordEntity.setAuthorizedpersonid(userinfo.getId());
        authorizedrecordEntity.setAuthorizedperson(userinfo.getUsername());
        authorizedrecordEntity.setAuthorizedtime(new Date());
        authorizedrecordEntity.setState("1");
        authorizedrecordService.save(authorizedrecordEntity);
        UsercompanyEntity usercompanyEntity=usercompanyEntityService.findByuseridAndCompanyid(userinfo.getId(),companyid);
        if(usercompanyEntity!=null){
            usercompanyEntity.setIslegalperson("0");
            usercompanyEntityService.update(usercompanyEntity);
        }
    }

}
