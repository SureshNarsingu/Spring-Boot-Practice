package com.movie.config;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	/** The title for the spring boot service to be displayed on swagger UI. */
	@Value("${swagger.title}")
	private String title;

	/** The description of the spring boot service. */
	@Value("${swagger.description}")
	private String description;

	/** The version of the service. */
	@Value("${swagger.version}")
	private String version;

	/** The terms of service url for the service if applicable. */
	@Value("${swagger.termsOfServiceUrl}")
	private String termsOfServiceUrl;

	/** The contact name for the service. */
	@Value("${swagger.contact.name}")
	private String contactName;

	/** The contact url for the service. */
	@Value("${swagger.contact.url}")
	private String contactURL;

	/** The contact email for the service. */
	@Value("${swagger.contact.email}")
	private String contactEmail;

	/** The license for the service if applicable. */
	@Value("${swagger.license}")
	private String license;

	/** The license url for the service if applicable. */
	@Value("${swagger.licenseUrl}")
	private String licenseURL;

	@Bean
    public OpenAPI api() {
        return new OpenAPI()
        		.info(new Info().title(title)
                .description(description)
                .contact(this.getContact().get())
                .version(version)
                .license(new License().name(license).url(licenseURL)))
                .externalDocs(new ExternalDocumentation()
                .description(description)
                .url(contactURL));
    }
	 private Supplier<Contact> getContact() {
	    	return () -> {
	    		Contact contact=new Contact();
	        	contact.setName(contactName);
	        	contact.setEmail(contactEmail);
	        	contact.setUrl(contactURL);
	        	return contact;
	    	};
	    }
}
