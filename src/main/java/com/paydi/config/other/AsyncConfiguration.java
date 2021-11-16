package com.paydi.config.other;

import java.time.Duration;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAsync
public class AsyncConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);

	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		LOGGER.debug("Creating Async Task Executor");
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(8);
		executor.setMaxPoolSize(40);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("MyCurentThread-");
		executor.initialize();
		return executor;
	}

	static final int TIMEOUT = 600;

	@Bean
	RestTemplate restTemplateWithConnectReadTimeout() {
		return new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(TIMEOUT * 1000))
				.setReadTimeout(Duration.ofMillis(TIMEOUT * 1000))
				.setBufferRequestBody(true)
				.build();
	}

}
