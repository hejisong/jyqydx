package com.ywjc.glsyjs.Policyservices.utils;

import com.ywjc.glsyjs.common.validate.ErrorEntity;
import com.ywjc.glsyjs.common.validate.ValidateBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;

public class PolicydetailValidate {

	public static ValidateBinding validateEvalResult(Sheet sheet){
		ValidateBinding bind = new ValidateBinding(new ArrayList<ErrorEntity>());
		String error="";
		String nplcategory=null;
		String plsubclassname=null;
		String plconditions=null;
		String rdescription=null;
		String ramount=null;
		String thpath=null;
		String pevaluationdepartment=null;
		String feaunit=null;
		String cjtype=null;
		String declareconditions=null;
		int numberrow=sheet.getPhysicalNumberOfRows();
		for(int j=1;j<numberrow;j++){
			Row row = sheet.getRow(j); // 获得工作薄的第j行
			if(row!=null){
				 for(int k=0;k<8;k++){// 获得工作薄的第j行的第k列的值
					 Cell cell = row.getCell(k);
					 if(cell!=null){
						 cell.setCellType(Cell.CELL_TYPE_STRING);
						 if(k==0){//政策大类
							 nplcategory=publicExcel(cell).trim();
						 }else if(k==1){//政策小类
							 plsubclassname=publicExcel(cell).trim();
						 }else if(k==2){//政策条件
							 plconditions=publicExcel(cell).trim();
						 }else if(k==3){//奖励描述
							 rdescription=publicExcel(cell).trim();
						 }else if(k==4){//奖励金额
							 ramount=publicExcel(cell).trim();
						 }else if(k==5){//兑付路径
							 thpath=publicExcel(cell).trim();
						 }else if(k==6){//审批单位
							 pevaluationdepartment=publicExcel(cell).trim();
						 }else if(k==7){//参与评定部门
							 feaunit=publicExcel(cell).trim();
						 }else if(k==8){//政策明细类型
							 cjtype=publicExcel(cell).trim();
						 }else if(k==9){//申报条件
							 declareconditions=publicExcel(cell).trim();
						 }
					 }
					 
				 }
				 if(StringUtils.isBlank(nplcategory)&& StringUtils.isBlank(plsubclassname)&& StringUtils.isBlank(plconditions)
						 && StringUtils.isBlank(rdescription)&& StringUtils.isBlank(ramount)&& StringUtils.isBlank(thpath)&& StringUtils.isBlank(pevaluationdepartment)
						 && StringUtils.isBlank(feaunit)&& StringUtils.isBlank(cjtype)&& StringUtils.isBlank(declareconditions)){
					     return bind;
				 }else{
					 error="\n第"+(j+1)+"行";
						if(nplcategory!=null &&nplcategory.length()>200){
							bind.rejectValue("nplcategory",error+"政策大类长度不能超过200个字符！",error+"政策大类长度不能超过200个字符！");
						}else if(plsubclassname!=null&&plsubclassname.length()>400){
						  bind.rejectValue("plsubclassname",error+"政策小类长度不能超过200个字符！",error+"政策小类长度不能超过200个字符！");
						}else if(StringUtils.isBlank(plconditions) ||(plconditions!=null&&plconditions.length()>1000)){
							bind.rejectValue("plconditions",error+"政策条件不能为空且长度不能超过1000个字符！",error+"政策条件不能为空且长度不能超过1000个字符！");
						}else if(StringUtils.isBlank(rdescription) ||(rdescription!=null&&rdescription.length()>500)){
							bind.rejectValue("rdescription",error+"奖励描述不能为空且长度不能超过500个字符！",error+"奖励描述不能为空且长度不能超过500个字符！");
						}else if(StringUtils.isBlank(plconditions) ||(plconditions!=null&&plconditions.length()>1000)){
							//bind.rejectValue("plconditions",error+"政策条件不能为空且长度不能超过1000个字符！",error+"政策条件不能为空且长度不能超过1000个字符！");
						}else if(thpath!=null&&thpath.length()>200){
							  bind.rejectValue("thpath",error+"兑付路径长度不能超过200个字符！",error+"兑付路径长度不能超过200个字符！");
						}else if(pevaluationdepartment!=null&&pevaluationdepartment.length()>200){
							  bind.rejectValue("pevaluationdepartment",error+"审批单位长度不能超过200个字符！",error+"审批单位长度不能超过200个字符！");
						}else if(feaunit!=null&&feaunit.length()>500){
							bind.rejectValue("feaunit",error+"参与评定部门长度不能超过500个字符！",error+"参与评定部门长度不能超过500个字符！");
						}else if(cjtype!=null&&cjtype.length()>100){
							bind.rejectValue("cjtype",error+"政策明细类型长度不能超过100个字符！",error+"政策明细类型长度不能超过100个字符！");
						}else if(declareconditions!=null&&declareconditions.length()>1000){
							bind.rejectValue("feaunit",error+"申报条件长度不能超过1000个字符！",error+"申报条件不能超过1000个字符！");
						}
						if(StringUtils.isNotBlank(ramount)){
							try{
								Double.parseDouble(ramount);
							}catch(NumberFormatException e){
								bind.rejectValue("ramount",error+"奖励金额数据格式错误！",error+"奖励金额数据格式错误！");
							}
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
