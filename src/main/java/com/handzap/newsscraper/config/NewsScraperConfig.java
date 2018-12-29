package com.handzap.newsscraper.config;

import java.util.concurrent.Executor;

import org.modelmapper.ModelMapper;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.handzap.newsscraper.exception.CustomAsyncExceptionHandler;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAsync
@EnableSwagger2
@EnableTransactionManagement
public class NewsScraperConfig implements AsyncConfigurer {

	/**
	 * News scraper service version
	 */
	private static final String SERVICE_VERSION = "1.0";
	/**
	 * Application Title
	 */
	private static final String TITLE = "News Scraper Service";

	private static final String DESCRIPTION = "Web service to scrap news articles and query scrap data using REST endpoints";

	/**
	 * Produces {@link ApiInfo}
	 * 
	 * @return {@link ApiInfo}
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(SERVICE_VERSION).build();
	}

	/**
	 * Produce Docket bean
	 * 
	 * @return Docket bean
	 */
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(TITLE).select()
				.apis(RequestHandlerSelectors.any()).build().enableUrlTemplating(true);
	}

	/**
	 * Produce modelmapper bean
	 * 
	 * @return Modelmapper instance
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();

	}

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CustomAsyncExceptionHandler();
	}

}