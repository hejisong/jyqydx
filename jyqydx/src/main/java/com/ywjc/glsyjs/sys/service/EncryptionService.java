package com.ywjc.glsyjs.sys.service;

import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Encryption;

import javax.servlet.http.HttpSession;
import java.util.Date;
//import com.centit.glsyjs.corpinfo.entity.Qydjxx;

public interface EncryptionService extends BaseService<Encryption, String> {
  
	public String  genKeyPairforcolumn(String tablename,String colmname,String recordid ,String val, Date now,HttpSession session);

//	public void encryptedAll();
	
	public void encrypted();
	//对单个对象进行加密(保存和更新)
	public void encryptforbean(String tablename, String p_key_value);
	
	public Object encryptforbean2(Object obj);
	
	public void encryptforcolmname(String tablename, String p_key_value,String colname,String decrypt_value);
//	public void encryptforQydjxx(Qydjxx qydjxx);

	public String findbyColnameAndtablename(String tableName, String colname, String p_key_value);
}
