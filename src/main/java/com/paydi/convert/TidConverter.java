package com.paydi.convert;

import java.util.ArrayList;
import java.util.List;

import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSPosEntity;
import com.paydi.entity.MMSPosRateConfigEntity;
import com.paydi.model.MMSPosModel;
import com.paydi.model.MMSPosRateConfigModel;
import com.paydi.repository.MMSBankRepository;
import com.paydi.service.MerchantMasterServiceImpl;
import com.paydi.service.PosRateConfigServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class TidConverter {

	private MerchantMasterServiceImpl merchantMasterServiceImpl;
	private PosRateConfigServiceImpl posRateConfigServiceImpl;
	private PosRateConverter posRateConverter;

	@Autowired
	public TidConverter(MerchantMasterServiceImpl merchantMasterServiceImpl,
			PosRateConfigServiceImpl posRateConfigServiceImpl, PosRateConverter posRateConverter) {
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;
		this.posRateConfigServiceImpl = posRateConfigServiceImpl;
		this.posRateConverter = posRateConverter;
	}

	public MMSPosModel convertPosEntityToModel(MMSPosEntity posEntity) {
		try {

			MMSPosModel posModel = new MMSPosModel();
			// convert
			posModel.setCreatedBy(posEntity.getCreatedBy());
			posModel.setCreatedDate(posEntity.getCreatedDate());
			posModel.setId(posEntity.getId());
			posModel.setMinRevDefault(posEntity.getMinRevDefault());
			posModel.setStatus(posEntity.getStatus());
			posModel.setTid(posEntity.getTid());
			posModel.setTerminalName(posEntity.getTerminalName());
			posModel.setUpdatedBy(posEntity.getUpdatedBy());
			posModel.setUpdatedDate(posEntity.getUpdatedDate());

			MMSMerchantMasterEntity merchant = this.merchantMasterServiceImpl
					.getMerchantById(posEntity.getMerchantId());
			posModel.setMerchant(merchant);

			List<MMSPosRateConfigEntity> listRateConfig = this.posRateConfigServiceImpl
					.findRateConfigByTerminalIdAndMerchantId(posEntity.getTid(), posEntity.getMerchantId());
			List<MMSPosRateConfigModel> listRateConfigModel = this.posRateConverter
					.convertPosRateEntityToModel(listRateConfig);

			posModel.setRates(listRateConfigModel);

			return posModel;

		} catch (Exception e) {
			Sentry.captureException(e);
			return null;
		}
	}

	public List<MMSPosModel> convertPosEntityToModel(List<MMSPosEntity> posEntities) {
		try {

			List<MMSPosModel> posModels = new ArrayList<>();
			for (MMSPosEntity posEntity : posEntities) {
				MMSPosModel posModel = convertPosEntityToModel(posEntity);

				posModels.add(posModel);
			}

			return posModels;

		} catch (Exception e) {
			Sentry.captureException(e);
			return new ArrayList<>();
		}
	}

}
