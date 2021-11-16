package com.paydi.repository;

import java.util.List;

import com.paydi.entity.MMSParamsEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MMSParamsRepository extends  CrudRepository<MMSParamsEntity, Long>  { 
	MMSParamsEntity findByParamCode(String code);
	
	@Query(value = "SELECT * FROM iftb_params pr WHERE pr.param_code = :code ; ", nativeQuery = true)
	List<MMSParamsEntity> findByParamCodes(String code);
}
