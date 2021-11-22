package com.paydi.model.requestBody;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestRateByCardTypeModel implements Serializable {

	private String cardType;
	private double costRate;
	private double cogsRate;
	public RequestRateByCardTypeModel() {

	}

}
