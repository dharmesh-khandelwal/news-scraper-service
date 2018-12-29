/**
 * 
 */
package com.handzap.newsscraper.dto;

import java.util.List;

/**
 * @author Dharmesh Khandelwal
 * @since 1.0.0
 *
 */
public class ResponseDTO<T> {

	private List<T> response;

	/**
	 * @return the response
	 */
	public List<T> getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(List<T> response) {
		this.response = response;
	}

}
