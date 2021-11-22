package com.paydi.repository;

import java.util.List;

import com.paydi.entity.MMSPosEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSPosRepository extends JpaRepository<MMSPosEntity, Long> {

    MMSPosEntity findByTid(String tId);

    List<MMSPosEntity> findByMerchantId(Long merchantId);
}
