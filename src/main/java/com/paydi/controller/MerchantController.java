package com.paydi.controller;

import java.util.HashMap;
import java.util.List;

import com.paydi.constant.CommonConstant;
import com.paydi.convert.AccountInfoConverter;
import com.paydi.model.ErrorsResponse;
import com.paydi.model.SucessResponse;
import com.paydi.repository.MMSUltilRepository;
import com.paydi.utils.ControllerBase;
import com.paydi.utils.UtilsFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sentry.Sentry;

@RestController
@CrossOrigin
@RequestMapping(value = "/merchant")
public class MerchantController extends ControllerBase {

	@Autowired
	private MMSUltilRepository ultilRepository;
	@Autowired
	private UtilsFunction utilsFunction;
	@Autowired
	private AccountInfoConverter accountInfoConverter;

	@SuppressWarnings("rawtypes")
	@GetMapping(value = "id/{accountId}")
	public ResponseEntity getAccount(@PathVariable(value = "accountId") Long accountId) {
		SucessResponse jwtResponse = null;

		try {

			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("account", null);

			jwtResponse = new SucessResponse(200, CommonConstant.API_MESSAGE_SUCCESS, result);

			return makeResponse(jwtResponse);
		} catch (Exception e) {
			Sentry.captureException(e);
			return makeResponse(new ErrorsResponse(CommonConstant.API_MESSAGE_FAIL, 500, "internal server error!", null));
		}
	}

}
