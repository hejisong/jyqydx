package com.ywjc.glsyjs.common.service;

import java.io.File;

import com.ywjc.glsyjs.common.FileInfo;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service - File
 * 
 */
public interface FileService {

	/**
	 * Upload file
	 * 
	 * @param fileType
	 * @param multipartFile
	 * @param async
	 * @return file URL
	 */
	String upload(FileInfo.FileType fileType, MultipartFile multipartFile, boolean async);
	/**
	 * Uploads file
	 *
	 * @param fileType
	 * @param multipartFile
	 * @param async
	 * @return file URL
	 */
	String uploads(FileInfo.FileType fileType, MultipartFile multipartFile, boolean async);
	/**
	 * Upload file asynchronously
	 * 
	 * @param fileType
	 * @param multipartFile
	 * @return file URL
	 */
	String upload(FileInfo.FileType fileType, MultipartFile multipartFile);

	/**
	 * Uploads file asynchronously
	 *
	 * @param fileType
	 * @param multipartFile
	 * @return file URL
	 */
	String uploads(FileInfo.FileType fileType, MultipartFile multipartFile);
	String serverUpload(File file,String filename);

	/**
	 * Download file asynchronously
	 * 
	 * @param fileType
	 * @param fileName
	 * @param multipartFile
	 * @param localPath
	 * @return true or false
	 */
	String download(String fileid);
	
/*	String downloadfile(String fileid, String filename, String path);*/
	
	boolean delete(String fileid);
	
	void deleteTemp(String id);
	
	String upload(File tempFile, Uploadfile file);
	String uploads(File tempFile,Uploadfile file);
	String upload2(FileInfo.FileType fileType, MultipartFile multipartFile);
	
	String upload2(FileInfo.FileType fileType, MultipartFile multipartFile, boolean async);
}