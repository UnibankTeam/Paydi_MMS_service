package com.paydi.model;

import java.util.Date;

import com.paydi.entity.MMSCardTypeEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MMSPosRateConfigModel {

	private long id;
	private double costRate;
	private double cogsRate;
	private MMSCardTypeEntity cardType;
	private int status;
	private Long createdBy;
	private Long updatedBy;
	private Date updatedDate;
	private Date createdDate;

	public MMSPosRateConfigModel() {
	}

}