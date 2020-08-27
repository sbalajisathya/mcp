/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class ContactDetail.
 */
@NoArgsConstructor
@Data
public class ContactDetail {

	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The full name. */
	private String fullName;
	
	/** The mobile number. */
	private String mobileNumber;
	
	/** The email id. */
	private String emailId;
	
}
