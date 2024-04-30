package com.jeffinjude.productcatalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	
	@Value("${ecomm.product.teststatus}")
	private String testProp;

	
	@GetMapping("test")
	public ResponseEntity<String> test() {	
		return ResponseEntity.ok("Product Catalogue Test status: " + testProp);
	}
}
