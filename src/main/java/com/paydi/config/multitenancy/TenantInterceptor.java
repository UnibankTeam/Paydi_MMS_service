package com.paydi.config.multitenancy;

import com.paydi.constant.CommonConstant;
import com.paydi.utils.FileUtils;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Component
public class TenantInterceptor implements WebRequestInterceptor {


    @Override
    public void preHandle(WebRequest request) {
        TenantStorage.setCurrentTenant(request.getHeader(CommonConstant.TENANT_HEADER));
        TenantStorage.setCurrentUID(request.getHeader(CommonConstant.UID_HEADER));
        TenantStorage.setCurrentApiKey(request.getHeader(CommonConstant.API_KEY_HEADER));
        
        try {
			String tenantDb = FileUtils.getPropertyTenant(CommonConstant.TENANT_DB_KEY);
			String tenantCoreServer = FileUtils.getPropertyTenant(CommonConstant.TENANT_URL_KEY);
	        TenantStorage.setCurrentTenantDB(tenantDb);
	        TenantStorage.setCurrentTenantUrlServer(tenantCoreServer);


		} catch (Exception e) {
			// TODO Auto-generated catch block
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