package com.paydi.model;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SuccessResponse {

	private String requestId;
	private int status;
	private String statusCode;
	private HashMap<String, Object> result;

	public SuccessResponse() {
	}

	public SuccessResponse(String requestId, int status, String statusCode, HashMap<String, Object> result) {

		this.requestId = requestId;
		this.status = status;
		this.statusCode = statusCode;
		this.result = result;

	}
}
