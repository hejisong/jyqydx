package com.ywjc.glsyjs.interfaces.company.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.AuthorizedrecordService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.businesshare.entity.Orderagency;
import com.ywjc.glsyjs.businesshare.service.OrderagencyService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.entity.EmployeesEntity;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.utils.CommonUtil;
import com.ywjc.glsyjs.interfaces.utils.LpApiNewUtil;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller("companyController")
@RequestMapping("/interface/company")
@CrossOrigin
public class CompanyController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "authorizedrecordServiceImpl")
    private AuthorizedrecordService authorizedrecordService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "usercompanyEntityServiceImpl")
    private UsercompanyEntityService usercompanyEntityService;

    @Resource(name = "orderagencyServiceImpl")
    private OrderagencyService orderagencyService;

    @Resource(name = "passwordHelper")
    private PasswordHelper passwordHelper;

    //获取所有授权记录
    @ResponseBody
    @RequestMapping(value = "/authorized", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil getAuthorizedList(@RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        if (StringUtils.isNotBlank(companyid)) {
            List<AuthorizedrecordEntity> list = authorizedrecordService.findlistBycompanyid(companyid);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取企业授权记录成功！");
            messageUtil.setResultObj(list);
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业id为空!");
        }
        return messageUtil;
    }

    //查询企业下的员工
    @ResponseBody
    @RequestMapping(value = "/employees_old", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil searchEmployees_old(@RequestParam(value = "companyid") String companyid, @RequestParam(value = "searchName") String searchName) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        if (StringUtils.isNotBlank(companyid)) {
            if (StringUtils.isNotBlank(searchName)) {
                searchName = CommonUtil.generateFuzzyString(searchName);
                List<EmployeesEntity> employeesEntityList = new ArrayList<>();
                List<Object[]> userlist = userService.findusersinname(searchName);
                for (Object[] temp : userlist) {
                    EmployeesEntity employeesEntity = new EmployeesEntity();
                    employeesEntity.setId(temp[0].toString());
                    employeesEntity.setUsername(temp[1].toString());
                    employeesEntity.setLinknum(temp[2].toString());
                    employeesEntity.setLinktel(temp[3].toString());
                    employeesEntityList.add(employeesEntity);
                }
                messageUtil.setResultObj(employeesEntityList);
            }
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取企业员工记录成功！");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业id为空!");
        }
        return messageUtil;
    }

    //查询企业下的员工
    @ResponseBody
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil searchEmployees(String companyid, String searchName) {
        MessageUtil messageUtil = new MessageUtil();

        // 查询企业信息(统一信用码)
        CompanyEntity companyEntity = companyEntityService.queryCompanyByid(companyid);
        String uscreditcode = companyEntity.getUscreditcode();
        if (StringUtils.isNotBlank(uscreditcode) && StringUtils.isNotBlank(searchName)) {

            //调用社保接口获取人员信息
            //JSONObject staffInfo = LpApiNewUtil.StaffInfo(uscreditcode, searchName);
            List<EmployeesEntity> entities = new ArrayList<>();
            try {
                List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findUserByCompany(companyid,searchName);
                for(UsercompanyEntity usercompanyEntity:usercompanyEntityList){
                    EmployeesEntity employeesEntity = new EmployeesEntity();
                    employeesEntity.setId(usercompanyEntity.getId());
                    employeesEntity.setUsername(usercompanyEntity.getUsername());
                    employeesEntity.setLinknum(usercompanyEntity.getLinknum());
                    employeesEntity.setLinktel(usercompanyEntity.getLinktel());
                    entities.add(employeesEntity);
                }
                //JSONArray employeesArray = staffInfo.getJSONObject("data").getJSONArray("data");
               /* for (int i = 0; i < employeesArray.size(); i++) {
                    String linknum = employeesArray.getJSONObject(i).getString("attrib_03");
                    String username = employeesArray.getJSONObject(i).getString("attrib_04");

                    User user = userService.findUserBynNameAndLinknum(username, linknum);
                    if (user == null) {
                        user = new User();
                        addUser(user, employeesArray.getJSONObject(i));
                        userService.saveinfo(user, "4028099574b3ae4f0174b3b2b5dc0000");
                    }
                    EmployeesEntity employeesEntity = new EmployeesEntity();
                    employeesEntity.setId(user.getId());
                    employeesEntity.setUsername(user.getUsername());
                    employeesEntity.setLinknum(user.getLinknum());
                    employeesEntity.setLinktel(user.getLinktel());
                    entities.add(employeesEntity);
                }*/
            } catch (Exception e) {
                logger.error(e);
            }
            messageUtil.setResultObj(entities);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取企业员工记录成功！");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业不存在！");
        }
        return messageUtil;
    }

    private void addUser(User user, JSONObject entity) {
        String linknum = entity.getString("attrib_03");
        String username = entity.getString("attrib_04");
        //新增用户数据
        user.setId(IdUtil.simpleUUID());
        user.setUsername(username);
        user.setLinkemail("");
        user.setLinknum(linknum);
        user.setLinktel(null);
        user.setAccept_sms("");
        user.setLoginname(linknum);
        user.setOrganid("");
        String salt = KeyUtils.getKey();
        user.setSalt(salt);
        user.setPassword(passwordHelper.getPassword(salt, "Jtyxjcyyjdd@2019"));
        user.setIsnewpwd("N");
        user.setUsertype("5");
        user.setIsvaild(Constantval.CONSTANT_ISVALID);
        user.setModifyDate(new Date());
        user.setCreateDate(new Date());

    }

    //取消所有授权记录
    @ResponseBody
    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil CancelAuthorized(@RequestParam(value = "companyid") String companyid) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(companyid)) {
            authorizedrecordService.updateshqu(companyid);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("取消企业授权记录成功！");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业id为空!");
        }
        return messageUtil;
    }

    //授权
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil saveAuthorized(@RequestParam(value = "companyid") String companyid, @RequestParam(value = "username") String username, @RequestParam(value = "linknum") String linknum) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(companyid) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum)) {

            User userinfo = userService.findUserBynNameAndLinknum(username, linknum);
            CompanyEntity companyEntity = companyEntityService.find(companyid);
            Integer result = authorizedrecordService.findUserIdAndState(userinfo.getId(), "1");
            if (result != null) {
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("该用户已被授权！");
                return messageUtil;
            }
            //（需要先取消授权再重新授权）
            authorizedrecordService.updateshqu(companyid);
            AuthorizedrecordEntity authorizedrecordEntity = new AuthorizedrecordEntity();
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
            UsercompanyEntity usercompanyEntity = usercompanyEntityService.findByuseridAndCompanyid(userinfo.getId(), companyid);
            if (usercompanyEntity != null) {
                usercompanyEntity.setIslegalperson("0");
                usercompanyEntityService.update(usercompanyEntity);
            } else {
                UsercompanyEntity usercompany = new UsercompanyEntity();
                String upid = UUID.randomUUID().toString().replace("-", "");
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
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("授权成功！");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业id为空!");
        }
        return messageUtil;
    }

    //查询一企来专窗人员
    @ResponseBody
    @RequestMapping(value = "/adminUsers", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil getAdminUsers() {
        MessageUtil messageUtil = new MessageUtil();
        List<Object[]> userList = userService.findAdminUsers("agencystaff");
        List<EmployeesEntity> employeesEntityList = new ArrayList<>();
        for (Object[] temp : userList) {
            EmployeesEntity employeesEntity = new EmployeesEntity();
            employeesEntity.setId(temp[0].toString());
            employeesEntity.setUsername(temp[1].toString());
            employeesEntity.setLinknum(temp[2] == null ? null : temp[2].toString());
            employeesEntity.setLinktel(temp[3] == null ? null : temp[3].toString());
            employeesEntity.setLinkUnit(temp[4] == null ? null : temp[4].toString());
            employeesEntityList.add(employeesEntity);
        }
        messageUtil.setResultObj(employeesEntityList);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("授权成功！");
        return messageUtil;
    }


    private MessageUtil saveAuthorizedForAdmin(String companyid, String username, String linknum) {
        MessageUtil messageUtil = new MessageUtil();

        if (StringUtils.isNotBlank(companyid) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum)) {
            // 获取当前登录人信息
            User userinfo = userService.findUserBynNameAndLinknum(username, linknum);

            // 查询一企来专窗的角色
            User useradmin = userService.findByUserIdAndDataType(userinfo.getId());
            messageUtil.setResultObj(useradmin);

            if (useradmin == null) {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("无法操作，不是一企来专窗人员");
                return messageUtil;
            }
            CompanyEntity companyEntity = companyEntityService.find(companyid);
            Integer result = authorizedrecordService.findUserIdAndState(useradmin.getId(), "1");
            if (result != null) {
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("该用户已被授权！");
                return messageUtil;
            }
            //（需要先取消授权再重新授权）
            authorizedrecordService.updateshqu(companyid);
            AuthorizedrecordEntity authorizedrecordEntity = new AuthorizedrecordEntity();
            String id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            authorizedrecordEntity.setAuthorizedid(id);
            authorizedrecordEntity.setCompanyid(companyid);
            authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
            authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
            authorizedrecordEntity.setAuthorizedpersonid(useradmin.getId());
            authorizedrecordEntity.setAuthorizedperson(useradmin.getUsername());
            authorizedrecordEntity.setAuthorizedtime(new Date());
            authorizedrecordEntity.setState("1");
            authorizedrecordService.save(authorizedrecordEntity);
            //授权完插入企业用户关联关系
            UsercompanyEntity usercompanyEntity = usercompanyEntityService.findByuseridAndCompanyid(useradmin.getId(), companyid);
            if (usercompanyEntity != null) {
                usercompanyEntity.setIslegalperson("0");
                usercompanyEntityService.update(usercompanyEntity);
            } else {
                UsercompanyEntity usercompany = new UsercompanyEntity();
                String upid = UUID.randomUUID().toString().replace("-", "");
                usercompany.setId(upid);
                usercompany.setUserid(useradmin.getId());
                usercompany.setUsername(useradmin.getUsername());
                usercompany.setCompanyid(companyid);
                usercompany.setEnterprisename(companyEntity.getEnterprisename());
                usercompany.setLinktel(useradmin.getLinktel());
                usercompany.setLinknum(useradmin.getLinknum());
                usercompany.setIslegalperson("0");
                usercompanyEntityService.save(usercompany);
            }
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("授权成功！");
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("企业id为空!");
        }
        return messageUtil;
    }

    //提交预约待办
    @ResponseBody
    @RequestMapping(value = "/saveOrderagency", method = RequestMethod.POST)
    @CrossOrigin
    public MessageUtil saveOrderagency(@RequestBody JSONObject reqParam) {
        MessageUtil messageUtil = new MessageUtil();
        String companyid = reqParam.getString("companyid");
        String username = reqParam.getString("username");
        String linknum = reqParam.getString("linknum");
        MessageUtil result = saveAuthorizedForAdmin(companyid, username, linknum);
        if (!result.isResult()) {
            return result;
        }
        User userAdmin = (User) result.getResultObj();

        Orderagency orderagency = JSON.parseObject(reqParam.toJSONString(), Orderagency.class);
        orderagency.setOrderid(UUID.randomUUID().toString().replace("-", ""));
        orderagency.setState("0");
        orderagency.setOrdermanid(userAdmin.getId());
        orderagency.setOrderman(userAdmin.getUsername());
        orderagency.setOrdercompany(userAdmin.getUnitname());
        orderagency.setOrdertime(new Date());
        orderagencyService.save(orderagency);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("提交成功！");
        return messageUtil;
    }


    //预约待办列表
    @ResponseBody
    @RequestMapping(value = "/orderagencyList", method = RequestMethod.GET)
    @CrossOrigin
    public MessageUtil orderagencyList(@RequestParam String username,
                                       @RequestParam String linknum) {
        MessageUtil messageUtil = new MessageUtil();
        User userinfo = userService.findUserBynNameAndLinknum(username, linknum);
        if (userinfo == null) {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("人员不存在！");
            return messageUtil;
        }
        List<Orderagency> orderagencyList = orderagencyService.findByordermanid(userinfo.getId());
        messageUtil.setResultObj(orderagencyList);
        messageUtil.setResult(true);
        messageUtil.setErrorMsg("提交成功！");
        return messageUtil;
    }


}
