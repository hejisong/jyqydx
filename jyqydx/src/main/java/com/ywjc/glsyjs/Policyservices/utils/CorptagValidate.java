package com.ywjc.glsyjs.Policyservices.utils;

import com.ywjc.glsyjs.common.validate.ErrorEntity;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

public class CorptagValidate {

	public static ValidateBinding validateCorptag(Sheet sheet){
		ValidateBinding bind = new ValidateBinding(new ArrayList<ErrorEntity>());
		String companyname=null;
		String uscreditcode=null;
		String tagyear=null;
		String tagname=null;
		String error="";
		int numberrow=sheet.getPhysicalNumberOfRows();
		for(int j=1;j<numberrow;j++){
			Row row = sheet.getRow(j); // 获得工作薄的第j行
			if(row!=null){
				 for(int k=0;k<8;k++){// 获得工作薄的第j行的第k列的值
					 Cell cell = row.getCell(k);
					 if(cell!=null){
						 cell.setCellType(Cell.CELL_TYPE_STRING);
						 if(k==0){//企业名称
							 companyname=publicExcel(cell).trim();
						 }else if(k==1){//统一社会信用代码
							 uscreditcode=publicExcel(cell).trim();
						 }else if(k==2){//年份
							 tagyear=publicExcel(cell).trim();
						 }else if(k==3){//标签名称
							 tagname=publicExcel(cell).trim();
						 }
					 }
					 
				 }
				 if(StringUtils.isBlank(companyname)&& StringUtils.isBlank(uscreditcode)&& StringUtils.isBlank(tagyear) && StringUtils.isBlank(tagname)){
					     return bind;
				 }else{
					 error="\n第"+(j+1)+"行";
						if(StringUtils.isBlank(companyname) ||(companyname!=null&&companyname.length()>200)){
							bind.rejectValue("companyname",error+"企业名称不能为空且长度不能超过1000个字符！",error+"企业名称不能为空且长度不能超过200个字符！");
						}else if(StringUtils.isBlank(uscreditcode) ||(uscreditcode!=null&&uscreditcode.length()>30)){
							bind.rejectValue("uscreditcode",error+"统一社会信用代码不能为空且长度不能超过30个字符！",error+"统一社会信用代码不能为空且长度不能超过30个字符！");
						}else if(StringUtils.isBlank(tagyear) ||(tagyear!=null&&tagyear.length()>4)){
							bind.rejectValue("tagyear",error+"年份不能为空且长度不能超过4个字符！",error+"年份不能为空且长度不能超过4个字符！");
						}else if(StringUtils.isBlank(tagname) ||(tagname!=null&&tagname.length()>100)){
							bind.rejectValue("tagname",error+"标签名称不能为空且长度不能超过100个字符！",error+"标签名称不能为空且长度不能超过100个字符！");
						}




				 }	 
			}		 
			
		}
		return bind;
	}
	
	
	
	 private static String publicExcel( Cell cell){
	        String value = null;   
	        if(cell!=null){
	            switch (cell.getCellType()) {  
	            case HSSFCell.CELL_TYPE_NUMERIC:
	             value =Double.toString(cell.getNumericCellValue());  
	             break;  
	            case HSSFCell.CELL_TYPE_STRING:
	             value = cell.getStringCellValue();  
	             break;  
	            case HSSFCell.CELL_TYPE_BLANK:
	             ;  
	             break;  
	            default:   
	          } 
	        }
	 
	        return value;  
	    }
	 
	   
	
}
