package com.ywjc.glsyjs.interfaces.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LpApiNewUtil {
    private static Logger log = LoggerFactory.getLogger(LpApiNewUtil.class);

    private static final String appId = "f32d34e43911504d814c96b7ac53cea4";

    private static final String appSecret = "b847228c6694a5d0dd52bab437e2114fb89f150bbffdaf28173c2f47d70f60dd";


    /**
     * 根据身份证和姓名，查询用户身份（法人/员工）和所在企业的接口
     *
     * @return JSONObject
     */
    public static JSONObject legalPersonOrStaffInfo(String username, String userkey) {
        JSONObject resultObject = new JSONObject();
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(userkey)) {
            String url = "https://mydata.nj.gov.cn:18080/lpapinew/toLegalPersonOrStaffInfo";
            SortedMap<String, Object> map = new TreeMap<>();
            long timestamp = System.currentTimeMillis();
            map.put("appId", appId);
            map.put("timestamp", timestamp);
            map.put("username", username);
            map.put("userkey", userkey);

            String parmamStr = createSign(map);

            String sign = Sm3Utils.encrypt(parmamStr + "&" + appSecret).toUpperCase();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appId", appId);
            jsonObject.put("sign", sign);
            jsonObject.put("timestamp", timestamp);
            jsonObject.put("username", username);
            jsonObject.put("userkey", userkey);
            System.out.println("jsonObject:-------" + jsonObject);
            resultObject = GetPostUtil.httpPost(url, jsonObject);
        }
        return resultObject;
    }

    /**
     * 根据统一社会信用码和员工名称，获取员工信息
     *
     * @return JSONObject
     */
    public static JSONObject StaffInfo(String dpucode, String staffname) {
        JSONObject resultObject = new JSONObject();
        if (StringUtils.isNotBlank(dpucode) && StringUtils.isNotBlank(staffname)) {
            String url = "https://mydata.nj.gov.cn:18080/lpapinew/toStaffInfo";
            SortedMap<String, Object> map = new TreeMap<>();
            long timestamp = System.currentTimeMillis();
            map.put("appId", appId);
            map.put("timestamp", timestamp);
            map.put("dpucode", dpucode);
            map.put("staffname", staffname);

            String parmamStr = createSign(map);

            String sign = Sm3Utils.encrypt(parmamStr + "&" + appSecret).toUpperCase();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appId", appId);
            jsonObject.put("sign", sign);
            jsonObject.put("timestamp", timestamp);
            jsonObject.put("dpucode", dpucode);
            jsonObject.put("staffname", staffname);
            System.out.println("jsonObject:-------" + jsonObject);
            resultObject = GetPostUtil.httpPost(url, jsonObject);
        }
        return resultObject;
    }

    /**
     * 根据企业统一社会信用代码，查询这个企业缴纳社保人数的接口
     *
     * @return JSONObject
     */
    public static JSONObject StaffNumInfo(String dpucode) {
        JSONObject resultObject = new JSONObject();
        if (StringUtils.isNotBlank(dpucode)) {
            String url = "https://mydata.nj.gov.cn:18080/lpapinew/toStaffNumInfo";
            SortedMap<String, Object> map = new TreeMap<>();
            long timestamp = System.currentTimeMillis();
            map.put("appId", appId);
            map.put("timestamp", timestamp);
            map.put("dpucode", dpucode);

            String parmamStr = createSign(map);

            String sign = Sm3Utils.encrypt(parmamStr + "&" + appSecret).toUpperCase();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appId", appId);
            jsonObject.put("sign", sign);
            jsonObject.put("timestamp", String.valueOf(timestamp));
            jsonObject.put("dpucode", dpucode);
            System.out.println("jsonObject:-------" + jsonObject);
            resultObject = GetPostUtil.httpPost(url, jsonObject);
        }
        return resultObject;
    }

    public static String createSign(SortedMap<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();

        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String result = sb.toString().substring(0, sb.toString().length() - 1);
        System.out.println("result:" + result);
        return result;
    }

    public static void main(String[] args) {

//        JSONObject jsonObject = legalPersonOrStaffInfo("葛蓉","320123199110013423");
//        JSONObject jsonObject = legalPersonOrStaffInfo("雷爱华","630102195908071262");
        JSONObject jsonObject = StaffInfo("913200007140938190", "葛蓉");
        /*JSONObject jsonObject = StaffNumInfo("91320113MA21NCCG7D");*/


        JSONArray ja = jsonObject.getJSONObject("data").getJSONArray("data");
        for(int i=0;i<ja.size();i++) {
            System.out.println(ja.getJSONObject(i).get("attrib_03"));
        }


        System.out.println("returnObj:" + jsonObject);


    }
}
