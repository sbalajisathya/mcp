/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.config;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.mcp.ccard.domain.CustomerDetail;


/**
 * The Class SchemaValidator.
 */
@Component
public class SchemaValidator {

	/**
	 * Validate application form method.
	 *
	 * @param customer the customer
	 * @return the processing report
	 * @throws Exception the exception
	 */
	public ProcessingReport validateApplicationForm(CustomerDetail customer) throws Exception {
		InputStream jsonSchemaFile = this.getClass().getClassLoader()
				.getResourceAsStream("application-form-schema.json");
		JsonNode jsonSchemaNode = new ObjectMapper().readTree(jsonSchemaFile);
		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		final JsonSchema schema = factory.getJsonSchema(jsonSchemaNode);
		return schema
				.validate(new ObjectMapper().readTree(new ObjectMapper().writeValueAsString(customer)));
	
	}
}
