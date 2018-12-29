package com.handzap.newsscraper.util;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Custom Id generator class which generates a UUID for id
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public class IdGeneratorUtil implements IdentifierGenerator {

	public static final String ID_GENERATOR = "idGenerator";

	@Override
	public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) {
		return UUID.randomUUID().toString();
	}
}