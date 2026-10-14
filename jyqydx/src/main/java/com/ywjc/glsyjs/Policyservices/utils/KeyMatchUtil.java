package com.ywjc.glsyjs.Policyservices.utils;

import com.ywjc.glsyjs.sys.utilenctry.StringUtil;
import org.apache.commons.lang3.StringUtils;

public class KeyMatchUtil {

    public static String getMatchedString(String Originalstring,String keystr  ) {
        if(StringUtils.isNotBlank(keystr)){
            String[] keys=keystr.split("、");
            for (String key:keys) {
                Originalstring=Originalstring.replaceAll(key,"<span style=\"color:red;\">"+key+"</span>");
            }
        }

      return Originalstring;
    }


}
