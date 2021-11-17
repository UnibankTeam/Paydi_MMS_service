package com.paydi.repository;

import java.util.List;

import com.paydi.entity.MMSMerchantMasterEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSMerchantMasterRepository extends CrudRepository<MMSMerchantMasterEntity, Long> {

    List<MMSMerchantMasterEntity> findByPartnerId(Long partnerId);

    MMSMerchantMasterEntity findByCode(String code);

}
