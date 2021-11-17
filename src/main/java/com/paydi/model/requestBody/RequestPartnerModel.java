package com.paydi.model.requestBody;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestPartnerModel {
	
	private String code;
	private String desc;
	private String odooContactId;
	private int status;
	public RequestPartnerModel() {
	}

	
}
