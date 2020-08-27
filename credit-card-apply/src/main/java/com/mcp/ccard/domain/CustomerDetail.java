/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class CustomerDetail.
 */
@NoArgsConstructor
@Data
public class CustomerDetail {

	/** The contact detail. */
	private ContactDetail contactDetail;
	
	/** The personal detail. */
	private PersonalDetail personalDetail;
	
	/** The professional detail. */
	private ProfessionalDetail professionalDetail;
	
	/** The addresses. */
	private List<Address> addresses;

}
