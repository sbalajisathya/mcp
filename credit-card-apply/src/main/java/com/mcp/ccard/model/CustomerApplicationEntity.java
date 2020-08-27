/*

 * Application: Credit Card Apply Service
 */

package com.mcp.ccard.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class CustomerApplicationEntity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CUSTOMER_APPLIACTION")
public class CustomerApplicationEntity {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The customer detail. */
    @OneToOne(cascade = CascadeType.ALL)
	private Customer customerDetail;
    
	/** The credit card number. */
	private String creditCardNumber;
	
	/** The application status. */
	private String applicationStatus;
	
	/** The application number. */
	private String applicationNumber;

}
