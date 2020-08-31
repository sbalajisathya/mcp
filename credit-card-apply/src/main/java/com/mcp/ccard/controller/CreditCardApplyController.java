/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcp.ccard.domain.CustomerDetail;
import com.mcp.ccard.service.CreditCardApplyService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class CreditCardApplyController.
 */
@RestController
@RequestMapping("ccapply")
public class CreditCardApplyController {
	
	private static final Logger log = LoggerFactory.getLogger(CreditCardApplyController.class);

	/** The credit card apply service. */
	@Autowired
	CreditCardApplyService creditCardApplyService;

	/**
	 * Adds the credit card application request.
	 *
	 * @param customerDto the customer dto
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping(path = "/addCreditCardApplication")
	@ApiOperation(value = "AddCreditCardApplication", notes = "Add Credit Card Application")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<Object> addCreditCardApplicationRequest(@RequestBody CustomerDetail customerDto)
			throws Exception {
		log.debug("Method started!!");
		return creditCardApplyService.addCreditCardApplciationInfo(customerDto);
	}

}
