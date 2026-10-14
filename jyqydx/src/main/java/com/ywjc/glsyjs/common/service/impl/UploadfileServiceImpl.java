package com.ywjc.glsyjs.common.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.repositories.UploadfileRepository;
import com.ywjc.glsyjs.common.service.FileService;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.service.impl.BaseServiceImpl;

@Service("uploadfileServiceImpl")
public class UploadfileServiceImpl extends BaseServiceImpl<Uploadfile, String>
		implements UploadfileService {

	@Resource(name = "uploadfileRepository")
	private UploadfileRepository repository;
	
	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	@Resource(name = "uploadfileRepository")
	public void setReposity(
			PagingAndSortingRepository<Uploadfile, String> repository) {
		super.setReposity(repository);
	}

	@Override
    @Transactional(readOnly = true)
    public Uploadfile find(String id) {
        return super.find(id);
    }

	@Override
	@Transactional(readOnly = true)
	public List<Uploadfile> findAll() {
		return super.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadfile> findList(String[] ids) {
		return super.findList(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadfile> findList(Sort sort) {
		return super.findList(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Uploadfile> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	@Override
	@Transactional
	public void save(Uploadfile entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	public Uploadfile update(Uploadfile entity) {
		Uploadfile uploadfile = super.find(entity.getId());
		super.update(entity);
		return uploadfile;
	}

	@Override
	@Transactional
	public Uploadfile update(Uploadfile entity, String... ignoreProperties) {
		Uploadfile uploadfile = super.update(entity, ignoreProperties);
		return uploadfile;
	}

	@Override
	@Transactional
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	@Transactional
	public void delete(String[] ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	public void delete(Uploadfile entity) {
		super.delete(entity);
	}

	@Override
	@Transactional
	public String uploadFlash(File tempFile,Uploadfile file){
		return fileService.upload(tempFile, file);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Uploadfile> findByIson(String ison){
		return repository.findByIson(ison);
	}
	
	@Override
	@Transactional
	public void updateFileIson(String ison,String[] ids){
		repository.updateFileIson(ison,ids);
	}
	
	@Override
	@Transactional
	public void updateFileIson(String ison,String ids){
		repository.updateFileIson(ison,ids);
 	}

	@Override
	@Transactional
	public void deleteUnIsonFile() {
		repository.deleteUnIsonFile();
	}
	
	// 解除文件关联
	@Override
	public void closeFileIson(String fileid) {
		if (fileid != null && !fileid.equals("")) {
			Uploadfile uploadfile =  find(fileid);
			if (uploadfile != null) {
				uploadfile.setIson(Uploadfile.CONSTANT_ISON_ZERO);// 解除关联
				 update(uploadfile);
			}
		}
	}

	// 打开文件关联
	@Override
	public void openFileIson(String fileid) {
		if (fileid != null && !fileid.equals("")) {
			Uploadfile uploadfile = find(fileid);
			if (uploadfile != null) {
				uploadfile.setIson(Uploadfile.CONSTANT_ISON_ONE);// 关联
				 update(uploadfile);
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Uploadfile findPolicyfile(String plfidid) {
		return repository.findPolicyfile(plfidid);
	}
	@Override
	@Transactional(readOnly = true)
	public List<Uploadfile> findPolicyfiles(String plfidid) {
		return repository.findPolicyfiles(plfidid);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadfile> findFlowProcessAsset(String assettype) {
		return repository.findFlowProcessAsset(assettype);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Uploadfile> findUploadfilesbyfujianfileid(String fujianfileid,String plycomresultid) {
		return repository.findUploadfilesbyfujianfileid(fujianfileid,plycomresultid);
	}

}
