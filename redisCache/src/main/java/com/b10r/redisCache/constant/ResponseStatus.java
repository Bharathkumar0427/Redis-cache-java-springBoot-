package com.b10r.redisCache.constant;

public enum ResponseStatus {
	
	SUCCESS("SUCCESS"),FAILED("FAILED"),UNKNOWN_ID("CANNOT FIND A ENTRY FOR THE ID");
	
	ResponseStatus(String str){
		this.value=str;
	}
	private String value;
	
	public String getValue() {
		return value;
	}
	
	
	
	

}
