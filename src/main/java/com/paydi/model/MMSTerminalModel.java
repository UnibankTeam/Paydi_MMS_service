package com.paydi.model;

import java.sql.Date;

import com.paydi.entity.MMSBankEntity;
import com.paydi.entity.MMSMerchantMasterEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MMSTerminalModel {

	private long id;
	private String serialNumber;
	private String model;
	private String factory;
	private String assetsCode;
	private String setupAddress;
	private String contractNo;
	private Date contractDate;
	private int status;
	private Long createdBy;
	private Long updatedBy;
	private Date updatedDate;
	private Date createdDate;
	private MMSPosModel posModel;
	private MMSBankEntity bankCode;
	private MMSMerchantMasterEntity merchant;

	public MMSTerminalModel() {
		super();
	}

}