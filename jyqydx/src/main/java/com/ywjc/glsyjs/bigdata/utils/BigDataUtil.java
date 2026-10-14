package com.ywjc.glsyjs.bigdata.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.interfaces.utils.GetPostUtil;

import java.util.HashMap;
import java.util.Map;

public class BigDataUtil {


    private static final String BUSINESS_RISK_SERIOUSTAX_TOKEN="1bcbf0f763e540589e6568e24051ae15";
    private static final String BUSINESS_RISK_SERIOUSTAX = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/MCF_BUSINESS_RISK_SERIOUSTAX";


    private static final String MCF_BUSINESS_IPINFO_TRADEMARK_TOKEN="9ca42c3d12f84a05b8d59ff689f3d446";
    private static final String MCF_BUSINESS_IPINFO_TRADEMARK = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/MCF_BUSINESS_IPINFO_TRADEMARK";


    private static final String MCF_BUSINESS_IPINFO_PATENT_TOKEN="646290bece2540618c37c5f52b6dda4e";
    private static final String MCF_BUSINESS_IPINFO_PATENT = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/MCF_BUSINESS_IPINFO_PATENT";


    private static final String MCF_BUSINESS_INSTITUTION_TOKEN="41d85a7fd4b648799eb3b9155086478c";
    private static final String MCF_BUSINESS_INSTITUTION = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/MCF_BUSINESS_INSTITUTION";


    private static final String MCF_BUSINESS_EQUITY_TOKEN="52d5e7bcbbde4a6182f44abc6043db85";
    private static final String MCF_BUSINESS_EQUITY = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/MCF_BUSINESS_EQUITY";



    private static final String MCF_BUSINESS_TOKEN="2180705a414f45489c1bc9d96a77c7ec";
    private static final String MCF_BUSINESS = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/MCF_BUSINESS";


    //南京建邺“秒触发”政策服务系统-GXCWB_QY_TJJ-0803
    //请求参数：USCC_2（统一社会信用代码），token
    private static final String GXCWB_QY_TJJ_TOKEN="79dd81569e7543aa8e6ce8acc7d8093c";
    private static final String GXCWB_QY_TJJ = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/GXCWB_QY_TJJ_824";


    //南京建邺“秒触发”政策服务系统-BUSINESS_IPINFO_TRADEMARK-0803
    //请求参数：PNAME，token
    private static final String BUSINESS_IPINFO_TRADEMARK_TOKEN="f3f3ede7b0714d77a4216e1199a07a1b";
    private static final String BUSINESS_IPINFO_TRADEMARK = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/BUSINESS_IPINFO_TRADEMARK_824";

    //南京建邺“秒触发”政策服务系统-BUSINESS_IPINFO_PATENT-0803
    //请求参数：NAME，token
    private static final String BUSINESS_IPINFO_PATENT_TOKEN="57e39795c6d843458b78185a0a3ec5cf";
    private static final String BUSINESS_IPINFO_PATENT = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/BUSINESS_IPINFO_PATENT_824";


    //南京建邺“秒触发”政策服务系统-BUSINESS_STOCKERHOLDER0803
    //请求参数：NAME，token
    private static final String BUSINESS_STOCKERHOLDER_TOKEN="dae37793ecdb47b68d0e684b25a0ade3";
    private static final String BUSINESS_STOCKERHOLDER = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/BUSINESS_STOCKERHOLDER_824";


    //南京建邺“秒触发”政策服务系统-BUSINESS_INSTITUTION-0803
    //请求参数：NAME，token
    private static final String BUSINESS_INSTITUTION_TOKEN="214feddbb5244f869bbfc29fb06cb2b7";
    private static final String BUSINESS_INSTITUTION = "http://198.18.128.114:8080/goladmin/resourceProxy/requestApi/BUSINESS_INSTITUTION_824";


    /**
     * BUSINESS_RISK_SERIOUSTAX :token,NAME
     *
     */


    public static JSONObject getDataSERIOUSTAX(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", BUSINESS_RISK_SERIOUSTAX_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(BUSINESS_RISK_SERIOUSTAX).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }

    /**
     * MCF_BUSINESS_IPINFO_TRADEMARK :token,PNAME
     *
     */
    public static JSONObject getDataTRADEMARK(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", MCF_BUSINESS_IPINFO_TRADEMARK_TOKEN);
        paramlist.put("COMPANY", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(MCF_BUSINESS_IPINFO_TRADEMARK).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }

    /**
     * MCF_BUSINESS_IPINFO_PATENT :token,NAME
     *
     */
    public static JSONObject getDataPATENT(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", MCF_BUSINESS_IPINFO_PATENT_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(MCF_BUSINESS_IPINFO_PATENT).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }

    /**
     * MCF_BUSINESS_INSTITUTION :token,NAME
     *
     */
    public static JSONObject getDataINSTITUTION(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", MCF_BUSINESS_INSTITUTION_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(MCF_BUSINESS_INSTITUTION).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }

    /**
     * MCF_BUSINESS_EQUITY :token,NAME
     *
     */
    public static JSONObject getDataEQUITY(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", MCF_BUSINESS_EQUITY_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(MCF_BUSINESS_EQUITY).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }

    /**
     * MCF_BUSINESS :token,NAME
     *
     */
    public static JSONObject getDataBUSINESS(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", MCF_BUSINESS_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(MCF_BUSINESS).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }




    //南京建邺“秒触发”政策服务系统-GXCWB_QY_TJJ-0803
    //请求参数：USCC_2（统一社会信用代码），token
    public static JSONObject getDataGXCWB_QY_TJJ(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", GXCWB_QY_TJJ_TOKEN);
        paramlist.put("UNIT_NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(GXCWB_QY_TJJ).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }


    //南京建邺“秒触发”政策服务系统-BUSINESS_IPINFO_TRADEMARK-0803
    //请求参数：PNAME，token
    public static JSONObject getDataBUSINESS_IPINFO_TRADEMARK(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", BUSINESS_IPINFO_TRADEMARK_TOKEN);
        paramlist.put("PNAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(BUSINESS_IPINFO_TRADEMARK).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }

    //南京建邺“秒触发”政策服务系统-BUSINESS_IPINFO_PATENT-0803
    //请求参数：NAME，token
    public static JSONObject getDataBUSINESS_IPINFO_PATENT(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", BUSINESS_IPINFO_PATENT_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(BUSINESS_IPINFO_PATENT).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }


    //南京建邺“秒触发”政策服务系统-BUSINESS_STOCKERHOLDER0803
    //请求参数：NAME，token
    public static JSONObject getDataBUSINESS_STOCKERHOLDER(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", BUSINESS_STOCKERHOLDER_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(BUSINESS_STOCKERHOLDER).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }


    //南京建邺“秒触发”政策服务系统-BUSINESS_INSTITUTION-0803
    //请求参数：NAME，token
    public static JSONObject getDataBUSINESS_INSTITUTION(String corpname) {
        JSONObject paramlist = new JSONObject();
        paramlist.put("token", BUSINESS_INSTITUTION_TOKEN);
        paramlist.put("NAME", corpname);
        JSONObject result = JSON.parseObject(HttpRequest.post(BUSINESS_INSTITUTION).body(paramlist.toJSONString()).execute().body(), JSONObject.class);
        return result;

    }



}
