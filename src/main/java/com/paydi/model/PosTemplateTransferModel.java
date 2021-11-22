package com.paydi.model;

import java.util.List;

import com.paydi.entity.MMSBankEntity;
import com.paydi.entity.MMSCardTypeEntity;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSPartnerEntity;
import com.paydi.entity.MMSPosEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PosTemplateTransferModel {

	private List<MMSPartnerEntity> listPartner;
	private List<MMSMerchantMasterEntity> listMerchant;
	private List<MMSCardTypeEntity> listCardType;
	private List<MMSBankEntity> listBank;
	private MMSPosModel posEntity;

	public PosTemplateTransferModel() {
		super();
	}

}