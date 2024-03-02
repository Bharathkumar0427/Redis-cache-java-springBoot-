package com.b10r.redisCache.Dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3402106694848243905L;

	private String responseStatus;
	
	private List<Object> obj;
	
	private Object obj1;

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public List<Object> getObj() {
		return obj;
	}

	public void setObj(List<Object> obj) {
		this.obj = obj;
	}

	public Object getObj1() {
		return obj1;
	}

	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}
	
	

}
