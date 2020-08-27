/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.domain;

import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * The Class PersonalDetail.
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
