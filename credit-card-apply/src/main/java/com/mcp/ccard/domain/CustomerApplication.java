/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.domain;



import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class CustomerApplication.
 */
@NoArgsConstructor
@Data
public class CustomerApplication{

	/** The customer detail. */
	private CustomerDetail customerDetail;
	
	/** The credit card number. */
	private String creditCardNumber;
	
	/** The application status. */
	private String applicationStatus;
	
	/** The application number. */
	private String applicationNumber;

}
