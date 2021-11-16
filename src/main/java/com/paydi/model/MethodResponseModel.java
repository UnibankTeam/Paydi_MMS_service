package com.paydi.model;

public class MethodResponseModel {

	private boolean isSuccess;
	private Object result;
	private String errorMessage;
	private int errorCode;

	/**
	 * @return boolean return the isSuccess
	 */
	public boolean isIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return int return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return Object return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * @return String return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
