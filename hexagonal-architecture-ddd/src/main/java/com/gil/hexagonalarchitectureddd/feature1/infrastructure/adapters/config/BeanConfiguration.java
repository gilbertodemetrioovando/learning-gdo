package com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.gil.hexagonalarchitectureddd.feature1.domain.service.ProductService;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.mapper.ProductMapper;
import com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.output.persistence.repository.ProductRepository;

@Configuration
public class BeanConfiguration {

	@Bean
	@Primary
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ProductMapper productMapper(ModelMapper modelMapper) {
		return new ProductMapper(modelMapper);
	}

	@Bean
	public ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository,
			ProductMapper productMapper) {
		return new ProductPersistenceAdapter(productRepository, productMapper);
	}

	@Bean
	public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter) {
		return new ProductService(productPersistenceAdapter);
	}
}
