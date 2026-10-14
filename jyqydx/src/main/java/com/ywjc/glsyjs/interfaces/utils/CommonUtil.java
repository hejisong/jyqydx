package com.ywjc.glsyjs.interfaces.utils;/**
 * Created by li_hao on 2019/9/29.
 */

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {


    /**
     * 字符串模糊匹配
     * @param parent 父字符串
     * @param child  子字符串
     */
    public static boolean matchString( String parent, String child){
        boolean flag = false;
        String[] childSplit = child.split("");
        int nums = 0;
        for(String str : childSplit){
            int count = 0;
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(parent);
            while(m.find()){
                count++;
            }
            nums += count;
        }
        if(nums >= childSplit.length){
            flag = true;
        }
        return flag;
    }

    /**
     * 生成1至6位随机数
     * @param type 1/2/3/4/5/6 生成几位随机数
     * @return
     */
    public static int mathRandom(int type){
        int randonNum = 0;
        switch(type){
                case 1://生成1位随机数字
                    randonNum = (int)((Math.random()*9+1));
                    break;
                case 2://生成2位随机数字
                    randonNum = (int)((Math.random()*9+1)*10);
                    break;
                case 3://生成3位随机数字
                    randonNum = (int)((Math.random()*9+1)*100);
                    break;
                case 4://生成4位随机数字
                    randonNum = (int)((Math.random()*9+1)*1000);
                    break;
                case 5://生成5位随机数字
                    randonNum = (int)((Math.random()*9+1)*10000);
                    break;
                case 6://生成6位随机数字
                    randonNum = (int)((Math.random()*9+1)*100000);
                    break;
            }
        return randonNum;
    }


    /**
     * 拆分数组
     * @param ary 数组
     * @param subSize 拆分大小
     * @return
     */
    public static Object[] splitAry(String[] ary, int subSize) {
        int count = ary.length % subSize == 0 ? ary.length / subSize : ary.length / subSize + 1;
        List<List<Object>> subAryList = new ArrayList<List<Object>>();
        for (int i = 0; i < count; i++) {
            int index = i * subSize;
            List<Object> list = new ArrayList<Object>();
            int j = 0;
            while (j < subSize && index < ary.length) {
                list.add(ary[index++]);
                j++;
            }
            subAryList.add(list);
        }
        Object[] subAry = new Object[subAryList.size()];
        for (int i = 0; i < subAryList.size(); i++) {
            List<Object> subList = subAryList.get(i);
            Object[] subAryItem = new Object[subList.size()];
            for (int j = 0; j < subList.size(); j++) {
                subAryItem[j] = subList.get(j);
            }
            subAry[i] = subAryItem;
        }
        return subAry;
    }

    public  static String generateFuzzyString(String code){
        code = StringUtils.isNotBlank(code)?"%"+code+"%":"%%";
        return code;
    }

    public static void main(String[] args) {
//        String str[] = new String[15];
//        for(int i=0;i<str.length;i++){
//            str[i] = String.valueOf(i);
//        }
//        System.out.println(111);
//        Object[] objects = splitAry(str, 4);
//
//        for(Object ary : objects){
//            JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(ary));
//            List<String> strList = JSONObject.parseArray(jsonArray.toJSONString(), String.class);
//
//            String[] strings = strList.toArray(new String[strList.size()]);
//
//            System.out.println(strings);
//
//
//        }


    }
    public static String getOrderIdByUUId() {
        int first = new Random(10).nextInt(8) + 1;
        System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }


}
