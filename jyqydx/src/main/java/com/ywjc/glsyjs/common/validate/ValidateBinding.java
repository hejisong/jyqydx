package com.ywjc.glsyjs.common.validate;

import java.util.List;


public class ValidateBinding {
	
	private List<ErrorEntity> errors = null;
	
	public ValidateBinding(){
	}
	
	public ValidateBinding(List<ErrorEntity> errors){
		this.errors = errors;
	}
	
	public void rejectValue(String colunname,String errorcode,String defaultMessage){
		ErrorEntity error=new ErrorEntity(colunname,errorcode,defaultMessage);
		errors.add(error);
	}
	
	public List<ErrorEntity> getAllErrors(){
		return errors;
	}
	
	public boolean hasErrors(){
		if(errors!=null&&errors.size()>0){
			return true;
		}
		return false;
	}
	
	
	public String getErrorHtml(){
		StringBuffer html=new StringBuffer();
		html.append("<button class='close' data-close='alert'></button>");
		for(ErrorEntity error:errors){
			html.append("<li>")
				.append(error.getDefaultMessage())
				.append("</li>");
		}
		return html.toString();
	}
}

