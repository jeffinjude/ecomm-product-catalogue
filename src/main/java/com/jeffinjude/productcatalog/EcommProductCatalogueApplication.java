package com.jeffinjude.productcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcommProductCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommProductCatalogueApplication.class, args);
	}

}
