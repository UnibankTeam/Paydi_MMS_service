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
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT `p`.* ");
			sql.append(" FROM `mms_partner` `p` ");
			sql.append(" JOIN `mms_app_access` `a` ON `a`.external_id = `p`.owner_external_id ");
			sql.append(" JOIN `mms_app_access` `aAccess` ON `aAccess`.external_id = ? ");
			sql.append(" WHERE   `a`.hierarchy LIKE Concat(`aAccess`.hierarchy, '%')  ");

			String appExternalId = TenantStorage.getCurrentTenantExternalId();
			Object[] listParam = new Object[] { appExternalId };

			return jdbcTemplate.query(sql.toString(), listParam,
					(rs, rowNum) -> new MMSPartnerEntity(rs.getLong("id"), rs.getString("code"), rs.getString("desc"),
							rs.getString("owner_external_id"), rs.getString("odoo_contact_id")));

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

	public MMSPartnerEntity findByMerchantId(Long merchantId) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT `p`.*  ");
			sql.append(" FROM `mms_merchant` `m`  ");
			sql.append(" JOIN `mms_partner` `p` ON `p`.id = `m`.partner_id ");
			sql.append(" WHERE  `m`.id = ? LIMIT 1 ");

			Object[] listParam = new Object[] { merchantId };

			return jdbcTemplate.queryForObject(sql.toString(), listParam,
					(rs, rowNum) -> new MMSPartnerEntity(rs.getLong("id"), rs.getString("code"), rs.getString("dec"),
							rs.getString("odoo_contact_id"), rs.getString("owner_external_id")));
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
