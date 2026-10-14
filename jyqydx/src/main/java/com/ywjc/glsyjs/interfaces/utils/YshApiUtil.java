package com.ywjc.glsyjs.interfaces.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.interfaces.oauth.entity.OauthEntity;
import com.ywjc.glsyjs.interfaces.oauth.service.OauthEntityService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

public class YshApiUtil {
    private static Logger log = LoggerFactory.getLogger(YshApiUtil.class);

    @Resource(name = "oauthEntityServiceImpl")
    private OauthEntityService oauthEntityService;

    /**
     * 获取支付平台的token
     */
    public String getYshApiAccessToken() {
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
        String access_token = temp.getAccess_token();
        String expires_in = temp.getExpires_in();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            if (org.apache.commons.lang.StringUtils.isBlank(access_token) || org.apache.commons.lang.StringUtils.isBlank(expires_in) || date.getTime() > new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expires_in).getTime()) {
                //获取支付平台token
                String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/getToken";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("merchantId", merchantId);
                jsonObject.put("merchantSecret", merchantSecret);
                JSONObject accesstokenInfo = GetPostUtil.httpPost(url, jsonObject);

                JSONObject result_info = accesstokenInfo.getJSONObject("result_info");
                access_token = result_info.getString("token");
                int expires = result_info.getInteger("expires");
                date.setTime(date.getTime() + expires * 1000);
                expires_in = df.format(date);
                temp.setAccess_token(access_token);
                temp.setExpires_in(expires_in);
                oauthEntityService.updateOmoauth(temp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return access_token;
    }

    /**
     * 子账户交易接口
     *
     * @param yshApiPayEntityList
     * @param token
     * @return JSONObject
     */
    public static JSONObject yshApiPay(List<YshApiPayEntity> yshApiPayEntityList, String token) {
        JSONObject resultObject = new JSONObject();
        if (CollectionUtils.isNotEmpty(yshApiPayEntityList) && StringUtils.isNotBlank(token)) {
            String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/payTransaction";
            //签名key
            String key = "4c9862e13dc69247361ab9df1a920b87";
            //获取加密后的签名
            StringBuffer sb = new StringBuffer();
            for (YshApiPayEntity temp : yshApiPayEntityList) {
                sb.append("inPaymentAccount").append(temp.getInPaymentAccount())
                        .append("outPaymentAccount").append(temp.getOutPaymentAccount())
                        .append("amount").append(temp.getAmount())
                        .append("remark").append(temp.getRemark());
            }
            sb.append(key);
            String sign = Md5Utils.getMD5(sb.toString());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("transactions", yshApiPayEntityList);
            jsonObject.put("token", token);
            jsonObject.put("sign", sign);
            System.out.println("jsonObject:-------" + jsonObject);
            resultObject = GetPostUtil.httpPost(url, jsonObject);
        }
        return resultObject;
    }

    /**
     * 云司会用户账户信息获取
     *
     * @param token
     * @param creditCode
     * @return
     */
    public static JSONObject getAccountInfoListBycreditCode(String token, String creditCode) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(creditCode)) {
            String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/getAccountInfoListBycreditCode";
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("token", token);
            paramsMap.put("creditCode", creditCode);
            jsonObject = GetPostUtil.httpPostWithForm(url, paramsMap);
        }
        return jsonObject;
    }

    public static JSONObject queryTransINfo(String token, String transId) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(transId)) {
            String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/queryTransINfo";
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("token", token);
            paramsMap.put("transId", transId);
            jsonObject = GetPostUtil.httpPostWithForm(url, paramsMap);
        }
        return jsonObject;
    }

    /**
     * 云司会回单查询
     *
     * @param token
     * @param transId
     * @return
     */
    public static JSONObject getReceiptUrl(String token, String transId) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(transId)) {
            String url = "http://121.196.184.146:8087/yshApi/subsidyGateway/getReceiptUrl";
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("token", token);
            paramsMap.put("transId", transId);
            jsonObject = GetPostUtil.httpPostWithForm(url, paramsMap);
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        List<YshApiPayEntity> yshApiPayEntityList = new ArrayList<>();
        YshApiPayEntity yshApiPayEntity = new YshApiPayEntity();
        yshApiPayEntity.setInPaymentAccount("AccSub20210413191307074");
        yshApiPayEntity.setOutPaymentAccount("AccSub20210413193254031");
        yshApiPayEntity.setAmount("0.05");
        yshApiPayEntity.setRemark("交易接口测试");
        yshApiPayEntityList.add(yshApiPayEntity);

//        JSONObject resultObject = yshApiPay(yshApiPayEntityList,"3fb9688b699745f5b2927ae315157397");
//        JSONArray result_infos = resultObject.getJSONArray("result_info");
//        String transId="";
//        for (int j = 0; j < result_infos.size(); j++) {
//            JSONObject jsonObject2 = result_infos.getJSONObject(j);
//            String transState=jsonObject2.getString("transState");
//            if("1".equals(transState)){
//                transId=jsonObject2.getString("transId");
//            }
//        }

        JSONObject jsonObject=YshApiUtil.getAccountInfoListBycreditCode("c991a9f22193406cb6c47e21ac81eeed","913200007140938190");
        System.out.println(jsonObject);
        JSONObject result_info = jsonObject.getJSONObject("result_info");
        System.out.println(result_info);
        JSONObject accountSubInfoListCompany = result_info.getJSONObject("accountSubInfoListCompany");
        System.out.println(accountSubInfoListCompany);
        JSONArray creditCodelist1=accountSubInfoListCompany.getJSONArray("913200007140938190");
        System.out.println(creditCodelist1);
        for (int j = 0; j < creditCodelist1.size(); j++) {
            JSONObject jsonObject2 = creditCodelist1.getJSONObject(j);
            String accountSubName=jsonObject2.getString("accountSubName");
            System.out.println(accountSubName);
            String stzhAccountId=jsonObject2.getString("stzhAccountId");
            System.out.println(stzhAccountId);
        }
//        JSONObject resultObject = getReceiptUrl("cd5a8e60c9eb4cf18e2d37b1fc9cc25a", "TRANS20210413200729020AUTO");
        /*JSONObject resultObject = queryTransINfo("cd5a8e60c9eb4cf18e2d37b1fc9cc25a", "TRANS20210413145635004");*/

        System.out.println(jsonObject);


    }
}
