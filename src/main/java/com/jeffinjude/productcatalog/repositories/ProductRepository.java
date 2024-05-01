package com.jeffinjude.productcatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jeffinjude.productcatalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}