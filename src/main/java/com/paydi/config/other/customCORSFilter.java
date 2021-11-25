package com.paydi.config.other;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paydi.constant.CommonConstant;
import com.paydi.entity.MMSAppAccessEntity;
import com.paydi.repository.MMSUtilRepository;
import com.paydi.service.AuthApiService;
import com.paydi.utils.FileUtils;
import com.paydi.utils.UtilsFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.sentry.Sentry;

@Component
public class customCORSFilter extends OncePerRequestFilter {

	@Autowired
	private AuthApiService authApiService;
	@Autowired
	private UtilsFunction utilsFunction;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "api-key, tenant, Authorization, content-type, xsrf-token");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");

		
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			String apiKeyHeader = request.getHeader(CommonConstant.API_KEY_HEADER);
		String coreTenant = request.getHeader(CommonConstant.TENANT_HEADER);
		// check valid api key
		MMSAppAccessEntity appAccessEntity = authApiService.checkApiKey(apiKeyHeader);
		if (appAccessEntity == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		} else {
			utilsFunction.initTenantSetting(coreTenant, appAccessEntity);
		}
			Sentry.captureMessage("REQUEST_TRACKING");
			filterChain.doFilter(request, response);
		}
	}
}
