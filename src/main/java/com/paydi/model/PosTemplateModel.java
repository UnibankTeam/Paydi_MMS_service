package com.paydi.model;

import java.util.List;

import com.paydi.entity.MMSBankEntity;
import com.paydi.entity.MMSCardTypeEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PosTemplateModel {

	private List<MMSCardTypeEntity> listCardType;
	private List<MMSBankEntity> listBank;
	private MMSPosModel posEntity;

	public PosTemplateModel() {
		super();
	}

}