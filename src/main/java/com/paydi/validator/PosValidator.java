package com.paydi.validator;

import com.paydi.constant.CommonConstant;
import com.paydi.model.MethodResponseModel;
import com.paydi.model.requestBody.RequestPosModel;
import com.paydi.model.requestBody.RequestRateByCardTypeModel;
import com.paydi.model.requestBody.RequestTerminalModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class PosValidator {

    public MethodResponseModel checkValidTidInfo(RequestPosModel requestPosModel) {
        MethodResponseModel methodResponseModel = new MethodResponseModel();

        try {

            if (StringUtils.isBlank(requestPosModel.getTid()) || StringUtils.isBlank(requestPosModel.getTerminalName())
                    || StringUtils.isBlank(String.valueOf(requestPosModel.getStatus()))
                    || StringUtils.isBlank(String.valueOf(requestPosModel.getMerchantId()))

            ) {

                methodResponseModel.setErrorCode(CommonConstant.API_CODE_MISSING_POS_PARAMS);
                methodResponseModel.setIsSuccess(false);
                methodResponseModel.setErrorMessage("invalid.pos.param");
                return methodResponseModel;
            } else {
                for (RequestRateByCardTypeModel rateByCardTypeModel : requestPosModel.getRates()) {
                    if (StringUtils.isBlank(rateByCardTypeModel.getCardType())
                            || StringUtils.isBlank(String.valueOf(rateByCardTypeModel.getCostRate()))
                            || StringUtils.isBlank(String.valueOf(rateByCardTypeModel.getCostRate()))
                            || rateByCardTypeModel.getCogsRate() < 0 || rateByCardTypeModel.getCogsRate() > 100
                            || rateByCardTypeModel.getCostRate() < 0 || rateByCardTypeModel.getCostRate() > 100) {
                        methodResponseModel.setErrorCode(CommonConstant.API_CODE_MISSING_RATE_POS_PARAMS);
                        methodResponseModel.setIsSuccess(false);
                        methodResponseModel.setErrorMessage("invalid.rate.param");
                    }
                }
            }

            methodResponseModel.setIsSuccess(true);
            return methodResponseModel;
        } catch (Exception e) {
            e.printStackTrace();
            Sentry.captureException(e);

            methodResponseModel.setErrorCode(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR);
            methodResponseModel.setIsSuccess(false);
            methodResponseModel.setErrorMessage(CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR);
            return methodResponseModel;
        }
    }

    public MethodResponseModel checkValidTerminalInfo(RequestTerminalModel requestTerminalModel) {
        MethodResponseModel methodResponseModel = new MethodResponseModel();

        try {

            if (StringUtils.isBlank(requestTerminalModel.getSerialNumber())
                    || StringUtils.isBlank(String.valueOf(requestTerminalModel.getBankCode()))
                    || StringUtils.isBlank(String.valueOf(requestTerminalModel.getModel()))

            ) {

                methodResponseModel.setErrorCode(CommonConstant.API_CODE_MISSING_POS_PARAMS);
                methodResponseModel.setIsSuccess(false);
                methodResponseModel.setErrorMessage("invalid.terminal.param");
                return methodResponseModel;
            }
            methodResponseModel.setIsSuccess(true);
            return methodResponseModel;
        } catch (Exception e) {
            e.printStackTrace();
            Sentry.captureException(e);

            methodResponseModel.setErrorCode(CommonConstant.API_CODE_INTERNAL_SERVER_ERROR);
            methodResponseModel.setIsSuccess(false);
            methodResponseModel.setErrorMessage(CommonConstant.API_MESSAGE_INTERNAL_SERVER_ERROR);
            return methodResponseModel;
        }
    }
}
