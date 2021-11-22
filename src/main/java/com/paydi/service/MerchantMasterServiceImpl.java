package com.paydi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.paydi.config.multitenancy.TenantStorage;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSMerchantCodeMasterEntity;
import com.paydi.repository.MMSMerchantCodeMasterRepository;
import com.paydi.repository.MMSMerchantMasterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class MerchantMasterServiceImpl {

	private MMSMerchantMasterRepository merchantMasterRepository;
	private MMSMerchantCodeMasterRepository merchantCodeMasterRepository;

	@Autowired
	public MerchantMasterServiceImpl(MMSMerchantMasterRepository merchantMasterRepository,
			MMSMerchantCodeMasterRepository merchantCodeMasterRepository) {
		this.merchantMasterRepository = merchantMasterRepository;
		this.merchantCodeMasterRepository = merchantCodeMasterRepository;

	}

	public MMSMerchantMasterEntity getMerchantById(Long merchantId) {
		try {
			Optional<MMSMerchantMasterEntity> merchantOptional = this.merchantMasterRepository.findById(merchantId);
			if (merchantOptional.isPresent()) {
				merchantOptional.get();
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<MMSMerchantMasterEntity> findBySamePartner(Long merchantId) {
		try {
			Optional<MMSMerchantMasterEntity> merchantOptional = this.merchantMasterRepository.findById(merchantId);
			if (merchantOptional.isPresent()) {

				return this.merchantMasterRepository.findByPartnerId(merchantOptional.get().getPartnerId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<MMSMerchantMasterEntity> findByPartnerId(Long partnerId) {
		try {
			return this.merchantMasterRepository.findByPartnerId(partnerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<MMSMerchantCodeMasterEntity> findAllMerchantCodeMaster() {
		try {
			return (List<MMSMerchantCodeMasterEntity>) this.merchantCodeMasterRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public MMSMerchantMasterEntity save(MMSMerchantMasterEntity merchantMasterEntity) {
		try {
			merchantMasterEntity.setCreatedBy(TenantStorage.getCurrentAppId());
			merchantMasterEntity.setCreatedDate(new Date());
			return merchantMasterRepository.save(merchantMasterEntity);
		} catch (Exception e) {
			Sentry.captureException(e);
			e.printStackTrace();
		}
		return null;
	}

	public MMSMerchantMasterEntity update(MMSMerchantMasterEntity merchantMasterEntity) {
		try {
			merchantMasterEntity.setUpdatedBy(TenantStorage.getCurrentAppId());
			merchantMasterEntity.setUpdatedDate(new Date());
			return merchantMasterRepository.save(merchantMasterEntity);
		} catch (Exception e) {
			Sentry.captureException(e);
			e.printStackTrace();
		}
		return null;
	}

	public MMSMerchantMasterEntity findByCode(String code) {
		try {
			return this.merchantMasterRepository.findByCode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MMSMerchantMasterEntity findById(Long merchantId) {
		try {
			Optional<MMSMerchantMasterEntity> optionalMerchant = this.merchantMasterRepository.findById(merchantId);
			if (optionalMerchant.isPresent()) {
				return optionalMerchant.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
