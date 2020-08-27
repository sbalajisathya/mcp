/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Customer.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	/** The contact detail. */
	@OneToOne(cascade = CascadeType.ALL)
	private ContactDetailEntity contactDetail;

	/** The personal detail. */
	@OneToOne(cascade = CascadeType.ALL)
	private PersonalDetailEntity personalDetail;

	/** The professional detail. */
	@OneToOne(cascade = CascadeType.ALL)
	private ProfessionalDetailEntity professionalDetail;
	
	/** The addresses. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	protected Collection<AddressEntity> addresses;

	/** The create date time. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDateTime;

	/** The update date time. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDateTime;

}
