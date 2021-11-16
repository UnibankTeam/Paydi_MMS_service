package com.paydi.repository;

import java.util.Optional;

import com.paydi.entity.MMSMsgLogEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MQMsgLogRepository extends JpaRepository<MMSMsgLogEntity, Long>{
    Optional<MMSMsgLogEntity> findById(long id);

    MMSMsgLogEntity findByUuid(String  uuid);
}
