package com.paydi.repository;

import com.paydi.entity.MMSBankEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSBankRepository extends JpaRepository<MMSBankEntity, Long> {
    
    MMSBankEntity findByCode(String code);
}
