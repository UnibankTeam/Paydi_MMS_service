package com.paydi.service;

import java.util.ArrayList;
import java.util.List;

import com.paydi.entity.MMSPosRateConfigEntity;
import com.paydi.repository.MMSPosRateConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class PosRateConfigServiceImpl {

	private MMSPosRateConfigRepository mmsPosLimitRepository;

	@Autowired
	public PosRateConfigServiceImpl(MMSPosRateConfigRepository mmsPosLimitRepository) {
		this.mmsPosLimitRepository = mmsPosLimitRepository;
	}

	public MMSPosRateConfigEntity findRateConfigByTerminalIdAndCardTypeAndMerchantId(Long tid, String cardType,
			Long merchantId) {

		try {
			return mmsPosLimitRepository.findByTidAndCardTypeAndMerchantId(tid, cardType, merchantId);
		} catch (Exception e) {
			Sentry.captureException(e);
			return null;
		}
	}

	public List<MMSPosRateConfigEntity> findRateConfigByTerminalId(String tid) {

		try {
			return mmsPosLimitRepository.findAllByTid(tid);
		} catch (Exception e) {
			Sentry.captureException(e);
			return new ArrayList<>();
		}
	}

	public List<MMSPosRateConfigEntity> findRateConfigByTerminalIdAndMerchantId(String tid, Long merchantId) {

		try {
			return mmsPosLimitRepository.findAllByTidAndMerchantId(tid, merchantId);
		} catch (Exception e) {
			Sentry.captureException(e);
			return new ArrayList<>();
		}
	}

	public MMSPosRateConfigEntity save(MMSPosRateConfigEntity posRateConfigEntity) {
		try {
			return mmsPosLimitRepository.save(posRateConfigEntity);
		} catch (Exception e) {
			Sentry.captureException(e);
			return null;
		}

	}

}
