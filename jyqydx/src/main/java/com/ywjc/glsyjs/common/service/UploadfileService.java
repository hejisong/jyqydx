package com.ywjc.glsyjs.common.service;

import java.io.File;
import java.util.List;

import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.core.service.BaseService;

public interface UploadfileService extends BaseService<Uploadfile, String>  {
	public String uploadFlash(File tempFile,Uploadfile file);
	public List<Uploadfile> findByIson(String ison);
	public void updateFileIson(String ison,String[] ids);
	
	public void updateFileIson(String ison,String ids);
	
	public void deleteUnIsonFile();
	void closeFileIson(String fileid);
	void openFileIson(String fileid);
	public Uploadfile findPolicyfile(String plfidid);

	public List<Uploadfile> findFlowProcessAsset(String assettype);

	public List<Uploadfile> findUploadfilesbyfujianfileid(String fujianfileid,String plycomresultid);

	List<Uploadfile> findPolicyfiles(String plfidid);
}
