package com.ywjc.datashare.core.repository.support;

public enum MatchType {
	
	EQUAL(false), 
	BEWTEEN(false), 
	LESS_THAN(false), 
	LESS_THAN_EQUAL(false),
	GREATEROR_THAN(false), 
	GREATEROR_THAN_EQUAL(false), 
	NOT_EQUAL(false), 
	IS_NULL(true), 
	IS_NOT_NULL(true), 
	RIGHT_LIKE(false), 
	LEFT_LIKE(false), 
	LIKE(false), 
	DEFAULT_LIKE(false), 
	NOT_LIKE(false), 
	IN(false);
	
	// 是否可以为空
	private boolean isCanBeNull;

	private MatchType(boolean isCanBeNull) {
		this.isCanBeNull = isCanBeNull;
	}

	public boolean checkIsNotCanBeNull() {
		return !this.isCanBeNull;
	}

	public boolean checkIsCanBeNull() {
		return this.isCanBeNull;
	}

}
