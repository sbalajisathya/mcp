/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.domain;

import lombok.Data;


/**
 * The Class ResponseMessage.
 */
@Data
public class ResponseMessage {

	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
}
