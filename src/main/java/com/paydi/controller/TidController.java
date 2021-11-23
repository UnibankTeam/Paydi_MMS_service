package com.paydi.controller;

import java.util.HashMap;
import java.util.List;

import com.paydi.constant.CommonConstant;
import com.paydi.convert.TidConverter;
import com.paydi.entity.MMSPosEntity;
import com.paydi.model.ErrorsResponse;
import com.paydi.model.MMSPosModel;
import com.paydi.model.MethodResponseModel;
import com.paydi.model.PosTemplateModel;
import com.paydi.model.SuccessResponse;
import com.paydi.model.requestBody.RequestPosModel;
import com.paydi.service.PosServiceImpl;
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
@RequestMapping(value = "/tid")
public class TidController extends ControllerBase {

	private TidConverter posConverter;
	private PosServiceImpl posServiceImpl;

	@Autowired
	public TidController(TidConverter posConverter, PosServiceImpl posServiceImpl) {

		this.posConverter = posConverter;
		this.posServiceImpl = posServiceImpl;
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "template")
	public ResponseEntity getMerchantInsertTemplate() throws Exception {

		try {
			PosTemplateModel posTemplateModel = this.posServiceImpl.getTemplatePos(null);

			String requestId = "_get-template-insert-pos";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("posTemplateModel", posTemplateModel);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "edit/{id}")
	public ResponseEntity getMerchantEditTemplate(@PathVariable(value = "id") Long id) throws Exception {

		try {
			PosTemplateModel posTemplateModel = this.posServiceImpl.getTemplatePos(id);

			String requestId = "_get-template-edit-pos";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("posTemplateModel", posTemplateModel);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "")
	public ResponseEntity getAllPos() throws Exception {

		try {

			List<MMSPosEntity> posEntities = this.posServiceImpl.findAllPos();

			// convert pos info
			List<MMSPosModel> posModels = posConverter.convertPosEntityToModel(posEntities);

			String requestId = "_get-pos-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("listPos", posModels);
			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "merchant/{merchantId}")
	public ResponseEntity getMerchant(@PathVariable(value = "merchantId") Long merchantId) throws Exception {

		try {

			List<MMSPosEntity> posEntities = this.posServiceImpl.findByMerchantId(merchantId);

			// convert pos info
			List<MMSPosModel> posModels = posConverter.convertPosEntityToModel(posEntities);

			String requestId = "_get-pos-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("listPos", posModels);
			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "{id}")
	public ResponseEntity getMerchantByPartnerId(@PathVariable(value = "id") Long id) throws Exception {

		try {

			MMSPosEntity posEntity = this.posServiceImpl.findById(id);
			if (posEntity == null) {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_NOT_FOUND,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_NOT_FOUND, null));
			}
			// convert pos info
			MMSPosModel posModel = posConverter.convertPosEntityToModel(posEntity);

			String requestId = "_get-pos-by-id";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("pos", posModel);

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
	public ResponseEntity addPos(@RequestBody RequestPosModel RequestPosModel) throws Exception {

		MMSPosEntity posEntity = null;
		try {

			posEntity = this.posServiceImpl.findByTid(RequestPosModel.getTid());
			if (posEntity == null) {
				MethodResponseModel responseCheck = this.posServiceImpl.validateTid(RequestPosModel);
				if (!responseCheck.isIsSuccess()) {
					return makeResponse(new ErrorsResponse(responseCheck.getErrorCode(),
							CommonConstant.API_MESSAGE_FAIL, responseCheck.getErrorMessage(), null));
				}
				// add pos for merchant
				posEntity = this.posServiceImpl.save(RequestPosModel);

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_DUPLICATE,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_DUPLICATE, null));
			}
			if (posEntity == null) {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
			}

			// convert pos info
			MMSPosModel posModel = posConverter.convertPosEntityToModel(posEntity);

			String requestId = "_add-pos";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("pos", posModel);

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
	public ResponseEntity updatePos(@RequestBody RequestPosModel RequestPosModel) throws Exception {

		MMSPosEntity posEntity = null;
		try {
			MMSPosEntity optional = this.posServiceImpl.findById(RequestPosModel.getId());

			if (optional != null) {
				MMSPosEntity tidSameEntity = this.posServiceImpl.findByTid(RequestPosModel.getTid());
				if (tidSameEntity != null) {

					return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_DUPLICATE,
							CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_DUPLICATE, null));
				}
				MethodResponseModel responseCheck = this.posServiceImpl.validateTid(RequestPosModel);
				if (!responseCheck.isIsSuccess()) {
					return makeResponse(new ErrorsResponse(responseCheck.getErrorCode(),
							CommonConstant.API_MESSAGE_FAIL, responseCheck.getErrorMessage(), null));
				}
				// add pos for merchant
				posEntity = this.posServiceImpl.update(RequestPosModel, optional);

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_NOT_FOUND,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_NOT_FOUND, null));
			}

			if (posEntity == null) {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
			}

			// convert pos info
			MMSPosModel posModel = posConverter.convertPosEntityToModel(posEntity);

			String requestId = "_update-pos";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("pos", posModel);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

}
