/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcp.ccard.config.CryptoUtil;
import com.mcp.ccard.config.SchemaValidator;
import com.mcp.ccard.domain.CustomerDetail;
import com.mcp.ccard.model.CustomerApplicationEntity;
import com.mcp.ccard.repository.CustomerApplicationRepository;
import com.mcp.ccard.service.CreditCardApplyServiceImpl;


/**
 * The Class CreditCardApplyServiceTest.
 */
@ExtendWith(SpringExtension.class)
public class CreditCardApplyServiceTest {

	/** The schema validator. */
	@Mock
	SchemaValidator schemaValidator;

	/** The crypto util. */
	@Mock
	CryptoUtil cryptoUtil;

	/** The repository. */
	@Spy
	CustomerApplicationRepository repository;

	/** The credit card apply service impl. */
	@InjectMocks
	CreditCardApplyServiceImpl creditCardApplyServiceImpl;

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
	 * Test add credit card applciation info.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAddCreditCardApplciationInfo() throws Exception {
		CustomerDetail applicationForm = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("applicationForm.json"), CustomerDetail.class);
		when(schemaValidator.validateApplicationForm(applicationForm)).thenCallRealMethod();
		ResponseEntity<Object> servResponse = creditCardApplyServiceImpl
				.addCreditCardApplciationInfo(applicationForm);
		assertEquals(HttpStatus.CREATED, servResponse.getStatusCode());
	}

	/**
	 * Test add credit card applciation info validation fail.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAddCreditCardApplciationInfo_validation_fail() throws Exception {
		CustomerDetail applicationForm = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("validationFailApplicationForm.json"),
				CustomerDetail.class);
		when(schemaValidator.validateApplicationForm(applicationForm)).thenCallRealMethod();
		ResponseEntity<Object> servResponse = creditCardApplyServiceImpl
				.addCreditCardApplciationInfo(applicationForm);
		assertEquals(HttpStatus.BAD_REQUEST, servResponse.getStatusCode());
	}

	/**
	 * Test add credit card applciation info customer already exists.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAddCreditCardApplciationInfo_customer_already_exists() throws Exception {
		CustomerDetail applicationForm = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("applicationForm.json"), CustomerDetail.class);
		when(schemaValidator.validateApplicationForm(applicationForm)).thenCallRealMethod();
		CustomerApplicationEntity applicationEntity = mapper.readValue(
				this.getClass().getClassLoader().getResourceAsStream("applicationFormEntity.json"),
				CustomerApplicationEntity.class);
		when(repository.findByCustomerDetail_PersonalDetail_Pancard(applicationForm.getPersonalDetail().getPancard()))
				.thenReturn(applicationEntity);
/*		Assertions.assertThrows(CustomerAlreadyFoundException.class, () -> {*/
			ResponseEntity<Object> servResponse = creditCardApplyServiceImpl
					.addCreditCardApplciationInfo(applicationForm);
			assertEquals(HttpStatus.BAD_REQUEST, servResponse.getStatusCode());
			/* }); */
	}

	/*
	 * @Test public void testAddCreditCardApplciationInfo_fallback_execution()
	 * throws Exception { CustomerDetail applicationForm = mapper.readValue(
	 * this.getClass().getClassLoader().getResourceAsStream("applicationForm.json"),
	 * CustomerDetail.class); ResponseEntity<Object> controllerResponse =
	 * creditCardApplyServiceImpl.fallBackAddCreditAppln(applicationForm);
	 * assertEquals(HttpStatus.BAD_REQUEST, controllerResponse.getStatusCode()); }
	 */

}
