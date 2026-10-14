package com.ywjc.glsyjs.core.util;

import com.ywjc.glsyjs.core.util.xss.SQLFilter;


public class ParameterFilter {
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        s = SQLFilter.sqlInject(s);
//        s = StringEscapeUtils.unescapeHtml(s);
//        s = XssHttpServletRequestWrapperAjax.xssEncode(s);
        return s;
    }

}
