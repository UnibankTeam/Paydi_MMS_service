package com.paydi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paydi.entity.MMSBankEntity;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSTerminalEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MMSPosModel {

	private long id;
	private MMSMerchantMasterEntity merchant;
	private String tid;
	private String terminalName;
	private Long minRevDefault;
	private MMSBankEntity bankCode;
	private int status;
	private Long createdBy;
	private Long updatedBy;
	private Date updatedDate;
	private Date createdDate;
	private List<MMSPosRateConfigModel> rates;

	public MMSPosModel() {
		super();
	}

}