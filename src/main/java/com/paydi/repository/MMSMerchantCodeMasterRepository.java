package com.paydi.repository;

import com.paydi.entity.MMSPartnerEntity;
import com.paydi.entity.MMSMerchantCodeMasterEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSMerchantCodeMasterRepository extends CrudRepository<MMSMerchantCodeMasterEntity, Long> {

    MMSPartnerEntity findByCode(String code);
}
