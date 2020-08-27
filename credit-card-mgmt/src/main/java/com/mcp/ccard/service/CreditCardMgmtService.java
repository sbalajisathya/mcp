/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.service;

import org.springframework.http.ResponseEntity;


/**
 * The Interface CreditCardMgmtService.
 */
public interface CreditCardMgmtService {
	
	/**
	 * Find all credit card application by status.
	 *
	 * @param status the status
	 * @return the response entity
	 */
	public ResponseEntity<Object> findAllCreditCardApplicationByStatus(String status); ;
	
	/**
	 * Process credit card application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<Object> processCreditCardApplication(String applicationNumber) throws Exception;
	
	/**
	 * Find credit card application info.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<Object> findCreditCardApplicationInfo(String applicationNumber)throws Exception; 
	
	/**
	 * Validate credit card application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<Object> validateCreditCardApplication(String applicationNumber) throws Exception; 

}
