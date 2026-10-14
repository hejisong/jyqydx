package com.ywjc.glsyjs.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import com.ywjc.glsyjs.common.FileInfo;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.FileService;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.util.ZIPUtil;

@Service("fileServiceImpl")
public class FileServiceImpl implements FileService, ServletContextAware {
	private static Logger log = Logger.getLogger(FileServiceImpl.class);
	/** servletContext */
	private ServletContext servletContext;

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

	@Resource(name = "uploadfileServiceImpl")
	private UploadfileService uploadfileService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * add upload task
	 * 
	 * @param tempFile
	 * @param contentType
	 */
	private void addTask(final File tempFile, final String contentType) {
		taskExecutor.execute(new Runnable() {
			public void run() {

			}
		});
	}

	@Override
	public String upload(FileInfo.FileType fileType, MultipartFile multipartFile, boolean async) {
		if (multipartFile == null) {
			return null;
		}

		String id = null;
		try {
			String fileName = multipartFile.getOriginalFilename();
			if(StringUtils.isNotBlank(fileName)) {
				 if (!fileName.endsWith(".image") && !fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".docx")
						 && !fileName.endsWith(".doc") && !fileName.endsWith(".rar") && !fileName.endsWith(".pdf") && !fileName.endsWith(".xlsx") 
						 && !fileName.endsWith(".xls")) {
					 return "{\"state\":\"文件类型不符合\"}";
			       }

			}
//			fileName = "<img onclick=alter(1)\\>" + fileName;
			fileName = fileName.replaceAll("eval\\((.*)\\)", "").replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
					"\"\"");
			fileName = fileName.replace("|", "");
			fileName = fileName.replace("$", "");
			fileName = fileName.replace("'", "");
			fileName = fileName.replace("\"", "");
			fileName = fileName.replace("alert", "");
			fileName = fileName.replace("http", "");
			fileName = fileName.replace("script", "");
			fileName = fileName.replace("\\", "");
			fileName = fileName.replace("<", "");
			fileName = fileName.replace(">", "");
			fileName = fileName.replaceAll("'", "").replaceAll("script", "").replaceAll("delete", "")
					.replaceAll("select", "").replaceAll("update", "").replaceAll("drop", "").replaceAll("insert", "")
					.replaceAll("export", "").replaceAll("insert", "").replaceAll("import", "").replaceAll("set", "")
					.replaceAll("from", "").replaceAll("where", "").replaceAll("into", "").replaceAll(" or ", "")
					.replaceAll(" and ", "").replaceAll("%", "").replaceAll("ltrim", "").replaceAll("load_file", "")
					.replaceAll("union", "").replaceAll("\"", "").replaceAll("exec", "").replaceAll(";", "")
					.replaceAll("|", "").replaceAll("&", "").replaceAll("$", "").replaceAll(";", "")
					.replaceAll("CR", "").replaceAll("LF", "");
			String filesize = Long.toString(multipartFile.getSize());
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (prefix.equals("image") || prefix.equals("png") || prefix.equals("jpg")||prefix.equals("docx")|| prefix.equals("doc") || prefix.equals("rar") || prefix.equals("pdf") ||
					prefix.equals("xlsx") || prefix.equals("xls") ) {
				id = UUID.randomUUID().toString();
				id = id.replace("-", "");
				File tempFile = new File(ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + id + "." + prefix);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}			
				multipartFile.transferTo(tempFile);
//		        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去
//		        compress("H:\\zip/scala","H:\\zip/oo.zip");
				/*String path=tempFile.getPath().substring(0,tempFile.getPath().lastIndexOf("."))+".rar";//文件生成地址
				ZIPUtil.compress(tempFile.getPath(),path,fileName);
				File tempFile2 = new File(path);*/
				if (async) {
					addTask(tempFile, multipartFile.getContentType());
				} else {
					// 保存数据库
//					byte[] filecontent = toinputStream(tempFile);
					id = saveUploadfile(id, null, prefix, fileName, filesize);
				}
			}
			return id;

		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		}
		return null;

	}
	@Override
	public String uploads(FileInfo.FileType fileType, MultipartFile multipartFile, boolean async) {
		if (multipartFile == null) {
			return null;
		}

		String id = null;
		try {
			String fileName = multipartFile.getOriginalFilename();

//			fileName = "<img onclick=alter(1)\\>" + fileName;
			fileName = fileName.replaceAll("eval\\((.*)\\)", "").replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
					"\"\"");
			fileName = fileName.replace("|", "");
			fileName = fileName.replace("$", "");
			fileName = fileName.replace("'", "");
			fileName = fileName.replace("\"", "");
			fileName = fileName.replace("alert", "");
			fileName = fileName.replace("http", "");
			fileName = fileName.replace("script", "");
			fileName = fileName.replace("\\", "");
			fileName = fileName.replace("<", "");
			fileName = fileName.replace(">", "");
			fileName = fileName.replaceAll("'", "").replaceAll("script", "").replaceAll("delete", "")
					.replaceAll("select", "").replaceAll("update", "").replaceAll("drop", "").replaceAll("insert", "")
					.replaceAll("export", "").replaceAll("insert", "").replaceAll("import", "").replaceAll("set", "")
					.replaceAll("from", "").replaceAll("where", "").replaceAll("into", "").replaceAll(" or ", "")
					.replaceAll(" and ", "").replaceAll("%", "").replaceAll("ltrim", "").replaceAll("load_file", "")
					.replaceAll("union", "").replaceAll("\"", "").replaceAll("exec", "").replaceAll(";", "")
					.replaceAll("|", "").replaceAll("&", "").replaceAll("$", "").replaceAll(";", "")
					.replaceAll("CR", "").replaceAll("LF", "");
			String filesize = Long.toString(multipartFile.getSize());
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		/*	if (prefix.equals("image") || prefix.equals("png") || prefix.equals("jpg")||prefix.equals("docx")|| prefix.equals("doc") || prefix.equals("rar") || prefix.equals("pdf") ||
					prefix.equals("xlsx") || prefix.equals("xls") ) {*/
				id = UUID.randomUUID().toString();
				id = id.replace("-", "");
				File tempFile = new File(
						ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + id + "." + prefix);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				multipartFile.transferTo(tempFile);
//		        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去
//		        compress("H:\\zip/scala","H:\\zip/oo.zip");
				/*String path=tempFile.getPath().substring(0,tempFile.getPath().lastIndexOf("."))+".rar";//文件生成地址
				ZIPUtil.compress(tempFile.getPath(),path,fileName);
				File tempFile2 = new File(path);*/
				if (async) {
					addTask(tempFile, multipartFile.getContentType());
				} else {
					// 保存数据库
//					byte[] filecontent = toinputStream(tempFile);
					id = saveUploadfile(id, null, prefix, fileName, filesize);
				}
//			}
			return id;

		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		}
		return null;

	}
	@Override
	public String serverUpload(File file, String filename) {
		if (file == null) {
			return null;
		}

		String fileId = null;
		String id = null;
		try {
			String prefix = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
			id = UUID.randomUUID().toString();
			id = id.replace("-", "");
			File tempFile = new File(ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + id + "." + prefix);
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(file, tempFile);
			fileId = uploadfile(tempFile);
			// 保存数据库
//			byte[] filecontent = toinputStream(tempFile);
			id = saveUploadfile(id, null, prefix, filename, null);
			return fileId + "," + id;

		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		}
		return null;
	}

	private String uploadfile(File tempFile) {
		return null;
	}

	public String upload(File tempFile, Uploadfile file) {
		return null;
	}
	public String uploads(File tempFile, Uploadfile file) {
		return null;
	}
	@Override
	public String upload(FileInfo.FileType fileType, MultipartFile multipartFile) {
		return upload(fileType, multipartFile, false);
	}
	@Override
	public String uploads(FileInfo.FileType fileType, MultipartFile multipartFile) {
		return uploads(fileType, multipartFile, false);
	}

	@Override
	public String download(String fileid) {
		return null;
	}

	@Override
	public boolean delete(String fileid) {
		return true;
	}

	@Override
	public void deleteTemp(String id) {
		Uploadfile docfile = uploadfileService.find(id);
		if (docfile != null) {
			String ext = '.' + docfile.getFileext().toLowerCase();
			String fromfileName = System.getProperty("java.io.tmpdir") + "/upload_" + docfile.getId() + ext;
			if (fromfileName != null) {
				File tempFile = new File(fromfileName);
				if (tempFile.exists()) {
					FileUtils.deleteQuietly(tempFile);
				}
			}
		}
	}

	private String saveUploadfile(String id, byte[] filecontent, String prefix, String filename, String filesize) {
		Uploadfile obj = new Uploadfile();
		obj.setId(id);
		obj.setFilecontent(filecontent);
		obj.setFileext(prefix);
		obj.setFilename(filename);
		obj.setIson(Uploadfile.CONSTANT_ISON_ZERO);
		obj.setUpdatedate(new Date());
		obj.setFilesize(filesize);
		uploadfileService.save(obj);
		return obj.getId();
	}

	
	@Override
	public String upload2(FileInfo.FileType fileType, MultipartFile multipartFile) {
		return upload2(fileType, multipartFile, false);
	}

	@Override
	public String upload2(FileInfo.FileType fileType, MultipartFile multipartFile, boolean async) {
		if (multipartFile == null) {
			return null;
		}

		String id = null;
		try {
			String fileName = multipartFile.getOriginalFilename();
			if(StringUtils.isNotBlank(fileName)) {
				 if (!fileName.endsWith(".image") && !fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".docx")
						 && !fileName.endsWith(".doc") && !fileName.endsWith(".rar") && !fileName.endsWith(".pdf") && !fileName.endsWith(".xlsx") 
						 && !fileName.endsWith(".xls")&& !fileName.endsWith(".TLE")) {
					 return "{\"state\":\"文件类型不符合\"}";
			       }

			}
//			fileName = "<img onclick=alter(1)\\>" + fileName;
			fileName = fileName.replaceAll("eval\\((.*)\\)", "").replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
					"\"\"");
			fileName = fileName.replace("|", "");
			fileName = fileName.replace("$", "");
			fileName = fileName.replace("'", "");
			fileName = fileName.replace("\"", "");
			fileName = fileName.replace("alert", "");
			fileName = fileName.replace("http", "");
			fileName = fileName.replace("script", "");
			fileName = fileName.replace("\\", "");
			fileName = fileName.replace("<", "");
			fileName = fileName.replace(">", "");
			fileName = fileName.replaceAll("'", "").replaceAll("script", "").replaceAll("delete", "")
					.replaceAll("select", "").replaceAll("update", "").replaceAll("drop", "").replaceAll("insert", "")
					.replaceAll("export", "").replaceAll("insert", "").replaceAll("import", "").replaceAll("set", "")
					.replaceAll("from", "").replaceAll("where", "").replaceAll("into", "").replaceAll(" or ", "")
					.replaceAll(" and ", "").replaceAll("%", "").replaceAll("ltrim", "").replaceAll("load_file", "")
					.replaceAll("union", "").replaceAll("\"", "").replaceAll("exec", "").replaceAll(";", "")
					.replaceAll("|", "").replaceAll("&", "").replaceAll("$", "").replaceAll(";", "")
					.replaceAll("CR", "").replaceAll("LF", "");
			String filesize = Long.toString(multipartFile.getSize());
			String prefix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (prefix.equals("image") || prefix.equals("png") || prefix.equals("jpg") || prefix.equals("docx")
					|| prefix.equals("doc") || prefix.equals("rar") || prefix.equals("pdf") || prefix.equals("xlsx")
					|| prefix.equals("xls") || fileName.endsWith(".TLE")) {

				id = UUID.randomUUID().toString();
				id = id.replace("-", "");
				File tempFile = new File(
						ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + id + "." + prefix);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				multipartFile.transferTo(tempFile);
				if (async) {
					addTask(tempFile, multipartFile.getContentType());
				} else {
					// 保存数据库
//					byte[] filecontent = toinputStream(tempFile);
					id = saveUploadfile(id, null, prefix, fileName, filesize);
				}
			}

			return id;

		} catch (IOException e) {
			log.error(e.getMessage());
			log.info(e);
		}
		return null;

	}
}
