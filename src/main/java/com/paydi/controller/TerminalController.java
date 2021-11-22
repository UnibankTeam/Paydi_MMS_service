package com.paydi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.paydi.constant.CommonConstant;
import com.paydi.convert.TerminalConverter;
import com.paydi.convert.TidConverter;
import com.paydi.entity.MMSMerchantMasterEntity;
import com.paydi.entity.MMSPartnerEntity;
import com.paydi.entity.MMSTerminalEntity;
import com.paydi.model.ErrorsResponse;
import com.paydi.model.MMSTerminalModel;
import com.paydi.model.MethodResponseModel;
import com.paydi.model.SuccessResponse;
import com.paydi.model.requestBody.RequestTerminalModel;
import com.paydi.repository.MMSTerminalRepository;
import com.paydi.service.MerchantMasterServiceImpl;
import com.paydi.service.PartnerServiceImpl;
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
@RequestMapping(value = "/terminal")
public class TerminalController extends ControllerBase {

	private PartnerServiceImpl partnerServiceImpl;
	private MMSTerminalRepository terminalRepository;
	private TerminalConverter terminalConverter;
	private PosServiceImpl posServiceImpl;
	private MerchantMasterServiceImpl merchantMasterServiceImpl;

	@Autowired
	public TerminalController(PartnerServiceImpl partnerServiceImpl, MMSTerminalRepository terminalRepository,
			TerminalConverter terminalConverter, PosServiceImpl posServiceImpl,
			MerchantMasterServiceImpl merchantMasterServiceImpl) {
		this.partnerServiceImpl = partnerServiceImpl;
		this.terminalRepository = terminalRepository;
		this.terminalConverter = terminalConverter;
		this.posServiceImpl = posServiceImpl;
		this.merchantMasterServiceImpl = merchantMasterServiceImpl;

	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "template")
	public ResponseEntity getTemplate() throws Exception {

		try {

			List<MMSPartnerEntity> listPartner = this.partnerServiceImpl.findAllPartner();

			String requestId = "_get-template-new-terminal";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("listPartner", listPartner);

			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "template/partner/{odooContactId}")
	public ResponseEntity getTemplateByPartnerId(@PathVariable(value = "odooContactID") String odooContactID)
			throws Exception {

		try {

			MMSPartnerEntity partner = partnerServiceImpl.findByContactId(odooContactID);
			List<MMSMerchantMasterEntity> listMerchant = this.merchantMasterServiceImpl
					.findByPartnerId(partner.getId());

			String requestId = "_get-template-new-terminal-by-partner";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("partner", partner);
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
	@GetMapping(value = "template/mid/{merchantId}")
	public ResponseEntity getTemplateByMerchantId(@PathVariable(value = "merchantId") Long merchantId)
			throws Exception {

		try {

			MMSMerchantMasterEntity merchantMasterEntity = this.merchantMasterServiceImpl.findById(merchantId);
			MMSPartnerEntity partner = this.partnerServiceImpl.findByMerchantId(merchantId);

			String requestId = "_get-template-new-terminal-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("partner", partner);
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
	@GetMapping(value = "transfer-terminal-template/{serialNumber}")
	public ResponseEntity EditTransferPosTemplate(@PathVariable(value = "serialNumber") String serialNumber)
			throws Exception {

		try {
			MMSTerminalEntity terminalEntity = this.terminalRepository.findBySerialNumber(serialNumber);
			// convert pos info
			MMSTerminalModel terminal = terminalConverter.convertTerminalEntityToModel(terminalEntity);
			List<MMSPartnerEntity> listPartner = partnerServiceImpl.findAllPartner();

			String requestId = "_get-template-edit-terminal";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("terminal", terminal);
			result.put("listPartner", listPartner);

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
	public ResponseEntity getAllTerminal() throws Exception {

		try {

			List<MMSTerminalEntity> terminalEntities = this.terminalRepository.findAll();

			// convert pos info
			List<MMSTerminalModel> listTerminal = terminalConverter.convertTerminalEntityToModel(terminalEntities);

			String requestId = "_get-pos-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("listTerminal", listTerminal);
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
	public ResponseEntity save(@RequestBody RequestTerminalModel requestTerminalModel) throws Exception {

		try {
			MMSTerminalEntity terminalEntity = this.terminalRepository
					.findBySerialNumber(requestTerminalModel.getSerialNumber());
			if (terminalEntity == null) {
				MethodResponseModel responseCheck = this.posServiceImpl.validateTerminal(requestTerminalModel);
				if (!responseCheck.isIsSuccess()) {
					return makeResponse(new ErrorsResponse(responseCheck.getErrorCode(),
							CommonConstant.API_MESSAGE_FAIL, responseCheck.getErrorMessage(), null));
				}
				terminalEntity = this.posServiceImpl.addTerminal(requestTerminalModel);

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_DUPLICATE,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_DUPLICATE, null));
			}
			// convert pos info
			MMSTerminalModel terminal = terminalConverter.convertTerminalEntityToModel(terminalEntity);

			String requestId = "_get-pos-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("terminal", terminal);
			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			e.printStackTrace();
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "update")
	public ResponseEntity update(@RequestBody RequestTerminalModel requestTerminalModel) throws Exception {
		MMSTerminalEntity terminalEntity = null;
		try {
			Optional<MMSTerminalEntity> terminalOptional = this.terminalRepository
					.findById(requestTerminalModel.getId());
			if (terminalOptional.isPresent()) {
				MMSTerminalEntity terminalSameSerialNumber = this.terminalRepository
						.findBySerialNumber(requestTerminalModel.getSerialNumber());
				if (terminalSameSerialNumber != null) {

					return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_DUPLICATE,
							CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_DUPLICATE, null));
				}
				MethodResponseModel responseCheck = this.posServiceImpl.validateTerminal(requestTerminalModel);
				if (!responseCheck.isIsSuccess()) {
					return makeResponse(new ErrorsResponse(responseCheck.getErrorCode(),
							CommonConstant.API_MESSAGE_FAIL, responseCheck.getErrorMessage(), null));
				}
				terminalEntity = this.posServiceImpl.editTerminal(requestTerminalModel, terminalOptional.get());

			} else {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_NOT_FOUND,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_ERROR_NOT_FOUND, null));
			}

			// update fail
			if (terminalEntity == null) {
				return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
						CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
			}
			// convert pos info
			MMSTerminalModel terminal = terminalConverter.convertTerminalEntityToModel(terminalEntity);

			String requestId = "_get-pos-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("terminal", terminal);
			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "terminal/{serialNumber}")
	public ResponseEntity getTerminalBySerialNumber(@PathVariable(value = "serialNumber") String serialNumber)
			throws Exception {

		try {

			MMSTerminalEntity terminalEntity = this.terminalRepository.findBySerialNumber(serialNumber);

			// convert pos info
			MMSTerminalModel terminal = terminalConverter.convertTerminalEntityToModel(terminalEntity);

			String requestId = "_get-pos-by-merchant";
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("terminal", terminal);
			return makeResponse(new SuccessResponse(requestId, CommonConstant.API_CODE_SUCCESS,
					CommonConstant.API_MESSAGE_SUCCESS, result));

		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
					CommonConstant.API_MESSAGE_FAIL, CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
		}
	}

	// @SuppressWarnings("rawtypes")
	// @GetMapping(value = "merchant/{merchantId}")
	// public ResponseEntity getMerchant(@PathVariable(value = "merchantId") Long
	// merchantId) throws Exception {

	// try {

	// List<MMSPosEntity> posEntities =
	// this.posServiceImpl.findByMerchantId(merchantId);

	// // convert pos info
	// List<MMSPosModel> posModels =
	// posConverter.convertPosEntityToModel(posEntities);

	// String requestId = "_get-pos-by-merchant";
	// HashMap<String, Object> result = new HashMap<String, Object>();
	// result.put("listPos", posModels);
	// return makeResponse(new SuccessResponse(requestId,
	// CommonConstant.API_CODE_SUCCESS,
	// CommonConstant.API_MESSAGE_SUCCESS, result));

	// } catch (Exception e) {
	// Sentry.captureException(e);
	// return makeResponse(new
	// ErrorsResponse(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR,
	// CommonConstant.API_MESSAGE_FAIL,
	// CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR, null));
	// }
	// }

}
