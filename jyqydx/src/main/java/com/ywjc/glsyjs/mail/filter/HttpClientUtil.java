package com.ywjc.glsyjs.mail.filter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HTTP请求服务端
 * @author xyz
 *
 */
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	/**
	 * get方式请求服务端
	 * @param url
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String httpGet(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		//请求超时 
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
		//读取超时 
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			int code = response.getStatusLine().getStatusCode();
			HttpEntity entity = null;
			if(!(200==code)) throw new Exception("服务端返回错误!错误码："+code+"");
			entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			logger.error("context", e);
			return null;
		}finally{
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	
	/**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数以json字符串的形式。
     * @return 所代表远程资源的响应结果
     */
	public static String sendPost(String url, String param) {
    	HttpClient httpclient = new DefaultHttpClient();
        //请求超时 
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15000000);
        //读取超时 
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15000000);
        
        try {
            HttpPost httppost = new HttpPost(url);
            
            StringEntity ent = new StringEntity(param.toString(),"utf-8");//解决中文乱码问题
            ent.setContentEncoding("UTF-8");    
            ent.setContentType("application/json");    
            httppost.setEntity(ent);    
                        
            HttpResponse response = httpclient.execute(httppost);
            int code = response.getStatusLine().getStatusCode();
            if(!(200==code)){
                httppost.abort();  
                return "";
            }
            
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = in.read(b)) != -1) {
                outSteam.write(b, 0, len);
            }
            outSteam.close();
            in.close();
           return new String(outSteam.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("context", e);
            return "";
        }finally{
            httpclient.getConnectionManager().shutdown();
        }
    }
	
	   
    /**
     * 通过POST方法请求服务器，并获取服务器返回(返回字符串)
     * 
     * @author liangyi
     * @date 2013-11-27上午11:42:04
     * @param action
     *            ACTION
     * @param paramMap
     *            参数集 paramName,value键值对
     * @return
     */
    public static String post(String url, Map<String, Object> paramMap) {
        HttpClient httpclient = new DefaultHttpClient();
        //请求超时 
        httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        //读取超时 
        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);
        
        try {
            HttpPost httppost = new HttpPost(url);
            
            // 建立参数列表
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            
            // 添加参数
            if (null != paramMap && 0 < paramMap.size()) {
                Set<String> keyset = paramMap.keySet();
                for (String str : keyset) {
                    if (null==paramMap.get(str))
                    {
                        continue;
                    }
                    params.add(new BasicNameValuePair(str, paramMap.get(str)
                            .toString()));
                }
            }
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, "UTF-8");
            httppost.setEntity(uefEntity);
            HttpResponse response = httpclient.execute(httppost);
            int code = response.getStatusLine().getStatusCode();
            if(!(200==code)){
                httppost.abort();  
                return "";
            }
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = in.read(b)) != -1) {
                outSteam.write(b, 0, len);
            }
            outSteam.close();
            in.close();
           return new String(outSteam.toByteArray(), "UTF-8");
        } catch (Exception e) {
            logger.error("context", e);
            return "";
        }finally{
            httpclient.getConnectionManager().shutdown();
        }
    }
}
