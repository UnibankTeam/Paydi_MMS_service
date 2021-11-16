package com.paydi.config.other;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paydi.constant.CommonConstant;
import com.paydi.utils.FileUtils;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.sentry.Sentry;

@Component
public class customCORSFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"api-key, core-tenant, uid, Authorization, content-type, xsrf-token");
		response.addHeader("Access-Control-Expose-Headers", "xsrf-token");
		String apiKeyHeader = request.getHeader(CommonConstant.API_KEY_HEADER);
		String apiKey;
		try {
			apiKey = FileUtils.getProperty("paydi.server.api.key");
		} catch (Exception e) {
			apiKey = String.valueOf(Math.random());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

		}

		if (!(apiKey.equalsIgnoreCase(apiKeyHeader))) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

		}

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			Sentry.captureMessage("REQUEST_TRACKING");
			filterChain.doFilter(request, response);
		}
	}
}
