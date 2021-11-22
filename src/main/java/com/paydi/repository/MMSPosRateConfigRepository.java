package com.paydi.repository;

import java.util.List;

import com.paydi.entity.MMSPosRateConfigEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSPosRateConfigRepository extends JpaRepository<MMSPosRateConfigEntity, Long> {

    List<MMSPosRateConfigEntity> findAllByTidAndMerchantId(String tid, Long merchantId);

    List<MMSPosRateConfigEntity> findAllByTid(String tid);

    List<MMSPosRateConfigEntity> findAllByTidAndCardType(String tid, String cardType);

    MMSPosRateConfigEntity findByTidAndCardTypeAndMerchantId(Long tid, String cardType, Long merchantId);

}
