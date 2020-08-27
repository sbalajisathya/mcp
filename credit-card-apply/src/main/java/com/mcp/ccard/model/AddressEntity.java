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
 * The Class AddressEntity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Address")
public class AddressEntity {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
