package com.paydi.convert;

import java.util.ArrayList;
import java.util.List;

import com.paydi.entity.MMSBankEntity;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSPosEntity;
import com.paydi.entity.MMSTerminalEntity;
import com.paydi.model.MMSPosModel;
import com.paydi.model.MMSTerminalModel;
import com.paydi.repository.MMSBankRepository;
import com.paydi.service.MerchantMasterServiceImpl;
import com.paydi.service.PosServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class TerminalConverter {

	private PosServiceImpl posServiceImpl;
	private TidConverter posConverter;
	private MMSBankRepository bankRepository;
	private MerchantMasterServiceImpl merchantMasterServiceImpl;

	@Autowired
	public TerminalConverter(PosServiceImpl posServiceImpl, TidConverter posConverter, MMSBankRepository bankRepository,
			MerchantMasterServiceImpl merchantMasterServiceImpl) {
		this.posServiceImpl = posServiceImpl;
		this.posConverter = posConverter;
		this.bankRepository = bankRepository;
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;

	}

	public MMSTerminalModel convertTerminalEntityToModel(MMSTerminalEntity terminalEntity) {
		try {

			MMSTerminalModel terminalModel = new MMSTerminalModel();
			// convert
			terminalModel.setId(terminalEntity.getId());
			terminalModel.setCreatedBy(terminalEntity.getCreatedBy());
			terminalModel.setCreatedDate(terminalEntity.getCreatedDate());
			terminalModel.setModel(terminalEntity.getModel());
			terminalModel.setStatus(terminalEntity.getStatus());
			terminalModel.setSerialNumber(terminalEntity.getSerialNumber());
			terminalModel.setFactory(terminalEntity.getFactory());
			terminalModel.setAssetsCode(terminalEntity.getAssetsCode());
			terminalModel.setContractDate(terminalEntity.getContractDate());
			terminalModel.setContractNo(terminalEntity.getContractNo());
			terminalModel.setSetupAddress(terminalEntity.getSetupAddress());
			terminalModel.setUpdatedBy(terminalEntity.getUpdatedBy());
			terminalModel.setUpdatedDate(terminalEntity.getUpdatedDate());
			MMSBankEntity bankCode = this.bankRepository.findByCode(terminalEntity.getBankCode());
			terminalModel.setBankCode(bankCode);

			MMSMerchantMasterEntity merchant = this.merchantMasterServiceImpl
					.getMerchantById(terminalEntity.getMerchantId());
			terminalModel.setMerchant(merchant);

			MMSPosEntity posEntity = this.posServiceImpl.findActiveTidBySerialNumber(terminalEntity.getSerialNumber());
			if (posEntity != null) {
				// convert pos info
				MMSPosModel posModel = posConverter.convertPosEntityToModel(posEntity);
				terminalModel.setPosModel(posModel);
			}

			return terminalModel;

		} catch (Exception e) {
			Sentry.captureException(e);
			return null;
		}
	}

	public List<MMSTerminalModel> convertTerminalEntityToModel(List<MMSTerminalEntity> terminalEntities) {
		try {

			List<MMSTerminalModel> terminalModels = new ArrayList<>();
			for (MMSTerminalEntity terminalEntity : terminalEntities) {
				MMSTerminalModel terminalModel = convertTerminalEntityToModel(terminalEntity);

				terminalModels.add(terminalModel);
			}

			return terminalModels;

		} catch (Exception e) {
			Sentry.captureException(e);
			return new ArrayList<>();
		}
	}

}
