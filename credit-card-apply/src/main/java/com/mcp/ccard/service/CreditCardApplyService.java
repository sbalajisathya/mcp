/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.service;

import org.springframework.http.ResponseEntity;

import com.mcp.ccard.domain.CustomerDetail;


/**
 * The Interface CreditCardApplyService.
 */
public interface CreditCardApplyService {
	
	/**
	 * Adds the credit card applciation info method.
	 *
	 * @param customerDto the customer dto
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<Object> addCreditCardApplciationInfo(CustomerDetail customerDto) throws Exception ;

}
