package com.ywjc.glsyjs.cache;

import java.util.Date;
import java.util.Map;

public class Cachemodel {

private Map<String, Object> cacheMap;
	
	private Date cdate;
	
	/** 以小时计算 */
	private int vdate;

	public Map<String, Object> getCacheMap() {
		return cacheMap;
	}

	public void setCacheMap(Map<String, Object> cacheMap) {
		this.cacheMap = cacheMap;
	}
	
	public void clear(){
		this.cacheMap.clear();
		this.cacheMap = null;
		this.cdate = null;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public int getVdate() {
		return vdate;
	}

	public void setVdate(int vdate) {
		this.vdate = vdate;
	} 
}
