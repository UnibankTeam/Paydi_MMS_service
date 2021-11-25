package com.paydi.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.paydi.config.multitenancy.TenantStorage;
import com.paydi.constant.CommonConstant;
import com.paydi.convert.TidConverter;
import com.paydi.entity.MMSBankEntity;
import com.paydi.entity.MMSCardTypeEntity;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSPartnerEntity;
import com.paydi.entity.MMSPosEntity;
import com.paydi.entity.MMSPosRateConfigEntity;
import com.paydi.entity.MMSTerminalEntity;
import com.paydi.model.MMSPosModel;
import com.paydi.model.MethodResponseModel;
import com.paydi.model.PosTemplateModel;
import com.paydi.model.PosTemplateTransferModel;
import com.paydi.model.requestBody.RequestPosModel;
import com.paydi.model.requestBody.RequestRateByCardTypeModel;
import com.paydi.model.requestBody.RequestTerminalModel;
import com.paydi.repository.MMSCardTypeRepository;
import com.paydi.repository.MMSBankRepository;
import com.paydi.repository.MMSPosRepository;
import com.paydi.repository.MMSTerminalRepository;
import com.paydi.validator.PosValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import io.sentry.Sentry;

@Service
public class PosServiceImpl {

	private MerchantMasterServiceImpl merchantMasterServiceImpl;
	private PosRateConfigServiceImpl posRateConfigServiceImpl;
	private MMSPosRepository posRepository;
	private MMSBankRepository MMSBankRepository;
	private MMSCardTypeRepository MMSCardTypeRepository;
	private PosValidator posValidator;
	private PartnerServiceImpl partnerServiceImpl;
	private TidConverter posConverter;
	private JdbcTemplate jdbcTemplate;
	private Date now;
	private MMSTerminalRepository terminalRepository;

	@Autowired
	public PosServiceImpl(MerchantMasterServiceImpl merchantMasterServiceImpl,
			PosRateConfigServiceImpl posRateConfigServiceImpl, MMSPosRepository posRepository,
			MMSBankRepository mMSBankRepository, MMSCardTypeRepository mMSCartTypeRepository, PosValidator posValidator,
			PartnerServiceImpl partnerServiceImpl, TidConverter posConverter, JdbcTemplate jdbcTemplate,
			MMSTerminalRepository terminalRepository) {
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;
		this.posRateConfigServiceImpl = posRateConfigServiceImpl;
		this.posRepository = posRepository;
		this.MMSBankRepository = mMSBankRepository;
		this.MMSCardTypeRepository = mMSCartTypeRepository;
		this.posValidator = posValidator;
		this.partnerServiceImpl = partnerServiceImpl;
		this.posConverter = posConverter;
		this.jdbcTemplate = jdbcTemplate;
		this.now = new Date(Calendar.getInstance().getTime().getTime());
		this.terminalRepository = terminalRepository;

	}

	public PosTemplateTransferModel getTemplateTransferPos(Long id) {
		PosTemplateTransferModel posTemplateModel = new PosTemplateTransferModel();
		MMSPosModel posModels = null;
		try {
			List<MMSCardTypeEntity> listCardType = this.MMSCardTypeRepository.findAll();
			List<MMSBankEntity> listBank = this.MMSBankRepository.findAll();
			if (id != null) {
				MMSPosEntity posEntity = findById(id);
				posModels = posConverter.convertPosEntityToModel(posEntity);

				List<MMSMerchantMasterEntity> listMerchant = this.merchantMasterServiceImpl
						.findBySamePartner(posEntity.getMerchantId());
				List<MMSPartnerEntity> listPartner = partnerServiceImpl.findAllPartner();

				posTemplateModel.setListMerchant(listMerchant);
				posTemplateModel.setListPartner(listPartner);
			}
			posTemplateModel.setListCardType(listCardType);
			posTemplateModel.setListBank(listBank);
			posTemplateModel.setPosEntity(posModels);

			return posTemplateModel;
		} catch (Exception e) {

			Sentry.captureException(e);
			return posTemplateModel;
		}
	}

	public PosTemplateModel getTemplatePos(Long id) {
		PosTemplateModel posTemplateModel = new PosTemplateModel();
		MMSPosModel posModels = null;
		try {
			List<MMSCardTypeEntity> listCardType = this.MMSCardTypeRepository.findAll();
			if (id != null) {
				MMSPosEntity posEntity = findById(id);
				posModels = posConverter.convertPosEntityToModel(posEntity);

			}
			posTemplateModel.setListCardType(listCardType);
			posTemplateModel.setPosEntity(posModels);
			return posTemplateModel;
		} catch (Exception e) {

			Sentry.captureException(e);
			return posTemplateModel;
		}
	}

	public MethodResponseModel validateTid(RequestPosModel requestPosModel) {
		MethodResponseModel methodResponseModel = new MethodResponseModel();
		methodResponseModel.setIsSuccess(true);
		try {

			methodResponseModel = posValidator.checkValidTidInfo(requestPosModel);
			if (!methodResponseModel.isIsSuccess()) {
				methodResponseModel.setIsSuccess(false);
				methodResponseModel.setErrorCode(methodResponseModel.getErrorCode());
				methodResponseModel.setErrorMessage(methodResponseModel.getErrorMessage());

				return methodResponseModel;
			}
			// validate merchant
			MMSMerchantMasterEntity MerchantValid = this.merchantMasterServiceImpl
					.getMerchantById(requestPosModel.getMerchantId());
			if (MerchantValid == null) {
				methodResponseModel.setIsSuccess(false);
				methodResponseModel.setErrorCode(CommonConstant.API_CODE_MERCHANT_NOT_FOUND);
				methodResponseModel.setErrorMessage("error.msg.merchant.not.found");

				return methodResponseModel;

			}

			// validate card type
			for (RequestRateByCardTypeModel rateByCardTypeModel : requestPosModel.getRates()) {
				MMSCardTypeEntity isCardTypeValid = this.MMSCardTypeRepository
						.findByCode(rateByCardTypeModel.getCardType());
				if (isCardTypeValid == null) {
					methodResponseModel.setIsSuccess(false);
					methodResponseModel.setErrorCode(CommonConstant.API_CODE_CARD_TYPE_NOT_FOUND);
					methodResponseModel.setErrorMessage("error.msg.card.type.not.found");

				}
			}

			return methodResponseModel;
		} catch (Exception e) {
			e.printStackTrace();
			methodResponseModel.setIsSuccess(false);
			methodResponseModel.setErrorCode(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR);
			methodResponseModel.setErrorMessage(CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR);
			Sentry.captureException(e);
		}
		return methodResponseModel;
	}

	public MethodResponseModel validateTerminal(RequestTerminalModel requestTerminalModel) {
		MethodResponseModel methodResponseModel = new MethodResponseModel();
		methodResponseModel.setIsSuccess(true);
		try {

			methodResponseModel = posValidator.checkValidTerminalInfo(requestTerminalModel);
			if (!methodResponseModel.isIsSuccess()) {

				return methodResponseModel;
			}

			return methodResponseModel;
		} catch (Exception e) {
			e.printStackTrace();
			methodResponseModel.setIsSuccess(false);
			methodResponseModel.setErrorCode(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR);
			methodResponseModel.setErrorMessage(CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR);
			Sentry.captureException(e);
		}
		return methodResponseModel;
	}

	@Transactional(propagation = Propagation.NESTED)
	public MMSTerminalEntity addTerminal(RequestTerminalModel requestTerminalModel) {

		try {

			MMSTerminalEntity terminalEntity = new MMSTerminalEntity();

			terminalEntity.setMerchantId(requestTerminalModel.getMerchantId());
			// terminalEntity.setBankCode(requestTerminalModel.getBankCode());
			terminalEntity.setModel(requestTerminalModel.getModel());
			terminalEntity.setSerialNumber(requestTerminalModel.getSerialNumber());
			terminalEntity.setFactory(requestTerminalModel.getFactory());
			terminalEntity.setContractDate(requestTerminalModel.getContractDate());
			terminalEntity.setContractNo(requestTerminalModel.getContractNo());
			terminalEntity.setSetupAddress(requestTerminalModel.getSetupAddress());
			terminalEntity.setAssetsCode(requestTerminalModel.getAssetsCode());
			terminalEntity.setCreatedBy(TenantStorage.getCurrentAppId());
			terminalEntity.setCreatedDate(this.now);

			terminalRepository.save(terminalEntity);

			return terminalEntity;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.captureException(e);
			return null;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	public MMSTerminalEntity editTerminal(RequestTerminalModel requestTerminalModel, MMSTerminalEntity terminalEntity) {

		try {

			terminalEntity.setMerchantId(requestTerminalModel.getMerchantId());
			// terminalEntity.setBankCode(requestTerminalModel.getBankCode());
			terminalEntity.setModel(requestTerminalModel.getModel());
			terminalEntity.setSerialNumber(requestTerminalModel.getSerialNumber());
			terminalEntity.setFactory(requestTerminalModel.getFactory());
			terminalEntity.setAssetsCode(requestTerminalModel.getAssetsCode());
			terminalEntity.setContractDate(requestTerminalModel.getContractDate());
			terminalEntity.setContractNo(requestTerminalModel.getContractNo());
			terminalEntity.setSetupAddress(requestTerminalModel.getSetupAddress());
			terminalEntity.setUpdatedBy(TenantStorage.getCurrentAppId());
			terminalEntity.setUpdatedDate(this.now);

			terminalRepository.save(terminalEntity);

			return terminalEntity;
		} catch (Exception e) {

			Sentry.captureException(e);
			return null;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	public MMSPosEntity save(RequestPosModel requestPosModel) {

		try {

			MMSPosEntity MMSPosEntity = new MMSPosEntity();

			MMSPosEntity.setTid(requestPosModel.getTid());
			MMSPosEntity.setMerchantId(requestPosModel.getMerchantId());
			MMSPosEntity.setMinRevDefault(requestPosModel.getMinRevDefault());
			MMSPosEntity.setTerminalName(requestPosModel.getTerminalName());
			MMSPosEntity.setStatus(requestPosModel.getStatus());
			MMSPosEntity.setCreatedBy(TenantStorage.getCurrentAppId());
			MMSPosEntity.setCreatedDate(this.now);
			posRepository.save(MMSPosEntity);

			for (RequestRateByCardTypeModel rateByCardTypeModel : requestPosModel.getRates()) {

				MMSPosRateConfigEntity configEntity = new MMSPosRateConfigEntity();
				configEntity.setCardType(rateByCardTypeModel.getCardType());
				configEntity.setCogsRate(rateByCardTypeModel.getCogsRate());
				configEntity.setCostRate(rateByCardTypeModel.getCostRate());
				configEntity.setMerchantId(requestPosModel.getMerchantId());
				configEntity.setCreatedBy(TenantStorage.getCurrentAppId());
				configEntity.setTid(MMSPosEntity.getId());
				configEntity.setStatus(requestPosModel.getStatus());
				configEntity.setCreatedDate(this.now);

				posRateConfigServiceImpl.save(configEntity);
			}

			return MMSPosEntity;
		} catch (Exception e) {

			Sentry.captureException(e);
			return null;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	public MMSPosEntity update(RequestPosModel requestPosModel, MMSPosEntity posEntity) {

		try {

			posEntity.setMerchantId(requestPosModel.getMerchantId());
			posEntity.setMinRevDefault(requestPosModel.getMinRevDefault());
			posEntity.setTerminalName(requestPosModel.getTerminalName());
			posEntity.setStatus(requestPosModel.getStatus());
			posEntity.setUpdatedBy(TenantStorage.getCurrentAppId());
			posEntity.setUpdatedDate(this.now);

			posRepository.save(posEntity);

			for (RequestRateByCardTypeModel rateByCardTypeModel : requestPosModel.getRates()) {

				MMSPosRateConfigEntity configEntity = posRateConfigServiceImpl
						.findRateConfigByTerminalIdAndCardTypeAndMerchantId(requestPosModel.getId(),
								rateByCardTypeModel.getCardType(), requestPosModel.getMerchantId());
				if (configEntity == null) {

					configEntity = new MMSPosRateConfigEntity();
					configEntity.setCardType(rateByCardTypeModel.getCardType());
					configEntity.setCogsRate(rateByCardTypeModel.getCogsRate());
					configEntity.setCostRate(rateByCardTypeModel.getCostRate());
					configEntity.setMerchantId(requestPosModel.getMerchantId());
					configEntity.setTid(requestPosModel.getId());
					configEntity.setStatus(requestPosModel.getStatus());
					configEntity.setCreatedDate(this.now);
					configEntity.setCreatedBy(TenantStorage.getCurrentAppId());

				} else {
					configEntity.setCogsRate(rateByCardTypeModel.getCogsRate());
					configEntity.setCostRate(rateByCardTypeModel.getCostRate());
					configEntity.setUpdatedDate(this.now);
					configEntity.setStatus(requestPosModel.getStatus());
					configEntity.setUpdatedBy(TenantStorage.getCurrentAppId());
				}

				posRateConfigServiceImpl.save(configEntity);
			}

			return posEntity;
		} catch (Exception e) {

			Sentry.captureException(e);
			return null;
		}
	}

	public MMSPosEntity findByTid(String tid) {

		try {

			return posRepository.findByTid(tid);
		} catch (Exception e) {

			Sentry.captureException(e);
			return null;
		}
	}

	public MMSPosEntity findById(Long id) {

		try {
			Optional<MMSPosEntity> optionalEntity = posRepository.findById(id);
			if (optionalEntity.isPresent()) {
				return optionalEntity.get();
			}
			return null;
		} catch (Exception e) {

			Sentry.captureException(e);
			return null;
		}
	}

	public List<MMSPosEntity> findByMerchantId(Long merchantId) {

		try {

			return posRepository.findByMerchantId(merchantId);
		} catch (Exception e) {

			Sentry.captureException(e);
			return new ArrayList<>();
		}
	}

	public List<MMSPosEntity> findAllPos() {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT `pos`.* ");
			sql.append(" FROM `mms_tid` `pos` ");
			sql.append(" JOIN `mms_terminal_tid_mapping` `tim` ON `tim`.tid = `pos`.id ");
			sql.append(" JOIN `mms_merchant` `m` ON `pos`.merchant_id = `m`.id ");
			sql.append(" JOIN `mms_partner` `p` ON `p`.id = `m`.partner_id ");
			sql.append(" JOIN `mms_app_access` `a` ON `a`.external_id = `p`.owner_external_id ");
			sql.append(" JOIN `mms_app_access` `aAccess` ON `aAccess`.external_id = ? ");
			sql.append(" WHERE   `a`.hierarchy LIKE Concat(`aAccess`.hierarchy, '%')  ");
			sql.append(" AND `tim`.`status` = 1 ");

			String appExternalId = TenantStorage.getCurrentTenantExternalId();
			Object[] listParam = new Object[] { appExternalId };

			return jdbcTemplate.query(sql.toString(), listParam,
					(rs, rowNum) -> new MMSPosEntity(rs.getLong("id"), rs.getLong("merchant_id"), rs.getString("tid"),
							rs.getString("terminal_name"), rs.getLong("min_rev_default"), rs.getInt("status"),
							rs.getLong("created_by"), rs.getLong("updated_by"), rs.getDate("updated_date"),
							rs.getDate("created_date")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<MMSPosEntity>();
	}

	public MMSPosEntity findActiveTidBySerialNumber(String serialNumber) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT `pos`.*  ");
			sql.append(" FROM `mms_terminal` `t`  ");
			sql.append(" JOIN `mms_terminal_tid_mapping` `tim` ON `tim`.serial_number = `t`.serial_number ");
			sql.append(" JOIN `mms_tid` `pos` ON `pos`.id = `tim`.tid ");
			sql.append(" JOIN `mms_merchant` `m` ON `pos`.merchant_id = `m`.id ");
			sql.append(" JOIN `mms_partner` `p` ON `p`.id = `m`.partner_id ");
			sql.append(" JOIN `mms_app_access` `a` ON `a`.external_id = `p`.owner_external_id ");
			sql.append(" JOIN `mms_app_access` `aAccess` ON `aAccess`.external_id = ? ");
			sql.append(" WHERE   `a`.hierarchy LIKE Concat(`aAccess`.hierarchy, '%')  ");
			sql.append(" AND `tim`.`status` = 1 AND `t`.serial_number = ? ");

			String appExternalId = TenantStorage.getCurrentTenantExternalId();
			Object[] listParam = new Object[] { appExternalId, serialNumber };

			return jdbcTemplate.queryForObject(sql.toString(), listParam,
					(rs, rowNum) -> new MMSPosEntity(rs.getLong("id"), rs.getLong("merchant_id"), rs.getString("tid"),
							rs.getString("terminal_name"), rs.getLong("min_rev_default"), rs.getInt("status"),
							rs.getLong("created_by"), rs.getLong("updated_by"), rs.getDate("updated_date"),
							rs.getDate("created_date")));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
