package com.ywjc.glsyjs.cache;

import java.util.HashMap;
import java.util.Map;
public final class CacheUtil {
	private static CacheUtil cutil = null;
	
	private Map<String,Cachemodel> cacheMap = new HashMap<>();

	private CacheUtil() {
	};
	
	public synchronized  static CacheUtil newInstance() {
		if(cutil==null){
//			synchronized (CacheUtil.class) {
//				if(cutil==null){
					cutil = new CacheUtil();
//				}
//			}
		}
		return cutil;
	}
	
	public void addCache(String k, Cachemodel m) {
		cacheMap.put(k, m);
	}
	
	public void clearModel(String k){
		Cachemodel m = cacheMap.get(k);
		if(m!=null){
			m.clear();
			m=null;
			cacheMap.put(k,m);
		}
	}
	
	public Map<String,Object> getCache(String k) {
		Map<String,Object> caches = null;
		Cachemodel m = cacheMap.get(k);
		if(m!=null){
			long mi = System.currentTimeMillis()-m.getCdate().getTime()-m.getVdate()*60*60*1000;
			if(mi<0){
				caches =  m.getCacheMap();
			}else{
				m.clear();
				m=null;
				cacheMap.put(k,m);
			}
		}
		return caches;
	}
}
