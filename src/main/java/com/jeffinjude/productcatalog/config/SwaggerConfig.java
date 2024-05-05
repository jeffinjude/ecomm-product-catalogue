package com.jeffinjude.productcatalog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "Product Catalogue Service API",
	        description = "This api manages the product catalogue.",
	        summary = "This api can add a product, get all products, get details of a product, update a product and delete a product.",
	        contact = @Contact(
	        			name = "Jeffin Pulickal",
	        			email = "test@test.com"
	        		),
	        version = "v1"
	    )
	)
public class SwaggerConfig {
	//Access swagger at http://localhost:8091/swagger-ui/index.html
}
