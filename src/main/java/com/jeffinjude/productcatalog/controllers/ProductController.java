package com.jeffinjude.productcatalog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffinjude.productcatalog.entities.Product;
import com.jeffinjude.productcatalog.services.ProductService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
	
	@Value("${ecomm.product.teststatus}")
	private String testProp;

	@Autowired
	ProductService productService;
	
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@GetMapping("test")
	public ResponseEntity<String> test() {	
		return ResponseEntity.ok("Product Catalogue Test status: " + testProp);
	}
	
	@PostMapping("add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Product createdProduct = productService.addProductService(product);
			log.info("Inside addProduct. Created product: " + createdProduct.toString());
			responseEntity = new ResponseEntity<>(createdProduct, HttpStatus.OK);
		}
		catch(Exception e) {
			log.debug("Inside addProduct. Exception: " + e.getMessage());
		}
		
		return responseEntity;
	}
	
	@GetMapping("list")
	public ResponseEntity<List<Product>> getAllProducts() {
		ResponseEntity<List<Product>> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			List<Product> fetchedProducts = productService.getAllProductsService();
			log.info("Inside getAllProducts. Fetched products: " + fetchedProducts.toString());
			responseEntity = new ResponseEntity<>(fetchedProducts, HttpStatus.OK);
		}
		catch(Exception e) {
			log.debug("Inside getAllProducts. Exception: " + e.getMessage());
		}
		
		return responseEntity;
	}
	
	@GetMapping("details/{id}")
	public ResponseEntity<Product> getProductDetails(@PathVariable("id") int id) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Product> fetchedProduct = productService.getProductDetailsService(id);
			log.info("Inside getProductDetails. Fetched product: " + fetchedProduct.toString());
			if(fetchedProduct.isPresent()) {
				responseEntity = new ResponseEntity<>(fetchedProduct.get(), HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			log.debug("Inside getProductDetails. Exception: " + e.getMessage());
		}
		
		return responseEntity;
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
		ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Product> fetchedProduct = productService.getProductDetailsService(id);
			log.info("Inside updateProduct. Fetched product: " + fetchedProduct.toString());
			if(fetchedProduct.isPresent()) {
				
				product.setProductId(fetchedProduct.get().getProductId());
				
				if(product.getProductName() == null) {
					product.setProductName(fetchedProduct.get().getProductName());
				}
				if(product.getProductDetails() == null) {
					product.setProductDetails(fetchedProduct.get().getProductDetails());
				}
				if(product.getProductPrice() == 0) {
					product.setProductPrice(fetchedProduct.get().getProductPrice());
				}
				
				Product updatedProduct = productService.addProductService(product);
				responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			log.debug("Inside updateProduct. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteProductDetails(@PathVariable("id") int id) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Product> fetchedProduct = productService.getProductDetailsService(id);
			log.info("Inside deleteProductDetails. Fetched product: " + fetchedProduct.toString());
			if(fetchedProduct.isPresent()) {
				productService.deleteProductService(id);
				responseEntity = new ResponseEntity<>("Product deleted.", HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<>("Product does not exist.", HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			log.debug("Inside deleteProductDetails. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
}

