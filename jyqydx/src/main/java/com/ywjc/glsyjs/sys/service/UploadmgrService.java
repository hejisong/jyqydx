package com.ywjc.glsyjs.sys.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Uploadmgr;

public interface UploadmgrService extends BaseService<Uploadmgr, String> {
	
	public Uploadmgr findmgr(String infoid,String infotype,String year,String actiontype);

}
