/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.service;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mcp.ccard.config.CryptoUtil;
import com.mcp.ccard.config.MailService;
import com.mcp.ccard.config.SchemaValidator;
import com.mcp.ccard.domain.CustomerApplication;
import com.mcp.ccard.domain.ResponseMessage;
import com.mcp.ccard.model.CustomerApplicationEntity;
import com.mcp.ccard.repository.CustomerApplicationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * The Class CreditCardMgmtServiceImpl.
 */
@Component
public class CreditCardMgmtServiceImpl implements CreditCardMgmtService {
	
	private static final Logger log = LoggerFactory.getLogger(CreditCardMgmtServiceImpl.class);

	/** The application repository. */
	@Autowired
	private CustomerApplicationRepository applicationRepository;

	/** The schema validator. */
	@Autowired
	private SchemaValidator schemaValidator;

	/** The mail service. */
	@Autowired
	private MailService mailService;

	/** The crypto util. */
	@Autowired
	private CryptoUtil cryptoUtil;

	/** The Constant mapper. */
	private static final DozerBeanMapper mapper = new DozerBeanMapper();

	/** The Constant API_102. */
	private static final String API_102 ="API-102";
	
	/** The Constant API_103. */
	private static final String API_103 ="API-103";
	
	/** The Constant API_104. */
	private static final String API_104 ="API-104";
	
	/** The Constant API_105. */
	private static final String API_105 ="API-105";
	
	/** The Constant API_106. */
	private static final String API_106 ="API-106";
	
	/** The Constant API_107. */
	private static final String API_107 ="API-107";
	
	/** The Constant APPLICATION_NOT_FOUND. */
	private static final String APPLICATION_NOT_FOUND ="APPLICATION_NOT_FOUND";

	/**
	 * Find credit card application information.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@HystrixCommand(fallbackMethod = "fallBackFindCreditAppln")
	public ResponseEntity<Object> findCreditCardApplicationInfo(String applicationNumber) throws Exception {
		log.debug("Service - findCreditCardApplicationInfo -Method started!!");
		CustomerApplicationEntity applicationEntity = applicationRepository.findByApplicationNumber(applicationNumber);
		if (applicationEntity == null) {
			throw new Exception("APPLICATION NOT FOUND");
		}

		CustomerApplication applicationDto = mapper.map(applicationEntity, CustomerApplication.class);

		return ResponseEntity.status(HttpStatus.OK).body(applicationDto);
	}

	/**
	 * Find all credit card application by status.
	 *
	 * @param status the status
	 * @return the response entity
	 */
	public ResponseEntity<Object> findAllCreditCardApplicationByStatus(String status) {
		log.debug("Service - findAllCreditCardApplicationByStatus -Method started!!");
		List<CustomerApplicationEntity> applications = applicationRepository.findByApplicationStatus(status);
		if (applications == null) {
			ResponseMessage respMsg = new ResponseMessage();
			respMsg.setCode(API_103);
			respMsg.setMessage(APPLICATION_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respMsg);
		}

		List<CustomerApplication> customerApplications = applications.stream()
				.map(applicationEntity -> mapper.map(applicationEntity, CustomerApplication.class))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(customerApplications);
	}

	/**
	 * Process credit card application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<Object> processCreditCardApplication(String applicationNumber) throws Exception {
		log.debug("Service - processCreditCardApplication -Method started!!");
		String creditCardNumber = UUID.randomUUID().toString();
		CustomerApplicationEntity application = applicationRepository.findByApplicationNumber(applicationNumber);
		
		if (application == null) {
			ResponseMessage respMsg = new ResponseMessage();
			respMsg.setCode(API_103);
			respMsg.setMessage(APPLICATION_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respMsg);
		} else if ("APPROVED".equals(application.getApplicationStatus())) {
			ResponseMessage respMsg = new ResponseMessage();
			respMsg.setCode(API_104);
			respMsg.setMessage("Application Already Processed");
			log.debug("Application Already Processed");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respMsg);
		} else if (!validationCheck(application)) {
			ResponseMessage respMsg = new ResponseMessage();
			respMsg.setCode(API_107);
			respMsg.setMessage("Credit Card Application Process Failed Due to Validation Failed!! ");
			log.debug("Credit Card Application Process Failed Due to Validation Failed!! ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respMsg);
		}
		application.setApplicationStatus("APPROVED");
		log.debug("APPROVED");
		application.setCreditCardNumber(cryptoUtil.encrypt(creditCardNumber));
		applicationRepository.save(application);

		Map<String, String> emailData = new HashMap<>();
		emailData.put("emailId", application.getCustomerDetail().getContactDetail().getEmailId());
		emailData.put("fullName", application.getCustomerDetail().getContactDetail().getFullName());
		emailData.put("applicationNumber", applicationNumber);

		mailService.sendSuccessAcknowledgement(emailData);

		ResponseMessage respMsg = new ResponseMessage();
		respMsg.setCode(API_102);
		respMsg.setMessage("Application Approved successfully.");
		log.debug("Application Approved successfully.");
		return ResponseEntity.status(HttpStatus.OK).body(respMsg);
	}

	/**
	 * Validate credit card application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	public ResponseEntity<Object> validateCreditCardApplication(String applicationNumber) throws Exception {
		log.debug("Service - validateCreditCardApplication -Method started!!");
		CustomerApplicationEntity applicationEntity = applicationRepository.findByApplicationNumber(applicationNumber);
		
		if (applicationEntity == null) {
			ResponseMessage respMsg = new ResponseMessage();
			respMsg.setCode(API_103);
			respMsg.setMessage(APPLICATION_NOT_FOUND);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respMsg);
		} else if (!validationCheck(applicationEntity)) {

			Map<String, String> emailData = new HashMap<>();
			emailData.put("emailId", applicationEntity.getCustomerDetail().getContactDetail().getEmailId());
			emailData.put("fullName", applicationEntity.getCustomerDetail().getContactDetail().getFullName());
			emailData.put("applicationNumber", applicationNumber);
			mailService.sendFailureAcknowledgement(emailData);

			ResponseMessage respMsg = new ResponseMessage();
			respMsg.setCode(API_105);
			respMsg.setMessage("Validation Failed. Sent Acknowledgement Status to Customer");
			log.debug("Validation Failed. Sent Acknowledgement Status to Customer");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respMsg);
		}

		ResponseMessage respMsg = new ResponseMessage();
		respMsg.setCode(API_106);
		respMsg.setMessage("Valiadtion Success!!");
		log.debug("Valiadtion Success!!");
		return ResponseEntity.status(HttpStatus.OK).body(respMsg);
	}

	/**
	 * Customer application validation check.
	 *
	 * @param applicationEntity the application entity
	 * @return true, if successful
	 */
	public boolean validationCheck(CustomerApplicationEntity applicationEntity) {
		log.debug("Service - validationCheck -Method started!!");
		boolean validationStatus = false;
		if (applicationEntity.getCustomerDetail().getProfessionalDetail().getGrossAnnualIncome() >= 500000) {
			return true;
		}
		return validationStatus;
	}

	
	/**
	 * Fall back find credit application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 */
	public ResponseEntity<Object> fallBackFindCreditAppln(String applicationNumber) {
		log.debug("Service - fallBackFindCreditAppln -Method started!!");
		ResponseMessage respMsg = new ResponseMessage();
		respMsg.setCode(API_103);
		respMsg.setMessage(APPLICATION_NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respMsg);
	}
	
}
