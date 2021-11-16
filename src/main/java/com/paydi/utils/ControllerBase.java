package com.paydi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ControllerBase {

	
	@Autowired
    private ApplicationEventPublisher eventPublisher;
	
//	private ListenerType listenerType;
	
	public ControllerBase() {
	}
	
//	public ControllerBase(ListenerType listenerType){
//		this.listenerType = listenerType;
//	}

	protected <T> ResponseEntity<T> makeResponse(T message) {
		return makeResponse(message, null, HttpStatus.OK);
	}

	protected <T> ResponseEntity<T> makeResponse(T message, HttpStatus status) {
		return makeResponse(message, null, status);
	}
	
	protected <T> ResponseEntity<T> makeResponse(T message, MultiValueMap<String, String> headers, HttpStatus status) {
		return new ResponseEntity<T>(message, headers, status);
	}

}
