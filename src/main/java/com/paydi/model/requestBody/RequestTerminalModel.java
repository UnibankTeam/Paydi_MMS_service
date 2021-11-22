package com.paydi.model.requestBody;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestTerminalModel implements Serializable {

	private Long id;
	private String model;
	private Long merchantId;
	private String serialNumber;
	private String factory;
	private String assetsCode;
	private String bankCode;
	private String setupAddress;
	private String contractNo;
	private Date contractDate;
	private int status;

	public RequestTerminalModel() {
	}

}
