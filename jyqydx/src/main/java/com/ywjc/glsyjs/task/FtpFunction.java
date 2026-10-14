package com.ywjc.glsyjs.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Ftp相关操作函数
 * 
 * @author hjs
 * 
 */
@Component("ftpfunction")
public class FtpFunction {

	private static Logger log = Logger.getLogger(FtpFunction.class);
	/**
	 * 创建FTP的目录
	 * 
	 * @param mFTPClient
	 * @param uploadDir
	 */
	public static void makeDirectory(FTPClient mFTPClient, String uploadDir) {
		try {
			String[] dirArray = uploadDir.split("/");
			for (int i = 0; i < dirArray.length; i++) {
				if (dirArray[i] != null && !dirArray[i].equals(""))
					if (!mFTPClient.changeWorkingDirectory(dirArray[i])) {
						mFTPClient.makeDirectory(dirArray[i]);
						mFTPClient.changeWorkingDirectory(dirArray[i]);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * upload 上传文件
	 * 
	 * @throws Exception
	 * @return -1 文件不存在 -2 文件内容为空 >0 成功上传，返回文件的大小
	 * @param newname
	 *            上传后的新文件名
	 * @param filename
	 *            上传的文件
	 * @throws IOException
	 */
	public static long upload(FTPClient ftpClient, String filename,
			String newname) throws IOException {
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		long result = 0;
		OutputStream os = null;
		FileInputStream is = null;
		try {
			File file_in = new File(filename);
			if (!file_in.exists())
				return -1;
			if (file_in.length() == 0)
				return -2;
			os = ftpClient.storeFileStream(newname);
			result = file_in.length();
			is = new FileInputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		return result;
	}

	/**
	 * download 从ftp下载文件到本地
	 * 
	 * @throws Exception
	 * @return
	 * @param newfilename
	 *            本地生成的文件名
	 * @param filename
	 *            服务器上的文件名
	 * @throws IOException
	 */
	public static long download(FTPClient ftpClient, String filename,
			String newfilename) throws IOException {
		long result = 0;
		InputStream is = null;
		FileOutputStream os = null;
		try {
			ftpClient.setControlEncoding("GBK");
			File outfile = new File(newfilename);
			os = new FileOutputStream(outfile);
			is = ftpClient.retrieveFileStream(filename);
			if (is == null) { // 如果文件不存在则返回0
				return 0;
			}
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
				result = result + c;
			}
		} catch (Exception e) {
			return result = 0;
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		return result;
	}

	/**
	 * 删除文件函数
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {

		// Date curDate = new Date();
		// Date oldDate = null;
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					// oldDate = new Date(fileList[i].lastModified());
					// if (getDateTimeDiff(curDate, oldDate) < 0)
					fileList[i].delete();
				} else {
					deleteFile(fileList[i].getAbsolutePath());
					fileList[i].deleteOnExit();// 删除文件夹
				}
			}
		} else if (file.isFile()) {
			// oldDate = new Date(file.lastModified());
			// if (getDateTimeDiff(curDate, oldDate) < 0)
			file.delete();
		}

	}

	/**
	 * 得到时间差数
	 * 
	 * @param curDate
	 * @param oldDate
	 * @return
	 */
	public static int getDateTimeDiff(Date curDate, Date oldDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return sdf.format(oldDate).compareTo(sdf.format(curDate));
	}

	/**
	 * 获取FTP服务器客户端对象
	 
	 * 
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	//@RequestMapping(value="/findlist",method = RequestMethod.GET)
	public static FTPClient getFtpClient() throws IOException {
	/*	if (ftpInfo == null || ftpInfo.isEmpty()) {
			return null;
		}*/

		/*String ftpServer = (String) ftpInfo.get("192.168.0.110");
		int ftpPort = Integer.parseInt((String) ftpInfo.get("21"));
		String ftpUser = (String) ftpInfo.get("wjl");
		String ftpPwd = (String) ftpInfo.get("123456");*/

		return getFtpClients("192.168.0.110", 21, "wjl", "123456");
	}

	/**
	 * 获取服务器客户端对象
	 * 
	 * @param ftpServer
	 * @param ftpPort
	 * @param ftpUser
	 * @param ftpPwd
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static FTPClient getFtpClients(String ftpServer, int ftpPort,
			String ftpUser, String ftpPwd) throws IOException {

		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("GBK");

		ftpClient.connect(ftpServer, ftpPort);
		ftpClient.login(ftpUser, ftpPwd);
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		return ftpClient;
	}
}
