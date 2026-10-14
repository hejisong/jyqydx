package com.ywjc.glsyjs.interfaces.oauth.controller;

import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.oauth.service.OauthEntityService;
import com.ywjc.glsyjs.interfaces.utils.GetPostUtil;
import com.ywjc.glsyjs.interfaces.utils.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller("oauthController")
@RequestMapping("/interface/oauth")
@CrossOrigin
public class OauthController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "oauthEntityServiceImpl")
    private OauthEntityService oauthEntityService;
    @Resource(name = "companyEntityRepository")
    private CompanyEntityRepository companyEntityRepository;


    //获取access_token
    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public MessageUtil getAccesstoken(@Valid OauthEntity oauthEntity, BindingResult bindingResult) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        if (bindingResult.hasErrors()) {
            StringBuilder errormsg = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errormsg.append(error.getDefaultMessage());
            }
            messageUtil.setResult(false);
            messageUtil.setErrorMsg(errormsg.toString());
            return messageUtil;
        }
        OauthEntity newOauthEntity = oauthEntityService.queryOne(oauthEntity);
        if (newOauthEntity == null) {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("无效的参数");
            //生成access_token 和过期时间
            /*String access_token = generateAccesstoken();
            String expires_in = generateExpiresin();
            oauthEntity.setAccess_token(access_token);
            oauthEntity.setExpires_in(expires_in);
            oauthEntityService.saveOmoauth(oauthEntity);*/
        } else {
            //判断access_token 是否过期
            String expires_in = newOauthEntity.getExpires_in();
            if (StringUtils.isNotBlank(expires_in) && !isExpiresin(expires_in)) {
                result.put("access_token", newOauthEntity.getAccess_token());
                result.put("expires_in", expires_in);
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("获取access_token成功");
                messageUtil.setResultObj(result);
            } else {
                //生成access_token 和过期时间
                String access_token = generateAccesstoken();
                String newexpires_in = generateExpiresin();
                newOauthEntity.setAccess_token(access_token);
                newOauthEntity.setExpires_in(newexpires_in);
                oauthEntityService.updateOmoauth(newOauthEntity);
                result.put("access_token", access_token);
                result.put("expires_in", newexpires_in);
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("获取access_token成功");
                messageUtil.setResultObj(result);
            }
        }
        return messageUtil;
    }

    private String generateAccesstoken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    //设置过期时间
    private String generateExpiresin() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 12);// 24小时制
        Date newDate = cal.getTime();
        String expires_in = df.format(newDate);
        return expires_in;
    }

    //未过期 false  已过期 true
    private boolean isExpiresin(String expires_in) {
        boolean isExpiresin = false;
        Date date = new Date();
        try {
            if (date.getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expires_in).getTime()) {
                isExpiresin = true;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return isExpiresin;
    }

    public MessageUtil checkAccesstoken(String access_token) {
        MessageUtil messageUtil = new MessageUtil();
        OauthEntity oauthEntity = oauthEntityService.queryAccesstoken(access_token);
        messageUtil.setResult(false);
        if (oauthEntity == null) {
            messageUtil.setErrorMsg("无效的access_token");
        } else {
            if (isExpiresin(oauthEntity.getExpires_in())) {
                messageUtil.setErrorMsg("access_token已过期");
            } else {
                messageUtil.setResult(true);
                messageUtil.setErrorMsg("access_token有效");
            }
        }
        return messageUtil;
    }

    /**
     * 云司会token获取 公共支付平台
     *
     * @return
     */
    //获取access_token
    @ResponseBody
    @RequestMapping(value = "/getYshApiToken", method = RequestMethod.GET)
    public MessageUtil getYshApiToken(@RequestParam("companyid") String companyid,
                                      @RequestParam("mobile") String mobile) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        String merchantId = "MCH2021032913535600685";
        String merchantSecret = "3aa5e2344f905191f68dbdfa6a4c6b1f";
        String grant_type = "client_credentials";
        String scope = "search";
        OauthEntity oauthEntity = new OauthEntity();
        oauthEntity.setClient_id(merchantId);
        oauthEntity.setClient_secret(merchantSecret);
        oauthEntity.setGrant_type(grant_type);
        oauthEntity.setScope(scope);
        OauthEntity temp = oauthEntityService.queryOne(oauthEntity);
        try {
            String access_token = temp.getAccess_token();
            String expires_in = temp.getExpires_in();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            if (StringUtils.isBlank(access_token) || StringUtils.isBlank(expires_in) || date.getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expires_in).getTime()) {
                //获取支付平台token
                String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/getToken";
                String contentType = "application/json";
                JSONObject paramMap = new JSONObject();
                paramMap.put("merchantId", merchantId);
                paramMap.put("merchantSecret", merchantSecret);
                //JSONObject accesstokenInfo = GetPostUtil.post(url, paramMap, contentType,null);
                JSONObject accesstokenInfo = GetPostUtil.httpPost(url, paramMap);

                JSONObject result_info = accesstokenInfo.getJSONObject("result_info");
                access_token = result_info.getString("token");
                int expires = result_info.getInteger("expires");
                date.setTime(date.getTime() + expires * 1000);
                expires_in = df.format(date);
                temp.setAccess_token(access_token);
                temp.setExpires_in(expires_in);
                oauthEntityService.updateOmoauth(temp);
            }
            CompanyEntity companyEntity = companyEntityRepository.queryCompanyByid(companyid);
            String jumpUrl = "https://subsidy.yunsihui.com/#/myWallet/home?token=access_token&mobile=" + mobile + "&creditCode=" + companyEntity.getUscreditcode();
            jumpUrl = jumpUrl.replace("access_token", access_token);
            result.put("access_token", access_token);
            result.put("jumpUrl", jumpUrl);
            messageUtil.setResultObj(result);
            messageUtil.setResult(true);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return messageUtil;
    }


}
