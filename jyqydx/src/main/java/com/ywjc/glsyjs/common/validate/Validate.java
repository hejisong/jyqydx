package com.ywjc.glsyjs.common.validate;

import org.apache.commons.lang.StringUtils;

import java.util.Date;


public class Validate {



	public static void valiString(String valuename,String value,String valuedescrip,boolean required,int maxlength,ValidateBinding bindingResult){
		if(required==true){
			if(value==null||value.trim().length()==0){
				if(-1!=maxlength){
					String error="请输入"+valuedescrip+"！";
					bindingResult.rejectValue(valuename,error,error);
				}else{
					String error="请选择"+valuedescrip+"！";
					bindingResult.rejectValue(valuename,error,error);					
				}

			}
		}
		if(maxlength>0&&value!=null&&value.trim().length()>0){
			if(value.trim().length()>maxlength){
				String error=valuedescrip+"长度不能超过"+maxlength+"个字符！";
				bindingResult.rejectValue(valuename,error,error);
			}
		}

	  }
public static  void valiDouble(String valuename,Double value,String valuedescrip,boolean required,Double maxnumber,ValidateBinding bindingResult){

	if(required==true){
		if(value==null||value.toString().trim().length()==0){
			String error="请输入"+valuedescrip+"！";
			bindingResult.rejectValue(valuename,error,error);
		}
	}
	if(value!=null&&value.toString().trim().length()>0) {
 		String[] numeric=value.toString().split("\\.");
 		String[] numericdtand=maxnumber.toString().split("\\.");
 		if("0".equals(numericdtand[1])){
 	    	if(Integer.parseInt(numeric[0]) >Integer.parseInt(numericdtand[0])||Integer.parseInt(numeric[1])>0){
 				String error=valuedescrip+"不能大于"+numericdtand[0]+"（必须为整数）！";
 				bindingResult.rejectValue(valuename,error,error);
 			}
 		}else{
 	    	if(Integer.parseInt(numeric[0])>Integer.parseInt(numericdtand[0])||Integer.parseInt(numeric[1])>Integer.parseInt(numericdtand[1])){
 				String error=valuedescrip+"不能大于"+maxnumber+"（整数位不能大于"+ numericdtand[0].length()+"位，小数位不能大于"+ numericdtand[1].length()+"位）！";
 				bindingResult.rejectValue(valuename,error,error);
 			}
 		}

 	}
   }

 
			
//日期比较
public static void valiDate(String valuename,Date value,String valuedescrip,ValidateBinding bindingResult){
	if(value==null||value.toString().trim().length()==0){
		String error="请输入"+valuedescrip+"！";
		bindingResult.rejectValue(valuename,error,error);
	}
 }
public static void valiDateCompare(String valuename,Date date_small,String date_smallscrip,Date date_big,String date_bigscrip,ValidateBinding bindingResult){
	if(date_small!=null&&date_big!=null&&((date_small.after(date_big))||date_small.equals(date_big))){
 		String error=date_bigscrip+"不能早于等于"+date_smallscrip+"！";
		bindingResult.rejectValue(valuename,error,error);
	}
 }

//身份证号18位
public static void valicardid(String valuename,String value,String valuedescrip,ValidateBinding bindingResult){
	if(value==null||value.trim().length()==0){
			String error="请输入"+valuedescrip+"！";
			bindingResult.rejectValue(valuename,error,error);
	}
	if(value!=null&&value.trim().length()!=18){
			String error=valuedescrip+"身份证号必须为18位！";
			bindingResult.rejectValue(valuename,error,error);
	}
  }
//附件
public static void valiFile(String valuename,String value,String valuedescrip,ValidateBinding bindingResult){
	if(value==null||value.trim().length()<1){
		String error="请上传"+valuedescrip+"附件！" ;
		bindingResult.rejectValue(valuename,error,error);
	}
 }

public static void valiFile1(String valuename,String value,String valuedescrip,boolean required,int maxnumber,ValidateBinding bindingResult){
	if(required) {
		if(null==value||value.trim().length()<1){
			String error="请上传"+valuedescrip+"附件！" ;
			bindingResult.rejectValue(valuename,error,error);
		}
	}
	if(StringUtils.isNotBlank(value)) {
		if(null!=value&&value.length()>200){
			String error=valuedescrip+"附件最多不能超过6个！" ;
			bindingResult.rejectValue(valuename,error,error);
		}
	}

 }


}
