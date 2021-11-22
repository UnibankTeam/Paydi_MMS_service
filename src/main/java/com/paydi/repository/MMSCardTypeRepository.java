package com.paydi.repository;

import com.paydi.entity.MMSCardTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSCardTypeRepository extends JpaRepository<MMSCardTypeEntity, Long> {
    
    MMSCardTypeEntity findByCode(String code);
}
