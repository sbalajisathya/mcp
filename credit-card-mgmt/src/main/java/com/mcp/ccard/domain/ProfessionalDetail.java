/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ProfessionalDetail.
 */
@NoArgsConstructor
@Data
public class ProfessionalDetail {
	
	/** The profession name. */
	private String professionName;
	
	/** The company name. */
	private String companyName;
	
	/** The designation name. */
	private String designationName;
	
	/** The gross annual income. */
	private long grossAnnualIncome;
	
}
