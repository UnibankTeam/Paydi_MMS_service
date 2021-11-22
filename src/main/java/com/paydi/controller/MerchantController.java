package com.paydi.controller;

import java.util.HashMap;
import java.util.List;

import com.paydi.constant.CommonConstant;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSMerchantCodeMasterEntity;
import com.paydi.model.ErrorsResponse;
import com.paydi.model.SuccessResponse;
import com.paydi.model.requestBody.RequestMerchantModel;
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
@RequestMapping(value = "/merchant")
public class MerchantController extends ControllerBase {

	private MerchantMasterServiceImpl merchantMasterServiceImpl;
	private PartnerServiceImpl partnerServiceImpl;

	@Autowired
	public MerchantController(MerchantMasterServiceImpl merchantMasterServiceImpl,
			PartnerServiceImpl partnerServiceImpl) {
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;
		this.partnerServiceImpl = partnerServiceImpl;

	}

	

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "template")
	public ResponseEntity getMerchantInsertTemplate() throws Exception {

		try {

			List<MMSMerchantCodeMasterEntity> listMerchantCode = this.merchantMasterServiceImpl
					.findAllMerchantCodeMaster();
			String requestId = "_get--template-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("listMerchantCode", listMerchantCode);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "edit/{merchantId}")
	public ResponseEntity getMerchantEditTemplate(@PathVariable(value = "merchantId") Long merchantId)
			throws Exception {

		try {

			MMSMerchantMasterEntity merchantMasterEntity = this.merchantMasterServiceImpl.findById(merchantId);
			if (merchantMasterEntity == null) {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_PARTNER_NOT_FOUND,
						CommonConstant.API_MESSAGE_FAIL, "error.msg.partner.not.found", null));
			}

			List<MMSMerchantCodeMasterEntity> listMerchantCode = this.merchantMasterServiceImpl
					.findAllMerchantCodeMaster();
			String requestId = "_get-template-edit-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("merchant", merchantMasterEntity);
			result.put("listMerchantCode", listMerchantCode);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "{merchantId}")
	public ResponseEntity getMerchant(@PathVariable(value = "merchantId") Long merchantId) throws Exception {

		try {

			MMSMerchantMasterEntity merchantMasterEntity = this.merchantMasterServiceImpl.findById(merchantId);

			String requestId = "_get-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("merchant", merchantMasterEntity);
			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "partnerId/{partnerId}")
	public ResponseEntity getMerchantByPartnerId(@PathVariable(value = "partnerId") Long partnerId) throws Exception {

		try {

			List<MMSMerchantMasterEntity> listMerchant = this.merchantMasterServiceImpl.findByPartnerId(partnerId);

			String requestId = "_get-merchant-by-partner";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("listMerchant", listMerchant);
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
	public ResponseEntity addMerchant(@RequestBody RequestMerchantModel requestMerchantModel) throws Exception {

		MMSMerchantMasterEntity merchantMasterEntity = null;
		try {

			merchantMasterEntity = this.merchantMasterServiceImpl.findByCode(requestMerchantModel.getCode());
			if (merchantMasterEntity == null) {

				// validate partner
				boolean isPartnerValid = this.partnerServiceImpl.validatePartner(requestMerchantModel.getPartnerId());
				if (!isPartnerValid) {
					return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_PARTNER_NOT_FOUND,
							CommonConstant.API_MESSAGE_FAIL, "error.msg.partner.not.found", null));
				}

				// add merchant
				merchantMasterEntity = new MMSMerchantMasterEntity();

				merchantMasterEntity.setPartnerId(requestMerchantModel.getPartnerId());
				merchantMasterEntity.setCode(requestMerchantModel.getCode());
				merchantMasterEntity.setDesc(requestMerchantModel.getDesc());
				merchantMasterEntity.setStatus(requestMerchantModel.getStatus());
				merchantMasterEntity.setMidMasterCode(requestMerchantModel.getMerchantMasterCode());

				this.merchantMasterServiceImpl.save(merchantMasterEntity);

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_DUPLICATE,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_DUPLICATE, null));
			}

			String requestId = "_add-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("merchant", merchantMasterEntity);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "update")
	public ResponseEntity updateMerchant(@RequestBody RequestMerchantModel requestMerchantModel) throws Exception {

		MMSMerchantMasterEntity merchantMasterEntity = null;
		try {

			merchantMasterEntity = this.merchantMasterServiceImpl.findByCode(requestMerchantModel.getCode());
			if (merchantMasterEntity != null) {

				// validate partner
				boolean isPartnerValid = this.partnerServiceImpl.validatePartner(requestMerchantModel.getPartnerId());
				if (!isPartnerValid) {
					return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_PARTNER_NOT_FOUND,
							CommonConstant.API_MESSAGE_FAIL, "error.msg.partner.not.found", null));
				}

				merchantMasterEntity.setPartnerId(requestMerchantModel.getPartnerId());
				merchantMasterEntity.setDesc(requestMerchantModel.getDesc());
				merchantMasterEntity.setStatus(requestMerchantModel.getStatus());
				merchantMasterEntity.setMidMasterCode(requestMerchantModel.getMerchantMasterCode());

				this.merchantMasterServiceImpl.update(merchantMasterEntity);

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_NOT_FOUND,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_NOT_FOUND, null));
			}
			String requestId = "_update-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("merchant", merchantMasterEntity);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

}
