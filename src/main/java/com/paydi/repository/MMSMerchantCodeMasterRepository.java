package com.paydi.repository;

import com.paydi.entity.MMSPartnerEntity;
import com.paydi.entity.MerchantCodeMasterEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSMerchantCodeMasterRepository extends CrudRepository<MerchantCodeMasterEntity, Long> {

    MMSPartnerEntity findByCode(String code);
}
