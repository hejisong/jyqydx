package com.ywjc.glsyjs.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class ImageUtil {
	private static Logger log = Logger.getLogger(ImageUtil.class);  
	
	 private static BufferedImage  Bytes2Bimap(byte[] b) throws IOException{  
         if(b.length!=0){
        	 ByteArrayInputStream in = new ByteArrayInputStream(b);    //将b作为输入流；
        	 BufferedImage img=null;
			try {
				if(in!=null) {
					img = ImageIO.read(in);
				}
			
			} catch (IOException e) {
				log.error(e.getMessage());
				log.info(e);
			}finally{
				if(in!=null){
					in.close();
				}
			}
             return img;  
         }  
         else {  
             return null;  
         }  
   } 
	public static byte[] compressPic(byte[] b){
		byte[] bout = null;
		ByteArrayOutputStream out=null;
		try{
			BufferedImage  img = Bytes2Bimap(b);   
		     // 判断图片格式是否正确   
			if(img!=null) {
				   if (img.getWidth(null) == -1) {  
				         return bout;   
				     } else {   
				         int newWidth; int newHeight;   
				             // 为等比缩放计算输出的图片宽度及高度   
				             double rate1 = ((double) img.getWidth(null)) / (double) 100 + 0.1;   
				             double rate2 = ((double) img.getHeight(null)) / (double) 100 + 0.1;   
				             // 根据缩放比率大的进行缩放控制   
				             double rate = rate1 > rate2 ? rate1 : rate2;   
				             newWidth = (int) (((double) img.getWidth(null)) / rate);   
				             newHeight = (int) (((double) img.getHeight(null)) / rate);   
				  
				        BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
				          
				        /* 
				         * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
				         * 优先级比速度高 生成的图片质量比较好 但速度慢 
				         */   
				        tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
				        out = new ByteArrayOutputStream();  
				        // JPEGImageEncoder可适用于其他图片类型的转换   
				        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
				       // encoder.encode(tag);   
				
				
				        bout = out.toByteArray();  
			}
		    
		        
		     }   
		 } catch (IOException e) {   
			 log.error(e.getMessage());
			 log.info(e);
		 } finally{
			 try {
				 if(out!=null){
						out.close();
					}
			} catch (IOException e) {
				 log.error(e.getMessage());
				 log.info(e);
			}  
		 }
		 return bout;   
		 }

	 
}
