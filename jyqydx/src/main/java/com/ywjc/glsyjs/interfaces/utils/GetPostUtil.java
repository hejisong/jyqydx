package com.ywjc.glsyjs.interfaces.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetPostUtil {
    private static Logger log = LoggerFactory.getLogger(GetPostUtil.class);

    /**
     * get请求
     *
     * @param url 为接口地址参数
     * @return
     */
    public static JSONObject doGetStr(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = null;// 接收结果
        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();// 从消息体里获取结果
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
            EntityUtils.consume(entity);
        } catch (ClientProtocolException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return jsonObject;
    }

    /**
     * post请求
     *
     * @param url    为接口地址参数
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url, String outStr) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        try {
            httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonObject;
    }


    public static JSONObject post(String url, Map<String, String> paramMap, String header, String authorization) throws IOException {
        List<NameValuePair> nvps = new ArrayList<>();
        if (null != paramMap && paramMap.size() > 0) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        CloseableHttpClient httpClient;
        if (url.toLowerCase().startsWith("https")) {
            httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(16000).setConnectTimeout(16000).build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        if (StringUtils.isNotBlank(authorization)) {
            httpPost.addHeader("Authorization", authorization);
        }
        httpPost.addHeader("Content-Type", header);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String retMsg = EntityUtils.toString(response.getEntity(), "UTF-8");
        if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
            log.error("调用接口{}返回状态码：{}, 返回内容：{}, url:" + url + ",response.getStatusLine():" + response.getStatusLine()
                    + ",retMsg:" + retMsg);
            retMsg = "{code:'99', msg:'接口报错-返回状态码：" + response.getStatusLine() + "'}";
        }

        try {
            if (null != response) {
                response.close();
            }
            httpClient.close();
        } catch (IOException ioe) {
            log.error("异常日志" + ioe.getMessage());
        }
        log.info("retMsg" + retMsg);
        return JSONObject.parseObject(retMsg);
    }

    /**
     * 发送get请求
     *
     * @return
     * @throws Exception
     */
    public static JSONObject getMethod(String httpType, String contentType, String authorization,
                                       String url, JSONObject paramMap) throws Exception {
        String result = "";
        // 创建默认的httpClient实例
        CloseableHttpClient httpclient = null;
        if (AttrConstants.HTTPS.equals(httpType.toLowerCase())) {
            httpclient = getIgnoeSSLClient();
        } else {
            // 创建默认的httpClient实例
            httpclient = HttpClients.createDefault();
        }
        // 设置连接超时
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
        URIBuilder builder = new URIBuilder(url);
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<String> keySet = paramMap.keySet();
        if (keySet.size() > 0) {
            for (String param : keySet) {
                if (isNotEmpty(param)) {
                    formparams.add(new BasicNameValuePair(param,
                            null == paramMap.get(param) ? null : paramMap.get(param).toString()));
                }
            }
        }
        builder.addParameters(formparams);
        URI uri = builder.build();
        // 创建http GET请求
        HttpGet httpget = new HttpGet(uri);
        httpget.setConfig(requestConfig);
        httpget.setHeader("Authorization", authorization);
        httpget.setHeader("Content-Type", contentType);
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getStatusCode() == 200) {
                    String temp = EntityUtils.toString(entity, "UTF-8") + "";
                    return JSONObject.parseObject(temp);
                }
                return JSONObject.parseObject(result);
            } catch (Exception e) {
                log.error("send error:", e);
                result = e.getMessage();
            } finally {
                response.close();
            }

        } catch (Exception e) {
            log.error("send error:", e);
            result = e.getMessage();
        } finally {
            // 关闭连接,释放资源
            try {
                if (null != httpclient) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.error("send error:", e);
                result = e.getMessage();
            }
        }
        return JSONObject.parseObject(result);
    }

    public static boolean isNotEmpty(String s) {
        return null != s && !"".equals(s) && !"".equals(s.trim()) && !"null".equalsIgnoreCase(s);
    }

    private static CloseableHttpClient getIgnoeSSLClient() throws Exception {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();
        CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        return client;
    }


    public static JSONObject httpPost(String url, JSONObject jsonObject) {
        String result = "";
        //CloseableHttpClient实现了HttpClient接口
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        //创建HttpClientBuilder设置属性
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(60000)
                .setSocketTimeout(60000)
                .setConnectTimeout(60000).build()).setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));

        //设置请求头信息
        Map<String, String> map = new HashMap<>();
        map.put("Accept", "application/json, text/plain, */*");
        map.put("Accept-Encoding", "gzip, deflate");
        map.put("Accept-Language", "zh-CN,zh;q=0.9");
        map.put("Connection", "keep-alive");
        map.put("Content-Type", "application/json");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.5024.400 QQBrowser/10.0.932.400");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }


        //创建指定内容和编码的字符串实体类
        StringEntity entity = new StringEntity(jsonObject.toString(), "UTF-8");
        //设置请求参数
        httpPost.setEntity(entity);

        // 创建HttpClient对象，CloseableHttpClient实例的生成器
        httpClient = httpClientBuilder.build();

        try {
            // 发送HttpPost请求，获取返回值
            CloseableHttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //释放资源
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return JSONObject.parseObject(result);

    }


    /**
     * 以form表单形式提交数据，发送post请求
     * @explain
     *   1.请求头：httppost.setHeader("Content-Type","application/x-www-form-urlencoded")
     *   2.提交的数据格式：key1=value1&key2=value2...
     * @param url 请求地址
     * @param paramsMap 具体数据
     * @return 服务器返回数据
     */
    public static JSONObject httpPostWithForm(String url,Map<String, String> paramsMap){
        // 用于接收返回的结果
        String result ="";
        try {
            HttpPost post = new HttpPost(url);
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            // 迭代Map-->取出key,value放到BasicNameValuePair对象中-->添加到list中
            for (String key : paramsMap.keySet()) {
                pairList.add(new BasicNameValuePair(key, paramsMap.get(key)));
            }
            UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairList, "utf-8");
            post.setEntity(uefe);
            // 创建一个http客户端
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            // 发送post请求
            HttpResponse response = httpClient.execute(post);

            // 状态码为：200
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                // 返回数据：
                result = EntityUtils.toString(response.getEntity(),"UTF-8");
            }else{
                throw new RuntimeException("接口连接失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("接口连接失败！");
        }
        return JSONObject.parseObject(result);
    }
}
