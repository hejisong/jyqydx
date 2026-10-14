package com.ywjc.glsyjs.common.controller;

import com.ywjc.glsyjs.account.entity.Principal;
import com.ywjc.glsyjs.common.entity.Uploadfile;
import com.ywjc.glsyjs.common.service.FileService;
import com.ywjc.glsyjs.common.service.UploadfileService;
import com.ywjc.glsyjs.core.util.ConfigUtil;
import com.ywjc.glsyjs.common.FileInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@Controller("uploadfileController")
@RequestMapping("/common/file")
public class UploadfileController {
	private static Logger log = Logger.getLogger(UploadfileController.class);
	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	@Resource(name = "uploadfileServiceImpl")
	private UploadfileService uploadfileService;
	

	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String testfile() {
		return "/sys/file/uploadfile";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String handleFormUpload(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upfile");
		if (file == null) {
			return "{\"state\":\"没有需要上传的文件\"}";
		}
		if (file.getSize() == 0) {
			return "{\"state\":\"没有需要上传的文件\"}";
		}
		String fileSize = "";
		if (file.getSize() > 1024 * 1024) {
			fileSize = (Math.round(file.getSize() * 100 / (1024 * 1024)) / 100) + "MB";
		} else {
			fileSize = (Math.round(file.getSize() * 100 / 1024) / 100) + "KB";
		}
		String fileName = file.getOriginalFilename();
		if(StringUtils.isNotBlank(fileName)) {
			 if (!fileName.endsWith(".image") && !fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".docx")
					 && !fileName.endsWith(".doc") && !fileName.endsWith(".rar") && !fileName.endsWith(".pdf") && !fileName.endsWith(".xlsx") 
					 && !fileName.endsWith(".xls") && !fileName.endsWith(".TLE")) {
				 return "{\"state\":\"文件类型不符合\"}";
		       }

		}
//		fileName = fileName +"<img onclick=alter(1)\\>";
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
				.replaceAll("|", "").replaceAll("&", "").replaceAll("$", "").replaceAll(";", "").replaceAll("CR", "")
				.replaceAll("LF", "");

		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!prefix.equals("image") && !prefix.equals("png") && !prefix.equals("jpg") && !prefix.equals("docx")
				&& !prefix.equals("doc") && !prefix.equals("rar") && !prefix.equals("pdf") && !prefix.equals("xlsx")
				&& !prefix.equals("xls")) {
			return "{\"state\":\"文件类型不符合\"}";
		}
		String type = file.getContentType();
		type = type.replaceAll("eval\\((.*)\\)", "").replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		type = type.replace("|", "");
		type = type.replace("$", "");
		type = type.replace("'", "");
		type = type.replace("\"", "");
		type = type.replace("alert", "");
		type = type.replace("http", "");
		type = type.replace("script", "");
		type = type.replace("\\", "");
		type = type.replace("<", "");
		type = type.replace(">", "");
		type = type.replaceAll("'", "").replaceAll("script", "").replaceAll("delete", "").replaceAll("select", "")
				.replaceAll("update", "").replaceAll("drop", "").replaceAll("insert", "").replaceAll("export", "")
				.replaceAll("insert", "").replaceAll("import", "").replaceAll("set", "").replaceAll("from", "")
				.replaceAll("where", "").replaceAll("into", "").replaceAll(" or ", "").replaceAll(" and ", "")
				.replaceAll("%", "").replaceAll("ltrim", "").replaceAll("load_file", "").replaceAll("union", "")
				.replaceAll("\"", "").replaceAll("exec", "").replaceAll(";", "").replaceAll("|", "").replaceAll("&", "")
				.replaceAll("$", "").replaceAll(";", "").replaceAll("CR", "").replaceAll("LF", "");
		String str = fileService.upload(FileInfo.FileType.file, file);
		if (str == null) {

			return "{\"state\":\"上传文件失败\"}";
		}
//		fileName=fileName.substring(0,fileName.lastIndexOf("."))+".rar";
		String str_utf = "{'fileType':'" + type + "','state':'SUCCESS'," + "'fileSize':'" + fileSize + "','name':'"
				+ fileName + "','id':'" + str + "'}";
		return str_utf;
	}

	
	@RequestMapping(value = "/uncompress",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String uncompress(HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upfile");
		if (file == null) {
			return "{\"state\":\"没有需要上传的文件\"}";
		}
		if (file.getSize() == 0) {
			return "{\"state\":\"没有需要上传的文件\"}";
		}
		String fileSize = "";
		if (file.getSize() > 1024 * 1024) {
			fileSize = (Math.round(file.getSize() * 100 / (1024 * 1024)) / 100) + "MB";
		} else {
			fileSize = (Math.round(file.getSize() * 100 / 1024) / 100) + "KB";
		}
		String fileName = file.getOriginalFilename();
		if(StringUtils.isNotBlank(fileName)) {
			 if (!fileName.endsWith(".image") && !fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".docx")
					 && !fileName.endsWith(".doc") && !fileName.endsWith(".rar") && !fileName.endsWith(".pdf") && !fileName.endsWith(".xlsx") 
					 && !fileName.endsWith(".xls")&& !fileName.endsWith(".TLE")) {
				 return "{\"state\":\"文件类型不符合\"}";
		       }

		}
//		fileName = fileName +"<img onclick=alter(1)\\>";
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
				.replaceAll("|", "").replaceAll("&", "").replaceAll("$", "").replaceAll(";", "").replaceAll("CR", "")
				.replaceAll("LF", "");

		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!prefix.equals("image") && !prefix.equals("png") && !prefix.equals("jpg") && !prefix.equals("docx")
				&& !prefix.equals("doc") && !prefix.equals("rar") && !prefix.equals("pdf") && !prefix.equals("xlsx")
				&& !prefix.equals("xls") && !fileName.endsWith(".TLE")) {
			return "{\"state\":\"文件类型不符合\"}";
		}
		String type = file.getContentType();
		type = type.replaceAll("eval\\((.*)\\)", "").replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		type = type.replace("|", "");
		type = type.replace("$", "");
		type = type.replace("'", "");
		type = type.replace("\"", "");
		type = type.replace("alert", "");
		type = type.replace("http", "");
		type = type.replace("script", "");
		type = type.replace("\\", "");
		type = type.replace("<", "");
		type = type.replace(">", "");
		type = type.replaceAll("'", "").replaceAll("script", "").replaceAll("delete", "").replaceAll("select", "")
				.replaceAll("update", "").replaceAll("drop", "").replaceAll("insert", "").replaceAll("export", "")
				.replaceAll("insert", "").replaceAll("import", "").replaceAll("set", "").replaceAll("from", "")
				.replaceAll("where", "").replaceAll("into", "").replaceAll(" or ", "").replaceAll(" and ", "")
				.replaceAll("%", "").replaceAll("ltrim", "").replaceAll("load_file", "").replaceAll("union", "")
				.replaceAll("\"", "").replaceAll("exec", "").replaceAll(";", "").replaceAll("|", "").replaceAll("&", "")
				.replaceAll("$", "").replaceAll(";", "").replaceAll("CR", "").replaceAll("LF", "");
		String str = fileService.upload2(FileInfo.FileType.file, file);
		if (str == null) {

			return "{\"state\":\"上传文件失败\"}";
		}

		String str_utf = "{'fileType':'" + type + "','state':'SUCCESS'," + "'fileSize':'" + fileSize + "','name':'"
				+ fileName + "','id':'" + str + "'}";
		return str_utf;
	}







	
	
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public String downloadfile(@PathVariable String id, HttpServletResponse response,HttpServletRequest request) {
		Uploadfile file = uploadfileService.find(id);
		if (file == null) {
			return "/error/uploadfilenotfound";
		}
//		Lawfile  lawfile =null;
//		Workfile workfile=null;
		HttpSession session=request.getSession();
		if(null!=session) {
			Principal principal=(Principal)session.getAttribute("userinfo");
			if(null==principal) {
//				lawfile =lawfileService.findbyfileid(file.getId());
//				workfile=workfileService.findbyfileid(file.getId());
//				if(null==lawfile&&null==workfile) {
//					return "/error/noauthority";
//				}
			}
		}else {
//			lawfile =lawfileService.findbyfileid(file.getId());
//			workfile=workfileService.findbyfileid(file.getId());
//			if(null==lawfile&&null==workfile) {
//				return "/error/noauthority";
//			}
		}
		String filename = file.getFilename();
		String filepath = ConfigUtil.getConfigVal("file_save_path") + "/doc/upload_" + file.getId() + "."
				+ file.getFileext();
		FileInputStream in = null;
		OutputStream out = null;
		if (!new File(filepath).exists()) {
			return "/error/uploadfilenotfound";
		} else {
			try {
				in = new FileInputStream(new File(filepath));

				// 所有不想下载的文件格式添加
				// if(!(filetype.equals("text")||filetype.equals("image")||filetype.equals("png")||filetype.equals("jpg")))
				response.setContentType("application/x-msdownload");
				// 2.设置文件头：最后一个参数是设置下载文件名
				response.setHeader("Content-Disposition",
						"attachment;filename=" + new String(filename.getBytes("gbk"), "iso-8859-1"));
				out = response.getOutputStream();
				byte[] buf = new byte[1048576];
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
			} finally {
				try {
					if (in != null) {
						in.close();
					}
					if (out != null) {
						out.flush();
						out.close();
					}

				} catch (IOException ex2) {
					log.error(ex2.getMessage());
					log.info(ex2);
				}
			}
		}

		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String testPage(@PathVariable String id) {
		uploadfileService.delete(id);
		return "OK";
	}
}
