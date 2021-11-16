package com.paydi.model;

import java.util.HashMap;

public class ErrorsResponse {
	private String status;
	private int statusCode;
	private String error;
	private HashMap<String, Object> result;

	public ErrorsResponse() {
	};

	public ErrorsResponse(String status, int statusCode, String error, HashMap<String, Object> result) {
		this.status = status;
		this.statusCode = statusCode;
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
