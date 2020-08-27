/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcp.ccard.model.CustomerApplicationEntity;


/**
 * The Interface CustomerApplicationRepository.
 */
@Repository
public interface CustomerApplicationRepository extends JpaRepository<CustomerApplicationEntity, Long> {
	
	/**
	 * Find application by application number.
	 *
	 * @param applicationNumber the application number
	 * @return the customer application entity
	 */
	CustomerApplicationEntity findByApplicationNumber(String applicationNumber);

	/**
	 * Find application by customer detail personal detail pancard.
	 *
	 * @param panNumber the pan number
	 * @return the customer application entity
	 */
	CustomerApplicationEntity findByCustomerDetail_PersonalDetail_Pancard(String panNumber);

	/**
	 * Find application by application status.
	 *
	 * @param status the status
	 * @return the list
	 */
	List<CustomerApplicationEntity> findByApplicationStatus(String status);
}