package com.b10r.redisCache.constant;

public enum ResponseStatus {
	
	SUCCESS("SUCCESS");
	
	ResponseStatus(String str){
		this.value=str;
	}
	private String value;
	
	public String getValue() {
		return value;
	}
	
	
	
	

}
