package com.paydi.utils;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paydi.config.multitenancy.TenantStorage;
import com.paydi.constant.CommonConstant;
import com.paydi.entity.MMSMsgLogEntity;
import com.paydi.entity.MMSServiceLogEntity;
import com.paydi.repository.MMSServiceLogRepository;
import com.paydi.repository.MMSUltilRepository;
import com.paydi.repository.MQMsgLogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import io.sentry.Sentry;

@Service
public class UtilsFunction {

	private static final Logger logger = LoggerFactory.getLogger(UtilsFunction.class);
	private MMSServiceLogRepository serviceLogRepository;
	private MMSUltilRepository ultilRepository;
	private MQMsgLogRepository mqMsgLogRepository;
	private RestTemplate restTemplate;

	@Autowired
	public UtilsFunction(MMSServiceLogRepository serviceLogRepository, MMSUltilRepository ultilRepository,
			MQMsgLogRepository mqMsgLogRepository, RestTemplate restTemplate) {
		this.serviceLogRepository = serviceLogRepository;
		this.ultilRepository = ultilRepository;
		this.mqMsgLogRepository = mqMsgLogRepository;
		this.restTemplate = restTemplate;

	}

	public void sendSuccessCallback(MMSMsgLogEntity entity, String hostUrl, String phoneNo, String uid,
			String clientRequestId, Object object) {

		try {
			HashMap<String, Object> payloadObj = new HashMap<String, Object>();
			payloadObj.put("phoneNo", phoneNo);
			payloadObj.put("uid", uid);
			payloadObj.put("clientRequestId", clientRequestId);
			payloadObj.put("data", object);
			payloadObj.put("statusCode", 200);
			payloadObj.put("errorMessage", null);

			callback3rd(hostUrl, HttpMethod.POST, payloadObj, entity, clientRequestId);
		} catch (Exception e) {

			e.printStackTrace();
			Sentry.captureException(e);
		}
	}

	public void sendErrorCallback(MMSMsgLogEntity entity, String hostUrl, String phoneNo, String uid,
			String clientRequestId, int statusCode, String error, Object data) {

		try {
			HashMap<String, Object> payloadObj = new HashMap<String, Object>();
			payloadObj.put("phoneNo", phoneNo);
			payloadObj.put("uid", uid);
			payloadObj.put("clientRequestId", clientRequestId);
			payloadObj.put("data", data);
			payloadObj.put("statusCode", statusCode);
			payloadObj.put("errorMessage", error);

			callback3rd(hostUrl, HttpMethod.POST, payloadObj, entity, clientRequestId);
		} catch (Exception e) {

			e.printStackTrace();
			Sentry.captureException(e);
		}
	}

	private String callback3rd(String endPoint, HttpMethod method, HashMap<String, Object> payload,
			MMSMsgLogEntity entity, String clientRequestId) throws Exception {

		HttpHeaders header = new HttpHeaders();
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();

		header.add("Content-Type", "application/json;charset=UTF-8");
		header.add("ApiKey", FileUtils.getPropertyUrlCore("api.3rd.net.key"));
		String hostUrl = FileUtils.getPropertyUrlCore("api.3rd.net.url.callBack");

		String bodyreq = objectMapper.writeValueAsString(payload);
		logger.info(" bodyreq :" + bodyreq);
		HttpEntity<String> requestEntity = new HttpEntity<String>(bodyreq, header);
		restTemplate = new RestTemplate();
		ResponseEntity<String> resp = null;
		try {
			resp = restTemplate.exchange(hostUrl, method, requestEntity, String.class);
			logger.info("=======================================Lưu log==========================================");
			logger.info(resp.getBody().toString());
			MMSServiceLogEntity serviceLogEntity = new MMSServiceLogEntity();
			serviceLogEntity.setChannel("3RD");
			serviceLogEntity.setCreatedDate(new Date());
			serviceLogEntity.setRequestUrl(hostUrl + endPoint);
			serviceLogEntity.setRequestContent(bodyreq);
			serviceLogEntity.setRequestType("api.3rd.url.callBack");
			serviceLogEntity.setResponseContent(resp.getBody().toString());
			serviceLogEntity.setCreatedBy(clientRequestId);

			serviceLogRepository.save(serviceLogEntity);
			logger.info("=======================================Done log==========================================");

			// store stage after call callback api
			if (entity != null) {
				entity.setExecute(2);
				mqMsgLogRepository.save(entity);
			}

			return resp.getBody();
		} catch (HttpStatusCodeException e) {

			Sentry.captureException(e);
			logger.info(
					"=======================================Lưu log error==========================================");
			MMSServiceLogEntity iFTBServiceLogEntity = new MMSServiceLogEntity();
			iFTBServiceLogEntity.setChannel("3RD");
			iFTBServiceLogEntity.setCreatedDate(new Date());
			iFTBServiceLogEntity.setRequestContent(bodyreq);
			iFTBServiceLogEntity.setRequestUrl(hostUrl + endPoint);
			iFTBServiceLogEntity.setRequestType("api.3rd.url.callBack");
			iFTBServiceLogEntity.setResponseContent(e.getResponseBodyAsString());
			iFTBServiceLogEntity.setCreatedBy(clientRequestId);
			serviceLogRepository.save(iFTBServiceLogEntity);
			logger.info("=======================================Done log==========================================");
		}

		return null;
	}

	public void initTenantSetting(String coreTenant) {

		String tenantDb = null;
		String tenantCoreServer = null;

		try {
			TenantStorage.setCurrentTenant(coreTenant);

			tenantDb = FileUtils.getPropertyTenant(CommonConstant.TENANT_DB_KEY);
			tenantCoreServer = FileUtils.getPropertyTenant(CommonConstant.TENANT_URL_KEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TenantStorage.setCurrentTenantDB(tenantDb);
		TenantStorage.setCurrentTenantUrlServer(tenantCoreServer);

	}

}
