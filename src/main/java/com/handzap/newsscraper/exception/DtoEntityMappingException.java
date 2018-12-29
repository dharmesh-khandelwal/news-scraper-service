package com.handzap.newsscraper.exception;

/**
 * Custom exception class for DtoEntityMappingException
 * 
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public class DtoEntityMappingException extends Exception {

	/**
	 * Generated serial id
	 */
	private static final long serialVersionUID = -7053001104604080438L;

	public DtoEntityMappingException() {
		super();
	}

	public DtoEntityMappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DtoEntityMappingException(String message) {
		super(message);
	}

	public DtoEntityMappingException(Throwable cause) {
		super(cause);
	}

}
