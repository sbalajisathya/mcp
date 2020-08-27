/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcp.ccard.config.CryptoUtil;
import com.mcp.ccard.config.MailService;
import com.mcp.ccard.config.SchemaValidator;
import com.mcp.ccard.model.CustomerApplicationEntity;
import com.mcp.ccard.repository.CustomerApplicationRepository;
import com.mcp.ccard.service.CreditCardMgmtServiceImpl;



/**
 * The Class CreditCardMgmtServiceImplTest.
 */
@ExtendWith(SpringExtension.class)
class CreditCardMgmtServiceImplTest {


	/** The schema validator. */
	@Mock
	SchemaValidator schemaValidator;

	/** The mail service. */
	@Mock
	MailService mailService;

	/** The crypto util. */
	@Mock
	CryptoUtil cryptoUtil;
	
	/** The repository. */
	@Spy
	CustomerApplicationRepository repository;

	/** The credit card mgmt service impl. */
	@InjectMocks
	CreditCardMgmtServiceImpl creditCardMgmtServiceImpl;
	
	/** The mapper. */
	ObjectMapper mapper;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mapper = new ObjectMapper();
	}

	/**
	 * Test find credit card application info success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFindCreditCardApplicationInfo_success() throws Exception {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("pendingApplicationForm.json"), CustomerApplicationEntity.class);
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(applicationEntity);
		ResponseEntity<Object> controllerResponse =	creditCardMgmtServiceImpl.findCreditCardApplicationInfo("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	/**
	 * Test find all credit card application by status success.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFindAllCreditCardApplicationByStatus_success() throws JsonParseException, JsonMappingException, IOException  {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("pendingApplicationForm.json"), CustomerApplicationEntity.class);
		List<CustomerApplicationEntity> applications = new ArrayList<>();
		applications.add(applicationEntity);
		when(repository.findByApplicationStatus("PENDING")).thenReturn(applications);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.findAllCreditCardApplicationByStatus("PENDING");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	/**
	 * Test process credit card application success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testProcessCreditCardApplication_success() throws Exception {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("pendingApplicationForm.json"), CustomerApplicationEntity.class);
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(applicationEntity);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.processCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	/**
	 * Test validate credit card application success.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidateCreditCardApplication_success() throws Exception {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("pendingApplicationForm.json"), CustomerApplicationEntity.class);
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(applicationEntity);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.validateCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.OK,controllerResponse.getStatusCode());
	}

	
	/**
	 * Test process credit card application approved record fail.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testProcessCreditCardApplication_approved_record_fail() throws Exception  {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("approvedApplicationForm.json"), CustomerApplicationEntity.class);
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(applicationEntity);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.processCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.BAD_REQUEST,controllerResponse.getStatusCode());
	}
	
	/**
	 * Test find credit card application info record not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testFindCreditCardApplicationInfo_record_not_found() throws Exception {
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(null);
		ResponseEntity<Object> controllerResponse =	creditCardMgmtServiceImpl.findCreditCardApplicationInfo("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.NOT_FOUND,controllerResponse.getStatusCode());
	}

	/**
	 * Test find all credit card application by status record not found.
	 *
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testFindAllCreditCardApplicationByStatus_record_not_found() throws JsonParseException, JsonMappingException, IOException  {
		when(repository.findByApplicationStatus("PENDING")).thenReturn(null);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.findAllCreditCardApplicationByStatus("PENDING");
		assertEquals( HttpStatus.NOT_FOUND,controllerResponse.getStatusCode());
	}

	/**
	 * Test process credit card application record not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testProcessCreditCardApplication_record_not_found() throws Exception {
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(null);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.processCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.NOT_FOUND,controllerResponse.getStatusCode());
	}

	/**
	 * Test validate credit card application record not found.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidateCreditCardApplication_record_not_found() throws Exception {
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(null);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.validateCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.NOT_FOUND,controllerResponse.getStatusCode());
	}
	
	/**
	 * Test validate credit card application validation failed.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidateCreditCardApplication_validation_failed() throws Exception {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("validationFailApplicationForm.json"), CustomerApplicationEntity.class);
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(applicationEntity);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.validateCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.BAD_REQUEST,controllerResponse.getStatusCode());
	}
	
	/**
	 * Test process credit card application validation failed.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testProcessCreditCardApplication_validation_failed() throws Exception {
		CustomerApplicationEntity applicationEntity = mapper.readValue(this.getClass().getClassLoader().getResourceAsStream("validationFailApplicationForm.json"), CustomerApplicationEntity.class);
		when(repository.findByApplicationNumber("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9")).thenReturn(applicationEntity);
		ResponseEntity<Object> controllerResponse = creditCardMgmtServiceImpl.processCreditCardApplication("fdb9e2e0-aaa0-4f96-8e1f-db19cc6ff4f9");
		assertEquals( HttpStatus.BAD_REQUEST,controllerResponse.getStatusCode());
	}
	
}
