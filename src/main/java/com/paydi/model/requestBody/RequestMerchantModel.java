package com.paydi.model.requestBody;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestMerchantModel {
	
	private String code;
	private String merchantMasterCode;
	private String desc;
	private Long partnerId;
	private int status;

	public RequestMerchantModel() {

	}

	
}
