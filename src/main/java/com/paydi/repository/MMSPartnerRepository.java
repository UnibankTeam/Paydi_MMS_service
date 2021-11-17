package com.paydi.repository;

import java.util.List;

import com.paydi.entity.MMSPartnerEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSPartnerRepository extends CrudRepository<MMSPartnerEntity, Long> {

    MMSPartnerEntity findByCode(String code);

    List<MMSPartnerEntity> findAllByStatus(String status);

    MMSPartnerEntity findByOdooContactId(String odooContactId);
}
