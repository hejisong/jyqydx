package com.ywjc.glsyjs.mail;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ywjc.glsyjs.account.entity.Ewmlogin;
import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.mail.filter.HttpClientUtil;
import com.ywjc.glsyjs.mail.twocode.MatrixToImageWriter;
import com.ywjc.glsyjs.mail.twocode.MyTest;
import com.ywjc.glsyjs.sys.util.Dimension2;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ErweimaLogn {
    private static Logger log = Logger.getLogger(ErweimaLogn.class);
    public static void ewmpost(){
        String date=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
        Properties prop = new Properties();
        String url="";
        try {
            prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("centit.properties"));
            url = prop.getProperty("createerweimaurl");
            if(url==null){
                url="";
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            log.info(e);
        }
        log.info("----获取数据的url："+url);
        //TODO
        if(url!=null){
            log.info(date+"开始访问生成二维码接口");
            String Datajson = HttpClientUtil.sendPost(url,"{\"qrType\":\"3S31\",\"custom\":\"测试接口\"}");
            log.info(date+"生成二维码接口返回参数"+Datajson);
            if(Datajson!=null&& !"".equals(Datajson)){
                JSONObject jsonObject = JSONObject.fromObject(Datajson);
                log.info(date+"jsonObject："+jsonObject);
                // 获取到key为shoppingCartItemList的值
                String retCode = jsonObject.getString("retCode");
                String retMessage = jsonObject.getString("retMessage");
                String qrCode = jsonObject.getString("qrCode");

                if(qrCode!=null&&!"".equals(qrCode)){
                    try {
//                        String content = "https://www.baidu.com";
                        String path = "D:/测试";// 二维码保存的路径
                        mkdirs(path);
                        String codeName = UUID.randomUUID().toString();// 二维码的图片名
                        String imageType = "jpg";// 图片类型
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
                        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
                        BitMatrix bitMatrix = multiFormatWriter.encode(qrCode, BarcodeFormat.QR_CODE, 400, 400, hints);
                        File file1 = new File(path, codeName + "." + imageType);
                        MatrixToImageWriter.writeToFile(bitMatrix, imageType, file1);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }
        public static void ccxxvs(String token) {
//            if(org.apache.commons.lang3.StringUtils.isBlank(token)){
//                return "false";
//            }
            String date=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date());
            Properties prop = new Properties();
            String url="";
            try {
                prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream("centit.properties"));
                url = prop.getProperty("querytokenurl");
                if(url==null){
                    url="";
                }
            } catch (IOException e) {
                log.error(e.getMessage());
                log.info(e);
            }
            log.info("----获取数据的url："+url);
            //TODO
            if(url!=null){
                url+="?token="+token;
                log.info(date+"开始访问获取用户信息接口");
                String Datajson = HttpClientUtil.httpGet(url);
                log.info(date+"获取用户信息接口返回参数"+Datajson);
                if(org.apache.commons.lang3.StringUtils.isNotBlank(Datajson)&&!"null".equals(Datajson)){
                    JSONObject jsonObject = JSONObject.fromObject(Datajson);
                    log.info(date+"jsonObject："+jsonObject);
                    // 获取到key为shoppingCartItemList的值
                    String retCode = jsonObject.getString("retCode");
                    String retMessage = jsonObject.getString("retMessage");
                    String tokenValue = jsonObject.getString("tokenValue");
                    if(org.apache.commons.lang3.StringUtils.isNotBlank(tokenValue)&&!"null".equals(tokenValue)){
                        //解密tokenvalue
//                    String re1 = Dimension2.decryptWithPublickey2("ad2Gylkt8cT++Ky5vU2yggSiWBRJ9jNunspwVuRMfXAMK9mcngedG+0xuMs1QCRTOgV3BJbABEKNmVxw/YTKMKoZlIsSpfBeSgczfEZ9mkhUxl+Zo8ExXop2fk5Eznlkz0rClwbec3TSPXZQxHBhVYxgD3f8qM4T5Lg2P/nMcfXudg+HLfddXBOVLOmYb4ksdgvlYNxgt0POTY8uRvsBnIDOHYuY2KecxA6KnNPEQW+XL32o2L8lTkV3DMN9nZixFlRMER/aaQeu7TldYyLm6FtBbWE5016NJMi0Kfs8nn5RS4DtsvjBPFTs6//bvXWoyIwRkoUCGoAcTj/EyvnzwRoH2rUV246NBGPg1rvsg38=");
                        String re1 = Dimension2.decryptWithPublickey2(tokenValue);
                        System.out.println("re1："+re1);
                        JSONObject jsonre1 = JSONObject.fromObject(re1);
                        log.info(date+"jsonObject："+jsonObject);
                        // 获取到key为shoppingCartItemList的值
                        String active = jsonre1.getString("active");
                        String custom = jsonre1.getString("custom");
                        String passive = jsonre1.getString("passive");
                        String qid = jsonre1.getString("qid");
                        String qrType = jsonre1.getString("qrType");
                        String sceneID = jsonre1.getString("sceneID");

                        // {"active":{"uid":"2e574b89-d179-478f-a98a-080d87d38a2a","idNum":"320xxxxxxxxx1233"},"custom":"111111","passive":{"uid":null},"qid":"222222222","qrType":"2S15","sceneID":""}
//                    if(tempStr!=null&&!"".equals(tempStr)){
//                        JSONObject jsonObject2 = JSONObject.fromObject(tempStr);
//                        String tqqk = jsonObject2.getString("tqqk");
//                        String wd = jsonObject2.getString("wd");
//                        String weather="天气："+tqqk+"；温度："+wd;
//                        localrecord.setWeather(weather);
//                    }
//                        if(StringUtils.isNotBlank(qid)){
//                            Ewmlogin ewmlogin=ewmloginService.findByQid(qid);
//                            if(ewmlogin!=null){
//                                ewmlogin.setEwmstate("2");
//                                ewmlogin.setEwmmessage("登录成功");
//                                ewmloginService.update(ewmlogin);
//                            }
//                        }
                        //TODO
                        String username="";
                        String linknum="";
                        String linktel="";

                        //查询用户信息添加用户信息
//                        List<User> userinfos=userService.findusersinnameorlinknum(username,linknum,linktel);
//                        if(userinfos!=null&&userinfos.size()>0){
//                            //存在用户信息返回登录成功
//                        }else{
//                            //不存在用户信息注册用户
//                            User userinfo=new User();
//                            String id = UUID.randomUUID().toString();
//                            id = id.replace("-", "");
//                            userinfo.setId(id);
//                            userinfo.setUsertype("5");
//                            userinfo.setUsername(username);
//                            userinfo.setLinknum(linknum);
//                            userinfo.setLinktel(linktel);
//                            userService.save(userinfo);
//                        }

                    }

                }

            }
//            return "true";
        }
    public static void main(String[] args) {
//        ewmpost();
        ccxxvs("BC0F69EA141D00BCE0530A656106EDCD");
    }

}
