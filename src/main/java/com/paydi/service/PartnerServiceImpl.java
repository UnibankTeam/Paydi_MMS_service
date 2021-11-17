package com.paydi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.paydi.config.multitenancy.TenantStorage;
import com.paydi.entity.MMSPartnerEntity;
import com.paydi.repository.MMSPartnerRepository;

@Service
public class PartnerServiceImpl {

	private MMSPartnerRepository partnerRepository;
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PartnerServiceImpl(MMSPartnerRepository partnerRepository, JdbcTemplate jdbcTemplate) {
		this.partnerRepository = partnerRepository;
		this.jdbcTemplate = jdbcTemplate;
	}

	public MMSPartnerEntity savePartner(MMSPartnerEntity partnerEntity) {
		try {
			partnerEntity.setOwnerExternalId(TenantStorage.getCurrentTenantExternalId());
			partnerEntity.setCreatedBy(TenantStorage.getCurrentAppId());
			partnerEntity.setCreatedDate(new Date());
			return partnerRepository.save(partnerEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MMSPartnerEntity updatePartner(MMSPartnerEntity partnerEntity) {
		try {
			partnerEntity.setOwnerExternalId(TenantStorage.getCurrentTenantExternalId());
			partnerEntity.setUpdatedBy(TenantStorage.getCurrentAppId());
			partnerEntity.setUpdatedDate(new Date());
			return partnerRepository.save(partnerEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<MMSPartnerEntity> findAllPartner() {
		try {
			Iterable<MMSPartnerEntity> listPartner = partnerRepository.findAll();
			return (List<MMSPartnerEntity>) listPartner;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<MMSPartnerEntity>();
	}

	public MMSPartnerEntity findByCode(String partnerCode) {
		try {
			MMSPartnerEntity partner = partnerRepository.findByCode(partnerCode);
			return partner;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MMSPartnerEntity findByPartnerId(Long partnerId) {
		try {
			Optional<MMSPartnerEntity> partnerOptional = this.partnerRepository.findById(partnerId);
			if (partnerOptional.isPresent()) {
				return partnerOptional.get();
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MMSPartnerEntity findByContactId(String contactId) {
		try {
			MMSPartnerEntity partners = partnerRepository.findByOdooContactId(contactId);
			return partners;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validatePartner(Long partnerId) {
		try {
			Optional<MMSPartnerEntity> partnerOptional = this.partnerRepository.findById(partnerId);
			if (partnerOptional.isPresent()) {
				return true;
			}

			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<MMSPartnerEntity> findAllPartnerStatus(String status) {
		try {
			return partnerRepository.findAllByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
