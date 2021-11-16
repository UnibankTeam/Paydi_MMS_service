package com.paydi.repository;

import com.paydi.entity.MMSCurrencyRateEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSCurrencyRateRepository extends CrudRepository<MMSCurrencyRateEntity, Long> {

}
