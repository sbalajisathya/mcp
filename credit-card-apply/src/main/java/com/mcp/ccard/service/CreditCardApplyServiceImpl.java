/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.mcp.ccard.config.CryptoUtil;
import com.mcp.ccard.config.SchemaValidator;
import com.mcp.ccard.domain.CustomerDetail;
import com.mcp.ccard.domain.ResponseMessage;
import com.mcp.ccard.model.Customer;
import com.mcp.ccard.model.CustomerApplicationEntity;
import com.mcp.ccard.repository.CustomerApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CreditCardApplyServiceImpl.
 */
@Component
public class CreditCardApplyServiceImpl implements CreditCardApplyService {
	
	private static final Logger log = LoggerFactory.getLogger(CreditCardApplyServiceImpl.class);

	/** The application repository. */
	@Autowired
	private CustomerApplicationRepository applicationRepository;

	/** The schema validator. */
	@Autowired
	private SchemaValidator schemaValidator;

	/** The crypto util. */
	@Autowired
	private CryptoUtil cryptoUtil;

	/** The mapper. */
	private final DozerBeanMapper mapper = new DozerBeanMapper();

	/**
	 * Adds the credit card applciation info method.
	 *
	 * @param customerDto the customer dto
	 * @return the response entity
	 */
	public ResponseEntity<Object> addCreditCardApplciationInfo(CustomerDetail customerDto) {
		log.debug("Method started!!");
		ResponseMessage respMsg = new ResponseMessage();

		ProcessingReport validationReport = schemaValidator.validateApplicationForm(customerDto);

		if (validationReport!=null && validationReport.isSuccess()) {
			Customer customer = mapper.map(customerDto, Customer.class);
			CustomerApplicationEntity application = null;
			application = applicationRepository
					.findByCustomerDetail_PersonalDetail_Pancard(customer.getPersonalDetail().getPancard());
			
			if (application != null && application.getApplicationNumber()!=null) {
				respMsg.setCode("API-100");
				respMsg.setMessage("Application Already Exists for this customer -" + application.getApplicationNumber());
				log.debug("Application Already Exists for this customer -" + application.getApplicationNumber());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respMsg);
			} else {
				application = new CustomerApplicationEntity();
			}

			customer.setCreateDateTime(new Date());
			customer.getContactDetail()
					.setMobileNumber(cryptoUtil.encrypt(customer.getContactDetail().getMobileNumber()));
			customer.getContactDetail().setEmailId(cryptoUtil.encrypt(customer.getContactDetail().getEmailId()));
			customer.getPersonalDetail().setPancard(cryptoUtil.encrypt(customer.getPersonalDetail().getPancard()));
			application.setCustomerDetail(customer);
			String applicationNumber = UUID.randomUUID().toString();
			application.setApplicationNumber(applicationNumber);
			application.setApplicationStatus("PENDING");
			applicationRepository.save(application);

			respMsg.setCode("API-101");
			respMsg.setMessage("Application added successfully. Acknowledgement Number : " + applicationNumber);
			log.debug("Application added successfully. Acknowledgement Number : " + applicationNumber);
			return ResponseEntity.status(HttpStatus.CREATED).body(respMsg);
		} else {
			respMsg.setCode("ERR-103");
			Optional.ofNullable(validationReport).ifPresent(report -> respMsg.setMessage("Input Validation Failed. : " + report.toString()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respMsg);
		}

	}

	
	

}
