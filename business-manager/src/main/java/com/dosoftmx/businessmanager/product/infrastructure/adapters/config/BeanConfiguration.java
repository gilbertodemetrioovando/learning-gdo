package com.dosoftmx.businessmanager.product.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.dosoftmx.businessmanager.product.domain.service.ProductService;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.mapper.ProductMapper;
import com.dosoftmx.businessmanager.product.infrastructure.adapters.output.persistence.repository.ProductRepository;

@Configuration
public class BeanConfiguration {

	@Bean
	@Primary
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	ProductMapper productMapper(ModelMapper modelMapper) {
		return new ProductMapper(modelMapper);
	}

	@Bean
	ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository,
			ProductMapper productMapper) {
		return new ProductPersistenceAdapter(productRepository, productMapper);
	}

	@Bean
	ProductService productService(ProductPersistenceAdapter productPersistenceAdapter) {
		return new ProductService(productPersistenceAdapter);
	}
}
