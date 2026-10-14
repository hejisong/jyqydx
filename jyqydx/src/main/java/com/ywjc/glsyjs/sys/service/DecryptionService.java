package com.ywjc.glsyjs.sys.service;

import com.ywjc.glsyjs.account.entity.User;
import com.ywjc.glsyjs.core.service.BaseService;
import com.ywjc.glsyjs.sys.entity.Decryption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DecryptionService extends BaseService<Decryption, String> {
  
	public Decryption findbyrecordid(String tablename, String colmname, String recordid);
	//解码
	public String decrypt(String str, String privateKey);
	  
	//为page解码
	public List<Object> decryptforPage(Page page);
	//为page解密，并且返回该对象
	public Page<Object> decryptforPage2(Page page,Pageable pageable);
	

	public User decryptforUser(User obj);


	//解密定时任务
	public void Decrypted();

}
