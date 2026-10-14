package com.ywjc.glsyjs.core.util.xss;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


public class SQLFilter {
    /**
     * SQL注入过滤
     * 
     * @param str
     *            待验证的字符串
     */
    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        // 去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");
        str = StringUtils.replace(str, "alert", "");
        str = StringUtils.replace(str, "confirm", "");
        str = StringUtils.replace(str, "prompt", "");
        str = StringUtils.replace(str, "script", "");
        
        // 转换成小写
        str = str.toLowerCase();

        // 非法字符
        String[] keywords = { "master", "truncate", "insert", "select", "delete", "update", "declare", "alert",
                "create", "drop" };
        // 判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword)!=-1) {
//                System.out.println("包含非法字符");
            }
        }

        return str;
    }
}
