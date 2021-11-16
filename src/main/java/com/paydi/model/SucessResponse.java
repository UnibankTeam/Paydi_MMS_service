package com.paydi.model;

import java.util.HashMap;

public class SucessResponse {
  
	private int status ;
	private HashMap<String, Object> result; 
	private String message;

	public SucessResponse() {
	}
 

	public SucessResponse(  int statusCode, String message, HashMap<String, Object> result) {
 
		this.status= statusCode;
		this.message = message;
		this.result = result;

	} 
	
	  
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "JwtResponse [ status=" + status + ", message=" + message + ", data="
				+ result + "]";
	};
}
