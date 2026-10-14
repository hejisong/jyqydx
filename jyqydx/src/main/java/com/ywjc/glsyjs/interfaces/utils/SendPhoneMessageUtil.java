package com.ywjc.glsyjs.interfaces.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SendPhoneMessageUtil {
    private static Logger log = LoggerFactory.getLogger(SendPhoneMessageUtil.class);

    /**
     * 查询字符串中(str)某个字符串(key)出现的次数
     *
     * @param str
     * @param key
     * @return
     */
    public static int getStrNums(String str, String key) {
        int count = 0;
        int index = 0;
        if (!"".equals(str) && str != null) {
            while ((index = str.indexOf(key, index)) != -1) {
                index = index + key.length();
                count++;
            }
        }
        return count;
    }

    /**
     * 字符串转数组
     *
     * @param str      字符串
     * @param splitStr 分隔符
     * @return
     */
    public static String[] StringToArray(String str, String splitStr) {
        String[] arrayStr = null;
        if (!"".equals(str) && str != null) {
            if (str.indexOf(splitStr) != -1) {
                arrayStr = str.split(splitStr);
            } else {
                arrayStr = new String[1];
                arrayStr[0] = str;
            }
        }
        return arrayStr;
    }

    static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // sing签名
    public static String getMD5(String plainText) {
        try {
            byte[] strTemp = plainText.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    // 创建验证码
    public String smsCode() {
        String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return random;
    }

    // 设置验证码到期时间
    public String getExDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        /* 设置验证码3分钟后过期 */
        calendar.add(Calendar.MINUTE, +3);
        date = calendar.getTime();
        return sdf.format(date);
    }


}
