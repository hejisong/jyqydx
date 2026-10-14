package com.ywjc.glsyjs.sys.entity;

import java.util.List;

import com.google.common.cache.LoadingCache;
public class DaoCache {
    
    public   LoadingCache<String, List<Object[]>> rsFindOjbect =null;
    public   LoadingCache<String, List<Object>> rslistOjbect =null;
    public   LoadingCache<String, List<Object[]>> rsMonthFindOjbect =null;
    
}