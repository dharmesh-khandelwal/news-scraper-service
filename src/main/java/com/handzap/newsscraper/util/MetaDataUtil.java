package com.handzap.newsscraper.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.handzap.newsscraper.constant.NewsScraperConstant;
import com.handzap.newsscraper.entity.BaseEntity;

/**
 * MetaDataUtils class provide method to create some meta data which is required
 * before an entity to be saved into database.
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
@Component
public class MetaDataUtil {

	public <T extends BaseEntity> T setMetaData(T entity) {
		String contextUser = NewsScraperConstant.DEFAULTADMIN;
		LocalDateTime createdDateTime = LocalDateTime.now(ZoneId.of(NewsScraperConstant.UTC));
		entity.setCreatedBy(contextUser);
		entity.setCreatedDateTime(createdDateTime);
		return entity;
	}

}
