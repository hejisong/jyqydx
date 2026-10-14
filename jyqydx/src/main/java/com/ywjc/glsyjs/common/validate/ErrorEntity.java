package com.ywjc.glsyjs.common.validate;

public class ErrorEntity {
	
	private String colname;

	private String errorcode;

	private String defaultMessage;
	
	public ErrorEntity(){
	}
	
	public ErrorEntity(String colname,String errorcode,String defaultMessage){
		this.colname = colname;
		this.errorcode = errorcode;
		this.defaultMessage = defaultMessage;
	}

	public String getColname() {
		return colname;
	}

	public void setColname(String colname) {
		this.colname = colname;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}
}
