package com.paydi.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.paydi.constant.CommonConstant;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSPartnerEntity;
import com.paydi.model.ErrorsResponse;
import com.paydi.model.SuccessResponse;
import com.paydi.model.requestBody.RequestPartnerModel;
import com.paydi.service.MerchantMasterServiceImpl;
import com.paydi.service.PartnerServiceImpl;
import com.paydi.utils.ControllerBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sentry.Sentry;

@RestController
@CrossOrigin
@RequestMapping("/partner")
public class PartnerController extends ControllerBase {

	private PartnerServiceImpl partnerServiceImpl;
	private MerchantMasterServiceImpl merchantMasterServiceImpl;

	@Autowired
	public PartnerController(PartnerServiceImpl partnerServiceImpl,
			MerchantMasterServiceImpl merchantMasterServiceImpl) {
		this.partnerServiceImpl = partnerServiceImpl;
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;

	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "contact/{odooContactID}")
	public ResponseEntity getPartnersByContactId(@PathVariable(value = "odooContactID") String odooContactID)
			throws Exception {

		SuccessResponse successResponse = null;
		try {

			MMSPartnerEntity partner = partnerServiceImpl.findByContactId(odooContactID);
			List<MMSMerchantMasterEntity> listMerchant = merchantMasterServiceImpl.findByPartnerId(partner.getId());

			String requestId = "_merchant-info-by-contact";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("partner", partner);
			result.put("listMerchant", listMerchant);

			successResponse = new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result);
			return makeResponse(successResponse);
		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "")
	public ResponseEntity getPartners(HttpServletRequest request) throws Exception {

		SuccessResponse successResponse = null;
		try {

			List<MMSPartnerEntity> listPartner = partnerServiceImpl.findAllPartner();

			String requestId = "_all";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("partners", listPartner);
			successResponse = new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result);
			return makeResponse(successResponse);
		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "update")
	public ResponseEntity updatePartner(RequestPartnerModel updatePartnerModel) throws Exception {

		try {

			MMSPartnerEntity partnerEntity = partnerServiceImpl.findByCode(updatePartnerModel.getCode());
			if (partnerEntity != null) {

				partnerEntity.setDesc(updatePartnerModel.getDesc());
				partnerEntity.setOdooContactId(updatePartnerModel.getOdooContactId());
				partnerEntity.setStatus(updatePartnerModel.getStatus());
				partnerServiceImpl.updatePartner(partnerEntity);
			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_NOT_FOUND,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_NOT_FOUND, null));
			}

			String requestId = "_update-partner";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("partner", partnerEntity);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "")
	public ResponseEntity addPartner(@RequestBody RequestPartnerModel addPartnerModel) throws Exception {

		MMSPartnerEntity partnerEntity = null;
		try {

			MMSPartnerEntity partnerEntityExisting = partnerServiceImpl.findByCode(addPartnerModel.getCode());

			if (partnerEntityExisting == null) {
				partnerEntity = new MMSPartnerEntity();
				partnerEntity.setCode(addPartnerModel.getCode());
				partnerEntity.setOdooContactId(addPartnerModel.getOdooContactId());
				partnerEntity.setDesc(addPartnerModel.getDesc());
				partnerEntity.setStatus(addPartnerModel.getStatus());

				partnerServiceImpl.savePartner(partnerEntity);

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_DUPLICATE,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_DUPLICATE, null));
			}

			String requestId = "_add_partner";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("partner", partnerEntity);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));
		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

}