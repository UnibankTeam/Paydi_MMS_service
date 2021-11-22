package com.paydi.convert;

import java.util.ArrayList;
import java.util.List;

import com.paydi.entity.MMSCardTypeEntity;
import com.paydi.entity.MMSPosRateConfigEntity;
import com.paydi.model.MMSPosRateConfigModel;
import com.paydi.repository.MMSBankRepository;
import com.paydi.repository.MMSCardTypeRepository;
import com.paydi.repository.MMSPosRepository;
import com.paydi.service.MerchantMasterServiceImpl;
import com.paydi.service.PosRateConfigServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class PosRateConverter {

	private MerchantMasterServiceImpl merchantMasterServiceImpl;
	private PosRateConfigServiceImpl posRateConfigServiceImpl;
	private MMSPosRepository posRepository;
	private MMSBankRepository MMSBankRepository;
	private MMSCardTypeRepository MMSCartTypeRepository;

	@Autowired
	public PosRateConverter(MerchantMasterServiceImpl merchantMasterServiceImpl,
			PosRateConfigServiceImpl posRateConfigServiceImpl, MMSPosRepository posRepository,
			com.paydi.repository.MMSBankRepository mMSBankRepository, MMSCardTypeRepository mMSCartTypeRepository) {
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;
		this.posRateConfigServiceImpl = posRateConfigServiceImpl;
		this.posRepository = posRepository;
		this.MMSBankRepository = mMSBankRepository;
		this.MMSCartTypeRepository = mMSCartTypeRepository;

	}

	public MMSPosRateConfigModel convertPosRateEntityToModel(MMSPosRateConfigEntity posEntity) {
		try {
			MMSPosRateConfigModel posRateModel = new MMSPosRateConfigModel();

			// convert
			posRateModel.setCreatedBy(posEntity.getCreatedBy());
			posRateModel.setCreatedDate(posEntity.getCreatedDate());
			posRateModel.setId(posEntity.getId());
			posRateModel.setStatus(posEntity.getStatus());
			posRateModel.setUpdatedBy(posEntity.getUpdatedBy());
			posRateModel.setCogsRate(posEntity.getCogsRate());
			posRateModel.setCostRate(posEntity.getCostRate());
			posRateModel.setUpdatedDate(posEntity.getUpdatedDate());

			MMSCardTypeEntity cardType = this.MMSCartTypeRepository.findByCode(posEntity.getCardType());
			posRateModel.setCardType(cardType);

			return posRateModel;

		} catch (Exception e) {
			Sentry.captureException(e);
			return null;
		}
	}

	public List<MMSPosRateConfigModel> convertPosRateEntityToModel(List<MMSPosRateConfigEntity> configEntities) {
		try {
			List<MMSPosRateConfigModel> posRateModels = new ArrayList<>();
			// convert
			for (MMSPosRateConfigEntity MMSPosRateConfigEntity : configEntities) {
				MMSPosRateConfigModel posRateModel = convertPosRateEntityToModel(MMSPosRateConfigEntity);

				posRateModels.add(posRateModel);

			}
			return posRateModels;

		} catch (Exception e) {
			Sentry.captureException(e);
			return new ArrayList<>();
		}
	}

}
