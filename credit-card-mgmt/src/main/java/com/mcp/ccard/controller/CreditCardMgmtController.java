/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mcp.ccard.service.CreditCardMgmtService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * The Class CreditCardMgmtController.
 */
@RestController
@RequestMapping("ccmgmt")
public class CreditCardMgmtController {

	/** The credit card mgmt service. */
	@Autowired
	CreditCardMgmtService creditCardMgmtService;

	/**
	 * Search credit card application information.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ApiOperation(value = "SearchCreditCardApplication", notes = "Search Credit Card Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/findCreditCardApplicationByApplnNo")
	public ResponseEntity<Object> searchCreditCardApplicationInformation(@RequestParam String applicationNumber) throws Exception {
		return creditCardMgmtService.findCreditCardApplicationInfo(applicationNumber);
	}

	/**
	 * Find all credit card applications.
	 *
	 * @param status the status
	 * @return the response entity
	 */
	@ApiOperation(value = "SearchCreditCardApplicationByStatus", notes = "Search All CreditCard Applications By Status")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/findCreditCardApplicationsByStatus")
	public ResponseEntity<Object> findAllCreditCardApplications(@RequestParam String status) {
		return creditCardMgmtService.findAllCreditCardApplicationByStatus(status);
	}

	/**
	 * Validate credit card application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ApiOperation(value = "ValidateCreditCardApplication", notes = "Validate Credit Card Application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/validateCreditCardApplication")
	public ResponseEntity<Object> validateCreditCardApplication(@RequestParam String applicationNumber)
			throws Exception {
		return creditCardMgmtService.validateCreditCardApplication(applicationNumber);
	}

	/**
	 * Process credit card application.
	 *
	 * @param applicationNumber the application number
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@ApiOperation(value = "ProcessCreditCardApplication", notes = "Process Credit Card Application By Application Number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/processCreditCardApplication")
	public ResponseEntity<Object> processCreditCardApplication(@RequestParam String applicationNumber)
			throws Exception {
		return creditCardMgmtService.processCreditCardApplication(applicationNumber);
	}

}
