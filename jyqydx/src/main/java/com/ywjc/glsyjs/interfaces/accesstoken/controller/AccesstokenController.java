package com.ywjc.glsyjs.interfaces.accesstoken.controller;

import authentication.rsa.common.ClientRSAUtils;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.account.entity.AuthorizedrecordEntity;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.account.service.AuthorizedrecordService;
import com.ywjc.glsyjs.account.service.UserService;
import com.ywjc.glsyjs.core.controller.BaseController;
import com.ywjc.glsyjs.core.util.Constantval;
import com.ywjc.glsyjs.core.util.DESUtil;
import com.ywjc.glsyjs.core.util.KeyUtils;
import com.ywjc.glsyjs.interfaces.SMS_Manage.SmsMsgUtil;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.PhonerodEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.entity.UsercompanyEntity;
import com.ywjc.glsyjs.interfaces.accesstoken.service.PhonerodEntityService;
import com.ywjc.glsyjs.interfaces.accesstoken.service.UsercompanyEntityService;
import com.ywjc.glsyjs.interfaces.company.entity.CompanyEntity;
import com.ywjc.glsyjs.interfaces.company.repositories.CompanyEntityRepository;
import com.ywjc.glsyjs.interfaces.company.service.CompanyEntityService;
import com.ywjc.glsyjs.interfaces.utils.*;
import com.ywjc.glsyjs.shiro.security.PasswordHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;


@Controller("AccesstokenController")
@RequestMapping("/interface/wdnj")
@CrossOrigin
public class AccesstokenController extends BaseController {

    /**
     * 系统日志工具
     */
    private Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "usercompanyEntityServiceImpl")
    private UsercompanyEntityService usercompanyEntityService;

    @Resource(name = "phonerodEntityServiceImpl")
    private PhonerodEntityService phonerodEntityService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "companyEntityServiceImpl")
    private CompanyEntityService companyEntityService;

    @Resource(name = "companyEntityRepository")
    private CompanyEntityRepository companyEntityRepository;


    @Resource(name = "passwordHelper")
    private PasswordHelper passwordHelper;

    @Resource(name = "authorizedrecordServiceImpl")
    private AuthorizedrecordService authorizedrecordService;

    private static final TimedCache<String, Integer> timedCache_m = CacheUtil.newTimedCache(1000 * 60);
    private static final TimedCache<String, Integer> timedCache_h = CacheUtil.newTimedCache(1000 * 60 * 60 * 2);

    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC0BAnSVa2nkDY6Qr6RagReLZBUIrr+V9J/7DCbBrVyVrXq9KKt3xbezJ7MVGgJft3sz2TGUYX25yh0u/OK4laFz0cCMt+XixoloOO0je/DhNZtJRWx+StrHKsl9PpmdeEa7VWC2+ECFr5JD4Znb0nV2rTDVbsRpLGC6BxOc7Msg1OrUsh3DzCsdaklFuFFdOi8xxFDADDzBcSaS4fPiRqu+5YLV8rVhibVXusGpIhUO7lPx8YqLsHyWZ7Zz2bnjQDuPtzs/DyPKC8tlk3TweSnG+GykaXWTAyugJ8nH0kTBP3TTJVLcI1V5AZJzDkRxO7qs+CHZB5g4v15MKYV+B8HAgMBAAECggEAa7tC+XrHC1HV8jYBgFr3NDmGd6O1TwPV+TKrwAtayYu1IWC4ft/U+w9wNY6KGKyxOcjAYaNmTeX3Iy1cShC8gSYUM++oOjRIOJ65x1NuWw+DmBwqiLE618Z3cqbSuEDxr8C7fS4D0dE0ndrJr9s9T9usdH45Dw0GIvawAg9h+k+2p1aIyskJAaPv81c1ii4akBp0WuWKadsYQLCQ59+uGJfKAv/mCkkDNp/QY1CIl3J9Kc0MA9gIVDk5eLnFLTRCWWf0i1Jh9EjIzX/qr4nFvpM7G/USBEdBtAK8MgO/dZTXbvDVH3NE8El9tZG+g7uRlj65pDJL8kHnvwvNn2C7SQKBgQD1QG9oPNQskX2wF2ZAj4D3msAiddMVqlCHai5rP+u0NNZhhNi0B4ZNtGoSnwpWm0TVYVRBPr4BjBCo/tS9zVr3LWyYVfJR5Gqxy2EoCD4GbpCYb1nppgsOrn61fw/gD/dKAtnnOq4Jmx0/87+4w/RvdYWPZkrYlAHxVvYtXoOCOwKBgQC757LOiJv4nqdQPanIF8OY5eSE1NpO3yrvbOA1rlWLIbJ3tC4ou8TR+IpUSdvYNbQe2Y574worl7sWx0qyWfZoxvLF9k8aL8Bfy1rQfTgof0dSRnk8IXfaS4Kh84OW+vHj6E8yuIEO4KaH5Ay/qNj7JdXUXS5kuoue2BBQqJWdpQKBgB9155SwkOsysrPfHI/VmM6qfFEvWieiVHg6h7/cUEkqqgGSgBYADRt0tFK1MidP/bF9rcg82zTpmc2D+xhp+mxQ2GhmRhJ5QbKTC2CORh6fGk9joV4Wnb6BZeG0bf3pLmjBGuDNpzu2OTdV9lcmU/nkUoDFt3425lTmOegyAp63AoGAK6Nfjz+CmXae/K+VYnbCYhvhukeEe5Swb/IlD9Jw42LsA4jAv4z75JeH6JNOjyPJtPBperP7hKw0MtINsuXZp/OsJfVR4Y79akHb0VeDV0RX1++t8B0FrJikud/Hy6Rz0K3+1pSqxD2/7wczPr+DxK/AvRT21UXNbxJWl6TPOlECgYEAhfXo19wghZwFi3ekkQbuKawYJuoPJ2SxfnDO5dB4PiFaFwfvxMDGlg7VZ4azSXfF16FbO5p5RIoChS1d5W8w0Sjs5go0QtEAdqvU5mbTzKLaMVPYi913uzWCH2QLg3LR+ExqKO21sTJxeYd2eVA0qmWFPJSJxbzgwXsgy/N2Qx0=";
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtAQJ0lWtp5A2OkK+kWoEXi2QVCK6/lfSf+wwmwa1cla16vSird8W3syezFRoCX7d7M9kxlGF9ucodLvziuJWhc9HAjLfl4saJaDjtI3vw4TWbSUVsfkraxyrJfT6ZnXhGu1VgtvhAha+SQ+GZ29J1dq0w1W7EaSxgugcTnOzLINTq1LIdw8wrHWpJRbhRXTovMcRQwAw8wXEmkuHz4karvuWC1fK1YYm1V7rBqSIVDu5T8fGKi7B8lme2c9m540A7j7c7Pw8jygvLZZN08HkpxvhspGl1kwMroCfJx9JEwT900yVS3CNVeQGScw5EcTu6rPgh2QeYOL9eTCmFfgfBwIDAQAB";

    /**
     * 第三方私钥
     */
    private final static String THIRD_SERVER_PRIVATEKEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKY48F89jaLH+KRC5elTLCVw+lSCcmZ1kghpiwbhJ173/NiWeUjuPvJ1S+Ocnw5E4Aou6bMvpTAOFRO1P/tWVVIuxyipGfLJXfnmVEhsRPkL3pndO1Gb4bHuenrj9LPfFX+DVjlnzvd641Zo+dJ2FZ0aMsGWuB2onsf/ShC/UKuDAgMBAAECgYEAn1LaS1+zf8CO+dtJhiB7oxOsTg6O2th8deW8JYbwdt3aFDv+2sHJwhpd+DgqWMH5wKkAxgi4G9yTpUVIo3833TPoc09SceYvXJo6RsKaoRPqs7piz3P2cxtolzUmRJWpOPk4y9ztI+1m8TggE6di5g4GWgdbmDEdcj8+Z3WfB/ECQQDsbsxx/6WG7C0jUlLOoGxd47EkJz3nsnd7uuvLRuRLiLKTIeEBe+G4RQPPfDD7oSPa2dGIfGGiuYxQ3n+H+DQ7AkEAs/qfZu2AM+LqYxIgVoS+LSS+aJBjLYLznIaVVjf0ETF2L1gxCpKTPdMI1fi05kP81P72XYpxchfFEv4a57pZWQJAQ7YjpQ5TCIYBE1mTT4vQHT+FTtZSO1X4NB4/Q1NFNICsq8iLGRKyfUR+p+BoYtguXZH+GTmN+L1s+wgNvBIQWwJAJ8a3+/N5Zf48dvDGnNhxf2CjhFCE2sYaOPm1f3yWQEaCLCx9iiyixgMh7MguksVr11cR89JpCCIgt/XY1GXm4QJBANINgTGZYB0f81cBstspC2Mj+IKZawTmZiia8M9R4kbXSuFw5PYFJE5t1S3Y2Dju/kf7SsCdgnSBZ0bR2QqnItE=";
    private final static String CESHITHIRD_SERVER_PRIVATEKEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIsJW3eg1bJWjJQqfijVeGgZgfpGk+lj1HeWBX11N8qsAElfiqdKMxgsLp5TNkkSEBEp7pLsRiVw/8i7DWT33C/bxUWzIP+7zwt0NF/CcgfQdJWkYLVNwPseiwZFIH/46pjV5ZCWXqlao1tyvG1MinqwSEeLseS2U3MEFpacCZNLAgMBAAECgYAcA62nZAPK5D99FzbGWYTiTMlwuUnSS+gilFrU1YqBTrjcD+ErJfs3kqduBUw52ubb3nwQpITZxHP1aSMgxgRaX62BPTWtnJLS9OnIz9l3NLCSWiI6PTKDXC6ORjuw9RtskB9KH1OjuE+hY1f2/zI/lwYD/WwaPPizIPbQFgQ4gQJBANVsmUFGr35G14aODgTBEEOpTTlKOF3QdSlgOJ4Erb9Bw72Z9VEsSOQD50gzyIoyree/TlfN/vkVTQYtBEItGosCQQCmxddVWykEMorm1KOjFmWG8ZIS3hrGUkJ6ll7mt33qtbsbNMikcp+CcSAqHqt7sYZ2f6Xyuua+8zKoZjUG+kJBAkA0jQX7bbU+iaD+D7Jh90vS7TgHmD/i0bZXlsq6FfrWMhJJeERQVXApPNDRFXcWmD375z4aHq3elSEfGSyIQuTbAkAQ/6lrMdeEbgJqdNIZFXdTwdu5wJnKDShphlo1+g4DlnJz23V4DZbknNNCd83S1c121pv7mcFbRT6sm35cL7YBAkAw2U0UO7FQMsxif5Ty68MJ56psGrsGkexUM/WbMALKARJEENrJg0Gy3KparfLvdodyktoULf1hr1FeW0YL5BQM";

    @ResponseBody
    @RequestMapping(value = "/decrypt", method = RequestMethod.GET)
    public MessageUtil decryptUserInfo(@RequestParam(value = "data") String data) {
        MessageUtil messageUtil = new MessageUtil();
        try {
            JSONObject result = new JSONObject();
            String data1 = "Gir56CkaA%2F30ENTxPgAxTpFK92FVvg%2Fv%2BoWHIR8Msg%2BVZjzfHprwt2VxxZZzmOZPeufQbTKMy25Z2FCpgaEq4mgASvSeLU16q0fMWZOZzft5MORfQc2P%2BFcMX5IhRz5IxyEUItRleGfzkyJp2X1o61c8fX7kNWkDpdUWCZsQQehxYpulR9OGdu9eJUHCrdz9%2FHWYbd5IgaMMcSwm1KayrDmgFJD1dCtEG00L3ebbyd6WhKVLDfBH0LISTu1WOqFdmjXIORPkaNrgRAJJkyDnUe4stoa87CvnSodDd7Ou5rbxZzJFHKvXk8TDhdOhzAroj3WS5ebfunnv3UOzFVSF6g%3D%3D";

            //解密
            data = data.replaceAll("%20", "%2B");
            /*String decryptCode = URLEncoder.encode(data, "UTF-8");*/
            String decryptCode = URLDecoder.decode(data, "UTF-8");
            System.out.println(decryptCode);
            //String data1 = "him94wnCMHxCZdZOWHGb7JkDNsnwL6Ub/r09MEUAKqfCltUXmxOBx+kYW/oDXTWDOqD+g9qNQEEavaJY7IlTSKfatuUTet5CNJubIgXBUYMXmZfHnUpr0E/PjqR23TC4kOyDkr2Q2GiU7QuLXmw/whXw7czwGdvbKmQbzlVhSrZh2WYL1ayApeAJk8HKKbXq97a0mg+pVBIUd/Lwxb9doU0u9l1FK2WUujiw79MYSHJCXow3cn9VafezC1TP86GbMBQILLnj0XGcS+RtVR5/NlXpQhdRE/K49CvzlBPhhUkgbCwNxTg0WDfLL9rZTc8/qoYkrngRVLpstnxiUKnCqg==";

            HashMap<String, String> decrypt = ClientRSAUtils.decrypt(decryptCode, THIRD_SERVER_PRIVATEKEY);
            //{desp=企业电子账户, idType=1, phone=15895802871, idNumber=320682199202051555, userName=曹磊, userId=4107db09-ce6e-4cad-b14a-85adfba9dc56}
            System.out.println(decrypt);
            String phone = decrypt.get("phone");
            String idNumber = decrypt.get("idNumber");
            String userName = decrypt.get("userName");
            result.put("userInfo", decrypt);
            List<User> userList = usercompanyEntityService.findUserByDecryptInfo(userName, idNumber, phone);
            User user = new User();
            //JSONObject jsonObject = LpApiNewUtil.legalPersonOrStaffInfo(userName, idNumber);
            //String tag = jsonObject.getJSONObject("data").getString("tag");
            //判断用户是否存在
            if (CollectionUtils.isEmpty(userList)) {
                //新增用户数据
                user.setId(IdUtil.simpleUUID());
                user.setUsername(userName);
                user.setLinkemail("");
                user.setLinknum(idNumber);
                user.setLinktel(phone);
                user.setAccept_sms("");
                user.setLoginname(idNumber);
                user.setOrganid("");
                String salt = KeyUtils.getKey();
                user.setSalt(salt);
                user.setPassword(passwordHelper.getPassword(salt, "Jtyxjcyyjdd@2019"));
                user.setIsnewpwd("N");
                user.setUsertype("5");
                user.setIsvaild(Constantval.CONSTANT_ISVALID);
                user.setModifyDate(new Date());
                user.setCreateDate(new Date());

                /*if (!jsonObject.getBoolean("fail")) {
                    if ("1".equals(tag)) {
                        user.setIslegalperson(tag);
                    }
                }*/
                userService.saveinfo(user, "4028099574b3ae4f0174b3b2b5dc0000");
                userList.add(user);
            }
            List<UsercompanyEntity> usercompanyEntityList = usercompanyEntityService.findCompanyByDecryptInfo(userName, idNumber, phone);
            try {
                if (CollectionUtils.isEmpty(usercompanyEntityList)) {
                    //公司名称
                    //String companyName = jsonObject.getJSONObject("data").getJSONArray("data").getJSONObject(0).getString("attrib_02");
                    //统一社会信用码
                    //String uscreditcode = jsonObject.getJSONObject("data").getJSONArray("data").getJSONObject(0).getString("attrib_01");

                    /*List<CompanyEntity> companyEntities = companyEntityService.queryCompanyList(companyName);
                    CompanyEntity companyEntity = new CompanyEntity();
                    // 若公司不存在，新增公司
                    if (CollectionUtils.isEmpty(companyEntities)) {
                        companyEntity.setCompanyid(IdUtil.simpleUUID());
                        companyEntity.setEnterprisename(companyName);
                        companyEntity.setUscreditcode(uscreditcode);
                        companyEntityRepository.save(companyEntity);
                        companyEntities.add(companyEntity);
                    }
                    UsercompanyEntity insertEntity = new UsercompanyEntity(null, userList.get(0).getId(), companyEntities.get(0).getCompanyid(), companyName, userName, idNumber, phone, tag);
                    usercompanyEntityService.saveUserCompany(insertEntity);
                    usercompanyEntityList.add(insertEntity);*/
                }
            } catch (Exception e) {
                logger.error("关联公司插入异常" + e.getCause() + ",提示信息：" + e.getMessage() + "!");
            }
            result.put("companyList", usercompanyEntityList);

            result.put("isReletedCompanyFlag", true);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("解密用户信息成功");
            messageUtil.setResultObj(result);
        } catch (Exception e) {
            e.printStackTrace();
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("解密用户信息异常");
            logger.error("解密用户信息异常,异常原因：" + e.getCause() + ",提示信息：" + e.getMessage() + "!");
            return messageUtil;
        }
        return messageUtil;
    }

    //根据企业名称模糊查询企业信息
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public MessageUtil getCompanyList(@RequestParam(value = "searchname") String searchname) {
        MessageUtil messageUtil = new MessageUtil();
        if (StringUtils.isNotBlank(searchname)) {
            searchname = CommonUtil.generateFuzzyString(searchname);
            List<CompanyEntity> companyEntityList = companyEntityService.queryCompanyList(searchname);

            messageUtil.setResult(true);
            messageUtil.setErrorMsg("获取企业信息成功");
            messageUtil.setResultObj(companyEntityList);
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("搜索企业名称为空");
        }
        return messageUtil;
    }





    //pc端根据手机号发送验证码
    @ResponseBody
    @RequestMapping(value = "/pcsendPhoneMessage", method = RequestMethod.GET)
    public MessageUtil pcsendPhoneMessage(@RequestParam(value = "phone") String phone, HttpSession session) {

        MessageUtil messageUtil = new MessageUtil();
        //解码手机验证码
        if (StringUtils.isNotBlank(phone)) {
            try {
                // Des解密
                String pwdsalt = (String) session.getAttribute("pwdsalt");
                phone= DESUtil.entry(phone, pwdsalt);
            }catch (Exception e){
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("手机号解析失败");
                return messageUtil;
            }

            if (!PhoneUtil.isMobile(phone)) {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("手机号格式不正确");
                return messageUtil;
            } else {
                // 发送验证码前判断一分钟内是否发送过超过2次
                Integer phoneCodeCount_m = timedCache_m.get(phone, false);
                Integer phoneCodeCount_h = timedCache_h.get(phone, false);
                if (phoneCodeCount_m == null) {
                    phoneCodeCount_m = 0;
                }
                if (phoneCodeCount_h == null) {
                    phoneCodeCount_h = 0;
                }

                if (phoneCodeCount_m >= 2 || phoneCodeCount_h >= 20) {
                    messageUtil.setResult(false);
                    messageUtil.setErrorMsg("同一手机号一分钟内最多发送2条，2小时内最多发送20条，请稍后再试。");
                } else {
                    SendPhoneMessageUtil sendPhoneMessageUtil = new SendPhoneMessageUtil();
                    String rod = sendPhoneMessageUtil.smsCode();
                    String exDay = sendPhoneMessageUtil.getExDay(new Date());
                    phonerodEntityService.replacePhoneRod(phone, rod, exDay);
                    //调用短信平台接口，发送短信
                    String messageContent = PropertyUtil.readProperty("centit.properties", "config.content").replace("ROD", rod);
                    String sender = PropertyUtil.readProperty("centit.properties", "config.sender");
                    String singn = PropertyUtil.readProperty("centit.properties", "config.singn");
                    try {
//                        String retMsg = SmsMsgUtil.smsMsgSend("0", messageContent, sender, phone, singn);
                        String retMsg =SendSMSUtil.sendMessnew(phone, messageContent);
                        if (CommonUtil.matchString(retMsg, "OK")) {  //校验返回retMsg中枢否有“成功”二字
                            System.out.println(retMsg);
                            messageUtil.setResult(true);
                            messageUtil.setErrorMsg("发送验证码成功");
                            timedCache_m.put(phone, phoneCodeCount_m + 1);
                            timedCache_h.put(phone, phoneCodeCount_h + 1);
                        } else {
                            messageUtil.setResult(false);
                            messageUtil.setErrorMsg("发送手机验证码失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        messageUtil.setResult(false);
                        messageUtil.setErrorMsg("发送手机验证码异常");
                        logger.error("发送手机验证码异常,异常原因：" + e.getCause() + ",提示信息：" + e.getMessage() + "!");
                    }
                }
            }
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("发送验证码失败");
        }
        return messageUtil;
    }

    //根据手机号发送验证码
    @ResponseBody
    @RequestMapping(value = "/sendPhoneMessage", method = RequestMethod.GET)
    public MessageUtil getPhoneMessage(@RequestParam(value = "phone") String phone) {

        MessageUtil messageUtil = new MessageUtil();
        //解码手机验证码
        if (StringUtils.isNotBlank(phone)) {
            try {
                RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
                phone = rsa.decryptStr(phone, KeyType.PrivateKey);
            }catch (Exception e){
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("手机号解析失败");
                return messageUtil;
            }

            if (!PhoneUtil.isMobile(phone)) {
                messageUtil.setResult(false);
                messageUtil.setErrorMsg("手机号格式不正确");
                return messageUtil;
            } else {
                // 发送验证码前判断一分钟内是否发送过超过2次
                Integer phoneCodeCount_m = timedCache_m.get(phone, false);
                Integer phoneCodeCount_h = timedCache_h.get(phone, false);
                if (phoneCodeCount_m == null) {
                    phoneCodeCount_m = 0;
                }
                if (phoneCodeCount_h == null) {
                    phoneCodeCount_h = 0;
                }

                if (phoneCodeCount_m >= 2 || phoneCodeCount_h >= 20) {
                    messageUtil.setResult(false);
                    messageUtil.setErrorMsg("同一手机号一分钟内最多发送2条，2小时内最多发送20条，请稍后再试。");
                } else {
                    SendPhoneMessageUtil sendPhoneMessageUtil = new SendPhoneMessageUtil();
                    String rod = sendPhoneMessageUtil.smsCode();
                    String exDay = sendPhoneMessageUtil.getExDay(new Date());
                    phonerodEntityService.replacePhoneRod(phone, rod, exDay);
                    //调用短信平台接口，发送短信
                    String messageContent = PropertyUtil.readProperty("centit.properties", "config.content").replace("ROD", rod);
                    String sender = PropertyUtil.readProperty("centit.properties", "config.sender");
                    String singn = PropertyUtil.readProperty("centit.properties", "config.singn");
                    try {
//                        String retMsg = SmsMsgUtil.smsMsgSend("0", messageContent, sender, phone, singn);
                        String retMsg =SendSMSUtil.sendMessnew(phone, messageContent);
                        if (CommonUtil.matchString(retMsg, "OK")) {  //校验返回retMsg中枢否有“成功”二字
                            System.out.println(retMsg);
                            messageUtil.setResult(true);
                            messageUtil.setErrorMsg("发送验证码成功");
                            timedCache_m.put(phone, phoneCodeCount_m + 1);
                            timedCache_h.put(phone, phoneCodeCount_h + 1);
                        } else {
                            messageUtil.setResult(false);
                            messageUtil.setErrorMsg("发送手机验证码失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        messageUtil.setResult(false);
                        messageUtil.setErrorMsg("发送手机验证码异常");
                        logger.error("发送手机验证码异常,异常原因：" + e.getCause() + ",提示信息：" + e.getMessage() + "!");
                    }
                }
            }
        } else {
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("发送验证码失败");
        }
        return messageUtil;
    }

    //校验手机号验证码
    @ResponseBody
    @RequestMapping(value = "/checkPhoneMessage", method = RequestMethod.GET)
    public MessageUtil checkPhoneMessage(UsercompanyEntity usercompanyEntity, @RequestParam(value = "phone") String phone, @RequestParam(value = "mess") String mess) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        //解码手机验证码
        try {
            RSA rsa = new RSA(PRIVATE_KEY, PUBLIC_KEY);
            phone = rsa.decryptStr(phone, KeyType.PrivateKey);
        }catch (Exception e){
            messageUtil.setResult(false);
            messageUtil.setErrorMsg("手机号解析失败");
            return messageUtil;
        }
        try {
            String companyid = usercompanyEntity.getCompanyid();
            if (StringUtils.isNotBlank(companyid) && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(mess)) {
                PhonerodEntity phonerodEntity = phonerodEntityService.queryPhoneRod(phone);
                if (phonerodEntity != null) {
                    Date nowTime = new Date();
                    Date exTime = phonerodEntity.getExtime();
                    if (nowTime.before(exTime)) {
                        if (mess.equals(phonerodEntity.getRod())) {
                            //根据姓名，手机号，身份证 查询用户信息
                            //查询企业进行关联,并且返回企业名称
                            CompanyEntity companyEntity = companyEntityService.queryCompanyByid(companyid);

                            //查看当前企业id是否有法人手机号，没有进行关联执行更新操作
                            if (StringUtils.isEmpty(companyEntity.getPhoneno())) {
                                companyEntityService.updateCompanyPhoneno(phone, companyid);
                            }

                            messageUtil.setResultObj(result);
                            messageUtil.setResult(true);
                            messageUtil.setErrorMsg("手机验证码成功");
                        } else {
                            messageUtil.setResult(false);
                            messageUtil.setErrorMsg("验证码错误，请重新输入！");
                            //根据姓名，手机号，身份证 查询用户信息
                            //查询企业进行关联,并且返回企业名称
                            CompanyEntity companyEntity = companyEntityService.queryCompanyByid(companyid);

                            //查看当前企业id是否有法人手机号，没有进行关联执行更新操作
                            if (StringUtils.isEmpty(companyEntity.getPhoneno())) {
                                companyEntityService.updateCompanyPhoneno(phone, companyid);
                            }

                            messageUtil.setResultObj(result);
                            messageUtil.setResult(true);
                            messageUtil.setErrorMsg("手机验证码成功");
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("服务端程序处理异常！异常信息为：%s.", e.getMessage()));
        }
        return messageUtil;
    }


    //校验手机号验证码
    @ResponseBody
    @RequestMapping(value = "/pccheckPhoneMessage", method = RequestMethod.GET)
    public MessageUtil pccheckPhoneMessage(UsercompanyEntity usercompanyEntity) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        try {
            String companyid = usercompanyEntity.getCompanyid();
            String username = usercompanyEntity.getUsername();
            String linknum = usercompanyEntity.getLinknum();
            String linktel = usercompanyEntity.getLinktel();

            //根据姓名，手机号，身份证 查询用户信息
            User user = usercompanyEntityService.findUserFromNj(username, linknum, linktel);
            String userid = addUser(username, linknum, linktel, user);

            //查询企业进行关联,并且返回企业名称
            CompanyEntity companyEntity = companyEntityService.queryCompanyByid(companyid);
            List<UsercompanyEntity> usercompanyEntityList = new ArrayList<>();
            UsercompanyEntity ucompany = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel, companyid);
            if (ucompany == null) {
                UsercompanyEntity insertEntity = new UsercompanyEntity(null, userid, companyEntity.getCompanyid(), companyEntity.getEnterprisename(), username, linknum, linktel, usercompanyEntity.getIslegalperson());
                usercompanyEntityService.saveUserCompany(insertEntity);
                usercompanyEntityList.add(insertEntity);
            }

            result.put("companyList", usercompanyEntityList);
            messageUtil.setResultObj(result);
            messageUtil.setResult(true);
            messageUtil.setErrorMsg("企业绑定成功!");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("服务端程序处理异常！异常信息为：%s.", e.getMessage()));
        }
        return messageUtil;
    }


    //校验手机号验证码
    @ResponseBody
    @RequestMapping(value = "/oldpccheckPhoneMessage", method = RequestMethod.GET)
    public MessageUtil oldpccheckPhoneMessage(UsercompanyEntity usercompanyEntity, @RequestParam(value = "phone") String phone, @RequestParam(value = "mess") String mess) {
        MessageUtil messageUtil = new MessageUtil();
        JSONObject result = new JSONObject();
        try {
            String companyid = usercompanyEntity.getCompanyid();
            String username = usercompanyEntity.getUsername();
            String linknum = usercompanyEntity.getLinknum();
            String linktel = usercompanyEntity.getLinktel();
            if (StringUtils.isNotBlank(companyid) && StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(mess) && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(linknum) && StringUtils.isNotBlank(linktel)) {
                PhonerodEntity phonerodEntity = phonerodEntityService.queryPhoneRod(phone);
                if (phonerodEntity != null) {
                    Date nowTime = new Date();
                    Date exTime = phonerodEntity.getExtime();
                    if (nowTime.before(exTime)) {
                        if (mess.equals(phonerodEntity.getRod())) {
                            //根据姓名，手机号，身份证 查询用户信息
                            User user = usercompanyEntityService.findUserFromNj(username, linknum, linktel);
                            String userid = addUser(username, linknum, linktel, user);

                            //查询企业进行关联,并且返回企业名称
                            CompanyEntity companyEntity = companyEntityService.queryCompanyByid(companyid);
                            List<UsercompanyEntity> usercompanyEntityList = new ArrayList<>();
                            UsercompanyEntity ucompany = usercompanyEntityService.findCompanyByDecryptInfo(username, linknum, linktel, companyid);
                            if (ucompany == null) {
                                UsercompanyEntity insertEntity = new UsercompanyEntity(null, userid, companyEntity.getCompanyid(), companyEntity.getEnterprisename(), username, linknum, linktel, usercompanyEntity.getIslegalperson());
                                usercompanyEntityService.saveUserCompany(insertEntity);
                                usercompanyEntityList.add(insertEntity);
                            }


                            //查看当前企业id是否有法人手机号，没有进行关联执行更新操作
                            companyEntityService.updateCompanyPhoneno(phone, companyid);

                            //授权
//                            qysq(userid,username,companyEntity);


                            result.put("companyList", usercompanyEntityList);
                            messageUtil.setResultObj(result);
                            messageUtil.setResult(true);

                            messageUtil.setErrorMsg("手机验证码成功,企业绑定成功!");
                        } else {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(String.format("服务端程序处理异常！异常信息为：%s.", e.getMessage()));
        }
        return messageUtil;
    }

    private String addUser(String username, String linknum, String phoneno, User userinfo) {
        if (userinfo == null) {
            userinfo = new User();
            String id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            userinfo.setId(id);
            userinfo.setUsertype("5");
            userinfo.setUsername(username);
            userinfo.setLinknum(linknum);
            userinfo.setLinktel(phoneno);
            userinfo.setCreateDate(new Date());
            String salt = KeyUtils.getKey();
            userinfo.setSalt(salt);
            userinfo.setPassword(passwordHelper.getPassword(salt, "Jtyxjcyyjdd@2019"));
            userinfo.setIsvaild(Constantval.CONSTANT_ISVALID);
            userinfo.setModifyDate(new Date());
            //用户登录名改用手机号
            userinfo.setLoginname(phoneno);
            userService.save(userinfo);
        } else {
            userinfo.setUsername(username);
            userinfo.setLinknum(linknum);
            userinfo.setLinktel(phoneno);
            userinfo.setLoginname(phoneno);
            userService.update(userinfo);
        }


        return userinfo.getId();
    }


    private void qysq(String userid, String username, CompanyEntity companyEntity) {
        List<AuthorizedrecordEntity> author = authorizedrecordService.findsqList(companyEntity.getCompanyid(), userid);
        if (author != null && author.size() > 0) {

        } else {
            AuthorizedrecordEntity authorizedrecordEntity = new AuthorizedrecordEntity();
            String id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            authorizedrecordEntity.setAuthorizedid(id);
            authorizedrecordEntity.setCompanyid(companyEntity.getCompanyid());
            authorizedrecordEntity.setCompanyname(companyEntity.getEnterprisename());
            authorizedrecordEntity.setLegalperson(companyEntity.getLrepresentative());
            authorizedrecordEntity.setAuthorizedpersonid(userid);
            authorizedrecordEntity.setAuthorizedperson(username);
            authorizedrecordEntity.setAuthorizedtime(new Date());
            authorizedrecordEntity.setState("1");
            authorizedrecordService.save(authorizedrecordEntity);
        }


    }

    public static void main(String[] args) {
        String phone = "La8edW7s3xL5e6dvu8+ug3PRjlqfGPnRWWi99AbdHxBYTrPjuyhdMs+4lydlu/TEg86vXoVK0U1Vmbww6F0Oh4olG/PayfjjdSY0UQ5NzubplH9CKNP9wTJBpyzPZYI+/dnVRtjVJu+oxTT8+LAuHggqwP7mr1mVEGi4KtN2fhI=";
        RSA rsa = new RSA(Base64Decoder.decode(PRIVATE_KEY), Base64Decoder.decode(PUBLIC_KEY));
        phone = new String(rsa.decrypt(Base64Decoder.decode(phone), KeyType.PrivateKey));
        System.out.println(phone);
    }


//    public static void main(String[] args) {
//        try {
//            //待解密数据（密文用户信息）
//            /*String data = "IzTDLvjSb%2BDmwizkGTve0omDQukRv2jOQ8njDTQxI6DwcoYYe92CEBF39AZ0Ft5FjaST89h00J2e1l96E0ZXjinTUGzB%2BIeGeB3ozJeh0y%2Fk5odKuAa2W8W%2F2YOW14F0CQVjoKSfDs0%2FPGY4A7YEam3aUkBUFJla0T0uj8x1ZToq9QNccridfd2Wjcz8V73jLEoAPZnWduf0XivhxwWXsBr6YaB3QVNfJQcfZ1d9PBy5Mf82Qvl%2BO8x6dX2SW%2FGVhirPjci5v94TdKWiVCzAqARTeH4mg0jb6Tlqjhzdohptfulv8IA38TiRPhKG2sm1RXtA%2FhMSqc6ybOieCaaasQ%3D%3D";
//             */
//            String datw = "KnTzcOmwFtDfr2VR7xkcuK9lLicZVUUySR93o5QLIwwL%252FH%252BTYwWxcuJo2gEAHiccYC9orHqF1KOvtaQMQTalP%252FqPYnlTlk0pMr5%252BC9BxZvghOmnx93O0I7cjKx%252Fd%252BwBqWWHXO3FruRXNVCL0eVPcqiGHEHphKpEx4VUOskqSkQMBcBx%252FxL87ioI9W3aHL3%252BJxJ%252Fv2YWQmHuBolsnPNnrj5q8Rrj57W7p0TwoA%252FzXj8vYjBW06m%252BspK2LDsrUlfD22cVtN%252FGi8HXVQLVNCQD4bJhTC6d%252Fiozjddaud8CepYXzC45C%252FygisaJeFhXjjgaGBmlFG42SwSTVaP38F1pTSA%253D%253D";
//            String data = "Gir56CkaA%2F30ENTxPgAxTpFK92FVvg%2Fv%2BoWHIR8Msg%2BVZjzfHprwt2VxxZZzmOZPeufQbTKMy25Z2FCpgaEq4mgASvSeLU16q0fMWZOZzft5MORfQc2P%2BFcMX5IhRz5IxyEUItRleGfzkyJp2X1o61c8fX7kNWkDpdUWCZsQQehxYpulR9OGdu9eJUHCrdz9%2FHWYbd5IgaMMcSwm1KayrDmgFJD1dCtEG00L3ebbyd6WhKVLDfBH0LISTu1WOqFdmjXIORPkaNrgRAJJkyDnUe4stoa87CvnSodDd7Ou5rbxZzJFHKvXk8TDhdOhzAroj3WS5ebfunnv3UOzFVSF6g%3D%3D";
//
//            data = data.replaceAll("%20", "%2B");
//            String decryptCode = URLEncoder.encode(data, "UTF-8");
//            decryptCode = URLDecoder.decode(data, "UTF-8");
//            System.out.println(decryptCode);
//            String data1 = "IzTDLvjSb+DmwizkGTve0omDQukRv2jOQ8njDTQxI6DwcoYYe92CEBF39AZ0Ft5FjaST89h00J2e1l96E0ZXjinTUGzB+IeGeB3ozJeh0y/k5odKuAa2W8W/2YOW14F0CQVjoKSfDs0/PGY4A7YEam3aUkBUFJla0T0uj8x1ZToq9QNccridfd2Wjcz8V73jLEoAPZnWduf0XivhxwWXsBr6YaB3QVNfJQcfZ1d9PBy5Mf82Qvl+O8x6dX2SW/GVhirPjci5v94TdKWiVCzAqARTeH4mg0jb6Tlqjhzdohptfulv8IA38TiRPhKG2sm1RXtA/hMSqc6ybOieCaaasQ==";
//            String data2 = "him94wnCMHxCZdZOWHGb7JkDNsnwL6Ub/r09MEUAKqfCltUXmxOBx+kYW/oDXTWDOqD+g9qNQEEavaJY7IlTSKfatuUTet5CNJubIgXBUYMXmZfHnUpr0E/PjqR23TC4kOyDkr2Q2GiU7QuLXmw/whXw7czwGdvbKmQbzlVhSrZh2WYL1ayApeAJk8HKKbXq97a0mg+pVBIUd/Lwxb9doU0u9l1FK2WUujiw79MYSHJCXow3cn9VafezC1TP86GbMBQILLnj0XGcS+RtVR5/NlXpQhdRE/K49CvzlBPhhUkgbCwNxTg0WDfLL9rZTc8/qoYkrngRVLpstnxiUKnCqg==";
//            String data3 = "Gir56CkaA/30ENTxPgAxTpFK92FVvg/v+oWHIR8Msg+VZjzfHprwt2VxxZZzmOZPeufQbTKMy25Z2FCpgaEq4mgASvSeLU16q0fMWZOZzft5MORfQc2P+FcMX5IhRz5IxyEUItRleGfzkyJp2X1o61c8fX7kNWkDpdUWCZsQQehxYpulR9OGdu9eJUHCrdz9/HWYbd5IgaMMcSwm1KayrDmgFJD1dCtEG00L3ebbyd6WhKVLDfBH0LISTu1WOqFdmjXIORPkaNrgRAJJkyDnUe4stoa87CvnSodDd7Ou5rbxZzJFHKvXk8TDhdOhzAroj3WS5ebfunnv3UOzFVSF6g==";
//            //解密
//            HashMap<String, String> decrypt = ClientRSAUtils.decrypt(decryptCode, THIRD_SERVER_PRIVATEKEY);
//            JSONObject jsonObject = LpApiNewUtil.legalPersonOrStaffInfo(decrypt.get("userName"), decrypt.get("idNumber"));
//            System.out.println(decrypt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
