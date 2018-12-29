package com.handzap.newsscraper.util;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGeneratorUtil implements IdentifierGenerator {

	public static final String ID_GENERATOR = "idGenerator";

	@Override
	public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) {
		return UUID.randomUUID().toString();
	}
}