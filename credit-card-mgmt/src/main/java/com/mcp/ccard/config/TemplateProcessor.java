/*

 * Application: Credit Card Management Service
 */
package com.mcp.ccard.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;


/**
 * The Class TemplateProcessor.
 */
@Component
public class TemplateProcessor {

	/** The template configuration. */
	@Autowired
    Configuration templateConfiguration;
	
	 /**
 	 * Gets the email content method.
 	 *
 	 * @param model the model
 	 * @param templateName the template name
 	 * @return the email content
 	 * @throws Exception the exception
 	 */
 	public String getEmailContent(Map<String, String> model, String templateName) throws Exception {
	        String emailContent =null;
	        try {
	        	emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(templateConfiguration.getTemplate(templateName), model);
	          
	        } catch (Exception e) {
	            throw new Exception ("Exception occured while processing email template:" + e.getMessage());
	        }
	        return emailContent;
	    }
}
