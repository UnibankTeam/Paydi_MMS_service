package com.paydi.config.multitenancy;

import com.paydi.constant.CommonConstant;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) {
        try {
            TenantStorage.setCurrentTenant(request.getHeader(CommonConstant.TENANT_HEADER));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) {
        TenantStorage.clear();
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) {

    }

}