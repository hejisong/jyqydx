package com.ywjc.glsyjs.interfaces.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.interfaces.cloudpivot.vo.Attachment;
import com.ywjc.glsyjs.interfaces.cloudpivot.vo.SubmitData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class FlowUtil {
    private static Logger log = LoggerFactory.getLogger(FlowUtil.class);
        private static final String ip = ConfigUtil.getConfigVal("dd_ip") ;
//    private static final String ip = "58.213.150.107";//本地测试,外网地址

    private static final String client_id = ConfigUtil.getConfigVal("dd_client_id") ;
    private static final String username = ConfigUtil.getConfigVal("dd_username") ;
    private static final String password = ConfigUtil.getConfigVal("dd_password") ;

    private static final String corpId = ConfigUtil.getConfigVal("dd_corpId") ;
    private static final String getOauthUrl = "http://" + ip + "/api/login?redirect_uri=http%3a%2f%2f" + ip + "%2fapi%2foauth%2fauthorize%3fclient_id%3dapi%26response_type%3dcode%26scope%3dread%26redirect_uri%3dhttp%3a%2f%2f" + ip + "%2foauth";
    private static final String gteTokenUrl = "http://" + ip + "/api/login/Authentication/get_token";
    private static final String submitDataUrl = "http://" + ip + "/api/api/runtime/form/submit";
    private static final String uploadFileUrl = "http://" + ip + "/api/api/aliyun/upload";
    private static final String getReplayToken = "http://" + ip + "/api/api/runtime/form/getReplayToken";

    // 获取oauth Code
    private static final String getOauthCodeUrl = "http://" + ip + "/api/login/Authentication/get_code";
    private static final String client_secret = ConfigUtil.getConfigVal("dd_client_secret") ;
    private static final String redirect_uri = "http://" + ip + "/oauth";
    private static final String getTokenUrl = "http://" + ip + "/api";

    /**
     * @return
     */
    public static String getAccessToken() {
        String access_token = "";
        try {
            JSONObject getOauthParam = new JSONObject();
            getOauthParam.put("username", username);
            getOauthParam.put("password", password);
            getOauthParam.put("corpId", corpId);
            getOauthParam.put("url", getOauthUrl);
            JSONObject oauthCode = JSON.parseObject(HttpRequest.post(getOauthCodeUrl).body(getOauthParam.toJSONString()).execute().body(), JSONObject.class);
            String code = null;
            if ("200".equals(oauthCode.getString("errcode"))) {
                code = oauthCode.getString("code");

                Map<String, Object> getTokenParam = new HashMap<>();
                getTokenParam.put("url", getTokenUrl);
                getTokenParam.put("code", code);
                getTokenParam.put("client_secret", client_secret);
                getTokenParam.put("client_id", client_id);
                getTokenParam.put("redirect_uri", redirect_uri);

                JSONObject token = JSON.parseObject(HttpRequest.get(gteTokenUrl).form(getTokenParam).execute().body(), JSONObject.class);
                access_token = token.getString("access_token");
            }

        } catch (Exception e) {
            log.error("请求失败" + e.getMessage());
        }
        log.info("access_token: " + access_token);
        return "Bearer " + access_token;
    }

    /**
     * 获取重放token
     */
    public static String getReplayToken(String authorization) {
        Map<String, Object> param = new HashMap<>();
        JSONObject result = JSON.parseObject(HttpRequest.get(getReplayToken).
                header(Header.AUTHORIZATION, authorization).
                form(param).
                execute().body());
        return result.getString("data");

    }

    /**
     * @param submitData
     * @return
     */
    public static JSONObject submitData(SubmitData submitData) {
        String authorization = getAccessToken();
        JSONObject reqParam = new JSONObject();
        JSONObject bizObject = new JSONObject();
        String replayToken = getReplayToken(authorization);
        bizObject.put("data", submitData);
        bizObject.put("id", CommonUtil.getOrderIdByUUId());
        bizObject.put("schemaCode", "Policyapply");
        bizObject.put("sheetCode", "Policyapply");
        reqParam.put("bizObject",bizObject);
        reqParam.put("actioncode","submit");
        reqParam.put("agree",true);
        reqParam.put("depatmentId","469201d5797a26ad0179824e6dd60d07");
        reqParam.put("formType", "1");
        reqParam.put("replayToken", replayToken);
        reqParam.put("workflowCode", "Policyapplywork");
        String reqBody = reqParam.toJSONString();
        JSONObject result = new JSONObject();
        try {
            result = JSONObject.parseObject(HttpRequest.post(submitDataUrl)
                    .header(Header.AUTHORIZATION, authorization)//头信息，多个头信息多次调用此方法即可
                    .body(reqBody)//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body(), JSONObject.class);

        } catch (Exception e) {
            log.error("请求失败" + e.getMessage());
        }
        return result;
    }

    /**
     * @param file 上传文件
     * @return
     */
    public static Attachment uploadFile(File file,String filename) {
        String authorization = getAccessToken();
        JSONObject result = new JSONObject();
        Attachment attachment = new Attachment();
        try {
            JSONObject paramMap = new JSONObject();
            paramMap.put("file", file);
            result = JSONObject.parseObject(HttpRequest.post(uploadFileUrl)
                    .header(Header.AUTHORIZATION, authorization)//头信息，多个头信息多次调用此方法即可
                    .form(paramMap)//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body(), JSONObject.class);
            if ("0".equals(result.getString("errcode"))) {
                JSONObject data = result.getJSONObject("data");
                attachment.setName(filename);
                attachment.setRefId(data.getString("refId"));
                attachment.setFileSize(data.getInteger("fileSize"));
            }
        } catch (Exception e) {
            log.error("请求失败" + e.getMessage());
        }
        return attachment;
    }


    public static void main(String[] args) {

        // 请求成功 errorcode=0

//         System.out.println(getAccessToken());
        // 上传文件
//         System.out.println(uploadFile(new File("C:\\Users\\user\\Desktop\\新建文本文档.txt")));

//        testSubmit();

//        System.out.println(getReplayToken(getAccessToken()));
    }

    private static void testSubmit() {
        SubmitData submitData = new SubmitData();
        submitData.setENTERPRISENAME("测试1");
        submitData.setUSCREDITCODE("测试2");
        submitData.setNPLCATEGORY("测试3");
        submitData.setPLSUBCLASSNAME("测试4");
        submitData.setPLCONDITIONS("测试5");
        submitData.setRDESCRIPTION("测试6");
        submitData.setPOLICYNAME("测试7");
        submitData.setRAMOUNT("20000");
        submitData.setApplyname("测试8");
        submitData.setApplycard("202021188709089999");
        submitData.setApplydate(DateUtil.formatDate(new Date()));
//        HashMap<String, String> user_department_map = new HashMap<>();
//        user_department_map.put("id", "469201d579c6fe9f0179d5b37d472156");
//        user_department_map.put("type", "3");
//        HashMap<String, String> user_park_map = new HashMap<>();
//        user_park_map.put("id", "469201d579c6fe9f0179d5b37d472156");
//        user_park_map.put("type", "3");
//        HashMap<String, String> user_finance_map = new HashMap<>();
//        user_finance_map.put("id", "469201d579c6fe9f0179d5b37d472156");
//        user_finance_map.put("type", "3");
//        submitData.setUser_department(JSON.toJSONString(user_department_map));
//        submitData.setUser_park(JSON.toJSONString(user_park_map));
//        submitData.setUser_finance(JSON.toJSONString(user_finance_map));
//        submitData.setFirstuser(JSON.toJSONString(user_finance_map));
        List<Attachment> attachments = new ArrayList<>();
        Attachment attachment1 = new Attachment();
        attachment1.setName("搜狗翻译 - 我的贴身智能翻译专家1.pdf");
        attachment1.setRefId("820a9fba06c64e3e861edd0da4ffb494");
        attachment1.setFileSize(294669);
        Attachment attachment2 = new Attachment();
        attachment2.setName("管理平台平台监控模块接口文档.docx");
        attachment2.setFileSize(146861);
        attachment2.setRefId("0ee6d067f3c045db8d4acf244ffcff9f");
        Attachment attachment3 = new Attachment();
        attachment3.setName("新建文本文档.txt");
        attachment3.setFileSize(949);
        attachment3.setRefId("6df447769b7f4ed88c7f083dcff57334");
        attachments.add(attachment2);
        attachments.add(attachment1);
        attachments.add(attachment3);
        submitData.setAttachment(attachments);
        System.out.println(JSON.toJSONString(submitData));
        //数据提交
        System.out.println(submitData(submitData));
    }

}
