/*

 * Application: Zipkin Service
 */
package com.mcp.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import zipkin.server.EnableZipkinServer;


/**
 * The Class ZipkinServerApplication.
 */
@SpringBootApplication
@EnableZipkinServer
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ZipkinServerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}

}
