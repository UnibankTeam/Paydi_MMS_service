package com.paydi.model.requestBody;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestPosModel implements Serializable {

	private Long id;
	private String tid;
	private String terminalName;
	private int status;
	private Long minRevDefault;
	private Long merchantId;
	private String createdBy;
	private ArrayList<RequestRateByCardTypeModel> rates;

	public RequestPosModel() {
	}

}
