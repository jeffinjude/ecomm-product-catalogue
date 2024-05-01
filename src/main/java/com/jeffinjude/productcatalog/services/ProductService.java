package com.jeffinjude.productcatalog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffinjude.productcatalog.entities.Product;
import com.jeffinjude.productcatalog.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    ProductRepository productRepository;
	
	public Product addProductService(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProductsService() {
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductDetailsService(int id) {
		return productRepository.findById(id);
	}
	
	public void deleteProductService(int id) {
		productRepository.deleteById(id);
	}
	
}
