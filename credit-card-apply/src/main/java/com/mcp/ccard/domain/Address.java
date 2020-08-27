/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class Address.
 */
@NoArgsConstructor
@Data
public class Address {
	
	/** The door no. */
	private String doorNo;
	
	/** The street name. */
	private String streetName;
	
	/** The city. */
	private String city;
	
	/** The state. */
	private String state;
	
	/** The country. */
	private String country;
	
	/** The zip code. */
	private String zipCode;
	
	/** The address type. */
	private String addressType;

}
