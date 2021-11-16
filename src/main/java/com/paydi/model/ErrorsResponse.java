package com.paydi.model;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorsResponse {
	private String status;
	private int statusCode;
	private String error;
	private HashMap<String, Object> result;

	public ErrorsResponse() {
	}

	public ErrorsResponse(String status, int statusCode, String error, HashMap<String, Object> result) {
		this.status = status;
		this.statusCode = statusCode;
		this.error = error;
	}

	
}
