package com.ywjc.glsyjs.interfaces.utils;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.RemoteException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendSMSUtil {
    private static Logger log = LoggerFactory.getLogger(SendSMSUtil.class);


    /**
     * post 发送短信
     * @return
     */
    public static void  doPostStrMsg(String url,NameValuePair[] data){
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
        post.setRequestBody(data);
        //这里写你的bai方法
        try {
            client.executeMethod(post);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
//				for(Header h : headers) {
//					System.out.println(h.toString());
//				}
        try {
            String result = post.getResponseBodyAsString();
            System.out.println(result);
            post.releaseConnection();
//			return result;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//			return null;
        }

    }





    //根据手机号发短信获取验证码
    //返回值：{"balance":-100,"list":[{"result":0,"mobile":"18913352807","mid":"BAAC7A4399E2766B"}],"status":"0"}
    public static String sendMessnew(String mobile, String content){
        String url = "http://47.111.170.128:7862/sms";
        String action="send";
        String account="921011";
        String password="F4WsJ#";
        String extno="1231";
        String rt="json";

        JSONObject param = new JSONObject();
        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
        String params = null;
        try {
            params = "action=" + URLEncoder.encode(action, "UTF-8")
                    +"&account="+URLEncoder.encode(account, "UTF-8")
                    +"&password="+URLEncoder.encode(password, "UTF-8")
                    +"&mobile="+URLEncoder.encode(mobile, "UTF-8")
                    +"&content="+URLEncoder.encode(content, "UTF-8")
                    +"&extno="+URLEncoder.encode(extno, "UTF-8")
                    +"&rt="+URLEncoder.encode(rt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String sendresult="FAIL";
        JSONObject results=GetPostUtil.doPostStr(url,params);
        if(results!=null&&results.get("status")!=null){
            String rs=(String)results.get("status");
            if("0".equals(rs)){
                sendresult="OK";
            }
        }


        System.out.println(results);
        return  sendresult;
    }


    public static void main(String[] args) throws RemoteException {

        /**  02
         * 本方法用于群送短信到指定的手机号码。messageContent：短信内容（最长支持220个汉字） sender：短信发送人（如
         * 计生系统） receivers： 短信接收号码（小灵通要加上区号，多个号码用半角分号“;”间隔） singn： 短信签名（最长25个汉字
         * 如 计生平台）
         */
//        String receivers = "18913352807";  //短信接 收号码（小灵通要加上区号）
//        String execSendMessage2 = SendSMSUtil.sendMessnew(receivers,"建邺测试短信");
//        System.out.println(execSendMessage2);


//        String result="{\"balance\":-100,\"list\":[{\"result\":0,\"mobile\":\"18913352807\",\"mid\":\"BAAC7A4399E2766B\"}],\"status\":\"0\"}";
//        JSONObject results=JSONObject.parseObject(result);
//        String rs=(String)results.get("status");
//        System.out.println(rs);


       /* String phone="18168109279";
        String ENTERPRISENAME="江苏南大先腾";
        String POLICYNAME="建邺区促进企业质量和知识产权发展暂行办法";
        String PLCONDITIONS="企业授权发明专利(一种基于GA与VGG网络的多源数据火灾检测系统的方法)";
        String RDESCRIPTION="每件补助4000元,兑现金额0.01元";
        String XXXXXX="11121145521145";
        //调用短信平台接口，发送短信
        String contentkey="config.content2";
        String messageContent = PropertyUtil.readProperty("centit.properties", contentkey);
        messageContent=messageContent.replace("ENTERPRISENAME", ENTERPRISENAME);
        messageContent=messageContent.replace("POLICYNAME", POLICYNAME);
        messageContent=messageContent.replace("PLCONDITIONS", PLCONDITIONS);
        messageContent=messageContent.replace("RDESCRIPTION", RDESCRIPTION);
        if("config.content2".equals(contentkey)){
            messageContent=messageContent.replace("XXXXXX", XXXXXX);
        }


        try {
            String retMsg = SendSMSUtil.sendMessnew(phone, messageContent);
            if (CommonUtil.matchString(retMsg, "OK")) {  //校验返回retMsg中枢否有“成功”二字
                System.out.println(retMsg);
            }
        } catch (Exception e) {
            log.error("发送手机短信异常,异常原因：" + e.getCause() + ",提示信息：" + e.getMessage() + "!");
        }*/


    }


}
