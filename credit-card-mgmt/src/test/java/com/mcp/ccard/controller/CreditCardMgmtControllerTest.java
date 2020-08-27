/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mcp.ccard.controller.CreditCardMgmtController;
import com.mcp.ccard.domain.CustomerApplication;
import com.mcp.ccard.domain.ResponseMessage;
import com.mcp.ccard.service.CreditCardMgmtService;


/**
 * The Class CreditCardMgmtControllerTest.
 */
@ExtendWith(SpringExtension.class)
class CreditCardMgmtControllerTest {

	/** The credit card mgmt service. */
	@Mock
	CreditCardMgmtService creditCardMgmtService;

	/** The credit card mgmt controller. */
	@InjectMocks
	CreditCardMgmtController creditCardMgmtController;

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
		CustomerApplication applicationDto = new CustomerApplication();
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).body(applicationDto);
		when(creditCardMgmtService.findCreditCardApplicationInfo(Mockito.anyString())).thenReturn(response);
		ResponseEntity<Object> controllerResponse = creditCardMgmtController
				.searchCreditCardApplicationInformation("345678");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	/**
	 * Test find all credit card application.
	 */
	@Test
	void testFindAllCreditCardApplication() {
		CustomerApplication applicationDto = new CustomerApplication();
		List<CustomerApplication> applications = new ArrayList<>();
		applications.add(applicationDto);
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).body(applications);
		when(creditCardMgmtService.findAllCreditCardApplicationByStatus(Mockito.anyString())).thenReturn(response);
		ResponseEntity<Object> controllerResponse = creditCardMgmtController.findAllCreditCardApplications("PENDING");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	/**
	 * Test validate credit card application.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testValidateCreditCardApplication() throws Exception {
		ResponseMessage respMsg = new ResponseMessage();
		respMsg.setCode("API-106");
		respMsg.setMessage("Valiadtion Success!!");
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).body(respMsg);
		when(creditCardMgmtService.validateCreditCardApplication(Mockito.anyString())).thenReturn(response);
		ResponseEntity<Object> controllerResponse = creditCardMgmtController.validateCreditCardApplication("345678");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	/**
	 * Test process credit card application.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testProcessCreditCardApplication() throws Exception {
		ResponseMessage respMsg = new ResponseMessage();
		respMsg.setCode("API-102");
		respMsg.setMessage("Application Approved successfully.");
		ResponseEntity<Object> response = ResponseEntity.status(HttpStatus.OK).body(respMsg);
		when(creditCardMgmtService.processCreditCardApplication(Mockito.anyString())).thenReturn(response);
		ResponseEntity<Object> controllerResponse = creditCardMgmtController
				.processCreditCardApplication("345678");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

}
