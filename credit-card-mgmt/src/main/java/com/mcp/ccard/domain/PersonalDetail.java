/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class PersonalDetail.
 */
@NoArgsConstructor
@Data
public class PersonalDetail {

	/** The father name. */
	private String fatherName;
	
	/** The date of birth. */
	private String dateOfBirth;
	
	/** The marital status. */
	private String maritalStatus;
	
	/** The citizenship. */
	private String citizenship;
	
	/** The residential status. */
	private String residentialStatus;
	
	/** The pancard. */
	private String pancard;
	
	
	
}
