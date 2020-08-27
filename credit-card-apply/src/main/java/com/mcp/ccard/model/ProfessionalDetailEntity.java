/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class ProfessionalDetailEntity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PROFESSIONAL_INFORMATION")
public class ProfessionalDetailEntity {
	
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/** The profession name. */
	private String professionName;
	
	/** The company name. */
	private String companyName;
	
	/** The designation name. */
	private String designationName;
	
	/** The gross annual income. */
	private long grossAnnualIncome;
}
