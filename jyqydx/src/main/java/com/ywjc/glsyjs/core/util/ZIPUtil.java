package com.ywjc.glsyjs.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ZIPUtil  {
	private static Logger log = Logger.getLogger(ZIPUtil.class);
	 /**
     * @param args 主方法
     */
//    public static void main(String[] args) {
//        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去，第三个参数是重新命名压缩包里的文件名字
//        compress("H:\\zip/scala","H:\\zip/oo.zip","");
//    }
 
    /**s
     * 压缩文件
     * @param srcFilePath 压缩源路径
     * @param destFilePath 压缩目的路径
     */
    public static void compress(String srcFilePath, String destFilePath,String fileName) {
        //
        File src = new File(srcFilePath);
        
        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);
        FileOutputStream fos=null;
        ZipOutputStream zos =null;
        try {
 
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(src, zos, baseDir,fileName);
            zos.close();
        } catch (FileNotFoundException e) {
        	log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if(null!=fos) {
				try {
					fos.close();
				} catch (IOException e) {
					System.err.println("IOException Occured");
				}
			}
		}
    }
    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     * @param src
     * @param zos
     * @param baseDir
     */
     private static void compressbyType(File src, ZipOutputStream zos,String baseDir,String fileName) {
 
            if (!src.exists())
                return;
//            System.out.println("压缩路径" + baseDir + src.getName());
            //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
            if (src.isFile()) {
                //src是文件，调用此方法
                compressFile(src, zos, baseDir,fileName);
                 
            } else if (src.isDirectory()) {
                //src是文件夹，调用此方法
                compressDir(src, zos, baseDir,fileName);
 
            }
 
        }
      
        /**
         * 压缩文件
        */
        private static void compressFile(File file, ZipOutputStream zos,String baseDir,String fileName) {
            if (!file.exists())
                return;
            BufferedInputStream bis =null;
            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                ZipEntry entry = new ZipEntry(baseDir + (StringUtils.isBlank(fileName)?file.getName():fileName));
                zos.putNextEntry(entry);
                int count;
                byte[] buf = new byte[1024];
                while ((count = bis.read(buf)) != -1) {
                    zos.write(buf, 0, count);
                }
                bis.close();
 
            } catch (IOException e) {
            	log.error(e.getMessage());
			} finally {
    			if(null!=bis) {
    				try {
    					bis.close();
    				} catch (IOException e) {
    					System.err.println("IOException Occured");
    				}
    			}
    		
    		}
        }
         
        /**
         * 压缩文件夹
         */
        private static void compressDir(File dir, ZipOutputStream zos,String baseDir,String fileName) {
            if (!dir.exists())
                return;
            File[] files = dir.listFiles();
            if(files.length == 0){
                try {
                    zos.putNextEntry(new ZipEntry(baseDir + dir.getName()+File.separator));
                } catch (IOException e) {
					System.err.println("IOException Occured");
                }
            }
            for (File file : files) {
                compressbyType(file, zos, baseDir + dir.getName() + File.separator,fileName);
            }
    }
	
}
