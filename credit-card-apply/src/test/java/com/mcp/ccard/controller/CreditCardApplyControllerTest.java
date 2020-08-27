/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mcp.ccard.controller.CreditCardApplyController;
import com.mcp.ccard.domain.CustomerDetail;
import com.mcp.ccard.domain.ResponseMessage;
import com.mcp.ccard.service.CreditCardApplyService;



/**
 * The Class CreditCardApplyControllerTest.
 */
@ExtendWith(SpringExtension.class)
public class CreditCardApplyControllerTest {

	/** The credit card apply service. */
	@Mock
	CreditCardApplyService creditCardApplyService;

	/** The credit card apply controller. */
	@InjectMocks
	CreditCardApplyController creditCardApplyController;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test search credit card application information.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSearchCreditCardApplicationInformation() throws Exception {
		CustomerDetail applicationForm  = new CustomerDetail();
		ResponseMessage respMsg = new ResponseMessage();
		respMsg.setCode("API-101");
		respMsg.setMessage("Application added successfully. Acknowledgement Number : fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		 ResponseEntity<Object> response =  ResponseEntity.status(HttpStatus.CREATED).body(respMsg);
		when(creditCardApplyService.addCreditCardApplciationInfo(applicationForm)).thenReturn(response);
		ResponseEntity<Object> controllerResponse = creditCardApplyController.addCreditCardApplicationRequest(applicationForm);
		assertEquals( HttpStatus.CREATED,controllerResponse.getStatusCode());
	}
	
	
}
