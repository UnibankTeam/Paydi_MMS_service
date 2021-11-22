package com.paydi.repository;

import com.paydi.entity.MMSTerminalEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSTerminalRepository extends JpaRepository<MMSTerminalEntity, Long> {

    MMSTerminalEntity findBySerialNumber(String serialNumber);
    
}
