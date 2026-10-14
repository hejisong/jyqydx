package com.ywjc.glsyjs.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读写配置文件 路径格式 ： src/user.properties
 * 
 * @author cyh
 *
 */
public class PropertiesUtil {
	private static Logger log = Logger.getLogger(PropertiesUtil.class);

    public static final Properties prop = new Properties();
    public static final String  dirpath =  ConfigUtil.getConfigVal("file_save_path")+"\\ywjcdatafile\\visitcount\\";
    public static final String  filepath =  dirpath+"visitcount.properties";

	/**
	 * 取出值
	 * 
	 * @param k
	 * @param filepath
	 * @return
	 */
	public static String getValue(String key) {

		String propValue = null;
		InputStream in = null;
		File file = new File(dirpath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(filepath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
			in = new BufferedInputStream(fs);
			try {
				prop.load(in);
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		propValue = prop.getProperty(key);
		if (propValue == null) {
			propValue = "0";
		}

		return propValue;
	}

	/**
	 * 设置键值
	 * 
	 * @param filepath
	 * @param map
	 */
	public static void setValue(Map<String, String> map) {
		/// 保存属性到b.properties文件
		FileOutputStream oFile = null;
		try {

			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			oFile = new FileOutputStream(file, false);
			// true表示追加打开
			prop.setProperty(map.get("key"), map.get("value"));
			prop.store(oFile, "The New properties file");
			oFile.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}  finally {
			try {
				if (null != oFile) {
					oFile.close();
				}

			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
			}
		}
	}

}