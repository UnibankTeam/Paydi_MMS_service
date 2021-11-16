package com.paydi.repository;

import com.paydi.entity.MMSServiceLogEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSServiceLogRepository extends JpaRepository<MMSServiceLogEntity, Integer> {
	
	
}