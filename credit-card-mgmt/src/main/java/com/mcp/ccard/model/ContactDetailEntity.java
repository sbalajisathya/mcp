/*

 * Application: Credit Card Management Service
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
 * The Class ContactDetailEntity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CONTACT_INFORMATION")
public class ContactDetailEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
