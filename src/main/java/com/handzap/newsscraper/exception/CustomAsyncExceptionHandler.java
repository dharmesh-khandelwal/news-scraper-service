package com.handzap.newsscraper.exception;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * Custom exception handler for AsyncTask
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

	@Override
	public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
		LOGGER.error("Exception message - {}", throwable.getMessage());
		LOGGER.error("Method name - {}", method.getName());
		for (final Object param : obj) {
			LOGGER.error("Param - {}", param);
		}
	}

}