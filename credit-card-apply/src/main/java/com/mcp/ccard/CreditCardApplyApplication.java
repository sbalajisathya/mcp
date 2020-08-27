/*

 * Application: Credit Card Apply Service
 */
package com.mcp.ccard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


/**
 * The Class CreditCardApplyApplication.
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
public class CreditCardApplyApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplyApplication.class, args);
	}

}
