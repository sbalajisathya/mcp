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
 * The Class PersonalDetailEntity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "PERSONAL_INFORMATION")
public class PersonalDetailEntity {
	
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
